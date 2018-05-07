package poke.fast.entities.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import poke.fast.Handler;
import poke.fast.gfx.Animation;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Player extends Character{

	//animation & direction
	private int direction, GPA;
	private Animation upimation, downimation, leftimation, rightimation;
	private boolean frozen;
	private Rectangle tpBounds;
	
	public Player( Handler handler, float x, float y) {
		super(handler, x, y, 64, 64);
		
		GPA = 400;
		name = "Student";
		frozen=false;
		tpBounds = new Rectangle();
		bounds.x = 16;
		bounds.y = 32;
		tpBounds.width=24;
		tpBounds.height=24;
		tpBounds.x=(int) x;
		tpBounds.y=(int) y;
		bounds.width = 32;
		bounds.height = Character.DEFAULT_HEIGHT - bounds.y;
		
		//Animations: up, down, left, right
		direction = 0;
		upimation = new Animation ( 1000/20, Assets.player_up);
		downimation = new Animation ( 1000/20, Assets.player_down);
		leftimation = new Animation ( 1000/20, Assets.player_left);
		rightimation = new Animation ( 1000/20, Assets.player_right);
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	@Override
	public void tick() {
		
		downimation.tick();
		upimation.tick();
		rightimation.tick();
		leftimation.tick();
		getInput();
		if(!isFrozen())
		move();
		createTpBounds();
		handler.getGameCamera().centerOnEntity(this);
		
	}

	private void createTpBounds() {
		if(direction==0) {	//down
			tpBounds.x=(int) this.xMove+20;
			tpBounds.y=(int) this.yMove+48;
		}
		else if(direction==1) {	//left
			tpBounds.x=(int) this.xMove;
			tpBounds.y=(int) this.yMove+32;
		}
		else if(direction==2) {	//right
			tpBounds.x=(int) this.xMove+42;
			tpBounds.y=(int) this.yMove+32;
		}
		else if(direction==3) {	//up
			tpBounds.x=(int) this.xMove+20;
			tpBounds.y=(int) this.yMove+12;
		}
		
	}
	
	public boolean stepOnPortal() {
		
		int ty = (int) (y + yMove + tpBounds.y) / Tile.TILEHEIGHT;
		if (handler.getMap().getTile( (int) (x + tpBounds.x) / Tile.TILEHEIGHT, ty ).isPortal()
				||
				handler.getMap().getTile( (int) (x + tpBounds.x + tpBounds.width) / Tile.TILEHEIGHT, ty) .isPortal())
		return true;
		return false;
	}

	private void getInput() {
		xMove = 0; yMove = 0;
		
		if(handler.getKeyManager().up) {
			if (!handler.getKeyManager().left && !handler.getKeyManager().right)
				yMove = -speed;
			else {
				yMove = -speed*(0.7071f);
				if ( handler.getKeyManager().left)
					xMove = -speed*(0.7071f);
				if ( handler.getKeyManager().right)
					xMove = speed*(0.7071f);
			}
		}	
		
		if(handler.getKeyManager().down) {
			if (!handler.getKeyManager().left && !handler.getKeyManager().right)
				yMove = speed;
			else {
				yMove = speed*(0.7071f);
				if ( handler.getKeyManager().left)
					xMove = -speed*(0.7071f);
				if ( handler.getKeyManager().right)
					xMove = speed*(0.7071f);
			}
		}	
		
		
		if(handler.getKeyManager().left) {
			if (!handler.getKeyManager().up && !handler.getKeyManager().down)
				xMove = -speed;
			else {
				xMove = -speed*(0.7071f);
				if ( handler.getKeyManager().up)
					yMove = -speed*(0.7071f);
				if ( handler.getKeyManager().down)
					yMove = speed*(0.7071f);
			}
		}	
		
		if(handler.getKeyManager().right) {
			if (!handler.getKeyManager().up && !handler.getKeyManager().down)
				xMove = speed;
			else {
				xMove = speed*(0.7071f);
				if ( handler.getKeyManager().up)
					yMove = -speed*(0.7071f);
				if ( handler.getKeyManager().down)
					yMove = speed*(0.7071f);
			}
		}	
		
	}
	
	
	@Override
	public void render(Graphics g) {
		//g.fillRect(tpBounds.x, tpBounds.y, tpBounds.width, tpBounds.height);
		//g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.drawImage(getCurrentAnimationFrame(), (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if( xMove < 0 ) {
			direction = 1;
			return leftimation.getCurrentFrame();
		}
		else if( xMove > 0 ) {
			direction = 2;
			return rightimation.getCurrentFrame();
		}
		else if( yMove < 0 ) {
			direction = 3;
			return upimation.getCurrentFrame();
		}
		else if( yMove > 0 ) {
			direction = 0;
			return downimation.getCurrentFrame();
		}
		else
			return Assets.player_still[direction];	//nicely done ;)
		
	}

	public int getDirection() {
		return direction;
	}
	
	public int getGPA () {
		return GPA;
	}
	
	public void setGPA (int GPA) {
		this.GPA = GPA;
	}
	
}
