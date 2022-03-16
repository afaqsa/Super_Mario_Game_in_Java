package com.mycompany.super_mario.tile;

import java.awt.Graphics;

import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.Game;

public class flag extends Tile {

	public flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.flag[1].getBufferedImage(), getX(), getY(), width, 64,null);
		
		g.drawImage(Game.flag[2].getBufferedImage(), getX(),getY()+64, width, 64,null);
		g.drawImage(Game.flag[2].getBufferedImage(), getX(),getY()+128, width, 64,null);
		g.drawImage(Game.flag[2].getBufferedImage(), getX(),getY()+192, width, 64,null);
		
		g.drawImage(Game.flag[0].getBufferedImage(), getX(), getY()+height-64, width, 64, null);
		
	}

	
	public void tick() {
	
		
	}

}
