/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.tile;

import com.mycompany.super_mario.*;
import java.awt.*;

/**
 *
 * @author Afaq_Sabghatullah
 */
public abstract class Tile {
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
	public int x,y;
    public int width,height;
    
    public int velo_x;
    public int velo_y;
    public int facing;
   
    
    public Id id;
    public Handler handler;
    public boolean activate=false;
    
     public Tile(int x,int y,int width,int height,boolean solid,Id id, Handler handler){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.solid=solid;
        this.id=id;
        this.handler= handler;
    
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

    public boolean isSolid() {
        return solid;
    }

     public Id getId(){
        return id;
    }
    
     public abstract void render(Graphics g);
    public abstract void tick();
    public void die(){
        handler.removeTile(this);
    }
     public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), width, height);
    }
}
