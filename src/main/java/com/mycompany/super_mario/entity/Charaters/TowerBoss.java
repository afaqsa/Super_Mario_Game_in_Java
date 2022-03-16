package com.mycompany.super_mario.entity.Charaters;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.state.BossState;
import com.mycompany.super_mario.tile.Tile;




public class TowerBoss extends Entity {

	public int jumpTime =0;
	
	private Random random;
	
	
	public boolean addjumpTime = false;
	public TowerBoss(int x, int y, int width, int height, Id id, Handler handler,int hp) {
		super(x, y, width, height, id, handler);
		this.hp = hp;
		
		boss = BossState.IDLE;
		random = new Random();
	}

	
	public void render(Graphics g) {
		if(boss==BossState.IDLE || boss == BossState.SPINNING)
			g.setColor(Color.GRAY);
		else if(boss == BossState.RECOVERING)
			g.setColor(Color.RED);
		else 
			g.setColor(Color.ORANGE);
		
		g.fillRect(x, y, width, height);
		
	}


	public void tick() {
		x+=velo_x;
		y+=velo_y;
		
		if(hp<=0) die();
		
		phaseTime++;
		
		if((phaseTime>=180 && boss == BossState.IDLE) || (phaseTime>=600 && boss != BossState.SPINNING)) chooseState();

		if( boss == BossState.RECOVERING && phaseTime>=180) {
			boss =BossState.SPINNING;
			phaseTime=0;
		}
		if(phaseTime>=360 && boss == BossState.SPINNING) {
			phaseTime=0;
			boss = BossState.IDLE;
		}
		
		
		if(boss == BossState.IDLE || boss == BossState.RECOVERING) {
			setVelo_x(0);
			setVelo_y(0);
		}
		
		if(boss == BossState.JUMPING || boss == BossState.RUNNING) attackable=true;
		else attackable = false;
		
		if(boss != BossState.JUMPING) {
			addjumpTime=false;
			jumpTime =0;
		}
		
		if(addjumpTime) {
			jumpTime++;
			
			if(jumpTime>=30) {
				addjumpTime=false;
				jumpTime=0;
				
			}
			
			if(!jumping && !falling) {
				jumping=true;
				gravity=8.0;
			}
		}
			
		
		
		 for(int i=0;i<Game.handler.tile.size();i++) {
	        	Tile t = Game.handler.tile.get(i);
	            if(t.isSolid()){
	                if(getBoundsTop().intersects(t.getBounds())){
	                    setVelo_y(0);
	                    if(jumping) {
	                    	jumping= false;
	                    	gravity =0.8;
	                    	falling=true;
	                    }
	                    
	                    	
	                }
	                if(getBoundsBottum().intersects(t.getBounds())){
	                    setVelo_y(0);
	                    if(falling) {
	                    	falling=false;
	                    	addjumpTime = true;
	                    }
	                }
	                
	                if(getBoundsLeft().intersects(t.getBounds())){
	                    setVelo_x(0);
	                    if(boss == BossState.RUNNING) setVelo_x(4);
	                    x=t.getX()+t.width;
	                }
	                if(getBoundsRight().intersects(t.getBounds())){
	                    setVelo_x(0);
	                    if(boss == BossState.RUNNING) setVelo_x(-4);
	                    x=t.getX()-t.width;
	                }
	                
	            }
	        }
		 
		 for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			
			if(e.getId()==Id.palyer) {
				
				if(boss == BossState.JUMPING) {
					if(jumping || falling) {
						if(getX()>=e.getX()-4&&getX()<=e.getX()+4) setVelo_x(0);
						else	if(e.getX()<getX())setVelo_x(-3);
						else if(e.getX() > getX())setVelo_x(3);
					}else setVelo_x(0);
				}
				else if(boss ==BossState.SPINNING) {
					if(e.getX()<getX())setVelo_x(-3);
					else if(e.getX() > getX())setVelo_x(3);
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
	        
	        
	     
	        
	        
		
	}
	
	public void chooseState() {
		int nextPhase = random.nextInt(2);
		if(nextPhase==0) {
			boss = BossState.RUNNING;
			int dir = random.nextInt(2);
			if(dir==0)setVelo_x(-4);
			else setVelo_x(4);
		}
		else if(nextPhase==1) {
			boss=BossState.JUMPING;
			
			jumping=true;
			gravity =8.0;
			
		}
		phaseTime=0;
	}

}
