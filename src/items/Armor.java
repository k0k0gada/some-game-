package items;

import characters.Hero;

public class Armor extends Item {
	private final int ARMOR_CONSTANT_GEMS_NEEDED = 5;
	private final int COINS_COST_PER_LEVEL_TILL_LEVEL5 = 150;
	private final int COINS_COST_PER_LEVEL_AFTER_LEVEL5 = 250;

	public Armor(String name) {
		super(name);
		this.setType("armor");
	}

	public void upgradeArmor(Hero hero) {
		Armor a = hero.getArmor();
		int coins = hero.getCoins();
		int gems = hero.getGems();
		if (a.getLevel() < ARMOR_CONSTANT_GEMS_NEEDED) {
			if (coins >= getCoinCost() && gems >= getGemCost()) {
				System.out.println("upgrade is possible.Do you want to upgrade ? ");
				if (Hero.yesNoDecision()) {
					System.out.println(a.toString());
					hero.payCoins(a.getCoinCost());
					hero.payGems(a.getGemCost());
					setArmorLevelStats(a.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(a.toString());
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
					System.out.println(a.toString());
					hero.payCoins(a.getCoinCost());
					hero.payGems(a.getGemCost());
					setArmorLevelStats(a.getLevel() + 1);
					System.out.println("new stats: ");
					System.out.println(a.toString());
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

	void setArmorLevelStats(int level) {
		switch (level) {
		case 1:
			setName(getName());
			setLevel(level);
			setDefInc(3);
			setMaxHPInc(8);
			setSpeedInc(5);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 2:
			setName("better " + getName());
			setLevel(level);
			setDefInc(6);
			setMaxHPInc(13);
			setSpeedInc(3);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 3:
			setName(getName());
			setLevel(level);
			setDefInc(8);
			setMaxHPInc(15);
			setSpeedInc(3);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 4:
			setName("Partly armored");
			setLevel(level);
			setDefInc(14);
			setMaxHPInc(20);
			setSpeedInc(0);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 5:
			setName(getName());
			setLevel(level);
			setDefInc(17);
			setMaxHPInc(22);
			setSpeedInc(0);
			setAttackInc(0);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 6:
			setName("fully armored");
			setLevel(level);
			setDefInc(25);
			setMaxHPInc(40);
			setSpeedInc(-10);
			setAttackInc(2);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 7:
			setName(getName());
			setLevel(level);
			setDefInc(30);
			setMaxHPInc(50);
			setSpeedInc(-6);
			setAttackInc(2);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 8:
			setName(getName());
			setLevel(level);
			setDefInc(35);
			setMaxHPInc(50);
			setSpeedInc(-4);
			setAttackInc(4);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 9:
			setName("almost Ultimate armor");
			setLevel(level);
			setDefInc(40);
			setMaxHPInc(70);
			setSpeedInc(-4);
			setAttackInc(6);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		case 10:
			setName("ULTIMATE ARMOR");
			setLevel(level);
			setDefInc(45);
			setMaxHPInc(80);
			setSpeedInc(-5);
			setAttackInc(10);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		default:
			setName("ULTIMATE ARMOR level" + (getLevel() - 9));
			setLevel(getLevel() + 1);
			setDefInc(getDefInc() + 5);
			setMaxHPInc(getMaxHPInc() + 8);
			setSpeedInc(getSpeedInc() + 1);
			setAttackInc(getAttackInc() + 1);
			setCritChanceInc(0);
			setCritMultiplierInc(0);
			break;
		}
	}

	public int getCoinCost() {
		if (this.getLevel() < ARMOR_CONSTANT_GEMS_NEEDED) {
			return ((this.getLevel() + 1) * COINS_COST_PER_LEVEL_TILL_LEVEL5);
		} else {
			return (this.getLevel() * COINS_COST_PER_LEVEL_AFTER_LEVEL5);
		}
	}

	public int getGemCost() {
		if (this.getLevel() < ARMOR_CONSTANT_GEMS_NEEDED) {
			return this.getLevel() + 1;
		} else {
			return ARMOR_CONSTANT_GEMS_NEEDED;
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
