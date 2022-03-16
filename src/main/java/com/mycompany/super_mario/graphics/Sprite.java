/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario.graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class Sprite {
    
	public SpriteSheet sheet;
	
	public BufferedImage image;
	public Sprite(SpriteSheet sheet,int x,int y) {
		image = sheet.getSprite(x, y);
	}
	
	public BufferedImage getBufferedImage() {
		return image;
	}
}
