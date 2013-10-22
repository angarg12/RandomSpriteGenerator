package examples.tools.spritegen.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import examples.tools.spritegen.SpriteGenerator;
import examples.tools.spritegen.Sprite;

public class SpriteGeneratorTest {	
	SpriteGenerator generator1;
	int[] colorTable1;
	Sprite baseSprite1;
	Sprite animatedSprite1;
	Sprite animatedSprite2;
	
	@Before
	public void setUp() throws Exception {
		generator1 = SpriteGenerator.shapes2[0];
		colorTable1 = new int[]	{
				0x010101, 0x010101, 0x010101, // trans
				0x000000, 0x000000, 0x000000,     // outline
				0xC0A080, 0x806040, 0x503010,     // col2
				0xFF7070, 0xD04040, 0xB02020,     // col1
				0xFFE020, 0xFFB000, 0xF0A000,     // col3
				0xFFFFFF, 0xB0B0B0, 0x808080,     // highlight
				};
		initializeSprites();
	}
	
	public void initializeSprites() throws Exception {
		baseSprite1 = new Sprite(colorTable1, generator1, 0, 0, 0);
		baseSprite1.colidx = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0}};
		baseSprite1.hull = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,10,0,0,10,0,10,10,10,10,0},
				{0,0,0,0,0,10,0,0,0,10,10,0},
				{0,0,10,0,0,0,0,0,0,10,10,0},
				{0,0,10,10,0,0,10,0,10,0,0,0},
				{0,10,0,10,0,0,10,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}};
		baseSprite1.pixels = new int[][]{{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
				{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793}};
		animatedSprite1 = new Sprite(colorTable1, generator1, 0, 0, 0);
		animatedSprite1.colidx = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,15,0,0,12,0,12,12,15,6,0},
				{0,0,0,0,0,12,0,0,0,15,8,0},
				{0,0,9,0,0,0,0,0,0,6,17,0},
				{0,0,12,12,0,0,6,0,15,0,0,0},
				{0,9,0,12,0,0,6,0,0,0,0,0},
				{0,9,0,12,0,0,6,0,0,0,0,0},
				{0,0,15,14,0,0,6,0,15,0,0,0},
				{0,0,9,0,0,0,0,0,0,15,15,0},
				{0,0,0,0,0,12,0,0,0,15,8,0},
				{0,0,15,0,0,12,0,12,12,17,8,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}};
		animatedSprite1.hull = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,10,0,0,10,0,10,10,10,10,0},
				{0,0,0,0,0,10,0,0,0,10,10,0},
				{0,0,10,0,0,0,0,0,0,10,10,0},
				{0,0,10,10,0,0,10,0,10,0,0,0},
				{0,10,0,10,0,0,10,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}};
		animatedSprite1.pixels = new int[][]{{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,0,16777215,0,0,16769056,0,16769056,16769056,16777215,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
				{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
				{65793,0,16769056,16769056,0,0,12624000,0,16777215,0,0,65793},
				{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
				{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
				{65793,0,16777215,15769600,0,0,12624000,0,16777215,0,0,65793},
				{65793,0,16740464,0,65793,0,0,65793,0,16777215,16777215,0},
				{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
				{65793,0,16777215,0,0,16769056,0,16769056,16769056,8421504,5255184,0},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793},
				{65793,65793,0,16777215,0,0,16769056,16769056,16777215,12624000,0,65793},
				{65793,65793,0,0,65793,0,16769056,0,16777215,5255184,0,65793},
				{65793,0,16740464,0,65793,65793,0,0,12624000,8421504,0,65793},
				{65793,0,16769056,16769056,0,0,12624000,16777215,0,0,65793,65793},
				{0,16740464,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
				{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
				{65793,0,16777215,15769600,0,0,12624000,0,16777215,0,0,65793},
				{65793,0,16740464,0,65793,0,0,65793,0,16777215,16777215,0},
				{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
				{65793,0,16777215,0,0,16769056,0,16769056,16769056,8421504,5255184,0},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,0,16777215,0,0,16769056,0,16769056,16769056,16777215,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
				{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
				{65793,0,16769056,16769056,0,0,12624000,0,16777215,0,0,65793},
				{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
				{0,16740464,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
				{65793,0,16777215,15769600,0,0,12624000,16777215,0,0,65793,65793},
				{65793,0,16740464,0,65793,65793,0,0,16777215,16777215,0,65793},
				{65793,65793,0,0,65793,0,16769056,0,16777215,5255184,0,65793},
				{65793,65793,0,16777215,0,0,16769056,16769056,8421504,5255184,0,65793},
				{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793}};
		animatedSprite2 = new Sprite(colorTable1, generator1, 0, 0, 0);
		animatedSprite2.colidx = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,12,0,0,15,0,15,6,6,6,0},
				{0,0,0,0,0,12,0,0,0,6,6,0},
				{0,0,9,0,0,0,0,0,0,12,6,0},
				{0,0,15,6,0,0,15,0,12,0,0,0},
				{0,6,0,15,0,0,15,0,0,0,0,0},
				{0,6,0,15,0,0,15,0,0,0,0,0},
				{0,0,15,6,0,0,15,0,12,0,0,0},
				{0,0,9,0,0,0,0,0,0,12,6,0},
				{0,0,0,0,0,12,0,0,0,6,6,0},
				{0,0,12,0,0,15,0,15,6,6,6,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}};
		animatedSprite2.hull = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,10,0,0,10,0,10,10,10,10,0},
				{0,0,0,0,0,10,0,0,0,10,10,0},
				{0,0,10,0,0,0,0,0,0,10,10,0},
				{0,0,10,10,0,0,10,0,10,0,0,0},
				{0,10,0,10,0,0,10,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0}};
		animatedSprite2.pixels = new int[][]{{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
				{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
				{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
				{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
				{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
				{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
				{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
				{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793},
				{65793,65793,0,16769056,0,0,16777215,12624000,12624000,12624000,0,65793},
				{65793,65793,0,0,65793,0,16769056,0,12624000,12624000,0,65793},
				{65793,0,16740464,0,65793,65793,0,0,16769056,12624000,0,65793},
				{65793,0,16777215,12624000,0,0,16777215,16769056,0,0,65793,65793},
				{0,12624000,0,16777215,0,0,16777215,0,65793,65793,65793,65793},
				{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
				{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
				{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
				{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
				{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
				{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
				{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
				{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
				{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
				{0,12624000,0,16777215,0,0,16777215,0,65793,65793,65793,65793},
				{65793,0,16777215,12624000,0,0,16777215,16769056,0,0,65793,65793},
				{65793,0,16740464,0,65793,65793,0,0,16769056,12624000,0,65793},
				{65793,65793,0,0,65793,0,16769056,0,12624000,12624000,0,65793},
				{65793,65793,0,16769056,0,0,16777215,12624000,12624000,12624000,0,65793},
				{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793}};
	}
	
	@Test
	public void test() throws Exception {
		spriteFunctionsTest(generator1,colorTable1,baseSprite1);
		createSpriteTest(generator1,colorTable1);
		mergeSpriteTest(generator1,animatedSprite1,animatedSprite2,0.8);
	}
	
	private void createSpriteTest(SpriteGenerator gen, int[] coltable) throws Exception {
		SpriteGenerator.fixRandom(1000);
		Sprite spr = gen.createSprite(coltable);
		assertArrayEquals("Created sprite does not match the expected.", getCreateSpriteTable(gen, coltable), spr.pixels);
	}
	
	private void mergeSpriteTest(SpriteGenerator gen, Sprite spr1, Sprite spr2, double weight) throws Exception {
		SpriteGenerator.fixRandom(1000);
		Sprite spr = gen.mergeSprites(spr1, spr2, weight);
		assertArrayEquals("Merged sprite does not match the expected.", getMergeSpriteTable(gen, spr1, spr2, weight), spr.pixels);
	}
	
	//mergeSprites
	// need their own function
	private void spriteFunctionsTest(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		colorize(gen,coltable,spr);
		flipAndShade(gen,coltable,spr);
		bevelShadeNew(gen,coltable,spr);
		gouraudShade(gen,coltable,spr);
		indexToRGB(gen,coltable,spr);
		animate(gen,coltable,spr);
		addOutlineRGB(gen,coltable,spr);
	}

	private void colorize(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		assertArrayEquals("Colorized sprite does not match the expected.", getColorizeTable(gen, coltable, spr), clone.colidx);
	}
	
	private void flipAndShade(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		assertArrayEquals("Flipped sprite does not match the expected.", getFlipTable(gen, coltable, spr), clone.colidx);
	}
	
	private void bevelShadeNew(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		SpriteGenerator.fixRandom(1000);
		gen.bevelShade(clone);
		assertArrayEquals("Bevel shaded sprite does not match the expected.", getBevelShadeTable(gen, coltable, spr), clone.colidx);
	}

	private void gouraudShade(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		SpriteGenerator.fixRandom(1000);
		gen.gouraudShade(clone);
		assertArrayEquals("Gouraud shaded sprite does not match the expected.", getGouraudShadeTable(gen, coltable, spr), clone.colidx);
	}

	private void indexToRGB(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		SpriteGenerator.fixRandom(1000);
		gen.bevelShade(clone);
		SpriteGenerator.fixRandom(1000);
		gen.indexToRGB(clone);
		assertArrayEquals("Colored sprite does not match the expected.", getIndexToRGBTable(gen, coltable, spr), clone.pixels);
	}

	private void animate(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		SpriteGenerator.fixRandom(1000);
		gen.bevelShade(clone);
		SpriteGenerator.fixRandom(1000);
		gen.indexToRGB(clone);
		SpriteGenerator.fixRandom(1000);
		gen.animate(clone);
		assertArrayEquals("Animated sprite does not match the expected.", getAnimateTable(gen, coltable, spr), clone.pixels);
	}

	private void addOutlineRGB(SpriteGenerator gen, int[] coltable, Sprite spr) throws Exception {
		Sprite clone = spr.clone();
		SpriteGenerator.fixRandom(1000);
		gen.colorize(clone);
		SpriteGenerator.fixRandom(1000);
		gen.flip(clone);
		SpriteGenerator.fixRandom(1000);
		gen.bevelShade(clone);
		SpriteGenerator.fixRandom(1000);
		gen.indexToRGB(clone);
		SpriteGenerator.fixRandom(1000);
		gen.animate(clone);
		gen.addOutlineRGB(clone);
		assertArrayEquals("Outline RGB sprite does not match the expected.", getAddOutlineRGBTable(gen, coltable, spr), clone.pixels);
	}
	
	private int[][] getColorizeTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,15,0,0,12,0,12,12,15,6,0},
					{0,0,0,0,0,12,0,0,0,6,6,0},
					{0,0,9,0,0,0,0,0,0,6,15,0},
					{0,0,12,12,0,0,6,0,15,0,0,0},
					{0,9,0,12,0,0,6,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getFlipTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,15,0,0,12,0,12,12,15,6,0},
					{0,0,0,0,0,12,0,0,0,6,6,0},
					{0,0,9,0,0,0,0,0,0,6,15,0},
					{0,0,12,12,0,0,6,0,15,0,0,0},
					{0,9,0,12,0,0,6,0,0,0,0,0},
					{0,9,0,12,0,0,6,0,0,0,0,0},
					{0,0,12,12,0,0,6,0,15,0,0,0},
					{0,0,9,0,0,0,0,0,0,6,15,0},
					{0,0,0,0,0,12,0,0,0,6,6,0},
					{0,0,15,0,0,12,0,12,12,15,6,0},
					{0,0,0,0,0,0,0,0,0,0,0,0}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getBevelShadeTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,15,0,0,12,0,12,12,15,6,0},
					{0,0,0,0,0,12,0,0,0,15,8,0},
					{0,0,9,0,0,0,0,0,0,6,17,0},
					{0,0,12,12,0,0,6,0,15,0,0,0},
					{0,9,0,12,0,0,6,0,0,0,0,0},
					{0,9,0,12,0,0,6,0,0,0,0,0},
					{0,0,15,14,0,0,6,0,15,0,0,0},
					{0,0,9,0,0,0,0,0,0,15,15,0},
					{0,0,0,0,0,12,0,0,0,15,8,0},
					{0,0,15,0,0,12,0,12,12,17,8,0},
					{0,0,0,0,0,0,0,0,0,0,0,0}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getGouraudShadeTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,15,0,0,14,0,14,14,17,8,0},
					{0,0,0,0,0,15,0,0,0,8,8,0},
					{0,0,15,0,0,0,0,0,0,8,17,0},
					{0,0,12,15,0,0,6,0,17,0,0,0},
					{0,9,0,12,0,0,15,0,0,0,0,0},
					{0,15,0,15,0,0,6,0,0,0,0,0},
					{0,0,15,12,0,0,15,0,15,0,0,0},
					{0,0,9,0,0,0,0,0,0,8,17,0},
					{0,0,0,0,0,14,0,0,0,8,8,0},
					{0,0,17,0,0,14,0,14,14,17,8,0},
					{0,0,0,0,0,0,0,0,0,0,0,0}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getIndexToRGBTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,16777215,12624000,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,12624000,8421504,65793},
					{65793,65793,16769056,16769056,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,65793,16777215,15769600,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,16777215,16777215,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,8421504,5255184,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getAnimateTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,16777215,12624000,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,12624000,8421504,65793},
					{65793,65793,16769056,16769056,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,65793,16777215,15769600,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,16777215,16777215,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,8421504,5255184,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,16777215,65793,65793,16769056,16769056,16777215,12624000,65793,65793},
					{65793,65793,65793,65793,65793,65793,16769056,65793,16777215,5255184,65793,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,12624000,8421504,65793,65793},
					{65793,65793,16769056,16769056,65793,65793,12624000,16777215,65793,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,65793,16777215,15769600,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,16777215,16777215,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,8421504,5255184,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793},
					{65793,65793,16777215,65793,65793,16769056,65793,16769056,16769056,16777215,12624000,65793},
					{65793,65793,65793,65793,65793,16769056,65793,65793,65793,16777215,5255184,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,65793,12624000,8421504,65793},
					{65793,65793,16769056,16769056,65793,65793,12624000,65793,16777215,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,16740464,65793,16769056,65793,65793,12624000,65793,65793,65793,65793,65793},
					{65793,65793,16777215,15769600,65793,65793,12624000,16777215,65793,65793,65793,65793},
					{65793,65793,16740464,65793,65793,65793,65793,65793,16777215,16777215,65793,65793},
					{65793,65793,65793,65793,65793,65793,16769056,65793,16777215,5255184,65793,65793},
					{65793,65793,65793,16777215,65793,65793,16769056,16769056,8421504,5255184,65793,65793},
					{65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793,65793}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getAddOutlineRGBTable(SpriteGenerator gen, 
			int[] coltable, 
			Sprite spr) throws Exception { 
		if(coltable == colorTable1 && gen == generator1 && spr == baseSprite1){
			return new int[][]{{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16777215,0,0,16769056,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,0,16769056,16769056,0,0,12624000,0,16777215,0,0,65793},
					{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{65793,0,16777215,15769600,0,0,12624000,0,16777215,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,16777215,16777215,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
					{65793,0,16777215,0,0,16769056,0,16769056,16769056,8421504,5255184,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793},
					{65793,65793,0,16777215,0,0,16769056,16769056,16777215,12624000,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,16777215,5255184,0,65793},
					{65793,0,16740464,0,65793,65793,0,0,12624000,8421504,0,65793},
					{65793,0,16769056,16769056,0,0,12624000,16777215,0,0,65793,65793},
					{0,16740464,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
					{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{65793,0,16777215,15769600,0,0,12624000,0,16777215,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,16777215,16777215,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
					{65793,0,16777215,0,0,16769056,0,16769056,16769056,8421504,5255184,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16777215,0,0,16769056,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,5255184,0},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,0,16769056,16769056,0,0,12624000,0,16777215,0,0,65793},
					{0,16740464,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{0,16740464,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
					{65793,0,16777215,15769600,0,0,12624000,16777215,0,0,65793,65793},
					{65793,0,16740464,0,65793,65793,0,0,16777215,16777215,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,16777215,5255184,0,65793},
					{65793,65793,0,16777215,0,0,16769056,16769056,8421504,5255184,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable+" "+spr);
	}
	
	private int[][] getCreateSpriteTable(SpriteGenerator gen, 
			int[] coltable) throws Exception { 
		if(coltable == colorTable1 && gen == generator1){
			return new int[][]{{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
					{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
					{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
					{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
					{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
					{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
					{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793},
					{65793,65793,0,16769056,0,0,16777215,12624000,12624000,12624000,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,12624000,12624000,0,65793},
					{65793,0,16740464,0,65793,65793,0,0,16769056,12624000,0,65793},
					{65793,0,16777215,12624000,0,0,16777215,16769056,0,0,65793,65793},
					{0,12624000,0,16777215,0,0,16777215,0,65793,65793,65793,65793},
					{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
					{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
					{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16769056,0,0,16777215,0,16777215,12624000,12624000,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,12624000,12624000,0},
					{65793,0,16740464,0,65793,0,0,65793,0,16769056,12624000,0},
					{65793,0,16777215,12624000,0,0,16777215,0,16769056,0,0,65793},
					{0,12624000,0,16777215,0,0,16777215,0,0,65793,65793,65793},
					{0,12624000,0,16777215,0,0,16777215,0,65793,65793,65793,65793},
					{65793,0,16777215,12624000,0,0,16777215,16769056,0,0,65793,65793},
					{65793,0,16740464,0,65793,65793,0,0,16769056,12624000,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,12624000,12624000,0,65793},
					{65793,65793,0,16769056,0,0,16777215,12624000,12624000,12624000,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+coltable);
	}
	
	private int[][] getMergeSpriteTable(SpriteGenerator gen, 
			Sprite spr1,
			Sprite spr2,
			double mergeRatio) throws Exception { 
		if(gen == generator1 && spr1 == animatedSprite1 && spr2 == animatedSprite2 && mergeRatio == 0.8){
			return new int[][]{{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16777215,0,0,16777215,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,12624000,0},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,0,16777215,16769056,0,0,12624000,0,16777215,0,0,65793},
					{0,12624000,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{0,12624000,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{65793,0,16777215,16769056,0,0,12624000,0,16777215,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,12624000,0},
					{65793,0,16777215,0,0,16777215,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793},
					{65793,65793,0,16777215,0,0,16777215,16769056,16777215,12624000,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,16777215,12624000,0,65793},
					{65793,0,16740464,0,65793,65793,0,0,12624000,8421504,0,65793},
					{65793,0,16777215,16769056,0,0,12624000,16777215,0,0,65793,65793},
					{0,12624000,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
					{0,12624000,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{65793,0,16777215,16769056,0,0,12624000,0,16777215,0,0,65793},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,12624000,0},
					{65793,0,16777215,0,0,16777215,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,65793,0,65793,65793,0,65793,0,0,0,0,65793},
					{65793,0,16777215,0,0,16777215,0,16769056,16769056,16777215,12624000,0},
					{65793,65793,0,65793,0,16769056,0,0,0,16777215,12624000,0},
					{65793,0,16740464,0,65793,0,0,65793,0,12624000,8421504,0},
					{65793,0,16777215,16769056,0,0,12624000,0,16777215,0,0,65793},
					{0,12624000,0,16769056,0,0,12624000,0,0,65793,65793,65793},
					{0,12624000,0,16769056,0,0,12624000,0,65793,65793,65793,65793},
					{65793,0,16777215,16769056,0,0,12624000,16777215,0,0,65793,65793},
					{65793,0,16740464,0,65793,65793,0,0,12624000,8421504,0,65793},
					{65793,65793,0,0,65793,0,16769056,0,16777215,12624000,0,65793},
					{65793,65793,0,16777215,0,0,16777215,16769056,16777215,12624000,0,65793},
					{65793,65793,65793,0,65793,65793,0,0,0,0,65793,65793}};
		}
		throw new Exception("Values not tabulated: "+gen+" "+spr1+" "+spr2+" "+mergeRatio);
	}
}
