import java.io.*;

public class Task_112A {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1;
        String s2;

        s1 = reader.readLine();
        s2 = reader.readLine();

        int answer = s1.compareToIgnoreCase(s2);

        if(answer < 0) {
            answer = -1;
        }
        else {
            if(answer > 0) {
                answer = 1;
            }
        }

        System.out.println(answer);
    }
}
