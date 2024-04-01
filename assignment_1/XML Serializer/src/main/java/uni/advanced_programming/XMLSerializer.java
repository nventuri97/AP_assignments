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

    public static void serialize(Object [ ] arr, String fileName){

        ArrayList<String> xmlFile = new ArrayList<>();
        xmlFile.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        Map<String, IntrospectedClass> introspectedClasses = new HashMap<>();

        for(Object obj: arr){
            if(obj==null){
                //I'll go to the next element of the array
                continue;
            }

            Class<?> c=obj.getClass();
            introspectedClasses.putIfAbsent(c.getName(), new IntrospectedClass(c));
            IntrospectedClass iClass=introspectedClasses.get(c.getName());

            if(iClass.isAnnotated){
                xmlFile.add('<'+iClass.name+'>');
                for(IntrospectedField iField: iClass.fields){
                    if(!iField.isAnnotated){
                        continue;
                    }

                    try{
                        Field f=c.getDeclaredField(iField.name);
                        f.setAccessible(true);
                        String fieldName= iField.alias.equals("") ? iField.name : iField.alias;
                        xmlFile.add("\t<" + fieldName + " type=\"" + f.getType() +"\">" + f.get(obj) + "</" + fieldName + ">");
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException("It is not possible to access the field", e);
                    }
                }
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

    private static class IntrospectedClass {
        private final String name;
        private final ArrayList<IntrospectedField> fields=new ArrayList<>();

        private final boolean isAnnotated;

        public IntrospectedClass(Class c) {
            this.name = c.getName();
            if (c.isAnnotationPresent(XMLable.class)) {
                this.isAnnotated = true;
                for (Field f : c.getDeclaredFields()) {
                    String fieldName = f.getName();
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
