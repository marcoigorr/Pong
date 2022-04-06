import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Text {
    public String text;
    public Font font;
    public Color color;
    public double x, y;

    public Text(String text, Font font, Color color, double x, double y) {
        this.text = text;
        this.font = font;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Text(int text, Font font, Color color, double x, double y) {
        this.text = "" + text;
        this.font = font;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, (float)x, (float)y);
    }
}
