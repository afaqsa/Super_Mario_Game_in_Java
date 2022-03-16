/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.entity;

import com.mycompany.super_mario.Game;
import com.mycompany.super_mario.Handler;
import com.mycompany.super_mario.Id;
import java.awt.Graphics;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class Coin extends Entity{


	public Coin(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	}


	public void render(Graphics g) {
        g.drawImage(Game.coin.getBufferedImage(), x, y, width,height,null);
    }

   
    public void tick() {
       
    }
    
}
