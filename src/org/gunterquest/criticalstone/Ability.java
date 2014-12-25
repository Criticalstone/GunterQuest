package org.gunterquest.criticalstone;

public class Ability {
	private String name, description;
	private int pp, damage;
	private float accuracy;

	public Ability(String name, int pp, int damage, float accuracy, String description){
		this.name = name; 
		this.description = description;
		this.pp = pp;
		this.damage = damage;
		this.accuracy = accuracy;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public int getPP(){
		return this.pp;
	}
	
	public float getAccuracy(){
		return this.accuracy;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
}
