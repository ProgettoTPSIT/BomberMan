/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.Serializable;
import java.util.Random;

public class Blocco extends Elemento implements Serializable {
    boolean distruttibile;
    Ability ability;

    public Blocco(boolean distruttibile) {
        this.distruttibile = distruttibile;
        if(distruttibile) {
            ability = getRandomAbility();
        } else {
            ability = Ability.NONE;
        }
    }
    
    private Ability getRandomAbility() {
        Ability[] allAbilities = {Ability.NBOMBS, Ability.RANGE, Ability.SPEED, Ability.NONE, Ability.NONE, Ability.NONE}; //6 blocchi ogni 16 hanno un'abilità
        Random rand = new Random();
        return allAbilities[rand.nextInt(allAbilities.length)]; //generiamo un numero casuale tra 0 e 2 compresi e prendiamo l'abilità corrispondente
    }
    
}
