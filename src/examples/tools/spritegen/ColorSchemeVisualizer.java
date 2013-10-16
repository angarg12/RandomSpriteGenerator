package examples.tools.spritegen;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.JGEngine;

/** Minimal shooter for jgame skeletons. */
public class ColorSchemeVisualizer extends JGEngine {
	static Random random = new Random();
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
		paintScheme(123,60*0);
		paintScheme(234,60*1);
		paintScheme(345,60*2);
		paintScheme(456,60*3);
		paintScheme(567,60*4);
		paintScheme(678,60*5);
	}
	
	public void paintScheme(int seed, int offset){
		ColorSchemeGenerator.fixRandom(seed);
		int[] c = ColorSchemeGenerator.genSpriteColorScheme(
				SpriteGenerator.transcolor, 0, 3, 3);
		for(int i = 0; i < c.length; i++){
			int b = c[i]&0xFF;
			int g = c[i]>>8&0xFF;
			int r = c[i]>>16&0xFF;
			System.out.println(r+" "+g+" "+b);
			JGColor boxColor = new JGColor(r, 
					g,
					b);
			setColor(boxColor);
			drawRect(20*i+10, 
					20+offset, 
					20, 
					50, 
					true, 
					false);
		}
		// Move the sprites to their position on the screen.
		moveObjects(null, 0);
	}
}
