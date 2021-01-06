/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanclient.UI;

import bomberman.*;
import bombermanclient.Constants;
import bombermanclient.Sandbox;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alex
 */
//classe di assegnamento dei vari colori agli elementi i ncampo
public class Drawer {
    static private GraphicsContext gc;

    static private void drawRectangle(Rectangle rect){
        gc.fillRect(rect.getX(),      
        rect.getY(), 
        rect.getWidth(), 
        rect.getHeight());
        gc.setFill(rect.getFill());
        gc.setStroke(rect.getStroke());
    }

    static private Paint getElementoFill(Elemento e) {
        Paint colore = Color.WHITE;
        //controllo se l'elemento da colorare è un blocco
        //lo switch accetta solo interi quindi siamo dovuti ricorrere ad un else if
        if(e.getClass() == Blocco.class) {
            Blocco b = (Blocco) e;
            if(b.getDistruttibile()) {
                //se è distruttibile gli assegnamo il colore grigio
                colore = Color.GREY;
            } else {
                //altrimenti quello nero
                colore = Color.BLACK;
            }
        } else if(e.getClass() == Bomb.class) {
            //se è una bomba diventa arancione
            colore = Color.ORANGE;
        }
        return colore;
    }

    //in caso di distruzione di un elemento bisogna assegnare diversi colori
    static private Paint getElementoStroke(Elemento e) {
        Paint colore = Color.WHITE;
        if(e.getClass() == Blocco.class) {
            colore = Color.BLACK;
        } else if(e.getClass() == Bomb.class) {
            colore = Color.RED;
        }
        return colore;
    }

    //impostazione di un elemento colorato
    static private void drawElemento(int x, int y, Elemento e) {
        //creazione dell'elemento con dimensioni statiche
        Rectangle border = new Rectangle(x, y, Constants.blockDimension, Constants.blockDimension);
        border.setFill(getElementoFill(e));
        border.setStroke(getElementoStroke(e));
        drawRectangle(border);
    }

    //colorazione sfondo e giocatori
    static public void drawCampo(Campo c) {
        gc = Sandbox.getGraphicsContext();		
        //disegno lo sfondo
        if(c != null) {
            drawPlayers(c.getPlayers());
            drawGriglia(c.getGriglia());
        } else {
            System.out.println("Il campo è null!");
        }
    }

    //colorazione griglia (vari blocchi)
    static private void drawGriglia(Elemento[][] griglia) {
        int rows = griglia.length;
        int columns = griglia[0].length;

        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                //disegno un blocco nello spazio corrispondente
                if(j==12) {
                        drawElemento(i*Constants.blockDimension, j*Constants.blockDimension, griglia[(j+1)%columns][(i+1)%rows]);
                } else {
                        drawElemento(i*Constants.blockDimension, j*Constants.blockDimension, griglia[(j+1)%columns][i]);
                }
            }
        }
    }
    //colorazione player
    static private void drawPlayers(Player[] players) {
            Node[] nodes = new Node[players.length];
            for(int i=0; i<players.length; i++) {
                    drawPlayer(players[i]);
            }
    }
    //metodo colorazione player
    static private void drawPlayer(Player p) {
            Rectangle border = new Rectangle(p.getX(), p.getY(), Constants.blockDimension, Constants.blockDimension);
            Color color = Color.RED;
            //in base all'id il player avrà un colore diverso
            switch(p.getId()) {
                    case 1:
                            color = Color.BLUE;
                            break;
                    case 2:
                            color = Color.GREEN;
                            break;
                    case 3:
                            color = Color.YELLOW;
            }

            border.setFill(color);
            drawRectangle(border);
    }

	
}
