package model;

import main.MazeGenerator;

public class Maze {
	
	private static Maze _instance_ = null;
	private int[][] maze;
	private Coords playerPos;
	private int LIFE, LEVEL;
	private double TIME;
	
	private Maze() {
		// TODO Auto-generated constructor stub
		LIFE = 3;
		LEVEL = 1;
		TIME = 25.0;
		maze = new int[21][21];
		playerPos = new Coords(0,0);
	}
	
	public static Maze getInstance() {
		if (_instance_ == null) {
			synchronized (Maze.class) {
				if (_instance_ == null) {
					_instance_ = new Maze();
				}
			}
		}
		
		return _instance_;
	}

	public int[][] getMaze() {
		return maze;
	}

	public void setMaze(int[][] maze) {
		this.maze = maze;
	}

	public Coords getPlayerPos() {
		return playerPos;
	}

	public void setPlayerPos(Coords playerPos) {
		this.playerPos = playerPos;
	}
	
	
	
	public int getLIFE() {
		return LIFE;
	}

	public void setLIFE(int lIFE) {
		LIFE = lIFE;
	}

	public int getLEVEL() {
		return LEVEL;
	}

	public void setLEVEL(int lEVEL) {
		LEVEL = lEVEL;
	}

	public double getTIME() {
		return TIME;
	}

	public void setTIME(double tIME) {
		TIME = tIME;
	}

	public void updateMaze() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 21; j ++) {
				if (maze[i][j] == 10 && (playerPos.x != j || playerPos.y != i)) {
					
					maze[i][j] = 1;
					maze[playerPos.y][playerPos.x] = 10;
					
					break;
				}
			}
		}
	}
}
