
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] fractions;
    private int times;

    public PercolationStats(int N, int T) {     // perform T independent experiments on an N-by-N grid
        if (N < 0 || T < 1) {
            throw new IllegalArgumentException();
        }

        times = T;
        fractions = new double[T];

        for (int i = 0; i < T; i++) {
            boolean percolates = false;
            double openedCount = 0;
            Percolation p = new Percolation(N);
            while (!percolates) {
                boolean opened = true;
                int k = -1;
                int l = -1;
                while (opened) {
                    k = StdRandom.uniform(1, N + 1);
                    l = StdRandom.uniform(1, N + 1);
                    opened = p.isOpen(k, l);
                }
                p.open(k, l);
                openedCount++;
                percolates = p.percolates();
            }
            fractions[i] = openedCount / (N * N);
        }
    }

    public double mean() {                      // sample mean of percolation threshold
        double sum = 0;
        for (int i = 0; i < times; i++) {
            sum += fractions[i];
        }
        return sum / times;
    }

    public double stddev() {                    // sample standard deviation of percolation threshold
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < times; i++) {
            double val = fractions[i] - mean;
            sum += val * val;
        }
        return Math.sqrt(sum / (times - 1));
    }

    public double confidenceLo() {              // low  endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();

        return mean - (1.96 * stddev / Math.sqrt(times));
    }

    public double confidenceHi() {              // high endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();

        return mean + (1.96 * stddev / Math.sqrt(times));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
