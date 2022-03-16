/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario;

import com.mycompany.super_mario.entity.Coin;
import com.mycompany.super_mario.entity.Entity;
import com.mycompany.super_mario.entity.Charaters.Goomba;
import com.mycompany.super_mario.entity.Charaters.Player;
import com.mycompany.super_mario.entity.Charaters.TowerBoss;
import com.mycompany.super_mario.entity.Charaters.Water;
import com.mycompany.super_mario.tile.Pipe;
import com.mycompany.super_mario.tile.PowerUpBlock;
import com.mycompany.super_mario.tile.Tile;
import com.mycompany.super_mario.tile.Wall;
import com.mycompany.super_mario.tile.flag;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 *
 * @author Afaq_Sabghatullah
 */
public class Handler {
    
    public LinkedList<Entity> entity =new LinkedList<Entity>();
    public LinkedList<Tile> tile =new LinkedList<Tile>();
     
   
     public void render(Graphics g){
    	 for(int i=0;i<entity.size();i++) {
    		 Entity en =entity.get(i);
    		if(Game.getVisiableArea() !=null && en.getBounds().intersects(Game. getVisiableArea())) en.render(g);
    	 }
           
    	 for(int i=0;i<tile.size();i++) {
    		 Tile ti =tile.get(i);
    		 if(Game.getVisiableArea() !=null && ti.getBounds().intersects(Game. getVisiableArea())) ti.render(g);
    	 }
    	 
    	 g.drawImage(Game.coin.getBufferedImage(),Game.getVisiableArea().x+300 ,Game.getVisiableArea().y+150,75,75,null);
    	 g.setColor(Color.WHITE);
         g.setFont(new Font("Courier",Font.BOLD,30));
         g.drawString(" x " +Game.TotalScore,Game.getVisiableArea().x+ 400, Game.getVisiableArea().y+200);
    	 
   }
     
     public void tick(){
    	 for(int i=0;i<entity.size();i++) {
    		 Entity en =entity.get(i);
    		 en.tick();
    	 }
           
    	 for(int i=0;i<tile.size();i++) {
    		 Tile ti =tile.get(i);
    		 if(Game.getVisiableArea() !=null && ti.getBounds().intersects(Game. getVisiableArea())) ti.tick();
    	 }
     }
     
     public void addEntity(Entity en){
         entity.add(en);
     }
     public void removeEntity(Entity en){
         entity.remove(en);
     }
      public void addTile(Tile ti){
         tile.add(ti);
     }
     public void removeTile(Tile ti){
         tile.remove(ti);
     }
     public void createLevel(BufferedImage level){
        int w = level.getWidth();
        int h= level.getHeight();
        int pixel=0;
         for (int y = 0; y < h; y++) {
             for (int x = 0; x < w; x++) {
                 pixel = level.getRGB(x, y); 
                
                 int red=(pixel >>16 ) & 0xff;
                 int green=(pixel >>8) & 0xff;
                 int blue=(pixel ) & 0xff;
                 
                 if(red==0 && green==0 && blue == 0)  addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                 if(red==0 && green==0 && blue == 255) addEntity(new Player(x*64,y*64,48,48,Id.palyer,this));
                 if(red==255 && green==128 && blue == 0)addEntity(new Goomba(x*64, y*64, 64, 64, Id.goomba, this));
                 if((red>=250 && red<=255) && green==0 && blue == 255)addTile(new PowerUpBlock(x*64,y*64,64,64,true,Id.powerup,this,255-red));
                 if(red==0&& (green>123&& green<129)&&blue==0)addTile(new Pipe(x*64,y*64,64*2,453,true,Id.pipe,this,128-green));
                 if(red==90 && green==90 && blue == 200)addEntity(new Water(x*64, y*64, 64, 64*2, Id.water, this));
                 if(red==255 && green==128 && blue==128) addEntity(new Coin(x*64, y*64, 64, 64, Id.coin, this));
                if(red==128 && green==64 && blue == 64) addEntity(new TowerBoss(x*64, y*64, 64, 64, Id.towerBoss, this,3));
                if(red==164 && green==73 && blue == 164) addTile(new flag(x*64,y*64,64,64*5,true,Id.flag,this));
             
             
             }
         }	
     }
     
     public void clearLevel() {
    	 entity.clear();
    	 tile.clear();
     }
}
