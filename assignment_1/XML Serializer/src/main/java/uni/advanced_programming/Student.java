package uni.advanced_programming;

/**
 * Class representing a student, annoteted with XMLable annotation
 * @author nicola
 */
@XMLable
public class Student {

    @XMLfield(type = "String", name = "name")
    public String firstName;
    @XMLfield(type = "String", name = "surname")
    public String lastName;
    @XMLfield(type = "int")
    private int age;

    public Student() {
    }

    public Student(String fn, String ln, int age) {
        this.firstName = fn;
        this.lastName = ln;
        this.age = age;
    }
}
