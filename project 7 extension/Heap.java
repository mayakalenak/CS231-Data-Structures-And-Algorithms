/* Name: Maya Kalenak
 * Purpose: create a min or max heap
 */

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> implements PriorityQueue<T>, Comparable<T> {
    // fields
    Comparator<T> compare;
    ArrayList<T> list;

    // constructor
    public Heap(Comparator<T> comparator, boolean maxHeap) {
        list = new ArrayList<>();

        if (comparator == null) {
            comparator = new Comparator<T>() {
                @Override
                public int compare(T obj1, T obj2) {
                    if (((Comparable<T>) obj1).compareTo(obj2) < 0)
                        return 1;
                    else if (((Comparable<T>) obj1).compareTo(obj2) > 0)
                        return -1;
                    else
                        return 0;
                }
            };
        } else
            this.compare = comparator;

        if (maxHeap == true) {
            compare = new Comparator<T>() {
                @Override
                public int compare(T obj1, T obj2) {
                    if (((Comparable<T>) obj1).compareTo(obj2) < 0)
                        return -1;
                    else if (((Comparable<T>) obj1).compareTo(obj2) > 0)
                        return 1;
                    else
                        return 0;
                }
            };
        }
    }

    // default constructor
    public Heap(Comparator<T> comparator) {
        this(comparator, false);
    }

    // swap nodes
    private void swap(int idx1, int idx2) {
        T swap = list.get(idx1);

        list.set(idx1, list.get(idx2));
        list.set(idx2, swap);
    }

    // get parent node index
    private int getParentIdx(int idx) {
        return (idx - 1) / 2;
    }

    // get left child node index
    private int getLeftChildIdx(int idx) {
        return (idx * 2) + 1;
    }

    // get right child node index
    private int getRightChildIdx(int idx) {
        return (idx * 2) + 2;
    }

    //bubble up after adding node
    private void bubbleUp(int idx) {
        if (idx < 0)
            return;

        while (compare.compare(list.get(idx), list.get(getParentIdx(idx))) < 0) {
            swap(idx, getParentIdx(idx));
            idx = getParentIdx(idx);
        }
    }

    //bobble down after removing node
    private void bubbleDown(int idx) {
        int size = list.size();
        while (true) {
            int leftChildIdx = getLeftChildIdx(idx);
            int rightChildIdx = getRightChildIdx(idx);
            int smallest = idx;

            if (leftChildIdx < size && compare.compare(list.get(leftChildIdx), list.get(smallest)) < 0) {
                smallest = leftChildIdx;
            }

            if (rightChildIdx < size && compare.compare(list.get(rightChildIdx), list.get(smallest)) < 0) {
                smallest = rightChildIdx;
            }

            if (smallest != idx) {
                swap(idx, smallest);
                idx = smallest;
            } else {
                break;
            }
        }
    }

    //print heap
    public String toString() {
        int depth = 0;
        return toString(0, depth);
    }

    private String toString(int idx, int depth) {
        if (idx >= this.size()) {
            return "";
        }
        String left = toString(getLeftChildIdx(idx), depth + 1);
        String right = toString(getRightChildIdx(idx), depth + 1);

        String myself = "\t".repeat(depth) + this.list.get(idx) + "\n";
        return right + myself + left;
    }

    //size of heap
    public int size() {
        return list.size();
    }

    //find value at front of heap
    public T peek() {
        if (list.size() == 0)
            return null;

        return list.get(0);
    }

    //add value to heap
    public void offer(T item) {
        list.add(item);
        // bubbleUp(list.indexOf(item));
        bubbleUp(list.size() - 1);
    }

    //remove value from heap
    public T poll() {
        if (list.size() == 0)
            return null;

        T root = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        bubbleDown(0);
        return root;
    }

    //update priority of a given item
    public void updatePriority(T item) {
        int idx = list.indexOf(item);

        // if (idx != -1)
        bubbleUp(idx);
        bubbleDown(idx);
    }

    @Override
    public int compareTo(T o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
