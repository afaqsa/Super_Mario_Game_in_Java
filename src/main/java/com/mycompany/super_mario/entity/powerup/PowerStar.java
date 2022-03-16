package com.mycompany.super_mario.entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.tile.Tile;


public class PowerStar extends Entity {
	private Random random;

	public PowerStar(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		random = new Random();
		 int dir = random.nextInt(2);
	        switch(dir) {
	        case 0:
	        	setVelo_x(-2);
	        	break;
	        case 1:
	        	setVelo_x(2);
	        	break;
	        }
	        falling = true;
	        gravity = 0.17;
		
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.star.getBufferedImage(),  x, y,width,height,null);
		
	}

	
	public void tick() {
		x+=velo_x;
		y+=velo_y;
		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			
			
			if(t.isSolid()) {
				if(getBoundsBottum().intersects(t.getBounds())) {
					
					jumping = true;
					falling =false;
					gravity=8.0;
				}
				
				if(getBoundsLeft().intersects(t.getBounds())) setVelo_x(2);
				
				
				if(getBoundsRight().intersects(t.getBounds())) setVelo_x(-2);
				
				
			}
		
		}
		
	    if(jumping){
	           gravity-=0.17;
	           setVelo_y((int)-gravity);
	           if(gravity<=0.5)
	           {
	               falling=true;
	               jumping=false;
	           }
	       }
	        
	       
	        if(falling){
	            
	            gravity+=0.17;
	            setVelo_y((int)gravity);
	            
	        }
		
	}

}
