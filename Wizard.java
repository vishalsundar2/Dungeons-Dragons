
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the hero category Wizard
*/
import java.util.Random;

public class Wizard extends Hero {
	public Wizard() {
		super.attack = new Random().nextFloat(5.0F, 10.0F + Float.MIN_VALUE);
		super.defense = new Random().nextFloat(0.1F, 0.3F + Float.MIN_VALUE);
		super.maxHitPoints = (int) (super.maxHitPoints * 0.35);
		super.jobName = "Wizard";
	}

	public float launchAttack(float modifier) {
		return (2.0F * attack * modifier) + 5.0F;
	}

	public int takeDamage(float modifier) {
		return currentHitPoints -= (modifier * (1 - defense));
	}
}
