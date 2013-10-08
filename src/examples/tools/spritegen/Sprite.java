package examples.tools.spritegen;
public class Sprite {
	// render parameters as part of instance
	int [] coltable;
	PixelArtGen gen;
	// output
	// xsize*ysize fill types
	int [][] hull;
	// xsize*ysize color indexes
	int [][] colidx;
	// xsize*ysize shade levels (0..2)
	//int [][] shades;
	// xsize*animsize*ysize colours
	int [][] pixels;
	public Sprite(int [] coltable,PixelArtGen gen,
	int xsize,int totalxsize,int ysize) {
		this.coltable = coltable;
		this.gen = gen;
		hull = new int[xsize][ysize];
		colidx = new int[xsize][ysize];
		pixels = PixelArtGen.createTransparentBitmap(totalxsize,ysize);
	}
	public int getWidth() {
		return pixels.length;
	}
	public int getHeight() {
		if (pixels.length==0) return 0;
		return pixels[0].length;
	}
	public int getNrFrames() {
		if (gen.animtable==null) return 1;
		return gen.animtable.length + 1;
	}
	public int[] getData(int frame) {
		int totalwidth=getWidth();
		int nrframes = getNrFrames();
		int width = totalwidth / nrframes;
		int [] ret= new int[width*getHeight()];
		for (int x=0; x<width; x++) {
			int [] pixcol = pixels[width*frame + x];
			for (int y=0; y<pixcol.length; y++) {
				int idx = x + width*y;
				if (pixcol[y]==PixelArtGen.transcolor) {
					ret[idx++] = 0;
				} else {
					ret[idx++] = 0xff000000 | pixcol[y];
				}
			}
		}
		return ret;
	}
	public int[] getHq2xData(int frame) {
		int [] inArr = getData(frame);
		int totalwidth=getWidth();
		int nrframes = getNrFrames();
		int width = totalwidth / nrframes;
		int [] outArr = Hq2x.Parse(inArr,width,getHeight());
		return outArr;
	}
}


