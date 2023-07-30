import java.util.Random;

public abstract class Hero {
	protected int maxHitPoints;
	protected int currentHitPoints;
	protected float attack;
	protected float defense;
	protected String jobName;
	protected int hitPoints;

	public Hero() {
		this.hitPoints = new Random().nextInt(50, 101);
		this.currentHitPoints = this.hitPoints;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public int getCurrentHitPoints() {
		return this.currentHitPoints;
	}

	public void setCurrentHitPoints(int currentHitPoints) {
		if (currentHitPoints < 0) {
			this.currentHitPoints = 0;
		} else {
			this.currentHitPoints = currentHitPoints;
		}
	}

	public float getAttack() {
		return this.attack;
	}

	public float getDefense() {
		return this.defense;
	}

	public String getJob() {
		return this.jobName;
	}

	public abstract float launchAttack(float modifier);

	public abstract int takeDamage(float modifier);

	public void levelUpAttack() {
		this.attack = this.attack + 0.5F;

	}

	public void levelUpDefense() {
		this.defense = this.defense + 0.05F;
		if (this.defense > 0.97) {
			this.defense = 0.97F;
		}

	}

	public void heal() {
		this.currentHitPoints = this.currentHitPoints + (int) 0.5 * (maxHitPoints - currentHitPoints);
	}

	public void heal(int hitPoints) {
		this.currentHitPoints = this.currentHitPoints + hitPoints;
	}

	public String toString() {
		return this.jobName + "\nAtt: " + String.format("%.2f", this.attack) + "\nDef: "
				+ String.format("%.2f", this.defense) + "\nHitPoints: " + this.currentHitPoints + "\n";
	}

}
