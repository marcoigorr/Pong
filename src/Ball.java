
public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double vy = Constants.BALL_SPEED;
    private double vx = Constants.BALL_SPEED;

    public Ball(Rect ball, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public double calcVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2)) - (this.ball.y + (this.ball.height / 2));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        // Se la pallina va verso sinistra
        if (vx < 0) {
            if (this.ball.x <= leftPaddle.x + leftPaddle.width && this.ball.x >= leftPaddle.x &&
                    this.ball.y >= leftPaddle.y && this.ball.y <= leftPaddle.y + leftPaddle.height) {
                // Calculate new Angle
                double theta = calcVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1 * oldSign);
                this.vy = newVy;
            }

        // Se la pallina va verso destra
        } else if (vx > 0) {
            if (this.ball.x + this.rightPaddle.width >= this.rightPaddle.x && this.ball.x <= this.rightPaddle.x &&
                    this.ball.y >= this.rightPaddle.y && this.ball.y <= this.rightPaddle.y + this.rightPaddle.height
                && this.ball.x <= Constants.SCREEN_WIDTH) {
                // Calculate new Angle
                double theta = calcVelocityAngle(rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1 * oldSign);
                this.vy = newVy;
            }
        }

        // Se la pallina colpisce gli estremi sopra o sotto
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
            Restart();
        // La pallina va dietro al paddle di destra, il giocatore ha fatto punto
        } else if (this.ball.x + this.ball.width > this.rightPaddle.x) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;

            // Repositioning
            Restart();
        }

        if (Integer.parseInt(leftScoreText.text) == Constants.WIN_SCORE) {
            // Player Won
            System.out.println("Player Won");
        } else if (Integer.parseInt(rightScoreText.text) == Constants.WIN_SCORE) {
            // AI Won
            System.out.println("AI Won");
        }
    }

    public void Restart() {
        this.ball.x = Constants.SCREEN_WIDTH/2;
        this.ball.y = Constants.SCREEN_HEIGHT/2;
        this.vx = 0;
        this.vy = 0;
        if (Integer.parseInt(leftScoreText.text) >= 3 || Integer.parseInt(rightScoreText.text) >= 3) {
            this.vx = -(Constants.BALL_SPEED + 200);
            this.vy = -(Constants.BALL_SPEED + 200);
        } else {
            this.vx = -Constants.BALL_SPEED;
            this.vy = -Constants.BALL_SPEED;
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
