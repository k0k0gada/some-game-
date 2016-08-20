package monsters;

import characters.Hero;
import items.Potion;
import main.Main;

public class BossMonsters extends Monster {
	private final int EXP_DIFFICULTY_MODIFIER = 8;
	private final int CHANCE_TO_DROP_MULTIPLE_POTIONS = 60;
	private final double COIN_DIFICULTY_MODIFIER = 8;
	private final double HP_DMG_DIFFICULTY_MODIFIER = (8 + 3 * Main.rd.nextDouble());
	private final double DEF_DIFFICULTY_MODIFIER = (6 + 3 * Main.rd.nextDouble());
	private final int MAX_ATTACK_SPEED_MODIFIER = 7;
	private final int MAX_CRIT_CHANCE_MODIFIER = 75;
	private final double MAX_CRIT_MULTIPLIER_MODIFIER = 5;

	public BossMonsters(Hero enemy) {
		super(enemy);
		this.setType("BOSS");

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

	public BossMonsters(Hero enemy, int level) {
		super(enemy, level);
		this.setType("BOSS");

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
		if (level == 12) {// for ultimate boss ^_^
			this.setAttackSpeed(2);
			this.setCritChance(95);
			this.setCritMultiplier(8);
		}
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
