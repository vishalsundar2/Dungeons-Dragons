
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains the logic for the enemy category Goblin
*/
import java.util.Random;

public class Goblin extends Enemy {
	public Goblin() {
		super.defenseModifier = 1.0F;
		super.attackModifier = new Random().nextFloat(6, 12 + Float.MIN_VALUE);
		super.enemyName = "Goblin";
	}

	@Override
	public Enemy generateNeighbour() {
		int probability = new Random().nextInt(1, 101);
		if (probability <= 60) {
			return new Goblin();
		} else if (probability <= 85 && probability > 60) {
			return new HobGoblin();
		} else if (probability <= 95 && probability > 85) {
			return new Kobold();
		} else {
			return new LizardFolk();
		}

	}

}
