import java.util.Random;

public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private Random random = new Random();
    private double initAngle = getRandomAngle();
    private double vx = Constants.BALL_SPEED * Math.cos(initAngle);
    private double vy = Constants.BALL_SPEED * Math.sin(initAngle);

    public Ball(Rect ball, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.ball = ball;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    private double getRandomAngle() {
        return random.nextInt(((int)Constants.MAX_INIT_ANGLE - (int)Constants.MIN_INIT_ANGLE) + 1) + Constants.MIN_INIT_ANGLE;
    }

    public double calcVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2)) - (this.ball.y + (this.ball.height / 2));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2);
        double theta = normalIntersectY * Constants.MAX_INIT_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        // Se la pallina va verso sinistra
        if (vx < 0) {
            if (this.ball.x <= leftPaddle.x + leftPaddle.width && this.ball.x >= leftPaddle.x &&
                    this.ball.y >= leftPaddle.y && this.ball.y <= leftPaddle.y + leftPaddle.height) {
                this.vx *= -1;
                // Calculate new Angle
                // double theta = calcVelocityAngle(leftPaddle);
                // double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                // double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                // double oldSign = Math.signum(vx);
                // this.vx = newVx * (-1 * oldSign);
                // this.vy = newVy;
            }

        // Se la pallina va verso destra
        } else if (vx > 0) {
            if (this.ball.x + this.ball.width >= rightPaddle.x && this.ball.x <= rightPaddle.x + rightPaddle.width &&
                    this.ball.y >= rightPaddle.y && this.ball.y <= rightPaddle.y + rightPaddle.height) {
                this.vx *= -1;
                // Calculate new Angle
                // double theta = calcVelocityAngle(rightPaddle);
                // double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                // double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                // double oldSign = Math.signum(vx);
                // this.vx = newVx * (-1 * oldSign);
                // this.vy = newVy;
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

        // Se la pallina va dietro al paddle di sinistra, l'AI ha fatto un punto
        if (this.ball.x + this.ball.width < leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;

            // Repositioning
            restart();

        // La pallina va dietro al paddle di destra, il giocatore ha fatto punto
        } else if (this.ball.x + this.ball.width > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;

            // Repositioning
            restart();
        }

        // Controlla i punteggi dei giocatori
        if (Integer.parseInt(leftScoreText.text) == Constants.WIN_SCORE) {
            // Player Won, STOP
            System.out.println("Player Won");
            this.vx = 0;
            this.vy = 0;
        } else if (Integer.parseInt(rightScoreText.text) == Constants.WIN_SCORE) {
            // AI Won, STOP
            System.out.println("AI Won");
            this.vx = 0;
            this.vy = 0;
        }

        // Avanza
        this.ball.x += vx * dt;
        this.ball.y += vy * dt;
    }

    public void restart() {
        double newAngle = getRandomAngle();
        this.ball.x = (Constants.SCREEN_WIDTH/2) - this.ball.width/2;
        this.ball.y = (Constants.SCREEN_HEIGHT/2) - this.ball.height/2;
        this.vx = 0;
        this.vy = 0;
        if (Integer.parseInt(leftScoreText.text) >= 3 || Integer.parseInt(rightScoreText.text) >= 3) {
            this.vx = -(Constants.BALL_SPEED + 100) * Math.cos(newAngle);
            this.vy = -(Constants.BALL_SPEED + 100) * Math.sin(newAngle);
        } else {
            this.vx = -Constants.BALL_SPEED * Math.cos(newAngle);
            this.vy = -Constants.BALL_SPEED * Math.sin(newAngle);
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
