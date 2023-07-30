
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the hero category Paladin
*/
import java.util.Random;

public class Paladin extends Hero {
	public Paladin() {
		super.attack = new Random().nextFloat(4.0F, 8.0F + Float.MIN_VALUE);
		super.defense = new Random().nextFloat(0.7F, 0.95F + Float.MIN_VALUE);
		super.jobName = "Paladin";
	}

	public float launchAttack(float modifier) {

		return super.currentHitPoints = (int) ((attack * modifier) + (0.5F * attack));
	}

	public int takeDamage(float modifier) {
		if (currentHitPoints >= 5) {
			int probability = new Random().nextInt(1, 101);
			if (probability <= 20) {
				super.currentHitPoints = super.currentHitPoints / 2;
				if (super.currentHitPoints < 5) {
					super.currentHitPoints = 5;
				}
			}
		} else {
			super.currentHitPoints -= (int) ((modifier * (1 - defense)));
		}
		return this.currentHitPoints;
	}
}