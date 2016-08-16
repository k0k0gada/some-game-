package monsters;

import characters.Hero;
import items.Potion;
import main.Main;

public class MedMonsters extends Monster {

	private  final int EXP_DIFFICULTY_MODIFIER = 2;
	private final int CHANCE_TO_DROP_MULTIPLE_POTIONS = 50;
	private final double COIN_DIFICULTY_MODIFIER = 2;
	// dif mod about~ 1.5
	private final double HP_DMG_DIFFICULTY_MODIFIER = (1.5 + Main.rd.nextDouble());
	private final double DEF_DIFFICULTY_MODIFIER = (1.7 + Main.rd.nextDouble());
	private final int MAX_ATTACK_SPEED_MODIFIER = 20;
	private final int MAX_CRIT_CHANCE_MODIFIER = 45;
	private final double MAX_CRIT_MULTIPLIER_MODIFIER = 2.1;

	public MedMonsters(Hero enemy) {
		super(enemy);
		this.setType("medium");
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

	public MedMonsters(Hero enemy, int level) {
		super(enemy, level);
		this.setType("medium");
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
		p.setAmount(amount);
		return p;

	}

	@Override
	public int giveEXP() {
		return super.giveEXP() * EXP_DIFFICULTY_MODIFIER;
	}
}
