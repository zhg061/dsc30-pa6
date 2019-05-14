/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.*;

/**
 * class that creates a heap array with d branches
 * @param <T>
 */
public class dHeap<T extends Comparable<? super T>>
        implements dHeapInterface<T> {

    private T[] heap; //heap array
    private int d; //branching factor
    private int nelems;
    private boolean isMaxHeap; //boolean to indicate whether heap is max or min
    private static int DEFAULT_SIZE = 5;
    private static int DEFAULT_TWO = 2;
    /**
     * Initializes a binary max heap with capacity = 5
     */
    public dHeap() {
        //constructor that set the capacity of the heap to 5
        d = DEFAULT_TWO;
        isMaxHeap = true;
        nelems = 0;
        heap = (T[]) new Comparable[DEFAULT_SIZE];
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    public dHeap(int heapSize) {
        //constructor that set the capacity of the heap to heapSize
        d = DEFAULT_TWO;
        isMaxHeap = true;
        nelems = 0;
        heap = (T[]) new Comparable[heapSize];
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d The number of child nodes each node in the heap should have.
     * @param heapSize The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap)
            throws IllegalArgumentException {
        //constructors that set d, heapsize, and isMaxHeap
        heap = (T[]) new Comparable[heapSize];
        if (d < 1)
            throw new IllegalArgumentException();
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems = 0;

    }


    @Override
    /**
     * Returns the number of elements stored in the heap.
     *
     * @return The number of elements stored in the heap.
     */
    public int size() {
        //return nelems.
        return nelems;
    }

    @Override
    /**
     * Adds the specified element to the heap; data cannot be null. Resizes the
     * storage if full.
     *
     * @param data The element to add.
     * @throws NullPointerException if data is null.
     */
    public void add(T data) throws NullPointerException {
        //throw exception is the data is null
        // if the capacity is reach, double the length
        // bubble up the new data from the leaf
        // update nelems
        if (data == null)
            throw new NullPointerException();
        if (nelems == heap.length)
            resize();
        heap[nelems] = data;
        bubbleUp(nelems);
        nelems++;
    }

    @Override
    /**
     * Removes and returns the element at the root. If the heap is empty, then
     * this method throws a NoSuchElementException.
     *
     * @return The element at the root stored in the heap.
     * @throws NoSuchElementException if the heap is empty
     */
    public T remove() throws NoSuchElementException {
        //throw exception if the heap is empty
        //return the root of the heap
        // update nelems
        // set the root of the heap as the last leaf of the heap
        // trickle down the new root
        if (nelems == 0)
            throw new NoSuchElementException();
        T result = heap[0];
        heap[0] = heap[nelems - 1];
        heap[nelems - 1] = null;
        nelems--;
        trickleDown(0);
        return result;
    }

    @Override
    /**
     * Clears all the items in the heap Heap will be empty after this call
     * returns
     */
    public void clear() {
        //set heap to empty
        // set nelems to zero
        heap = (T[]) new Comparable[heap.length];
        nelems = 0;
    }

    @Override
    /**
     * Retrieves, but does not remove, the element at the root.
     *
     * @return item at the root of the heap
     * @throws NoSuchElementException - if this heap is empty
     */
    public T element() {
        //return the root of the heap, throw exception is the
        // heap is empty
        if (nelems == 0)
            throw new NoSuchElementException();
        return heap[0];
    }

    /**
     * move the element at index index up
     * @param index
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / d;
            // if the parent index is greater,
            // max heap doesn't do anything
            // min heap swap
            if (heap[index].compareTo(heap[parentIndex]) < 0) {
                if (isMaxHeap) {
                    return;
                } else {
                    swap(parentIndex, index);
                    index = parentIndex;
                }
            }
            // if the parent index is smaller
            // max heap swap
            // min heap do nothing
            else {
                if (isMaxHeap) {
                    swap(parentIndex, index);
                    index = parentIndex;
                } else {
                    return;
                }
            }
        }

    }

    /**
     * helper method for bubbleUp, swap two values when it's called
     * @param parentIndex
     * @param index
     */
    private void swap(int parentIndex, int index) {
        // swap two values
        T swap = heap[parentIndex];
        heap[parentIndex] = heap[index];
        heap[index] = swap;
    }

    /**
     * when remove() is called, the new root needed to be trace down
     * @param index
     */
    private void trickleDown(int index) {
        int childIndex = d * index + 1;
        T value = heap[index];
        while (childIndex < size()) {
            // find the max  or min among the node and all the node's children
            T currValue = value;
            int currIndex = -1;
            // for loop to find the element that is closest to value
            for (int i = 0; i < d && i + childIndex < size(); i++) {
                if (heap[i + childIndex].compareTo(currValue) > 0 && isMaxHeap) {
                    currValue = heap[i + childIndex];
                    currIndex = i + childIndex;
                }
                else if (heap[i + childIndex].compareTo(currValue) < 0 && !isMaxHeap) {
                    currValue = heap[i + childIndex];
                    currIndex = i + childIndex;
                }

            }
            // if the founded element equals to the value,
            // don't do anything
            if (currIndex == -1 || currValue.compareTo(value) == 0) {
                return;
            }
            // if element and value are different, swap
            else {
                swap(currIndex, index);
                index = currIndex;
                childIndex = d * index + 1;
            }
        }

    }

    /**
     * increase the capacity by 2 when the capacity is reached
     */
    private void resize() {
        // Doubles the capacity of the queue.
        T[] heap2 = (T[]) new Comparable[heap.length * DEFAULT_TWO];
        for (int i = 0; i < heap.length; i++) {
            heap2[i] = heap[i];
        }
        heap = heap2;
    }

    /**
     * based on the index, find its parent index
     * @param index
     * @return the parent index
     */
    private int parent(int index) {
        // get the parent index by dividing (index - 1) by 2
        return (index - 1) / d;
    }

    /**
     * helper method for testing
     * @return heap
     */
    public T[] getHeap() {
        // return the array of heap
        return heap;
    }

    /**
     * returns all the occurrences of the values greater than
     * a parameter 'k' in a max-heap. You can use built-in
     * Java’s Linked List. You must use heap methods and not simply scan the array.
     * @param k
     * @return all the values greater than k
     */
    public LinkedList findGreaterThanK(T k) {
        dHeap<T> heapCopy = this;
        LinkedList result = new LinkedList<>();
        // add k to the heapCopy
        heapCopy.add(k);
        // iterating through the whole heapCopy
        while (heapCopy.nelems > 0) {
            // if the current element is greater than k
            // add it to the result, removes it, and keeps going
            if (heapCopy.element().compareTo(k) > 0)
                result.add(heapCopy.remove());
            // if the current element if smaller or equal
            // to k, removes it and keeps going
            else if (heapCopy.element().compareTo(k) == 0)
                heapCopy.remove();
            else
                heapCopy.remove();
        }
        return result;
    }

    /**
     *  a method that returns all the occurrences of the values
     *  greater than a parameter 'k' in a max-heap.
     * @param a
     * @param num1
     * @param num2
     * @return
     */
    public int findSum(int[] a, int num1, int num2) {
        isMaxHeap = false;
        dHeap<Integer> heapCopy = (dHeap<Integer>) this;
        int curIndex = 0;
        ArrayList<Integer> result = new ArrayList<>();
        // create a min heap of the elements based on int[] a
        for (int i = 0; i < a.length; i++) {
            heapCopy.add(a[i]);
        }
        // if the current index is less that num1, remove it and
        // keeps going
        while (curIndex < num1) {
            heapCopy.remove();
            curIndex++;
        }
        // when the current index equals to num1, add
        // the element at num1 to the array list
        result.add((Integer) heapCopy.remove());
        curIndex++;
        // keeps adding element to the array list, until
        // curIndex is greater the num2
        while (curIndex < num2) {
            result.add((Integer) heapCopy.remove());
            curIndex++;
        }
        result.add((Integer) heapCopy.remove());
        int finalResult = 0;
        // looping through the result, and adding all the values together
        for (int i = 0; i < result.size(); i++) {
            finalResult += result.get(i);
        }
        return finalResult;
    }



}
