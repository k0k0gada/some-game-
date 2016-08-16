package items;

public class Potion extends Item {
	int amount;
	int hpInc;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getHpInc() {
		return hpInc;
	}

	public Potion(int hpInc) {
		super();
		this.setType("potion");
		this.hpInc = hpInc;

	}

	public Potion(String name, int hpInc) {
		super(name);
		this.setType("potion");
		this.hpInc = hpInc;
	}

	@Override
	public String toString() {
		String s;
		s = "Potion that heals " + this.hpInc + " HP.There are " + this.amount + " uses left!\n";
		return s;
	}
}
