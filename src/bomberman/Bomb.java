/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author Alexander.Perathoner
 */
public class Bomb extends Elemento {
    int range = 2;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }


}
