/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alex
 */
public class ServerThread implements Runnable {
	
	private Socket client;
	private BufferedReader inDalServer;
	private ObjectOutputStream objectOutputStream;

	public ServerThread(Socket client) {
		try {
			this.client = client;
			inDalServer= new BufferedReader(new InputStreamReader(client.getInputStream()));
			objectOutputStream = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void run() {
		String dalClient;
		
		
		do{
			try {
				dalClient=inDalServer.readLine(); //ottiene aggiornamenti player
				objectOutputStream.writeObject(BomberMan.campo); //invia il campo al client
				
			} catch (IOException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		} while(BomberMan.partitaFinita);
		
	   
		try {
			client.close();
		} catch (IOException ex) {
			Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}
 
	
}
