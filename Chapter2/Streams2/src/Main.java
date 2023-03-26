import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: SandeeraJayampathi
 * Date: 3/21/2023
 * Time: 2:57 PM
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


        ///// 1 ....
        List<Transaction> transIn2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println("Transaction list for 1 : " + transIn2011);

        ////// 2 ....
        List<String> uniqueCities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println("Unique cities :" + uniqueCities);

        ////// 3 ....
        List<Trader> cambridgeTraders = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge")).
                        distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println("Cambridge Traders :" + cambridgeTraders);

        ////// 4 ....

        String traderNames = transactions.stream().map(t->t.getTrader().getName())
                .distinct().sorted().reduce("",(a,b)->a+b);
        System.out.println("traderNames 1:" + traderNames);

        traderNames = transactions.stream().map(t->t.getTrader().getName())
                .distinct().sorted().collect(Collectors.joining());
        System.out.println("traderNames 2:" + traderNames);

        ////// 5 ....
        boolean anyMilan = transactions.stream().anyMatch(t->t.getTrader().getCity().equalsIgnoreCase("Milan"));
        System.out.println("any Milan:" + anyMilan);

        ////// 6 ....
        System.out.println("Start of printing values of Cambridge Traders");
        transactions.stream().filter(t-> "Cambridge".equalsIgnoreCase(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("end of printing values of Cambridge Traders");

        ////// 7 ....
        Optional<Integer> highestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        if(highestValue.isPresent()){
            System.out.println("highest value :"+highestValue.get());
        }

        ////// 8 ....
        Optional<Integer> smallestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        if(smallestValue.isPresent()){
            System.out.println("smallest value 1:"+smallestValue.get());
        }

        Optional<Transaction> smallestValTrs = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        if(smallestValTrs.isPresent()){
            System.out.println("smallest value 2    :"+smallestValTrs.get().getValue());
        }
    }
}
