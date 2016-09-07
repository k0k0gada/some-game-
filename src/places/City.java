package places;

import characters.Hero;
import main.Main;

public class City {

	public static final int HEAL_PRICE = 300;
	private static final int GEM_SELL_PRICE = 300;
	private static final int GEM_BUY_PRICE = 500;
	public static int FREE_HEALS = 3;

	public static void getCityMenu(Hero hero) {
		System.out.println("Places in the city:");
		System.out.println("M=merchant;\tH=FREE Heal;\te/else=go to main menu:");
		String[] options = { "m", "e", "h" };
		String s = Main.sc.nextLine();
		s = s.toLowerCase();
		{
			boolean foundString = false;
			for (int i = 0; i < options.length; i++) {
				if (s.startsWith(options[i])) {
					s = options[i];
					foundString = true;
					break;
				}
			}
			if (!foundString) {
				System.out.println("wrong choice!choose again! ");
				City.getCityMenu(hero);
			}
		}
		switch (s) {
		case "m":
			showMerchantItems(hero);
			break;
		case "h":
			if (hero.getHP() == hero.getMAXHP()) {
				System.out.println("hero is at full hp!!");
				break;
			}
			if (FREE_HEALS > 0) {
				FREE_HEALS--;
				System.out.println("healing hero to max HP");
				hero.setHP(hero.getMAXHP());
				System.err.println("there are " + FREE_HEALS + " free heals left");
			} else {
				System.out.println("no more free heals available");
			}
			break;
		case "e":
		default:
			Main.chooseOption(hero);
			break;
		}

	}

	}

	private static void showMerchantItems(Hero hero) {
		System.out.println("the merchant offers:");
		System.out.println(
				"1:H-heal 50 HP-(300 coins);\t2:sg=sell 1 gem for 300 coins;\t3:bg=buy 1 gem for 500 coins;\t0:else=exit;");
		String[] options = { "h", "1", "2", "sg", "3", "bg", "e", "0" };
		System.out.println("hero's hp: " + hero.getHP() + "\t" + hero.showCoinsAndGemsAmount());
		String s = Main.sc.nextLine();
		s = s.toLowerCase();
		{
			boolean foundString = false;
			for (int i = 0; i < options.length; i++) {
				if (s.startsWith(options[i])) {
					s = options[i];
					foundString = true;
					break;
				}
			}
			if (!foundString) {
				System.out.println("wrong choice .Choose again!");
				City.showMerchantItems(hero);
			}
		}
		switch (s) {
		case "1":
		case "h":
			System.out.println("are you sure u want heal for 300 coins?");
			if (Hero.yesNoDecision()) {
				if (hero.getCoins() >= HEAL_PRICE) {
					System.out.println("The hero paid " + HEAL_PRICE + " coins to heal himself ");
					hero.payCoins(HEAL_PRICE);
					System.out.println("hero's HP :" + hero.getHP());
					hero.setHP(hero.getHP() + 50);

				} else {
					System.out.println("not enough coins for heal");
				}
			} else {
				System.out.println("you choose not to heal");
			}
			tradeAgain(hero);
			break;
		case "2":
		case "sg":
			System.out.println("are you sure u want to sell a gem?");
			if (Hero.yesNoDecision()) {
				if (hero.getGems() > 0) {
					System.out.println(hero.showCoinsAndGemsAmount());
					hero.setGems(hero.getGems() - 1);
					hero.setCoins(hero.getCoins() + GEM_SELL_PRICE);
					System.out.println("the hero sold 1 gem for 300");
					System.out.println(hero.showCoinsAndGemsAmount());
				} else {
					System.out.println("You don't have any gems");
				}
			} else {
				System.out.println("you choose not to sell.");
			}
			tradeAgain(hero);
			break;
		case "3":
		case "bg":
			System.out.println("are you sure u want to buy a gem?");
			if (Hero.yesNoDecision()) {
				if (hero.getCoins() > GEM_BUY_PRICE) {
					System.out.println(hero.showCoinsAndGemsAmount());
					hero.setGems(hero.getGems() + 1);
					hero.setCoins(hero.getCoins() - GEM_BUY_PRICE);
					System.out.println("the hero bought 1 gem for 500");
					System.out.println(hero.showCoinsAndGemsAmount());
				} else {
					System.out.println("You don't have enough coins ");
				}
			} else {
				System.out.println("you choose not to buy.");
			}
			tradeAgain(hero);
			break;
		case "0":
		default:
			Main.chooseOption(hero);
			break;
		}

	}

	static void tradeAgain(Hero hero) {
		System.out.println("trade again ?");
		if (Hero.yesNoDecision()) {
			showMerchantItems(hero);
		} else {
			Main.chooseOption(hero);
		}
	}
}
