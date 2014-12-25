import org.newdawn.slick.Graphics;

/**
 * Created by Criticalstone on 24-Dec-14.
 */
public abstract class MenuAction {
    public String name;
    public MenuAction(String name){
        this.name = name;
    }
    public abstract void performAction();
    public String getName(){
        return name;
    }
}
