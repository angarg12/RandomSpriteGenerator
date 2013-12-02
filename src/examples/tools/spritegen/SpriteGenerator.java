package examples.tools.spritegen;

import java.util.Random;

import examples.tools.spritegen.color.ColorScheme;

public final class SpriteGenerator {
	/**
	 * TODO: Encapsulate filling tables, animation tables and generation
	 * parameters in some kind of pattern (factory, strategy?)
	 */
	private static Random random = new Random();

	/**
	 * TODO: fixRandom should be package private, but tests are in another package
	 * @param seed
	 */
	public static void fixRandom(int seed) {
		random = new Random(seed);
	}

	private final int[] color_table;
	private final int[][] fill_table;
	private final int[][][] animation_table;

	private final boolean flip_x;
	private final boolean flip_y;

	private final Shading shading;
	// shades the colour when flipping
	// this is based on the fact that there are only 3 hard-coded shades.
	// with arbitrary values you don't have this limitation
	// note: xshadingfac+yshadingfac must be <= 2
	// 0=no shading
	// 1=darken
	// 2=darken more
	private final int shade_at_flip_x;
	private final int shade_at_flip_y;

	// probability of filling pixel
	private final double fill_probability;
	// probability that a pixel is filled the same as its neighbours
	private final double fill_smoothing;
	// balance between taking horizontal versus vertical neighbours
	private final double fill_smoothing_x_bias;
	// probability of black pixel if enabled
	// TODO: actually fills it black or transparent??
	private final double black_probability;
	// probability of highlight pixel if enabled
	private final double highlight_probability;
	// probability that a colour (non-black) pixel is taken from neighbour
	private final double color_smoothing;
	// balance between taking horizontal versus vertical neighbours
	private final double color_smoothing_x_bias;

	/**
	 * TODO: Constructor should be private, and make the builder a nested class?.
	 * 
	 * @param color_table
	 * @param fill_table
	 * @param animation_table
	 * @param flip_x
	 * @param flip_y
	 * @param shading
	 * @param shade_at_flip_x
	 * @param shade_at_flip_y
	 * @param fill_probability
	 * @param fill_smoothing
	 * @param fill_smoothing_x_bias
	 * @param black_probability
	 * @param highlight_probability
	 * @param color_smoothing
	 * @param color_smoothing_x_bias
	 */
	public SpriteGenerator(int[] color_table, 
			int[][] fill_table, 
			int[][][] animation_table, 
			boolean flip_x, 
			boolean flip_y, 
			Shading shading, 
			int shade_at_flip_x, 
			int shade_at_flip_y, 
			double fill_probability, 
			double fill_smoothing, 
			double fill_smoothing_x_bias, 
			double black_probability, 
			double highlight_probability, 
			double color_smoothing, 
			double color_smoothing_x_bias){
		this.color_table = color_table;
		this.fill_table = fill_table;
		this.animation_table = animation_table;
		this.flip_x = flip_x;
		this.flip_y = flip_y;
		this.shading = shading;
		
		switch (this.shading){
			case BEVEL:
				this.shade_at_flip_x = 0;
				this.shade_at_flip_y = 0;
				this.highlight_probability = 0;
				break;
			case GOURAUD:
				this.shade_at_flip_x = shade_at_flip_x;
				this.shade_at_flip_y = shade_at_flip_y;
				this.highlight_probability = 0;
				break;
			default:
				this.shade_at_flip_x = shade_at_flip_x;
				this.shade_at_flip_y = shade_at_flip_y;
				this.highlight_probability = highlight_probability;
		}
		this.fill_probability = fill_probability;
		this.fill_smoothing = fill_smoothing;
		this.fill_smoothing_x_bias = fill_smoothing_x_bias;
		this.black_probability = black_probability;
		this.color_smoothing = color_smoothing;
		this.color_smoothing_x_bias = color_smoothing_x_bias;
	}
	
	//================================================================================
    // createSprite
    //================================================================================
	
