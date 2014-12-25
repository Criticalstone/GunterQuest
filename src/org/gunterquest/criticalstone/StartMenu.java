package org.gunterquest.criticalstone;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class StartMenu extends BasicGameState{
	TiledMap map;
	boolean enter = false;
	Menu startMenu;
	MenuItem[] menuActions;
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		menuActions = new MenuItem[2];
		menuActions[0] = new MenuItem("1. Start game") {
			@Override
			public void performAction() {
				enter = true;
			}
		};
		menuActions[1] = new MenuItem("2. Exit") {
			@Override
			public void performAction() {
				System.exit(1);
			}
		};
		startMenu = new Menu(50, 50, Color.yellow, Color.blue, menuActions);
		startMenu.setVisible(true);
		System.out.println(startMenu.getItem(0).getName());
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		startMenu.paint(g);
	}

	public void update(GameContainer c, StateBasedGame game, int delta)
			throws SlickException {
		if(enter){
			game.enterState(1);
			enter = false;
		}
	}

	public void keyPressed(int key, char c){
		if(key == Input.KEY_UP){
			startMenu.movePointer(-1);
		}else if(key == Input.KEY_DOWN){
			startMenu.movePointer(1);
		}else if(key == Input.KEY_ENTER){
			startMenu.performAction();
		}
	}

	public int getID() {
		return 0;
	}

}
