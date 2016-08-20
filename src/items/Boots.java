package items;

import characters.Hero;

public class Boots extends Item {

	private final int BOOTS_CONSTANT_GEMS_NEEDED = 4;
	private final int COINS_COST_PER_LEVEL_TILL_LEVEL5 = 120;
	private final int COINS_COST_PER_LEVEL_AFTER_LEVEL5 = 180;

	public Boots(String name) {
		super(name);
		this.setType("boots");
	}

	public void upgradeBoots(Hero hero) {
		Boots b = hero.getBoots();
		int coins = hero.getCoins();
		int gems = hero.getGems();
		if (b.getLevel() < BOOTS_CONSTANT_GEMS_NEEDED) {
			if (coins >= getCoinCost() && gems >= getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(b.toString());
					hero.payCoins(b.getCoinCost());
					hero.payGems(b.getGemCost());
					setBootsLevelStats(b.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(b.toString());
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
			if (coins >= getCoinCost() && gems >= getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(b.toString());
					hero.payCoins(b.getCoinCost());
					hero.payGems(b.getGemCost());
					setBootsLevelStats(b.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(b.toString());
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

		}
	}

	void setBootsLevelStats(int level) {
		switch (level) {
		case 1:
			setName("Sandals");
			setLevel(level);
			setDefInc(3);
			setMaxHPInc(5);
			setSpeedInc(-4);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 2:
			setName("better " + getName());
			setLevel(level);
			setDefInc(5);
			setMaxHPInc(9);
			setSpeedInc(-1);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 3:
			setName("trainers");
			setLevel(level);
			setDefInc(6);
			setMaxHPInc(13);
			setSpeedInc(1);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 4:
			setName("better " + getName());
			setLevel(level);
			setDefInc(8);
			setMaxHPInc(15);
			setSpeedInc(3);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 5:
			setName("boots");
			setLevel(level);
			setDefInc(10);
			setMaxHPInc(15);
			setSpeedInc(0);
			setAttackInc(3);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 6:
			setName("leather boots");
			setLevel(level);
			setDefInc(12);
			setMaxHPInc(17);
			setSpeedInc(0);
			setAttackInc(5);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 7:
			setName(getName());
			setLevel(level);
			setDefInc(13);
			setMaxHPInc(20);
			setSpeedInc(0);
			setAttackInc(5);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 8:
			setName(getName());
			setLevel(level);
			setDefInc(15);
			setMaxHPInc(24);
			setSpeedInc(0);
			setAttackInc(7);
			setCritChanceInc(2);
			setCritMultiplierInc(0);
			break;
		case 9:
			setName("almost Ultimate boots");
			setLevel(level);
			setDefInc(17);
			setMaxHPInc(27);
			setSpeedInc(0);
			setAttackInc(7);
			setCritChanceInc(4);
			setCritMultiplierInc(0);
			break;
		case 10:
			setName("ULTIMATE BOOTS");
			setLevel(level);
			setDefInc(20);
			setMaxHPInc(30);
			setSpeedInc(5);
			setAttackInc(10);
			setCritChanceInc(5);
			setCritMultiplierInc(0);
			break;
		default:
			setName("ULTIMATE BOOTS level" + (getLevel() - 9));
			setLevel(getLevel() + 1);
			setDefInc(getDefInc() + 1);
			setMaxHPInc(getMaxHPInc() + 3);
			if (getLevel() % 2 == 0) {
				setSpeedInc(getSpeedInc() + 1);
				setAttackInc(getAttackInc() + 1);
			}
			if (getLevel() % 3 == 0) {
				setCritChanceInc(getCritChanceInc() + 1);
			}
			setCritMultiplierInc(0);
			break;
		}
	}

	public int getCoinCost() {
		if (this.getLevel() < BOOTS_CONSTANT_GEMS_NEEDED) {
			return ((this.getLevel() + 1) * COINS_COST_PER_LEVEL_TILL_LEVEL5);
		} else {
			return (this.getLevel() * COINS_COST_PER_LEVEL_AFTER_LEVEL5);
		}
	}

	public int getGemCost() {
		if (this.getLevel() < BOOTS_CONSTANT_GEMS_NEEDED) {
			return this.getLevel() + 1;
		} else {
			return BOOTS_CONSTANT_GEMS_NEEDED;
		}
	}

	public String getCost() {
		String s;
		int coins = this.getCoinCost();
		int gems = this.getGemCost();
		s = this.getType() + " coins cost:" + coins + "\tgems cost:" + gems;
		return s;
	}
}
