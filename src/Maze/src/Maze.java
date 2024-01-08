package Maze.src;
import java.util.Random;

public class Maze {

	int[][] map = new int[22][10];
	int[][] new_map = map.clone();
	
	public void printing() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int recur(int hx, int hy) {
		int step = 0x0, temp;
		
		if(hx > map[0].length || hx < 0)
			return 0x0;
		if(hy > map.length || hy < 0)
			return 0x0;
		
		if(map[hy][hx] == 4)
			step = 1;
		
		if(hx > 0 && map[hy][hx] == 0) {
			temp = recur(hx - 1, hy) + 1;
			if (temp <= step || step == 0x0) step = temp;
		}
		if(hx < map[0].length - 1 && map[hy][hx] == 0) {
			temp = recur(hx + 1, hy) + 1;
			if (temp <= step || step == 0x0) step = temp;
		}
		if(hy > 0 && map[hy][hx] == 0) {
			temp =  recur(hx, hy - 1) + 1;
			if (temp <= step || step == 0x0) step = temp;			
		}
		if(hy < map.length - 1 && map[hy][hx] == 0) {
			temp = recur(hx, hy + 1) + 1;
			if (temp <= step || step == 0x0) step = temp;			
		}
		
		return step;
	}
	
	public void make_map(int tx, int ty, int hx, int hy, int ox1, int oy1, int ox2, int oy2, int ox3, int oy3) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
			}
		}
		
		map[ty][tx] = 4; // target
		map[hy][hx] = 1; // worm head
		map[oy1][ox1] = 3;
		map[oy2][ox2] = 3;
		map[oy3][ox3] = 3;
		
		// Printing the matrix
		System.out.println("Printing the initial matrix");
		printing();
	}

	public static void main(String[] args) {
		Maze eng = new Maze();
		
		Random rand = new Random();
		int tx = rand.nextInt(0, 10), ty = rand.nextInt(0, 22);
		int hx, hy;
		do {
			hx = rand.nextInt(0, 10);
			hy = rand.nextInt(0, 22);
		} while (hy == ty && hx == tx);
		
		int ox1, oy1, ox2, oy2, ox3, oy3;
		do {
			ox1 = rand.nextInt(0, 10);
			oy1 = rand.nextInt(0, 20);
		} while ((oy1 == ty || oy1 == hy) && (ox1 == tx || ox1 == hx));
		
		do {
			ox2 = rand.nextInt(0, 10);
			oy2 = rand.nextInt(0, 20);
		} while ((oy2 == ty || oy2 == hy) && (ox2 == tx || ox2 == hx));
		
		do {
			ox3 = rand.nextInt(0, 10);
			oy3 = rand.nextInt(0, 20);
		} while ((oy3 == ty || oy3 == hy) && (ox3 == tx || ox3 == hx));
		
		eng.make_map(8, 1, 4, 5, 5, 1, 6, 2, 7, 2);
		int st = eng.recur(4, 5);
		
		System.out.println("\n===================\n");
		System.out.println("Printing the final matrix");
		eng.printing();
		System.out.println("\nStep: " + st);
		//engine.make_map(tx, ty, hx, hy, ox1, oy1, ox2, oy2, ox3, oy3);
		
		//engine.engine(tx, ty, hx, hy, ox1, oy1, ox2, oy2, ox3, oy3);
		//engine.engine(8, 1, 4, 5, 5, 1, 6, 2, 7, 2);
		
	}

}