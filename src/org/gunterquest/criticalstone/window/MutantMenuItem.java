package org.gunterquest.criticalstone.window;

import org.gunterquest.criticalstone.Mutant;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Criticalstone on 06-Jan-15.
 */
public class MutantMenuItem extends MenuItem {
    private Mutant mutant;

    public MutantMenuItem(Mutant mutant) {
        super(mutant.getName());
        this.mutant = mutant;
    }

    public void paint(int x, int y, Graphics g){
        g.setColor(Color.black);
        g.drawString(name, x + 30, y + 10);
        g.drawString(":L " + mutant.getLevel(), x + 130, y + 10);
        g.drawString("HP:", x + 60, y + 30);

        g.drawRect(x + 90, y + 35, 101, 7);

        g.drawString(mutant.getHealth() + "/" + mutant.getMaxHealth(), x + 200, y + 30);


        double healthBar = ((double)100/mutant.getMaxHealth())*mutant.getHealth();
        g.setColor(Color.green);
        g.fillRect(x + 91, y + 36, (float)Math.ceil(healthBar), 6);

        g.setColor(Color.black);
    }

    public void performAction(){
        //TODO: Add action which happens when the Mutant is clicked
    }
}
