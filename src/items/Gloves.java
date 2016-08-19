package items;

import characters.Hero;

public class Gloves extends Item {
	private final int GLOVES_CONSTANT_GEMS_NEEDED = 3;
	private final int COINS_COST_PER_LEVEL_TILL_LEVEL5 = 120;
	private final int COINS_COST_PER_LEVEL_AFTER_LEVEL5 = 180;

	public Gloves(String name) {
		super(name);
		this.setType("gloves");
	}

	public void upgradeGloves(Hero hero) {
		Gloves g = hero.getGloves();
		int coins = hero.getCoins();
		int gems = hero.getGems();
		if (g.getLevel() < GLOVES_CONSTANT_GEMS_NEEDED) {
			if (coins > getCoinCost() && gems > getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (hero.yesNoDecision()) {
					System.out.println(g.toString());
					setGlovesLevelStats(g.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(g.toString());
				} else {
					System.out.println("you didn't upgrade anything .. ?");
				}
			} else {
				if (!(coins > getCoinCost())) {
					System.out.println("not enough coins!");
				}
				if (!(gems > getGemCost())) {
					System.out.println("not enough gems!");
				}
			}
		} else {
			if (coins >getCoinCost()&& gems >= getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (hero.yesNoDecision()) {
					System.out.println(g.toString());
					setGlovesLevelStats(g.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(g.toString());
				} else {
					System.out.println("you didn't upgrade anything .. ?");
				}
			} else {
				if (!(coins > getCoinCost())) {
					System.out.println("not enough coins!");
				}
				if (!(gems >getGemCost())) {
					System.out.println("not enough gems!");
				}
			}

		}
	}

	void setGlovesLevelStats(int level) {
		switch (level) {
		case 1:
			setName("silk gloves");
			setLevel(level);
			setDefInc(1);
			setCritMultiplierInc(-0.1);
			setAttackInc(1);
			setMaxHPInc(1);
			setSpeedInc(1);
			setCritChanceInc(0);
			break;
		case 2:
			setName("leather gloves");
			setLevel(level);
			setDefInc(2);
			setCritMultiplierInc(0);
			setAttackInc(2);
			setMaxHPInc(2);
			setSpeedInc(1);
			setCritChanceInc(0);
			break;
		case 3:
			setName("better leather gloves");
			setLevel(level);
			setDefInc(3);
			setCritMultiplierInc(0.1);
			setAttackInc(2);
			setMaxHPInc(4);
			setSpeedInc(2);
			setCritChanceInc(0);
			break;
		case 4:
			setName("further better leather gloves");
			setLevel(level);
			setDefInc(4);
			setCritMultiplierInc(0.2);
			setAttackInc(3);
			setMaxHPInc(4);
			setSpeedInc(3);
			setCritChanceInc(0);
			break;
		case 5:
			setName("aligator leather gloves");
			setLevel(level);
			setDefInc(5);
			setCritMultiplierInc(0.25);
			setAttackInc(4);
			setMaxHPInc(5);
			setSpeedInc(4);
			setCritChanceInc(1);
			break;
		case 6:
			setName("dragon leather gloves");
			setLevel(level);
			setDefInc(6);
			setCritMultiplierInc(0.3);
			setAttackInc(5);
			setMaxHPInc(6);
			setSpeedInc(4);
			setCritChanceInc(2);
			break;
		case 7:
			setName("black dragon leather gloves");
			setLevel(level);
			setDefInc(7);
			setCritMultiplierInc(0.3);
			setAttackInc(7);
			setMaxHPInc(7);
			setSpeedInc(5);
			setCritChanceInc(2);
			break;
		case 8:
			setName("pretty good gloves");
			setLevel(level);
			setDefInc(8);
			setCritMultiplierInc(0.38);
			setAttackInc(7);
			setMaxHPInc(8);
			setSpeedInc(5);
			setCritChanceInc(3);
			break;
		case 9:
			setName("almost ultimate gloves");
			setLevel(level);
			setDefInc(9);
			setCritMultiplierInc(0.42);
			setAttackInc(8);
			setMaxHPInc(9);
			setSpeedInc(6);
			setCritChanceInc(3);
			break;
		case 10:
			setName("ultimate gloves");
			setLevel(level);
			setDefInc(10);
			setCritMultiplierInc(0.45);
			setAttackInc(9);
			setMaxHPInc(15);
			setSpeedInc(6);
			setCritChanceInc(3);
			break;
		default:
			setName("ULTIMATE GLOVES level" + (getLevel() - 9));
			setLevel(getLevel() + 1);
			setDefInc(getDefInc() + 1);
			setCritMultiplierInc(getCritMultiplierInc() + 0.05);
			setMaxHPInc(getMaxHPInc() + 1);
			if (getLevel() % 2 == 0) {
				setSpeedInc(getSpeedInc() + 1);
				setAttackInc(getAttackInc() + 1);
			}
			if (getLevel() % 3 == 0) {
				setCritChanceInc(getCritChanceInc() + 1);
			}
			break;
		}
	}

	public int getCoinCost() {
		if (this.getLevel() < GLOVES_CONSTANT_GEMS_NEEDED) {
			return ((this.getLevel() + 1) * COINS_COST_PER_LEVEL_TILL_LEVEL5);
		} else {
			return (this.getLevel() * COINS_COST_PER_LEVEL_AFTER_LEVEL5);
		}
	}

	public int getGemCost() {
		if (this.getLevel() < GLOVES_CONSTANT_GEMS_NEEDED) {
			return this.getLevel() + 1;
		} else {
			return GLOVES_CONSTANT_GEMS_NEEDED;
		}
	}

	public String getCost() {
		String s;
		int coins = this.getCoinCost();
		int gems = this.getGemCost();
		s = this.getType() +"coins cost:" + coins + "\tgems cost:" + gems;
		return s;
	}

}
