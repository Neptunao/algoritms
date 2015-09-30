
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[][] opened;
    private int arrLength;
    private int size;

    public Percolation(int N) {               // create N-by-N grid, with all sites blocked
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        opened = new boolean[N][N];
        arrLength = N * (N - 2) + 2;
        uf = new WeightedQuickUnionUF(arrLength);
    }

    private int mapIndex(int i, int j) {
        if (i < 1 || j < 1) {
            throw new IllegalArgumentException();
        }

        if (i == 1) {
            return 0;
        }
        if (i == size) {
            return arrLength - 1;
        }
        return (i - 2) * size + j;
    }

    private void openCore(int i, int j, int k, int l) {
        //StdOut.println("Union " + i + " " + j + " , " + k + " " + l);
        uf.union(mapIndex(i, j), mapIndex(k, l));
        opened[i - 1][j - 1] = true;
        opened[k - 1][l - 1] = true;
    }

    public void open(int i, int j) {       // open site (row i, column j) if it is not open already
        if (i < 1 || j < 1 || i > size || j > size) {
            throw new IllegalArgumentException();
        }

        if (opened[i - 1][j - 1]) {
            return;
        }
        
        opened[i - 1][j - 1] = true;

        if (i < size && opened[i][j - 1]) {
            openCore(i, j, i + 1, j);
        }
        if (j < size && opened[i - 1][j]) {
            openCore(i, j, i, j + 1);
        }
        if (i > 1 && opened[i - 2][j - 1]) {
            openCore(i, j, i - 1, j);
        }
        if (j > 1 && opened[i - 1][j - 2]) {
            openCore(i, j, i, j - 1);
        }

    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        if (i < 1 || j < 1) {
            throw new IllegalArgumentException();
        }
        return opened[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {  // is site (row i, column j) full?
        if (i < 1 || j < 1) {
            throw new IllegalArgumentException();
        }

        int index = mapIndex(i, j);
        return uf.connected(arrLength - 1, index);
    }

    public boolean percolates() {            // does the system percolate?
        if(arrLength == 1)
            return isOpen(1,1);
        return uf.connected(0, arrLength - 1);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Percolation percolation = new Percolation(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            //StdOut.println(p + " " + q);
            percolation.open(p, q);
        }
        /*for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                StdOut.println("IsOpen " + i + " " + j + " = " + percolation.isOpen(i, j));
            }
        }
        StdOut.println("Percolates ??? " + percolation.percolates());*/
    }
}
