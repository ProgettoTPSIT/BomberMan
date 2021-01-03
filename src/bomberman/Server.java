
package bomberman;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket socketServer;
    private Socket socketClient;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }
    
    public void attendi() {
        System.out.println("Server in ascolto sulla porta "+porta);
        
         try {
                //il server si mette in ascolto
                socketServer=new ServerSocket(porta);
                int playerConnessi = 0;
                while (playerConnessi < 2) {
                    socketClient=socketServer.accept();
                    System.out.println("Client Connesso: "+socketClient.getRemoteSocketAddress());
                    Thread thread=new Thread(new ServerThread(socketClient));
                    thread.start();
					playerConnessi++;
                }
               
               
                
            } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
