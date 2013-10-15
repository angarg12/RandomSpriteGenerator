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
public class Main extends JGEngine {
	static int NUMBER_SPRITES_HORIZONTAL = 16;
	static int NUMBER_SPRITES_VERTICAL = 16;
	static int NUMBER_SPRITES_TOTAL = NUMBER_SPRITES_HORIZONTAL*NUMBER_SPRITES_VERTICAL;
	static int SEPARATION_HORIZONTAL = 22;
	static int SEPARATION_VERTICAL = 22;
	static Random random = new Random();
	private static final long serialVersionUID = 2698073082669353351L;

	// Define sprite coordinates in the sprite matrix
	int selectedSpriteXCoordinate = 0;
	int selectedSpriteYCoordinate = 0;
	int selectionBoxTimer = 0;
	int selectedSpriteIndex = 0;

	Sprite selectedSprite = null;
	Sprite[] sprites = new Sprite[NUMBER_SPRITES_TOTAL];
	
	public static void fixRandom(int seed) {
		random = new Random(seed);
	}

	public static void main(String[] args) {
		new Main(new JGPoint(1024, 768));
	}

	public Main() {
		initEngineApplet();
		fillSpriteVector();
	}

	public Main(JGPoint size) {
		initEngine(size.x, size.y);
		fillSpriteVector();
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
		drawInfo();
		drawSelectionBoxFlash();
		// Move the sprites to their position on the screen.
		moveObjects(null, 0);
		if (isMouseClicked()) {
			processMouseClick();
		}
	}
	
	/**
	 * Draw the text information about the app.
	 */
	private void drawInfo(){
		setFont(new JGFont("Sans", 0, 10));
		drawString("Random Sprite Generator", pfWidth(), 30, 1);
		drawString("(version 1.0, 2nd April 2013)", pfWidth(), 45, 1);
		drawString("by Boris van Schooten", pfWidth(), 60, 1);
		drawString("Click on a sprite to save.", pfWidth(), 100, 1);
		drawString("Click outside sprite region", pfWidth(), 130, 1);
		drawString("to generate new sprites.", pfWidth(), 145, 1);
	}
	/**
	 * Flash a box around a selected sprite.
	 * Starts up white and fades to black.
	 */
	private void drawSelectionBoxFlash(){
		if (selectionBoxTimer > 0) {
			JGColor boxColor = new JGColor(selectionBoxTimer / 15.0, 
					selectionBoxTimer / 15.0,
					selectionBoxTimer / 15.0);
			setColor(boxColor);
			drawRect(5 + SEPARATION_HORIZONTAL * selectedSpriteXCoordinate, 
					5 + SEPARATION_VERTICAL * selectedSpriteYCoordinate, 
					SEPARATION_HORIZONTAL, 
					SEPARATION_VERTICAL, 
					false, 
					false);
			selectionBoxTimer--;
		}
	}
	
	private boolean isMouseClicked(){
		return getMouseButton(1) || getMouseButton(3);
	}
	
	private void processMouseClick(){
		clearMouseButton(1);
		clearMouseButton(3);
		if (isClickInsideSpriteMatrix()) {
			selectedSpriteIndex = ((getMouseX() - 10) / SEPARATION_HORIZONTAL) + NUMBER_SPRITES_HORIZONTAL * 
					((getMouseY() - 10) / SEPARATION_VERTICAL);
			if (selectedSpriteIndex >= 0 && selectedSpriteIndex < NUMBER_SPRITES_TOTAL) {
				selectedSprite = sprites[selectedSpriteIndex];
				selectedSpriteXCoordinate = selectedSpriteIndex % NUMBER_SPRITES_HORIZONTAL;
				selectedSpriteYCoordinate = selectedSpriteIndex / NUMBER_SPRITES_HORIZONTAL;
				selectionBoxTimer = 15;

				String filename = getSavefileName();
				
				ImageUtils.writePNG("."+File.separator + filename+".png",
						selectedSprite.pixels,
						selectedSprite.pixels.length,
						selectedSprite.pixels[0].length,
						SpriteGenerator.transcolor);
			} else {
				fillSpriteVector();
			}
		} else {
			fillSpriteVector();
		}
	}
	
