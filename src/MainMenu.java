import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

public class MainMenu extends JFrame implements Runnable {
    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public FpsCounter fpsCounter;
    public Text startGame, exitGame;
    public boolean isRunning = true;

    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        startGame = new Text("Start", new Font("Helvetica", Font.PLAIN, 26), Constants.TEXT_COLOR, (Constants.SCREEN_WIDTH/2) - ("Start".length() * 26)/2 + ("Start".length()/2) * 22, Constants.SCREEN_HEIGHT/2 - 50);
        exitGame = new Text("Quit", new Font("Helvetica", Font.PLAIN, 22), Constants.TEXT_COLOR, (Constants.SCREEN_WIDTH/2) - ("Quit".length() * 22)/2 + ("Quit".length()/2) * 16 , Constants.SCREEN_HEIGHT/2 + 50);

        g2 = (Graphics2D)getGraphics();
    }

    public void update(double dt) {
        // System.out.println(1 / dt + " fps");
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        // If mouse hover Start btn
        if (mouseListener.getX() > startGame.x && mouseListener.getX() < startGame.x + startGame.width/2 &&
            mouseListener.getY() > startGame.y - startGame.height/2 && mouseListener.getY() < startGame.y + startGame.height/2) {
            startGame.color = Color.lightGray;
            if (mouseListener.isPressed()) {
                Main.changeState(1);
            }
        } else {
            startGame.color = Color.WHITE;
        }

        // If mouse hover Quit btn
        if (mouseListener.getX() > exitGame.x && mouseListener.getX() < exitGame.x + exitGame.width/2 &&
                mouseListener.getY() > exitGame.y - exitGame.height/2 && mouseListener.getY() < exitGame.y + exitGame.height/2) {
            exitGame.color = Color.lightGray;
        } else {
            exitGame.color = Color.WHITE;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Constants.SCREEN_BACKGROUND);
        g2.fillRect(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        startGame.draw(g2);
        exitGame.draw(g2);
    }

    public void stop() {
        isRunning = false;
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }
}

