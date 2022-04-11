package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel{
	
	private int life, level;
	private double time;
	
	private JPanel top, mid, bottom, hint, hoverPanel;
	private JLabel titleLbl, timeLbl, lifeLbl, levelLbl, pauseLbl, hoverLbl;
	private JLabel goalLbl, coinLbl, playerLbl, trapLbl;
	private JLabel lifeAmount, timeAmount, levelAmount, hoverTxt1, hoverTxt2;
	private JButton exitBtn;
	private JPanel goalSquare, playerSquare, coinSquare, trapSquare;
	private JPanel goalPanel, playerPanel, coinPanel, trapPanel;
	
	
	
	public ScoreBoard(int life, int level, double time) {
		this.life = life;
		this.level = level;
		this.time = time;
		
		
		top = new JPanel(new GridLayout(1,1));
		mid = new JPanel(new GridLayout(3,2, 30, 10));
		bottom = new JPanel();
//		hoverPanel = new JPanel(new FlowLayout());
		hoverPanel = new JPanel();
		
		
		
		titleLbl = new JLabel("Find The X-It");
		titleLbl.setFont(new Font("", Font.BOLD | Font.ITALIC, 30));
		titleLbl.setHorizontalAlignment(JLabel.CENTER);
		
		timeLbl = new JLabel("Time Left :   " );
		lifeLbl = new JLabel("Life :        ");
		levelLbl = new JLabel("Level :      ");
	
		timeAmount = new JLabel((int) Math.ceil(time) + "");
		lifeAmount = new JLabel(life + "");
		lifeAmount.setForeground(Color.RED);
		levelAmount = new JLabel(level + "");
	
		
		timeLbl.setHorizontalAlignment(JLabel.CENTER);
		lifeLbl.setHorizontalAlignment(JLabel.CENTER);
		levelLbl.setHorizontalAlignment(JLabel.CENTER);
		timeAmount.setHorizontalAlignment(JLabel.CENTER);
		lifeAmount.setHorizontalAlignment(JLabel.CENTER);
		levelAmount.setHorizontalAlignment(JLabel.CENTER);
		
		goalLbl = new JLabel("Your Goal");
		playerLbl = new JLabel("Player");
		coinLbl = new JLabel("Coin (Extra Time)");
		trapLbl = new JLabel("It's a Trap !");
		
		goalSquare = new PanelSquare("#00C3E5");
		playerSquare = new PanelSquare("#07F723");
		coinSquare = new PanelSquare("#FBC02D");
		trapSquare = new PanelSquare("#ED020A");
		
		goalLbl.setHorizontalAlignment(JLabel.CENTER);
		playerLbl.setHorizontalAlignment(JLabel.CENTER);
		coinLbl.setHorizontalAlignment(JLabel.CENTER);
		trapLbl.setHorizontalAlignment(JLabel.CENTER);
		
		goalSquare.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerSquare.setAlignmentX(Component.CENTER_ALIGNMENT);
		coinSquare.setAlignmentX(Component.CENTER_ALIGNMENT);
		trapSquare.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pauseLbl = new JLabel("Press 'Space' to pause the game");
		pauseLbl.setForeground(Color.MAGENTA);
		
		hoverTxt1 = new JLabel("Hover ");
		hoverTxt1.setForeground(Color.decode("#808000"));
		hoverTxt2 = new JLabel(" to show exit button");
		hoverTxt2.setForeground(Color.decode("#808000"));
		
		hoverLbl = new JLabel("THIS");
		hoverLbl.setFont(new Font("", Font.BOLD, 15));
		hoverLbl.setForeground(Color.RED);
		
		exitBtn = new JButton("Exit");
		exitBtn.setVisible(false);
//		exitBtn.setHorizontalAlignment(JButton.CENTER);
		
		top.add(titleLbl);
		
		mid.add(timeLbl);
		mid.add(timeAmount);
		
		mid.add(lifeLbl);
		mid.add(lifeAmount);
		
		mid.add(levelLbl);
		mid.add(levelAmount);
		

		goalPanel = new JPanel();
		goalPanel.add(goalSquare);
		goalPanel.add(goalLbl);
		
		playerPanel = new JPanel();
		playerPanel.add(playerSquare);
		playerPanel.add(playerLbl);
		
		coinPanel = new JPanel();
		coinPanel.add(coinSquare);
		coinPanel.add(coinLbl);
		
		trapPanel = new JPanel();
		trapPanel.add(trapSquare);
		trapPanel.add(trapLbl);
		
		hint = new JPanel(new GridLayout(4,1));
		hint.add(goalPanel);
		hint.add(playerPanel);
		hint.add(coinPanel);
		hint.add(trapPanel);
		
		
//		hint.add(goalSquare);
//		hint.add(goalLbl);
//
//		hint.add(playerSquare);
//		hint.add(playerLbl);
//		
//		hint.add(coinSquare);
//		hint.add(coinLbl);
//		
//		hint.add(trapSquare);
//		hint.add(trapLbl);
		

		hoverPanel.add(hoverTxt1);
		hoverPanel.add(hoverLbl);
		hoverPanel.add(hoverTxt2);
		hoverPanel.add(exitBtn);
		
		bottom.add(pauseLbl, BorderLayout.NORTH);
		bottom.add(hoverPanel, BorderLayout.CENTER);
//		bottom.add(exitBtn, BorderLayout.SOUTH);
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(top);
		this.add(mid);
		this.add(hint);
		this.add(bottom);
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		hoverLbl.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				exitBtn.setVisible(true);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});	
	}

	
	public JLabel getHoverLbl() {
		return hoverLbl;
	}

	public void setHoverLbl(JLabel hoverLbl) {
		this.hoverLbl = hoverLbl;
	}

	public JButton getExitBtn() {
		return exitBtn;
	}

	public void setExitBtn(JButton exitBtn) {
		this.exitBtn = exitBtn;
	}

	public JLabel getLifeAmount() {
		return lifeAmount;
	}

	public void setLifeAmount(JLabel lifeAmount) {
		this.lifeAmount = lifeAmount;
	}

	public JLabel getTimeAmount() {
		return timeAmount;
	}

	public void setTimeAmount(JLabel timeAmount) {
		this.timeAmount = timeAmount;
	}
	
	
	class PanelSquare extends JPanel {
		
		private Color color;
		
		public PanelSquare(String color) {
			this.color = Color.decode(color);
		}
		
		@Override
		public void paint(Graphics g) {
			g.setColor(color);;
			g.fillRect(0, 0, 20, 20);
		}
		
	}
}
