import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task_281A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();

        String buf = str.toUpperCase();

        System.out.print(buf.charAt(0));
        System.out.println(str.substring(1));
    }
}
