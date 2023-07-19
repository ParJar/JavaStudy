import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodLab {

    public static void main (String[] args) {
        System.out.println("Hello world");

        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(7);
        numbers.add(4);
        numbers.add(5);

        Consumer<List<Integer>> consumer = o -> Collections.sort(o);
        consumer.accept(numbers);

        numbers.forEach(System.out::println);
    }

    public static void boundMR() {
        String name = "Mr.Joe Bloggs";

        Predicate<String> pred = str -> str.startsWith("Mr.");

        System.out.println(pred.test("Mr."));
        System.out.println(pred.test("Mrs."));

        Predicate<String> predmr =  name::startsWith;
        System.out.println(predmr.test("Mr."));
        System.out.println(predmr.test("Ms."));

    }

    public static void unboundMR() {
        Predicate<String> isEmpty = str -> str.isEmpty();

        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("xyz"));

        Predicate<String> isEmptyMR = String::isEmpty;

        isEmptyMR.test("");
        isEmptyMR.test("xyz");

        BiPredicate<String, String> match = (str1, str2) -> str1.startsWith(str2);
        System.out.println(match.test("Mr. Joe Bloggs","Mr."));
        System.out.println(match.test("Mr. Joe Bloggs","Ms."));

        BiPredicate<String, String> matchMR = String::startsWith;

        System.out.println(matchMR.test("Mr. Joe Bloggs","Mr."));
        System.out.println(matchMR.test("Mr. Joe Bloggs","Ms."));
    }

    public static void constructorMR() {
        Supplier<List<String>> supp = () -> new ArrayList<String>();

        List<String> list = supp.get();
        list.add("Lambda");
        System.out.println(list);

        Supplier<List<String>> suppMR = ArrayList::new;
        list = suppMR.get();
        list.add("Method Reference");
        System.out.println(list);

        Function<Integer, List<String>> func = i -> new ArrayList<String>(i);
        list = func.apply(10);
        list.add("Lambda");
        System.out.println(list);

        Function<Integer, List<String>> funcMR = ArrayList::new;
        list = funcMR.apply(10);
        list.add("Method Reference");
        System.out.println(list);
    }

}
