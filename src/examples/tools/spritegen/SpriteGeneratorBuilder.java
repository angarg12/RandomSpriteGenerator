package examples.tools.spritegen;

import java.util.Vector;

import examples.tools.spritegen.color.ColorScheme;

public class SpriteGeneratorBuilder {
	private int[] color_table;
	private int[][] fill_table;
	private int[][][] animation_table;
	private boolean flip_x = true;
	private boolean flip_y = false;
	private Shading shading = Shading.NONE;
	private int shade_at_flip_x = 0;
	private int shade_at_flip_y = 0;
	private double fill_probability = 0.6;
	private double fill_smoothing = 0.2;
	private double fill_smoothing_x_bias = 0.8;
	private double black_probability = 0.2;
	private double highlight_probability = 0.4;
	private double color_smoothing = 0.7;
	private double color_smoothing_x_bias = 0.5;
	
	public static SpriteGenerator[] predefinedInstances(){
		Vector<SpriteGenerator> instances = new Vector<SpriteGenerator>();
		SpriteGenerator generator =	new SpriteGenerator();
		generator.setColorTable(new int[]{
					0x010101, 0x010101, 0x010101, // trans
					0x000000, 0x000000, 0x000000,     // outline
					0xC0A080, 0x806040, 0x503010,     // col2
					0xFF7070, 0xD04040, 0xB02020,     // col1
					0xFFE020, 0xFFB000, 0xF0A000,     // col3
					0xFFFFFF, 0xB0B0B0, 0x808080,     // highlight
					});
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.WALK_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.FLY_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE_PURPLE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.CRAWL2_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BROWN_RED_YELLOW);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.CRAWL_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GRAY_BLUE_TEAL);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BEND_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GREEN);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BUBBLE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.PURPLE_RED);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.POKE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.RED_YELLOW);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.WALK2_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.CRAWL_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE_GREEN);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BEND_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE_PURPLE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BUBBLE_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);

		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BROWN_RED_YELLOW);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.POKE_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GRAY_BLUE_TEAL);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.TURN_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GREEN);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BEND_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.PURPLE_RED);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.CRAWL_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.RED_YELLOW);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BUBBLE_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.POKE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE_GREEN);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.WIGGLE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BLUE_PURPLE);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.WIGGLE2_12);
		generator.setFlipHorizontal(false);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.BROWN_RED_YELLOW);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BOUNCE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GRAY_BLUE_TEAL);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.BOUNCE_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.GREEN);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.NULL_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(false);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator();
		generator.setColorTable(ColorScheme.PURPLE_RED);
		generator.setFillTable(FillingTable.RAND_12);
		generator.setAnimationTable(AnimationTable.NULL_12);
		generator.setFlipHorizontal(true);
		generator.setFlipVertical(true);
		generator.setShadeAtFlipHorizontal(0); 
		generator.setShadeAtFlipVertical(0); 
		generator.setFillProbability(0.6); 
		generator.setFillSmoothing(0.2); 
		generator.setFillSmoothingHorizontalBias(0.5);
		generator.setBlackProbability(0.3); 
		generator.setHighlightProbability(0.4); 
		generator.setColorSmoothing(0.3);
		generator.setColorSmoothingHorizontalBias(0.5);
		instances.add(generator);
		
		return instances.toArray(new SpriteGenerator[]{});
	}
	
	public SpriteGeneratorBuilder colorTable(int[] color_table) {
		this.color_table = color_table;
		return this;
	}

	public SpriteGeneratorBuilder fillTable(int[][] fill_table) {
		this.fill_table = fill_table;
		return this;
	}

	public SpriteGeneratorBuilder animationTable(int[][][] animation_table) {
		this.animation_table = animation_table;
		return this;
	}

	public SpriteGeneratorBuilder flipHorizontal(boolean flip_x) {
		this.flip_x = flip_x;
		return this;
	}

	public SpriteGeneratorBuilder flipVertical(boolean flip_y) {
		this.flip_y = flip_y;
		return this;
	}

	public SpriteGeneratorBuilder shading(Shading shading) {
		this.shading = shading;
		return this;
	}

	public SpriteGeneratorBuilder shadeAtFlipHorizontal(int shade_at_flip_x) {
		this.shade_at_flip_x = shade_at_flip_x;
		return this;
	}

	public SpriteGeneratorBuilder shadeAtFlipVertical(int shade_at_flip_y) {
		this.shade_at_flip_y = shade_at_flip_y;
		return this;
	}

	public SpriteGeneratorBuilder fillProbability(double fill_probability) {
		this.fill_probability = fill_probability;
		return this;
	}

	public SpriteGeneratorBuilder fillSmoothing(double fill_smoothing) {
		this.fill_smoothing = fill_smoothing;
		return this;
	}

	public SpriteGeneratorBuilder fillSmoothingHorizontalBias(double fill_smoothing_x_bias) {
		this.fill_smoothing_x_bias = fill_smoothing_x_bias;
		return this;
	}

	public SpriteGeneratorBuilder blackProbability(double black_probability) {
		this.black_probability = black_probability;
		return this;
	}

	public SpriteGeneratorBuilder highlightProbability(double highlight_probability) {
		this.highlight_probability = highlight_probability;
		return this;
	}

	public SpriteGeneratorBuilder colorSmoothing(double color_smoothing) {
		this.color_smoothing = color_smoothing;
		return this;
	}

	public SpriteGeneratorBuilder colorSmoothingHorizontalBias(double color_smoothing_x_bias) {
		this.color_smoothing_x_bias = color_smoothing_x_bias;
		return this;
	}
	
	public SpriteGenerator build(){
		SpriteGenerator generator =	new SpriteGenerator();
		generator.setColorTable(this.color_table);
		generator.setFillTable(this.fill_table);
		generator.setAnimationTable(this.animation_table);
		generator.setFlipHorizontal(this.flip_x);
		generator.setFlipVertical(this.flip_y);
		generator.setShading(shading);
		generator.setShadeAtFlipHorizontal(this.shade_at_flip_x); 
		generator.setShadeAtFlipVertical(this.shade_at_flip_y); 
		generator.setFillProbability(this.fill_probability); 
		generator.setFillSmoothing(this.fill_smoothing); 
		generator.setFillSmoothingHorizontalBias(this.fill_smoothing_x_bias);
		generator.setBlackProbability(this.black_probability); 
		generator.setHighlightProbability(this.highlight_probability); 
		generator.setColorSmoothing(this.color_smoothing);
		generator.setColorSmoothingHorizontalBias(this.color_smoothing_x_bias);
		return generator;
	}
}