	/**
	 * Returns a string representation of a number filled with zeros
	 * up to len.
	 * 
	 * @param len
	 * @param number
	 * @return
	 */
	private String fillWithZeros(int len, int number) {
		String ret = "" + number;
		while (ret.length() < len)
			ret = "0" + ret;
		return ret;
	}

	/**
	 * Returns the filename of the sprite to be saved properly formated.
	 * 
	 * @return
	 */
	private String getSavefileName(){
		Calendar calendar = Calendar.getInstance();
		
		int msec = calendar.get(Calendar.MILLISECOND);
		int sec = calendar.get(Calendar.SECOND);
		int min = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		return "sprite" 
				+ "-" + fillWithZeros(2, day) 
				+ "-" + fillWithZeros(2, hour) 
				+ "-" + fillWithZeros(2, min)
				+ "-" + fillWithZeros(2, sec) 
				+ "." + fillWithZeros(3, msec);
	}
	
	/**
	 * Checks if a mouse click is inside the sprite matrix.
	 * 
	 * @return
	 */
	private boolean isClickInsideSpriteMatrix(){
		return getMouseX() >= 10 && 
				getMouseX() < 10 + NUMBER_SPRITES_HORIZONTAL * SEPARATION_HORIZONTAL && 
				getMouseY() >= 10 && 
				getMouseY() < 10 + NUMBER_SPRITES_VERTICAL * SEPARATION_VERTICAL;
	}
	
	private void fillSpriteVector(){
		int x = 0;
		int y = 0;
		removeObjects(null, 0);
		// generate new color tables. Part handpicked, part random
		int[][] coltables = new int[64][];
		for (int i = 0; i < coltables.length; i++) {
			if (i < SpriteGenerator.coltables.length) {
				coltables[i] = SpriteGenerator.coltables[i];
			} else {
				coltables[i] = ColorSchemeGenerator.genSpriteColorScheme(
						SpriteGenerator.transcolor, 0, 3, 3);
			}
		}
		SpriteGenerator.coltables = coltables;

		//Sprite sprt = shape.createSprite(coltable);
		for (int i = 0; i < NUMBER_SPRITES_TOTAL; i++) {
			int shapetype = (int) (random.nextDouble() * SpriteGenerator.shapes2.length);
			int[] coltable = coltables[(int) (random.nextDouble() * coltables.length)];
			SpriteGenerator shape = SpriteGenerator.shapes2[shapetype];
			if (selectedSprite != null) {
				shape = selectedSprite.gen;
			} else {
				shape.shading = random.nextDouble() < 0.75 ? 0 : random
						.nextDouble() < 0.5 ? 1 : 2;
				shape.highlight_prob = shape.shading == 0 ? 0.4 : 0;
			}
			sprites[i] = shape.createSprite(coltable);
			//sprites[i] = shape.mergeSprites(sprt,
			//		shape.createSprite(coltable), 0.9);
			if (selectedSprite != null) {
				if (selectedSpriteIndex == i) {
					sprites[i] = selectedSprite;
				} else {
					sprites[i] = shape.mergeSprites(
							selectedSprite, 
							sprites[i], 
							0.7);
				}
			}
			int startframe = 0;
			int nrframes = 1;
			if (sprites[i].getNrFrames() > 1) {
				startframe = 1;
				nrframes = sprites[i].getNrFrames() - 1;
			}
			String[] frames = new String[nrframes];
			for (int f = startframe; f < sprites[i].getNrFrames(); f++) {
				frames[f - startframe] = "rand" + i + "f" + f;

				defineImageFromData(frames[f - startframe], "-", 0,
						sprites[i].getWidth() / sprites[i].getNrFrames(),
						sprites[i].getHeight(), sprites[i].getData(f), 0,
						sprites[i].getWidth() / sprites[i].getNrFrames(),
						"", -1, -1, -1, 1);
			}
			// XXX animations not undefined: memory leak
			defineAnimation("rand" + i, 
					frames,
					random.nextDouble() * 0.3 + 0.2);
			new JGObject("rand", 
					true, 
					10 + x * SEPARATION_HORIZONTAL, 
					10 + y * SEPARATION_VERTICAL, 
					0,
					"rand" + i);
			x++;
			if (x >= NUMBER_SPRITES_HORIZONTAL) {
				x = 0;
				y++;
			}
		}
	}
}
