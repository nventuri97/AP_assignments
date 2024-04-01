package uni.advanced_programming;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Object[] arrObjs=new Object[4];

        arrObjs[0]=new Student("Nicola", "Venturi", 27);
        arrObjs[1]=new Object();
        arrObjs[2]=new Exam("ASE", 25, "17/01/2024", true);
        arrObjs[3]=new Exam("ICT RA", 30, "09/06/2023", true);

        XMLSerializer.serialize(arrObjs, "./carrier.xml");
    }
}