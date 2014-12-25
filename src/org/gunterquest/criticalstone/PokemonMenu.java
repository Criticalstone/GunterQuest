package org.gunterquest.criticalstone;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

/**
 * Created by Criticalstone on 25-Dec-14.
 */
public class PokemonMenu extends Menu{

    public PokemonMenu(int x, int y, Color background, Color foreground, MenuItem[] items) {
        super(x, y, background , foreground, items);
    }

    public PokemonMenu(int x, int y, Image background, Color foreground, MenuItem[] items) {
        super(x, y, background, foreground, items);
    }
}
