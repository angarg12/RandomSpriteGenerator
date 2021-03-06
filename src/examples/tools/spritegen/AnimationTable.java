package examples.tools.spritegen;

final class AnimationTable {
	private AnimationTable(){};
	
	private static final int A = 10;
	private static final int B = 11;
	private static final int C = 12;
	private static final int D = 13;
	private static final int E = 14;
	private static final int F = 15;
	private static final int G = 16;

	// 1 = 1x up
	// 2 = 1x up right
	// 3 = 1x right
	// 4 = 1x down right
	// 5 = 1x down
	// 6 = 1x down left
	// 7 = 1x left
	// 8 = 1x up left
	// 9 .. 16 = 2x
	//
	//       9
	//   G   1   A
	//    8  |  2
	//     \ | /
	// F 7--- ---3 B
	//     / | \
	//    6  |  4
	//   E   5   C
	//       D

	static int[][][] BEND_12 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 6, 6, 6, 7, 7, 7, 7, 7, F, F, F, F },
					{ 6, 6, 6, 7, 7, 7, 7, 7, 7, F, F, F },
					{ 0, 6, 6, 6, 7, 7, 7, 7, 7, 7, F, F },
					{ 0, 0, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7 },
					{ 0, 8, 8, 8, 7, 7, 7, 7, 7, 7, F, F },
					{ 8, 8, 8, 7, 7, 7, 7, 7, 7, F, F, F },
					{ 8, 8, 8, 7, 7, 7, 7, 7, F, F, F, F }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { B, B, B, B, 3, 3, 3, 3, 3, 4, 4, 4 },
					{ B, B, B, 3, 3, 3, 3, 3, 3, 4, 4, 4 },
					{ B, B, 3, 3, 3, 3, 3, 3, 4, 4, 4, 0 },
					{ 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 0, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 0, 0 },
					{ B, B, 3, 3, 3, 3, 3, 3, 2, 2, 2, 0 },
					{ B, B, B, 3, 3, 3, 3, 3, 2, 2, 2, 2 },
					{ B, B, B, B, 3, 3, 3, 3, 3, 2, 2, 2 }}};
	
	static int[][][] BIRD_16 = new int[][][] {
				  { { 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 }},
			      { { B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F },
					{ B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F }}};
	
	static int[][][] BIRD_18 = new int[][][] {
			      { { 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 }},
			      { { B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F },
					{ B, B, B, B, B, 3, 3, 0, 0, 0, 0, 7, 7, F, F, F, F, F }},
			      { { 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7 }}};
	
	static int[][][] BOUNCE_12 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 },
					{ 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { D, D, D, D, D, D, D, D, D, D, D, D },
					{ D, D, D, D, D, D, D, D, D, D, D, D },
					{ D, D, D, D, D, D, D, D, D, D, D, D },
					{ D, D, D, D, D, D, D, D, D, D, D, D },
					{ D, D, D, D, D, D, D, D, D, D, D, D },
					{ D, D, D, D, D, D, D, D, D, D, D, D },
					{ 5, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4, 5 },
					{ 5, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4, 5 },
					{ 5, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }}};
	
	static int[][][] BUBBLE_12 = new int[][][] {
			      { { 3, 2, 2, 2, 1, 1, 1, 1, 8, 8, 8, 7 },
					{ 3, 3, 2, 2, 1, 1, 1, 1, 8, 8, 7, 7 },
					{ 3, 3, 3, 2, 2, 1, 1, 8, 8, 7, 7, 7 },
					{ 3, 3, 3, 3, 2, 1, 1, 8, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 4, 5, 5, 6, 7, 7, 7, 7 },
					{ 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 7 },
					{ 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 7, 7 },
					{ 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4 },
					{ 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4 },
					{ 6, 6, 6, 5, 5, 5, 5, 5, 5, 4, 4, 4 },
					{ 7, 7, 6, 6, 0, 0, 0, 0, 4, 4, 3, 3 },
					{ 7, 7, 7, 7, 0, 0, 0, 0, 3, 3, 3, 3 },
					{ 7, 7, 7, 7, 0, 0, 0, 0, 3, 3, 3, 3 },
					{ 7, 7, 8, 8, 0, 0, 0, 0, 2, 2, 3, 3 },
					{ 8, 8, 8, 1, 1, 1, 1, 1, 1, 2, 2, 2 },
					{ 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2 },
					{ 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }}};

	static int[][][] CRAWL_12 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 7, 7, 7, 7, 7, 6, 6, 6, 6 },
					{ 0, 0, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6 },
					{ 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 6, 6 },
					{ 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0 },
					{ 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 2, 2, 2, 2, 3, 3, 3, 3, 3, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 4, 4, 4, 4, 3, 3, 3, 3, 3, 0, 0, 0 },
					{ 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0 },
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7 },
					{ 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8 },
					{ 0, 0, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8 },
					{ 0, 0, 0, 7, 7, 7, 7, 7, 8, 8, 8, 8 }}};

	static int[][][] CRAWL2_12 = rotateTable(CRAWL_12);

	static int[][][] FLY_10 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 }},
			      { { B, B, 3, 3, 0, 0, 7, 7, F, F },
					{ B, B, 3, 3, 0, 0, 7, 7, F, F },
					{ B, B, 3, 3, 0, 0, 7, 7, F, F },
					{ B, B, B, 3, 0, 0, 7, F, F, F },
					{ B, B, B, 3, 0, 0, 7, F, F, F },
					{ B, B, B, 3, 0, 0, 7, F, F, F },
					{ B, B, B, 3, 0, 0, 7, F, F, F },
					{ B, B, 3, 3, 0, 0, 7, 7, F, F },
					{ B, B, 3, 3, 0, 0, 7, 7, F, F },
					{ B, B, 3, 3, 0, 0, 7, 7, F, F }},
			      { { 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 7, 7, 7 }}};

	static int[][][] FLY_12 = new int[][][] {
			      { { 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
					{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
					{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 }},
		 	      { { 3, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 0 },
					{ 0, 0, 3, 3, 0, 0, 0, 0, 7, 7, 0, 0 },
					{ 0, 0, 3, 3, 0, 0, 0, 0, 7, 7, 0, 0 },
					{ 0, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7 }},
			      { { B, B, B, B, B, 3, 7, F, F, F, F, F },
					{ B, B, B, B, 3, 0, 0, 7, F, F, F, F },
					{ B, B, B, 3, 3, 0, 0, 7, 7, F, F, F },
					{ B, B, B, 3, 3, 0, 0, 7, 7, F, F, F },
					{ 3, B, B, 3, 3, 0, 0, 7, 7, F, F, 7 },
					{ 3, 3, B, 3, 3, 0, 0, 7, 7, F, 7, 7 },
					{ 3, 3, B, 3, 3, 0, 0, 7, 7, F, 7, 7 },
					{ 3, B, B, 3, 3, 0, 0, 7, 7, F, F, 7 },
					{ B, B, B, 3, 3, 0, 0, 7, 7, F, F, F },
					{ B, B, B, 3, 3, 0, 0, 7, 7, F, F, F },
					{ B, B, B, B, 3, 0, 0, 7, F, F, F, F },
					{ B, B, B, B, B, 3, 7, F, F, F, F, F }},
			      { { 3, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 0 },
					{ 0, 0, 3, 3, 0, 0, 0, 0, 7, 7, 0, 0 },
					{ 0, 0, 3, 3, 0, 0, 0, 0, 7, 7, 0, 0 },
					{ 0, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 0 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 0, 0, 7, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7 }}};

	static int[][][] MAN_16 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 }}};

	static int[][][] MAN_18 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
				  { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 }}};

	static int[][][] NULL_12 = new int[][][] { {
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }}};

	static int[][][] POKE_12 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 7, 7, 6, 6, 6 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6 },
					{ 0, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6 },
					{ 0, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 5 },
					{ 0, 0, 0, 0, 0, 0, 7, 6, 6, 6, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0 },
					{ 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0 },
					{ 1, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0 },
					{ 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0 },
					{ 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0 },
					{ 2, 2, 2, 3, 3, 0, 0, 0, 0, 0, 0, 0 }},
		          { { 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 4, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0 },
					{ 4, 4, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0 },
					{ 5, 4, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 7, 8, 8, 8, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 7, 8, 8, 8, 8, 1 },
					{ 0, 0, 0, 0, 0, 0, 7, 8, 8, 8, 8, 8 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 8, 8, 8, 8 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 8, 8, 8 }}};

	static int[][][] TURN_12 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8 },
					{ 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8 },
					{ 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8 },
					{ 6, 6, 6, 6, 6, 7, 7, 8, 8, 8, 8, 8 },
					{ 5, 5, 5, 6, 6, 7, 7, 8, 8, 1, 1, 1 },
					{ 5, 5, 5, 5, 5, 7, 7, 1, 1, 1, 1, 1 },
					{ 5, 5, 5, 5, 5, 3, 3, 1, 1, 1, 1, 1 },
					{ 5, 5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 1 },
					{ 4, 4, 4, 4, 4, 3, 3, 2, 2, 2, 2, 2 },
					{ 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2 },
					{ 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2 },
					{ 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2 }},
			      { { D, D, E, E, F, F, F, F, F, F, F, F },
					{ D, D, E, E, F, F, F, F, F, F, F, F },
					{ D, D, E, E, F, F, F, F, G, G, G, G },
					{ D, D, E, E, E, F, F, G, G, G, G, G },
					{ D, D, D, E, E, F, F, G, G, 9, 9, 9 },
					{ D, D, D, D, D, F, F, 9, 9, 9, 9, 9 },
					{ D, D, D, D, D, B, B, 9, 9, 9, 9, 9 },
					{ D, D, D, C, C, B, B, A, A, 9, 9, 9 },
					{ C, C, C, C, C, B, B, A, A, A, 9, 9 },
					{ C, C, C, C, B, B, B, B, A, A, 9, 9 },
					{ B, B, B, B, B, B, B, B, A, A, 9, 9 },
					{ B, B, B, B, B, B, B, B, A, A, 9, 9 }},
			      { { B, B, B, B, B, B, B, B, C, C, D, D },
					{ B, B, B, B, B, B, B, B, C, C, D, D },
					{ A, A, A, A, B, B, B, B, C, C, D, D },
					{ A, A, A, A, A, B, B, C, C, C, D, D },
					{ 9, 9, 9, A, A, B, B, C, C, D, D, D },
					{ 9, 9, 9, 9, 9, B, B, D, D, D, D, D },
					{ 9, 9, 9, 9, 9, F, F, D, D, D, D, D },
					{ 9, 9, 9, G, G, F, F, E, E, D, D, D },
					{ 9, 9, G, G, G, F, F, E, E, E, E, E },
					{ 9, 9, G, G, F, F, F, F, E, E, E, E },
					{ 9, 9, G, G, F, F, F, F, F, F, F, F },
					{ 9, 9, G, G, F, F, F, F, F, F, F, F }},
			      { { 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 },
					{ 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 },
					{ 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 },
					{ 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4 },
					{ 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5 },
					{ 1, 1, 1, 1, 1, 3, 3, 5, 5, 5, 5, 5 },
					{ 1, 1, 1, 1, 1, 7, 7, 5, 5, 5, 5, 5 },
					{ 1, 1, 1, 8, 8, 7, 7, 6, 6, 5, 5, 5 },
					{ 8, 8, 8, 8, 8, 7, 7, 6, 6, 6, 6, 6 },
					{ 8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6 },
					{ 8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6 },
					{ 8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6 }}};

	static int[][][] WALK_10 = new int[][][] {
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }}};

	static int[][][] WALK_12 = new int[][][] {
			      { { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }}};

	static int[][][] WALK2_10 = new int[][][] {
			      { { 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 7, 7, 7 }}};

	static int[][][] WALK2_12 = new int[][][] {
			      { { 3, 3, 3, 3, 3, 3, 3, 3, 7, 7, 7, 7 },
					{ 0, 3, 3, 3, 3, 3, 3, 3, 7, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 3, 3, 3, 3, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }},
			      { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 0, 0, 0, 3, 3, 3, 3, 0, 7, 7, 7, 7 },
					{ 0, 0, 3, 3, 3, 3, 3, 0, 7, 7, 7, 7 },
					{ 0, 3, 3, 3, 3, 3, 3, 3, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 3, 3, 3, 3, 7, 7, 7, 7 }}};

	static int[][][] WIGGLE_12 = new int[][][] {
		       	  { { 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F }},
			      { { 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 }},
			      { { B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 }},
			      { { B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
					{ B, 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, F, F },
					{ 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7, F },
					{ 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7, 7 },
					{ 3, 3, 3, 3, 0, 0, 0, 0, 0, 7, 7, 7 }}};

	static int[][][] WIGGLE2_12 = rotateTable(WIGGLE_12);


	static int[][][] NULL_16_8 = new int[][][] { {
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0 }}};
	
	private static int[][][] rotateTable(int[][][] table) {
		int frameCount = table.length;
		int lineCount = table[0].length;
		int columnCount = table[0][0].length;
		int[][][] rotated = new int[frameCount][columnCount][lineCount];
		for (int frame = 0; frame < frameCount; frame++) {
			for (int line = 0; line < lineCount; line++) {
				for (int column = 0; column < columnCount; column++) {
					int value = table[frame][line][column];
					// The value must be adjusted to rotate 90�
					// This condition checks the inner values (1 pixel distance)
					if (value >= 1 && value <= 8) {
						value += 2;
						if (value > 8){
							value -= 8;
						}
					}
					// The value must be adjusted to rotate 90�
					// This condition checks the outer values (2 pixel distance)
					if (value >= 9 && value <= G) {
						value += 2;
						if (value > G){
							value -= 8;
						}
					}
					rotated[frame][column][line] = value;
				}
			}
		}
		return rotated;
	}
	
	public static int distanceTraveled(int movement){
		if(movement == 0){
			return 0;
		}
		if(1 <= movement && movement <= 8){
			return 1;
		}
		if(8 <= movement && movement <= 16){
			return 2;
		}
		// TODO: BAD practice. If the value is not in range it should throw an error indicating
		// that the value is bad
		return -1;
	}
}
