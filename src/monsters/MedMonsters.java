package monsters;

import characters.Hero;
import main.Main;

public class MedMonsters extends Monster {
	// dif mod about~ 1.5
	final double HP_DMG_DIFFICULTY_MODIFIER = (1.5 + Main.rd.nextDouble());
	final double DEF_DIFFICULTY_MODIFIER = (1.7 + Main.rd.nextDouble());
	final int MAX_ATTACK_SPEED_MODIFIER = 20;
	final int MAX_CRIT_CHANCE_MODIFIER = 45;
	final double MAX_CRIT_MULTIPLIER_MODIFIER = 2.1;

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

}
