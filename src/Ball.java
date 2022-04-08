import java.util.Random;

public class Ball {
    public Rect ball;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double ballSpeed = Constants.BALL_SPEED;

    private Random random = new Random();
    private double initAngle = getRandomAngle();
    private double vx = ballSpeed;
    private double vy = ballSpeed * Math.sin(initAngle);
    private double bonusSpeed;

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
        // Più la pallina è rimbalzata verso il bordo del paddle, più il valore si avvicina a 1.0 e quindi più l'angolo è inclinato
        // Differenza tra altezza paddle e pallina
        double relativeIntersectY = (paddle.y + ((paddle.height - 10) / 2)) - (this.ball.y + (this.ball.height / 2));
        // Divisione per l'altezza del paddle/2 per ottenere un valore tra 0 e 1.0
        double normalIntersectY = relativeIntersectY / ((paddle.height - 10) / 2);
        // Angolo di rimbalzo
        double theta = Math.abs(normalIntersectY) * Constants.MAX_BOUNCING_ANGLE;

        bonusSpeed = Math.abs(normalIntersectY) * Constants.MAX_BONUS_SPEED;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        // Se la pallina va verso sinistra
        if (vx < 0) {
            if (this.ball.x <= leftPaddle.x + leftPaddle.width && this.ball.x >= leftPaddle.x &&
                    this.ball.y >= leftPaddle.y && this.ball.y <= leftPaddle.y + leftPaddle.height) {
                // this.vx *= -1;
                // Calculate new Angle
                double theta = calcVelocityAngle(leftPaddle);
                double newVy = Math.sin(theta) * (ballSpeed + bonusSpeed);

                this.vx = (Math.signum(vx) * -1) * (Math.cos(theta) * (ballSpeed + bonusSpeed));

                // Se la pallina va verso il basso, rimane verso il basso
                if (this.vy > 0) {
                    this.vy = Math.abs(newVy);
                } else if (this.vy < 0) {
                    this.vy = -(Math.abs(newVy));
                }
            }

        // Se la pallina va verso destra
        } else if (vx > 0) {
            if (this.ball.x + this.ball.width >= rightPaddle.x && this.ball.x <= rightPaddle.x + rightPaddle.width &&
                    this.ball.y >= rightPaddle.y && this.ball.y <= rightPaddle.y + rightPaddle.height) {
                // this.vx *= -1;
                // Calculate new Angle
                double theta = calcVelocityAngle(rightPaddle);
                double newVx = (Math.abs(Math.cos(theta)) * (ballSpeed + bonusSpeed));
                double newVy = Math.sin(theta) * (ballSpeed + bonusSpeed);

                this.vx = (Math.signum(vx) * -1) * newVx;

                // La pallina rimane con la stessa traiettoria, cambia solo verso x
                if (this.vy > 0) {
                    this.vy = Math.abs(newVy);
                } else if (this.vy < 0) {
                    this.vy = -(Math.abs(newVy));
                }
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
            this.ball.x = (Constants.SCREEN_WIDTH/2) - this.ball.width/2;
            this.ball.y = (Constants.SCREEN_HEIGHT/2) - this.ball.height/2;

            reposition();

        // La pallina va dietro al paddle di destra, il giocatore ha fatto punto
        } else if (this.ball.x + this.ball.width > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;

            reposition();
        }

        // Controlla i punteggi dei giocatori
        if (Integer.parseInt(leftScoreText.text) == Constants.WIN_SCORE) {
            // Player Won, STOP
            Main.changeState(2);
        } else if (Integer.parseInt(rightScoreText.text) == Constants.WIN_SCORE) {
            // AI Won, STOP
            Main.changeState(2);
        }

        // Avanza
        this.ball.x += vx * dt;
        this.ball.y += vy * dt;

    }

    public void reposition() {
        double newAngle = getRandomAngle();
        this.ball.x = (Constants.SCREEN_WIDTH/2) - this.ball.width/2;
        this.ball.y = (Constants.SCREEN_HEIGHT/2) - this.ball.height/2;
        leftPaddle.y = Constants.PADDLE_Y_OFFSET;

        if (Integer.parseInt(leftScoreText.text) >= 3 || Integer.parseInt(rightScoreText.text) >= 3) {
            ballSpeed += 100;
            this.vx = -(ballSpeed) * Math.cos(newAngle);
            this.vy = -(ballSpeed) * Math.sin(newAngle);
        } else {
            this.vx = -ballSpeed * Math.cos(newAngle);
            this.vy = -ballSpeed * Math.sin(newAngle);
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
