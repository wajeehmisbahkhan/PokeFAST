package poke.fast.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import poke.fast.Handler;
import poke.fast.bars.Health;
import poke.fast.entities.characters.Enemy;
import poke.fast.entities.characters.Player;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.DialogueBox;
import poke.fast.textboxes.OptionBox;

public class BattleState extends State {
	
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	private Player player;
	private Enemy enemy;
	
	//Image
	private BufferedImage character;
	
	//For battle
	private int playerWidth;
	private int enemyWidth;
	private int playerX, playerY;
	private int enemyX, enemyY;
	private Health playerHealth;
	private Health enemyHealth;
	
	//Animations
	private boolean starting, sliding;
	private boolean battling = false, selected = false, damaging = false;
	private boolean canPress;
	private int ticks = 0;
	
	//Battle
	
	public BattleState (Handler handler, Player player, Enemy enemy) { //Take a player and enemy
		super(handler);
		this.player = player;
		this.enemy = enemy;
		//For Battling
		dialogueBox = new DialogueBox(handler);
		optionBox = new OptionBox(handler, enemy);
		playerHealth = new Health();
		enemyHealth = new Health();
		
		//Too many variables
		starting = true;
		sliding = true;
		playerWidth = 120;
		playerX = handler.getWidth() + playerWidth/2;
		playerY = 179;
		enemyWidth = 160;
		enemyX = -(enemyWidth);
		enemyY = 10;
	}


	public void tick() {
			
		if (ticks > 200 && handler.getKeyManager().space) {
			canPress = true;
			ticks = 0;
		}
		else
			canPress = false;
		
		if (starting && !sliding && handler.getKeyManager().space) {
			starting = false;
			battling = true;
		}
		
		if (battling) {
			optionBox.tick();
			dialogueBox.tick();
		}
		ticks++;
	}

	public void render(Graphics g) {
		//When there is nothing
		dialogueBox.render(g);
		//The starting animations
		if (starting)
			init(g);
		else if (battling)
			battle(g);
		else
			end(g);
	}
	
	//Starting animations
	private void init (Graphics g) {
		//Slide and stay
		slideIn(player.getName().toLowerCase(), "left", g);
		slideIn(enemy.getName().toLowerCase(), "right", g);
		//End here
		if (!sliding) {
			dialogueBox.say(g, "Wild " + enemy.getName() + " Appeared!");
		}
	}
	
	//Slider
	private void slideIn(String entity, String direction, Graphics g) {		
		if (entity.equals("student")) //Compare strings
			character = Assets.student;
		else if (entity.equals("senior"))
			character = Assets.senior;
		else if (entity.equals("teacher"))
			character = Assets.teacher;
		else if (entity.equals("assignment"))
			character = Assets.assignment;
		
		if (direction == "left") {
			g.drawImage(character, playerX, playerY, null);
			if (playerX > 50)
				playerX-=5;
			else
				sliding = false;
		} else {
			g.drawImage(character, enemyX, enemyY, enemyWidth, enemyWidth, null);
			if (enemyX < 250)
				enemyX+=5;
			else
				sliding = false;
		}
		
	}
	
	//Battle
	public void battle (Graphics g) {
		//Images
		g.drawImage(Assets.student, playerX, playerY, null);
		g.drawImage(Assets.teacher, enemyX, enemyY, enemyWidth, enemyWidth, null);
		playerHealth.render(g, enemyX, playerY, player.getGPA(), 400);
		enemyHealth.render(g, playerX, enemyY, enemy.getHealth(), enemy.getFullHealth());
		//Begin
		if (!selected) {
			optionBox.render(g);
			dialogueBox.say(g, "What will you do?");
		}
		
		if (canPress) {
			attack(g);
		}
		
		if (selected)
			attack(g);
		
	}
	
	private void attack(Graphics g) {
		selected = true;
		dialogueBox.say(g, "You used " + optionBox.getSelectedOption().getText() + "... " + optionBox.getSelectedOption().getEffect());
		if (!damaging) {
			enemy.setHealth(enemy.getHealth() - optionBox.getSelectedOption().getDamage());
			damaging = true;
		}
		enemyHealth.decrease(enemy.getHealth());
		System.out.println(enemy.getHealth());
	}


	//End
	private void end(Graphics g) {
		
	}
	
	//GETTERS & SETTERS
	public DialogueBox getDialogueBox() {
		return dialogueBox;
	}


	public void setDialogueBox(DialogueBox dialogueBox) {
		this.dialogueBox = dialogueBox;
	}


	public OptionBox getOptionBox() {
		return optionBox;
	}


	public void setOptionBox(OptionBox optionBox) {
		this.optionBox = optionBox;
	}
	
	
}
