
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the hero category Barbarian
*/
import java.util.Random;

public class Barbarian extends Hero {
	public Barbarian() {
		super.attack = new Random().nextFloat(5.0F, 10.0F + Float.MIN_VALUE);
		super.defense = new Random().nextFloat(0.1F, 0.3F + Float.MIN_VALUE);
		super.maxHitPoints = (int) (super.maxHitPoints * 1.15);
		super.jobName = "Barbarian";
	}

	public float launchAttack(float modifier) {
		float bonus = 5 * (1.0F - modifier);

		return (int) (attack + bonus);
	}

	public int takeDamage(float modifier) {

		return (int) (modifier * (1 - defense));
	}
}
