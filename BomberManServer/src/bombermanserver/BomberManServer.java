/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.util.Random;
import bomberman.Campo;
import javafx.scene.paint.Color;

public class BomberManServer {

    static Campo campo;
    static boolean partitaIniziata = false;
    static boolean partitaFinita = false;
	
    public static void main(String[] args) {
        Server s=new Server(6789);
        //aspetto che si connettano tutti i player
        int nPlayer = 1;
        s.attendi(nPlayer);
        partitaIniziata = true;
        System.out.println(nPlayer + " player si sono connessi! Inizia la partita!");
        //creazione campo
        campo = new Campo(13, 13, nPlayer);
        System.out.println("Costruito il campo");
    }
	
    //aggiornamento mosse dei player
    static void aggiornaPlayer(int id, int azione) {
        //switch case che in base all'azione del player modifica la sua posizione/il campo
        //quindi invai una richiesta di modifica al server
        switch(azione) {
            case 1:
                campo.movePlayerUp(id);
                break;
            case 2:
                campo.movePlayerDown(id);
                break;
            case 3:
                campo.movePlayerRight(id);
                break;
            case 4:
                campo.movePlayerLeft(id);
                break;
            case 5:
                campo.piazzaBomba(id);
                break;
            default:
                System.out.println("Azione nulla - Ignoro...");
        }
    }
	
}
