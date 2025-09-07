package uni.advanced_programming;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLSerializer {

    /**
     * The method serialize the elements of an object array to produce an XML file
     * that represent the element of the array if they are serializable
     * @param arr the array to serialize
     * @param fileName the name of the XML file to produce
     */
    public static void serialize(Object [ ] arr, String fileName){

        //An ArrayList<String> that represent the XML file to produce
        ArrayList<String> xmlFile = new ArrayList<>();
        xmlFile.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        //An HashMap that keeps the introspected class as value and the name of the class as key
        Map<String, IntrospectedClass> introspectedClasses = new HashMap<>();

        for(Object obj: arr){
            if(obj==null){
                //I'll go to the next element of the array
                continue;
            }
            
            //The class of the object is taken, if it is absent from the array of the 
            //introspected classes a new class it is added to the array and then the instance
            //of the introspected classes is taken
            Class<?> c=obj.getClass();
            introspectedClasses.putIfAbsent(c.getName(), new IntrospectedClass(c));
            IntrospectedClass iClass=introspectedClasses.get(c.getName());

            //If the class is annotated
            if(iClass.isAnnotated){
                //A tag is added to the xmlFile ArrayList<String>
                xmlFile.add('<'+iClass.name+'>');
                //For each field of the class an IntrospectedField instance is created
                for(IntrospectedField iField: iClass.fields){
                    if(!iField.isAnnotated){
                        continue;
                    }

                    try{
                        //Using introspection the field with iField.name is taken and it is made accessible if not
                        Field f=c.getDeclaredField(iField.name);
                        Boolean alreadyAccessible=f.isAccessible();
                        if(!alreadyAccessible)
                            f.setAccessible(true);
                        //a row dedicated to the field is added to the XML file
                        String fieldName= iField.alias.equals("") ? iField.name : iField.alias;
                        xmlFile.add("\t<" + fieldName + " type=\"" + f.getType() +"\">" + f.get(obj) + "</" + fieldName + ">");
                        //if the field was not accessible the property is restored
                        if(!alreadyAccessible)
                            f.setAccessible(false);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException("It is not possible to access the field", e);
                    }
                }
                //The close tag for the class is addedd
                xmlFile.add("</"+iClass.name+">");
            } else {
                xmlFile.add("<notXMLable/>");
            }

            Path filePath = Paths.get(fileName);
            String finalXmlFile = String.join("\n", xmlFile);
            try {
                // Write the string to the file
                Files.write(filePath, finalXmlFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inner class to describe an introspected class
     */
    private static class IntrospectedClass {
        //Name of the introspected class
        private final String name;
        //Array containing the IntrospectedFields of the class
        private final ArrayList<IntrospectedField> fields=new ArrayList<>();
        //Boolean to understand if the class is annotated with XMLable annotation
        private final boolean isAnnotated;

        public IntrospectedClass(Class c) {
            this.name = c.getName();
            //Check if annotation is present
            if (c.isAnnotationPresent(XMLable.class)) {
                this.isAnnotated = true;
                //Construction of the InstropectedField array taking all the declared fields
                for (Field f : c.getDeclaredFields()) {
                    String fieldName = f.getName();
                    //Check if the field is also annotated and related insertion in the Array of IntrospectedField
                    if (f.isAnnotationPresent(XMLfield.class)) {
                        XMLfield xmlField = f.getAnnotation(XMLfield.class);
                        String nameAlias = xmlField.name();
                        String type = xmlField.type();
                        fields.add(new IntrospectedField(fieldName, nameAlias, true, type));
                    } else {
                        fields.add(new IntrospectedField(fieldName, "", false, ""));
                    }
                }
            } else {
                this.isAnnotated = false;
            }
        }
    }

    /**
     * Inner class to describe an introspected field
     */
    private static class IntrospectedField {

        private final String name;
        private final String alias;
        private final boolean isAnnotated;
        private final String type;

        public IntrospectedField(String name, String alias, boolean isAnnotated, String type) {
            this.name = name;
            this.alias = alias;
            this.isAnnotated = isAnnotated;
            this.type = type;
        }
    }
}
