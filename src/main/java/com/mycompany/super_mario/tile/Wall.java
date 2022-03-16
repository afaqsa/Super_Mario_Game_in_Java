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
public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }
 
    
    public void render(Graphics g) {
    	g.drawImage(Game.grass.getBufferedImage(), x,y,width,height,null);
    }

    
    public void tick() {
       
    }
    
}
