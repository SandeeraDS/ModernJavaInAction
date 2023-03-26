import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        List<Apple> inventory =
                Arrays.asList(new Apple(Common.GREEN, 120),
                        new Apple(Common.GREEN, 80),
                        new Apple(Common.GREEN, 70),
                        new Apple(Common.RED, 150));

        // using lambada functions
        System.out.println("using lambada functions");
        test1.filterApple(inventory, (Apple apple) -> apple.getColour().equals(Common.GREEN));
        test1.filterApple(inventory, (Apple apple) -> apple.getWeight() > 100);

        // using written methods
        System.out.println("using written methods");
        test1.filterApple(inventory, Main::isGreenApple);
        test1.filterApple(inventory, Main::isHeavyApple);

        // using Streams
        System.out.println("using Streams");
        List<Apple> heavyApples = inventory.stream().filter((Apple a) -> a.getWeight() > 100).collect(toList());
        System.out.println("Heavy apples - >"+heavyApples);

        // using parallel stream processing
        System.out.println("Using parallel Stream processing");
        heavyApples = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 100).collect(toList());
        System.out.println("Heavy apples - >"+heavyApples);


//        inventory.sort((Comparator.comparing(Apple::getWeight)));
        inventory.sort((Apple a,Apple b)->a.getWeight().compareTo(b.getWeight()));
        System.out.println(inventory);


        List<String> str = Arrays.asList("a","b","B","A");
        str.sort(String::compareToIgnoreCase);
//        str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        System.out.println(str);


        // equivalent  method reference
        ToIntFunction<String> stringToIntFunction = (String a) -> Integer.parseInt(a);
        stringToIntFunction = Integer::parseInt;

        BiPredicate<String,String> biPredicate = (list,element) -> list.contains(element);
        biPredicate = String::contains;

        //-----------------------------------------------
        Function<Integer,Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer,Integer> h =f.andThen(g);
        int result = h.apply(1);
        System.out.println("and then"+result);

        h = f.compose(g);
         result = h.apply(1);
        System.out.println("Compose "+result);


    }

    public static boolean isGreenApple(Apple a) {
        return Common.GREEN.equals(a.getColour());
    }

    public static boolean isHeavyApple(Apple a) {
        return a.getWeight() > 100;
    }




}
