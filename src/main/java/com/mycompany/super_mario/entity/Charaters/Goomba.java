package com.mycompany.super_mario.entity.Charaters;

import java.awt.Graphics;
import java.util.Random;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.tile.Tile;

public class Goomba extends Entity {
	private int frame=0;
	private int frameDelay=0;
	
	private Random random=new Random();
	
	public Goomba(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height,  id, handler);
		
		  int dir = random.nextInt(2);
	        switch(dir) {
	        case 0:
	        	setVelo_x(-2);
	        	facing=0;
	        	break;
	        case 1:
	        	setVelo_x(2);
	        	facing=1;
	        	break;
	        }
	}

	@Override
	public void render(Graphics g) {

		    if(facing==0)
		   g.drawImage(Game.goomba[frame+4].getBufferedImage(), getX(),getY(),getWidth(),getHeight(),null);
		   else if(facing==1)
			   g.drawImage(Game.goomba[frame].getBufferedImage(), getX(),getY(),getWidth(),getHeight(),null);
		  
		
	}

	@Override
	public void tick() {
	    x+=velo_x;
	    y+=velo_y;
	       
	    
	       for(Tile t:handler.tile){
	           if(t.isSolid()) {
	           if(t.getId()==Id.wall || t.getId()==Id.pipe){
	               
	               if(getBoundsBottum().intersects(t.getBounds())){
	                   setVelo_y(0);
	                   if(falling) falling=false;
	                   else	if(!falling || t.getId()==Id.wall) {
	                   		gravity=0.8;
	                   		falling=true;
	                   	}
	                   
	               }
	               if(getBoundsLeft().intersects(t.getBounds())){
	                   setVelo_x(2);
	                 facing=1;  
	               }
	               if(getBoundsRight().intersects(t.getBounds())){
	                   setVelo_x(-2);
	                   facing=0;
	               }
	           }
	       }
	       }
	       if(falling){
	           
	           gravity+=0.1;
	           setVelo_y((int)gravity);
	       }
	       if(velo_x!=0) {
	           frameDelay++;
	           if(frameDelay>=10) {
	           	frame++;
	           		if(frame>3) {
	           			frame=0;
	           		} 
	           		frameDelay=0;
	           }
	       }
		
	}
	

	}

