
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the hero category Rogue
*/
import java.util.Random;

public class Rogue extends Hero {
	public Rogue() {
		super.attack = new Random().nextFloat(8.0F, 12.0F + Float.MIN_VALUE);
		super.defense = new Random().nextFloat(0.4F, 0.6F + Float.MIN_VALUE);
		super.maxHitPoints = (int) (super.maxHitPoints * 0.6);
		super.jobName = "Rogue";
	}

	public float launchAttack(float modifier) {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 20) {
			super.currentHitPoints = 2 * (int) attack;
		} else {
			super.currentHitPoints = (int) attack;
		}
		return super.currentHitPoints;
	}

	public int takeDamage(float modifier) {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 20) {
			return super.currentHitPoints;
		} else {
			return super.currentHitPoints -= (modifier * (1 - defense));
		}
	}
}
