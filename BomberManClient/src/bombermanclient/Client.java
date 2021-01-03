/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.JSType;

public class Client implements Runnable {
	private String indirizzoServer;
	private int porta;
	private int playerID;
	
	private BufferedReader input;
	private String stringaDaInviare; 
	
	private ObjectInputStream objectInputStream;
	private DataOutputStream outVersoServer;
	private Socket miosocket;
	
	public Client(String ind, int port){
		indirizzoServer=ind;
		porta=port;
		input=new BufferedReader (new InputStreamReader (System.in));
	}
	
	public void connetti() {
		try {
			miosocket=new Socket(indirizzoServer,porta);
			outVersoServer= new DataOutputStream(miosocket.getOutputStream());
			objectInputStream= new ObjectInputStream(miosocket.getInputStream());
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void ottieniID() {
		try {
			playerID = (int)objectInputStream.readObject();
			System.out.println("Ho ottenuto iD");
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void iniziaAggiornamentoCampo() {
		do {
			try {
				System.out.println("Chiedo campo");
				outVersoServer.writeBytes(" \n");
				BomberManClient.campo = (Campo)objectInputStream.readObject();
				if(BomberManClient.campo != null) {
					System.out.println("Campo ottenuto! " + BomberManClient.campo.griglia + " " + BomberManClient.campo.player.length);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (IOException ex) {
				System.out.println("Connessione persa");
				break;
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			}
		} while(playerIsAlive());
		
		try{
			miosocket.close();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
		BomberManClient.vivo = false;
	}
	
	private boolean playerIsAlive() {
		if (BomberManClient.campo == null) { //campo non ancora caricato
			return true;
		}
		return BomberManClient.campo.player[playerID] != null;
	}

	@Override
	public void run() {
		System.out.println("Sto comunicando!");
		connetti();
		System.out.println("Chiedo ID");
		ottieniID();
		
		iniziaAggiornamentoCampo();
	}
}
