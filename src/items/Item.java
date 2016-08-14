package items;

public class Item {
	static int counter;
	private String name;
	private String type;
	private int attackInc;
	private int defInc;
	private int speedInc;
	private double critChanceInc;
	private double critMultiplierInc;
	private int HPInc;

	Item(String name) {
		this.name = name;
		this.attackInc = 0;
		this.speedInc = 0;
		this.defInc = 0;
		this.critChanceInc = 0;
		this.critMultiplierInc = 0;
		this.HPInc = 0;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
