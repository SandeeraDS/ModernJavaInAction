package com.ds;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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


        System.out.println("Filter names of dish with low calories(<400) sorted");

        List<String> lowCaloriesDishNames = menu.stream().
                filter(d -> d.getCalories() < 400).
                sorted(Comparator.comparing(Dish::getCalories)).
                map(Dish::getName).collect(toList());

        System.out.println("low Calories dishes : " + lowCaloriesDishNames);
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Dishes group by type");
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("Dish groups :" + dishesByType);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("First three High calories dishes");

        List<String> threeHighCaloricDishNames = menu.stream().
                filter(d -> d.getCalories() > 300).
                map(Dish::getName).
                limit(3).collect(toList());

        System.out.println("three high Calories dishes : " + threeHighCaloricDishNames);
/////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Dish names");
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        System.out.println("Dish names : " + dishNames);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("Looping and printing elements");
        menu.stream().forEach(System.out::println);


        /////////////////////////////////////////////////////////////////////////
        List<Dish> specialMenu = Arrays.asList(new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        List<Dish> slicedMenu1 = specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());
        System.out.println("take while : " + slicedMenu1);

        List<Dish> slicedMenu2 = specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());
        System.out.println("drop while : " + slicedMenu2);

        List<Dish> truncateMenu = specialMenu.stream().filter(dish -> dish.getCalories() > 300).limit(3).collect(toList());
        System.out.println("limit(n) test : " + truncateMenu);

        List<Dish> skipMenu = specialMenu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
        System.out.println("skip(n) test : " + skipMenu);


        ///////////////////////////////////////////////////////////////////////

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        System.out.println("word length 1 : " + wordLengths);

        List<String> words1 = Arrays.asList("Hewa", "Kottage", "Dilusha", "Sandeera", "Jayampathi");
        List<Integer> wordLengths1 = words1.stream().map((s) -> s.length()).collect(toList());
        System.out.println("word length 2: " + wordLengths1);

        List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length).collect(toList());
        System.out.println("dish name length: " + dishNameLengths);

        //////////////////////////////////////////////////////////////////////////////
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        streamOfWords.forEach(System.out::println);


//        List<String[]> uniqueCharacters = words.stream() .map(word -> word.split("")).collect(Collectors.toList());
//
//        for(String[] a: uniqueCharacters){
//            Arrays.stream(a).forEach(System.out::println);
//        }

        List<String> uniqueCharacters = words.stream().map(word -> word.split("")).
                flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println("uniqueCharacters: " + uniqueCharacters);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(n -> n * n).forEach(System.out::println);


        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        if (menu.stream().allMatch(dish->dish.getCalories() < 1000)) {
            System.out.println("The menu is healthy!!");
        }


//        Optional<Dish> dish = menu.stream() .filter(Dish::isVegetarian).findAny();
//        System.out.println("is any veg :"+dish);

        menu.stream() .filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        List<Integer> listNumbers = Arrays.asList(4,5,3,9);

        int sum = listNumbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum :"+sum);

        int multiply = listNumbers.stream().reduce(1,(a,b)->a*b);
        System.out.println("multiply :"+multiply);

//        listNumbers = new ArrayList<>();
//         sum = listNumbers.stream().reduce(0, Integer::sum);
//        System.out.println("Sum for empty :"+sum);


        Optional<Integer> minValue = listNumbers.stream().reduce(Integer::min);
        if(minValue.isPresent()){
            System.out.println("min value : "+minValue.get());
        }

        Optional<Integer> maxValue = listNumbers.stream().reduce(Integer::max);
        if(maxValue.isPresent()){
            System.out.println("max value : "+maxValue.get());
        }

        long count = listNumbers.stream().count();
        System.out.println("count : "+count);

        count= listNumbers.stream().map(d->1).reduce(0,Integer::sum);
        System.out.println("count : "+count);

    }
}
