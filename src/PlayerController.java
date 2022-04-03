import java.awt.*;
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
            if ((rect.y + 150 * dt) < Constants.SCREEN_HEIGHT - (Constants.PADDLE_HEIGHT + 5)) {
                this.rect.y += 150 * dt;
            }
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            if ((rect.y - 150 * dt) > Constants.TOOLBAR_HEIGHT) {
                this.rect.y += -150 * dt;
            }
        }
    }
}
