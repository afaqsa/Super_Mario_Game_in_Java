package com.mycompany.super_mario.tile;

import java.awt.Graphics;
import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;


public class Pipe extends Tile {

	


	public Pipe(int x, int y, int width, int height, boolean solid, Id id, Handler handler,int facing) {
		super(x, y, width, height, solid, id, handler);
		this.facing = facing;
		
		
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.pipe.getBufferedImage(), x, y, width, height,null);
		
	}

	
	public void tick() {
		
		
	}
	

}
