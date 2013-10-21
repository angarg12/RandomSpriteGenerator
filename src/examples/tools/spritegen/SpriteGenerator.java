package examples.tools.spritegen;

import java.io.*;
import java.util.Random;

import examples.tools.spritegen.color.ColorScheme;

public class SpriteGenerator {
	/**
	 * TODO: Encapsulate filling tables, animation tables and generation parameters in some kind of pattern (factory, strategy?)
	 */
	static Random random = new Random();
	public static void fixRandom(int seed){
		random = new Random(seed);
	}
	
	// hull code:
	// 0 = transparent
	// 1 = any colour
	// 2 = any colour
	// 4 = always black

	// render parameters as part of type (instance variables)

	int xsize = 16;
	int ysize = 16;

	int[][] filltable;

	int[][][] animtable;

	boolean flipx = true, flipy = false;

	public final static int NONE = 0;
	public final static int BEVEL = 1;
	public final static int GOURAUD = 2;
	int shading = NONE;

	// note: xshadingfac+yshadingfac must be <= 2
	// 0=no shading 1=darken 2=darken more
	int xshadingfac = 0;
	// 0=no shading 1=darken 2=darken more
	int yshadingfac = 0;

	// probability of filling pixel
	double fill_prob = 0.6;
	// probability that a pixel is filled the same as its neighbours
	double fill_smoothing = 0.2;
	// balance between taking horizontal versus vertical neighbours
	double fill_smoothing_horiz_bias = 0.8;

	// probability of black pixel if enabled
	double black_prob = 0.2;
	// probability of highlight pixel if enabled
	double highlight_prob = 0.4;
	// probability that a colour (non-black) pixel is taken from neighbour
	double color_smoothing = 0.7;
	// balance between taking horizontal versus vertical neighbours
	double color_smoothing_horiz_bias = 0.5;

	public static SpriteGenerator[] shapes = new SpriteGenerator[] {
			new SpriteGenerator(16, 16, FillingTable.SHIP, null, true, false, 1, 1,
					0.5, 0.6, 0.5, 0.3, 0.4, 0.6, 0.5),
			new SpriteGenerator(18, 18, FillingTable.BUTTERFLY_18, AnimationTable.BIRD_18,
					true, false, 1, 1, 0.5, 0.7, 0.5, 0.3, 0.4, 0.6, 0.5),
			new SpriteGenerator(18, 18, FillingTable.MAN_18, AnimationTable.MAN_18, true,
					false, 1, 1, 0.5, 0.6, 0.5, 0.3, 0.4, 0.6, 0.5),
			new SpriteGenerator(18, 18, FillingTable.UFO_18, null, true, true, 1, 1,
					0.5, 0.75, 0.5, 0.3, 0.4, 0.8, 0.5) };

