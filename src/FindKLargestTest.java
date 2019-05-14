/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FindKLargestTest {
    FindKLargest test1;
    ArrayList<Integer> num1;
    FindKLargest test2;
    ArrayList<Integer> num2;
    FindKLargest test3;
    ArrayList<Integer> num3;

    @org.junit.Before
    public void setUp() throws Exception {
        test1 = new FindKLargest();
        num1 = new ArrayList<>();
        test2 = new FindKLargest();
        num2 = new ArrayList<>();
        test3 = new FindKLargest();
        num3 = new ArrayList<>();
    }
    @Test
    public void findKLargest() {
        num1.add(3);
        num1.add(9);
        num1.add(8);
        num1.add(99);
        num1.add(100);
        num1.add(8);
        ArrayList<Integer> result1 = test1.findKLargest(3, num1);
        assertEquals(new Integer(100), result1.get(0));
        assertEquals(new Integer(99), result1.get(1));
        assertEquals(new Integer(9), result1.get(2));
        num2.add(-100);
        num2.add(-200);
        num2.add(-300);
        num2.add(-111);
        num2.add(-99);
        num2.add(-101);
        ArrayList<Integer> result2 = test2.findKLargest(4, num2);
        assertEquals(new Integer(-99), result2.get(0));
        assertEquals(new Integer(-100), result2.get(1));
        assertEquals(new Integer(-101), result2.get(2));
        assertEquals(new Integer(-111), result2.get(3));
        num3.add(100);
        num3.add(200);
        num3.add(300);
        num3.add(111);
        num3.add(99);
        num3.add(101);
        ArrayList<Integer> result3 = test3.findKLargest(4, num3);
        assertEquals(new Integer(300), result3.get(0));
        assertEquals(new Integer(200), result3.get(1));
        assertEquals(new Integer(111), result3.get(2));
        assertEquals(new Integer(101), result3.get(3));


    }
}