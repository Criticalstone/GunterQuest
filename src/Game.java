
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

	MenuAction[] menuActions;
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
		x = 1;
		y = 1;
		moved = false;
		map = new TiledMap("res/map/testmap.tmx");
		blocked = new boolean[map.getWidth()][map.getHeight()];
		int layerName = map.getLayerIndex("Collideable");
		dir = Direction.NONE;

		File f = new File("save.txt");
		if(f.exists())
			loadGame();
		
		
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


		/****************Menu************************/
		menuActions = new MenuAction[6];
		menuActions[0] = new MenuAction("POKèMON") {
			@Override
			public void performAction() {
				System.out.println("Du har kommit till pokemonmenyn!!!");
			}
		};
		menuActions[1] = new MenuAction("ITEM") {
			@Override
			public void performAction() {
				System.out.println("Du har kommit till ITEMS");
			}
		};
		menuActions[2] = new MenuAction("GüNTER") {
			@Override
			public void performAction() {
				System.out.println("Du har kommit till GUNTER info!");
			}
		};
		menuActions[3] = new MenuAction("SAVE") {
			@Override
			public void performAction() {
				System.out.println("Spelet hat sparats!");
				saveGame();
			}
		};
		menuActions[4] = new MenuAction("OPTION") {
			@Override
			public void performAction() {
				System.out.println("Du har kommit till inställningar!");
			}
		};
		menuActions[5] = new MenuAction("EXIT") {
			@Override
			public void performAction() {
				ingameMenu.setVisible(false);
				ingameMenu.movePointer(-5);
			}
		};
		ingameMenu = new Menu(gc.getWidth()-100, 0, gc.getWidth(), gc.getHeight(), menuActions);
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
		}else if(key == Input.KEY_I){
			ingameMenu.setVisible(true);
		}

		if(ingameMenu.getVisible()){
			if(key == Input.KEY_UP) {
				ingameMenu.movePointer(-1);
			}else if(key == Input.KEY_DOWN){
				ingameMenu.movePointer(1);
			}else if(key == Input.KEY_ENTER){
				ingameMenu.performAction();
			}
		}
		moved = false;
	}
	
	public void keyReleased(int key, char c){
		if(key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_LEFT || key == Input.KEY_RIGHT)  {
			dir = Direction.NONE;
		}
	}

	public void saveGame(){
		try {
			PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
			writer.println("Position: " + x + ":" + y);
			writer.close();
		}catch (FileNotFoundException e){}catch(IOException ex){}
	}

	public void loadGame(){
		List<String> fileLines = null;
		try {
			fileLines = Files.readAllLines(Paths.get("save.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = Integer.parseInt(fileLines.get(0).split(":")[1].trim());
		y = Integer.parseInt(fileLines.get(0).split(":")[2].trim());
	}
	
	
	@Override
	public int getID() {
		return 1;
	}
	
	


}
