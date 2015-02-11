package org.gunterquest.criticalstone.window;

/**
 * Created by Criticalstone on 24-Dec-14.
 */
public abstract class MenuItem extends Item {
    public MenuItem(String name){
        super(name);
    }
    public abstract void performAction();
}
