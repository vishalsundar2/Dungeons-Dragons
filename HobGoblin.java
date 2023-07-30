
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the enemy category HobGoblin
*/
import java.util.Random;

public class HobGoblin extends Enemy {
	public HobGoblin() {
		super.defenseModifier = new Random().nextFloat(0.6F, 0.8F + Float.MIN_VALUE);
		super.attackModifier = new Random().nextFloat(12, 20 + Float.MIN_VALUE);
		super.enemyName = "HobGoblin";

	}

	@Override
	public Enemy generateNeighbour() {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 40) {
			return new HobGoblin();
		} else if (probability <= 65 && probability > 40) {
			return new Goblin();
		} else if (probability <= 90 && probability > 65) {
			return new Kobold();
		} else {
			return new LizardFolk();
		}

	}

}
