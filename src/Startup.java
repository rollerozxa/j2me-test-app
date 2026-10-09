import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Startup extends MIDlet {
	private BallCanvas canvas;

	public void startApp() {
		if (canvas == null)
			canvas = new BallCanvas();

		Display.getDisplay(this).setCurrent(canvas);
		canvas.start();
	}

	public void pauseApp() {
		if (canvas != null)
			canvas.stop();
	}

	public void destroyApp(boolean unconditional) {
		if (canvas != null)
			canvas.stop();
	}
}
