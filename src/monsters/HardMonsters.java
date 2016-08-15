package monsters;

import characters.Hero;
import main.Main;

public class HardMonsters extends Monster {
	// dif mod about ~ 3.5
	final double HP_DMG_DIFFICULTY_MODIFIER = (3.3 + Main.rd.nextDouble());
	final double DEF_DIFFICULTY_MODIFIER = (3.4 + Main.rd.nextDouble());
	final int MAX_ATTACK_SPEED_MODIFIER = 15;
	final int MAX_CRIT_CHANCE_MODIFIER = 55;
	final double MAX_CRIT_MULTIPLIER_MODIFIER = 3;

	public HardMonsters(Hero enemy) {
		super(enemy);
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

}
