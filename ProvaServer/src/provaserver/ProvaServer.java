/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provaserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import common.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class ProvaServer {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		try {
			// don't need to specify a hostname, it will be the current machine
			ServerSocket ss = new ServerSocket(7777);
			System.out.println("ServerSocket awaiting connections...");
			Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
			System.out.println("Connection from " + socket + "!");
			
			// get the input stream from the connected socket
			InputStream inputStream = socket.getInputStream();
			// create a DataInputStream so we can read data from it.
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			
			// read the list of messages from the socket
			List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
			System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
			// print out the text of every message
			System.out.println("All messages:");
			listOfMessages.forEach((msg)-> System.out.println(msg.getText()));
			
			System.out.println("Closing sockets.");
			ss.close();
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(ProvaServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