	public static SpriteGenerator[] shapes2 = new SpriteGenerator[] {
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.WALK_12, true,
					false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.FLY_12, true,
					false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.CRAWL2_12,
					true, false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.CRAWL_12,
					true, false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BEND_12, true,
					false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BUBBLE_12,
					true, false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.POKE_12, true,
					false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.WALK2_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.CRAWL_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BEND_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BUBBLE_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.POKE_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.TURN_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BEND_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.CRAWL_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BUBBLE_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.POKE_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.WIGGLE_12,
					true, false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.WIGGLE2_12,
					false, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BOUNCE_12,
					true, false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.BOUNCE_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.NULL_12, true,
					false, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5),
			new SpriteGenerator(12, 12, FillingTable.RAND_12, AnimationTable.NULL_12,
					true, true, 0, 0, 0.6, 0.2, 0.5, 0.3, 0.4, 0.3, 0.5), };

	public SpriteGenerator(int xsize, int ysize, int[][] filltable,
			int[][][] animtable, boolean flipx, boolean flipy, int xshadingfac,
			int yshadingfac, double fill_prob, double fill_smoothing,
			double fill_smoothing_horiz_bias, double black_prob,
			double highlight_prob, double color_smoothing,
			double color_smoothing_horiz_bias) {
		this.xsize = xsize;
		this.ysize = ysize;
		this.filltable = filltable;
		this.animtable = animtable;
		this.flipx = flipx;
		this.flipy = flipy;
		this.xshadingfac = xshadingfac;
		this.yshadingfac = yshadingfac;
		this.fill_prob = fill_prob;
		this.fill_smoothing = fill_smoothing;
		this.fill_smoothing_horiz_bias = fill_smoothing_horiz_bias;
		this.black_prob = black_prob;
		this.highlight_prob = highlight_prob;
		this.color_smoothing = color_smoothing;
		this.color_smoothing_horiz_bias = color_smoothing_horiz_bias;
		if (this.shading == BEVEL) {
			this.xshadingfac = 0;
			this.yshadingfac = 0;
			// highlight_prob = 0;
		}
		if (this.shading == GOURAUD) {
			highlight_prob = 0;
		}
	}

	public static void addOutlineRGB(int[][] pixels) {
		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[x].length; y++) {
				boolean neigh = false;
				neigh = neigh || x > 0 && pixels[x - 1][y] != ColorScheme.TRANSPARENT
						&& pixels[x - 1][y] != 0;
				neigh = neigh || x < pixels.length - 1
						&& pixels[x + 1][y] != ColorScheme.TRANSPARENT
						&& pixels[x + 1][y] != 0;
				neigh = neigh || y > 0 && pixels[x][y - 1] != ColorScheme.TRANSPARENT
						&& pixels[x][y - 1] != 0;
				neigh = neigh || y < pixels[x].length - 1
						&& pixels[x][y + 1] != ColorScheme.TRANSPARENT
						&& pixels[x][y + 1] != 0;
				if (neigh && pixels[x][y] == ColorScheme.TRANSPARENT)
					pixels[x][y] = 0;
				if (!neigh && pixels[x][y] == 0)
					pixels[x][y] = ColorScheme.TRANSPARENT;
			}
		}
	}

	/** Weight is probability of using sprite 1. */
	public Sprite mergeSprites(Sprite spr1, Sprite spr2, double weight) {
		Sprite spr = new Sprite(spr1.coltable, spr1.gen, xsize,
				spr1.pixels.length, ysize);
		int xmax = flipx ? xsize / 2 : xsize;
		int ymax = flipy ? ysize / 2 : ysize;
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				if (random.nextDouble() > weight) {
					spr.colidx[x][y] = spr2.colidx[x][y];
				} else {
					spr.colidx[x][y] = spr1.colidx[x][y];
				}
			}
		}
		flip(spr);
		indexToRGB(spr);
		animate(spr);
		addOutlineRGB(spr.pixels);
		return spr;
	}

	public Sprite createSprite(int[] coltable) {
		int xmax = flipx ? xsize / 2 : xsize;
		int ymax = flipy ? ysize / 2 : ysize;
		int totalxsize = animtable == null ? xsize : xsize
				* (animtable.length + 1);
		Sprite spr = new Sprite(coltable, this, xsize, totalxsize, ysize);
		// decide which parts of hull to fill:
		// * main fill type 1 -> 2
		// * add outline
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				int filltype = filltable[y][x];
				int filltype_main = (filltype & 3);
				int filltype_fill = ((filltype & 12) | 2);
				if (filltype_main == 1) {
					// smooth = get colour from neighbouring pixel
					if (random.nextDouble() < fill_smoothing) {
						int above = 0, left = 0, chosen = 0;
						if (x > 0)
							left = (spr.hull[x - 1][y] & 3) == 2 ? 1 : 0;
						if (y > 0)
							above = (spr.hull[x][y - 1] & 3) == 2 ? 1 : 0;
						if (above == 0 && left == 0) {
							chosen = 0;
						} else if (above != 0 && left == 0) {
							chosen = above;
						} else if (above == 0 && left != 0) {
							chosen = left;
						} else if (above != 0 && left != 0) {
							if (random.nextDouble() > fill_smoothing_horiz_bias) {
								chosen = above;
							} else {
								chosen = left;
							}
						}
						if (chosen != 0)
							spr.hull[x][y] = filltype_fill;
					} else {
						if (random.nextDouble() > fill_prob)
							spr.hull[x][y] = filltype_fill;
					}
				} else if (filltype_main == 2) {
					spr.hull[x][y] = filltype_fill;
				} else if (filltype_main == 3) {
					spr.hull[x][y] = 3;
				}
			}
		}
		// addOutline(spr.hull);
		// colour fill type is handled by colorize
		colorize(spr);
		flip(spr);
		if (shading == BEVEL)
			bevelShade(spr);
		if (shading == GOURAUD)
			gouraudShade(spr);
		indexToRGB(spr);
		animate(spr);
		addOutlineRGB(spr.pixels);
		// colorizeShade(pixels,spr.hull);
		return spr;
	}

	public void colorize(Sprite spr) {
		int xmax = flipx ? xsize / 2 : xsize;
		int ymax = flipy ? ysize / 2 : ysize;
		int white = spr.coltable.length / 3 - 1;
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				int colnr = 0;
				int filltype = spr.hull[x][y];
				if ((filltype & 3) == 3) { // outline
					colnr = 1;
				} else if ((filltype & 3) == 2) { // normal fill
					if ((filltype & 4) == 4) { // black enabled
						if (random.nextDouble() < black_prob) {
							colnr = 1; // black
						} else {
							if ((filltype & 8) == 8) { // highlight enabled
								if (random.nextDouble() < highlight_prob) {
									colnr = white;
								} else {
									// any colour except black and highlight
									colnr = (int) (2 + random.nextDouble()
											* (spr.coltable.length / 3 - 3));
								}
							}
						}
					} else if ((filltype & 8) == 8) { // highlight enabled
						if (random.nextDouble() < highlight_prob) {
							colnr = white;
						} else {
							// any colour except black and highlight
							colnr = (int) (2 + random.nextDouble()
									* (spr.coltable.length / 3 - 3));
						}
					} else { // any colour except black and highlight
						// NOTE: previously highlight was also enabled but with
						// normal probability
						colnr = (int) (2 + random.nextDouble()
								* (spr.coltable.length / 3 - 3));
					}
					// XXX both black and highlight not supported
					// smooth = get colour from neighbouring pixel
					if (colnr > 1 && random.nextDouble() < color_smoothing) {
						int above = 0, left = 0, chosen = 0;
						if (x > 0)
							left = spr.colidx[x - 1][y] / 3;
						if (y > 0)
							above = spr.colidx[x][y - 1] / 3;
						if (above == 0 && left == 0) {
							chosen = 0;
						} else if (above != 0 && left == 0) {
							chosen = above;
						} else if (above == 0 && left != 0) {
							chosen = left;
						} else if (above != 0 && left != 0) {
							if (random.nextDouble() > color_smoothing_horiz_bias) {
								chosen = above;
							} else {
								chosen = left;
							}
						}
						if (chosen > 1)
							colnr = chosen;
					}
				}
				spr.colidx[x][y] = colnr * 3;
			}
		}
	}

	/** flip according to symmetry axes and shade */
	public void flip(Sprite spr) {
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				int colnr = spr.colidx[x][y];
				if (flipx && x < xsize / 2) {
					spr.colidx[xsize - x - 1][y] = colnr + xshadingfac;
				}
				if (flipy && y < ysize / 2) {
					spr.colidx[x][ysize - y - 1] = colnr + yshadingfac;
				}
				if (flipx && flipy && x < xsize / 2 && y < ysize / 2) {
					spr.colidx[xsize - x - 1][ysize - y - 1] = colnr
							+ xshadingfac + yshadingfac;
				}
			}
		}
	}

	public void indexToRGB(Sprite spr) {
		for (int x = 0; x < xsize; x++) {
			for (int y = 0; y < ysize; y++) {
				spr.pixels[x][y] = spr.coltable[spr.colidx[x][y]];
			}
		}
	}

	public void bevelShade(Sprite spr) {
		// shade given colours
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				int idx = spr.colidx[x][y];
				// if (idx >= 15) idx -= 3; // remove highlights
				if (idx >= 6) {
					int tldist = findOutlineDist(spr, x, y, -1, -1, 2);
					int brdist = findOutlineDist(spr, x, y, 1, 1, 2);
					// System.err.println(" "+tldist+" "+brdist);
					// 0=darkest ... 4=brightest. Odd numbers will dither.
					int bright = 2;
					// if (tldist == 2) bright = 4;
					if (tldist == 1)
						bright = 4;
					// if (brdist == 2) bright = 1;
					if (brdist == 1)
						bright = 0;
					// special cases: thin areas
					if (tldist == 1 && brdist == 1)
						bright = 2;
					boolean dither = (bright & 1) == 1 && ((x + y) & 1) == 1;
					// 0, 1, or 2
					bright = bright / 2 + (dither ? 1 : 0);
					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						spr.colidx[x][y] = 3 * (idx / 3) + 2 - 2 * bright;
					}
				}
			}
		}
	}

	public int findOutlineDist(Sprite spr, int x, int y, int dx, int dy,
			int depth) {
		if (x < 0 || x >= xsize || y < 0 || y >= ysize)
			return 0;
		if (depth <= 0)
			return 0;
		if (spr.colidx[x][y] <= 5)
			return 0;
		int xdist = findOutlineDist(spr, x + dx, y, dx, dy, depth - 1);
		int ydist = findOutlineDist(spr, x, y + dy, dx, dy, depth - 1);
		return xdist < ydist ? xdist + 1 : ydist + 1;
	}

	public void gouraudShade(Sprite spr) {
		int cenx = xsize / 4 + (int) (random.nextDouble() * 2.999);
		int ceny = ysize / 4 + (int) (random.nextDouble() * 2.999);
		int maxdist = xsize - cenx - 1;
		int hlt_rx = (int) (random.nextDouble() * 2.9999);
		int hlt_ry = (int) (random.nextDouble() * 2.9999);
		int inner_r = 7 + (int) (random.nextDouble() * 16);
		int outer_r = 7 + (int) (random.nextDouble() * 16);
		for (int y = 0; y < ysize; y++) {
			int dy = Math.abs(y - ceny);
			for (int x = 0; x < xsize; x++) {
				int dx = Math.abs(x - cenx);
				int dd = dx * dx + dy * dy;
				int idx = spr.colidx[x][y];
				// if (idx==15) idx-=3;
				if (idx >= 6) {
					// 0=darkest .. 4=brightest. Odd numbers will dither.
					int bright = 2;
					if (dx <= hlt_rx && dy <= hlt_ry)
						bright = 4;
					else if (dd <= inner_r)
						bright = 3;
					else if (dd >= maxdist * maxdist - outer_r)
						bright = 0;
					else if (dd >= maxdist * maxdist - outer_r - 13)
						bright = 1;
					boolean dither = (bright & 1) == 1 && ((x + y) & 1) == 1;
					// 0, 1, or 2
					bright = bright / 2 + (dither ? 1 : 0);
					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						spr.colidx[x][y] = 3 * (idx / 3) + 2 - 2 * bright;
					}
					// spr.colidx[x][y] = 3*(idx/3) + 2 - bright;
				}
			}
		}
	}

	public void animate(Sprite spr) {
		// now, animate if applicable
		if (animtable == null)
			return;
		// d = distance travelled. Pixels that travel the largest distance
		// should overwrite other pixels
		for (int d = 0; d <= 2; d++) {
			for (int y = 0; y < ysize; y++) {
				for (int x = 0; x < xsize; x++) {
					int col = spr.pixels[x][y];
					if (col == ColorScheme.TRANSPARENT)
						continue;
					for (int a = 0; a < animtable.length; a++) {
						int anim = animtable[a][y][x];
						if ((anim + 7) / 8 != d)
							continue;
						int dx = 0, dy = 0, mul = 1;
						if (anim > 8) {
							mul = 2;
							anim -= 8;
						}
						if (anim == 8 || anim == 1 || anim == 2)
							dy = -1;
						if (anim == 2 || anim == 3 || anim == 4)
							dx = 1;
						if (anim == 4 || anim == 5 || anim == 6)
							dy = 1;
						if (anim == 6 || anim == 7 || anim == 8)
							dx = -1;
						dx *= mul;
						dy *= mul;
						// spr.pixels[(a+1)*xsize + x][y] = ColorScheme.TRANSPARENT;
						if (x + dx >= 0 && x + dx < xsize && y + dy >= 0
								&& y + dy < ysize) {
							spr.pixels[(a + 1) * xsize + x + dx][y + dy] = col;
						}
					}
				}
			}
		}
	}
}
