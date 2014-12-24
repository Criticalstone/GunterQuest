import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Criticalstone on 23-Dec-14.
 */
public class Menu {
    private int x, y;
    private String[] items;
    private boolean isVisible;
    private int pointer;

    public Menu(int x, int y, String[] items){
        this.x = x;
        this.y = y;
        this.items = items;
    }

    public void setVisible(boolean visibility){
        isVisible = visibility;
    }

    public boolean getVisible(){
        return isVisible;
    }

    public void movePointer(int dir){
        dir = dir >= 1 ? 1 : dir <= -1 ? -1 : 0;
        pointer += dir;
    }

    public void paint(Graphics g){
        if(isVisible) {
            for (int i = 0; i < items.length; i++) {
                g.drawString(items[i], x + 10, y + 10 + 30 * i);
            }
            g.drawLine(x, y+pointer*30, x+5, y+pointer*30+5);
            g.drawLine(x, y+pointer*30+10, x+5, y+pointer*30+5);
        }
    }
}
