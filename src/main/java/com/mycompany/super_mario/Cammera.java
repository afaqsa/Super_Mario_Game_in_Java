package com.mycompany.super_mario;

import com.mycompany.super_mario.entity.Entity;

public class Cammera {

	public int x;
	public int y;
	
	
	
	public void tick(Entity player) {
		setX(-((player.getX()-Game.WIDTH/2)-200));
		setY(-((player.getY()-Game.HEIGHT/2)-350));
		    //      System.out.println(getX()+"      "+getY() +"player "+player.getX()+"  "+player.getY());
	}
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
	
}
