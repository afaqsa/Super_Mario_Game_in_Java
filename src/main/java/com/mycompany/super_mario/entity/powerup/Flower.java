package com.mycompany.super_mario.entity.powerup;

import java.awt.Graphics;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.Entity;

public class Flower extends Entity {

	public Flower(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		
	}

	
	public void render(Graphics g) {
		
		g.drawImage(Game.flower.getBufferedImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	
	public void tick() {
		
	}

}
