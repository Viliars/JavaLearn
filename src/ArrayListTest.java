import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("1 элемент");
        a1.add("2");
        a1.add("3 элемент");
        a1.set(1, "New");
        Iterator it = a1.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("------------------");
        a1.add(2, "Вставка");
        it = a1.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        String[] buf1 = new String[3];
        buf1 = a1.toArray(buf1);
        System.out.println("------------------");
        for(String s: buf1) System.out.println(s);
        String[] buf2 = new String[7];
        System.out.println("------------------");
        buf2 = a1.toArray(buf2);
        for(String s: buf2) System.out.println(s);
    }
}
