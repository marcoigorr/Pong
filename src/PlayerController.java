import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void update(double dt){
        // Se freccia in su premuta
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((rect.y + Constants.PADDLE_SPEED * dt) < (Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) - Constants.PADDLE_HEIGHT) {
                // Se premuto shift aumenta velocitá
                if (keyListener.isKeyPressed(KeyEvent.VK_SHIFT)) {
                    this.rect.y += (Constants.PADDLE_SPEED + 200) * dt;
                } else {
                    this.rect.y += (Constants.PADDLE_SPEED) * dt;
                }
            }
        // Se freccia in giú premuta
        } else if (keyListener.isKeyPressed(KeyEvent.VK_UP )) {
            if ((rect.y - Constants.PADDLE_SPEED * dt) > Constants.TOOLBAR_HEIGHT) {
                // Se premuto shift aumenta velocitá
                if (keyListener.isKeyPressed(KeyEvent.VK_SHIFT)) {
                    this.rect.y -= (Constants.PADDLE_SPEED + 200) * dt;
                } else {
                    this.rect.y -= (Constants.PADDLE_SPEED) * dt;
                }
            }
        }
    }
}
