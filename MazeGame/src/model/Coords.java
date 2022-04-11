package model;


public class Coords {

	public int x;
	public int y;
	public Coords parent;
	
	public Coords(int x, int y, Coords parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
}
