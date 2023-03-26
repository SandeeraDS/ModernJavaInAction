import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * User: SandeeraJayampathi
 * Date: 3/13/2023
 * Time: 8:46 AM
 */
public class Test1 {

    public void filterApple(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();

        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }

        System.out.println(result);
    }

}
