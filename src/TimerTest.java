import java.util.Timer;
import java.util.TimerTask;


public class TimerTest {
    private static final int TIME = 23;

    public static void main (String[] args) {

        Timer timer = new Timer(true);
        final Integer r = 0;
        TimerTask makeAvailable = new TimerTask() {
            @Override
            public void run() {
                Task.make(0);
            }
        };

        timer.schedule(makeAvailable, 0, 3*1000);

        for(int i = 0; i < 4; i++) new Task().start();

        try {
            Thread.sleep(TIME*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Task.run = false;

        timer.cancel();
    }
}

class Task extends Thread {
    static public volatile boolean run = true;
    static private boolean flag = false;
    static synchronized boolean make(int mode) {
        if(mode == 1) {
            if(flag) { flag = false; return true; }
            else return false;
        }
        flag = true;
        return flag;
    }

    @Override
    public void run() {
        while (run) {
            if(make(1)) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
