package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//import Board.MyAdapter;
import model.Coords;
import model.Maze;

public class Game implements ActionListener{
	
	private int LIFE, LEVEL;
	private double TIME;
	private boolean RUNNING, GOAL, END, COLLIDE;
	
	private Maze instance = Maze.getInstance();
	private int[][] maze;
	
	private Board board;
	private Timer timer;
	private JFrame frame;
	
	private ScoreBoard scBoard;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	public Game() {
		initGame();
		initFrame();	
	}

	public static void main(String[] args) {
		new Game();
	}
	
	private void initFrame() {
		frame = new JFrame("Maze Game");
		
		gbl = new GridBagLayout();

        gbl.columnWidths = new int[] {500,340};
        gbl.rowHeights = new int[] {500};
        gbl.columnWeights = new double[] {1, 1};
        gbl.rowWeights = new double[] {1};
		
		frame.setLayout(gbl);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		board = new Board();
		scBoard = new ScoreBoard(LIFE, LEVEL, TIME);

		
		addPanel(0, 0, 0, 0, board);
		addPanel(0, 1, 0, 1, scBoard);
		
		
		frame.addKeyListener(new MyAdapter());
		frame.setResizable(false);
		frame.setSize(840,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		scBoard.getExitBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		
		timer = new Timer(100, this);
		timer.start();
	}
	
	private void addPanel(int row, int col, int height, int width, Component com) {
		gbc.gridx = col;
        gbc.gridy = row;
        gbc.gridheight = height;
        gbc.gridwidth = width;
        gbl.setConstraints(com, gbc);
        
        frame.getContentPane().add(com);
	}
	
	private void initGame() {
		MazeGenerator mazeGenerator = new MazeGenerator();
		mazeGenerator.generateMaze();
		maze = instance.getMaze();
		
		LIFE = instance.getLIFE();
		LEVEL = instance.getLEVEL();
		if (LEVEL > 1) {
			TIME = instance.getTIME() - 3;
		} else {
			TIME = instance.getTIME();
		}
		RUNNING = true;
		GOAL = false;
		END = false;
		COLLIDE = false;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		frame.repaint();
		
		if (RUNNING && !END && !GOAL) {
			checkPlayer();
			TIME -= 0.1;
//			System.out.println("Time = " + TIME + "; Life = " + LIFE);
			scBoard.getLifeAmount().setText(LIFE + "");
			scBoard.getTimeAmount().setText((int) Math.ceil(TIME) + "");
		}
		
		if (!RUNNING) {
		
			timer.stop();
			
			JOptionPane.showMessageDialog(null, "Game paused..");
			RUNNING = !RUNNING;
			timer.start();
		}
		
		if (COLLIDE) {
			timer.stop();
			
			JOptionPane.showMessageDialog(null, "You lose a life..");
			COLLIDE = !COLLIDE;
			timer.start();
		}
		
		if (GOAL) {
			timer.stop();
			
			if (LEVEL+1 > 7) {
				JOptionPane.showMessageDialog(null, "You Won!");
				frame.dispose();
				System.exit(0);
			}
			
			instance.setLEVEL(LEVEL + 1);
			instance.setTIME(TIME);
			JOptionPane.showMessageDialog(null, "Go to next level!");
			frame.dispose();
			initGame();
			initFrame();
		}
		
		if (END) {
			timer.stop();
			instance.setLEVEL(1);
			JOptionPane.showMessageDialog(null, "You Lose!");
			frame.dispose();
			initGame();
			initFrame();
		}

		
	}
	
	private void checkPlayer() {
		Coords player = instance.getPlayerPos();
		int x = player.x;
		int y = player.y;
		
		if (maze[y][x] == 2) {
			TIME += 5;
		}
		
		if (maze[y][x] == 20) {
			GOAL = true;
		}
		
		if (maze[y][x] == 3) {
			LIFE -= 1;
		}
		
		if (LIFE <= 0 || TIME <= 0) {
			END = true;
		}
		
		
		instance.updateMaze();
	}
	
	private class MyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();;
			Coords player = instance.getPlayerPos();

			int x = player.x;
			int y = player.y;
			
			
			if (key == 87) {
//				System.out.println("UP");
				if (y - 1 >= 0 && maze[y-1][x] != 0) {
					player.y -= 1;
				}
			}
			
			if (key == 83) {
//				System.out.println("DOWN");
				if (y + 1 < 21 && maze[y+1][x] != 0) {
					player.y += 1;
					
				}
			}
			
			if (key == 65) {
//				System.out.println("LEFT");
				if (x - 1 >= 0 && maze[y][x-1] != 0) {
					player.x -= 1;
					
				}
			}
			
			if (key == 68) {
//				System.out.println("RIGHT");
				if (x + 1 < 21 && maze[y][x+1] != 0) {
					player.x += 1;
					
				}
			}
			
			if (maze[player.y][player.x] == 3) {
				COLLIDE = true;
			}
			
			if (key == 32) {
				RUNNING = !RUNNING;
			}
			instance.setPlayerPos(player);
		}
	}
}
