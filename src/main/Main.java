package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import characters.Hero;
import items.Item;
import monsters.BossMonsters;
import monsters.EasyMonsters;
import monsters.HardMonsters;
import monsters.MedMonsters;
import monsters.Monster;

public class Main {
	public static ArrayList<Item> allItems = new ArrayList<>();
	public static ArrayList<Monster> monsters = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	public static Random rd = new Random();

	public static void main(String[] args) {
		System.out.println("would you like to create a hero ? y/n");
		char z = sc.nextLine().charAt(0);
		if (z == 'y' || z == 'Y') {
			Hero hero = new Hero(setHeroName());

			do {
				chooseOption(hero);

			} while (true);
		} else {
			System.out.println("you don't want ot play my game :( so SAD  :(  :(   \n BYE BYE !!");
		}
	}

	public static void chooseOption(Hero hero) {
		Scanner sc = new Scanner(System.in);
		System.out.println("what will you do? ");
		System.out.println("1=go fight;0=get hero info");
		System.out.println("enter your choice now:");
		String s = sc.nextLine();
		switch (s.charAt(0)) {
		case '1':
			Monster m = ChooseEnemy(hero);
			if (m != null) {
				hero.fight(m);
			}
			break;
		case '0':
			System.out.println(hero.toString());
			break;
		default:
			System.out.println("wrong choice!CHOOSE AGAIN!");
			chooseOption(hero);
			break;
		}
	}

