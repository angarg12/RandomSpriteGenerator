package examples.tools.spritegen.color;

import java.util.Random;

import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;

/** Minimal shooter for jgame skeletons. */
public class ColorSchemeVisualizer extends JGEngine {
	static Random random = new Random();
	int i1 = 123;
	int i2 = 234;
	int i3 = 345;
	int i4 = 456;
	int i5 = 567;
	int i6 = 678;
	private static final long serialVersionUID = 2698073082669353351L;

	public static void fixRandom(int seed) {
		random = new Random(seed);
	}

	public static void main(String[] args) {
		new ColorSchemeVisualizer(new JGPoint(1024, 768));
	}

	public ColorSchemeVisualizer() {
		initEngineApplet();
	}

	public ColorSchemeVisualizer(JGPoint size) {
		initEngine(size.x, size.y);
	}

	public void initCanvas() {
		setCanvasSettings(32, 24, 16, 16, null, null, null);
	}

	public void initGame() {
		if (isMidlet()) {
			setFrameRate(10, 1);
			setGameSpeed(2.0);
		} else {
			setFrameRate(25, 1);
		}

		setBGColor(new JGColor(80, 80, 80));
		dbgShowFullStackTrace(true);
		dbgShowMessagesInPf(false);
		setSmoothing(false);
	}

	public void paintFrame() {
		if (getMouseButton(1)) {
			i1 = random.nextInt();
			i2 = random.nextInt();
			i3 = random.nextInt();
			i4 = random.nextInt();
			i5 = random.nextInt();
			i6 = random.nextInt();
		}
		paintScheme(i1, 60 * 0);
		paintScheme(i2, 60 * 1);
		paintScheme(i3, 60 * 2);
		paintScheme(i4, 60 * 3);
		paintScheme(i5, 60 * 4);
		paintScheme(i6, 60 * 5);
	}

	public void paintScheme(int seed, int offset) {
		ColorSchemeGenerator.fixRandom(seed);
		System.out.println("generate");
		int[] c = ColorSchemeGenerator.genSpriteColorScheme(
				ColorScheme.TRANSPARENT, 0, 4, 3);

		// System.out.println(seed);
		for (int i = 0; i < c.length; i++) {
			int b = c[i] & 0xFF;
			int g = c[i] >> 8 & 0xFF;
			int r = c[i] >> 16 & 0xFF;
			// System.out.println(r+" "+g+" "+b);
			JGColor boxColor = new JGColor(r, g, b);
			setColor(boxColor);
			drawRect(20 * i + 10, 20 + offset, 20, 50, true, false);
			// Move the sprites to their position on the screen.
			moveObjects(null, 0);
		}
	}
}
