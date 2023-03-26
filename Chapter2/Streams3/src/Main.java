import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: SandeeraJayampathi
 * Date: 3/21/2023
 * Time: 4:17 PM
 */
public class Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));


        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Sum Calories :" + calories);


        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

        if (maxCalories.isPresent()) {
            System.out.println("max calories :" + maxCalories.getAsInt());
        }

        IntStream evenNumber = IntStream.rangeClosed(2, 100).filter(n -> n % 2 == 0);
        System.out.println("even number count 1 :" + evenNumber.count());

        evenNumber = IntStream.range(2, 100).filter(n -> n % 2 == 0);
        System.out.println("even number count 2 :" + evenNumber.count());


        /// Pythagorean theorem
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed().
                        flatMap(a -> IntStream.rangeClosed(a, 100).
                                filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                                mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        /////////////////////
        Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
        stream.map(String::toUpperCase).
                forEach(System.out::println);

        Stream.iterate(0, n -> n + 2) .limit(10) .forEach(System.out::println);

        IntStream.iterate(0, n -> n < 100, n -> n + 4) .forEach(System.out::println);

        Stream.generate(Math::random) .limit(5) .forEach(System.out::println);



    }
}
