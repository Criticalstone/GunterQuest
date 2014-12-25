import org.newdawn.slick.Image;
		import org.newdawn.slick.SlickException;

import java.nio.file.Paths;


public class Player {
	Image player;
	Mutant[] mutants;
	public Player(){
		try {
			player = new Image("res/Gunter/Fram/GunterFram.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Unable to load " + e);
		}
		mutants = new Mutant[30];
		mutants[0] = new Mutant(Paths.get("src", "res", "Lores_Attacks", "002Osquatch.txt"));
	}
	public Image getImage(){
		return player;
	}
}
