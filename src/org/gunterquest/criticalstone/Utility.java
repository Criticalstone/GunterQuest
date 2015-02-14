package org.gunterquest.criticalstone;

/**
 * Created by Criticalstone on 14-Feb-15.
 */
public class Utility {

    public static enum Direction{
        DOWN(0,1),
        UP(0,-1),
        LEFT(-1, 0),
        RIGHT(1, 0);


        public int dx, dy;
        Direction(int dx, int dy){
            this.dx = dx;
            this.dy = dy;
        }
        public int getDy(){
            return this.dy;
        }
        public int getDx(){
            return this.dx;
        }
    }
}
