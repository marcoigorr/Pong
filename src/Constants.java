import java.awt.Color;

public class Constants {
    // Window Edges
    public static double TOOLBAR_HEIGHT;
    public static double INSETS_BOTTOM;

    // Screen
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final String SCREEN_TITLE = "Pong";
    public static final Color SCREEN_BACKGROUND = Color.BLACK;

    // Paddle
    public static final double PADDLE_WIDTH = 15;
    public static final double PADDLE_HEIGHT = 90;
    public static final double PADDLE_X_OFFSET = 20;
    public static final double PADDLE_Y_OFFSET = SCREEN_HEIGHT/2 - (PADDLE_HEIGHT/2);
    public static final Color PADDLE_COLOR = Color.WHITE;
    public static final double PADDLE_SPEED = 300;

    // AI controller
    public static final double PADDLE_AI_SPEED = 250;

    // Ball
    public static final double BALL_SPEED_Y = 100;
    public static final double BALL_SPEED_X = -250;
    public static final double BALL_WIDTH = 10;

    // Text
    public static final int TEXT_SIZE = 16;
    public static final Color TEXT_COLOR = Color.WHITE;
    public static final double TEXT_X_OFFSET = (SCREEN_WIDTH * 35) / 100;
    public static final double TEXT_X_OFFSET_RIGHT = SCREEN_WIDTH - ((SCREEN_WIDTH * 35) / 100);
    public static final double TEXT_Y_OFFSET = TOOLBAR_HEIGHT + ((SCREEN_HEIGHT * 10) / 100);

}
