/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintman;

/**
 *
 * @author Rizzi
 */
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author alex
 */
public class Player implements Serializable {
	Point p;
	int id;
	
	public Player(int x, int y, int id) {
		p = new Point(x, y);
		this.id = id;
	}

	public int getX() {
		return p.x;
	}

	public int getY() {
		return p.y;
	}

	public int getId() {
		return id;
	}
	
	public void moveUp() {
		p.y--;
	}
	public void moveDown() {
		p.y++;
	}
	public void moveRight() {
		p.x++;
	}
	public void moveLeft() {
		p.x--;
	}

	private void setPosition(int x, int y) {
		p = new Point(x, y);
	}
}
