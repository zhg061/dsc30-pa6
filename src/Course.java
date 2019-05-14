/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;
import java.util.List;

/**
 * class that implements course
 */
public class Course implements Course_Interface {

	private String courseName;
	private String courseCode;
	private MyPriorityQueue<Registration> waitlistQueue;
	private List<Student> roster;
	private int maxCapacity;

    /**
     * constructors that set courseName, courseCode, and maxCapacity
     * waitlistQueue, and roster
     * @param name
     * @param code
     * @param capacity
     */
	public Course(String name, String code, int capacity) {
	//constructor for the class Course
        this.courseName = name;
        this.courseCode = code;
        this.maxCapacity = capacity;
        // set the new waitlistQueue to a new MyPriorityQueue
        this.waitlistQueue = new MyPriorityQueue<Registration>(capacity);
        this.roster = new ArrayList<>();
	}

	//override methods for Course_Interface

	@Override
	public String toString() {
		return courseCode;
	}

    /**
     * Accessor for course name
     *
     * @return course name
     */
    @Override
    public String getCourseName() {
        return courseName;
    }

    /**
     * Accessor for course code
     *
     * @return course code
     */
    @Override
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Accessor for course capacity
     *
     * @return course capacity
     */
    @Override
    public int getCourseCapacity() {
        return maxCapacity;
    }

    /**
     * Accessor for Course Roster
     *
     * @return course roster
     */
    @Override
    public List<Student> getCourseRoster() {
        return roster;
    }

    /**
     * Checks whether the course enrollment has reached its capacity
     *
     * @return Returns true if the number of enrolled students is equal to
     * capacity, false otherwise
     */
    @Override
    public boolean isFull() {
        return maxCapacity <= roster.size();
    }

    /**
     * Enqueues the student to the waitlist for this course
     *
     * @param r Registration to be waitlisted
     */
    @Override
    public void addToWaitlist(Registration r) {
        // takes a Registration object as a parameter and
        // adds it to the Priority Queue waitlist
        // (ensure that you record the timestamp while doing so,
        // which will be used to break ties in case the number of coins are equal).
        r.setTimestamp();
        waitlistQueue.offer(r);
        r.getStudent().waitlistCourse(this);
    }

    /**
     * Enrolls the next student in the waitlist to the course. Does nothing if
     * the waitlist is empty
     *
     * @return Registration Request that was processed
     */
    @Override
    public Registration processWaitlist(){
        //removes the next Registration from the Priority Queue
        // and enrolls the Student into the Course.
        // Note that you must update the Course Roster
        // as well as the Student's Enrollment List
        // when you enroll the student into the course.
        if (waitlistQueue.peek() == null)
            return null;
        Registration pollStudent = waitlistQueue.poll();
        Student student = pollStudent.getStudent();
        student.enrollCourse(pollStudent.getCourse());
        student.getmyWaitlist().remove(pollStudent.getCourse());
        roster.add(student);
        return pollStudent;
    }
    public MyPriorityQueue<Registration> waitlistGetter() {
         return waitlistQueue;
    }
}
