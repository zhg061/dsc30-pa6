/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;

/**
 *  this class finds the k largest elements in an int array of size n
 *  The results in the returned ArrayList should be in descending order,
 *  which are the k largest elements in the array.
 */
public class FindKLargest {
    private static dHeap<Integer> data;
    private static int DEFAULT_TWO = 2;
    public static ArrayList<Integer> findKLargest(int k, ArrayList<Integer> arr) {
        if(k <= 0)
            return new ArrayList<Integer>();

        data = new dHeap<> (DEFAULT_TWO, k, false);
        for (int i = 0; i < arr.size(); i++) {
            //when heap size < k
            //  add elements into it
            if (data.size() < k)
                data.add(arr.get(i));
            //when heap size == k, add element, then remove min
            else if (data.size() == k) {
                data.add(arr.get(i));
                data.remove();
            }
        }
        //pop everything in heap to a list
        ArrayList<Integer> result = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            result.add(0, data.remove());
        }
        return result;
    }

}
