package main;

import java.util.ArrayList;
import java.util.Random;

import model.Coords;
import model.Maze;

public class MazeGenerator {
	
	private Random rand = new Random();
	private final int WIDTH = 21;
	private final int HEIGHT = 21;
	
	private Maze instance = Maze.getInstance();
	private int[][] maze;
	
	public void generateMaze() {
		maze = new int[21][21];
		maze = generate();
		instance.setPlayerPos(new Coords(0,0));
		instance.setMaze(maze);		
	}
	
	private int[][] generate() {
		ArrayList<Coords> visited = new ArrayList<>();
		ArrayList<Coords> toVisit = new ArrayList<>();
		
		maze[0][0] = 1;

		toVisit.add(new Coords(0,0));

		
		while (!toVisit.isEmpty()) {
			int randomIndex = rand.nextInt(toVisit.size());
			Coords next = toVisit.remove(randomIndex);
			
			boolean flag = true;
			for (Coords v : visited) {
				if (v.x == next.x && v.y == next.y) {
					flag = false; 
					break;
				}
				
			}
			
			if (!flag) continue;

			visited.add(next);
			connectPath(next.parent, next);
			
			
			ArrayList<Coords> neighbour = new ArrayList<>();
			int x = next.x;
			int y = next.y;
			
			
			if (x - 2 >= 0 && !visited.contains(new Coords(x-2, y, next)) && maze[y][x-2] == 0) {
				neighbour.add(new Coords(x-2,y, new Coords(x-1,y)));
			}
			
			if (y - 2 >= 0 && !visited.contains(new Coords(x, y-2, next)) && maze[y-2][x] == 0) {
				neighbour.add(new Coords(x,y-2, new Coords (x, y-1)));
			}
			
			if (x + 2 < WIDTH && !visited.contains(new Coords(x+2, y, next)) && maze[y][x+2] == 0) {
				neighbour.add(new Coords(x+2,y, new Coords(x+1, y)));

			}
			
			if (y + 2 < HEIGHT && !visited.contains(new Coords(x, y+2, next)) && maze[y+2][x] == 0) {
				neighbour.add(new Coords(x,y+2, new Coords(x, y+1)));
	
			}
			
			if(!neighbour.isEmpty()) {
				toVisit.addAll(neighbour);
			}
		}
		
		// Special Tiles
		// Player
		maze[0][0] = 10;
		
		//Goal
		maze[HEIGHT - 1][WIDTH - 1] = 20;
		
		// Coin
		for (int i=0; i<5; i++) {
			int randX = rand.nextInt(10) *2;
			int randY = rand.nextInt(10) * 2;
			
			if (maze[randY][randX] != 1) {
				i-=1;
				continue;
			} else {
				maze[randY][randX] = 2;
			}
		}
		
		
		// Trap
		for (int i=0; i<3; i++) {
			int randX = rand.nextInt(10) *2;
			int randY = rand.nextInt(10) * 2;
			
			if (maze[randY][randX] != 1) {
				i-=1;
				continue;
			} else {
				maze[randY][randX] = 3;
			}
		}
		
		
		return maze;
	}
	
	public void connectPath(Coords source, Coords dest) {
		
		if (source == null) return;
		
		maze[dest.y][dest.x] = 1;
		
		if (source.x == dest.x) {
			if (source.y > dest.y) {
				maze[dest.y + 1][dest.x] = 1;
			} 
			else if (source.y < dest.y) {
				maze[dest.y - 1][dest.x] = 1;
			}
			
		} else if (source.y == dest.y) {
			
			if (source.x > dest.x) {
				maze[dest.y][dest.x + 1] = 1;
			} 
			else if (source.x < dest.x) {
				maze[dest.y][dest.x - 1] = 1;
			}
			
		}
	}

}
