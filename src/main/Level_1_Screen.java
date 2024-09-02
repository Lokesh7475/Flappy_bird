package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Level_1_Screen extends JPanel implements Runnable{

	Thread level1Thread;
	final int FPS = 60;
	
	boolean gameOver;
	boolean levelFinished;
	
	KeyHandler kh;
	
	long l;
	int t = 0;
	int score;
	
	Random rn;
	
	PriorityQueueUsingLinkedList pq;
	
	ArrayList<Image_Object> poles;
//	
//	Image_Object sky;
//	Image_Object sun;
//	Image_Object clouds1;
//	Image_Object clouds2;
//	Image_Object buildings1;
//	Image_Object buildings2;
//	
	Image_Object birdAlive;
	Image_Object birdDead;
//
//	Image_Object jet1;
	
	Level_1_Screen()
	{
		pq = new PriorityQueueUsingLinkedList();
		kh = new KeyHandler();
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		this.setBackground(Color.darkGray);
		this.addKeyListener(kh);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		this.setVisible(true);
		innit();
		
	}
	
	void innit() {
		rn = new Random();
		poles = new ArrayList<>();
//		sky = new Image_Object("sky","SKY_FIGMA_02.png",-5,0,1400,700,10);
//		sun = new Image_Object("sun","SUN_FIGMA_02.png",0,0,1400,700,9);
//		clouds1 = new Image_Object("clouds","CLOUDS_FIGMA_02.png",0,0,1400,700,8);
//		clouds2 = new Image_Object("clouds","CLOUDS_FIGMA_02.png",1400,0,1400,700,8);
//		buildings1 = new Image_Object("buildings","BUILDINGS_FIGMA_02.png",0,0,1400,700,7);
//		buildings2 = new Image_Object("buildings","BUILDINGS_FIGMA_02.png",1400,0,1400,700,7);
//		jet1 = new Image_Object("jet","JET_FIGMA_01.png", 100, 500, 69, 45, 8);
//		birdAlive = new Image_Object("bird alive","BIRD_ALIVE_FIGMA_02.png", 100, 100, 50, 50,5);
//		birdDead = new Image_Object("bird dead","BIRD_DEAD_FIGMA_02.png", birdAlive.x, birdAlive.y, 50, 50,5);
		
		pq.add(new Image_Object("sky","SKY_FIGMA_02.png",-5,0,1400,700,10));
		pq.add(new Image_Object("sun","SUN_FIGMA_02.png",0,0,1400,700,9));
		pq.add(new Image_Object("clouds","CLOUDS_FIGMA_02.png",0,0,1400,700,8));
		pq.add(new Image_Object("clouds","CLOUDS_FIGMA_02.png",1400,0,1400,700,8));
		pq.add(new Image_Object("buildings","BUILDINGS_FIGMA_02.png",0,0,1400,700,7));
		pq.add(new Image_Object("buildings","BUILDINGS_FIGMA_02.png",1400,0,1400,700,7));
//		pq.add(new Image_Object("jet","JET_FIGMA_01.png", 100, 500, 69, 45, 8));
		birdAlive = new Image_Object("bird alive","BIRD_ALIVE_FIGMA_02.png", 100, 100, 50, 50,5);
		birdDead = new Image_Object("bird dead","BIRD_DEAD_FIGMA_02.png", birdAlive.x, birdAlive.y, 50, 50,5);
		
		int x = 0;
		int y = 700+200;
		for(int i = 0; i<5; i++)
		{
			int t1 = -500 + rn.nextInt(50);
			poles.add(new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+x, t1, 70, 700, 5));
			poles.add(new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+x, t1+y, 70, 700, 5));
			x += 500+70;
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	 	long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;

	        
        while (level1Thread != null) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / nsPerTick;
        	lastTime = now;
        	while (delta >= 1) {
        		if(t >= 180)
        			stopLevel_1();	
       			if(gameOver || levelFinished)
       				t++;
       			else {
            		update(); // Update game logic
       			}
       			delta--;
       		}
        	repaint(); // Render game graphics
		}
	        
        repaint();
	}

	public void update() {
		l++;
//		System.out.println("update");
		
		if(kh.space)
			birdAlive.y -= 3;
		else
			birdAlive.y += 2;

		
//		if(l%8 == 0)
//			sun.x -= 1;
//		if(l%4 == 0){
//			clouds1.x -= 1;
//			clouds2.x -= 1;
//		}
//		if(l%2 == 0)
//		{
//			buildings1.x -= 1;
//			buildings2.x -= 1;
//		}
//		jet1.x++;
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			
			if(l%8 == 0 && imgO.name.equals("sun"))
				imgO.x--;
			if(l%4 == 0 && imgO.name.equals("clouds"))
				imgO.x--;
			if(l%2 == 0 && imgO.name.equals("buildings"))
				imgO.x--;
//			if(imgO.name.equals("jet"))
//				imgO.x++;
			
		}

		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			pole.x--;
			if(i%2 == 0 && !pole.crossed && birdAlive.x > pole.x+pole.width)
			{
				pole.crossed = true;
				score++;
			}
		}
		
		if(Collide())
		{
			gameOver = true;
		}
		
		if(score >= 5)
		{
			levelFinished = true;
		}
	}
	
	public boolean Collide()
	{
		if(birdAlive.y < 0 || birdAlive.y+birdAlive.height >= 700)
			return true;
		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			if((birdAlive.x+birdAlive.width>pole.x && birdAlive.x<pole.x+pole.width) && 
					(birdAlive.y+birdAlive.width > pole.y &&  birdAlive.y < pole.y + pole.height))
				return true;
		}
		return false;
	}
	
	public void startLevel_1() {
		level1Thread = new Thread(this);
		level1Thread.start();
	}
	
	public void stopLevel_1() {
		level1Thread = null;
		Frame.i = -9;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			g2.drawImage(imgO.img, imgO.x, imgO.y, null);
			
			if(imgO.name.equals("clouds") || imgO.name.equals("buildings"))
				imgO.updateImageLocation();
		}
		
//		g2.drawImage(sky.img, sky.x, sky.y, null);
//		g2.drawImage(sun.img, sun.x, sun.y, null);
//		g2.drawImage(clouds1.img, clouds1.x, clouds1.y, null);
//		g2.drawImage(clouds2.img, clouds2.x, clouds2.y, null);
//		g2.drawImage(jet1.img, jet1.x, jet1.y, null);
//		g2.drawImage(buildings1.img, buildings1.x, buildings1.y, null);
//		g2.drawImage(buildings2.img, buildings2.x, buildings2.y, null);

		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			g2.drawImage(pole.img, pole.x, pole.y, null);
		}
		
		g2.drawImage(birdAlive.img, birdAlive.x, birdAlive.y, null);
		
//		clouds1.updateImageLocation();
//		clouds2.updateImageLocation();
//		buildings1.updateImageLocation();
//		buildings2.updateImageLocation();
		
		
		if(gameOver) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Game Over", 700-150, 350);
		}
		
		if(levelFinished) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Level Finished", 700-150, 350);
			Frame.isLevel_1_Cleared = true;
			try {
				Frame.db.setLevelBooleans();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

//		g2.setColor(Color.cyan);
//		g2.setFont(Arcade_Font.getArcade50());
//		g2.drawString("Score : "+score, 1000, 100);
		
		
		g2.dispose();
	}
}
