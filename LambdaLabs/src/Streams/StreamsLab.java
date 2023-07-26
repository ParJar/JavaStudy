package Streams;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsLab {

    public static void main(String[] args) {
        intStream();
        processItems();
        filterStream();
        processPeople();
        processMarks();
        processBooks();
        processMoreBooks();
        processEvenMoreBooks();
        processMorePeople();
        processPrices();
        processAnotherBook();
        processNumbers();
        processThirteen();
        processFourteen();
    }

    public static void intStream() {
        IntStream intStreamOut = IntStream.range(0,5);
        intStreamOut.forEach(System.out::println);

        IntStream intStreamAvg = IntStream.range(0,5);
        System.out.println(intStreamAvg.average());

//        List<Integer> listi = Arrays.asList(0,1,2,3,4);
//        System.out.println(listi.stream().peek(System.out::println).filter(i -> i > -1).count());
    }

    public static void processItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, "Screw"));
        items.add(new Item(2, "Nail"));
        items.add(new Item(3, "Bolt"));

//        items.sort((s1,s2) -> s1.getName().compareTo(s2.getName()));
//        items.forEach(System.out::println);

        items.stream().sorted(Comparator.comparing(a -> a.getName())).forEach(System.out::println);


    }

    public static void filterStream() {
        Stream<List<String>> input =  Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"));
        input.filter(list -> list.contains("c")).flatMap(list -> list.stream()).forEach(System.out::println);
    }

    public static void processPeople() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).max();
        System.out.println(max);

        List<Person> people = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe","Peters",20),
                new Person("Peter","Castle",29)
        );

        Optional<Person> oldest =  people.stream().max(Comparator.comparing(person -> person.getAge()));

        System.out.println(oldest);

        List<Integer> nums = Arrays.asList(10,47,33,23);
        System.out.println(nums.stream().reduce((x,y)-> Integer.max(x,y)));
        System.out.println(nums.stream().reduce(0, (x,y)-> Integer.max(x,y)));
    }

    public static void processMarks() {
        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);

        System.out.println(grade1.orElse("Unknown"));
        System.out.println(grade2.isPresent() ? grade2 : "Empty");
    }

    public static Optional<String> getGrade(int marks) {
        Optional<String> grade;

        if(marks > 50) {
            return grade = Optional.of("Pass");
        } else {
            return grade = Optional.of("Fail");
        }
    }

    public static void processBooks() {
        List<Book> books = Arrays.asList(
                new Book("Thinking in Java",30),
                new Book("Java in 24 hrs",20),
                new Book("Java Recipes",10)
        );

        OptionalDouble avg1 = books.stream().filter(b -> b.getPrice() > 10).mapToDouble(x -> x.getPrice()).average();
        OptionalDouble avg2 = books.stream().filter(b -> b.getPrice() > 90).mapToDouble(x -> x.getPrice()).average();

        System.out.println(avg1);
        System.out.println(avg2);
    }

    public static void processMoreBooks() {
        List<Book> books = Arrays.asList(
                new Book("Atlas Shrugged", 10),
                new Book("Freedom at Midnight", 5),
                new Book("Gone with the wind", 5)
        );

        Map<String, Double> bookMap = books.stream().collect(Collectors.toMap(Book::getTitle, Book::getPrice));

        bookMap.forEach((b1,b2) -> {
            if(b1.startsWith("A")) {
                System.out.println(b1);
            }
        });
    }

    public static void processEvenMoreBooks() {
        List<Book> books = Arrays.asList(
                new Book("Gone with the wind", 5.0),
                new Book("Gone with the wind", 10.0),
                new Book("Atlas shrugged", 15.0)
        );

        Map<String, Double> bookMap = books.stream().collect(Collectors.toMap(Book::getTitle, Book::getPrice, (e,r) -> e));
        bookMap.forEach((b1,b2) -> System.out.println(b1));
    }

    public static void processMorePeople() {
        List<Person> people = Arrays.asList(
                new Person("Bob", "", 31),
                new Person("Paul","",32),
                new Person("John","",33)
        );

        double averageAge = people.stream().filter(x -> x.getAge() < 30).mapToInt(x -> x.getAge()).average().orElse(0.0);
        System.out.println(averageAge);
    }

    public static void processPrices() {
        Optional<Double> price = Optional.ofNullable(20.0);

        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() -> 0.0));

        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        if (price2.isEmpty()) {
            System.out.println("empty");
        }
        price2.ifPresent((x) -> System.out.println("empty"));

        Double x = price2.orElse(44.0);
        System.out.println(x);

        Optional<Double> price3 = Optional.ofNullable(null);
        //Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code"));
    }

    public static void processAnotherBook() {
        List<AnotherBook> books = Arrays.asList(
          new AnotherBook("Gone with the wind","Fiction"),
          new AnotherBook("Bourne Ultimatum","Thriller"),
          new AnotherBook("The Client","Thriller")
        );

        List<String> genreList = new ArrayList<>();

        books.stream().map( x -> x.getGenre()).forEach(genreList::add);
        System.out.println(genreList);
    }

    public static void processNumbers() {
        DoubleStream numbers = DoubleStream.of(0, 2, 4);
        System.out.println(numbers.filter(x -> x % 2 == 1).sum());

        DoubleStream nums = DoubleStream.of(1, 3);
        nums.filter(x -> x % 2 == 1).average();
    }

    public static void processThirteen() {
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);

        System.out.println(ls.stream().distinct().anyMatch(x -> x == 11));
        System.out.println(ls.stream().noneMatch(x -> x%11>0));
    }

    public static void processFourteen() {
        AtomicInteger ai = new AtomicInteger();
        List<Integer> numbers = Arrays.asList(11,11,22,33);

        numbers.stream().parallel().filter( e -> {
            ai.incrementAndGet();
            return e%2==0;
        }).forEach(System.out::println);

        numbers.forEach(System.out::println);
        System.out.println(ai);
    }
}
