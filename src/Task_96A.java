import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task_96A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();

        int count1=0, count2=0, max=0;

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '1') {
                count1 += 1;
                max = Math.max(max, count2);
                count2 = 0;
            }
            else {
                count2 += 1;
                max = Math.max(max, count1);
                count1 = 0;
            }
        }

        max = Math.max(max, count1);
        max = Math.max(max, count2);

        if (max >= 7) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }

    }
}
