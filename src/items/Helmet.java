package items;

import characters.Hero;

public class Helmet extends Item {
	private final int HELMET_CONSTANT_GEMS_NEEDED = 4;
	private final int COINS_COST_PER_LEVEL_TILL_LEVEL5 = 200;
	private final int COINS_COST_PER_LEVEL_AFTER_LEVEL5 = 320;

	public Helmet(String name) {
		super(name);
		this.setType("helmet");
	}

	public void upgradeHelmet(Hero hero) {
		Helmet h = hero.getHelmet();
		int coins = hero.getCoins();
		int gems = hero.getGems();
		if (h.getLevel() < HELMET_CONSTANT_GEMS_NEEDED) {
			if (coins >= this.getCoinCost() && gems >= this.getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(h.toString());
					hero.payCoins(h.getCoinCost());
					hero.payGems(h.getGemCost());
					setHelmetLevelStats(h.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(h.toString());
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
					System.out.println(h.toString());
					hero.payCoins(h.getCoinCost());
					hero.payGems(h.getGemCost());
					setHelmetLevelStats(h.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(h.toString());
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

	void setHelmetLevelStats(int level) {
		switch (level) {
		case 1:

			setName("some hair");
			setLevel(level);
			setDefInc(0);
			setMaxHPInc(-1);

			setCritMultiplierInc(0);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 2:
			setName("REALLY VERY HAIRY YOU ARE");
			setLevel(level);
			setDefInc(1);
			setMaxHPInc(1);
			setCritMultiplierInc(0);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 3:
			setName("some sort of cap");
			setLevel(level);
			setDefInc(2);
			setMaxHPInc(3);
			setCritMultiplierInc(0);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 4:
			setName("wrong hat");
			setLevel(level);
			setDefInc(3);
			setMaxHPInc(6);
			setCritMultiplierInc(-0.3);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 5:
			setName("right hat ");
			setLevel(level);
			setDefInc(5);
			setMaxHPInc(9);
			setCritMultiplierInc(0);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 6:
			setName("wizard's Hat");
			setLevel(level);
			setDefInc(8);
			setMaxHPInc(12);
			setCritMultiplierInc(0.2);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(4);
			break;
		case 7:
			setName("better wizard's Hat ");
			setLevel(level);
			setDefInc(9);
			setMaxHPInc(15);
			setCritMultiplierInc(0.22);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 8:
			setName("strong helmet");
			setLevel(level);
			setDefInc(11);
			setMaxHPInc(22);
			setCritMultiplierInc(0);
			setAttackInc(0);
			setSpeedInc(0);
			setCritChanceInc(0);
			break;
		case 9:
			setName("almost ultimate helmet");
			setLevel(level);
			setDefInc(14);
			setMaxHPInc(31);
			setCritMultiplierInc(0.25);
			setAttackInc(0);
			setSpeedInc(2);
			setCritChanceInc(0);
			break;
		case 10:
			setName("ultimate helmet");
			setLevel(level);
			setDefInc(15);
			setMaxHPInc(35);
			setCritMultiplierInc(0.25);
			setAttackInc(0);
			setSpeedInc(2);
			setCritChanceInc(0);
			break;
		default:
			setName("ULTIMATE HELMET level" + (getLevel() - 9));
			setLevel(getLevel() + 1);
			setDefInc(getDefInc() + 2);
			setMaxHPInc(getMaxHPInc() + 3);
			setCritMultiplierInc(getCritMultiplierInc() + 0.05);

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
		if (this.getLevel() < HELMET_CONSTANT_GEMS_NEEDED) {
			return ((this.getLevel() + 1) * COINS_COST_PER_LEVEL_TILL_LEVEL5);
		} else {
			return (this.getLevel() * COINS_COST_PER_LEVEL_AFTER_LEVEL5);
		}
	}

	public int getGemCost() {
		if (this.getLevel() < HELMET_CONSTANT_GEMS_NEEDED) {
			return this.getLevel() + 1;
		} else {
			return HELMET_CONSTANT_GEMS_NEEDED;
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
