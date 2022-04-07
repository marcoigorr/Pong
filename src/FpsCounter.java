import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FpsCounter {
    public Text fpsCounterText, fpsAverageText;
    private double averageFpsCount, fpsCount, fpsSum, avgFps;
    DecimalFormat df = new DecimalFormat("#");

    public FpsCounter(Text fpsCounterText) {
        this.fpsCounterText = fpsCounterText;
        this.averageFpsCount = 60;
        this.fpsCount = 0;
        this.fpsSum = 0;
        this.avgFps = 0;

        df.setRoundingMode(RoundingMode.DOWN);
    }

    public void update(double dt) {
        fpsSum += (1 / dt);
        if (fpsCount >= averageFpsCount) {
            this.fpsCounterText.text =
                    "fps " + df.format(((double)Math.round((1 / dt)))) + " | " +
                    "avg. fps " + df.format((fpsSum / fpsCount));
            fpsCount = 0;
            fpsSum = 0;
        } else {
            fpsCount++;
        }
    }
}
