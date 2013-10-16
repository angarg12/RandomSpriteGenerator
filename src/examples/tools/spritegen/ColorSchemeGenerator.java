package examples.tools.spritegen;

import java.util.Random;

public class ColorSchemeGenerator {
	// https://lotsacode.wordpress.com/2010/03/11/hsvtorgb-and-rgbtohsv-in-c/
	static Random random = new Random();
	public static void fixRandom(int seed){
		random = new Random(seed);
	}
	/**
	 * h,s,v between 0 and 1. XXX this algorithm has h=0-blue,
	 * h=0.3333333-green, h=0.5-yellow
	 **/
	public static void HSVtoRGB(double h, double s, double v, int[] arr, int ofs) {
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
		arr[ofs] = (int) (r * 255.49) + ((int) (g * 255.49) << 8)
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
		double darken = (0.3 + 0.2 * random.nextDouble()) / (shades - 1);
		for (int i = 0; i < shades; i++) {
			colorScheme[i] = transparent;
			colorScheme[i + shades] = black;
			// generates shades of gray starting from white
			HSVtoRGB(0, 0, 1.0 - darken * i, colorScheme, i + shades * (colors + 2));
		}
		double hshift=0;
		double svshift=0; 
		double s=0; 
		double h=0;
		boolean goodCombinationFound = false;
		while (goodCombinationFound == false) { // find good combination

			if (random.nextDouble() > 0.5) {
				hshift = random.nextDouble(); // random colours
			} else {
				hshift = 0.1 * random.nextDouble(); // tight colour key
			}
			svshift = 0.5 * random.nextDouble();
			s = 0.5 + 0.5 * random.nextDouble();
			h = random.nextDouble();
			// we want either big hshift or big svshift
			if ((hshift < 0.2 || hshift > 0.8) && svshift < 0.2)
				continue;

			// if we have low s, we want svshift to be high enough to
			// shift it to 1
			if (s + svshift < 1)
				continue;

			// reject some pervasive greens
			if (h >= 0.1666667 && h <= 0.4 && (hshift < 0.1 || hshift > 0.9)
					&& random.nextDouble() > 0.5)
				continue;

			goodCombinationFound = true;
		}
		
		svshift /= (shades - 1); // normalise over shades
		double shadeshift = (0.4 + 0.3 * random.nextDouble()) / (shades - 1);
		double v = 1;
		for (int c = 2; c < colors + 2; c++) {
			double ssh = s, vsh = v;
			for (int i = 0; i < shades; i++) {
				HSVtoRGB(h, ssh, vsh, colorScheme, i + shades * c);
				if (ssh < 1) {
					ssh += shadeshift;
					if (ssh > 1) {
						vsh -= ssh - 1;
						ssh = 1;
					}
				} else {
					vsh -= svshift;
				}
			}
			h += hshift;
			if (h > 1)
				h -= 1;
			if (s < 1) {
				s += svshift;
				if (s > 1) {
					v -= s - 1;
					s = 1;
				}
			} else {
				v -= svshift;
			}
		}
		return colorScheme;
	}

}
