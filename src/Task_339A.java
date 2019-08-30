import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Task_339A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int one=0, two=0, three=0;

        String str = reader.readLine();

        for(int i = 0; i < str.length(); ++i) {
            switch (str.charAt(i)) {
                case '1': {
                    one++;
                    break;
                }
                case '2': {
                    two++;
                    break;
                }
                case '3': {
                    three++;
                    break;
                }
            }
        }

        StringJoiner answer = new StringJoiner("+");
        for(int i = 0; i < one; i++) {
            answer.add("1");
        }
        for(int i = 0; i < two; i++) {
            answer.add("2");
        }
        for(int i = 0; i < three; i++) {
            answer.add("3");
        }

        System.out.println(answer);
    }
}
