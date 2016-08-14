package monsters;

import characters.Hero;
import main.Main;

public class EasyMonster extends Monster {
	final double HP_DMG_DIFFICULTY_MODIFIER = (0.5 + Main.rd.nextDouble());

	final double DEF_DIFFICULTY_MODIFIER = (0.7 + Main.rd.nextDouble());
	final int MAX_ATTACK_SPEED_MODIFIER = 6;
	final int MAX_CRIT_CHANCE_MODIFIER = 75;
	final double MAX_CRIT_MULTIPLIER_MODIFIER = 5;

	EasyMonster(Hero enemy) {
		super(enemy);
		this.setType("easy");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (getDefence() * this.DEF_DIFFICULTY_MODIFIER));
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
