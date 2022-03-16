package com.mycompany.super_mario.entity.Charaters;

import java.awt.Graphics;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.tile.Tile;

public class Water extends Entity {
	private int frame=0;
	private int frameDelay=0;

	public Water(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	}

	
	public void render(Graphics g) {
		 if(facing==0)
			   g.drawImage(Game.water[frame+2].getBufferedImage(), x,y,width,height,null);
		 else if(facing==1)
				 g.drawImage(Game.water[frame].getBufferedImage(), x,y,width,height,null);
		
	}

	
	public void tick() {
		 x+=(velo_x/50);
	       y+=(velo_y/50);
	       
	       for(Tile t:handler.tile){
	           if(!t.solid)break;
	           if(t.getId()==Id.wall){
	               
	               if(getBoundsBottum().intersects(t.getBounds())){
	                   setVelo_y(0);
	               }
	               if(getBoundsLeft().intersects(t.getBounds())){
	                   setVelo_x(1);
	                   facing=1;
	                   
	               }
	               if(getBoundsRight().intersects(t.getBounds())){
	                   setVelo_x(-1);
	                   facing=0;
	               }
	           }
	       }
	       if(velo_x==0) {
			frameDelay++;
	           if(frameDelay>=10) {
	           	frame++;
	           		if(frame>2) {
	           			frame=0;
	           		} 
	           		frameDelay=0;
	           }
	       }
		
	}

}
