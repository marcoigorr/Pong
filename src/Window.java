import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    Graphics2D g2;
    KL keyListener = new KL();
    Rect playerOne, ai, ball;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D)this.getGraphics();

        playerOne = new Rect(
                Constants.PADDLE_X_OFFSET,
                Constants.PADDLE_Y_OFFSET,
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT,
                Color.WHITE
        );

        ai = new Rect(
                Constants.SCREEN_WIDTH - (Constants.PADDLE_X_OFFSET + Constants.PADDLE_WIDTH),
                Constants.PADDLE_Y_OFFSET,
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT,
                Constants.PADDLE_COLOR
        );

        ball = new Rect(
                Constants.SCREEN_WIDTH/2,
                Constants.SCREEN_HEIGHT/2,
                Constants.BALL_WIDTH,
                Constants.BALL_WIDTH,
                Constants.PADDLE_COLOR
        );
    }

    public void update(double dt) {
        // System.out.println("" + dt + "s passed since last Frame");
        // System.out.println(1 / dt + "fps");
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        playerOne.Draw(g2);
        ai.Draw(g2);
        ball.Draw(g2);

        if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("Sudo su");


        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            System.out.println("sudo giú");

        }
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(15);
            } catch (Exception e) {
                // Exception
            }
        }
    }
}
