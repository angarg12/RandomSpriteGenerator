package examples.tools.spritegen;

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

	int size_x = 16;
	int size_y = 16;
	
	int[][] fill_table;
	int[][][] animation_table;
	
	boolean flip_x = true;
	boolean flip_y = false;
	
	Shading shading = Shading.NONE;
	// shades the colour when flipping
	// note: xshadingfac+yshadingfac must be <= 2
	// 0=no shading 
	// 1=darken 
	// 2=darken more
	int shade_at_flip_x = 0;
	int shade_at_flip_y = 0;

	// probability of filling pixel
	double fill_probability = 0.6;
	// probability that a pixel is filled the same as its neighbours
	double fill_smoothing = 0.2;
	// balance between taking horizontal versus vertical neighbours
	double fill_smoothing_x_bias = 0.8;
	// probability of black pixel if enabled
	// TODO: actually fills it black or transparent??
	double black_probability = 0.2;
	// probability of highlight pixel if enabled
	double highlight_probability = 0.4;
	// probability that a colour (non-black) pixel is taken from neighbour
	double color_smoothing = 0.7;
	// balance between taking horizontal versus vertical neighbours
	double color_smoothing_x_bias = 0.5;

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
		this.size_x = xsize;
		this.size_y = ysize;
		this.fill_table = filltable;
		this.animation_table = animtable;
		this.flip_x = flipx;
		this.flip_y = flipy;
		this.shade_at_flip_x = xshadingfac;
		this.shade_at_flip_y = yshadingfac;
		this.fill_probability = fill_prob;
		this.fill_smoothing = fill_smoothing;
		this.fill_smoothing_x_bias = fill_smoothing_horiz_bias;
		this.black_probability = black_prob;
		this.highlight_probability = highlight_prob;
		this.color_smoothing = color_smoothing;
		this.color_smoothing_x_bias = color_smoothing_horiz_bias;
		if (this.shading == Shading.BEVEL) {
			this.shade_at_flip_x = 0;
			this.shade_at_flip_y = 0;
		}
		if (this.shading == Shading.GOURAUD) {
			highlight_prob = 0;
		}
	}

	/**
	 * Adds a black outline sorrounding the sprite.
	 * @param spr
	 */
	public void addOutlineRGB(Sprite spr) {
		for (int x = 0; x < spr.pixels.length; x++) {
			for (int y = 0; y < spr.pixels[x].length; y++) {
				boolean neigh = false;
				neigh = neigh || x > 0 && spr.pixels[x - 1][y] != ColorScheme.TRANSPARENT
						&& spr.pixels[x - 1][y] != 0;
				neigh = neigh || x < spr.pixels.length - 1
						&& spr.pixels[x + 1][y] != ColorScheme.TRANSPARENT
						&& spr.pixels[x + 1][y] != 0;
				neigh = neigh || y > 0 && spr.pixels[x][y - 1] != ColorScheme.TRANSPARENT
						&& spr.pixels[x][y - 1] != 0;
				neigh = neigh || y < spr.pixels[x].length - 1
						&& spr.pixels[x][y + 1] != ColorScheme.TRANSPARENT
						&& spr.pixels[x][y + 1] != 0;
				if (neigh && spr.pixels[x][y] == ColorScheme.TRANSPARENT){
					spr.pixels[x][y] = 0;
				}
				if (!neigh && spr.pixels[x][y] == 0){
					spr.pixels[x][y] = ColorScheme.TRANSPARENT;
				}
			}
		}
	}

	/** 
	 * Merges two sprites to create a new one. Is the base for mutation. Should be rewritten to
	 * give a more clear abstraction of mutation.
	 */
	public Sprite mergeSprites(
			Sprite spr1, 
			Sprite spr2, 
			double weight) {
		Sprite spr = new Sprite(
				spr1.coltable, 
				spr1.gen, 
				size_x,
				spr1.pixels.length, 
				size_y);
		int xmax = flip_x ? size_x / 2 : size_x;
		int ymax = flip_y ? size_y / 2 : size_y;
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
		addOutlineRGB(spr);
		return spr;
	}

	/**
	 * Creates a new sprite.
	 * 
	 * @param coltable
	 * @return
	 */
	public Sprite createSprite(int[] coltable) {
		int xmax = flip_x ? size_x / 2 : size_x;
		int ymax = flip_y ? size_y / 2 : size_y;
		int totalxsize = animation_table == null ? size_x : size_x
				* (animation_table.length + 1);
		Sprite spr = new Sprite(
				coltable, 
				this, 
				size_x, 
				totalxsize, 
				size_y);
		// decide which parts of hull to fill:
		// * main fill type 1 -> 2
		// * add outline
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				int filltype = fill_table[y][x];
				int filltype_main = (filltype & 3);
				int filltype_fill = ((filltype & 12) | 2);
				if (filltype_main == 1) {
					// smooth = get colour from neighbouring pixel
					if (random.nextDouble() < fill_smoothing) {
						int above = 0, left = 0, chosen = 0;
						if (x > 0){
							left = (spr.hull[x - 1][y] & 3) == 2 ? 1 : 0;
						}
						if (y > 0){
							above = (spr.hull[x][y - 1] & 3) == 2 ? 1 : 0;
						}
						if (above == 0 && left == 0) {
							chosen = 0;
						} else if (above != 0 && left == 0) {
							chosen = above;
						} else if (above == 0 && left != 0) {
							chosen = left;
						} else if (above != 0 && left != 0) {
							if (random.nextDouble() > fill_smoothing_x_bias) {
								chosen = above;
							} else {
								chosen = left;
							}
						}
						if (chosen != 0){
							spr.hull[x][y] = filltype_fill;
						}
					} else {
						if (random.nextDouble() > fill_probability){
							spr.hull[x][y] = filltype_fill;
						}
					}
				} else if (filltype_main == 2) {
					spr.hull[x][y] = filltype_fill;
				} else if (filltype_main == 3) {
					spr.hull[x][y] = 3;
				}
			}
		}
		// colour fill type is handled by colorize
		colorize(spr);
		flip(spr);
		if (shading == Shading.BEVEL){
			bevelShade(spr);
		}
		if (shading == Shading.GOURAUD){
			gouraudShade(spr);
		}
		indexToRGB(spr);
		animate(spr);
		addOutlineRGB(spr);
		return spr;
	}

	/**
	 * Colorizes a sprite.
	 * @param spr
	 */
	public void colorize(Sprite spr) {
		int xmax = flip_x ? size_x / 2 : size_x;
		int ymax = flip_y ? size_y / 2 : size_y;
		int white = spr.coltable.length / 3 - 1;
		for (int y = 0; y < ymax; y++) {
			for (int x = 0; x < xmax; x++) {
				int colnr = 0;
				int filltype = spr.hull[x][y];
				if ((filltype & 3) == 3) { // outline
					colnr = 1;
				} else if ((filltype & 3) == 2) { // normal fill
					if ((filltype & 4) == 4) { // black enabled
						if (random.nextDouble() < black_probability) {
							colnr = 1; // black
						} else {
							if ((filltype & 8) == 8) { // highlight enabled
								if (random.nextDouble() < highlight_probability) {
									colnr = white;
								} else {
									// any colour except black and highlight
									colnr = (int) (2 + random.nextDouble()
											* (spr.coltable.length / 3 - 3));
								}
							}
						}
					} else if ((filltype & 8) == 8) { // highlight enabled
						if (random.nextDouble() < highlight_probability) {
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
						if (x > 0){
							left = spr.colidx[x - 1][y] / 3;
						}
						if (y > 0){
							above = spr.colidx[x][y - 1] / 3;
						}
						if (above == 0 && left == 0) {
							chosen = 0;
						} else if (above != 0 && left == 0) {
							chosen = above;
						} else if (above == 0 && left != 0) {
							chosen = left;
						} else if (above != 0 && left != 0) {
							if (random.nextDouble() > color_smoothing_x_bias) {
								chosen = above;
							} else {
								chosen = left;
							}
						}
						if (chosen > 1){
							colnr = chosen;
						}
					}
				}
				spr.colidx[x][y] = colnr * 3;
			}
		}
	}

	/** 
	 * flip according to symmetry axes. Apply shade if needed. 
	 */
	public void flip(Sprite spr) {
		for (int y = 0; y < size_y; y++) {
			for (int x = 0; x < size_x; x++) {
				int colnr = spr.colidx[x][y];
				if (flip_x && x < size_x / 2) {
					spr.colidx[size_x - x - 1][y] = colnr + shade_at_flip_x;
				}
				if (flip_y && y < size_y / 2) {
					spr.colidx[x][size_y - y - 1] = colnr + shade_at_flip_y;
				}
				if (flip_x && flip_y && x < size_x / 2 && y < size_y / 2) {
					spr.colidx[size_x - x - 1][size_y - y - 1] = colnr
							+ shade_at_flip_x + shade_at_flip_y;
				}
			}
		}
	}

	/**
	 * Transforms the colour indexes to the colors themselves 
	 * @param spr
	 */
	public void indexToRGB(Sprite spr) {
		for (int x = 0; x < size_x; x++) {
			for (int y = 0; y < size_y; y++) {
				spr.pixels[x][y] = spr.coltable[spr.colidx[x][y]];
			}
		}
	}

	/**
	 * Performs a bevel shading
	 * @param spr
	 */
	public void bevelShade(Sprite spr) {
		// shade given colours
		for (int y = 0; y < size_y; y++) {
			for (int x = 0; x < size_x; x++) {
				int idx = spr.colidx[x][y];
				if (idx >= 6) {
					int top_left_distance = findOutlineDistance(spr, x, y, -1, -1, 2);
					int bottom_right_distance = findOutlineDistance(spr, x, y, 1, 1, 2);
					// System.err.println(" "+tldist+" "+brdist);
					// 0=darkest ... 4=brightest. Odd numbers will dither.
					int bright = 2;
					if (top_left_distance == 1)
						bright = 4;
					if (bottom_right_distance == 1)
						bright = 0;
					// special cases: thin areas
					if (top_left_distance == 1 && bottom_right_distance == 1)
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

	/**
	 * Finds the closest distance to an outline (a black or transparent pixel)
	 * up to depth distance. dx and dy determine the direction to explore.
	 * 
	 * @param spr
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param depth
	 * @return
	 */
	public int findOutlineDistance(Sprite spr, 
			int x, 
			int y, 
			int dx, 
			int dy,
			int depth) {
		if (x < 0 || x >= size_x || y < 0 || y >= size_y){
			return 0;
		}
		if (depth <= 0){
			return 0;
		}
		if (spr.colidx[x][y] <= 5){
			return 0;
		}
		int xdist = findOutlineDistance(spr, x + dx, y, dx, dy, depth - 1);
		int ydist = findOutlineDistance(spr, x, y + dy, dx, dy, depth - 1);
		return xdist < ydist ? xdist + 1 : ydist + 1;
	}

	/**
	 * Performs a Gouraud shading
	 * @param spr
	 */
	public void gouraudShade(Sprite spr) {
		int cenx = size_x / 4 + (int) (random.nextDouble() * 2.999);
		int ceny = size_y / 4 + (int) (random.nextDouble() * 2.999);
		int maxdist = size_x - cenx - 1;
		int hlt_rx = (int) (random.nextDouble() * 2.9999);
		int hlt_ry = (int) (random.nextDouble() * 2.9999);
		int inner_r = 7 + (int) (random.nextDouble() * 16);
		int outer_r = 7 + (int) (random.nextDouble() * 16);
		for (int y = 0; y < size_y; y++) {
			int dy = Math.abs(y - ceny);
			for (int x = 0; x < size_x; x++) {
				int dx = Math.abs(x - cenx);
				int dd = dx * dx + dy * dy;
				int idx = spr.colidx[x][y];
				if (idx >= 6) {
					// 0=darkest .. 4=brightest. Odd numbers will dither.
					int bright = 2;
					if (dx <= hlt_rx && dy <= hlt_ry){
						bright = 4;
					}else if (dd <= inner_r){
						bright = 3;
					}else if (dd >= maxdist * maxdist - outer_r){
						bright = 0;
					}else if (dd >= maxdist * maxdist - outer_r - 13){
						bright = 1;
					}
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

	/**
	 * Animates a sprite
	 * @param spr
	 */
	public void animate(Sprite spr) {
		// now, animate if applicable
		if (animation_table == null)
			return;
		// d = distance travelled. Pixels that travel the largest distance
		// should overwrite other pixels
		for (int d = 0; d <= 2; d++) {
			for (int y = 0; y < size_y; y++) {
				for (int x = 0; x < size_x; x++) {
					int col = spr.pixels[x][y];
					if (col == ColorScheme.TRANSPARENT){
						continue;
					}
					for (int a = 0; a < animation_table.length; a++) {
						int anim = animation_table[a][y][x];
						if ((anim + 7) / 8 != d){
							continue;
						}
						int dx = 0, dy = 0, mul = 1;
						if (anim > 8) {
							mul = 2;
							anim -= 8;
						}
						if (anim == 8 || anim == 1 || anim == 2){
							dy = -1;
						}
						if (anim == 2 || anim == 3 || anim == 4){
							dx = 1;
						}
						if (anim == 4 || anim == 5 || anim == 6){
							dy = 1;
						}
						if (anim == 6 || anim == 7 || anim == 8){
							dx = -1;
						}
						dx *= mul;
						dy *= mul;
						if (x + dx >= 0 && x + dx < size_x && y + dy >= 0
								&& y + dy < size_y) {
							spr.pixels[(a + 1) * size_x + x + dx][y + dy] = col;
						}
					}
				}
			}
		}
	}
}
