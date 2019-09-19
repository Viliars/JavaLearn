import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        long count = IntStream.of(-5, -4, -3, -2, -1,0 ,1, 2, 3, 4, 5).filter(w -> w> 0).count();
        System.out.println(count);

        ArrayList a = new ArrayList();
        a.add("11");
        a.add("22");
        a.add("hello");

        a.forEach(System.out::println);
        Stream.of(a).forEach(System.out::println);

        String s1[] = {"1", "11", "111", "222"};
        List<String> list = Arrays.stream(s1).filter(s -> s.length() <= 2).collect(Collectors.toList());

        IntStream.of(120, 710, 85, 12, 300, 4)
                .filter(x -> x < 300)
                .map(x -> x + 11)
                .limit(3)
                .forEach(System.out::println);
    }
}
