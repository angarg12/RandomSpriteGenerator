package examples.tools.spritegen.color;

public class ColorScheme {
	public static int TRANSPARENT = 0x010101;

	public static int[] BROWN_RED_YELLOW = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0xC0A080, 0x806040, 0x503010, // col2
			0xFF7070, 0xD04040, 0xB02020, // col1
			0xFFE020, 0xFFB000, 0xF0A000, // col3
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] GRAY_BLUE_TEAL = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0x808080, 0x505050, 0x202020, // col2
			0x9090FF, 0x6060F0, 0x4040E0, // col1
			0x20E0FF, 0x00B0FF, 0x00A0F0, // col3
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] GREEN = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0xA0C080, 0x608040, 0x305010, // col2
			0x70FF70, 0x40D040, 0x20B020, // col1
			0xE0FF20, 0xB0FF00, 0xA0F000, // col3
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] PURPLE_RED = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0x907090, 0x604060, 0x301030, // col2
			0xE020E0, 0xB000B0, 0xA000A0, // col3
			0xFF9090, 0xF06060, 0xE04040, // col1
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] BLUE_PURPLE = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0xA080C0, 0x604080, 0x301050, // col2
			0x7070FF, 0x4040D0, 0x2020B0, // col1
			0xE020FF, 0xB000FF, 0xA000F0, // col3
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] BLUE_GREEN = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0x80A0C0, 0x507090, 0x204060, // col2
			0x20D0F0, 0x00B0D0, 0x0090B0, // col3
			0x50FF50, 0x30F030, 0x10E010, // col1
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] RED_YELLOW = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0xFF0000, 0xD00000, 0xA00000, // col
			0xFF0000, 0xD00000, 0xA00000, // col
			0xFFD000, 0xD0B000, 0xA08000, // col
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[] BLUE = new int[] { 
			TRANSPARENT, TRANSPARENT, TRANSPARENT, // trans
			0x000000, 0x000000, 0x000000, // outline
			0x0000FF, 0x0000D0, 0x0000A0, // col
			0x0000FF, 0x0000D0, 0x0000A0, // col
			0x9090FF, 0x6868D0, 0x4040A0, // col
			0xFFFFFF, 0xB0B0B0, 0x808080, // highlight
	};

	public static int[][] colorSchemes() {
		int[][] schemes = new int[8][];
		schemes[0] = BLUE;
		schemes[1] = BLUE_GREEN;
		schemes[2] = BLUE_PURPLE;
		schemes[3] = BROWN_RED_YELLOW;
		schemes[4] = GRAY_BLUE_TEAL;
		schemes[5] = GREEN;
		schemes[6] = PURPLE_RED;
		schemes[7] = RED_YELLOW;
		
		return schemes;
	}
}
