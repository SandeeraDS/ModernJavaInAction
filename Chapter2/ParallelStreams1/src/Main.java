import java.util.stream.Stream;

/**
 * User: Sandeera Jayampathi (DS)
 * Date: 3/28/2023
 * Time: 1:28 PM
 */
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        long sum = m.parallelSum(1000000);
        System.out.println("parallel sum 1 :"+ sum);

        System.out.println("Fork Join Pool Size = "+Runtime.getRuntime().availableProcessors());

    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }


}
