package examples.tools.spritegen;

import java.io.*;

//import java.util.zip.CRC32;

public class ImageTools {


private static byte[] pngHeader = new byte[] {
(byte)137,(byte)80,(byte)78,(byte)71,(byte)13,(byte)10,(byte)26,(byte)10};

private static int[] pngIHDR = new int[] {73, 72, 68, 82};

private static int[] pngIDAT = new int[] { 73, 68, 65, 84};

private static int[] pngIEND = new int[] {73, 69, 78, 68};

public static void writePNG(String filename,int[][] pix,int width,int height,
int transcolor) {
	try {
		byte [] buf = new byte[256+4*width*height];
		FileOutputStream out = new FileOutputStream(filename);
		out.write(pngHeader);
		int ofs=0;
		//ofs=writeInt(buf,ofs,0x0d);
		ofs=writeBytes(buf,ofs,pngIHDR);
		ofs=writeInt(buf,ofs,width);
		ofs=writeInt(buf,ofs,height);
		ofs=writeByte(buf,ofs,8);  // bit depth
		ofs=writeByte(buf,ofs,6); // rgba
		ofs=writeByte(buf,ofs,0); // compression: zlib
		ofs=writeByte(buf,ofs,0); // filter
		ofs=writeByte(buf,ofs,0); // interlace: no
		writePNGChunk(out,buf,ofs);
		ofs=0;
		//ofs=writeInt(buf,ofs,width*height*4 + /*zlibhdr*/3 + /*zlibcrc*/4);
		ofs=writeBytes(buf,ofs,pngIDAT);
		// ZLIB header
		int tmp = 8;       
		ofs=writeByte(buf,ofs,tmp);    // CM = 8, CMINFO = 0
		//FCHECK (FDICT/FLEVEL=0)
		ofs=writeByte(buf,ofs,(31 - ((tmp << 8) % 31)) % 31);
		// ZLIB chunk header (we assume chunk < 65535 bytes
		// Final flag, Compression type 0
		boolean last=true;
		ofs=writeByte(buf,ofs,(last ? 1 : 0));
		int len=height + width*height*4;
		ofs=writeByte(buf,ofs,(len & 0xFF));           // Length LSB
		ofs=writeByte(buf,ofs,((len & 0xFF00) >> 8));  // Length MSB
		ofs=writeByte(buf,ofs,(~len & 0xFF));          // ~length
		ofs=writeByte(buf,ofs,((~len & 0xFF00) >> 8)); // ~length
		int zlibofs = ofs;
		//ofs=writeShortLE(buf,ofs,width*height*4);
		//ofs=writeShortLE(buf,ofs,0xffff ^ (width*height*4) );
		for (int y=0; y<height; y++) {
			ofs = writeByte(buf,ofs,0); // filter for scanline: none
			for (int x=0; x<width; x++) {
				ofs=writeByte(buf,ofs, (pix[x][y]>>16)&0xff);
				ofs=writeByte(buf,ofs, (pix[x][y]>>8)&0xff);
				ofs=writeByte(buf,ofs, (pix[x][y]   )&0xff);
				ofs=writeByte(buf,ofs, 
					(pix[x][y]&0xffffff) == transcolor ? 0 : 0xff);
				//ofs=writeByte(buf,ofs, (pix[x][y]>>24)&0xff); // alpha
				//ofs=writeInt(buf,ofs,pix[x][y]|0xff000000);
				//ofs=writeInt(buf,ofs,0xffffffff);
			}
		}
		// ZLIB CRC
		ofs=writeInt(buf,ofs,calcADLER32(buf,zlibofs,ofs-zlibofs));
		// PNG chunck CRC
		//ofs=writeInt(buf,ofs,calcCRC(buf,ofs-width*height*4,width*height*4));
		writePNGChunk(out,buf,ofs);
		ofs=0;
		ofs=writeBytes(buf,ofs,pngIEND);
		writePNGChunk(out,buf,ofs);
		out.close();
	} catch (Exception e){//Catch exception if any
		System.err.println("Error writing PNG: " + e.getMessage());
	}
}

private static void writePNGChunk(OutputStream out,
byte[] data,int len) throws IOException {
	byte[] buf = new byte[4];
	writeInt(buf,0,len-4);
	out.write(buf,0,4);
	out.write(data,0,len);
	writeInt(buf, 0, calcCRC(data,0,len) );
	out.write(buf,0,4);
}

private static int writeBytes(byte[]buf,int ofs,int[] data) {
	for (int i=0; i<data.length; i++) {
		buf[ofs++] = (byte)(data[i]&0xff);
	}
	return ofs;
}

private static int writeInt(byte[]buf,int ofs,int val) {
	buf[ofs++] = (byte)((val>>24)&0xff);
	buf[ofs++] = (byte)((val>>16)&0xff);
	buf[ofs++] = (byte)((val>> 8)&0xff);
	buf[ofs++] = (byte)( val     &0xff);
	return ofs;
}

private static int writeShortLE(byte[]buf,int ofs,int val) {
	buf[ofs++] = (byte)( val     &0xff);
	buf[ofs++] = (byte)((val>> 8)&0xff);
	return ofs;
}

private static int writeByte(byte[]buf,int ofs,int val) {
	buf[ofs++] =  (byte)(val     &0xff);
	return ofs;
}


private static int calcADLER32(byte[]buf,int ofs,int len) {
	int s1 = 1;
	int s2 = 0;
	for (int i = ofs; i < ofs+len; i++) {
		int abs = buf[i] >=0 ? buf[i] : (buf[i] + 256);
		s1 = (s1 + abs) % 65521;
		s2 = (s2 + s1) % 65521;
	}
	return (s2 << 16) + s1;
}

//http://stackoverflow.com/questions/11590075/crc32-calculation-with-pycrc-and-php-code-doesnt-match-expected-result
//http://stackoverflow.com/questions/2587766/how-is-a-crc32-checksum-calculated

/* Table of CRCs of all 8-bit messages. */
static private long []crc_table = new long[256];
	
/* Flag: has the table been computed? Initially false. */
static private boolean crc_table_computed = false;

// table appears correct, see:
//http://docs.factorcode.org/content/word-crc32-table,checksums.crc32.html
/** Make a table for a fast CRC. */
private static void makeCRCTable() {
	long c; // Is int; we use long to ensure calculations are unsigned
	for (int n = 0; n < 256; n++) {
		c = (long) n;
		for (int k = 0; k < 8; k++) {
			if ((c & 1) != 0)
				c = /*0x04C11DB7L*/ 0xedb88320L ^ (c >> 1);
			else
				c = c >> 1;
		}
		crc_table[n] = c;
		//System.out.println(Integer.toHexString((int)c));

	}
	crc_table_computed = true;
}
 
/** Update a running CRC with the bytes buf[ofs..ofs+len-1]. The CRC
 * should be initialized to all 1's, and the transmitted value
 * is the 1's complement of the final running CRC (see the
 * crc() routine below).
 * */
public static int updateCRC(int crc, byte [] buf, int ofs, int len) {
	long c = crc;
	c &= 0xffffffffL; // ensure value remains unsigned
	if (!crc_table_computed)
		makeCRCTable();
	for (int n = ofs; n < ofs+len; n++) {
		c = crc_table[(int)(c ^ buf[n]) & 0xff] ^ (c >> 8);
	}
	return (int)c;
}
	
/** Return the CRC of the bytes buf[ofs..ofs+len-1]. */
public static int calcCRC(byte [] buf, int ofs,int len) {
	return updateCRC(0xffffffff, buf, ofs,len) ^ 0xffffffff;
}

/*public static int calcCRC2(byte [] buf, int ofs,int len) {
	CRC32 crc = new CRC32();
	crc.update(buf,ofs,len);
	int otherv = calcCRC(buf,ofs,len);
	int thisv = (int)crc.getValue();
	System.out.println(Integer.toHexString(thisv));
	System.out.println(Integer.toHexString(otherv));
	return thisv;
}*/


public static void writePPM(String filename,int[][] pix,int width,int height) {
	try {
		// Create file 
		FileWriter fstream = new FileWriter(filename);
		PrintWriter out = new PrintWriter(fstream);
		out.println("P3\n"+width+" "+height+"\n255");
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				int p = pix[x][y];
				int b = p&255;
				int g = (p>>8)&255;
				int r = (p>>16)&255;
				out.print(r+" ");
				out.print(g+" ");
				out.print(b+" ");
			}
			out.println();
		}
		//Close the output stream
		out.close();
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
}
public static void writePAM(String filename,int[][] pix,int width,int height) {
	try {
		// Create file 
		FileOutputStream out = new FileOutputStream(filename);
		StringBuffer hdr = new StringBuffer();
		hdr.append("P7\n");
		hdr.append("WIDTH "+width+"\n");
		hdr.append("HEIGHT "+height+"\n");
		hdr.append("DEPTH 4\n");
		hdr.append("MAXVAL 255\n");
		hdr.append("TUPLTYPE RGBA\n");
		hdr.append("ENDHDR\n");
		byte[] byteArray = hdr.toString().getBytes("US-ASCII");
		out.write(byteArray);
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				int p = pix[x][y];
				int b = p&255;
				int g = (p>>8)&255;
				int r = (p>>16)&255;
				int a = (p>>24)&255;
				out.write(r);
				out.write(g);
				out.write(b);
				out.write(a);
			}
		}
		//Close the output stream
		out.close();
	} catch (Exception e){//Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
}


}
