import java.text.NumberFormat;
import java.util.Locale;
import java.util.SplittableRandom;

public class ParallelMonteCarloPi {

    private static final long ITERATIONS = 1_000_000_000L;

    private static final long BASE_SEED = 123456789L;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Usage: java ParallelMonteCarloPi <threads>");
            return;
        }

        final int threads;
        try {
            threads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Threads must be an integer.");
            return;
        }
        if (threads <= 0) {
            System.out.println("Threads must be > 0.");
            return;
        }

        final long start = System.nanoTime();

        final long base = ITERATIONS / threads;
        final long rem = ITERATIONS % threads;

        final long[] insideCounts = new long[threads];
        Thread[] workers = new Thread[threads];

        for (int t = 0; t < threads; t++) {
            final int tid = t;
            final long itersForThread = base + (tid < rem ? 1 : 0);

            workers[tid] = new Thread(() -> {
                SplittableRandom rnd = new SplittableRandom(BASE_SEED + tid * 1_000_003L);

                long inside = 0;
                for (long i = 0; i < itersForThread; i++) {
                    double x = rnd.nextDouble(); // [0,1)
                    double y = rnd.nextDouble(); // [0,1)
                    double r2 = x * x + y * y;
                    if (r2 <= 1.0) inside++;
                }
                insideCounts[tid] = inside;
            });

            workers[tid].start();
        }

        for (Thread w : workers) w.join();

        long insideTotal = 0;
        for (long c : insideCounts) insideTotal += c;

        double pi = 4.0 * ((double) insideTotal / (double) ITERATIONS);

        final long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setGroupingUsed(true);

        System.out.printf(Locale.US, "PI is %.5f%n", pi);
        System.out.println("THREADS " + threads);
        System.out.println("ITERATIONS " + nf.format(ITERATIONS));
        System.out.printf(Locale.US, "TIME %.2fms%n", timeMs);
    }
}
