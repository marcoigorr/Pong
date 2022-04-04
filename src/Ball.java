
public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double vy = Constants.BALL_SPEED_Y;
    private double vx = Constants.BALL_SPEED_X;

    public Ball(Rect ball, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public void update(double dt) {
        // Se la pallina va verso sinistra
        if (vx < 0) {

            if (this.ball.x <= this.leftPaddle.x + leftPaddle.width && this.ball.x >= this.leftPaddle.x &&
                    this.ball.y >= this.leftPaddle.y && this.ball.y <= this.leftPaddle.y + this.leftPaddle.height) {
                this.vx *= -1;
                // this.vy *= -1;
            }

        // Se la pallina va verso destra
        } else if (vx > 0) {
            if (this.ball.x + this.rightPaddle.width >= this.rightPaddle.x && this.ball.x <= this.rightPaddle.x &&
                    this.ball.y >= this.rightPaddle.y && this.ball.y <= this.rightPaddle.y + this.rightPaddle.height
                && this.ball.x <= Constants.SCREEN_WIDTH) {
                this.vx *= -1;
                // this.vy *= -1;
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

        // Se la pallina va dietro al paddle di sinistra, l'AI ha fatto un punto
        if (this.ball.x + this.ball.width < this.leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;

            // Repositioning
            this.ball.x = Constants.SCREEN_WIDTH/2;
            this.ball.y = Constants.SCREEN_WIDTH/2;
            this.vx = -Constants.BALL_SPEED_X;
            this.vy = -Constants.BALL_SPEED_Y;

        // La pallina va dietro al paddle di destra, il giocatore ha fatto punto
        } else if (this.ball.x + this.ball.width > this.rightPaddle.x) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;

            // Repositioning
            this.ball.x = Constants.SCREEN_WIDTH/2;
            this.ball.y = Constants.SCREEN_WIDTH/2;
            this.vx = Constants.BALL_SPEED_X;
            this.vy = Constants.BALL_SPEED_Y;
        }
    }

    public double getVx() {
        return vx;
    }
    public double getVy() {
        return vy;
    }
    public double getX() {
        return this.ball.x;
    }
    public double getY() {
        return this.ball.y;
    }
}
