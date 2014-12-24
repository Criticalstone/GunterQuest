
import java.nio.file.Paths;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class Game extends BasicGameState{
	private TiledMap map;
	Image img;
	Player player;
	int x,y;
	private boolean blocked[][];
	private Direction dir;
	private boolean moved;

	Menu ingameMenu;
	
	public enum Direction{
		UP(0,-1),
		DOWN(0,1),
		LEFT(-1,0),
		RIGHT(1,0),
		NONE(0,0);
		
		private int deltaX, deltaY;
		private Direction(int deltaX, int deltaY){
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}
		
		private int getDeltaX(){
			return this.deltaX;
		}
		
		private int getDeltaY(){
			return this.deltaY;
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		moved = false;
		map = new TiledMap("res/map/testmap.tmx");
		blocked = new boolean[map.getWidth()][map.getHeight()];
		int layerName = map.getLayerIndex("Collideable");
		dir = Direction.NONE;
		new Mutant(Paths.get("src", "res", "Lores_Attacks", "002Osquatch.txt"));
		
		
		for(int i = 5; i < map.getWidth(); i++){
			for(int j = 5; j < map.getHeight(); j++){
				int tileID = map.getTileId(i, j, layerName);
				if(map.getTileProperty(tileID, "blocked", "false").equals("true")){
					blocked[i][j] = true;
				}else{
					blocked[i][j] = false;
				}
			}
		}
		
		player = new Player();
		x = 1;
		y = 1;

		/****************TESTING************************/
		String[] test = {"test1", "test2", "test3"};
		ingameMenu = new Menu(100, 100, test);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		map.render(-x*46, -y*46);
		g.drawImage(player.getImage(),5*46,5*46);

		ingameMenu.paint(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if(!moved && isNextEmpty(y,x) && !ingameMenu.getVisible()){
			x = getNextX();
			y = getNextY();
			moved = true;
		}
		if(x==5 && y==5){
			game.enterState(2);
			x = 6;
		}
	}
	
	public boolean isNextEmpty(int x, int y){
		return !blocked[getNextX()+5][getNextY()+5];
	}
	
	public int getNextX(){
		return x + dir.getDeltaX();
	}
	
	public int getNextY(){
		return y + dir.getDeltaY();
	}
	
	public void keyPressed(int key, char c){

		if(key == Input.KEY_UP){
			dir = Direction.UP;
		}else if(key == Input.KEY_DOWN){
			dir = Direction.DOWN;
		}else if(key == Input.KEY_LEFT){
			dir = Direction.LEFT;
		}else if(key == Input.KEY_RIGHT){
			dir = Direction.RIGHT;
		}else if(key == Input.KEY_ENTER){
			ingameMenu.setVisible(ingameMenu.getVisible() ? false : true);
		}

		if(ingameMenu.getVisible()){
			if(key == Input.KEY_UP) {
				ingameMenu.movePointer(1);
			}else if(key == Input.KEY_DOWN){
				ingameMenu.movePointer(-1);
			}
		}
		moved = false;
	}
	
	public void keyReleased(int key, char c){
		if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT)  {
			dir = Direction.NONE;
		}
	}
	
	
	@Override
	public int getID() {
		return 1;
	}
	
	


}
