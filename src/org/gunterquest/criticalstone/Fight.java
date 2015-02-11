package org.gunterquest.criticalstone;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class Fight extends BasicGameState {
	boolean exit = false;
	Image battleScene;
	String[] messages;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		battleScene = new Image("res/Fightscen/fightscene.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		battleScene.draw();
		g.setColor(new Color(0,0,0));

		g.drawString("Fight",gc.getWidth()-220, gc.getHeight()-70);
		g.drawString("Mutants",gc.getWidth()-220, gc.getHeight()-30);
		g.drawString("Bag",gc.getWidth()-90, gc.getHeight()-70);
		g.drawString("Flee",gc.getWidth()-90, gc.getHeight()-30);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if (exit){
			exit = false;
			game.enterState(1);
		}
		
	}
	
	
	public void keyPressed(int key, char c){
		if(key == Input.KEY_ESCAPE){
			exit = true;
		}
	}

	@Override
	public int getID() {
		return 2;
	}

}
