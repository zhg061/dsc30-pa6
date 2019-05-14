import java.util.Arrays;

/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> data;
    private static int DEFAULT_SIZE = 5;
    public MyPriorityQueue(int initialSize) {
        //constructor that create an empty dheap array with the initial size
        data = new dHeap<>(DEFAULT_SIZE, initialSize, true);

    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot
     * be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        //Inserts the specified element ‘e’ into the priority queue.
        //Throws NullPointerException if the element ‘e’ is null.
        if (element == null)
            throw new NullPointerException();

        data.add(element);
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if
     * the queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is
     * empty.
     */
    public T poll() {
        //Removes and returns the head of the priority queue
        // (the largest element), or null if the queue is empty.
        if (data.size() == 0)
            return null; //XXX-CHANGE-XXX
        return data.remove();
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        //Removes all elements from the queue.
        data = new dHeap<>(DEFAULT_SIZE, data.size(), true);
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null
     * if this queue is empty.
     *
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
    // Returns the head of the queue,
    // or null if the queue is empty.
        if (data.size() == 0)
            return null;
        return data.element(); //XXX-CHANGE-XXX
    }
}
