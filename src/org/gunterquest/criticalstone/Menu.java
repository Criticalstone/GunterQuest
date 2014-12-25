package org.gunterquest.criticalstone;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Criticalstone on 23-Dec-14.
 */
public class Menu {
    private int x, y, width;
    private MenuItem[] items;
    private boolean isVisible;
    private int pointer;
    private Image backgroundImage;
    private Color background, foreground;
    private static final Image DEFAULT_IMAGE = null;

    public Menu(int x, int y, Color background, Color foreground, MenuItem[] items){
        this(x,y, DEFAULT_IMAGE, foreground, items);
        this.background = background;
    }

    public Menu(int x, int y, Image background, Color foreground, MenuItem[] items){
        this.x = x;
        this.y = y;
        this.items = items;
        this.backgroundImage = background;
        this.foreground = foreground;
    }

    public void setVisible(boolean visibility){
        isVisible = visibility;
    }

    public boolean getVisible(){
        return isVisible;
    }

    public void movePointer(int dir){
        pointer += dir;
        pointer = pointer < 0 ? 0 : pointer > items.length-1 ? items.length-1 : pointer;
    }

    public void performAction(){
        items[pointer].performAction();
    }

    public MenuItem getItem(int index){
        return items[index];
    }

    public String getLongestItemName(){
        String longest = "";
        for(int i = 0; i < items.length; i++){
            if (items[i].getName().length() > longest.length()){
                longest = items[i].getName();
            }
        }
        return longest;
    }

    public void paint(Graphics g){
        if(isVisible) {
            width = 30+getLongestItemName().length()*10;
            g.setColor(background);
            g.fillRect(x, y, width, (items.length)*30+10);
            if(backgroundImage != null)
                g.drawImage(backgroundImage, x, y);
            g.setColor(foreground);
            g.drawRect(x, y, width-1, (items.length)*30+10);
            g.drawRect(x+1, y+1, width-3, (items.length)*30+10);

            for (int i = 0; i < items.length; i++) {
                g.drawString(items[i].getName(), x + 30, y + 10 + 30 * i);
            }
            g.drawLine(x+10, y+pointer*30+13, x+15, y+pointer*30+18);
            g.drawLine(x+10, y+pointer*30+23, x+15, y+pointer*30+18);
        }
    }
}
