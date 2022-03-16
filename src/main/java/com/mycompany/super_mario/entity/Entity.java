/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.entity;

import com.mycompany.super_mario.*;
import com.mycompany.super_mario.state.BossState;
import com.mycompany.super_mario.state.PlayerState;

import java.awt.*;

/**
 *
 * @author Afaq_Sabghatullah
 */
public  abstract class Entity {
    
    public int x,y;
    public int width,height;
    
    public int velo_x;
    public int velo_y;
    public int hp;
    public int phaseTime;
    
     public PlayerState state;
    public boolean goingupCheck=false;
    public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}
	public boolean jumping=false;
    public boolean falling = true;
    public boolean goingIntoPipe=false;
    public boolean attackable =false;
    
    
    public int facing=0;
    public double gravity =0.0;
    
    public Id id;
    public Handler handler;
    public BossState boss;
    
    
    
    public Entity(int x,int y,int width,int height,Id id,Handler handler){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.id=id;
        this.handler=handler;
    }
    
    
    
    public int getVelo_x() {
        return velo_x;
    }

    public void setVelo_x(int velo_x) {
        this.velo_x = velo_x;
    }

    public int getVelo_y() {
        return velo_y;
    }

    public void setVelo_y(int velo_y) {
        this.velo_y = velo_y;
    }
    public boolean solid;
 
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Id getId(){
        return id;
    }
    public abstract void render(Graphics g);
    public abstract void tick();
    
    public void die(){
        handler.removeEntity(this);
        if(getId()==Id.palyer) {
        Game.lives--;
        Game.showLives=true;
        Game.mario_die_s.play();
        if(Game.lives<=0) {
        	Game.gameover=true;
        	Game.mario_die_s.stop();
        	Game.gameover_s.play();
        }
       
        }
        else if(getId()==Id.coin) {
        	Game.coin_s.play();
        }
        else if(getId()==Id.mashroom || getId()==Id.star) {
        	Game.mario_powerup_s.play();
        }
        else if(getId()!=Id.coin || getId()!=Id.mashroom || getId()!=Id.star || getId()!=Id.fireball ) {
        	Game.kick_s.play();
        }
       
        
        
    }
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), width, height);
    }
    public Rectangle getBoundsTop(){
        return new Rectangle(getX()+10, getY(), width-20, 5);
    }
      public Rectangle getBoundsBottum(){
        return new Rectangle(getX()+10, getY()+height-5, width-20, 5);
    }
     public Rectangle getBoundsLeft(){
        return new Rectangle(getX(), getY()+10, 5, height-20);
    }
       public Rectangle getBoundsRight(){
        return new Rectangle(getX()+width-5, getY()+10, 5, height-20);
    }
}
