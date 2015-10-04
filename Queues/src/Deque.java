
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Container first;
    private Container last;
    private int size;

    public Deque() // construct an empty deque
    {
        size = 0;
    }

    public boolean isEmpty() // is the deque empty?
    {
        return first == null;
    }

    public int size() // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item) // add the item to the front
    {
        if (item == null) {
            throw new NullPointerException();
        }
        Container old = first;
        if (size == 0) {
            last = first;
        }
        first = new Container(item);
        first.next = old;
        old.prev = first;
        size++;
    }

    public void addLast(Item item) // add the item to the end
    {
        if (item == null) {
            throw new NullPointerException();
        }
        Container old = last;
        if (size == 0) {
            first = last;
        }
        last = new Container(item);
        last.prev = old;
        old.next = last;
        size++;
    }

    public Item removeFirst() // remove and return the item from the front
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Container old = first;
        first = old.next;
        old.next = null;
        size--;
        if (size == 0) {
            last = null;
        }
        return old.item;
    }

    public Item removeLast() // remove and return the item from the end
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Container old = last;
        last = old.prev;
        old.prev = null;
        size--;
        if (size == 0) {
            first = null;
        }
        return old.item;
    }

    @Override
    public Iterator<Item> iterator() // return an iterator over items in order from front to end
    {
        return new DequeueIterator(first);
    }

    public static void main(String[] args) // unit testing
    {
    }

    private class Container {

        public Container prev;
        public Container next;
        public final Item item;

        public Container(Item item) {
            this.item = item;
        }
    }

    private class DequeueIterator implements Iterator<Item> {

        private Container current;

        public DequeueIterator(Container root) {
            current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Container res = current;
            current = current.next;
            return res.item;
        }
    }
}
