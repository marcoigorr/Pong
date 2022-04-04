public class AIController {
    public Rect rect;
    public Ball ball;

    public AIController(Rect rect, Ball ball) {
        this.rect = rect;
        this.ball = ball;
    }

    public void update(double dt) {
        // Se la pallina é in direzione dell'AI
        if (this.ball.getVx() > 0) {
            // Se la pallina sta piú in basso (y maggiore) della barra
            if (this.ball.getY() > this.rect.y + Constants.PADDLE_HEIGHT/2 &&
                this.rect.y + Constants.PADDLE_AI_SPEED * dt < (Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) - Constants.PADDLE_HEIGHT) {

                this.rect.y += (Constants.PADDLE_AI_SPEED * dt);

            // Se la pallina sta piú in alto (y minore) della barra
            } else if (this.ball.getY() < this.rect.y + Constants.PADDLE_HEIGHT/2 &&
                this.rect.y - Constants.PADDLE_AI_SPEED * dt > Constants.TOOLBAR_HEIGHT) {

                this.rect.y -= (Constants.PADDLE_AI_SPEED * dt);
            }
        }
    }

}
