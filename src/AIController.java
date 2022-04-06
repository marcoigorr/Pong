public class AIController {
    public Rect rect;
    public Ball ball;

    public AIController(Rect rect, Ball ball) {
        this.rect = rect;
        this.ball = ball;
    }

    public void update(double dt) {
        // Se la pallina é in direzione dell'AI
        if (ball.getVx() > 0) {
            // Se la pallina sta piú in basso (y maggiore) della barra
            if (ball.getY() > rect.y + Constants.PADDLE_HEIGHT/2 &&
                rect.y + Constants.PADDLE_AI_SPEED * dt < (Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) - Constants.PADDLE_HEIGHT) {

                // Scatto se c'è molta differenza di altezza tra pallina e paddle
                if (Math.abs(ball.getY() - (rect.y + Constants.PADDLE_HEIGHT)) >= 100) {
                    rect.y += ((Constants.PADDLE_AI_SPEED + Constants.PADDLE_SPEED_BOOST) * dt);
                } else {
                    rect.y += (Constants.PADDLE_AI_SPEED * dt);
                }

            // Se la pallina sta piú in alto (y minore) della barra
            } else if (ball.getY() < rect.y + Constants.PADDLE_HEIGHT/2 &&
                rect.y - Constants.PADDLE_AI_SPEED * dt > Constants.TOOLBAR_HEIGHT) {

                // Scatto se c'è molta differenza di altezza tra pallina e paddle
                if (Math.abs(ball.getY() - (rect.y + Constants.PADDLE_HEIGHT)) >= 100) {
                    rect.y -= ((Constants.PADDLE_AI_SPEED + Constants.PADDLE_SPEED_BOOST) * dt);
                } else {
                    rect.y -= (Constants.PADDLE_AI_SPEED * dt);
                }
            }
        } else if ((int)(rect.y + Constants.PADDLE_HEIGHT/2) != (int)Constants.SCREEN_HEIGHT/2) {
            // Riposizionati al centro
            if ((int)(rect.y + Constants.PADDLE_HEIGHT/2) < (int)Constants.SCREEN_HEIGHT/2) {
                rect.y += Constants.PADDLE_AI_SPEED * dt;
            } else {
                rect.y -= Constants.PADDLE_AI_SPEED * dt;
            }
        }
    }

}
