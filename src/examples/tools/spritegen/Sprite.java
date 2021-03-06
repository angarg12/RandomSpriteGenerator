package examples.tools.spritegen;

import examples.tools.spritegen.color.ColorScheme;

public class Sprite {
	// render parameters as part of instance
	public int[] coltable;
	public SpriteGenerator gen;
	// output
	// xsize*ysize fill types
	public int[][] hull;
	// xsize*ysize color indexes
	public int[][] colidx;
	// xsize*ysize shade levels (0..2)
	// int [][] shades;
	// xsize*animsize*ysize colours
	public int[][] pixels;
	private int frames;

	public Sprite(int[] coltable, SpriteGenerator gen, int xsize, int totalxsize,
			int ysize, int frames) {
		this.coltable = coltable;
		this.gen = gen;
		hull = new int[xsize][ysize];
		colidx = new int[xsize][ysize];
		pixels = ImageUtils.createTransparentBitmap(totalxsize, ysize);
		this.frames = frames;
	}
	
	public Sprite(Sprite spr){
        coltable = spr.coltable.clone();
        colidx = spr.colidx.clone();
        frames = spr.frames;
        for(int i = 0; i < spr.colidx.length; i++){
                colidx[i] = spr.colidx[i].clone();
        }
        
        hull = spr.hull.clone();
        for(int i = 0; i < spr.hull.length; i++){
                hull[i] = spr.hull[i].clone();
        }
        
        pixels = spr.pixels.clone();
        for(int i = 0; i < spr.pixels.length; i++){
                pixels[i] = spr.pixels[i].clone();
        }
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
	
	@Override
	public String toString(){
		String representation = "";
		representation += "color_table\n";
		for(int i = 0; i < coltable.length; i++){
			representation += coltable[i]+" ";
		}
		representation += "\n\n";
		representation += "hull\n";
		for(int i = 0; i < hull.length; i++){
			for(int j = 0; j < hull[i].length; j++){
				representation += hull[i][j]+" ";
			}
			representation += "\n";
		}
		representation += "\n";
		representation += "color_index\n";
		for(int i = 0; i < colidx.length; i++){
			for(int j = 0; j < colidx[i].length; j++){
				representation += colidx[i][j]+" ";
			}
			representation += "\n";
		}
		representation += "\n";
		representation += "pixels\n";
		for(int i = 0; i < pixels.length; i++){
			for(int j = 0; j < pixels[i].length; j++){
				representation += pixels[i][j]+" ";
			}
			representation += "\n";
		}
		representation += "\n";
		representation += "frames_count\n";
		representation += frames;
		return representation;
	}
}
