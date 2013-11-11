package examples.tools.spritegen.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import examples.tools.spritegen.SpriteGenerator;
import examples.tools.spritegen.Sprite;
import examples.tools.spritegen.SpriteGeneratorBuilder;

public class SpriteTest {	
	int[] colorTable1;
	int[] colorTable2;
	
	@Before
	public void setUp() throws Exception {
		colorTable1 = new int[]{0,0,0,0,999,999,999,999,12713793,11665168,9821696,9755648,8650559,6553358,5102592,5102080,16777215,14277081,11842740,9342606};
		colorTable2 = new int[]{200,200,200,200,12713793,7646208,8519484,3909376,4063031,369152,3342189,40237,16777215,9342606};
	}
	
	@Test
	public void test() throws Exception {
		spriteTest(colorTable1,5,15,5,3);
		spriteTest(colorTable2,5,15,5,3);
		spriteTest(colorTable1,5,25,5,5);
		spriteTest(colorTable1,10,30,5,3);
		spriteTest(colorTable1,5,15,10,3);
		spriteTest(colorTable1,7,21,12,3);
	}
	
	private void spriteTest(int [] coltable, 
			int xsize,
			int totalxsize,
			int ysize,
			int numberOfFrames) throws Exception {
		Sprite sprite = new Sprite(coltable, null, xsize, totalxsize, ysize, numberOfFrames);
		assertEquals("Width does not match the expected.",getWidthTable(coltable,xsize,totalxsize,ysize), sprite.getWidth());
		assertEquals("Height does not match the expected.",getHeightTable(coltable,xsize,totalxsize,ysize), sprite.getHeight());
		assertEquals("Number of frames does not match the expected.",getNrFramesTable(coltable,xsize,totalxsize,ysize), sprite.getNrFrames());
		for(int i = 0; i < sprite.getNrFrames(); i++){
			int[] frame = sprite.getData(i);
			assertArrayEquals("Frame array does not match the expected.", getDataTable(coltable,xsize,totalxsize,ysize,i), frame);
		}
	}
	
	private int getWidthTable(int [] coltable, 
			int xsize,
			int totalxsize,
			int ysize) throws Exception { 
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable2 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 25 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 10 && totalxsize == 30 && ysize == 5){
			return 10;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 10){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 7 && totalxsize == 21 && ysize == 12){
			return 7;
		}
		throw new Exception("Values not tabulated: "+coltable+" "+xsize+" "+totalxsize+" "+ysize);
	}	
	
	private int getHeightTable(int [] coltable, 
			int xsize,
			int totalxsize,
			int ysize) throws Exception { 
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable2 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 25 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 10 && totalxsize == 30 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 10){
			return 10;
		}
		if(coltable == colorTable1 && xsize == 7 && totalxsize == 21 && ysize == 12){
			return 12;
		}
		throw new Exception("Values not tabulated: "+coltable+" "+xsize+" "+totalxsize+" "+ysize);
	}
	
	private int getNrFramesTable(int [] coltable, 
			int xsize,
			int totalxsize,
			int ysize) throws Exception { 
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 3;
		}
		if(coltable == colorTable2 && xsize == 5 && totalxsize == 15 && ysize == 5){
			return 3;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 25 && ysize == 5){
			return 5;
		}
		if(coltable == colorTable1 && xsize == 10 && totalxsize == 30 && ysize == 5){
			return 3;
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 10){
			return 3;
		}
		if(coltable == colorTable1 && xsize == 7 && totalxsize == 21 && ysize == 12){
			return 3;
		}
		throw new Exception("Values not tabulated: "+coltable+" "+xsize+" "+totalxsize+" "+ysize);
	}	
	
	private int[] getDataTable(int [] coltable, 
			int xsize,
			int totalxsize,
			int ysize,
			int frame) throws Exception { 
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 5 && frame >= 0 && frame <= 2){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		if(coltable == colorTable2 && xsize == 5 && totalxsize == 15 && ysize == 5 && frame >= 0 && frame <= 2){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 25 && ysize == 5 && frame >= 0 && frame <= 4){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		if(coltable == colorTable1 && xsize == 10 && totalxsize == 30 && ysize == 5 && frame >= 0 && frame <= 2){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		if(coltable == colorTable1 && xsize == 5 && totalxsize == 15 && ysize == 10 && frame >= 0 && frame <= 2){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		if(coltable == colorTable1 && xsize == 7 && totalxsize == 21 && ysize == 12 && frame >= 0 && frame <= 2){
			return new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		}
		throw new Exception("Values not tabulated: "+coltable+" "+xsize+" "+totalxsize+" "+ysize+" "+frame);
	}	
}
