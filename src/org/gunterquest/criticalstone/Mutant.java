package org.gunterquest.criticalstone;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Mutant {
	private int[] attr;
	private int[] attrMod;
	
	private static int MAX_HEALTH;
	private int level;
	private int exp;
	private String name;
	private Ability[] abilities;
	
	public Mutant(Path path){
		List<String> fileLines = null;
		attr = new int[5];
		attrMod = new int[5];
		abilities = new Ability[4];
		level = 1;
		exp = 0;
		
		//read file 
		try {
			fileLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		name = fileLines.get(1).trim();
		
		//get attributes from textfile
		for(int i=0; i < 5; i++){
			attr[i] = getAttr(fileLines.get(i+9));
			attrMod[i] = getMod(fileLines.get(i+9));
		}	
		
		MAX_HEALTH = attr[0];
		
		//create the abilities
		for(int i=0; i < 4; i++){
			String name, description;
			int pp, dmg;
			float accuracy;
			int delta = i*8;
			name = fileLines.get(18+delta).split("PP")[0];
			pp = Integer.parseInt(fileLines.get(18+delta).split("PP")[1].trim());
			dmg = Integer.parseInt(fileLines.get(20+delta).split(" ")[1].trim());
			accuracy = Float.parseFloat(fileLines.get(21+delta).split(" ")[1].trim());
			description = fileLines.get(23+delta);
						
			abilities[i] = new Ability(name, pp, dmg, accuracy, description);
		}		
	}
	
	private int getAttr(String line){
		String[] lineList = line.split(" ");
		return Integer.parseInt(lineList[1]);
	}
	
	private int getMod(String line){
		String[] lineList = line.split(" ");
		return Integer.parseInt(lineList[2]);
	}
	
	public int getHealth(){
		return attr[0];
	}
	
	public int getMaxHealth(){
		return MAX_HEALTH + attrMod[0]*getLevel();
	}

	public int getAttack(){
		return attr[1] + attrMod[1]*getLevel();
	}
	
	public int getDefense(){
		return attr[2] + attrMod[2]*getLevel();
	}
	
	public int getSpecial(){
		return attr[3] + attrMod[3]*getLevel();
	}
	
	public int getSpeed(){
		return attr[4] + attrMod[4]*getLevel();
	}
	
	public int getLevel(){
		return this.level;
	}

	public String getName(){
		return this.name;
	}
	
	public Ability getAbility(int index){
		return this.abilities[index];
	}
}
