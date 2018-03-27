package poke.fast.textboxes;

public class Option {
	
	private String text; //Will contain the text of the option
	private int damage; //The damage it will do
	//private String[] effects; //The effects
	
	
	public Option (String text, int damage) {
		//String[] effects
		this.text = text;
		this.damage = damage;
		//this.effects = effects; 
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
	
	
}
