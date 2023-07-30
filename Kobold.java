
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the enemy category Kobold
*/
import java.util.Random;

public class Kobold extends Enemy {
	public Kobold() {
		super.defenseModifier = new Random().nextFloat(0.3F, 0.6F + Float.MIN_VALUE);
		super.attackModifier = new Random().nextFloat(3, 15 + Float.MIN_VALUE);
		super.enemyName = "Kobold";

	}

	@Override
	public Enemy generateNeighbour() {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 50) {
			return new Kobold();
		} else if (probability <= 85 && probability > 50) {
			return new LizardFolk();
		} else if (probability <= 95 && probability > 85) {
			return new HobGoblin();
		} else {
			return new Goblin();
		}

	}

}
