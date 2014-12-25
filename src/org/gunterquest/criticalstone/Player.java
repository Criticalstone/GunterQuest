package org.gunterquest.criticalstone;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;


public class Player {
	Image player;
	ArrayList<Mutant> mutants;
	public Player(){
		try {
			player = new Image("res/Gunter/Fram/GunterFram.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Unable to load " + e);
		}
		mutants = new ArrayList<Mutant>();
	}

	public void addMutant(Mutant mutant){
		if(mutants.size() < 6){
			mutants.add(mutant);
		}
	}

	public Mutant getMutant(int index){
		return mutants.get(index);
	}

	public Image getImage(){
		return player;
	}
}
