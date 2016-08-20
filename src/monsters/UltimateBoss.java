package monsters;

public class UltimateBoss extends BossMonsters {

	public UltimateBoss(int level) {
		super(level);
		this.setAttackSpeed(2);
		this.setCritChance(85);
		this.setCritMultiplier(8);
		this.setDmg((int) (this.getDmg() * 1.5));
		this.setMAXHP(this.getMAXHP() * 2);
	}

}
