import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

//    Отображение движущегося мяча
public class BounceThread {
    public static void main(String[] args) {
        JFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/*
   Интерфейс Runnable, анимирующий движения мяча :
код, выполняющий движение мяча размещен в методе run
*/
class BallRunnable implements Runnable {
    public static final int STEPS = 500000;             // Число движений
    public static final int DELAY = 5;
    private Ball ball;
    private Component component;
    /**
     * @aBall мяч для перемещения
     * @aPanel контейнер, в котором мяч перемещается
     */
    public BallRunnable(Ball aBall, Component aComponent) {
        ball = aBall;
        component = aComponent;
    }

    public void run() {
        try {
            for (int i = 1; i <= STEPS; i++)      // 1000 движений отдельного мяча
            {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);     // Задержка для текущего потока
            }
        } catch (InterruptedException e) {
        }
    }
}

// Мяч, который движется и перемещается из угла
class Ball {
    private static final int XSIZE = 15;                 // Размер мяча
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    /**
     * Перемещение мяча в следующую позицию, изменяя направление
     * если он ударился в один из углов
     */
    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    // Запрос фигуры мяча в текущей позиции
    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}

// Панель, на которой отображается мяч
class BallPanel extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<Ball>();

    /**
     * Добавляем мяч на панель
     *
     * @param b – добавляемый мяч
     */
    public void add(Ball b) {
        balls.add(b);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }
}

// Фрейм с панелью и двумя кнопками
class BounceFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    // Создаем новый мяч и запускаем поток, отвечающий за его движение
    public static final int STEPS = 1000;
    public static final int DELAY = 3;
    private BallPanel panel;
    public BounceFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("BounceThread");

        panel = new BallPanel();
        add(panel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start",
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        addBall();
                    }
                });

        addButton(buttonPanel, "Close",
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.exit(0);
                    }
                });
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Добавление кнопок в контейнер
     *
     * @param c        - контейнер
     * @param title    – заголовок кнопки
     * @param listener the action listener for the button
     */
    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball b = new Ball();
        panel.add(b);
        Runnable r = new BallRunnable(b, panel);
        Thread t = new Thread(r);
        t.start();
    }
}
