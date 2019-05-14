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
        data = new dHeap<> (DEFAULT_TWO, arr.size(), true);
        // put the arr into a descending order
        for (int i = 0; i < arr.size(); i++) {
            data.add(arr.get(i));
        }
        // find the k largest number in this descending arr
        ArrayList<Integer> result = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            result.add(data.remove());
        }
        return result;
    }

}
