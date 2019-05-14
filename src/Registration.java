/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
public class Registration implements Comparable<Registration> {

    private Student student;
    private Course course;
    private int coins;
    private long timestamp;

    public Registration(Student student, Course course, int coins) {
        //constructor that sets studnet, course, and the coins the student puts
        this.student = student;
        this.course = course;
        this.coins = coins;
    }

    /**
     * geeter mthod for student
     * @return student
     */
    public Student getStudent() {
        return student; //XXX-CHANGE-XXX
    }

    /**
     * getter method for course
     * @return course
     */
    public Course getCourse() {
        return course; //XXX-CHANGE-XXX
    }

    /**
     * getter method for coins
     * @return coins
     */
    public int getCoins() {
        return coins; //XXX-CHANGE-XXX
    }

    /**
     * Compares this Student with another Student, by comparing their course
     * coins/timestamps If the coins of this is less, returns a negative
     * integer. If the coins of the Student received is less, returns a positive
     * integer. If the number of coins is same, use the timestamp comparison to
     * ensure FCFS. (You may want to check the implementation of System.nanoTime
     * to ensure correctness)
     *
     * @param o Student to be compared with
     * @return Result of the comparison
     */
    @Override
    public int compareTo(Registration o) {
        // compare which registration has priority
        // more coins more priority
        if (coins < o.coins)
            return -1;
        else if (coins > o.coins)
            return 1;
        // smaller timestamp, more priority
        else {
            if (timestamp > o.timestamp)
                return -1;
            else
                return 1;
        }
    }

    /**
     * Sets the timestamp inside this registration to be the current time in
     * nano seconds.
     */
    public void setTimestamp() {
        timestamp = System.nanoTime();
    }

    /**
     * check the current time, coins of one student
     * @return the student, the student's coin and timestamp for testing
     */
    public String toString() {
        // get the string of student, time, and coins
        return student.getStudentName() + " " + getCoins() + " " + timestamp;
    }
}
