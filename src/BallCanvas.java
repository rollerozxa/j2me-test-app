import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class BallCanvas extends Canvas {
	private static final int BALL_COUNT = 32;
	private static final int FRAME_DELAY = 30;

	private Ball[] balls;
	private Timer timer;

	public BallCanvas() {
		Image icon = loadIcon();

		Random random = new Random();

		balls = new Ball[BALL_COUNT];

		for (int i = 0; i < BALL_COUNT; i++) {
			int x = (int) (random.nextInt(getWidth() - icon.getWidth()));
			int y = (int) (random.nextInt(getHeight() - icon.getHeight()));
			int dx = (int) (random.nextInt(5) + 1);
			int dy = (int) (random.nextInt(5) + 1);

			balls[i] = new Ball(icon, x, y, dx, dy);
		}
	}

	private Image loadIcon() {
		try {
			InputStream in = getClass().getResourceAsStream("/icon.png");
			Image icon = Image.createImage(in);
			in.close();
			return icon;
		} catch (IOException e) {
			return null;
		}
	}

	protected void paint(Graphics g) {
		if (balls == null) return;

		g.setColor(0x000000);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < balls.length; i++)
			balls[i].paint(g);
	}

	public void start() {
		if (timer != null) return;

		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				tick();
			}
		}, 0, FRAME_DELAY);
	}

	public void stop() {
		if (timer == null) return;

		timer.cancel();
		timer = null;
	}

	private void tick() {
		int width = getWidth();
		int height = getHeight();

		for (int i = 0; i < balls.length; i++)
			balls[i].update(width, height);

		repaint();
	}
}
