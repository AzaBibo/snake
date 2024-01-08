package Maze.src;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Queue_Try {
	
	private int x; 
	private int y;
	private int step_count;
	private Queue_Try prev;   
	
	public Queue_Try(int y, int x, int step_count, Queue_Try prev) {
		this.x = x;
		this.y = y;
		this.step_count = step_count;
		this.prev = prev;
	}
	
	public Queue_Try(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public Queue_Try() {}
	
	int[][] map = new int[22][10];
	int step = 0;
	
	public static void printing(int[][] map) {
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
		
		System.out.println("Printing the initial matrix");
		printing(map);

		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
		Queue<Queue_Try> q = new LinkedList<>();
		Queue<Queue_Try> prevs = new LinkedList<>();
		Queue_Try the_best = new Queue_Try();
		
		q.add(new Queue_Try(hy, hx, 0, new Queue_Try()));
		prevs.add(new Queue_Try(hy, hx));
		
		while(!q.isEmpty()) {
			Queue_Try temp = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Queue_Try current = new Queue_Try(temp.y + dy[i], temp.x + dx[i], temp.step_count + 1, temp);		
				Queue_Try cc = new Queue_Try(temp.y + dy[i], temp.x + dx[i]);
				if (!prevs.contains(cc) && cc.x >= 0 && cc.x <= 9 && cc.y >= 0 && cc.y <= 21 && map[cc.y][cc.x] != 3) {
					if(map[current.y][current.x] == 4) {
						step = current.step_count;
						the_best = current;
						prevs.add(cc);
					}
					else {
						q.add(current);
						prevs.add(cc);						
						}
				}
				else if(prevs.contains(cc) && cc.y == ty && cc.x == tx) {
					if(the_best.step_count > current.step_count) { 
						step = current.step_count;
						the_best = current;
					}
				}			
			}
		}
		
		while(map[the_best.y][the_best.x] != 1) {
			map[the_best.y][the_best.x] = 2;
			the_best = the_best.prev;
		}
	}

	public static void main(String[] args) {
		Queue_Try engine = new Queue_Try();
		
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
		
		// Printing the matrix
		System.out.println("\n===================\n");
		System.out.println("Printing the final matrix");
		printing(engine.map);
		System.out.println("\nStep: " + engine.step);
	}

}
