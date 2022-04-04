import java.awt.*;
import java.sql.Ref;

public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;

    private double vy = 200;
    private double vx = -230;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.rect.x <= this.leftPaddle.x + leftPaddle.width && this.rect.x >= this.leftPaddle.x &&
                    this.rect.y >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                this.vx *= -1;
                this.vy *= -1;
            } else if (this.rect.x + this.rect.width < this.leftPaddle.x) {
                System.out.println("You Lost");
            }
        } else if (vx > 0) {
            if (this.rect.x + this.rightPaddle.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x &&
                    this.rect.y >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height
                && this.rect.x <= Constants.SCREEN_WIDTH) {
                this.vx *= -1;
                this.vy *= -1;
            } else if (this.rect.x + this.rect.width > this.rightPaddle.x) {
                System.out.println("AI Lost");
            }
        }

        if (vy > 0) {
            if (this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.rect.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }
}
