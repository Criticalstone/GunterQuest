package org.gunterquest.criticalstone.window;

import org.newdawn.slick.Graphics;

/**
 * Created by Criticalstone on 08-Jan-15.
 */
public class Item {
    public String name;

    public Item(String name){
        this.name = name;
    }

    public void paint(int x, int y, Graphics g){
        g.drawString(getName(), x + 30, y + 10);
    }

    public String getName(){
        return this.name;
    }
}
