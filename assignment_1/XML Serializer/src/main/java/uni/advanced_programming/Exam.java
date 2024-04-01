package uni.advanced_programming;

@XMLable
public class Exam {

    @XMLfield(type="String", name="name")
    private String examName;

    @XMLfield(type = "int")
    private int vote;

    @XMLfield(type="String", name="exam_date")
    private String examDate;

    @XMLfield(type="boolean")
    private boolean passed;

    public Exam() {
    }

    public Exam(String examName, int vote, String examDate, boolean passed) {
        this.examName = examName;
        this.vote = vote;
        this.examDate = examDate;
        this.passed = passed;
    }
}
