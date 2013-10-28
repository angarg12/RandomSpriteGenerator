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

	public SpriteGenerator(int xsize, 
			int ysize, 
			int[][] filltable,
			int[][][] animtable, 
			boolean flipx, 
			boolean flipy, 
			int xshadingfac,
			int yshadingfac, 
			double fill_prob, 
			double fill_smoothing,
			double fill_smoothing_horiz_bias, 
			double black_prob,
			double highlight_prob, 
			double color_smoothing,
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
	 * Adds a black outline surrounding the sprite.
	 * @param spr
	 */
	public void addOutline(Sprite spr) {
		for (int x = 0; x < spr.pixels.length; x++) {
			for (int y = 0; y < spr.pixels[x].length; y++) {
				boolean isNeighbourColored = isLeftPixelColored(x,y,spr) || 
						isRightPixelColored(x,y,spr) || 
						isDownPixelColored(x,y,spr) || 
						isUpPixelColored(x,y,spr);
				if (isNeighbourColored && 
						spr.pixels[x][y] == ColorScheme.TRANSPARENT){
					spr.pixels[x][y] = 0;
				}
				if (isNeighbourColored == false && 
						spr.pixels[x][y] == 0){
					spr.pixels[x][y] = ColorScheme.TRANSPARENT;
				}
			}
		}
	}

	/**
	 * Returns whether the left pixel is not transparent or black.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 * @return
	 */
	private boolean isLeftPixelColored(int x, int y, Sprite spr){
		return x > 0 && 
				spr.pixels[x - 1][y] != ColorScheme.TRANSPARENT && 
				spr.pixels[x - 1][y] != 0;
	}

	/**
	 * Returns whether the right pixel is not transparent or black.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 * @return
	 */
	private boolean isRightPixelColored(int x, int y, Sprite spr){
		return x < spr.pixels.length - 1 && 
				spr.pixels[x + 1][y] != ColorScheme.TRANSPARENT && 
				spr.pixels[x + 1][y] != 0;
	}

	/**
	 * Returns whether the right pixel is not transparent or black.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 * @return
	 */
	private boolean isDownPixelColored(int x, int y, Sprite spr){
		return y > 0 && 
				spr.pixels[x][y - 1] != ColorScheme.TRANSPARENT	&& 
				spr.pixels[x][y - 1] != 0;
	}

	/**
	 * Returns whether the right pixel is not transparent or black.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 * @return
	 */
	private boolean isUpPixelColored(int x, int y, Sprite spr){
		return y < spr.pixels[x].length - 1	&& 
				spr.pixels[x][y + 1] != ColorScheme.TRANSPARENT	&& 
				spr.pixels[x][y + 1] != 0;
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
		addOutline(spr);
		return spr;
	}

	/**
	 * Creates a new sprite.
	 * 
	 * @param color_table
	 * @return
	 */
	public Sprite createSprite(int[] color_table) {
		// the limit of the loop is the size of the sprite
		int x_max = size_x;
		// if the sprite is flipped, only fill half of it (the other half will be the symmetric)
		if(flip_x){
			x_max /= 2;
		}
		// the limit of the loop is the size of the sprite
		int y_max = size_y;
		// if the sprite is flipped, only fill half of it (the other half will be the symmetric)
		if(flip_y){
			y_max /= 2;
		}
		int total_x_size = size_x;
		// the total x size is the x size by the number of frames (plus 1 for the still)
		if(animation_table != null){
			total_x_size = size_x*(animation_table.length+1);
		}

		Sprite spr = new Sprite(
				color_table, 
				this, 
				size_x, 
				total_x_size, 
				size_y);

		for (int y = 0; y < y_max; y++) {
			for (int x = 0; x < x_max; x++) {
				fillPixel(x,y,spr);
			}
		}
		colorize(spr);
		flip(spr);
		switch(shading){
			case BEVEL: bevelShade(spr); break;
			case GOURAUD: gouraudShade(spr); break;
			default: break;
		}
		indexToRGB(spr);
		animate(spr);
		addOutline(spr);
		return spr;
	}
	
	/** 
	 * Decide which parts of hull to fill:
	 * this decision is made based on the smooth coefficient
	 * and the fill probability.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 */
	private void fillPixel(int x, int y, Sprite spr){
		int filltype = fill_table[y][x];
		// extract code from two lower bits
		int filltype_main = (filltype & 3);
		// extract code from two upper bits
		int filltype_fill = ((filltype & 12) | 2);
		// fill or empty
		// TODO: Notice that the logic is a little bit warped. If a neighbour is filled, the pixel
		// gets filled, but with its own value, not the one of the neighbour. This means that fill_smoothing
		// actually does nothing, and this function can be greatly simplified. Check if this is a bug or a feature.
		if (filltype_main == 1) {
			// smooth = fill the pixel if a neighbour is
			// since the pixels are filled from left to right and from top to bottom
			// the neighbours selected are above and left
			if (random.nextDouble() < fill_smoothing) {
				boolean isChosenFilled = false;

				if (isAboveFilled(spr,x,y) && 
						isLeftFilled(spr,x,y) == false) {
					isChosenFilled = true;
				} else if (isAboveFilled(spr,x,y) == false && 
						isLeftFilled(spr,x,y)) {
					isChosenFilled = true;
				} else if (isAboveFilled(spr,x,y) && 
						isLeftFilled(spr,x,y)) {
					if (random.nextDouble() > fill_smoothing_x_bias) {
						isChosenFilled = true;
					} else {
						isChosenFilled = true;
					}
				}
				if (isChosenFilled){
					spr.hull[x][y] = filltype_fill;
				}
			} else {
				// if the colour is not smooth, just fill it at random
				if (random.nextDouble() > fill_probability){
					spr.hull[x][y] = filltype_fill;
				}
			}
		// always fill 
		} else if (filltype_main == 2) {
			spr.hull[x][y] = filltype_fill;
		// always black
		} else if (filltype_main == 3) {
			spr.hull[x][y] = 3;
		}
	}

	private boolean isLeftFilled(Sprite spr, int x, int y){
		if (x > 0){
			if((spr.hull[x-1][y] & 3) == 2){
				return true;
			}
		}
		return false;
	}
	
	private boolean isAboveFilled(Sprite spr, int x, int y){
		if (y > 0){
			if((spr.hull[x][y-1] & 3) == 2){
				return true;
			}
		}
		return false;
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
				int color_number = spr.colidx[x][y];
				int flipped_x = size_x - x - 1;
				int flipped_y = size_y - y - 1;
				// if a pixel must be flipped, flip it and apply shade
				if (mustFlipX(x)) {
					spr.colidx[flipped_x][y] = color_number + shade_at_flip_x;
				}
				if (mustFlipY(y)) {
					spr.colidx[x][flipped_y] = color_number + shade_at_flip_y;
				}
				if (mustFlipX(x) &&
						mustFlipY(y)) {
					spr.colidx[flipped_x][flipped_y] = color_number
							+ shade_at_flip_x 
							+ shade_at_flip_y;
				}
			}
		}
	}
	
	/**
	 * returns whether the x coordinate must be flipped or not
	 */
	private boolean mustFlipX(int x){
		return flip_x && 
				x < size_x / 2;
	}
	
	/**
	 * returns whether the y coordinate must be flipped or not
	 */
	private boolean mustFlipY(int y){
		return flip_y && 
				y < size_y / 2;
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
	 * 
	 * @param spr
	 */
	public void bevelShade(Sprite spr) {
		// shade given colours
		for (int y = 0; y < size_y; y++) {
			for (int x = 0; x < size_x; x++) {
				int color_index = spr.colidx[x][y];
				// if is a colour (not transparent or black)
				if (color_index >= 6) {
					int top_left_distance = Math.min(
							findOutlineDistanceTop(spr,x,y), 
							findOutlineDistanceLeft(spr,x,y));
					int bottom_right_distance = Math.min(
							findOutlineDistanceBottom(spr,x,y), 
							findOutlineDistanceRight(spr,x,y));
					// 0=darkest ... 2=brightest.
					int bright = 1;
					// special cases: thin areas
					if (top_left_distance == 1 && 
							bottom_right_distance == 1){
						bright = 1;
					}else if (top_left_distance == 1){
						bright = 2;
					}else if (bottom_right_distance == 1){
						bright = 0;
					}

					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						// base index for all the shades of a color
						int color_base_index = 3 * (color_index / 3);
						// adjusts the shade index by making it brighter
						int color_shade_index = 2 - 2 * bright;
						spr.colidx[x][y] =  color_base_index + color_shade_index;
					}
				}
			}
		}
	}

	private int findOutlineDistanceTop(Sprite spr, 
			int x, 
			int y) {
		return findOutlineDistance(spr, x, y, -1, 0);
	}
	
	private int findOutlineDistanceBottom(Sprite spr, 
			int x, 
			int y) {
		return findOutlineDistance(spr, x, y, 1, 0);
	}
	
	private int findOutlineDistanceLeft(Sprite spr, 
			int x, 
			int y) {
		return findOutlineDistance(spr, x, y, 0, -1);
	}
	
	private int findOutlineDistanceRight(Sprite spr, 
			int x, 
			int y) {
		return findOutlineDistance(spr, x, y, 0, 1);
	}
	
	/**
	 * Finds the closest distance to an outline (a black or transparent pixel)
	 * up to depth distance. dx and dy determine the direction to explore.
	 * 
	 * TODO: This function probably can be refactored or modified further to make more clear 
	 * its function (look for the closer distance to an outline.
	 * 
	 * Sketch of a solution: a function that calculates the closer distance to an outline in every direction 
	 * (vertical, horizontal, maybe diagonals).
	 * 
	 * @param spr
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param depth
	 * @return
	 */
	private int findOutlineDistance(Sprite spr, 
			int x, 
			int y, 
			int dx, 
			int dy) {
		if (isInBounds(x,y) == false){
			return 0;
		}
		// if is transparent or black, is the outline
		if (spr.colidx[x][y] <= 5){
			return 0;
		}
		// set the distance to the maximum posible
		// in case we are not exploring that direction
		int xdist = Integer.MAX_VALUE;
		// move x by the amount dx (this is used to move to left and right)
		if(dx != 0){
			xdist = findOutlineDistance(spr, x + dx, y, dx, dy);
		}
		
		int ydist = Integer.MAX_VALUE;
		// move y by the amount dy (this is used to move up and down)
		if(dy != 0){
			ydist = findOutlineDistance(spr, x, y + dy, dx, dy);
		}
		
		// return the minimum distance from both axis
		if(xdist < ydist){
			return xdist + 1;
		}else{
			return ydist + 1;
		}
	}

	/**
	 * returns whether the coordinates are inside the bounds of the sprite. 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isInBounds(int x, int y){
		return x >= 0 && 
				x < size_x && 
				y >= 0 && 
				y < size_y;
	}
	
	/**
	 * Performs a Gouraud shading
	 * @param spr
	 */
	public void gouraudShade(Sprite spr) {
		// coordinates of the centre of the focus
		int focus_center_x = size_x / 4 + (int) (random.nextDouble() * 3);
		int focus_center_y = size_y / 4 + (int) (random.nextDouble() * 3);
		// maximum distance from the focus, given squared
		int maximum_distance = (int) Math.pow(size_x - focus_center_x - 1, 2);
		int highlight_radius_x = (int) (random.nextDouble() * 3);
		int highlight_radius_y = (int) (random.nextDouble() * 3);
		int inner_radius = 7 + (int) (random.nextDouble() * 16);
		int outer_radius = 7 + (int) (random.nextDouble() * 16);
		for (int y = 0; y < size_y; y++) {
			int distance_focus_y = Math.abs(y - focus_center_y);
			for (int x = 0; x < size_x; x++) {
				int distance_focus_x = Math.abs(x - focus_center_x);
				// the distance is dx^2+dy^2, where dx is the distance of x to the focus
				int distance = (int) (Math.pow(distance_focus_x,2) +  Math.pow(distance_focus_y,2));
				int color_index = spr.colidx[x][y];
				// if is a colour (not transparent or black)
				if (color_index >= 6) {
					// 0=darkest .. 4=brightest. Odd numbers will dither.
					int bright = 2;
					if (isInsideHighlightRadius(distance_focus_x,
							distance_focus_y,
							highlight_radius_x, 
							highlight_radius_y)){
						bright = 4;
					}else if (distance <= inner_radius){
						bright = 3;
					}else if (distance >= maximum_distance - outer_radius){
						bright = 0;
					}else if (distance >= maximum_distance - outer_radius - 13){
						bright = 1;
					}
					boolean dither = isDither(bright, x, y);
					bright /= 2;
					// 0, 1, or 2
					if(dither){
						bright++;
					}
					
					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						// base index for all the shades of a color
						int color_base_index = 3 * (color_index / 3);
						// adjusts the shade index by making it brighter
						int color_shade_index = 2 - 2 * bright;
						spr.colidx[x][y] =  color_base_index + color_shade_index;
					}
				}
			}
		}
	}

	private boolean isInsideHighlightRadius(int distance_x, int distance_y, int radius_x, int radius_y){
		return distance_x <= radius_x && distance_y <= radius_y;
	}
	
	private boolean isDither(int bright, int x, int y){
		// if the bright level is odd and the pixel coordinates are odd
		// the pixel dithers
		return (bright % 2) == 1 && 
				((x + y) % 2) == 1;
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
