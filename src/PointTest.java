class Point {
    public double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    synchronized public void increment() {
        x++;
        y++;
    }

    public String toString() {
        return "(" + x + "," + y + ") ";
    }
}

public class PointTest extends Thread {
    Point point;

    public PointTest(Point point) {
        this.point = point;
    }

    public static void main(String args[]) {
        Point myPoint = new Point(0.0, 0.0);
        PointTest myThread1 = new PointTest(myPoint);
        PointTest myThread2 = new PointTest(myPoint);
        long startTime = System.currentTimeMillis();
        myThread1.start(); myThread2.start();

        try {
            myThread1.join();
            myThread2.join();
        }
        catch (InterruptedException e) {}
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        System.out.println(myPoint);
    }

    public void run() {
        for(int i = 0; i < 2000000; i++)
            point.increment();
    }
}