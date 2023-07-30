
/*
Author:Vishal Vincentsundar
Date: 6/23/2022
Description: contains all the logic to run the game in order to play the game and also to auto play the game.
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Adventure {
	private static final Hero[] heroes = new Hero[4]; // initializing array for heroes
	private static ArrayList<Enemy> dungeon = new ArrayList<Enemy>(); // initializing dungeon array list
	private static final Scanner s = new Scanner(System.in);
	private int gold = 0;
	private static int totalGold = 0; // initializes variable for sum of gold

	public static void main(String[] args) {
		System.out.println(
				"Welcome to the Dastardly Dungeon!\nWould you like to:\n1 - Determine the length of the Dungeon\n2 - Run a random length Dungeon\n-1 - Exit"); // printing the prompt
		int choice = s.nextInt();
		if (choice == 1) {
			System.out.println("Enter length of dungeon: "); // prompting user to enter dungeon length
			int dungeonLength = s.nextInt();

			Enemy currentEnemy = Enemy.randomEnemy(); // generates random enemy
			dungeon.add(currentEnemy); // adding current enemy to dungeon
			for (int i = 1; i < dungeonLength; i++) { // looping through dungeon length to generate enemy neighbors and
														// add them to dungeon
				Enemy nextEnemy = currentEnemy.generateNeighbour();
				dungeon.add(nextEnemy);
				currentEnemy = nextEnemy;
			}
		} else if (choice == 2) { // generates random dungeon length

			int dungeonLength = new Random().nextInt(10, 51);
			Enemy currentEnemy = Enemy.randomEnemy();
			dungeon.add(currentEnemy);
			for (int i = 1; i < dungeonLength; i++) { // generates random neighbors
				Enemy nextEnemy = currentEnemy.generateNeighbour();
				dungeon.add(nextEnemy);
				currentEnemy = nextEnemy;
			}
		} else { // exits game
			System.exit(0);
		}
		Adventure adventure = new Adventure();

		System.out.println("Would you like to: \n1 - Play\n2 - Auto-play mode");
		choice = s.nextInt();
		if (choice == 1) { // user plays game
			for (int i = 0; i < dungeon.size(); i++) {
				adventure.runAdventure(dungeon.get(i), i + 1);
			}
			String survivingHeros = " ";
			boolean anySurvivors = false; // exits code is there are no hero survivors
			for (Hero hero : heroes) { // prints surviving heroes
				if (hero.getCurrentHitPoints() > 0) {
					anySurvivors = true;
					survivingHeros = survivingHeros + hero.getJob() + ", ";
				}
			}
			if (anySurvivors) { // message that displays when player wins
				System.out.println("Congratulations!You have defeated the dungeon monsters!\nYour final Gold Total: "
						+ totalGold + "\nSurviving heroes:" + survivingHeros);
			} else {
				System.out.println("GAME OVER.");
			}
		} else if (choice == 2) { // this condition is executed for auto mode, does the same thing as choice
									// 1(would you like to play),
									// but through random number generation for hero choice
			for (int i = 0; i < dungeon.size(); i++) {
				adventure.runAutoAdventure(dungeon.get(i), i + 1);
			}
			String survivingHeros = " ";
			boolean anySurvivors = false;
			for (Hero hero : heroes) {
				if (hero.getCurrentHitPoints() > 0) {
					anySurvivors = true;
					survivingHeros = survivingHeros + hero.getJob() + ", ";
				}
			}
			if (anySurvivors) {
				System.out.println("Congratulations!You have defeated the dungeon monsters!\nYour final Gold Total: "
						+ totalGold + "\nSurviving heroes:" + survivingHeros);
			} else {
				System.out.println("GAME OVER.");
			}
		}
	}

	private Hero randomHero() { // method using random number generator to assign 4 random heroes categories
		int probability = new Random().nextInt(0, 4);
		if (probability == 0) {
			return new Wizard();
		} else if (probability == 1) {
			return new Rogue();
		} else if (probability == 2) {
			return new Barbarian();
		} else {
			return new Paladin();
		}
	}

	public Adventure() {
		System.out.println(
				"Would you like:\n1 - A standard party (Wizard, Rogue, Barbarian, Paladin)\n2 - A random party\n-1 - Exit");
		int choice = s.nextInt();
		if (choice == 1) { // creates standard party of heroes
			heroes[0] = new Wizard();
			heroes[1] = new Rogue();
			heroes[2] = new Barbarian();
			heroes[3] = new Paladin();
		} else if (choice == 2) { // executes randomHero to get 4 random hero categories
			for (int i = 0; i < 4; i++)
				heroes[i] = randomHero();
		} else if (choice == -1) { // exits game
			System.exit(0);
		}
	}

	public void runAdventure(Enemy enemy, int roomNumber) {

		int i = 1;
		int currentEnemyHitPoint = enemy.getHitPoints(); // executes the accessor method for getting enemy hit points
		int currentHeroHitPoint = 0; // initializes hero's hit points to 0
		int choiceHero = 0;
		do {
			System.out.println("Room #" + roomNumber + " has:\n" + enemy);
			System.out.println("\nParty:");
			for (int index = 0; index < heroes.length; index++) {
				System.out.println("Hero #" + (index + 1) + ":");
				System.out.println(heroes[index]);
			}
			System.out.print("Round #" + i + "\n");
			gold = (int) (roomNumber - i + 1 * new Random().nextInt(100, 201));
			i++;
			System.out.println("Which Hero will engage in Combat? >>\n");
			choiceHero = s.nextInt();

			if (heroes[choiceHero - 1].getHitPoints() <= 0) {
				System.out.println("This hero has died. Please enter any key to continue choosing other hero.");
				s.next();
			} else {
				float attack = heroes[choiceHero - 1].launchAttack(enemy.getDefense());
				int enemyDamage = enemy.takeDamage(attack);
				currentEnemyHitPoint = currentEnemyHitPoint - enemyDamage; // calculates the enemies hit points after
																			// each round
				enemy.setHitPoints(currentEnemyHitPoint);

				currentHeroHitPoint = heroes[choiceHero - 1].getHitPoints();
				int heroDamage = heroes[choiceHero - 1].takeDamage(enemy.getAttack()); // calculates hero's damage by
																						// calling the getAttack
				currentHeroHitPoint = currentHeroHitPoint - heroDamage;
				heroes[choiceHero - 1].setCurrentHitPoints(currentHeroHitPoint);

				System.out.println(heroes[choiceHero - 1].getJob() + " attacks and does " + enemyDamage + " Damage!");
				System.out.println("The " + enemy.enemyName + " attacks and does " + heroDamage + " Damage!\n");

			}
			totalGold = totalGold + gold;

		} while (currentEnemyHitPoint > 0);
		int probability = new Random().nextInt(1, 101);
		if (probability <= 10) {
			boolean anyHeroDead = false;
			for (i = 0; i < heroes.length; i++) {
				if (heroes[i].getCurrentHitPoints() == 0) {
					heroes[i] = randomHero();
					anyHeroDead = true;
					break;
				}
			}
			if (!anyHeroDead) {
				gold = (int) (gold * 1.2);
			}

		} else if (probability <= 35 && probability > 10) {
			heroes[choiceHero - 1].levelUpAttack();
		} else if (probability <= 60 && probability > 35) {
			heroes[choiceHero - 1].levelUpDefense();
		} else if (probability <= 95 && probability > 60) {
			gold = (int) (gold * 1.2);
		} else {
			heroes[choiceHero - 1].levelUpAttack();
			heroes[choiceHero - 1].levelUpDefense();
		}
		if (currentEnemyHitPoint == 0) {
			for (Hero hero : heroes) {
				int missingPoints = hero.hitPoints - hero.currentHitPoints;
				hero.heal(missingPoints / 2);
			}
		}
		totalGold = totalGold + gold;
	}

	public void runAutoAdventure(Enemy enemy, int roomNumber) { // same as runAdventure method but auto version

		int i = 1;
		int currentEnemyHitPoint = enemy.getHitPoints();
		int currentHeroHitPoint = 0;
		int choiceHero = 0;
		do {
			System.out.println("Room #" + roomNumber + " has:\n" + enemy);
			System.out.println("\nParty:");
			for (int index = 0; index < heroes.length; index++) {
				System.out.println("Hero #" + (index + 1) + ":");
				System.out.println(heroes[index]);
			}
			System.out.print("Round #" + i + "\n");
			gold = (int) (roomNumber - i + 1 * new Random().nextInt(100, 201));
			i++;
			System.out.println("Which Hero will engage in Combat? >>\n");
			choiceHero = new Random().nextInt(1, 5);

			if (heroes[choiceHero - 1].getHitPoints() <= 0) {
				System.out.println("This hero has died. Please enter any key to continue choosing other hero.");
				s.next();
			} else {
				float attack = heroes[choiceHero - 1].launchAttack(enemy.getDefense());
				int enemyDamage = enemy.takeDamage(attack);
				currentEnemyHitPoint = currentEnemyHitPoint - enemyDamage;
				enemy.setHitPoints(currentEnemyHitPoint);

				currentHeroHitPoint = heroes[choiceHero - 1].getHitPoints();
				int heroDamage = heroes[choiceHero - 1].takeDamage(enemy.getAttack());
				currentHeroHitPoint = currentHeroHitPoint - heroDamage;
				heroes[choiceHero - 1].setCurrentHitPoints(currentHeroHitPoint);

				System.out.println(heroes[choiceHero - 1].getJob() + " attacks and does " + enemyDamage + " Damage!");
				System.out.println("The " + enemy.enemyName + " attacks and does " + heroDamage + " Damage!\n");

			}
			totalGold = totalGold + gold;

		} while (currentEnemyHitPoint > 0);
		int probability = new Random().nextInt(1, 101);
		if (probability <= 10) {
			boolean anyHeroDead = false;
			for (i = 0; i < heroes.length; i++) {
				if (heroes[i].getCurrentHitPoints() == 0) {
					heroes[i] = randomHero();
					anyHeroDead = true;
					break;
				}
			}
			if (!anyHeroDead) {
				gold = (int) (gold * 1.2);
			}

		} else if (probability <= 35 && probability > 10) {
			heroes[choiceHero - 1].levelUpAttack();
		} else if (probability <= 60 && probability > 35) {
			heroes[choiceHero - 1].levelUpDefense();
		} else if (probability <= 95 && probability > 60) {
			gold = (int) (gold * 1.2);
		} else {
			heroes[choiceHero - 1].levelUpAttack();
			heroes[choiceHero - 1].levelUpDefense();
		}
		if (currentEnemyHitPoint == 0) {
			for (Hero hero : heroes) {
				int missingPoints = hero.hitPoints - hero.currentHitPoints;
				hero.heal(missingPoints / 2);
			}
		}
		totalGold = totalGold + gold;
	}
}
