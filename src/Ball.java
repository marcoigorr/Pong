import javax.crypto.Cipher;
import javax.net.ssl.CertPathTrustManagerParameters;
import java.util.concurrent.CompletionService;

public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;

    private double vy = Constants.BALL_SPEED_Y;
    private double vx = Constants.BALL_SPEED_X;

    public Ball(Rect ball, Rect leftPaddle, Rect rightPaddle) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.ball.x <= this.leftPaddle.x + leftPaddle.width && this.ball.x >= this.leftPaddle.x &&
                    this.ball.y >= this.leftPaddle.y && this.ball.y <= this.leftPaddle.y + this.leftPaddle.height) {
                this.vx *= -1;
                // this.vy *= -1;
            } else if (this.ball.x + this.ball.width < this.leftPaddle.x) {
                System.out.println("You Lost");
            }
        } else if (vx > 0) {
            if (this.ball.x + this.rightPaddle.width >= this.rightPaddle.x && this.ball.x <= this.rightPaddle.x &&
                    this.ball.y >= this.rightPaddle.y && this.ball.y <= this.rightPaddle.y + this.rightPaddle.height
                && this.ball.x <= Constants.SCREEN_WIDTH) {
                this.vx *= -1;
                // this.vy *= -1;
            } else if (this.ball.x + this.ball.width > this.rightPaddle.x) {
                System.out.println("AI Lost");
            }
        }

        if (vy > 0) {
            if (this.ball.y + this.ball.height > Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.ball.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        this.ball.x += vx * dt;
        this.ball.y += vy * dt;
    }

    public double getVx() {
        return vx;
    }
    public double getVy() {
        return vy;
    }
    public double getX() {
        return ball.x;
    }
    public double getY() {
        return ball.y;
    }
}
