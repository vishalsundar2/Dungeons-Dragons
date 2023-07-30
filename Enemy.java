
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: Abstract Base Class containing the logic for enemies.
*/
import java.util.Random;

public abstract class Enemy { // declaring variables for enemy
	protected int hitPoints;
	protected float defenseModifier;
	protected float attackModifier;
	protected String enemyName;

	public Enemy() { // constructor for enemy initializing hit points
		this.hitPoints = new Random().nextInt(100, 201);

	}

	public abstract Enemy generateNeighbour(); // abstract class for randomly generating enemy neighbors

	public int takeDamage(float damage) { // converts enemy damage from float to int
		return (int) damage;
	}

	public float getAttack() { // accessor for attack modifier in enemy child classes
		return this.attackModifier;
	}

	public float getDefense() { // accessor for defense modifier in enemy child classes
		return this.defenseModifier;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		if (hitPoints < 0) {
			this.hitPoints = 0;
		} else {
			this.hitPoints = hitPoints;
		}
	}

	public static Enemy randomEnemy() {
		int probability = new Random().nextInt(0, 5);
		if (probability == 0) {
			return new Goblin();
		} else if (probability == 1) {
			return new HobGoblin();
		} else if (probability == 2) {
			return new Kobold();
		} else {
			return new LizardFolk();
		}

	}

	public String toString() {
		return this.enemyName + " - " + getHitPoints() + " Hit-Points";
	}

}