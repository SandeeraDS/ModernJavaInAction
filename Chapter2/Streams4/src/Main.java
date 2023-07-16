import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * User: SandeeraJayampathi
 * Date: 3/22/2023
 * Time: 4:18 PM
 */
public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        Optional<Dish> mostCalories = menu.stream().max(comparingInt(Dish::getCalories));
        if (mostCalories.isPresent()) {
            System.out.println("Max Calories 1: " + mostCalories.get().getCalories());
        }

        mostCalories = menu.stream().collect(Collectors.maxBy(comparingInt(Dish::getCalories)));
        if (mostCalories.isPresent()) {
            System.out.println("Max Calories 2: " + mostCalories.get().getCalories());
        }


        Optional<Dish> minCalories = menu.stream().min(comparingInt(Dish::getCalories));
        if (mostCalories.isPresent()) {
            System.out.println("Min Calories 1: " + minCalories.get().getCalories());
        }

        minCalories = menu.stream().collect(Collectors.minBy(comparingInt(Dish::getCalories)));
        if (mostCalories.isPresent()) {
            System.out.println("Min Calories 2: " + minCalories.get().getCalories());
        }


        ///////////////////////////////////////////////
        int sumCal = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("Sum Calories 1: " + sumCal);

        sumCal = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Sum Calories 1: " + sumCal);

        IntSummaryStatistics menuSummery = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("Summerizing menu : " + menuSummery);


        String shortName = menu.stream().map(Dish::getName).collect(joining());
        System.out.println("Short name : " + shortName);

        shortName = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("Short name (comma separated) : " + shortName);


        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("total calories " + totalCalories);


        //Grouping
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("grouping dishes by type : " + dishesByType);


        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.FAT;
        }));
        System.out.println("grouping dishes by calorie level : " + dishesByCaloricLevel);

        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        // here missing the Fish type completely
        System.out.println("calories groups " + caloricDishesByType);

        caloricDishesByType = menu.stream().collect(groupingBy(Dish::getType,
                filtering(dish -> dish.getCalories() > 500, toList())));
        // here retain the Fish type as empty list.
        System.out.println("calories groups " + caloricDishesByType);

        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("dish names by type " + dishNamesByType);

        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.FAT;
        })));

        System.out.println("multi level grouping = " + dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

        System.out.println("group with count = " + typesCount);

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("mostCaloricByType = " + mostCaloricByType);

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
            else return Dish.CaloricLevel.FAT;
        }, toCollection(HashSet::new))));

        System.out.println("caloricLevelsByType = " + caloricLevelsByType);


        // partitioning
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("Veg food: " + partitionedMenu.get(true));
        System.out.println("non veg food: " + partitionedMenu.get(false));


    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(this::isPrime));
    }


    // check number id prime number
    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }


}
