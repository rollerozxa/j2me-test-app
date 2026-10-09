import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class Ball {
	private Image image;
	private int x;
	private int y;
	private int dx;
	private int dy;

	public Ball(Image image, int x, int y, int dx, int dy) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public void update(int width, int height) {
		int maxX = width - image.getWidth();
		int maxY = height - image.getHeight();

		x += dx;
		y += dy;

		if (x < 0) {
			x = 0;
			dx = -dx;
		} else if (x > maxX) {
			x = maxX;
			dx = -dx;
		}

		if (y < 0) {
			y = 0;
			dy = -dy;
		} else if (y > maxY) {
			y = maxY;
			dy = -dy;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, x, y, Graphics.TOP | Graphics.LEFT);
	}
}
