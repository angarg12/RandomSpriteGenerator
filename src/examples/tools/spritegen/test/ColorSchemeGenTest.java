package examples.tools.spritegen.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import examples.tools.spritegen.ColorSchemeGenerator;

public class ColorSchemeGenTest {	
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
		assertArrayEquals("Color array does not match the expected.", colorScheme, testScheme);
	}
	
	private int[] genSpriteColorSchemeTable(int transparentColor, int blackColor, int colorCount, int shadesCount){
		if(transparentColor == 999 && blackColor == 0 && colorCount == 3 && shadesCount == 3){
			return new int[] {999,999,999,0,0,0,12713793,10941952,10810368,8585023,5698560,5632256,4390716,520448,519936,16777215,13092807,9342606};
		}
		if(transparentColor == 0 && blackColor == 999 && colorCount == 2 && shadesCount == 4){
			return new int[] {0,0,0,0,999,999,999,999,12713793,11665168,9821696,9755648,8650559,6553358,5102592,5102080,16777215,14277081,11842740,9342606};
		}
		if(transparentColor == 200 && blackColor == 200 && colorCount == 4 && shadesCount == 2){
			return new int[] {200,200,200,200,12713793,7646208,8519484,3909376,4063031,369152,3342189,40237,16777215,9342606};
		}
		return null;
	}
}
