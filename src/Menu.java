import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tests.SoundTest;

/**
 * Created by Criticalstone on 23-Dec-14.
 */
public class Menu {
    private int x, y, width, height;
    private MenuAction[] items;
    private boolean isVisible;
    private int pointer;

    public Menu(int x, int y, int width, int height, MenuAction[] items){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.items = items;
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

    public void paint(Graphics g){
        if(isVisible) {
            g.setColor(Color.white);
            g.fillRect(x, y, width-x, (items.length)*30+10);
            g.setColor(Color.black);
            g.drawRect(x, y, width-x-2, (items.length)*30+10);
            g.drawRect(x+1, y+1, width-x-2, (items.length)*30+10);

            for (int i = 0; i < items.length; i++) {
                g.drawString(items[i].getName(), x + 30, y + 10 + 30 * i);
            }
            g.drawLine(x+10, y+pointer*30+13, x+15, y+pointer*30+18);
            g.drawLine(x+10, y+pointer*30+23, x+15, y+pointer*30+18);
        }
    }
}
