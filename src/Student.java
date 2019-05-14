/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;
import java.util.List;

public class Student implements Student_Interface {

    private String studentID;
    private String name;
    private List<Course> myEnrolledCourses;
    private List<Course> myWaitlist;
    private int courseCoins;

    /**
     * Constructor that populates the instance variables with parameters passed
     *
     * @param id StudentID
     * @param name Name of the student
     * @param coins Course Coins
     */
    public Student(String id, String name, int coins) {
        //TODO
        this.studentID = id;
        this.name = name;
        this.courseCoins = coins;
        this.myEnrolledCourses = new ArrayList<Course>();
        this.myWaitlist = new ArrayList<Course>();
    }

    //TODO - Implement methods from the interface

    /**
     * Returns a string representation of the Student that includes the name and
     * the studentID
     *
     * @return String representation of the student
     */
    @Override
    public String toString() {
        return this.name + "(" + this.studentID + ")";
    }

    @Override
    public void enrollCourse(Course c) {
        myEnrolledCourses.add(c);
    }

    @Override
    public void waitlistCourse(Course c) {
        myWaitlist.add(c);
    }

    @Override
    public String getStudentName() {
        return name;
    }

    @Override
    public String getStudentID() {
        return studentID;
    }

    @Override
    public List<Course> getmyEnrolledCourses() {
        return myEnrolledCourses;
    }

    @Override
    public List<Course> getmyWaitlist() {
        return myWaitlist;
    }

    @Override
    public int getCoins() {
        return courseCoins;
    }

    @Override
    public void deductCoins(int numCoins) {
        courseCoins -= numCoins;
    }
}
