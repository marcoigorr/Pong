import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne, aiRect, ballRect;
    public PlayerController playerController;
    public AIController aiController;
    public Ball ball;
    public Text leftScoreText, rightScoreText, fpsCounterText, fpsAverageText;
    public FpsCounter fpsCounter;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        g2 = (Graphics2D)this.getGraphics();

        leftScoreText = new Text("0", new Font("Helvetica", Font.BOLD, Constants.TEXT_SIZE), Constants.TEXT_COLOR, Constants.TEXT_X_OFFSET, Constants.TEXT_Y_OFFSET);
        rightScoreText = new Text("0", new Font("Helvetica", Font.BOLD, Constants.TEXT_SIZE), Constants.TEXT_COLOR, Constants.TEXT_X_OFFSET_RIGHT, Constants.TEXT_Y_OFFSET);

        fpsCounterText = new Text("0", new Font("Didot", Font.PLAIN, Constants.FPS_SIZE ), Constants.FPS_COLOR, Constants.FPS_OFFSET_X, Constants.FPS_OFFSET_Y);
        fpsCounter = new FpsCounter(fpsCounterText);

        playerOne = new Rect(
                Constants.PADDLE_X_OFFSET,
                Constants.PADDLE_Y_OFFSET,
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT,
                Constants.PADDLE_COLOR
        );
        playerController = new PlayerController(playerOne, keyListener);

        aiRect = new Rect(
                Constants.SCREEN_WIDTH - (Constants.PADDLE_X_OFFSET + Constants.PADDLE_WIDTH),
                Constants.PADDLE_Y_OFFSET,
                Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT,
                Constants.PADDLE_COLOR
        );

        ballRect = new Rect(
                Constants.SCREEN_WIDTH/2,
                Constants.SCREEN_HEIGHT/2,
                Constants.BALL_WIDTH,
                Constants.BALL_WIDTH,
                Constants.PADDLE_COLOR
        );

        ball = new Ball(ballRect, playerOne, aiRect, leftScoreText, rightScoreText);
        aiController = new AIController(aiRect, ball);

    }

    public void update(double dt) {
        //System.out.println(1 / dt + " fps");
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        fpsCounter.update(dt);

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Constants.SCREEN_BACKGROUND);
        g2.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);

        fpsCounterText.draw(g2);

        playerOne.draw(g2);
        aiRect.draw(g2);
        ballRect.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
    }
}
