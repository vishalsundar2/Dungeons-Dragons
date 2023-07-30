
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the enemy category LizardFolk
*/
import java.util.Random;

public class LizardFolk extends Enemy {
	public LizardFolk() {
		super.defenseModifier = new Random().nextFloat(0.0F, 0.3F + Float.MIN_VALUE);
		super.attackModifier = new Random().nextFloat(12, 30 + Float.MIN_VALUE);
		super.enemyName = "LizardFolk";

	}

	@Override
	public Enemy generateNeighbour() {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 60) {
			return new LizardFolk();
		} else if (probability <= 90 && probability > 60) {
			return new Kobold();
		} else if (probability <= 95 && probability > 90) {
			return new Goblin();
		} else {
			return new HobGoblin();
		}

	}

}
