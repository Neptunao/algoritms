
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    public Deque() // construct an empty deque
    {
    }

    public boolean isEmpty() // is the deque empty?
    {
        return true;
    }

    public int size() // return the number of items on the deque
    {
        return -1;
    }

    public void addFirst(Item item) // add the item to the front
    {
    }

    public void addLast(Item item) // add the item to the end
    {
    }

    public Item removeFirst() // remove and return the item from the front
    {
        return null;
    }

    public Item removeLast() // remove and return the item from the end
    {
        return null;
    }

    @Override
    public Iterator<Item> iterator() // return an iterator over items in order from front to end
    {
        return null;
    }

    public static void main(String[] args) // unit testing
    {
    }
}
