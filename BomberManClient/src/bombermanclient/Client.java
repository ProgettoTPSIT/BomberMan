/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import bomberman.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
//<<<<<<< HEAD


//>>>>>>> main
//creazione classe client di default
public class Client implements Runnable {
	private String indirizzoServer;
	private int porta;
	private int playerID;
	
	private BufferedReader input;
	private String stringaDaInviare; 
	
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream; //permette l'invio di oggetti al server
	private Socket miosocket;
	
	public Client(String ind, int port){
            indirizzoServer=ind;
            porta=port;
            input=new BufferedReader (new InputStreamReader (System.in));
	}
        
	//connessione client al server
	public void connetti() {
            try {
                miosocket=new Socket(indirizzoServer,porta);
                objectOutputStream = new ObjectOutputStream(miosocket.getOutputStream());
                objectInputStream = new ObjectInputStream(miosocket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
        //metodo ottenimento id
	public void ottieniID() {
		try {
                    playerID = (int)objectInputStream.readObject();
                    //System.out.println("Ho ottenuto iD");
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
        //metodo di invio del comando
	public void inviaComando(int n) {
            try {
                //writeObject permette di inviare un oggetto al server
                objectOutputStream.writeObject(n);
                System.out.println("Inviato n " + n);
            } catch (IOException ex) {
                System.out.println("OPS ERRORE IN INVIA COMANDO");
            }
	} 
	
        //metodo di stampa del campo
	public void printCampo(Campo c) {
                //creazione matrice griglia
		Elemento[][] griglia = c.getGriglia();
		System.out.println("GRIGLIA");
                //creazione del campo con i 2 for
		for(int i=0; i<griglia.length; i++) {
                    for(int j=0;j<griglia[0].length; j++) {
                        System.out.print("{" + griglia[i][j].getClass() + "}");
                    }
                    System.out.println("");
		}
		System.out.println("FINE GRIGLIA");
	}
	
	public void iniziaAggiornamentoCampo() {
            do {
                //ottieni campo aggiornato
                try {
                        Sandbox.campo = (Campo) objectInputStream.readObject();
                        //System.out.println("Campo ottenuto");

                } catch (IOException ex) {
                        System.out.println("Connessione persa");
                } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

                //aspetta
                try {
                        Thread.sleep(1000);
                } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //invia aggiornamenti al server
                inviaComando(GestoreInput.gestisciMovimentiPlayer());
            } while(Sandbox.playerIsAlive(playerID));

            try{
                    miosocket.close();
            } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            BomberManClient.vivo = false;
	}

	@Override
        //run del client
	public void run() {
		System.out.println("Mi connetto...");
		connetti();
		System.out.println("Chiedo ID");
		ottieniID();
		
		System.out.println("Invio comando");
		inviaComando(0);
		System.out.println("Chiedo campo");
		
		iniziaAggiornamentoCampo();
	}
}
