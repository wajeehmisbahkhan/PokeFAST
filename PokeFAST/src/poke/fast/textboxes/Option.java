package poke.fast.textboxes;

public class Option {
	
	private String text; //Will contain the text of the option
	private int damage; //The damage it will do
	private String effect; //The effects
	
	
	public Option (String text, int damage, String effect) {
		//String[] effects
		this.text = text;
		this.damage = damage;
		this.effect = effect;
	}
	
	public String getText () {
		return text;
	}
	
	public void setText (String text) {
		this.text = text;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
	
}
