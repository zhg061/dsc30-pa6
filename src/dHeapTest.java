/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class dHeapTest {
    dHeap<Integer> test1;
    dHeap<Integer> test2;
    dHeap<Integer> test3;
    dHeap<Integer> test4;
    dHeap<Registration> test5;


    @org.junit.Before
    public void setUp() throws Exception {
        test1 = new dHeap<>();
        test2 = new dHeap<>(2);
        test3 = new dHeap<>(4, 2, false);
        test4 = new dHeap<>(6, 5, false);
        test5 = new dHeap<>(4, 5, true);
    }
    @org.junit.Test
    public void size() {
        assertEquals(0, test1.size());
        test1.add(1);
        assertEquals(1, test1.size());
        test1.add(3);
        test1.add(9);
        assertEquals(3, test1.size());

    }

    @org.junit.Test
    public void add() {
        test3.add(9);
        test3.add(10);
        test3.add(99);
        test3.add(9999);
        assertArrayEquals(new Integer[] {9, 10, 99, 9999}, test3.getHeap());
        assertEquals(4, test3.size());
    }

    @org.junit.Test
    public void remove() {
        test3.add(9);
        test3.add(10);
        test3.add(99);
        test3.add(9999);
        test3.remove();
        assertArrayEquals(new Integer[] {10, 9999, 99, null}, test3.getHeap());
        assertEquals(3, test3.size());
        test1.add(10);
        test1.add(9);
        test1.add(99);
        assertArrayEquals(new Integer[] {99, 9, 10, null, null}, test1.getHeap());
        test1.remove();
        assertArrayEquals(new Integer[] {10, 9, null, null, null}, test1.getHeap());
        Course testCourse = new Course("BCD", "CSE", 20);
        Student s1 = new Student("ABC", "Tony", 50);
        Student s2 = new Student("BCD", "Boy", 50);
        Student s3 = new Student("CDE", "Girl", 50);

        Registration r1 = new Registration(s1, testCourse, 30);
        r1.setTimestamp();

        Registration r2 = new Registration(s2, testCourse, 20);
        r2.setTimestamp();

        Registration r3 = new Registration(s3, testCourse, 10);
        r3.setTimestamp();


        test5.add(r2);
        test5.add(r3);
        test5.add(r1);

        assertArrayEquals(new Registration[] {r1, r3, r2, null, null}, test5.getHeap());
        test5.remove();
        assertArrayEquals(new Registration[] {r2, r3, null, null, null}, test5.getHeap());




    }

    @org.junit.Test
    public void clear() {
        test4.add(9);
        test4.add(10);
        test4.add(99);
        test4.add(9999);
        test4.add(0);
        test4.add(99);
        test4.clear();
        assertEquals(0, test4.size());
        test1.add(8);
        test1.add(90);
        test1.add(0);
        test1.clear();
        assertEquals(0, test1.size());
        test2.add(9);
        test2.add(109);
        test2.add(-100);
        test2.remove();
        assertArrayEquals(new Integer[] {9, -100, null, null}, test2.getHeap());
    }

    @org.junit.Test
    public void element() {
        test4.add(9);
        assertEquals(new Integer (9), test4.element());
        test3.add(9);
        test3.add(10);
        test3.add(99);
        test3.add(9999);
        test3.remove();
        assertEquals(new Integer (10), test3.element());
        test1.add(8);
        test1.add(90);
        test1.add(0);
        assertEquals(new Integer (90), test1.element());
    }


    @org.junit.Test
    public void findGreaterThanK() {
        test3.add(9);
        test3.add(10);
        test3.add(99);
        test3.add(9999);
        assertArrayEquals(new Integer[] {9, 10, 99, 9999}, test3.getHeap());
        assertEquals(9999, test3.findGreaterThanK(new Integer(99)).peek());
        test4.add(9);
        test4.add(10);
        test4.add(99);
        test4.add(9999);
        test4.add(0);
        test4.add(99);
        LinkedList<Integer> result4 = test4.findGreaterThanK(new Integer(9));
        assertEquals(new Integer(10), result4.pop());
        assertEquals(new Integer(99), result4.pop());
        assertEquals(new Integer(99), result4.pop());
        assertEquals(new Integer(9999), result4.pop());
        test1.add(8);
        test1.add(90);
        test1.add(0);
        LinkedList<Integer> result1 = test1.findGreaterThanK(new Integer(-9));
        assertEquals(new Integer(90), result1.pop());
        assertEquals(new Integer(8), result1.pop());
        assertEquals(new Integer(0), result1.pop());
    }

    @Test
    public void findSum() {
        int sum3 = test3.findSum(new int[] {9, 10, 99, 9999}, 0, 3);
        assertEquals(10117, sum3);
        int sum1 = test1.findSum(new int[] {2, 5, 9, 3, 5, 5, 2, 6, 6}, 4, 8);
        assertEquals(31, sum1);
        int sum2 = test2.findSum(new int[] {2497, 2894, 248, 4, -1, 48}, 0, 1);
        assertEquals(3, sum2);
    }
}