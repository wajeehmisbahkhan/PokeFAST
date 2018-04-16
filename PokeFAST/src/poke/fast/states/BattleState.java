package poke.fast.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import poke.fast.Handler;
import poke.fast.bars.Health;
import poke.fast.entities.characters.Enemy;
import poke.fast.entities.characters.Player;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.DialogueBox;
import poke.fast.textboxes.Option;
import poke.fast.textboxes.OptionBox;

public class BattleState extends State {
	
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	private Player player;
	private Enemy enemy;
	
	//Image
	private BufferedImage character; //For Sliding
	private BufferedImage enemyImage; //For showing
	private String eName;
	//For battle
	private int playerWidth, playerHeight;
	private int enemyWidth, enemyHeight;
	private int playerX, playerY;
	private int enemyX, enemyY;
	private Health playerHealth;
	private Health enemyHealth;
	private Option enemyAttack;
	
	//Animations
	private boolean starting, sliding;
	private boolean battling = false, playerTurn = false, enemyTurn = false, selecting = false, damaging = false, damaged = false;
	private boolean won;
	int decreasedHealth = 0;
	static int blackWidth = 0;
	
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
		eName = enemy.getName().toLowerCase();
		if (eName.equals("senior"))
			enemyImage = Assets.senior;
		else if (eName.equals("teacher"))
			enemyImage = Assets.teacher;
		else if (eName.equals("assignment"))
			enemyImage = Assets.assignment;
		//Too many variables
		starting = true;
		sliding = true;
		playerWidth = 120;
		playerHeight = 179;
		playerX = handler.getWidth() + playerWidth/2;
		playerY = 179;
		enemyWidth = 160;
		enemyHeight = enemyWidth;
		enemyX = -(enemyWidth);
		enemyY = 10;
	}


	public void tick() {
		
		spacePressed = handler.getKeyManager().spacePressed;
		
		if (starting && !sliding && spacePressed) {
			starting = false;
			battling = true;
			playerTurn = true;
			selecting = true;
			spacePressed = false; //hatana hai
		}
		
		if (battling) {
			dialogueBox.tick();
			if (playerTurn && !damaging)
				optionBox.tick();
		}
	}

	public void render(Graphics g) {
		//When there is nothing
		g.drawImage(Assets.battleGround, 0, 0, 400, 400 - dialogueBox.getHeight(), null); //background
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
		slide(player.getName().toLowerCase(), "left", g);
		slide(enemy.getName().toLowerCase(), "right", g);
		//End here
		if (!sliding) {
			dialogueBox.say(g, "Wild " + enemy.getName() + " Appeared!");
		}
	}
	
	//Battle
	public void battle (Graphics g) {
		//Images
		g.drawImage(Assets.student, playerX, playerY, null);
		g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
		playerHealth.render(g, enemyX, playerY, player.getGPA(), 400);
		enemyHealth.render(g, playerX, enemyY, enemy.getHealth(), enemy.getFullHealth());
		//Begin
		if (playerTurn)
			attack(g);
		else if (enemyTurn)
			getAttacked(g);
		
	}
	
	private void attack(Graphics g) {
		//These keep happening every frame
		if (selecting) {
			optionBox.render(g);
			dialogueBox.say(g, "What will you do?");
		} else if (damaging) {
			enemyHealth.decrease(decreasedHealth);
			dialogueBox.say(g, "You used " + optionBox.getSelectedOption().getText() + "... ");
			if (enemyHealth.getHealthWidth() <= decreasedHealth*100/enemy.getFullHealth() || enemyHealth.getHealthWidth() <= 0) {
				damaging = false;
				damaged = true;
			}
		} else if (damaged) {
			dialogueBox.say(g, "You used " + optionBox.getSelectedOption().getText() + "... " + optionBox.getSelectedOption().getEffect());
		}
		//Only happen once when space is pressed
		if (spacePressed) {
			if (selecting) {
				selecting = false;
				damaging = true;
				decreasedHealth = enemy.getHealth() - optionBox.getSelectedOption().getDamage();
				enemy.setHealth(decreasedHealth);
			} else if (damaged) {
				damaged = false;
				playerTurn = false;
				enemyTurn = true;
				selecting = true;
				if (enemyHealth.getHealthWidth() <= 0) {
					battling = false;
					won = true;
					sliding = true;
				}
			}
		}
	}
	
	private void getAttacked(Graphics g) {
		//Keep happening
		if (selecting) {
			//YOHO - You only happen once
			enemyAttack = enemy.getRandomAttack();
			decreasedHealth = player.getGPA() - enemyAttack.getDamage();
			player.setGPA(decreasedHealth);
			selecting = false;
			damaging = true;
		} else if (damaging) {
			dialogueBox.say(g, enemy.getName() + " used " + enemyAttack.getText());
			playerHealth.decrease(decreasedHealth);
			if (playerHealth.getHealthWidth() <= decreasedHealth*100/400 || playerHealth.getHealthWidth() <= 0) {
				dialogueBox.say(g, enemy.getName() + " used " + enemyAttack.getText() + "... " + enemyAttack.getEffect());
			}
		}
		//Space pressed
		if (spacePressed) {
			if (damaging) {
				damaging = false;
				enemyTurn = false;
				playerTurn = true;
				selecting = true;
				if (playerHealth.getHealthWidth() <= 0) {
					battling = false;
					won = false;
					sliding = true;
				}
			}
		}
		
	}
	
	//End
	private void end(Graphics g) {
		if (won) {
			g.drawImage(Assets.student, playerX, playerY, null);
			slide(enemy.getName().toLowerCase(), "down", g);
			dialogueBox.render(g); // So that the enemy doesn't go over the dialogueBox
			if (!sliding) {
				dialogueBox.say(g, "You won! No knowledge gained.");
				if (spacePressed)
					State.setState(handler.getGame().getGameState());
			}
		} else {
			g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
			slide(player.getName().toLowerCase(), "down", g);
			dialogueBox.render(g);
			if (!sliding) {
				dialogueBox.say(g, "You lost! Better luck next year.");
				if (spacePressed)
					State.setState(new MenuState(handler)); //Go to menu; Change later
			}
		}
			
	}
	
	//Slider
		private void slide(String entity, String direction, Graphics g) {
			if (entity.equals("student")) //Compare strings
				character = Assets.student;
			else if (entity.equals("senior"))
				character = Assets.senior;
			else if (entity.equals("teacher"))
				character = Assets.teacher;
			else if (entity.equals("assignment"))
				character = Assets.assignment;
			
			if (direction == "left") { //Only happens for player
				g.drawImage(character, playerX, playerY, null);
				if (playerX > 50)
					playerX-=5;
				else
					sliding = false;
			} else if (direction == "right") { //Only for enemy
				g.drawImage(character, enemyX, enemyY, enemyWidth, enemyHeight, null);
				if (enemyX < 250)
					enemyX+=5;
				else
					sliding = false;
			} else if (direction == "down") { //For both
				if (entity.equals("student")) {
					g.drawImage(character, playerX, playerY, null);
					if (playerY <= handler.getHeight() + playerHeight)
						playerY += 10;
					else
						sliding = false;
				} else {
					g.drawImage(character, enemyX, enemyY, enemyWidth, enemyHeight, null);
					if (enemyY <= handler.getHeight() + enemyHeight)
						enemyY += 15;
					else
						sliding = false;
				}
			}
			
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
