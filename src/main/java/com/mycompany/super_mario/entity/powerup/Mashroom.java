/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.entity.powerup;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.tile.Tile;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class Mashroom extends Entity{

	
	private Random random=new Random();
    public Mashroom(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height,  id, handler);
        int dir = random.nextInt(2);
        switch(dir) {
        case 0:
        	setVelo_x(-2);
        	break;
        case 1:
        	setVelo_x(2);
        	break;
        }
    }

   
    public void render(Graphics g) {
      g.drawImage(Game.mashroom.getBufferedImage(), x, y,width,height,null);
    }

  
    public void tick() {
       x+=velo_x;
       y+=velo_y;
       
       for(Tile t:handler.tile){
           if(t.isSolid()) {
           if(t.getId()==Id.wall || t.getId()==Id.pipe){
               
               if(getBoundsBottum().intersects(t.getBounds())){
                   setVelo_y(0);
                   if(falling) falling=false;
                   else {
                   	if(!falling) {
                   		gravity=0.8;
                   		falling=true;
                   	}
                   }
               }
               if(getBoundsLeft().intersects(t.getBounds())){
                   setVelo_x(2);
                   
               }
               if(getBoundsRight().intersects(t.getBounds())){
                   setVelo_x(-2);
                   
               }
           }
       }
       }
       if(falling){
           
           gravity+=0.1;
           setVelo_y((int)gravity);
          
       }
    }
    
    
}
