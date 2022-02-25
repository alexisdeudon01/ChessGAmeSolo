package Game.Board.Pieces;

import java.awt.Color;

public class Cell {
	private int x;
	private int y;
	private Color color;

	public Cell(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color=color;
	}

	public Cell(int xp, int yp) {
		this.x=xp;
		this.y=yp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
