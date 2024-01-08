package Maze.src;
import java.util.Random;

public class Main {

	int[][] map = new int[22][10];
	
	public void printing(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void engine(int tx, int ty, int hx, int hy, int ox1, int oy1, int ox2, int oy2, int ox3, int oy3) {
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

		int step = 0;
		int u = -1, d = 1, r = 1, l = -1;
		int dx, dy;
		
		// direction initialization
		if (tx < hx)
			dx = l;
		else 
			dx = r;
		
		if(ty < hy) 
			dy = u;
		else 
			dy = d;

		// Printing the matrix
		System.out.println("Printing the initial matrix");
		printing(map);
		
		while (tx != hx || ty != hy) { // until it reaches the target
			if((map[hy][hx + dx] != 3 && hx + dx <= map[0].length && hx + dx >= 0) || (map[hy + dy][hx] != 3 && hy + dy <= map.length && hy + dy >= 0)) {				
				while (hy != ty && hy + dy <= map.length && hy + dy >= 0 && map[hy + dy][hx] != 3) { // hy != ty, and the y path is clear
					hy += dy;
					step++;
					map[hy][hx] = 2;
				}
				
				while(hx != tx && hx + dx <= map[0].length && hx + dx >= 0 && map[hy][hx + dx] != 3) { // hx != tx, and the x path is clear
					hx += dx;
					step++;
					map[hy][hx] = 2;			
				}
				
				while(hy != ty && map[hy + dy][hx] == 3 && hy + dy >= 0 && hy + dy <= map.length) {
					if(map[hy][hx + dx] != 3) {
						while(map[hy][hx + dx] != 3 && hx + dx >= 0 && hx + dx <= map[0].length) {
							hx += dx;
							step++;
							map[hy][hx] = 2;
						}
					}
				}
				
				while(map[hy][hx + dx] == 3 && hx + dx >= 0 && hx + dx <= map[0].length) {
					if(map[hy + dy][hx] != 3) {
						while(map[hy + dy][hx] != 3 && hy + dy < map.length && hy + dy > 0) {
							hy += dy;
							step++;
							map[hy][hx] = 2;
						}					
					}
				}
				
				/*if(hx == tx && map[hy + dy][hx] == 3) {					
					if(map[hy][hx + dx] != 3) {						
						while(map[hy][hx + dx] != 3 && map[hy+dy][hx] == 3 && hx + dx > 0 && hx + dx < map[0].length) {
							hx += dx;
							step++;
							map[hy][hx] = 2;
						}
					}
					else {
						while((map[hy][hx - dx] != 3 && map[hy + dy][hx] == 3) && hx - dx >= 0 && hx - dx <= map[0].length) {
							hx -= dx;
							step++;
							map[hy][hx] = 2;
						}
					}
				}
				
				else if(hy == ty && map[hy][hx + dx] == 3) {					
					if(map[hy + dy][hx] != 3) {						
						while(map[hy + dy][hx] != 3 && map[hy][hx + dx] == 3 && hy + dy <= map.length && hy + dy >= 0) {
							hy += dy;
							step++;
							map[hy][hx] = 2;
						}
					}
					else {
						while(map[hy - dy][hx] != 3 && map[hy][hx + dx] == 3 && hy - dy >= 0 && hy - dy <= map.length) {
							hy -= dy;
							step++;
							map[hy][hx] = 2;
						}
					}
				} */
			}
			
			else {	
				if(tx > hx || map[hy][hx + 1] != 3 || hx + 1 < map[0].length || hx + 1 > 0) {
					dx = r;
				}
				else if(tx < hx || map[hy][hx - 1] != 3 || hx - 1 < map[0].length || hx - 1 > 0) {
					dx = l;
				}
				else if(ty > hy || map[hy + 1][hx] != 3 || hy + 1 > 0 || hy + 1 < map.length) {
					dy = d;
				}
				else if(ty > hy || map[hy - 1][hx] != 3 || hy - 1 > 0 || hy - 1 < map.length) {
					dy = u;
				}
			}			
		}

		// Printing the matrix
		System.out.println("\n===================\n");
		System.out.println("Printing the final matrix");
		printing(map);
		System.out.println("\nStep: " + step);
	}

	public static void main(String[] args) {
		Main engine = new Main();
		
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
		
		//engine.engine(tx, ty, hx, hy, ox1, oy1, ox2, oy2, ox3, oy3);
		engine.engine(8, 1, 4, 5, 5, 1, 6, 2, 7, 2);
	}

}