package monsters;

import characters.Hero;
import main.Main;

public class EasyMonster extends Monster {
	// dif mod about~ 0.5
	final double HP_DMG_DIFFICULTY_MODIFIER = (0.5 + Main.rd.nextDouble());
	final double DEF_DIFFICULTY_MODIFIER = (0.7 + Main.rd.nextDouble());
	final int MAX_ATTACK_SPEED_MODIFIER = 30;
	final int MAX_CRIT_CHANCE_MODIFIER = 35;
	final double MAX_CRIT_MULTIPLIER_MODIFIER = 1.8;

	public EasyMonster(Hero enemy) {
		super(enemy);
		this.setType("easy");
		this.setDmg((int) (this.getDmg() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setMAXHP((int) (this.getMAXHP() * this.HP_DMG_DIFFICULTY_MODIFIER));
		this.setHP(this.getMAXHP());

		this.setDefence((int) (this.getDefence() * this.DEF_DIFFICULTY_MODIFIER));
		this.setAttackSpeed((int) (this.getAttackSpeed() /1.3));
		if (this.getAttackSpeed() < MAX_ATTACK_SPEED_MODIFIER) {
			this.setAttackSpeed(MAX_ATTACK_SPEED_MODIFIER);
		}

		if (this.getCritChance() > MAX_CRIT_CHANCE_MODIFIER) {
			this.setCritChance(MAX_CRIT_CHANCE_MODIFIER);
		}
		this.setCritMultiplier(MAX_CRIT_MULTIPLIER_MODIFIER);
	}

}
