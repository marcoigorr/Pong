public class FpsCounter {
    public Text fpsCounterText;

    public FpsCounter(Text fpsCounterText) {
        this.fpsCounterText = fpsCounterText;
    }

    public void update(double dt) {
        fpsCounterText.text = "" + ((double)Math.round((1 / dt) * 100d) / 100d);
    }

}
