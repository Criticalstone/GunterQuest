package org.gunterquest.criticalstone.window;

import org.gunterquest.criticalstone.Game;
import org.gunterquest.criticalstone.Utility;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Criticalstone on 23-Dec-14.
 */
public class Menu extends Window{
    private int itemVGap, itemHGap, columns, rows;
    private int pointerX, pointerY;
    private Menu previous;
    private List<MenuItem> items;

    public Menu(int x, int y, int itemGap, Color background, Color foreground, ArrayList<MenuItem> items){
        super(x, y, background, foreground);
        this.items = items;
        this.columns = 1;
        this.rows = items.size()/columns;
        setItemGap(itemGap, 0);

        setWidth(getLongestItemName().length() * 14);
        setHeight(items.size() * itemGap + 10);
    }

    public Menu(int x, int y, int itemVGap, int itemHGap, int columns, Color background, Color foreground, ArrayList<MenuItem> items){
        super(x, y, background, foreground);
        this.items = items;
        this.columns = columns;
        this.rows = items.size()/columns;
        setItemGap(itemVGap, itemHGap);
    }

    public void addItem(MenuItem item){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i) == null){
                items.add(i, item);
                break;
            }
        }

    }

    public void clearMenu(){
        items.clear();
    }

    public void movePointer(Utility.Direction dir){
        pointerY += dir.getDeltaY();
        pointerX += dir.getDeltaX();
        pointerY = pointerY < 0 ? 0 : pointerY > (items.size()-1)/columns ? (items.size()-1)/columns : pointerY;
        pointerX = pointerX < 0 ? 0 : pointerX > columns-1 ? columns-1 : pointerX;
    }

    public void performAction(){
        items.get(pointerX*rows+pointerY).performAction();
    }

    public void setItemGap(int itemVGap, int itemHGap){
        this.itemVGap = itemVGap;
        this.itemHGap = itemHGap;
    }

    public MenuItem getItem(int index){
        return items.get(index);
    }

    public String getLongestItemName(){
        String longest = "";
        for(int i = 0; i < items.size(); i++){
            if (items.get(i) == null){return longest;}
            if (items.get(i).getName().length() > longest.length()){
                longest = items.get(i).getName();
            }
        }
        return longest;
    }

    public void render(Graphics g) {
        if (isVisible) {
            super.render(g);
            setHeight(items.size() * itemVGap / columns + 10);
            setWidth(columns * getLongestItemName().length()*13 + columns * itemHGap + 10);
            int nextItem = 0;

            /*for(int i = 0; i < columns; i++) {
                for (int j = 1; j < rows; j++) {
                    if (items.get(i*j+j) != null) {
                        items.get(i*j+j).paint(x + i * getLongestItemName().length() * 13 + itemHGap * i, y + j * itemVGap, g);
                        nextItem++;
                    }
                }
            }*/

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null)
                    items.get(i).paint(x, y + itemVGap * i, g);
            }

            int pointerPaintX = x + pointerX * getLongestItemName().length() * 10 + itemHGap * pointerX + 10;
            int pointerPaintY = y + pointerY * itemVGap + 13;
            float[] points = new float[]{pointerPaintX, pointerPaintY, pointerPaintX + 5, pointerPaintY + 5, pointerPaintX, pointerPaintY + 10};
            Shape pointerShape = new Polygon(points);

            g.fill(pointerShape);
        }
    }
    public void setVisible(boolean visibility) {
        isVisible = visibility;
    }

    public void setPrevious(Menu menu){
        this.previous = menu;
    }

    public Menu getPrevious(){
        return this.previous;
    }
}

