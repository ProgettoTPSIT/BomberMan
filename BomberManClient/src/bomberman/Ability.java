/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.Serializable;

//instanziazione enum delle abilità del giocatore e della bomba.
public enum Ability implements Serializable {
    SPEED, 
    RANGE,
    NBOMBS,
    NONE
}
