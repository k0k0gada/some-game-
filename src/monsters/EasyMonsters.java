package monsters;

import characters.Hero;
import items.Potion;
import main.Main;

public class EasyMonsters extends Monster {
	private final int EXP_DIFFICULTY_MODIFIER = 1;
	private final int CHANCE_TO_DROP_MULTIPLE_POTIONS = 50;
	private final double COIN_DIFICULTY_MODIFIER = 1.2;
	// dif mod about~ 0.5
	private final double HP_DMG_DIFFICULTY_MODIFIER = (0.5 + 0.70*Main.rd.nextDouble());
	private final double DEF_DIFFICULTY_MODIFIER = (0.7 + Main.rd.nextDouble());
	private final int MAX_ATTACK_SPEED_MODIFIER = 45;
	private final int MAX_CRIT_CHANCE_MODIFIER = 35;
	private final double MAX_CRIT_MULTIPLIER_MODIFIER = 1.8;

	public EasyMonsters(Hero enemy) {
		super(enemy);
		this.setType("easy");

		System.out.println("An " + getType() + " Monster was created");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (this.getDefence() * this.DEF_DIFFICULTY_MODIFIER));
		this.setAttackSpeed((int) (this.getAttackSpeed() / 1.3));
		if (this.getAttackSpeed() < MAX_ATTACK_SPEED_MODIFIER) {
			this.setAttackSpeed(MAX_ATTACK_SPEED_MODIFIER);
		}

		if (this.getCritChance() > MAX_CRIT_CHANCE_MODIFIER) {
			this.setCritChance(MAX_CRIT_CHANCE_MODIFIER);
		}
		this.setCritMultiplier(MAX_CRIT_MULTIPLIER_MODIFIER);
	}

	public EasyMonsters(Hero enemy, int level) {
		super(enemy, level);
		this.setType("easy");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (this.getDefence() * this.DEF_DIFFICULTY_MODIFIER));
		this.setAttackSpeed((int) (this.getAttackSpeed() / 1.3));
		if (this.getAttackSpeed() < MAX_ATTACK_SPEED_MODIFIER) {
			this.setAttackSpeed(MAX_ATTACK_SPEED_MODIFIER);
		}

		if (this.getCritChance() > MAX_CRIT_CHANCE_MODIFIER) {
			this.setCritChance(MAX_CRIT_CHANCE_MODIFIER);
		}
		this.setCritMultiplier(MAX_CRIT_MULTIPLIER_MODIFIER);
	}

	@Override
	public int dropCoins() {
		int dropCoins = super.dropCoins();
		dropCoins *= COIN_DIFICULTY_MODIFIER;
		return dropCoins;
	}

	@Override
	public Potion dropPotion() {
		Potion p = super.dropPotion();
		int amount = 0;
		for (int i = 0; i < COIN_DIFICULTY_MODIFIER; i++) {
			if (Main.randomNumTo100() < CHANCE_TO_DROP_MULTIPLE_POTIONS) {
				amount++;
			}
		}
		if (amount > 0) {
			p.setAmount(amount);
		} else {
			p = null;
		}
		return p;

	}

	@Override
	public int giveEXP() {
		return super.giveEXP() * EXP_DIFFICULTY_MODIFIER;
	}

}
