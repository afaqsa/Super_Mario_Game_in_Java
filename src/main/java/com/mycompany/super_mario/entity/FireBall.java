package com.mycompany.super_mario.entity;

import java.awt.Graphics;
import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.state.BossState;
import com.mycompany.super_mario.tile.Tile;

public class FireBall extends Entity{

	public FireBall(int x, int y, int width, int height, Id id, Handler handler,int facing) {
		super(x, y, width, height, id, handler);
		switch (facing) {
		case 0: 
			setVelo_x(-8);
		break;
		case 1:
		setVelo_x(8);
		break;
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.fireball.getBufferedImage(),getX(), getY(), getWidth(), getHeight(), null);
		
	}

	@Override
	public void tick() {
		x +=velo_x;
		y += velo_y;
		
		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if(t.isSolid()) {
				if(getBoundsLeft().intersects(t.getBounds()) || getBoundsRight().intersects(t.getBounds())) die();
			
				if(getBoundsBottum().intersects(t.getBounds())) {
					jumping=true;
					falling=false;
					gravity=4.0;
				}else if(!falling&&!jumping) {
					falling=true;
					gravity=1.0;
				}
			
			}
		}
		
		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.goomba || e.getId()==Id.towerBoss) {
				if(getBounds().intersects(e.getBounds())) {
					Game.fire_hit_s.play();
				die();
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
			}
		}
		
		if(jumping){
	           gravity-=0.32;
	           setVelo_y((int)-gravity);
	           if(gravity<=0.5)
	           {
	               falling=true;
	               jumping=false;
	           }
	       }
	        
	       
	        if(falling){
	            
	            gravity+=0.32;
	            setVelo_y((int)gravity);
	        }
		
	}

}