	private static Monster ChooseEnemy(Hero hero) {
		Scanner scanner = new Scanner(System.in);
		String s;
		Monster m = null;
		int moreLevels = 1 + Main.rd.nextInt(2);
		if (hero.getLevel() == 1) {
			System.out.println("what would you like to look for ?");
			System.out.println(
					"easy/medium/hard/BOSS\t  !THERE IS ALWAYS 3 % chance to get a boss and 1 % to get ultimate BOSS!\n"
							+ " AND 7 % chance to get other kind of monster ");
			System.out.println("e=easy;m=medium;h=hard;b=boss;se=stronger easy;sm=stronger medium;\nsh=stronger hard;"
					+ "sb=stronger BOSS;0=back\n");
			String[] options = { "e", "m", "h", "b", "se", "sm", "sh", "sb" };
			s = scanner.nextLine();

			if (s.startsWith("0")) {
				chooseOption(hero);
			}
			int rd = Main.randomNumTo100();
			if (rd == 69) {// if the number is 69 ,you get an ultimate boss

				BossMonsters ub = new BossMonsters(hero, 12);// ub=ult boss
				if (!monsters.contains(ub)) {
					monsters.add(ub);
				}
				return ub;
			}
			if (rd == 23 || rd == 54 || rd == 78) {
				BossMonsters nb = new BossMonsters(hero);// new boss
				monsters.add(nb);
				return nb;
			}
			if (rd > 92) {// if u get 7 % chance then you get a random monster
				s = options[(Main.rd.nextInt(options.length))];
			}
			for (int i = 0; i < options.length; i++) {// sees what s is,and
														// makes it
				if (s.startsWith(options[i])) {
					s = options[i];
					break;
				}
			}
			switch (s) {
			case "e":
				EasyMonsters em = new EasyMonsters(hero);
				m = em;
				monsters.add(em);
				break;
			case "se":
				EasyMonsters sem = new EasyMonsters(hero, hero.getLevel() + moreLevels);
				m = sem;
				monsters.add(sem);
				break;
			case "m":
				MedMonsters mm = new MedMonsters(hero);
				m = mm;
				monsters.add(mm);
				break;
			case "sm":
				MedMonsters smm = new MedMonsters(hero, hero.getLevel() + moreLevels);
				m = smm;
				monsters.add(smm);
				break;
			case "h":
				HardMonsters hm = new HardMonsters(hero);
				m = hm;
				monsters.add(hm);
				break;
			case "sh":
				HardMonsters shm = new HardMonsters(hero, hero.getLevel() + moreLevels);
				m = shm;
				monsters.add(shm);
				break;
			case "b":
				BossMonsters bm = new BossMonsters(hero);
				m = bm;
				monsters.add(bm);
				break;
			case "sb":
				BossMonsters hbm = new BossMonsters(hero, hero.getLevel() + moreLevels);
				m = hbm;
				monsters.add(hbm);
				break;
			}
			return m;
		} else {
			System.out.println("what would you like to look for ?");
			System.out.println(
					"easy/medium/hard/BOSS\t  !THERE IS ALWAYS 3 % chance to get a boss and 1 % to get ultimate BOSS!\n"
							+ " AND 7 % chance to get other kind of monster ");
			System.out.println("e=easy;m=medium;h=hard;b=boss;se=stronger easy;sm=stronger medium;\nsh=stronger hard;"
					+ "sb=stronger BOSS;\nwe=weaker easy;wm=weaker medium;wh=weaker hard;wb=weaker boss;0=back");

			String[] options = { "e", "m", "h", "b", "se", "sm", "sh", "sb", "we", "wm", "wh", "wb" };

			s = sc.nextLine();
			if (s.startsWith("0")) {
				chooseOption(hero);
			}
			int rd = Main.randomNumTo100();
			if (rd == 69) {// if the number is 69 ,you get an ultimate boss
				BossMonsters ub = new BossMonsters(hero, 12);// ub=ult boss
				if (!monsters.contains(ub)) {
					monsters.add(ub);
				}
				return ub;
			}
			if (rd == 23 || rd == 54 || rd == 78) {
				BossMonsters nb = new BossMonsters(hero);// new boss
				monsters.add(nb);
				return nb;
			}
			if (rd > 92) {// if u get 7 % chance then you get a random monster
				s = options[(Main.rd.nextInt(options.length))];
			}
			for (int i = 0; i < options.length; i++) {// sees what s is,and
														// makes it
				if (s.startsWith(options[i])) {
					s = options[i];
					break;
				}
			}

			switch (s) {
			case "e":
				EasyMonsters em = new EasyMonsters(hero);
				m = em;
				monsters.add(em);
				break;
			case "se":
				EasyMonsters sem = new EasyMonsters(hero, hero.getLevel() + moreLevels);
				m = sem;
				monsters.add(sem);
				break;
			case "m":
				MedMonsters mm = new MedMonsters(hero);
				m = mm;
				monsters.add(mm);
				break;
			case "sm":
				MedMonsters smm = new MedMonsters(hero, hero.getLevel() + moreLevels);
				m = smm;
				monsters.add(smm);
				break;
			case "h":
				HardMonsters hm = new HardMonsters(hero);
				m = hm;
				monsters.add(hm);
				break;
			case "sh":
				HardMonsters shm = new HardMonsters(hero, hero.getLevel() + moreLevels);
				m = shm;
				monsters.add(shm);
				break;
			case "b":
				BossMonsters bm = new BossMonsters(hero);
				m = bm;
				monsters.add(bm);
				break;
			case "sb":
				BossMonsters hbm = new BossMonsters(hero, hero.getLevel() + moreLevels);
				m = hbm;
				monsters.add(hbm);
				break;
			case "we":
				EasyMonsters wem = new EasyMonsters(hero);
				m = wem;
				monsters.add(wem);
				break;
			case "wm":
				MedMonsters wmm = new MedMonsters(hero);
				m = wmm;
				monsters.add(wmm);
				break;
			case "wh":
				HardMonsters whm = new HardMonsters(hero);
				m = whm;
				monsters.add(whm);
				break;
			case "wb":
				BossMonsters wbm = new BossMonsters(hero);
				m = wbm;
				monsters.add(wbm);
				break;
			}
			return m;
		}

	}

	@SuppressWarnings("static-access")
	static String setHeroName() {
		System.out.println("prepare to enter hero's name.");
		System.out.println("minimum 3 characters long! Only letters; digits; and ',' ; '.' ; '-' ; '_' allowed");
		String name;
		boolean nameOK;
		do {
			nameOK = true;
			System.out.println("enter hero's name: ");
			name = sc.next();
			if (name.length() < 3) {
				System.out.println("less then 3 characters !try again!");
				nameOK = false;
				continue;
			}
			for (int i = 0; i < name.length(); i++) {
				Character c = name.charAt(i);
				if (c.isLetterOrDigit(c)) {
					continue;
				} else {
					if (c == ',' || c == '.' || c == '-' || c == '_') {
						continue;
					} else {
						nameOK = false;
						System.out.println("the character " + c + " is not allowed!try again!");
						break;
					}
				}
			}
		} while (!nameOK);
		System.out.println("a Hero with the name " + name + " was created!");
		return name;
	}

	static public int randomNumTo100() {
		return rd.nextInt(100);
	}

	static public double randomNum() {
		return rd.nextDouble();
	}
}
