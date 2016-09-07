package monsters;

import characters.Hero;
import items.Potion;
import main.Main;

public class HardMonsters extends Monster {

	private final int EXP_DIFFICULTY_MODIFIER = 4;
	private final int CHANCE_TO_DROP_MULTIPLE_POTIONS = 50;
	private final double COIN_DIFICULTY_MODIFIER = 4;
	// dif mod about ~ 3.5
	private final double HP_DMG_DIFFICULTY_MODIFIER = (3.3 + Main.rd.nextDouble());
	private final double DEF_DIFFICULTY_MODIFIER = (3.4 + Main.rd.nextDouble());
	private final int MAX_ATTACK_SPEED_MODIFIER = 25;
	private final int MAX_CRIT_CHANCE_MODIFIER = 55;
	private final double MAX_CRIT_MULTIPLIER_MODIFIER = 3;

	public HardMonsters(Hero enemy) {
		super(enemy);
		this.setType("strong");

		System.out.println("A " + getType() + " Monster was created");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (this.getDefence() * this.DEF_DIFFICULTY_MODIFIER));
		this.setAttackSpeed(this.getAttackSpeed() / 2);
		if (this.getAttackSpeed() < MAX_ATTACK_SPEED_MODIFIER) {
			this.setAttackSpeed(MAX_ATTACK_SPEED_MODIFIER);
		}

		if (this.getCritChance() > MAX_CRIT_CHANCE_MODIFIER) {
			this.setCritChance(MAX_CRIT_CHANCE_MODIFIER);
		}
		this.setCritMultiplier(MAX_CRIT_MULTIPLIER_MODIFIER);
	}

	public HardMonsters(Hero enemy, int level) {
		super(enemy, level);
		this.setType("strong");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (this.getDefence() * this.DEF_DIFFICULTY_MODIFIER));
		this.setAttackSpeed(this.getAttackSpeed() / 2);
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
		int amount = 1;
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
