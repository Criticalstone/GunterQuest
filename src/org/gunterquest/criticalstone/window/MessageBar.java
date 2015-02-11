package org.gunterquest.criticalstone.window;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Criticalstone on 13-Jan-15.
 */

public class MessageBar extends Window{

    private int currentMessageIndex = 0;
    private String message1, message2;
    private String[] message;

    public MessageBar(int x, int y, int width, int height) {
        super(x, y, width, height, Color.white, Color.black);
        message1 = "";
        message2 = "";
    }

    public void render(Graphics g){
        if(isVisible) {
            super.render(g);

            g.drawString(message1, x + 30, y + 30);
            g.drawString(message2, x + 30, y + 60);
        }
    }

    public void clearMessageBoard(){
        message1 = "";
        message2 = "";
    }

    public void setNextMessage() {
        try{
            clearMessageBoard();
            message1 = message[currentMessageIndex].substring(0,message[currentMessageIndex].length() > 40 ? 40 : message[currentMessageIndex].length());
            message2 = message[currentMessageIndex].length() > 40?message[currentMessageIndex].substring(40,message[currentMessageIndex].length()) : "";
        }catch(ArrayIndexOutOfBoundsException e){
            setVisible(false);
        }
        currentMessageIndex++;

    }

    public void setMessage(String message){

        currentMessageIndex = 0;
        this.message = message.split("[.!?]\\s");
        setVisible(true);
        setNextMessage();
    }
}
