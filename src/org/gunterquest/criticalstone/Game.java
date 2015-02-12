package org.gunterquest.criticalstone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.gunterquest.criticalstone.window.*;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;


public class Game extends BasicGameState{
	private TiledMap map;
	Player player;
	int x,y;
	private boolean blocked[][];
	private Direction dir;
	private boolean moved;
	private boolean exit = false;

	public MessageBar messageBar;

	ArrayList<MenuItem> menuItems;
	ArrayList<MenuItem> mutantItems;

	Menu ingameMenu;
	MutantMenu mutantMenu;
	Menu activeMenu;
	private boolean state2;

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
		if (f.exists())
			loadGame();


		for (int i = 5; i < map.getWidth(); i++) {
			for (int j = 5; j < map.getHeight(); j++) {
				int tileID = map.getTileId(i, j, layerName);
				blocked[i][j] = map.getTileProperty(tileID, "blocked", "false").equals("true");
			}
		}

		player = new Player();
		player.addMutant(new Mutant(Paths.get("res", "Lores_Attacks", "002Osquatch.txt")));
		player.addMutant(new Mutant(Paths.get("res", "Lores_Attacks", "003Godmar.txt")));
		player.addMutant(new Mutant(Paths.get("res", "Lores_Attacks", "004Gadlas.txt")));

		messageBar = new MessageBar(10, gc.getHeight() - 110, gc.getWidth() - 20, 100);


		/****************Ingame menu************************/
		menuItems = new ArrayList<MenuItem>(1);
		menuItems.add(new MenuItem("POKèMON") {
			@Override
			public void performAction() {
				updateMutantMenu();
				mutantMenu.setVisible(true);
				mutantMenu.setActive(true);
				ingameMenu.setActive(false);
			}
		});
		menuItems.add(new MenuItem("ITEM") {
			@Override
			public void performAction() {
				messageBar.setMessage("Welcome to the items menu! UNDER CONSTRUCTION");
			}
		});
		menuItems.add(new MenuItem("GüNTER") {
			@Override
			public void performAction() {
				messageBar.setMessage("Welcome to the GüNTER menu! UNDER CONSTRUCTION");
			}
		});
		menuItems.add(new MenuItem("SAVE") {
			@Override
			public void performAction() {
				messageBar.setMessage("The game has been saved! UNDER CONSTRUCTION");
				saveGame();
			}
		});
		menuItems.add(new MenuItem("OPTION") {
			@Override
			public void performAction() {
				messageBar.setMessage("Welcome to the settings menu! UNDER CONSTRUCTION");
			}
		});
		menuItems.add(new MenuItem("EXIT") {
			@Override
			public void performAction() {
				ingameMenu.setVisible(false);
				ingameMenu.movePointer(-5);
			}
		});
		ingameMenu = new Menu(gc.getWidth() - 110, 10, 20, Color.white, Color.black, menuItems);


		/*************Pokemon menu*************************/
		mutantItems = new ArrayList<MenuItem>(1);
		mutantMenu = new MutantMenu(10, 10, 50, Color.white, Color.black, mutantItems);
		mutantMenu.setWidth(300);
		mutantMenu.setPrevious(ingameMenu);

		player.changeMutantPlace(1, 2);
		updateMutantMenu();
	}

	private void updateMutantMenu() {
		mutantMenu.clearMenu();
		for(Mutant m: player.getMutants()){
			mutantItems.add(new MutantMenuItem(m));
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		map.render(-x*46, -y*46);
		g.drawImage(player.getImage(),5*46,5*46);

		messageBar.render(g);
		ingameMenu.render(g);
		mutantMenu.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if(!moved && isNextEmpty() && !ingameMenu.getVisible()){
			x = getNextX();
			y = getNextY();
			moved = true;
		}
		if(state2)
			game.enterState(2);
		if(exit){
			game.enterState(0);
		}
	}
	
	public boolean isNextEmpty(){
		return !blocked[getNextX()+5][getNextY()+5];
	}
	
	public int getNextX(){
		return x + dir.getDeltaX();
	}
	
	public int getNextY(){
		return y + dir.getDeltaY();
	}
	
	public void keyPressed(int key, char c){

		activeMenu = ingameMenu.isActive()?ingameMenu:mutantMenu;
		if(messageBar.getVisible()){
			if (key == Input.KEY_ENTER){
				messageBar.setNextMessage();
			}
		}else if(ingameMenu.getVisible() || mutantMenu.getVisible()){
			if(key == Input.KEY_UP) {
				activeMenu.movePointer(-1);
			}else if(key == Input.KEY_DOWN){
				activeMenu.movePointer(1);
			}else if(key == Input.KEY_ENTER){
				activeMenu.performAction();
			}else if(key == Input.KEY_ESCAPE){
				activeMenu.setVisible(false);
				activeMenu.setActive(false);
				if(activeMenu.getPrevious() != null)
					activeMenu.getPrevious().setActive(true);
			}
		}else{
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
				ingameMenu.setActive(true);
			}else if(key == Input.KEY_ESCAPE){
				exit = true;
			}else if(key == Input.KEY_2){
				state2 = true;
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
		}catch (FileNotFoundException ignored){}catch(IOException ex){}
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
