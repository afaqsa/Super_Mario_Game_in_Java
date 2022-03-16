package com.mycompany.super_mario.tile;

import java.awt.Graphics;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import com.mycompany.super_mario.entity.powerup.Flower;
import com.mycompany.super_mario.entity.powerup.Mashroom;
import com.mycompany.super_mario.entity.powerup.PowerStar;


public class PowerUpBlock extends Tile {
	
	private int popUp;
	private boolean poopedUp= false;
	private int spriteY = getY();

	public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler,int popUp) {
		super(x, y, width, height, solid, id, handler);
		this.popUp=popUp;
	}

	
	public void render(Graphics g) {
		if(!poopedUp) {
			if(popUp==1)
			g.drawImage(Game.mashroom.getBufferedImage(),x,spriteY,width,height,null);
			else if(popUp == 2)
				g.drawImage(Game.star.getBufferedImage(),x,spriteY,width,height,null);
		}
		if(!activate) g.drawImage(Game.powerBlock.getBufferedImage(), x, y, width, height, null);
		else g.drawImage(Game.usedPowerBlock.getBufferedImage(), x, y, width, height, null);
		
		
	}

	
	public void tick() {
		
		if(activate&&!poopedUp) {
			spriteY--;
			if(spriteY<=y-height) {
				Game.powerup_show_s.play();
				if(popUp==1)
				handler.addEntity(new Mashroom(x,spriteY,width,height,Id.mashroom,handler));
				else if(popUp==2)
					handler.addEntity(new PowerStar(x,spriteY,width,height,Id.star,handler));
				else if(popUp==3)
					handler.addEntity(new Flower(x,spriteY,width,height,Id.flower,handler));
				poopedUp=true;
			}
		}
		
	}

}
