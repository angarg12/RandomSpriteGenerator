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

	Sprite[][] sprites = new Sprite[NUMBER_SPRITES_X][NUMBER_SPRITES_Y];
	
	public static void fixRandom(int seed) {
		random = new Random(seed);
	}

	public static void main(String[] args) {
		new Main(new JGPoint(1024, 768));
	}

	public Main() {
		initEngineApplet();
		createSprites();
		drawSprites();
	}

	public Main(JGPoint size) {
		initEngine(size.x, size.y);
		SpriteGenerator.fixRandom(1000);
		ColorSchemeGenerator.fixRandom(1000);
		Main.fixRandom(1000);
		createSprites();
		drawSprites();
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
		if (isClickInsideSpriteMatrix()) {
			selectedSpriteX = ((getMouseX() - 10) / SEPARATION_X);
			selectedSpriteY = ((getMouseY() - 10) / SEPARATION_Y);
			Sprite sprite = sprites[selectedSpriteX][selectedSpriteY];
			
			selectionBoxTimer = 15;

			if(getMouseButton(1)){
				clearMouseButton(1);
				saveSprite(sprite);
			}else if(getMouseButton(3)){
				clearMouseButton(3);
				createMutations(sprite);
				drawSprites();
			}
		} else {
			createSprites();
			drawSprites();
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
	 * Saves a sprite to a .png file.
	 */
	private void saveSprite(Sprite sprite){
		String filename = getSavefileName();
		ImageUtils.writePNG("."+File.separator + filename+".png",
				sprite.pixels,
				sprite.pixels.length,
				sprite.pixels[0].length,
				ColorScheme.TRANSPARENT);
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
	 * Fills the sprite data structure with sprites.
	 */
	private void createSprites(){
		int[][] colorSchemes = generateColorSchemes();

		for (int i = 0; i < NUMBER_SPRITES_X; i++) {
			for (int j = 0; j < NUMBER_SPRITES_Y; j++) {
				int[] colorScheme = colorSchemes[(int) (random.nextDouble() * colorSchemes.length)];
				SpriteGenerator generator = randomizedGenerator();
				sprites[i][j] = generator.createSprite(colorScheme);			
			}
		}
	}
	
	/**
	 * Randomizes a new SpriteGenerator
	 * @return
	 */
	private SpriteGenerator randomizedGenerator(){
		final double NO_SHADE_PROB = 0.75;
		final double BEVEL_SHADE_PROB = 0.125;
		// The rest up to 1 is the Gouraud prob
		final double HIGHLIGHT_PROB = 0.4;
		
		// Choose a random shape
		SpriteGenerator generator = SpriteGenerator.shapes2[(int) (random.nextDouble() * SpriteGenerator.shapes2.length)];
		// Defines the probabilities of sprite features
		double randomRoll = random.nextDouble();
		// NO_SHADE_PROB of time the sprite doesn't have a shadow
		if(randomRoll < NO_SHADE_PROB){
			generator.shading = SpriteGenerator.NONE;
		// BEVEL_SHADE_PROB of time it has Bevel shadow
		}else if((randomRoll-NO_SHADE_PROB) < BEVEL_SHADE_PROB){
			generator.shading = SpriteGenerator.BEVEL;
		// the rest of the time it has Gouraud shadow
		}else{
			generator.shading = SpriteGenerator.GOURAUD;
		}
		
		// If it doesn't have a shadow, it has a HIGHLIGHT_PROB chance of highlight
		if(generator.shading == 0){
			generator.highlight_prob = HIGHLIGHT_PROB;
		}else{
			generator.highlight_prob = 0;
		}
		return generator;
	}
	
	/**
	 * Fills the sprite data structure with mutations of a sprite.
	 */
	private void createMutations(Sprite origin){
		final double SAMENESS_FACTOR = 0.8;
		int[][] colorSchemes = generateColorSchemes();

		for (int i = 0; i < NUMBER_SPRITES_X; i++) {
			for (int j = 0; j < NUMBER_SPRITES_Y; j++) {
				int[] colorScheme = colorSchemes[(int) (random.nextDouble() * colorSchemes.length)];
				SpriteGenerator generator = origin.gen;
				// Create a mutant of the origin sprite
				Sprite mutant = generator.mergeSprites(
						origin,
						generator.createSprite(colorScheme), 
						SAMENESS_FACTOR);
				// A mere formality to not override the origin sprite. 
				if (sprites[i][j] != origin) {
					sprites[i][j] = mutant;
				}		
			}
		}
	}
	
	/**
	 * Generates an array of color schemes
	 * @return
	 */
	private int[][] generateColorSchemes(){
		final int COLOR_SCHEMES_COUNT = 64;
		// generate new color tables. Part handpicked, part random
		int[][] colorSchemes = new int[COLOR_SCHEMES_COUNT][];
		for (int i = 0; i < colorSchemes.length; i++) {
			if (i < ColorScheme.colorSchemes().length) {
				colorSchemes[i] = ColorScheme.colorSchemes()[i];
			} else {
				colorSchemes[i] = ColorSchemeGenerator.genSpriteColorScheme(
						ColorScheme.TRANSPARENT, 0, 3, 3);
			}
		}
		return colorSchemes;
	}
	
	/**
	 * Draw the sprites in the sprite data structure.
	 */
	private void drawSprites(){
		removeObjects(null, 0);
		for (int i = 0; i < NUMBER_SPRITES_X; i++) {
			for (int j = 0; j < NUMBER_SPRITES_Y; j++) {
				int framesStart = 0;
				int framesCount = 1;
				
				// Index adjustment, since if the sprite is animated the first frame
				// (the standing frame) must be ommited.
				if (sprites[i][j].getNrFrames() > 1) {
					framesStart = 1;
					framesCount = sprites[i][j].getNrFrames() - 1;
				}
				
				// Generates a new unique id for each sprite.
				String spriteId = "rand"+(i+j*NUMBER_SPRITES_Y);
				
				String[] frames = new String[framesCount];
				for (int f = 0; f < framesCount; f++) {
					frames[f] = spriteId + "f" + f;
					// Defines a new image for each frame.
					defineImageFromData(frames[f], "-", 0,
							sprites[i][j].getWidth() / sprites[i][j].getNrFrames(),
							sprites[i][j].getHeight(), 
							sprites[i][j].getData(f+framesStart), 
							0,
							sprites[i][j].getWidth() / sprites[i][j].getNrFrames(),
							"", -1, -1, -1, 1);
				}
				// Defines a new animation from each frame.
				// XXX animations not undefined: memory leak
				defineAnimation(spriteId, 
						frames,
						random.nextDouble() * 0.3 + 0.2);
				
				// Create a new JGObject, the ones that are rendered by the engine
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
