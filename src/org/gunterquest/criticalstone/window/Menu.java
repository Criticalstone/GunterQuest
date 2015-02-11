package org.gunterquest.criticalstone.window;

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
    private int itemGap;
    private int pointer;
    private Menu previous;
    private List<MenuItem> items;

    public Menu(int x, int y, int itemGap, Color background, Color foreground, ArrayList<MenuItem> items){
        super(x, y, background, foreground);
        this.items = items;
        setItemGap(itemGap);

        setWidth(getLongestItemName().length() * 14);
        setHeight(items.size() * itemGap + 10);
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

    public void movePointer(int dir){
        pointer += dir;
        pointer = pointer < 0 ? 0 : pointer > items.size()-1 ? items.size()-1 : pointer;
    }

    public void performAction(){
        items.get(pointer).performAction();
    }

    public void setItemGap(int itemGap){
        this.itemGap = itemGap;
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
            setHeight(items.size() * itemGap + 10);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null)
                    items.get(i).paint(x, y + itemGap * i, g);
            }

            float[] points = new float[]{x + 10, y + pointer * itemGap + 13, x + 15, y + pointer * itemGap + 18, x + 10, y + pointer * itemGap + 23};
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

