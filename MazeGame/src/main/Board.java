package main;

//import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

import main.ScoreBoard.PanelSquare;
import model.Maze;



public class Board extends JPanel{
	
	private final int WIDTH = 21;
	private final int HEIGHT = 21;
	private final int TILE_WIDTH = 20;
	private final int TILE_HEIGHT = 20;
	
	private Maze instance = Maze.getInstance();
	private int[][] maze;

	public Board() {
		maze = instance.getMaze();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {

		g.drawLine(20, 20, 20, HEIGHT * TILE_HEIGHT + 20);
		g.drawLine(20, 20, WIDTH * TILE_WIDTH + 20, 20);
		g.drawLine(WIDTH * TILE_WIDTH + 20, 20, WIDTH * TILE_WIDTH + 20, HEIGHT * TILE_HEIGHT + 20);
		g.drawLine(20, HEIGHT * TILE_HEIGHT + 20, WIDTH * TILE_WIDTH + 20, HEIGHT * TILE_HEIGHT + 20);
		
		// fillRect(x1,y1,x2,y2)
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WIDTH * TILE_WIDTH + 20, HEIGHT);
		g.fillRect(0, HEIGHT, WIDTH, HEIGHT * TILE_HEIGHT + 20);
		g.fillRect(WIDTH * TILE_WIDTH + 20, 0, TILE_WIDTH, HEIGHT * TILE_HEIGHT + 20);
		g.fillRect(WIDTH,  HEIGHT * TILE_HEIGHT + 20, WIDTH * TILE_WIDTH + 20, TILE_HEIGHT);
		
		
		
		for (int j = 0; j< HEIGHT; j++) {
			for (int i = 0; i < WIDTH ; i++) {
				
				// Path
				if (maze[j][i] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
				}
				
				// Player
				if (maze[j][i] == 10) {
					g.setColor(Color.decode("#76FF03"));
					
					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
				}
				
				// Goal
				if (maze[j][i] == 20) {
					g.setColor(Color.decode("#00C3E5"));
					
					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
				}
				
				// WALL
				if (maze[j][i] == 0) {
					g.setColor(Color.BLACK);
					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
					
				}
				
				// COIN
				if (maze[j][i] == 2) {
					g.setColor(Color.YELLOW);
					
					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
				}
				
				// TRAP
				if (maze[j][i] == 3) {
					g.setColor(Color.decode("#ED020A"));

					g.fillRect(i * TILE_WIDTH + 20, j * TILE_HEIGHT + 20, 20, 20);
				}
			}
		}
	}
	
}