	/**
	 * Creates a new sprite.
	 * 
	 * @param color_table
	 * @return
	 */
	public Sprite createSprite() {
		// the limit of the loop is the size of the sprite
		int x_max = fill_table[0].length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_x) {
			x_max /= 2;
		}
		// the limit of the loop is the size of the sprite
		int y_max = fill_table.length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_y) {
			y_max /= 2;
		}
		int total_x_size = fill_table[0].length;
		// the total x size is the x size by the number of frames (plus 1 for
		// the still)
		if (animation_table != null) {
			total_x_size = fill_table[0].length * (animation_table.length + 1);
		}

		int numberOfFrames = 1;
		if(animation_table != null){
			numberOfFrames = animation_table.length + 1;
		}
		
		Sprite spr = new Sprite(color_table, 
				this, 
				fill_table[0].length, 
				total_x_size, 
				fill_table.length,
				numberOfFrames);

		for (int y = 0; y < y_max; y++) {
			for (int x = 0; x < x_max; x++) {
				fillPixel(x, y, spr);
			}
		}
		colorize(spr);
		flip(spr);
		switch (shading) {
		case BEVEL:
			bevelShade(spr);
			break;
		case GOURAUD:
			gouraudShade(spr);
			break;
		default:
			break;
		}
		indexToRGB(spr);
		animate(spr);
		addOutline(spr);
		return spr;
	}

	/**
	 * Decide which parts of hull to fill: this decision is made based on the
	 * smooth coefficient and the fill probability.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 */
	private void fillPixel(int x, int y, Sprite spr) {
		int filltype = fill_table[y][x];
		// extract code from two lower bits
		int filltype_main = (filltype & 3);
		// extract code from two upper bits
		int filltype_fill = ((filltype & 12) | 2);
		// fill or empty
		// TODO: Notice that the logic is a little bit warped. If a neighbour is
		// filled, the pixel
		// gets filled, but with its own value, not the one of the neighbour.
		// This means that fill_smoothing
		// actually does nothing, and this function can be greatly simplified.
		// Check if this is a bug or a feature.
		if (filltype_main == 1) {
			// smooth = fill the pixel if a neighbour is
			// since the pixels are filled from left to right and from top to
			// bottom
			// the neighbours selected are above and left
			if (random.nextDouble() < fill_smoothing) {
				boolean isChosenFilled = false;

				if (isAboveFilled(spr, x, y)
						&& isLeftFilled(spr, x, y) == false) {
					isChosenFilled = true;
				} else if (isAboveFilled(spr, x, y) == false
						&& isLeftFilled(spr, x, y)) {
					isChosenFilled = true;
				} else if (isAboveFilled(spr, x, y) && isLeftFilled(spr, x, y)) {
					if (random.nextDouble() > fill_smoothing_x_bias) {
						isChosenFilled = true;
					} else {
						isChosenFilled = true;
					}
				}
				if (isChosenFilled) {
					spr.hull[x][y] = filltype_fill;
				}
			} else {
				// if the colour is not smooth, just fill it at random
				if (random.nextDouble() > fill_probability) {
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

	private boolean isLeftFilled(Sprite spr, int x, int y) {
		if (x > 0) {
			if ((spr.hull[x - 1][y] & 3) == 2) {
				return true;
			}
		}
		return false;
	}

	private boolean isAboveFilled(Sprite spr, int x, int y) {
		if (y > 0) {
			if ((spr.hull[x][y - 1] & 3) == 2) {
				return true;
			}
		}
		return false;
	}
	
	//================================================================================
    // colorize
    //================================================================================
	
	/**
	 * Colorizes a sprite.
	 * 
	 * @param spr
	 */
	public void colorize(Sprite spr) {
		// the limit of the loop is the size of the sprite
		int x_max = fill_table[0].length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_x) {
			x_max /= 2;
		}
		// the limit of the loop is the size of the sprite
		int y_max = fill_table.length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_y) {
			y_max /= 2;
		}
		// the index of the highlight color
		int white = color_table.length / 3 - 1;
		for (int y = 0; y < y_max; y++) {
			for (int x = 0; x < x_max; x++) {
				int color_index = 0;
				int fill_type = spr.hull[x][y];
				if (isOutline(fill_type)) { // outline
					color_index = 1;
				} else if (isAlwaysFill(fill_type)) { // normal fill
					if (isBlackEnabled(fill_type)) { // black enabled
						if (random.nextDouble() < black_probability) {
							color_index = 1; // black
						} else {
							if (isHighlightEnabled(fill_type)) { // highlight enabled
								if (random.nextDouble() < highlight_probability) {
									color_index = white;
								} else {
									// any colour except black and highlight
									color_index = (int) (2 + random.nextDouble()
											* (color_table.length / 3 - 3));
								}
							}
						}
					} else if (isHighlightEnabled(fill_type)) { // highlight enabled
						if (random.nextDouble() < highlight_probability) {
							color_index = white;
						} else {
							// any colour except black and highlight
							color_index = (int) (2 + random.nextDouble()
									* (color_table.length / 3 - 3));
						}
					} else { // any colour except black and highlight
						// NOTE: previously highlight was also enabled but with
						// normal probability
						color_index = (int) (2 + random.nextDouble()
								* (color_table.length / 3 - 3));
					}
					// XXX both black and highlight not supported
					// smooth = get colour from neighbouring pixel
					if (color_index > 1 && random.nextDouble() < color_smoothing) {
						int above_index = 0;
						int left_index = 0;
						int chosen_index = 0;
						
						if (x > 0) {
							left_index = spr.colidx[x - 1][y] / 3;
						}
						if (y > 0) {
							above_index = spr.colidx[x][y - 1] / 3;
						}
						if (above_index == 0 && left_index == 0) {
							chosen_index = 0;
						} else if (above_index != 0 && left_index == 0) {
							chosen_index = above_index;
						} else if (above_index == 0 && left_index != 0) {
							chosen_index = left_index;
						} else if (above_index != 0 && left_index != 0) {
							if (random.nextDouble() > color_smoothing_x_bias) {
								chosen_index = above_index;
							} else {
								chosen_index = left_index;
							}
						}
						if (chosen_index > 1) {
							color_index = chosen_index;
						}
					}
				}
				spr.colidx[x][y] = color_index * 3;
			}
		}
	}

	private boolean isOutline(int fill_type){
		return (fill_type & 3) == 3;
	}
	
	private boolean isAlwaysFill(int fill_type){
		return (fill_type & 3) == 2;
	}
	
	private boolean isBlackEnabled(int fill_type){
		return (fill_type & 4) == 4;
	}
	
	private boolean isHighlightEnabled(int fill_type){
		return (fill_type & 8) == 8;
	}
	//================================================================================
    // flip
    //================================================================================
	
	/**
	 * flip according to symmetry axes. Apply shade if needed.
	 */
	public void flip(Sprite spr) {
		for (int y = 0; y < fill_table.length; y++) {
			for (int x = 0; x < fill_table[0].length; x++) {
				int color_number = spr.colidx[x][y];
				int flipped_x = fill_table[0].length - x - 1;
				int flipped_y = fill_table.length - y - 1;
				// if a pixel must be flipped, flip it and apply shade
				if (mustFlipX(x)) {
					spr.colidx[flipped_x][y] = color_number + shade_at_flip_x;
				}
				if (mustFlipY(y)) {
					spr.colidx[x][flipped_y] = color_number + shade_at_flip_y;
				}
				if (mustFlipX(x) && mustFlipY(y)) {
					spr.colidx[flipped_x][flipped_y] = color_number
							+ shade_at_flip_x + shade_at_flip_y;
				}
			}
		}
	}

	/**
	 * returns whether the x coordinate must be flipped or not
	 */
	private boolean mustFlipX(int x) {
		return flip_x && x < fill_table[0].length / 2;
	}

	/**
	 * returns whether the y coordinate must be flipped or not
	 */
	private boolean mustFlipY(int y) {
		return flip_y && y < fill_table.length / 2;
	}

	//================================================================================
    // bevelShade
    //================================================================================
	
	/**
	 * Performs a bevel shading
	 * 
	 * @param spr
	 */
	public void bevelShade(Sprite spr) {
		// shade given colours
		for (int y = 0; y < fill_table.length; y++) {
			for (int x = 0; x < fill_table[0].length; x++) {
				int color_index = spr.colidx[x][y];
				// if is a colour (not transparent or black)
				if (color_index >= 6) {
					int top_left_distance = Math.min(
							findOutlineDistanceTop(spr, x, y),
							findOutlineDistanceLeft(spr, x, y));
					int bottom_right_distance = Math.min(
							findOutlineDistanceBottom(spr, x, y),
							findOutlineDistanceRight(spr, x, y));
					// 0=darkest ... 2=brightest.
					int bright = 1;
					// special cases: thin areas
					if (top_left_distance == 1 && bottom_right_distance == 1) {
						bright = 1;
					} else if (top_left_distance == 1) {
						bright = 2;
					} else if (bottom_right_distance == 1) {
						bright = 0;
					}

					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						// base index for all the shades of a color
						int color_base_index = 3 * (color_index / 3);
						// adjusts the shade index by making it brighter
						int color_shade_index = 2 - 2 * bright;
						spr.colidx[x][y] = color_base_index + color_shade_index;
					}
				}
			}
		}
	}

	private int findOutlineDistanceTop(Sprite spr, int x, int y) {
		return findOutlineDistance(spr, x, y, -1, 0);
	}

	private int findOutlineDistanceBottom(Sprite spr, int x, int y) {
		return findOutlineDistance(spr, x, y, 1, 0);
	}

	private int findOutlineDistanceLeft(Sprite spr, int x, int y) {
		return findOutlineDistance(spr, x, y, 0, -1);
	}

	private int findOutlineDistanceRight(Sprite spr, int x, int y) {
		return findOutlineDistance(spr, x, y, 0, 1);
	}

	/**
	 * Finds the closest distance to an outline (a black or transparent pixel)
	 * up to depth distance. dx and dy determine the direction to explore.
	 * 
	 * TODO: This function probably can be refactored or modified further to
	 * make more clear its function (look for the closer distance to an outline.
	 * 
	 * Sketch of a solution: a function that calculates the closer distance to
	 * an outline in every direction (vertical, horizontal, maybe diagonals).
	 * 
	 * @param spr
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param depth
	 * @return
	 */
	private int findOutlineDistance(Sprite spr, int x, int y, int dx, int dy) {
		if (isInBounds(x, y) == false) {
			return 0;
		}
		// if is transparent or black, is the outline
		if (spr.colidx[x][y] <= 5) {
			return 0;
		}
		// set the distance to the maximum posible
		// in case we are not exploring that direction
		int xdist = Integer.MAX_VALUE;
		// move x by the amount dx (this is used to move to left and right)
		if (dx != 0) {
			xdist = findOutlineDistance(spr, x + dx, y, dx, dy);
		}

		int ydist = Integer.MAX_VALUE;
		// move y by the amount dy (this is used to move up and down)
		if (dy != 0) {
			ydist = findOutlineDistance(spr, x, y + dy, dx, dy);
		}

		// return the minimum distance from both axis
		if (xdist < ydist) {
			return xdist + 1;
		} else {
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
	private boolean isInBounds(int x, int y) {
		return x >= 0 && x < fill_table[0].length && y >= 0 && y < fill_table.length;
	}

	//================================================================================
    // gouraudShade
    //================================================================================
	
	/**
	 * Performs a Gouraud shading
	 * 
	 * @param spr
	 */
	public void gouraudShade(Sprite spr) {
		// coordinates of the centre of the focus
		int focus_center_x = fill_table[0].length / 4 + (int) (random.nextDouble() * 3);
		int focus_center_y = fill_table.length / 4 + (int) (random.nextDouble() * 3);
		// maximum distance from the focus, given squared
		int maximum_distance = (int) Math.pow(fill_table[0].length - focus_center_x - 1, 2);
		int highlight_radius_x = (int) (random.nextDouble() * 3);
		int highlight_radius_y = (int) (random.nextDouble() * 3);
		int inner_radius = 7 + (int) (random.nextDouble() * 16);
		int outer_radius = 7 + (int) (random.nextDouble() * 16);
		for (int y = 0; y < fill_table.length; y++) {
			int distance_focus_y = Math.abs(y - focus_center_y);
			for (int x = 0; x < fill_table[0].length; x++) {
				int distance_focus_x = Math.abs(x - focus_center_x);
				// the distance is dx^2+dy^2, where dx is the distance of x to
				// the focus
				int distance = (int) (Math.pow(distance_focus_x, 2) + Math.pow(
						distance_focus_y, 2));
				int color_index = spr.colidx[x][y];
				// if is a colour (not transparent or black)
				if (color_index >= 6) {
					// 0=darkest .. 4=brightest. Odd numbers will dither.
					int bright = 2;
					if (isInsideHighlightRadius(distance_focus_x,
							distance_focus_y, highlight_radius_x,
							highlight_radius_y)) {
						bright = 4;
					} else if (distance <= inner_radius) {
						bright = 3;
					} else if (distance >= maximum_distance - outer_radius) {
						bright = 0;
					} else if (distance >= maximum_distance - outer_radius - 13) {
						bright = 1;
					}
					boolean dither = isDither(bright, x, y);
					bright /= 2;
					// 0, 1, or 2
					if (dither) {
						bright++;
					}

					if (bright == 2) {
						spr.colidx[x][y] = 15; // highlight
					} else {
						// base index for all the shades of a color
						int color_base_index = 3 * (color_index / 3);
						// adjusts the shade index by making it brighter
						int color_shade_index = 2 - 2 * bright;
						spr.colidx[x][y] = color_base_index + color_shade_index;
					}
				}
			}
		}
	}

	private boolean isInsideHighlightRadius(int distance_x, int distance_y,
			int radius_x, int radius_y) {
		return distance_x <= radius_x && distance_y <= radius_y;
	}

	// if the bright level is odd and the pixel coordinates are odd
	// the pixel dithers
	private boolean isDither(int bright, int x, int y) {
		return (Math.abs(bright) % 2) == 1 && (Math.abs(x + y) % 2) == 1;
	}

	//================================================================================
    // indexToRGB
    //================================================================================
	
	/**
	 * Transforms the colour indexes to the colors themselves
	 * 
	 * @param spr
	 */
	public void indexToRGB(Sprite spr) {
		for (int x = 0; x < fill_table[0].length; x++) {
			for (int y = 0; y < fill_table.length; y++) {
				spr.pixels[x][y] = spr.coltable[spr.colidx[x][y]];
			}
		}
	}

	//================================================================================
    // animate
    //================================================================================
	
	/**
	 * Animates a sprite
	 * 
	 * @param spr
	 */
	public void animate(Sprite spr) {
		// now, animate if applicable
		if (animation_table == null) {
			return;
		}
		// d = distance travelled. Pixels that travel the largest distance
		// should overwrite other pixels. This is why we process pixels in the
		// order
		// of the distance they travel
		for (int distance = 0; distance <= 2; distance++) {
			for (int y = 0; y < fill_table.length; y++) {
				for (int x = 0; x < fill_table[0].length; x++) {
					int color = spr.pixels[x][y];
					// if the colour is transparent, there is no need for
					// animation
					if (color == ColorScheme.TRANSPARENT) {
						continue;
					}
					for (int frame = 0; frame < animation_table.length; frame++) {
						int movement = animation_table[frame][y][x];
						// if the distance travelled is different from distance,
						// there is no need
						// to animate
						// 0 is 0, 1-8 is 1 and > 8 is 2
						if (AnimationTable.distanceTraveled(movement) != distance) {
							continue;
						}
						// a pretty obscure way to decide in what direction it
						// should move.
						// will get better once the animation movements are
						// refactored to its own classes.
						// that way you could use inversion of control
						// have a TwoLeft movement class, with getX and getY
						// methods that return -2 and 0 respectively
						int distance_x = 0;
						int distance_y = 0;
						// use this multiplier if the distance is two
						if (movement > 8) {
							movement -= 8;
						}
						// one up
						if (movement == 8 || movement == 1 || movement == 2) {
							distance_y = -1;
						}
						// one right
						if (movement == 2 || movement == 3 || movement == 4) {
							distance_x = 1;
						}
						// one down
						if (movement == 4 || movement == 5 || movement == 6) {
							distance_y = 1;
						}
						// one left
						if (movement == 6 || movement == 7 || movement == 8) {
							distance_x = -1;
						}
						distance_x *= distance;
						distance_y *= distance;
						// TODO: small 'bug': if the animation paints a pixel in
						// the border, it shows
						// coloured, when it should be shown black (as per the
						// outline).
						if (isInBounds(x + distance_x, y + distance_y)) {
							// paint the pixel where the animation lands in the
							// current frame
							spr.pixels[(frame + 1) * fill_table[0].length + x + distance_x]
									  [y + distance_y] = color;
						}
					}
				}
			}
		}
	}
	
	//================================================================================
    // addOutline
    //================================================================================
	
	/**
	 * Adds a black outline surrounding the sprite.
	 * 
	 * @param spr
	 */
	public void addOutline(Sprite spr) {
		for (int x = 0; x < spr.pixels.length; x++) {
			for (int y = 0; y < spr.pixels[x].length; y++) {
				boolean isNeighbourColored = isLeftPixelColored(x, y, spr)
						|| isRightPixelColored(x, y, spr)
						|| isDownPixelColored(x, y, spr)
						|| isUpPixelColored(x, y, spr);
				if (isNeighbourColored
						&& spr.pixels[x][y] == ColorScheme.TRANSPARENT) {
					spr.pixels[x][y] = 0;
				}
				if (isNeighbourColored == false && spr.pixels[x][y] == 0) {
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
	private boolean isLeftPixelColored(int x, int y, Sprite spr) {
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
	private boolean isRightPixelColored(int x, int y, Sprite spr) {
		return x < spr.pixels.length - 1
				&& spr.pixels[x + 1][y] != ColorScheme.TRANSPARENT
				&& spr.pixels[x + 1][y] != 0;
	}

	/**
	 * Returns whether the right pixel is not transparent or black.
	 * 
	 * @param x
	 * @param y
	 * @param spr
	 * @return
	 */
	private boolean isDownPixelColored(int x, int y, Sprite spr) {
		return y > 0 && 
				spr.pixels[x][y - 1] != ColorScheme.TRANSPARENT && 
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
	private boolean isUpPixelColored(int x, int y, Sprite spr) {
		return y < spr.pixels[x].length - 1
				&& spr.pixels[x][y + 1] != ColorScheme.TRANSPARENT
				&& spr.pixels[x][y + 1] != 0;
	}
	
	//================================================================================
    // mutate
    //================================================================================
	
	/**
	 * Mutates the hull of a sprite.
	 */
	public Sprite mutateHull(Sprite spr1, double mutation_factor) {
		// TODO: 'conceptual' bug: since the second sprite uses the same base shape than
		// the original, the mutations aren't that much different from the original.
		// this is good for mutation because we want sprites that are different, but not completely
		// yet for instance, if we set a mutation factor of 1, we would expect completely unrelated sprites
		// (totally random), yet there is a strong correlation between them (because they use the same
		// base filling table).
		Sprite spr = new Sprite(spr1);
		Sprite spr2 = createSprite();
		// the limit of the loop is the size of the sprite
		int x_max = fill_table[0].length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_x) {
			x_max /= 2;
		}

		// the limit of the loop is the size of the sprite
		int y_max = fill_table.length;
		// if the sprite is flipped, only fill half of it (the other half will
		// be the symmetric)
		if (flip_y) {
			y_max /= 2;
		}
		
		for (int y = 0; y < y_max; y++) {
			for (int x = 0; x < x_max; x++) {
				if (random.nextDouble() < mutation_factor) {
					spr.colidx[x][y] = spr2.colidx[x][y];
				} else {
					spr.colidx[x][y] = spr1.colidx[x][y];
				}
			}
		}

		flip(spr);
		switch (shading) {
		case BEVEL:
			bevelShade(spr);
			break;
		case GOURAUD:
			gouraudShade(spr);
			break;
		default:
			break;
		}
		indexToRGB(spr);
		animate(spr);
		addOutline(spr);
		return spr;
	}	
	
	/**
	 * Mutates the color of a sprite.
	 */
	public Sprite mutateColor(Sprite spr1, int[] mutated_color_table, double mutation_factor) {
		Sprite spr = new Sprite(spr1);
		
		int shorter_table_length = spr.coltable.length;
		if(mutated_color_table.length < shorter_table_length){
			shorter_table_length = mutated_color_table.length;
		}
		for(int i = 0; i < shorter_table_length; i++){
			if(random.nextDouble() < mutation_factor){
				spr.coltable[i] = mutated_color_table[i];
			}
		}

		indexToRGB(spr);
		animate(spr);
		addOutline(spr);
		return spr;
	}
}
