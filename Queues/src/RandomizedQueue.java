
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] array;

    public RandomizedQueue() // construct an empty randomized queue
    {
        size = 0;
        array = (Item[]) new Object[100];
    }

    private void resize(int n) {
        Item[] arr = (Item[]) new Object[n];
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }
        array = arr;
    }

    private void swap(int i, int j) {
        Item old = array[i];
        array[i] = array[j];
        array[j] = old;
    }

    public boolean isEmpty() // is the queue empty?
    {
        return size == 0;
    }

    public int size() // return the number of items on the queue
    {
        return size;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
    }

    public Item dequeue() // remove and return a random item
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size <= array.length / 4) {
            resize(array.length / 2);
        }
        int i = StdRandom.uniform(size);
        swap(i, size - 1);
        Item res = array[size - 1];
        size--;
        return res;
    }

    public Item sample() // return (but do not remove) a random item
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniform(size);
        return array[i];
    }

    @Override
    public Iterator<Item> iterator() // return an independent iterator over items in random order
    {
        return new RandomizedIterator(array, size);
    }

    public static void main(String[] args) // unit testing
    {
    }

    private class RandomizedIterator implements Iterator<Item> {

        private final Item[] array;
        private final int size;
        private final int[] indexes;
        private int cur;

        public RandomizedIterator(Item[] array, int size) {
            this.cur = 0;
            this.array = array;
            this.size = size;
            indexes = new int[size];
            for (int i = 0; i < size; i++) {
                int r = i + StdRandom.uniform(size - i);     // between i and N-1
                indexes[i] = r;
            }
        }

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item res = array[indexes[cur]];
            cur++;
            return res;
        }
    }
}
