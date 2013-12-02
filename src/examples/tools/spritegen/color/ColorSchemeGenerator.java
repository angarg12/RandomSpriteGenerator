package examples.tools.spritegen.color;

import java.util.Random;

final public class ColorSchemeGenerator {
	private ColorSchemeGenerator(){};
	
	private static Random random = new Random();
	public static void fixRandom(int seed){
		random = new Random(seed);
	}
	
	// https://lotsacode.wordpress.com/2010/03/11/hsvtorgb-and-rgbtohsv-in-c/
	/**
	 * h,s,v between 0 and 1. XXX this algorithm has h=0-blue,
	 * h=0.3333333-green, h=0.5-yellow
	 **/
	private static int HSVtoRGB(double h, double s, double v) {
		double r = 0, g = 0, b = 0;
		if (s == 0) {
			r = v;
			g = v;
			b = v;
		} else {
			double varH = h * 6;
			int varI = (int) Math.floor(varH);
			double var1 = v * (1 - s);
			double var2 = v * (1 - (s * (varH - varI)));
			double var3 = v * (1 - (s * (1 - (varH - varI))));
			switch (varI) {
			case 0:
				r = v;
				g = var3;
				b = var1;
				break;
			case 1:
				r = var2;
				g = v;
				b = var1;
				break;
			case 2:
				r = var1;
				g = v;
				b = var3;
				break;
			case 3:
				r = var1;
				g = var2;
				b = v;
				break;
			case 4:
				r = var3;
				g = var1;
				b = v;
				break;
			case 5:
				r = v;
				g = var1;
				b = var2;
			}
		}
		return (int) (r * 255.49) + ((int) (g * 255.49) << 8)
				+ ((int) (b * 255.49) << 16);
	}

	/**
	 * generate a colors+3 x shades array of colours. 
	 * 0th subarray is transparent 
	 * 1st subarray is black 
	 * 2nd ... n+1th subarray are the colours
	 * n+2th subarray is white 
	 * each subarray is shades long
	 */
	public static int[] genSpriteColorScheme(
			int transparent, 
			int black,
			int colors, 
			int shades) {
		int[] colorScheme = new int[(colors + 3) * shades];
		initializeColorScheme(colorScheme, transparent, black, shades, colors);
		
		HSVColor color = new HSVColor(shades);
		color.findGoodCombination();
		
		// for each color
		for (int c = 2; c < colors + 2; c++) {
			color.resetShade();
			// for each shade
			for (int i = 0; i < shades; i++) {
				colorScheme[i + shades * c] = HSVtoRGB(color.getHue(), color.getShadeSaturation(), color.getShadeValue());
				color.shiftShade();
			}
			color.shiftHue();
			color.shiftSaturationAndValue();
		}
		return colorScheme;
	}
	
	private static void initializeColorScheme(int[] colorScheme, int transparent, int black, int shades, int colors){
		double darken = (0.3 + 0.2 * random.nextDouble()) / (shades - 1);
		for (int i = 0; i < shades; i++) {
			colorScheme[i] = transparent;
			colorScheme[i + shades] = black;
			// generates shades of gray starting from white
			colorScheme[i + shades * (colors + 2)] = HSVtoRGB(0, 0, 1.0 - darken * i);
		}
	}

	private static class HSVColor {
		double hue_shift = 0;
		double saturation_value_shift = 0;
		double shade_shift = 0;
		double saturation = 0; 
		double hue = 0;
		double value = 1;
		double shade_saturation = 0;
		double shade_value = 0;
		int shadeCount;
		
		public HSVColor(int shadeCount_){
			shadeCount = shadeCount_;
		}
		
		/**
		 * Finds a good combination of parameters to generate a Color scheme.
		 * @param shadesCount
		 */
		private void findGoodCombination(){
			boolean goodConfigurationFound = false;
			while (goodConfigurationFound == false) { // find good combination
				// half of the time generate random schemes
				// the other half generate closely related schemes
				if (random.nextDouble() > 0.5) {
					hue_shift = random.nextDouble(); // random colours
				} else {
					hue_shift = 0.2 * random.nextDouble(); // tight colour key
				}
				saturation_value_shift = 0.5 * random.nextDouble();
				saturation = 0.5 + 0.5 * random.nextDouble();
				hue = random.nextDouble();
				value = 0.8 + 0.2 * random.nextDouble();

				// If the hue_shift is extreme and the saturation_value_shift is small
				// the combination is bad
				if ((hue_shift < 0.2 || hue_shift > 0.8) && saturation_value_shift < 0.2){
					continue;
				}

				// if we have low saturation, we want saturation_value_shift
				// to be high enough to shift it to 1
				if (saturation + saturation_value_shift < 1){
					continue;
				}

				goodConfigurationFound = true;
			}
			saturation_value_shift /= shadeCount; // normalise over shades
			shade_shift = (0.4 + 0.3 * random.nextDouble()) / shadeCount;
		}
		
		/**
		 * Resets the value of the shade to the base color.
		 */
		public void resetShade(){
			shade_saturation = saturation;
			shade_value = value;
		}
		
		/**
		 * Shifts the saturation and value of the color.
		 */
		private void shiftSaturationAndValue(){
			if (saturation < 1) {
				saturation += saturation_value_shift;
				if (saturation > 1) {
					value -= saturation - 1;
					saturation = 1;
				}
			} else {
				value -= saturation_value_shift;
				// Notice that if the new value does not leave room for the new shades, they will underflow
				// That is why we recalculate the value on this case
				if(value < shade_shift*shadeCount){
					value = 0.8 + 0.2 * random.nextDouble();
					saturation = 0.5 + 0.5 * random.nextDouble();
				}
			}
		}
		
		/**
		 * Shifts the hue of the color.
		 */
		private void shiftHue(){
			hue += hue_shift;
			if (hue > 1){
				hue -= 1;
			}
		}
	
		/**
		 * Shifts the shade of the color.
		 */
		private void shiftShade(){
			if (shade_saturation < 1) {
				shade_saturation += shade_shift;
				if (shade_saturation > 1) {
					shade_value -= shade_saturation - 1;
					shade_saturation = 1;
				}
			} else {
				shade_value -= shade_shift;
				// If the value goes below 0, recalculate at random. This shouldn't happen given that shiftSaturationAndValue is correct
				// If it happens, the next shade will be lighter in color. And that is a problem if you assume that color are ordered
				// by brightness.
				if(shade_value < 0){
					shade_value = 0.8 + 0.2 * random.nextDouble();
					shade_saturation = 0.5 + 0.5 * random.nextDouble();
				}
			}
		}
		
		public double getShadeSaturation() {
			return shade_saturation;
		}
		
		public double getHue() {
			return hue;
		}
		
		public double getShadeValue() {
			return shade_value;
		}
	}
}
