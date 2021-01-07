/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintManServer;

/**
 *
 * @author Rizzi
 */
import labirintman.Campo;
import labirintman.Player;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import labirintman.Campo;
import labirintman.Player;

//classe serverThread default
public class ServerThread implements Runnable {
    private int id;
    private Socket client;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerThread(Socket client, int id) {
        try {
            this.client = client;
            this.id = id;
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	// metodo di comunicazione dell'id
	public void comunicaID() {
		try {
			objectOutputStream.writeObject(id);
			System.out.println("Inviato ID " + id);
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		System.out.println("comunico iD");
		comunicaID(); //innanzitutto inviamo al client il suo id, con cui sarà in grado di capire quale player rappresenta nel campo
		System.out.println("ID comunicato" );
		boolean error = false;
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		} while(!LabirintManServer.partitaIniziata);
		int loopCounter = 0;
		do {
			try {
				//System.out.println("Aspetto comando dal client...");
				int comandoDalClient = (int)objectInputStream.readObject(); //ottiene aggiornamenti player
				System.out.println("Ricevuto il comando " + comandoDalClient);

				LabirintManServer.campo.aggiornaPlayer(id, comandoDalClient); //aggiornamento dei player con i loro id e i loro comandi
				//System.out.println("Invio il campo a " + id);
				objectOutputStream.writeObject(LabirintManServer.campo); //invia il campo al client
				System.out.println("Inviato il campo a: " + id);
				String str = playersToString();
				System.out.println(str);
				objectOutputStream.writeObject(str);
				
			} catch (IOException ex) {
				System.out.println("Il client ha chiuso la connessione con il server!");
				error = true;
			} catch (NullPointerException ex) {
				System.out.println("ERROR: Persa la connessione con il client");
				error = true;
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
			//Ogni 5 secondi il campo viene rigenerato. I player rimangono nelle stesse posizioni
                        //facciamo aggiornare il campo solo al primo thread
			if(loopCounter >= 50 && id==0) {
				System.out.println("Genero un nuovo campo!");
				LabirintManServer.campo = new Campo(LabirintManServer.campo);
				loopCounter = 0;
			}
			try {
				 //minimo delay durante la partita per evitare sovraccaricamenti alal cpu
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
			loopCounter++;
		} while(!LabirintManServer.playersWon() || error);
        //inviamo l'ultimo aggiornamento ai client per fargli apparire la schermata di fine game    
        try {
            int comandoDalClient = (int)objectInputStream.readObject();
            objectOutputStream.writeObject(LabirintManServer.campo);
            String str = playersToString();
            System.out.println(str);
            objectOutputStream.writeObject(str);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		if(!error) {
			System.out.println("Partita finita");
		}
		try {
			client.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
    
 
	public String playersToString() {
		String str = "";
		for(Player p : LabirintManServer.campo.getPlayers()) {
			str += ""+p.getX()+"-"+p.getY()+"#";
		}
		return str;
	}
}