/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.super_mario;


import com.mycompany.super_mario.entity.Entity;

import com.mycompany.super_mario.graphics.Sprite;
import com.mycompany.super_mario.graphics.SpriteSheet;
import com.mycompany.super_mario.input.KeyInput;




import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Afaq_sabghatullah
 */
@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH=256;
    public static final int HEIGHT= 130;
    public static final int SCALE=4;
    public static final String Title ="Mario";
    public static int TotalScore=0;
    public static int currentScore=0;
    public static int lives=3;
    public static int deathscreenTime=0;
    public static boolean gameover=false;
 
   
    public static boolean showLives=true;
    
    public static Handler handler;
    private Thread thread;
    private boolean running = false;
    private static BufferedImage[] levels;
    private static BufferedImage[] background;
  
    
    private Cammera cam;
    private static int level=0;
    
    
    public static SpriteSheet sheet;
    
    public static Sprite grass;
    public static Sprite powerBlock;
    public static Sprite pipe;
    public static Sprite usedPowerBlock;
    public static Sprite[] flag;
    
    public static Sprite star;
    
    public static Sprite mashroom;
    public static Sprite coin;
    public static Sprite fireball;
    public static Sprite flower;
        
     
    public static Sprite[] goomba;
    public static Sprite[] player;
    public static Sprite[] powerplayer;
    public static Sprite[] water;
    public static Sprite[] Fireplayer;
    
  
    
   
    
    public static Sound jump_s;
    public static Sound coin_s;
    public static Sound gameover_s;
    public static Sound kick_s;
    public static Sound mario_die_s;
    public static Sound pipe_s;
    public static Sound powerup_show_s;
    public static Sound mario_powerup_s;
    public static Sound level_compelet_s;
    public static Sound breakBlock_s;
    public static Sound fire_s;
    public static Sound fire_hit_s;
    
    
    
    public Game(){
        Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }
   
    
    
    private void init(){
        handler = new Handler();
        sheet = new SpriteSheet("/spritesheet.png");
        cam = new Cammera();
       
      
        grass=new Sprite(sheet, 1, 1);
        powerBlock =new Sprite(sheet,2,1);
        usedPowerBlock = new Sprite(sheet,3,1);
        pipe= new Sprite(sheet,1,5);
        coin=new Sprite(sheet,1,7);
        
        mashroom = new Sprite(sheet,1,3);
        flower = new Sprite(sheet,2,7);
        fireball = new Sprite(sheet,3,5);
        
        
        addKeyListener(new KeyInput());
        
        star = new Sprite(sheet,1,12);
        goomba = new Sprite[8];
        player = new Sprite[16];
        Fireplayer = new Sprite[8];
        
        
        water = new Sprite[6];
        flag = new Sprite[3];
        
        levels = new BufferedImage[2];
        background= new BufferedImage[2];
        
        for (int i = 0; i < player.length; i++) {
        player[i] =new Sprite(sheet,i+1,16);
        
        } 
        for (int i = 0; i < Fireplayer.length; i++) {
			Fireplayer[i] = new Sprite(sheet,i+1,15);
		}
        for (int i = 0; i < goomba.length; i++) {
        goomba[i] =new Sprite(sheet,i+1,14);
        }
        
        for (int i = 0; i < flag.length; i++) {
			flag[i] = new Sprite(sheet, i+1, 9);
		}
        
        for (int i = 0; i < water.length; i++) {
          water[i] =new Sprite(sheet,i+1,2);
         }
      
        
        try {
        	
			levels[0] = ImageIO.read(getClass().getResource("/level.png"));
			levels[1] = ImageIO.read(getClass().getResource("/level2.png"));
			background[0] = ImageIO.read(getClass().getResource("/background.png"));
			background[1] = ImageIO.read(getClass().getResource("/background2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         jump_s = new Sound("/smb_jump-small.mp3");
         coin_s = new Sound("/smb_coin.mp3");
         gameover_s = new Sound("/smb_gameover.wav");
         kick_s = new Sound("/smb_kick.mp3");
         mario_die_s =  new Sound("/smb_mariodie.mp3");
         pipe_s = new Sound("/smb_pipe.mp3");
         powerup_show_s = new Sound("/smb_powerup.mp3");
         level_compelet_s = new Sound("/smb_stage_clear.mp3");
         mario_powerup_s= new Sound("/smb_vine.mp3");
         breakBlock_s = new Sound("/smb_breakblock.mp3");
         fire_s = new Sound("/smb_fireball.mp3");
         fire_hit_s = new Sound("/smb_fireworks.mp3");
    
    
    }
    private synchronized void start(){
        if(running) return;
        running=true;
        thread =new Thread(this,"Thread");
        thread.start();
    }
     private synchronized void stop(){
        if(!running)return;
        running=false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
     public void render(){
         BufferStrategy buffer = getBufferStrategy();
         if(buffer==null)
         {
             createBufferStrategy(3);
             return;
         }
         
         Graphics grap = buffer.getDrawGraphics();
        
         
         if(!showLives) {
        	 grap.drawImage(background[level], 0, 0,getWidth(), getHeight(), null);
        	
        	 
         }
         else {
        	 grap.setColor(Color.black);
        	 grap.fillRect(0, 0, getWidth(), getHeight());
         }
         if(showLives) {
        	 if(!gameover) {
        	 grap.setColor(Color.WHITE);
             grap.setFont(new Font("Courier",Font.BOLD,60));
             grap.drawImage(Game.player[0].getBufferedImage(),400 ,200,100,100,null);
             grap.drawString(" x " +lives,500 ,300);
        	 }
        	 else {
        		 grap.setColor(Color.WHITE);
                 grap.setFont(new Font("Courier",Font.BOLD,60));
                 grap.drawString(" Game Over. :( " ,300 ,150);
        	 }
         }
       
         grap.translate(cam.getX(), cam.getY());
         
         if(!showLives )handler.render(grap);
       
         grap.dispose();
         buffer.show();
       }
     public void tick(){
    	 handler.tick();
         for (int i = 0; i < handler.entity.size(); i++) {
        	 Entity e =handler.entity.get(i);
             if(e.getId()==Id.palyer){
                if(!e.goingIntoPipe)cam.tick(e);
             }
         }
        if(showLives&&!gameover)deathscreenTime++;
        
        if(deathscreenTime>=280) {
        	showLives = false;
        	deathscreenTime = 0;
        	handler.clearLevel();
        	handler.createLevel(levels[level+1]);
    
        }
     }
    
     public static int getFrameWidth() {
    	 return WIDTH*SCALE;
     }
     public static int getFrameHeight() {
    	 return HEIGHT*SCALE;
     }
     
     public static void switchLevel() {
    	 Game.level++;
    	
    	 handler.clearLevel();
    	 level_compelet_s.play();
    	 handler.createLevel(levels[level]);
    	
     }
     
     public static Rectangle getVisiableArea() {
    	 
    	 for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.palyer) return new Rectangle(e.getX()-(getFrameWidth()/2)-60,e.getY()-(getFrameWidth())/2,getFrameWidth()*3,getFrameHeight()+100);
    	 
    	 }
		return null;
    	 
     }
     
     
     
     @Override
    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta=0.0;
        double ns= 1000000000.0/56.0;
      //  int ticks = 0;
       // int frames = 0;
        
        
        while(running)
        {
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            
            while(delta>=1){
                tick();
             //   ticks++;
                delta--;
            }
            render();
       //     frames++;
            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
             //   System.out.println(frames+"Frames pr sec "+ticks+" Updates pr sec" );
       //         frames=0;
        //        ticks=0;
            }
        }
        stop();
    }
    
    
    public static void main(String[] args) {
    	
       
    	
    	 JFrame frame = new JFrame(Title);   
    	 Game mario = new Game(); 
    	    
         frame.add(mario);
         frame.pack();
         frame.setResizable(false);
         frame.setVisible(true);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         mario.start();
                
    
    
    }

   
}
