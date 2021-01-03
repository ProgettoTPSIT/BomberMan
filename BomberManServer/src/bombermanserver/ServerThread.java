/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanserver;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread implements Runnable {
	private int id;
	private Socket client;
	private BufferedReader inDalServer;
	private ObjectOutputStream objectOutputStream;

	public ServerThread(Socket client, int id) {
		try {
			this.client = client;
			this.id = id;
			inDalServer= new BufferedReader(new InputStreamReader(client.getInputStream()));
			objectOutputStream = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
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
		comunicaID(); //innanzitutto inviamo al client il suo id, con cui sarà in grado di capire quale player rappresenta nel campo
		
		String dalClient;
		boolean error = false;
		do {
			try {
				dalClient = inDalServer.readLine(); //ottiene aggiornamenti player
				System.out.println("Ricevuta il comando " + dalClient);
				
				//1 -> muove in su
				//2 giù
				//3 destra
				//4 sinistra
				//5 piazza bomba
				//BomberManServer.aggiornaPlayer(id, dalClient);
				objectOutputStream.writeObject(BomberManServer.campo); //invia il campo al client
				System.out.println("Inviato il campo");
			} catch (IOException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
				error = true;
			} catch (NullPointerException ex) {
				System.out.println("Persa la connessione con il client");
				error = true;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		} while(!BomberManServer.partitaFinita);
		if(!error) {
			System.out.println("Partita finita");
		}
		try {
			client.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}
 
	
}
