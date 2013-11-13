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
		SpriteGenerator generator =	new SpriteGenerator(
		new int[]{
					0x010101, 0x010101, 0x010101, // trans
					0x000000, 0x000000, 0x000000,     // outline
					0xC0A080, 0x806040, 0x503010,     // col2
					0xFF7070, 0xD04040, 0xB02020,     // col1
					0xFFE020, 0xFFB000, 0xF0A000,     // col3
					0xFFFFFF, 0xB0B0B0, 0x808080,     // highlight
					},
		FillingTable.RAND_12,
		AnimationTable.WALK_12,
		true,
		false,
		Shading.NONE, 
		0,
		0,
		0.6,
		0.2,
		0.5,
		0.3,
		0.4,
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BLUE,
		FillingTable.RAND_12,
		AnimationTable.FLY_12,
		true,
		false,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BLUE_PURPLE,
		FillingTable.RAND_12,
		AnimationTable.CRAWL2_12,
		true,
		false,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BROWN_RED_YELLOW,
		FillingTable.RAND_12,
		AnimationTable.CRAWL_12,
		true,
		false,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.GRAY_BLUE_TEAL,
		FillingTable.RAND_12,
		AnimationTable.BEND_12,
		true,
		false,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.GREEN,
		FillingTable.RAND_12,
		AnimationTable.BUBBLE_12,
		true,
		false,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.PURPLE_RED,
		FillingTable.RAND_12,
		AnimationTable.POKE_12,
		true,
		false,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.RED_YELLOW,
		FillingTable.RAND_12,
		AnimationTable.WALK2_12,
		false,
		true,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BLUE,
		FillingTable.RAND_12,
		AnimationTable.CRAWL_12,
		false,
		true,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BLUE_GREEN,
		FillingTable.RAND_12,
		AnimationTable.BEND_12,
		false,
		true,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BLUE_PURPLE,
		FillingTable.RAND_12,
		AnimationTable.BUBBLE_12,
		false,
		true,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);

		generator =	new SpriteGenerator(
		ColorScheme.BROWN_RED_YELLOW,
		FillingTable.RAND_12,
		AnimationTable.POKE_12,
		false,
		true,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.GRAY_BLUE_TEAL,
		FillingTable.RAND_12,
		AnimationTable.TURN_12,
		true,
		true,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.GREEN,
		FillingTable.RAND_12,
		AnimationTable.BEND_12,
		true,
		true,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.PURPLE_RED,
		FillingTable.RAND_12,
		AnimationTable.CRAWL_12,
		true,
		true,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.RED_YELLOW,
		FillingTable.RAND_12,
		AnimationTable.BUBBLE_12,
		false,
		true,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.BLUE,
		FillingTable.RAND_12,
		AnimationTable.POKE_12,
		true,
		true,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.BLUE_GREEN,
		FillingTable.RAND_12,
		AnimationTable.WIGGLE_12,
		true,
		false,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.BLUE_PURPLE,
		FillingTable.RAND_12,
		AnimationTable.WIGGLE2_12,
		false,
		true,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.BROWN_RED_YELLOW,
		FillingTable.RAND_12,
		AnimationTable.BOUNCE_12,
		true,
		false,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.GRAY_BLUE_TEAL,
		FillingTable.RAND_12,
		AnimationTable.BOUNCE_12,
		true,
		true,
		Shading.GOURAUD, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.GREEN,
		FillingTable.RAND_12,
		AnimationTable.NULL_12,
		true,
		false,
		Shading.NONE, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
		instances.add(generator);
		
		generator =	new SpriteGenerator(
		ColorScheme.PURPLE_RED,
		FillingTable.RAND_12,
		AnimationTable.NULL_12,
		true,
		true,
		Shading.BEVEL, 
		0, 
		0, 
		0.6, 
		0.2, 
		0.5,
		0.3, 
		0.4, 
		0.3,
		0.5);
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
		SpriteGenerator generator =	new SpriteGenerator(
			color_table,
			fill_table,
			animation_table,
			flip_x,
			flip_y,
			shading,
			shade_at_flip_x,
			shade_at_flip_y,
			fill_probability,
			fill_smoothing,
			fill_smoothing_x_bias,
			black_probability,
			highlight_probability,
			color_smoothing,
			color_smoothing_x_bias);
		return generator;
	}
}
