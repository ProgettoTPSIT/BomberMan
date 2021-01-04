/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.io.Serializable;

public class Bomb extends Elemento implements Serializable {
	int range = 2;

	@Override
	public String toString() {
		return "Bomb{" + "range=" + range + '}';
	}
	
	public Bomb(int range) {
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}


}
