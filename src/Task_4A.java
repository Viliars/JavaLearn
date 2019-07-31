import java.io.*;

public class Task_4A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int w = Integer.parseInt(reader.readLine());
        if(w != 2 && w%2==0) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
