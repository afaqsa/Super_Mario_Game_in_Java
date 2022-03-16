/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.input;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.entity.FireBall;
import com.mycompany.super_mario.state.PlayerState;
import com.mycompany.super_mario.tile.Tile;



import java.awt.event.*;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class KeyInput implements KeyListener {

	private boolean  fire;
    public void keyTyped(KeyEvent e) {
      
       
    }

    
    public void keyPressed(KeyEvent e) {
    	
        int key = e.getKeyCode();
       
        for (int i = 0; i <Game.handler.entity.size(); i++) {
        	Entity en = Game.handler.entity.get(i);
            if(en.getId()==Id.palyer) {
            	if(en.goingIntoPipe) return;
            	switch(key){
            		case  KeyEvent.VK_UP:
            		case KeyEvent.VK_W:
            			
            			for (int k = 0; k < Game.handler.tile.size(); k++) {
            				  Tile t = Game.handler.tile.get(k);
            				  if(t.isSolid()) {
            					if(en.getBoundsBottum().intersects(t.getBounds())) {
            						if(!en.jumping) {
            							en.jumping=true;
            							if(en.state == PlayerState.small) {
            								en.gravity=8.0;
            							}
            							else {
            								en.gravity=10.0;
            							}
            							
            							Game.jump_s.play();
            						}
            					}
            				  else if(t.getId()==Id.pipe) {
            					if(en.getBoundsTop().intersects(t.getBounds())) {
            						if(!en.goingIntoPipe) {
            							en.goingIntoPipe=true;
            							en.goingupCheck =true;
            						}
								}
            				}
            				  
						}
            		}
            		
     
            			
            		break;
            		case KeyEvent.VK_DOWN:
            		case KeyEvent.VK_S:
            			for (int j = 0; j < Game.handler.tile.size(); j++) {
            				Tile t = Game.handler.tile.get(j);
            				if(t.getId()==Id.pipe) {
            					if(en.getBoundsBottum().intersects(t.getBounds())) {
            						if(!en.goingIntoPipe) {
            							en.goingIntoPipe=true;
            							en.goingupCheck =false;
            						}
								}
            				}
						}
            			break;
            		
            		case KeyEvent.VK_LEFT:
            		case KeyEvent.VK_A:
            			en.setVelo_x(-5);
            			en.facing=0;
            			break;
            		case KeyEvent.VK_RIGHT:
            		case KeyEvent.VK_D:
            			en.setVelo_x(5);
            			en.facing=1;
            			break;
            			
            		case KeyEvent.VK_SPACE:
            			if(en.state==PlayerState.FIRE&&!fire) {
            				Game.fire_s.play();
            			switch (en.facing) {
						case 0: 
							Game.handler.addEntity(new FireBall(en.getX()-24,en.getY()+12,24,24,Id.fireball,Game.handler,en.facing));
							fire=true;
							break;
						case 1: 
							Game.handler.addEntity(new FireBall(en.getX()+en.getWidth(),en.getY()+12,24,24,Id.fireball,Game.handler,en.facing));
							fire=true;
							break;
            			}
            			}
            	
            	break;
   
            	}
                
            	}
        	}
    	
    }
   
    public void keyReleased(KeyEvent e) {
       int key = e.getKeyCode();
        for(Entity en:Game.handler.entity){
        	if(en.getId()==Id.palyer) {
        switch(key){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            	
                en.setVelo_y(0);
             break;
            
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            	
                en.setVelo_x(0);
            break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            	
               en.setVelo_x(0);
            break;
            case KeyEvent.VK_SPACE:
            	fire=false;
            	break;
        }
                
        }  
    }
    
}
}