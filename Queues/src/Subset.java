
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int K = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            final String readString = StdIn.readString();
            queue.enqueue(readString);
        }

        for (int i = 0; i < K; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
