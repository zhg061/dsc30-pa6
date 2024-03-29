/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.io.*;
import java.util.*;

/**
 * Course Registration Manager
 *
 * @author //Insert name and pid here
 * @version 1.0
 * @since //Insert today's date
 */
public class CourseScheduling {

    public static List<Course> courseList = new LinkedList<>();
    public static List<Student> studentList = new LinkedList<>();

    /**
     * Read info file to establish course catalog and student list
     *
     * @param fname
     */
    public static void populateCourseandStudents(String fname) {
        File file = new File(fname);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            boolean loadCourse = true;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.isEmpty()) {
                    loadCourse = false;
                    scanner.nextLine();
                    continue;
                }
                Scanner wordscan = new Scanner(nextLine);
                if (loadCourse) {
                    String id = wordscan.next();
                    String cname = wordscan.next();
                    int capacity = wordscan.nextInt();
                    Course course = new Course(cname, id, capacity);
                    courseList.add(course);
                } else {
                    String sname = wordscan.next();
                    String pid = wordscan.next();
                    int coins = wordscan.nextInt();
                    Student student = new Student(pid, sname, coins);
                    studentList.add(student);
                }
                wordscan.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Output successful course enrollment/waitlisting
     *
     * @param s student identifier
     * @param c desired course
     * @param coins request weighting
     * @param enroll true if the student was enrolled
     */
    public static void print(Student s, Course c, int coins, boolean enroll) {
        String signup = ((enroll) ? "Enrolling " : "Waitlisting ");
        String amount = coins + ((coins == 1) ? " coin" : " coins");
        System.out.println(signup + s + " to Course " + c + " with " + amount);
    }

    /**
     * Output unsuccessful course enrollment/waitlisting
     *
     * @param s student identifier
     * @param c desired course
     * @param enroll true if the student was enrolled
     */
    public static void printFail(Student s, Course c, boolean enroll) {
        String signup = ((enroll) ? "enrolled" : "waitlisted");
        System.out.println(s + "is already " + signup + " in Course " + c);
    }

    /**
     * Output course capacity message
     *
     * @param c desired course
     */
    public static void printCapacity(Course c) {
        System.out.println(c + " has already reached maximum capacity."
                + " There are no more seats left");
    }

    /**
     * Output insufficient tokens message
     *
     * @param s student identifier
     * @param c desired course
     */
    public static void printNoCoins(Student s, Course c) {
        System.out.println("Insufficient course coins."
                + " Cannot waitlist " + s + " to " + c);
    }

    /**
     * Output general registration information
     */
    public static void generateOutput() {
        System.out.println("\n\n########COURSE INFORMATION######");
        ListIterator<Course> iter = courseList.listIterator();
        while (iter.hasNext()) {
            Course temp = iter.next();
            System.out.println(temp.getCourseCode()
                    + "  " + temp.getCourseName());
            System.out.println("\tRoster: " + temp.getCourseRoster());
        }

        System.out.println("\n\n########STUDENT INFORMATION######");
        ListIterator<Student> iterS = studentList.listIterator();
        while (iterS.hasNext()) {
            Student temp = iterS.next();
            System.out.println(temp.toString());
            System.out.println("\t Enrolled courses: "
                    + temp.getmyEnrolledCourses());
            System.out.println("\t Waitlisted courses: "
                    + temp.getmyWaitlist());
        }
    }

    //**********************YOUR WORK STARTS HERE*****************************
    /**
     * Returns a reference to the Course object whose courseCode is code
     *
     * @param code Course code of the Course to be returned
     * @return Course Course with the course code passed as a parameter
     */
    public static Course getCourse(String code) {
        ListIterator<Course> iter = courseList.listIterator();
        //iterate through the while loop to find the course with the matched code
        while (iter.hasNext()) {
            Course c = iter.next();
            if (c.getCourseCode().equals(code))
                return c;
        }
        return null;
    }

    /**
     * Returns a reference to the Student object whose StudentID is pid
     *
     * @param pid StudentID of the Student to be returned
     * @return Student student with the id passed as a parameter
     */
    public static Student getStudent(String pid) {
        ListIterator<Student> iter = studentList.listIterator();

        //iterate the while loop to find the student with the matched PID
        while(iter.hasNext())
        {
            Student s = iter.next();
            if(s.getStudentID().equals(pid))
                return s;
        }
        return null;

    }

    /**
     * main method to get the actual input of the file
     * @param args
     */
    public static void main(String[] args) {
        populateCourseandStudents(args[0]);
        File file = new File(args[1]);

        try {
            Scanner scLine = new Scanner(file);	//scan lines in file
            System.out.println("####START COURSE SCHEDULING####\n");
            while (scLine.hasNextLine()) {//if the file has next line
                Scanner scWord = new Scanner(scLine.nextLine());
                String property;

                if (scWord.hasNext()) {//if the file has next word
                    property = scWord.next();
                } else {
                    break;
                }
                if (property.equals("register")) {
                    //add a new student to the course list
                    //get the pid, course code, coins
                    String pid = scWord.next();
                    Student curStudent = getStudent(pid);
                    String code = scWord.next();
                    int coins = Integer.parseInt(scWord.next());
                    Course curCourse = getCourse(code);
                    Registration curRegist = new Registration(curStudent, curCourse, coins);
                    //get the current student's list of enroll and waitlisted
                    List<Course> studentEnrolled = curStudent.getmyEnrolledCourses();
                    List<Course> studentWaitlist = curStudent.getmyWaitlist();
                    //
                    if (studentEnrolled.contains(curCourse) ) {
                        printFail(curStudent, curCourse, true);
                        continue;
                    }
                    if (studentWaitlist.contains(curCourse) ) {
                        printFail(curStudent, curCourse, false);
                        continue;
                    }
                    if (curStudent.getCoins() < coins) {
                        printNoCoins(curStudent, curCourse);
                        continue;
                    }
                    curCourse.addToWaitlist(curRegist);
                    print(curStudent, curCourse, coins, false);
                    curStudent.deductCoins(coins);
                } else if (property.equals("enroll")) {
                    //process registrations in the waitlist
                    System.out.println("\n####STARTING BATCH ENROLLMENT####");

                    //iterate the loop of how many people to enroll for each course
                    int num = Integer.parseInt(scWord.next());
                    for (int j = 0; j < num; j++) {
                        for (int i = 0; i < courseList.size(); i++) {
                            if (!courseList.get(i).isFull()) {
                                Registration reg = courseList.get(i).processWaitlist();
                                if (reg == null) {
                                    continue;
                                }
                                print(reg.getStudent(), reg.getCourse(), reg.getCoins(), true);
                            }
                            else
                                printCapacity(courseList.get(i));

                        }
                    }
                    System.out.println("####ENDING BATCH ENROLLMENT####\n");
                } else {
                    break;
                }
                scWord.close();
            }
            scLine.close();
            System.out.println("\n####END COURSE SCHEDULING####");
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + file);
            System.exit(1);
        }

        generateOutput();
    }//end of main method
}
