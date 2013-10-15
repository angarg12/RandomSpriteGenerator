package examples.tools.spritegen;

import jgame.*;
import jgame.platform.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.Random;

/** Minimal shooter for jgame skeletons. */
public class Main extends JGEngine {
	static Random random = new Random();
	public static void fixRandom(int seed){
		random = new Random(seed);
	}
	public static void main(String[] args) {
		// new Main(new JGPoint(512,384));
		new Main(new JGPoint(1024, 768));
	}

	public Main() {
		initEngineApplet();
	}

	public Main(JGPoint size) {
		initEngine(size.x, size.y);
	}

	public void initCanvas() {
		if (isAndroid()) {
			setCanvasSettings(16, 12, 16, 16, null, null, null);
			NRSPR = 64;
			SPRX = 22;
			SPRY = 22;
			NRSPRX = 8;
			NRSPRY = 8;
		} else {
			setCanvasSettings(32, 24, 16, 16, null, null, null);
		}
	}

	public void initGame() {
		// defineMedia("media.tbl");
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

	boolean imgdef = false;
	static int NRSPR = 256;
	static int SPRX = 22;
	static int SPRY = 22;
	static int NRSPRX = 16;
	static int NRSPRY = 16;
	Sprite[] sprites = new Sprite[NRSPR];

	String formatString(int len, int number) {
		String ret = "" + number;
		while (ret.length() < len)
			ret = "0" + ret;
		return ret;
	}

	int selx = 0, sely = 0, seltimer = 0;

	public void paintFrame() {
		setFont(new JGFont("Sans", 0, isAndroid() ? 7 : 10));
		drawString("Random Sprite Generator", pfWidth(), 30, 1);
		drawString("(version 1.0, 2nd April 2013)", pfWidth(), 45, 1);
		drawString("by Boris van Schooten", pfWidth(), 60, 1);
		drawString("Click on a sprite to save.", pfWidth()/*-80*/, 100, 1);
		drawString("Click outside sprite region", pfWidth()/*-80*/, 130, 1);
		drawString("to generate new sprites.", pfWidth()/*-80*/, 145, 1);
		if (seltimer > 0) {
			setColor(new JGColor(seltimer / 15.0, seltimer / 15.0,
					seltimer / 15.0));
			drawRect(5 + SPRX * selx, 5 + SPRY * sely, SPRX, SPRY, false, false);
			seltimer--;
		}
		int x = 0;
		int y = 0;
		moveObjects(null, 0);
		boolean regen = false;
		Sprite selspr = null;
		int sprnr = 0;
		if (imgdef && getMouseButton(1)) {
			clearMouseButton(1);
			if (getMouseX() >= 10 && getMouseX() < 10 + NRSPRX * SPRX
					&& getMouseY() >= 10 && getMouseY() < 10 + NRSPRY * SPRY) {
				sprnr = ((getMouseX() - 10) / SPRX) + NRSPRX
						* ((getMouseY() - 10) / SPRY);
				if (sprnr >= 0 && sprnr < NRSPR) {
					selspr = sprites[sprnr];
					sely = sprnr / NRSPRX;
					selx = sprnr % NRSPRX;
					seltimer = 15;
					long timestamp = System.currentTimeMillis();
					int msec = (int) (timestamp % 1000L);
					int sec = (int) ((timestamp / 1000L) % 60L);
					int min = (int) ((timestamp / (60L * 1000L)) % 60L);
					long rest = timestamp / (60L * 60L * 1000L);
					String mypath = "";
					if (isAndroid()) {
						// File sdDir = android.os.Environment
						// .getExternalStorageDirectory();
						// File mypathf = new File(sdDir, "spritegen");
						// if (!mypathf.exists()) {
						// mypathf.mkdir();
						// }
						// mypath = mypathf.getPath();
					} else {
						// a big mess just to get the Jar's path
						try {
							String path = Main.class.getProtectionDomain()
									.getCodeSource().getLocation().getPath();
							File myjar = new File(URLDecoder.decode(path,
									"UTF-8"));
							mypath = myjar.getParentFile().getPath();
							// mypath =
							// Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					String filename = "spr" + rest + "-" + formatString(2, min)
							+ "-" + formatString(2, sec) + "."
							+ formatString(3, msec);
					ImageUtils.writePNG(mypath + File.separator + filename
							+ ".png", selspr.pixels, selspr.pixels.length,
							selspr.pixels[0].length, SpriteGenerator.transcolor);
				} else {
					regen = true;
				}
			} else {
				regen = true;
			}
		}
		if (!imgdef || regen) {
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
			// PixelArtGen.coltables = coltables;
			int shapetype = (int) (random.nextDouble() * SpriteGenerator.shapes2.length);
			int[] coltable = coltables[(int) (random.nextDouble() * coltables.length)];
			SpriteGenerator shape = SpriteGenerator.shapes2[shapetype];
			if (selspr != null) {
				shape = selspr.gen;
			} else {
				shape.shading = random.nextDouble() < 0.75 ? 0
						: random.nextDouble() < 0.5 ? 1 : 2;
				shape.highlight_prob = shape.shading == 0 ? 0.4 : 0;
			}
			Sprite sprt = shape.createSprite(coltable);
			for (int i = 0; i < NRSPR; i++) {
				/*
				 * int shapetype =
				 * (int)(Math.random()*PixelArtGen.shapes2.length); int []
				 * coltable = coltables[(int)(Math.random()*coltables.length)];
				 * PixelArtGen shape = PixelArtGen.shapes2[shapetype]; if
				 * (selspr!=null) { shape = selspr.gen; } else { shape.shading =
				 * random(0,1) < 0.75 ? 0 : random(0,1) < 0.5 ? 1 : 2;
				 * shape.highlight_prob = shape.shading==0 ? 0.4:0; }
				 */
				// sprites[i] = shape.createSprite(coltable);
				sprites[i] = shape.mergeSprites(sprt,
						shape.createSprite(coltable), 0.9);
				if (selspr != null) {
					if (sprnr == i) {
						sprites[i] = selspr;
					} else {
						sprites[i] = shape
								.mergeSprites(selspr, sprites[i], 0.7);
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
				defineAnimation("rand" + i, frames, random.nextDouble()*0.3+0.2);
				new JGObject("rand", true, 10 + x * SPRX, 10 + y * SPRY, 0,
						"rand" + i);
				x++;
				if (x >= NRSPRX) {
					x = 0;
					y++;
				}
			}
			imgdef = true;
		}
	}
}
