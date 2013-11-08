package examples.tools.spritegen;

import examples.tools.spritegen.color.ColorScheme;

public class Sprite {
	// render parameters as part of instance
	public int[] coltable;
	SpriteGenerator gen;
	// output
	// xsize*ysize fill types
	public int[][] hull;
	// xsize*ysize color indexes
	public int[][] colidx;
	// xsize*ysize shade levels (0..2)
	// int [][] shades;
	// xsize*animsize*ysize colours
	public int[][] pixels;
	public int frames;

	public Sprite(int[] coltable, SpriteGenerator gen, int xsize, int totalxsize,
			int ysize, int frames) {
		this.coltable = coltable;
		this.gen = gen;
		hull = new int[xsize][ysize];
		colidx = new int[xsize][ysize];
		pixels = ImageUtils.createTransparentBitmap(totalxsize, ysize);
		this.frames = frames;
	}

	public int getWidth() {
		return pixels.length/getNrFrames();
	}

	public int getHeight() {
		if (pixels.length == 0){
			return 0;
		}
		return pixels[0].length;
	}

	public int getNrFrames() {
		return frames;
	}

	public int[] getData(int frame) {
		int nrframes = getNrFrames();
		int totalwidth = getWidth()*nrframes;
		int width = totalwidth / nrframes;
		int[] ret = new int[width * getHeight()];
		for (int x = 0; x < width; x++) {
			int[] pixcol = pixels[width * frame + x];
			for (int y = 0; y < pixcol.length; y++) {
				int idx = x + width * y;
				if (pixcol[y] == ColorScheme.TRANSPARENT) {
					ret[idx++] = 0;
				} else {
					ret[idx++] = 0xff000000 | pixcol[y];
				}
			}
		}
		return ret;
	}
	
	public Sprite clone(){
		Sprite spr = new Sprite(null,gen,0,0,0,0);
		spr.coltable = coltable.clone();
		spr.colidx = colidx.clone();
		spr.frames = frames;
		for(int i = 0; i < spr.colidx.length; i++){
			spr.colidx[i] = colidx[i].clone();
		}
		
		spr.hull = hull.clone();
		for(int i = 0; i < spr.hull.length; i++){
			spr.hull[i] = hull[i].clone();
		}
		
		spr.pixels = pixels.clone();
		for(int i = 0; i < spr.pixels.length; i++){
			spr.pixels[i] = pixels[i].clone();
		}
		return spr;
	}
}
