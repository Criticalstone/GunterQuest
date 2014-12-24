import org.newdawn.slick.Image;
		import org.newdawn.slick.SlickException;


public class Player {
	Image player;
	public Player(){
		try {
			player = new Image("res/Gunter/Fram/GunterFram.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Unable to load " + e);
		}
	}
	public Image getImage(){
		return player;
	}
}
