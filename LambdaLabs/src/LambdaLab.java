import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class LambdaLab {

    public static class Person {
        Integer age;
        String name;
        Double height;

        Person(String name, Integer age, Double height) {
            this.age = age;
            this.name = name;
            this.height = height;
        }
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        //Part 1
        consumer();
        supplier();
        predicate();
        function();

        //Part 2
        System.out.println("Part 2");
        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    public static <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    public static void consumer() {
        interface Printable<T>{
            void print(T t);
        }

        Printable<String> printr = str -> System.out.println(str);
        printr.print("Printable lambda");

        Consumer<String> consumerPrintrLamda = str -> System.out.println(str);
        Consumer<String> consumerPrintrMethodRef = System.out::println;

        consumerPrintrLamda.accept("Printable lambda");
        consumerPrintrMethodRef.accept("Printable lambda");

    }

    public static void supplier() {

        interface Retrieveable<T> {
            T retrieve();
        }

        Retrieveable<Integer> retrieveable = () -> 77;
        System.out.println(retrieveable.retrieve());

        Supplier<Integer> supplier = () -> 77;
        System.out.println(supplier.get());

    }

    public static void predicate() {

        interface Evaluate<T> {
            boolean eval(int i);
        }

        Evaluate eval = i -> i < 0;

        eval.eval(1);
        eval.eval(-1);

        Predicate<Integer> predicate = i -> i < 0;

        predicate.test(1);
        predicate.test(-1);


        Predicate<Integer> isEven = i -> i % 2 == 0;
        check(4, isEven);
        check(7, isEven);

        Predicate<String> isMan = str -> str.startsWith("Mr.");
        check("Mr.Joe Bloggs", isMan);
        check("Ms. Ann Bloggs", isMan);


        Person personA = new Person("Mike", 33, 1.8);
        Person personB = new Person("Ann", 13, 1.4);

        Predicate<Person> isAdult = p -> p.age > 17;
        check(personA, isAdult);
        check(personB, isAdult);
    }

    public static void function(){

        interface Functionable<T, U> {
            U exec(T t);
        }

        Functionable<Integer, String> func = i -> "Number is: " + i;
        func.exec(25);


        Function<Integer, String> function = i -> "Number is: " + i;
        function.apply(25);
    }

    private static void sortAge(List<Person> listPeople) {
        listPeople.sort((o1, o2) -> o1.age > o2.age ? 1 : -1);

        listPeople.forEach(person -> System.out.println(person.age));
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(o -> o.name));

        listPeople.forEach(person -> System.out.println(person.name));
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(p -> p.height));

        listPeople.forEach(p -> System.out.println(p.height));
        listPeople.forEach(System.out::println);
    }
}