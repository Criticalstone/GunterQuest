

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{
	
	//Identifiers for gamestates
	public static final int MENU = 0;
	public static final int GAME = 1;
	public static final int BATTLE = 2;
	public static final int END = 3;
	
	public static final double VERSION = 1.0;
	
	public Main(String name){
		super(name);
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new StartMenu());
		this.addState(new Game());
		this.addState(new Fight());
	}
	
	public static void main(String [] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new Main("GunterQuest" + VERSION));
		app.setDisplayMode(460, 460, false);
		app.start();
	}	
}
