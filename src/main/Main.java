package main;

import java.util.Random;
import java.util.Scanner;

import characters.Hero;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Random rd = new Random();

	public static void main(String[] args) {
		Hero hero = new Hero(setHeroName());
		System.out.println(hero.toString());
		System.out.println(hero.getItemList().toString());
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

}
