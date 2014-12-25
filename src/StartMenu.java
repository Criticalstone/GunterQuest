

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;


public class StartMenu extends BasicGameState{
	TiledMap map;
	boolean enter = false;
	Menu startMenu;
	MenuAction[] menuActions;
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		menuActions = new MenuAction[2];
		menuActions[0] = new MenuAction("1. Start game") {
			@Override
			public void performAction() {
				enter = true;
			}
		};
		menuActions[1] = new MenuAction("2. Exit") {
			@Override
			public void performAction() {
				System.exit(1);
			}
		};
		startMenu = new Menu(0,0,gc.getWidth(), gc.getHeight(), menuActions);
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
