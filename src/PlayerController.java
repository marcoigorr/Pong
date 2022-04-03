import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void update(double dt){
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            moveUp(dt);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            moveDown(dt);
        }
    }

    public void moveUp(double dt) {
        if ((rect.y + Constants.PADDLE_SPEED * dt) < (Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) - Constants.PADDLE_HEIGHT) {
            this.rect.y += Constants.PADDLE_SPEED * dt;
        }
    }
    public void moveDown(double dt) {
        if ((rect.y - Constants.PADDLE_SPEED * dt) > Constants.TOOLBAR_HEIGHT) {
            this.rect.y += -Constants.PADDLE_SPEED * dt;
        }
    }
}
