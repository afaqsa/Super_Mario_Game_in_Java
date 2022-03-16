/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.entity.Charaters;

import com.mycompany.super_mario.*;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.state.BossState;
import com.mycompany.super_mario.state.PlayerState;
import com.mycompany.super_mario.tile.Tile;
import java.awt.Graphics;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class Player extends Entity{
	
	private int frame=0;
	private int frameDelay=0;
	private int invincibilityTime=0;
	
	private int pixelsTravelled=0;
	private boolean invincible=false;
	
	private int restoreTime=0;
	
	private boolean restoring;

    public Player(int x, int y, int width, int height, Id id, Handler handler) {
        super(x, y, width, height, id, handler);
        state = PlayerState.small;
    }

    
    
   @Override
    public void render(Graphics g) {
	 if(state==PlayerState.FIRE) {
		 if(facing==0)
			   g.drawImage(Game.Fireplayer[frame+4].getBufferedImage(), x,y,width,height,null);
		 else if(facing==1)
				   g.drawImage(Game.Fireplayer[frame].getBufferedImage(), x,y,width,height,null);
	 }
	 else {  
	   
	   
	  if(frame<=7) {
		  if(facing==0)
		   g.drawImage(Game.player[frame+4].getBufferedImage(), x,y,width,height,null);
		   else if(facing==1)
			   g.drawImage(Game.player[frame].getBufferedImage(), x,y,width,height,null);
	  }else if(frame>=8) {
		  if(facing==0)
			   g.drawImage(Game.player[frame+4].getBufferedImage(), x,y,width,height,null);
		 else if(facing==1)
				   g.drawImage(Game.player[frame].getBufferedImage(), x,y,width,height,null);
	  }
	 }
	  
   }

    @Override
    public void tick() {
        x+=velo_x;
        y+=velo_y;
        
        if(velo_x!=0) {
		}
        
        if(invincible) {
        	invincibilityTime++;
        	
        	if(invincibilityTime>=600) {
        		invincible=false;
        		invincibilityTime=0;
        		frame=0;
        	}
        }
     
       if(restoring) {
    	   restoreTime++;
    	   if(restoreTime>=90) {
    		   restoreTime=0;
    		   restoring=false;
    	   }
       }
       
        for(int i=0;i<Game.handler.tile.size();i++) {
        	Tile t = Game.handler.tile.get(i);
            if(t.isSolid()&&!goingIntoPipe){
                if(getBoundsTop().intersects(t.getBounds())){
                    setVelo_y(0);
                    if(jumping) {
                    	jumping= false;
                    	gravity =0.8;
                    	falling=true;
                    }
                    if(t.getId()==Id.powerup) {
                    	if(getBoundsTop().intersects(t.getBounds())) t.activate =true;
                    }
                    	
                }
                if(getBounds().intersects(t.getBounds())&&t.getId()==Id.flag) {
                    
            		Game.switchLevel();
            	}
                
                if(getBoundsBottum().intersects(t.getBounds())){
                    setVelo_y(0);
                    if(falling) falling=false;
                }
                else if(!falling&&!jumping) {
                    		gravity=0.8;
                    		falling=true;
                    	}
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelo_x(0);
                    x=t.getX()+t.width;
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelo_x(0);
                    x=t.getX()-width;
                }
                
            }
            
           
            if(state==PlayerState.big &&t.getId()==Id.wall) {
            	if(getBoundsTop().intersects(t.getBounds())) {
            		Game.breakBlock_s.play();
            		t.die();
            	}
            	
            }
            
            
        }
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity e = handler.entity.get(i);
            
            if(e.getId()==Id.mashroom){
                if(getBounds().intersects(e.getBounds())){
                	if(state!=PlayerState.FIRE) {
                	int tpx= getX();
                	int tpy= getY();
                    width+=(width/3);
                    height+=(height/3);
                    setX(tpx-width);
                    setY(tpy-height);
                   if(state==PlayerState.small) state = PlayerState.big;
                	}
                    e.die();
                }
            } 
            else if(e.getId()==Id.goomba || e.getId()==Id.towerBoss ) {
            	
            	if(invincible&&getBounds().intersects(e.getBounds())) {
            			e.die();
            	}else {
            	
            	if(getBoundsBottum().intersects(e.getBoundsTop())) {
            		
            			if(e.getId()!=Id.towerBoss)e.die();
            			else if(e.attackable) {
            				e.hp--;
            				e.falling=true;
            				e.gravity=3.0;
            				e.boss = BossState.RECOVERING;
            				e.attackable=false;
            				e.phaseTime=0;
            				
            				jumping=true;
            				falling=false;
            				gravity = 3.5;
            			}
            	}
            	else if(getBounds().intersects(e.getBounds())){
            		takeDemage();
            	}
           
          }
            }
            else if(e.getId()==Id.coin) {
            	if(getBounds().intersects(e.getBounds())&&e.getId()==Id.coin) {
            		Game.currentScore++;
            		Game.TotalScore++;
            		e.die();
            	}
            }
            else if (e.getId()==Id.star) {
            	if(getBounds().intersects(e.getBounds())&&e.getId()==Id.star) {
            	
            		if(state!=PlayerState.FIRE) {
            			invincible = true;
            			frame=8;
            	}
            	else {
            			frame=0;
            			invincible=false;
            		}
            	e.die();
            	}
            }
            else if(e.getId()==Id.flower) {
            	if(getBounds().intersects(e.getBounds())&&e.getId()==Id.flower) {
            	state = PlayerState.FIRE;
            	if(state!=PlayerState.big) {
            	int tpx= getX();
            	int tpy= getY();
                width+=(width/3);
                height+=(height/3);
                setX(tpx-width);
                setY(tpy-height);
            	}
            	frame=0;
    			invincible=false;
            	e.die();
            	}
            }
            
            else if(e.getId()==Id.water) {
            	if(getBoundsBottum().intersects(e.getBounds())) {
            		die();
            	}
            	
            }
            
       }
       if(jumping&&!goingIntoPipe){
           gravity-=0.17;
           setVelo_y((int)-gravity);
           if(gravity<=0.5)
           {
               falling=true;
               jumping=false;
           }
       }
        
       
        if(falling&&!goingIntoPipe){
            
            gravity+=0.17;
            setVelo_y((int)gravity);
        }
       if(velo_x!=0) {
        frameDelay++;
        if(frameDelay>=10) {
        	frame++;
        	if(invincible) {
        		if(frame>11) {
        			frame=8;
        		}
        	}else
        	{
        		if(frame>3) {
        			frame=0;
        		} 
        	}
        		frameDelay=0;
        }
      }
       
      if(goingIntoPipe) {
    	  Game.pipe_s.play();
    	  for (int i = 0; i < Game.handler.tile.size(); i++) {
    		  Tile t = Game.handler.tile.get(i);
    		  if(t.getId()==Id.pipe) {
    			  if(getBounds().intersects(t.getBounds())) {
    				 
    			  switch(t.facing) {
    			  case 0:
    				  if(goingupCheck) {
    				  setVelo_y(-5);
    				  setVelo_x(0);
    				  pixelsTravelled +=velo_y;
    				  System.out.println("Hight of pipe is : "+(t.height) +" Value of x is "+ getVelo_x() +" pixels are" + pixelsTravelled + " height is "+ this.height);

    				  }
    				  else {
    					  goingIntoPipe=false;
    				  }
    				  break;
    			  case 2:
    				  if(!goingupCheck) {
    				 pixelsTravelled +=velo_y;
    				  setVelo_y(5);
    				  setVelo_x(0);
    				 
    				  System.out.println("Hight of pipe is : "+(t.height) +" Value of x is "+ getVelo_x() +" pixels are" + pixelsTravelled);
    				  }
    				  else{
    					  goingIntoPipe=false;
    				  }
    				  break;
    			  }
    			 
    			  }
    			  if(pixelsTravelled - this.height+20>t.height || pixelsTravelled <-(t.height+this.height) ) {
    				  goingIntoPipe=false;
    				 pixelsTravelled = 0;
    			  }
    		  }
    	  }
       }
      
    	  
   }
    
    public void takeDemage() {
    	if(restoring)return;
    	if(state==PlayerState.small) {
    		die();
    		return;
    	}
    	else if(state==PlayerState.big) {
    		Game.pipe_s.play();
    		int tpx= getX();
    		int tpy = getY();
    		width-=(width/3);
			height-=(height/3);
			setX(tpx-width);
			setY(tpy-height);
			restoring=true;
			restoreTime=0;
			state = PlayerState.small;
			return;
    	}else if(state==PlayerState.FIRE) {
    		Game.pipe_s.play();
    			state = PlayerState.big;
    		restoring=true;
    		restoreTime=0;
    		return;
    	}
    }
}
