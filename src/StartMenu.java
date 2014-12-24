

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
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawString("Press the keys 1 or 2 to navigate",100, 100);
		g.drawString("1. Start game", 100, 120);
		g.drawString("2. Exit", 100, 140);
	}

	public void update(GameContainer c, StateBasedGame game, int delta)
			throws SlickException {
		Input input = c.getInput();
		if(input.isKeyPressed(Input.KEY_1)){
			game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}else if(input.isKeyPressed(Input.KEY_2)){
			System.exit(1);
		}
	}

	public int getID() {
		return 0;
	}

}
