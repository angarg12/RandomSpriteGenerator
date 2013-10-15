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
	static int NUMBER_SPRITES_X = 16;
	static int NUMBER_SPRITES_Y = 16;
	static int SEPARATION_X = 22;
	static int SEPARATION_Y = 22;
	static Random random = new Random();
	private static final long serialVersionUID = 2698073082669353351L;

	// Define sprite coordinates in the sprite matrix
	int selectedSpriteX = 0;
	int selectedSpriteY = 0;
	int selectionBoxTimer = 0;

	Sprite selectedSprite = null;
	Sprite[][] sprites = new Sprite[NUMBER_SPRITES_X][NUMBER_SPRITES_Y];
	
	public static void fixRandom(int seed) {
		random = new Random(seed);
	}

	public static void main(String[] args) {
		new Main(new JGPoint(1024, 768));
	}

	public Main() {
		initEngineApplet();
		createAndDrawSprites();
	}

	public Main(JGPoint size) {
		initEngine(size.x, size.y);
		createAndDrawSprites();
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
			drawRect(5 + SEPARATION_X * selectedSpriteX, 
					5 + SEPARATION_Y * selectedSpriteY, 
					SEPARATION_X, 
					SEPARATION_Y, 
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
			selectedSpriteX = ((getMouseX() - 10) / SEPARATION_X);
			selectedSpriteY = ((getMouseY() - 10) / SEPARATION_Y);

			if (selectedSpriteX >= 0 && 
					selectedSpriteX < NUMBER_SPRITES_X &&
					selectedSpriteX >= 0 &&
					selectedSpriteX < NUMBER_SPRITES_Y) {
				selectedSprite = sprites[selectedSpriteX][selectedSpriteY];
				
				selectionBoxTimer = 15;

				String filename = getSavefileName();
				
				ImageUtils.writePNG("."+File.separator + filename+".png",
						selectedSprite.pixels,
						selectedSprite.pixels.length,
						selectedSprite.pixels[0].length,
						SpriteGenerator.transcolor);
			} else {
				createAndDrawSprites();
			}
		} else {
			createAndDrawSprites();
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
				getMouseX() < 10 + NUMBER_SPRITES_X * SEPARATION_X && 
				getMouseY() >= 10 && 
				getMouseY() < 10 + NUMBER_SPRITES_Y * SEPARATION_Y;
	}
	
	/**
	 * Fills the sprite data structure with sprites and draws them.
	 */
	private void createAndDrawSprites(){
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
		for (int i = 0; i < NUMBER_SPRITES_X; i++) {
			for (int j = 0; j < NUMBER_SPRITES_Y; j++) {
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
				sprites[i][j] = shape.createSprite(coltable);
				//sprites[i] = shape.mergeSprites(sprt,
				//		shape.createSprite(coltable), 0.9);
				if (selectedSprite != null) {
					if (selectedSpriteX == i && selectedSpriteX == j) {
						sprites[i][j] = selectedSprite;
					} else {
						sprites[i][j] = shape.mergeSprites(
								selectedSprite, 
								sprites[i][j], 
								0.7);
					}
				}
				int startframe = 0;
				int nrframes = 1;
				if (sprites[i][j].getNrFrames() > 1) {
					startframe = 1;
					nrframes = sprites[i][j].getNrFrames() - 1;
				}
				String spriteId = "rand"+(i+j*NUMBER_SPRITES_Y);
				String[] frames = new String[nrframes];
				for (int f = startframe; f < sprites[i][j].getNrFrames(); f++) {
					frames[f - startframe] = spriteId + "f" + f;
	
					defineImageFromData(frames[f - startframe], "-", 0,
							sprites[i][j].getWidth() / sprites[i][j].getNrFrames(),
							sprites[i][j].getHeight(), 
							sprites[i][j].getData(f), 
							0,
							sprites[i][j].getWidth() / sprites[i][j].getNrFrames(),
							"", -1, -1, -1, 1);
				}
				// XXX animations not undefined: memory leak
				defineAnimation(spriteId, 
						frames,
						random.nextDouble() * 0.3 + 0.2);
				
				new JGObject(spriteId, 
						true, 
						10 + i * SEPARATION_X, 
						10 + j * SEPARATION_Y, 
						0,
						spriteId);						
			}
		}
	}
}
