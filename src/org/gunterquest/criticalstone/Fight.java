package org.gunterquest.criticalstone;

import org.gunterquest.criticalstone.window.Menu;
import org.gunterquest.criticalstone.window.MenuItem;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by Criticalstone on 13-Feb-15.
 */
public class Fight {

    private Mutant firstMutant, secondMutant;
    private boolean turn;                       //true = players turn, false = computers turn
    private ArrayList menuItems;
    private Menu fightMenu;
    private StateBasedGame game;

    public Fight(Mutant firstMutant, Mutant secondMutant, StateBasedGame game){
        this.firstMutant = firstMutant;
        this.secondMutant = secondMutant;
        this.game = game;
    }

    public void initMenu(){
        menuItems.add(new MenuItem("EXIT") {
            @Override
            public void performAction() {
                fightMenu.setVisible(false);
                fightMenu.movePointer(-5);
            }
        });
        fightMenu = new Menu(game.getContainer().getWidth() - 220, game.getContainer().getHeight() - 100, 20, Color.white, Color.black, menuItems);
    }

    public Mutant getSecondMutant() {
        return secondMutant;
    }

    public Mutant getFirstMutant() {
        return firstMutant;
    }

    public boolean isTurn() {
        return turn;
    }
}
