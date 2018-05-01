package poke.fast.maps;

import poke.fast.Handler;

public class MapManager {
	private Handler handler;
	
	public MapManager(Handler handler) {
		this.handler=handler;
	}
	
	
	public void tick() {
		if (	handler.getGame().getGameState().getPlayer().stepOnPortal()	&& handler.getKeyManager().spacePressed	) {
			if(handler.getMap().getCurrentMap()==0) {
				switchMap("cs_1",handler.getGame().getGameState().getPlayer().getDirection());
				handler.getMap().setCurrentMap(1);
			}
			
			else if(handler.getMap().getCurrentMap()==1) {
				switchMap("fast",handler.getGame().getGameState().getPlayer().getDirection());
				handler.getMap().setCurrentMap(0);
			}
				
		}
	}
	
	public void switchMap(String name, int direction) {
		float x=64,y=64;
		if(handler.getMap().getCurrentMap()==0) {
			
			if(direction==3) {
				x*=6;
				y*=9;
			}
			else if(direction==0){
				x*=6;
				y*=5;
			}
			else if(direction==1 || direction==2){
				x*=13;
				y*=7;
			}		
				
		}
		
		else if(handler.getMap().getCurrentMap()==1) {

			if(direction==0) {
				x*=9;
				y*=20;
			}
			else if(direction==3){
				x*=6;
				y*=13;
			}
			else if(direction==1	||	direction==2){
				x*=17;
				y*=17;
			}
		}

		handler.getMap().entityManager.getPlayer().setX(x);
		handler.getMap().entityManager.getPlayer().setY(y);
		handler.getMap().loadMap(name);
	}
	
	
}
