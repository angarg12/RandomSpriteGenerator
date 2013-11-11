package examples.tools.spritegen.test;

import static org.junit.Assert.*;

import org.junit.Test;

import examples.tools.spritegen.color.ColorSchemeGenerator;

public class ColorSchemeGeneratorTest {	
	@Test
	public void test() {
		genSpriteColorSchemeTest(999,0,3,3);
		genSpriteColorSchemeTest(0,999,2,4);
		genSpriteColorSchemeTest(200,200,4,2);
	}
	
	private void genSpriteColorSchemeTest(int transparentColor, int blackColor, int colorCount, int shadesCount){
		ColorSchemeGenerator.fixRandom(1000);
		int[] colorScheme = ColorSchemeGenerator.genSpriteColorScheme(transparentColor,blackColor,colorCount,shadesCount);
		int[] testScheme = genSpriteColorSchemeTable(transparentColor,blackColor,colorCount,shadesCount);
		assertArrayEquals("Color array does not match the expected.", testScheme, colorScheme);
	}
	
	private int[] genSpriteColorSchemeTable(int transparentColor, int blackColor, int colorCount, int shadesCount){
		if(transparentColor == 999 && blackColor == 0 && colorCount == 3 && shadesCount == 3){
			return new int[] {999,999,999,0,0,0,13445598,13240542,11534530,14603525,12498432,10064384,47546,37781,28271,16777215,13092807,9342606};
		}
		if(transparentColor == 0 && blackColor == 999 && colorCount == 2 && shadesCount == 4){
			return new int[] {0,0,0,0,999,999,999,999,13445598,13308126,12648661,11010233,14603534,13814016,11972096,10130176,16777215,14277081,11842740,9342606};
		}
		if(transparentColor == 200 && blackColor == 200 && colorCount == 4 && shadesCount == 2){
			return new int[] {200,200,200,200,13445598,12648661,13616640,9932800,36753,22617,15401949,11993259,16777215,9342606};
		}
		return null;
	}
}
