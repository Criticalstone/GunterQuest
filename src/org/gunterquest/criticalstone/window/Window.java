package org.gunterquest.criticalstone.window;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Criticalstone on 08-Jan-15.
 */
public class Window{
    int x;
    int y;
    int width;
    int height;
    Image backgroundImage;
    Color background;
    Color foreground;
    boolean isVisible, isActive;

    public Window(int x, int y, int width, int height, Color background, Color foreground){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.foreground = foreground;
    }

    public Window(int x, int y, int width, int height, Image backgroundImage, Color foreground){
        this(x, y, width, height, Color.white, foreground);
        this.backgroundImage = backgroundImage;
    }

    public Window(int x, int y, Color background, Color foreground){
        this(x, y, 0, 0, background, foreground);
    }

    public void setVisible(boolean visibility){
        isVisible = visibility;
    }

    public boolean getVisible(){
        return isVisible;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void render(Graphics g) {
        if (isVisible) {
            g.setColor(background);
            g.fillRect(x, y, width, height);

            g.setColor(foreground);
            g.drawRect(x, y, width, height);
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
