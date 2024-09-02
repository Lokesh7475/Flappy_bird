package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Freemode_Screen_1  extends JPanel implements Runnable{

	Thread freemode1Thread;
	final int FPS = 60;
	
	boolean gameOver;
	
	KeyHandler kh;
	PriorityQueueUsingLinkedList pq1;
	PriorityQueueUsingLinkedList pq2;
	PriorityQueueUsingLinkedList pq3;
	
	long l;
	int t = 0;
	int score = 0;
	
	Random rn;
	
	static int map = 0;

//	Image_Object jet1;
//	Image_Object sky;
//	Image_Object sun;
//	Image_Object clouds1;
//	Image_Object clouds2;
//	Image_Object buildings1;
//	Image_Object buildings2;
//	
//	Image_Object spaceBackground;
//	Image_Object spaceDebrisSmall1;
//	Image_Object spaceDebrisSmall2;
//	Image_Object spaceDebrisBig1;
//	Image_Object spaceDebrisBig2;	
//
//	Image_Object desert;
//	Image_Object desert2;
//	Image_Object desert3;
//	Image_Object desertPyramid;
	
	Image_Object birdAlive;
	Image_Object birdDead;
	
	Image_Object pole1;
	Image_Object pole10;
	Image_Object pole2;
	Image_Object pole20;
	Image_Object pole3;
	Image_Object pole30;
	Image_Object pole4;
	Image_Object pole40;
	Image_Object pole5;
	Image_Object pole50;

	
	Freemode_Screen_1(){
		pq1 = new PriorityQueueUsingLinkedList();
		pq2 = new PriorityQueueUsingLinkedList();
		pq3 = new PriorityQueueUsingLinkedList();
		kh = new KeyHandler();
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		this.setBackground(Color.cyan);
		
		this.addKeyListener(kh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		
		innit();
	}
	
	public void innit() {
		rn = new Random();
		
		pq1.add(new Image_Object("sky","SKY_FIGMA_02.png",-5,0,1400,700,10));
		pq1.add(new Image_Object("sun","SUN_FIGMA_02.png",0,0,1400,700,9));
		pq1.add(new Image_Object("clouds","CLOUDS_FIGMA_02.png",0,0,1400,700,8));
		pq1.add(new Image_Object("clouds","CLOUDS_FIGMA_02.png",1400,0,1400,700,8));
		pq1.add(new Image_Object("buildings","BUILDINGS_FIGMA_02.png",0,0,1400,700,7));
		pq1.add(new Image_Object("buildings","BUILDINGS_FIGMA_02.png",1400,0,1400,700,7));
		pq1.add(new Image_Object("jet","JET_FIGMA_01.png", 100, 500, 69, 45, 8));
		
		pq2.add(new Image_Object("space","SPACE_FIGMA_01.png",0,0,1400,700,10));
		pq2.add(new Image_Object("space deb big","SPACE_DEBRIS_BIG_FIGMA_03.png",0,0,2800,700,9));
		pq2.add(new Image_Object("space deb big","SPACE_DEBRIS_BIG_FIGMA_03.png",2800,0,2800,700,9));
		pq2.add(new Image_Object("space deb small","SPACE_DEBRIS_SMALL_FIGMA_02.png",0,0,1400,700,8));
		pq2.add(new Image_Object("space deb small","SPACE_DEBRIS_SMALL_FIGMA_02.png",1400,0,1400,700,8));
		
		pq3.add(new Image_Object("space","SPACE_FIGMA_01.png",0,0,1400,700,10));
		pq3.add(new Image_Object("desert","DESERT_BLANK_FIGMA_02.png", 0, 0, 1400, 700, 9));
		pq3.add(new Image_Object("desert pyramid","DESERT_PYRAMIDS_FIGMA_03.png", 1400, 0, 1400, 700, 9));
		pq3.add(new Image_Object("desert u","DESERT_BLANK_FIGMA_02.png", 2800, 0, 1400, 700, 9));
		pq3.add(new Image_Object("desert u","DESERT_BLANK_FIGMA_02.png", 4200, 0, 1400, 700, 9));

		birdAlive = new Image_Object("bird alive","BIRD_ALIVE_FIGMA_02.png", 100, 100, 50, 50,5);
		birdDead = new Image_Object("bird dead","BIRD_DEAD_FIGMA_02.png", birdAlive.x, birdAlive.y, 50, 50,5);

		if(map == 0) {
			pole1 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500, -500 + rn.nextInt(50), 70, 700, 5);
			pole10 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole2 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+300+70, -500 + rn.nextInt(50), 70, 700, 5);
			pole20 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+300+70, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole3 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+600+140, -500 + rn.nextInt(50), 70, 700, 5);
			pole30 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+600+140, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole4 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+900+210, -500 + rn.nextInt(50), 70, 700, 5);
			pole40 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+900+210, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole5 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+1200+280, -500 + rn.nextInt(50), 70, 700, 5);
			pole50 = new Image_Object("brick pole","BRICK_POLE_FIGMA_01.png", 500+1200+280, -500 + rn.nextInt(50)+900, 70, 700, 5);
		}
		else if(map == 1) {
			pole1 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500, -500 + rn.nextInt(50), 70, 700, 5);
			pole10 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole2 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+300+70, -500 + rn.nextInt(50), 70, 700, 5);
			pole20 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+300+70, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole3 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+600+140, -500 + rn.nextInt(50), 70, 700, 5);
			pole30 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+600+140, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole4 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+900+210, -500 + rn.nextInt(50), 70, 700, 5);
			pole40 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+900+210, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole5 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+1200+280, -500 + rn.nextInt(50), 70, 700, 5);
			pole50 = new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+1200+280, -500 + rn.nextInt(50)+900, 70, 700, 5);
		}
		else {
			pole1 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500, -500 + rn.nextInt(50), 70, 700, 5);
			pole10 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole2 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+300+70, -500 + rn.nextInt(50), 70, 700, 5);
			pole20 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+300+70, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole3 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+600+140, -500 + rn.nextInt(50), 70, 700, 5);
			pole30 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+600+140, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole4 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+900+210, -500 + rn.nextInt(50), 70, 700, 5);
			pole40 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+900+210, -500 + rn.nextInt(50)+900, 70, 700, 5);
			pole5 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+1200+280, -500 + rn.nextInt(50), 70, 700, 5);
			pole50 = new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+1200+280, -500 + rn.nextInt(50)+900, 70, 700, 5);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / FPS;
		double delta = 0;
		
		
		while (freemode1Thread != null) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (delta >= 1) {
				if(t >= 180)
					stopFreemode1_Screen();	
       			if(gameOver)
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
	
	public void startFreemode1_Screen() {
		freemode1Thread = new Thread(this);
		freemode1Thread.start();
	}
	
	public void stopFreemode1_Screen() {
		freemode1Thread = null;
		Frame.i = -13;
	}
	
	public void update() {
		l++;
		if(kh.space)
			birdAlive.y -= 3;
		else
			birdAlive.y += 2;
		
		if(map == 0) {
			Iterator_pq itr = pq1.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				
				if(l%8 == 0 && imgO.name.equals("sun"))
					imgO.x--;
				if(l%4 == 0 && imgO.name.equals("clouds"))
					imgO.x--;
				if(l%2 == 0 && imgO.name.equals("buildings"))
					imgO.x--;
				if(imgO.name.equals("jet"))
					imgO.x++;
				
			}

//			if(l%8 == 0)
//				sun.x -= 1;
//			if(l%4 == 0){
//				clouds1.x -= 1;
//				clouds2.x -= 1;
//			}
//			if(l%2 == 0)
//			{
//				buildings1.x -= 1;
//				buildings2.x -= 1;
//			}
//			
//			jet1.x++;
			
		} else if(map == 1) {
			Iterator_pq itr = pq2.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				
				if(l%6 == 0 && imgO.name.equals("space deb big"))
					imgO.x--;
				if(l%2 == 0 && imgO.name.equals("space deb small"))
					imgO.x--;
			}
			
//			if(l%6 == 0)
//			{
//				spaceDebrisBig1.x -= 1;
//				spaceDebrisBig2.x -= 1;
//			}
//			if(l%2 == 0)
//			{
//				spaceDebrisSmall1.x -= 1;
//				spaceDebrisSmall2.x -= 1;
//			}
		} else {
			Iterator_pq itr = pq3.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				
				if(l%2 == 0 && (imgO.name.equals("desert u") || imgO.name.equals("desert")|| imgO.name.equals("desert pyramid")))
					imgO.x--;
			}
			
//			if(l%2 == 0) {
//				desert.x--;
//				desert2.x--;
//				desert3.x--;
//				desertPyramid.x--;
//			}
			
		}
		
		if(!pole1.crossed && birdAlive.x > pole1.x+pole1.width)
		{
			pole1.crossed = true;
			pole10.crossed = true;
			score++;
		}
		if(!pole2.crossed && birdAlive.x > pole2.x+pole2.width)
		{
			pole2.crossed = true;
			pole20.crossed = true;
			score++;
		}
		if(!pole3.crossed && birdAlive.x > pole3.x+pole3.width)
		{
			pole3.crossed = true;
			pole30.crossed = true;
			score++;
		}
		if(!pole4.crossed && birdAlive.x > pole4.x+pole4.width)
		{   
			pole4.crossed = true;
			pole40.crossed = true;
			score++;
		}
		if(!pole5.crossed && birdAlive.x > pole5.x+pole5.width)
		{
			pole5.crossed = true;
			pole50.crossed = true;
			score++;
		}
		
		pole1.x--;
		pole10.x--;

		pole2.x--;
		pole20.x--;
		
		pole3.x--;
		pole30.x--;
		
		pole4.x--;
		pole40.x--;
		
		pole5.x--;
		pole50.x--;

		if(Collide())
		{
			try {
				Frame.db.addTry(map, score);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameOver = true;
		}
	}
	
	public boolean Collide() {
		if(birdAlive.x+birdAlive.width > pole1.x && birdAlive.x < pole1.x + pole1.width 
				&& birdAlive.y+birdAlive.height > pole1.y && birdAlive.y < pole1.y + pole1.height)
			return true;
		if(birdAlive.x+birdAlive.width > pole10.x && birdAlive.x < pole10.x + pole10.width 
				&& birdAlive.y+birdAlive.height > pole10.y && birdAlive.y < pole10.y + pole10.height)
			return true;
		
		
		if(birdAlive.x+birdAlive.width > pole2.x && birdAlive.x < pole2.x + pole2.width 
				&& birdAlive.y+birdAlive.height > pole2.y && birdAlive.y < pole2.y + pole2.height)
			return true;
		if(birdAlive.x+birdAlive.width > pole20.x && birdAlive.x < pole20.x + pole20.width 
				&& birdAlive.y+birdAlive.height > pole20.y && birdAlive.y < pole20.y + pole20.height)
			return true;
		
		
		if(birdAlive.x+birdAlive.width > pole3.x && birdAlive.x < pole3.x + pole3.width 
				&& birdAlive.y+birdAlive.height > pole3.y && birdAlive.y < pole3.y + pole3.height)
			return true;
		if(birdAlive.x+birdAlive.width > pole30.x && birdAlive.x < pole30.x + pole30.width 
				&& birdAlive.y+birdAlive.height > pole30.y && birdAlive.y < pole30.y + pole30.height)
			return true;
		
		
		if(birdAlive.x+birdAlive.width > pole4.x && birdAlive.x < pole4.x + pole4.width 
				&& birdAlive.y+birdAlive.height > pole4.y && birdAlive.y < pole4.y + pole4.height)
			return true;
		if(birdAlive.x+birdAlive.width > pole40.x && birdAlive.x < pole40.x + pole40.width 
				&& birdAlive.y+birdAlive.height > pole40.y && birdAlive.y < pole40.y + pole40.height)
			return true;
		
		
		if(birdAlive.x+birdAlive.width > pole5.x && birdAlive.x < pole5.x + pole5.width 
				&& birdAlive.y+birdAlive.height > pole5.y && birdAlive.y < pole5.y + pole5.height)
			return true;
		if(birdAlive.x+birdAlive.width > pole50.x && birdAlive.x < pole50.x + pole50.width 
				&& birdAlive.y+birdAlive.height > pole50.y && birdAlive.y < pole50.y + pole50.height)
			return true;
		
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(map == 0) {
			Iterator_pq itr = pq1.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				g2.drawImage(imgO.img, imgO.x, imgO.y, null);
				
				if(imgO.name.equals("clouds") || imgO.name.equals("buildings"))
					imgO.updateImageLocation();
			}
			
//			g2.drawImage(sky.img, sky.x, sky.y, null);
//			g2.drawImage(sun.img, sun.x, sun.y, null);
//			g2.drawImage(clouds1.img, clouds1.x, clouds1.y, null);
//			g2.drawImage(clouds2.img, clouds2.x, clouds2.y, null);
//			g2.drawImage(jet1.img, jet1.x, jet1.y, null);
//			g2.drawImage(buildings1.img, buildings1.x, buildings1.y, null);
//			g2.drawImage(buildings2.img, buildings2.x, buildings2.y, null);
//			
//			clouds1.updateImageLocation();
//			clouds2.updateImageLocation();
//			buildings1.updateImageLocation();
//			buildings2.updateImageLocation();
		}
		else if(map == 1) {
			Iterator_pq itr = pq2.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				g2.drawImage(imgO.img, imgO.x, imgO.y, null);
				
				if(imgO.name.equals("space deb big") || imgO.name.equals("space deb small"))
					imgO.updateImageLocation();
			}
			
//			g2.drawImage(spaceBackground.img, spaceBackground.x, spaceBackground.y, null);
//			g2.drawImage(spaceDebrisBig1.img, spaceDebrisBig1.x, spaceDebrisBig1.y, null);
//			g2.drawImage(spaceDebrisBig2.img, spaceDebrisBig2.x, spaceDebrisBig2.y, null);
//			g2.drawImage(spaceDebrisSmall1.img, spaceDebrisSmall1.x, spaceDebrisSmall1.y, null);
//			g2.drawImage(spaceDebrisSmall2.img, spaceDebrisSmall2.x, spaceDebrisSmall2.y, null);
//			
//			spaceDebrisBig1.updateImageLocation();
//			spaceDebrisBig2.updateImageLocation();
//			spaceDebrisSmall1.updateImageLocation();
//			spaceDebrisSmall2.updateImageLocation();
		}
		else {
			Iterator_pq itr = pq3.getIterator();
			while(itr.hasNext())
			{
				Image_Object imgO = itr.next();
				g2.drawImage(imgO.img, imgO.x, imgO.y, null);
				
				if(imgO.name.equals("desert u"))
					imgO.updateImageLocation();
			}
//			g2.drawImage(spaceBackground.img, spaceBackground.x, spaceBackground.y, null);
//			g2.drawImage(desert.img, desert.x, desert.y, null);
//			g2.drawImage(desert2.img, desert2.x, desert2.y, null);
//			g2.drawImage(desert3.img, desert3.x, desert3.y, null);
//			g2.drawImage(desertPyramid.img, desertPyramid.x, desertPyramid.y, null);
//			
//			desert2.updateImageLocation();
//			desert3.updateImageLocation();
		}
		
		g2.drawImage(pole1.img, pole1.x, pole1.y, null);
		g2.drawImage(pole10.img, pole10.x, pole10.y, null);
		
		g2.drawImage(pole2.img, pole2.x, pole2.y, null);
		g2.drawImage(pole20.img, pole20.x, pole20.y, null);
		
		g2.drawImage(pole3.img, pole3.x, pole3.y, null);
		g2.drawImage(pole30.img, pole30.x, pole30.y, null);
		
		g2.drawImage(pole4.img, pole4.x, pole4.y, null);
		g2.drawImage(pole40.img, pole40.x, pole40.y, null);
		
		g2.drawImage(pole5.img, pole5.x, pole5.y, null);
		g2.drawImage(pole50.img, pole50.x, pole50.y, null);

		g2.setColor(Color.cyan);
		g2.setFont(Arcade_Font.getArcade50());
		g2.drawString("Score : "+score, 1000, 100);
		
		g2.drawImage(birdAlive.img, birdAlive.x, birdAlive.y, null);
		
		pole1.updatePoleLocation();
		pole10.updatePoleLocation();
		pole2.updatePoleLocation();
		pole20.updatePoleLocation();
		pole3.updatePoleLocation();
		pole30.updatePoleLocation();
		pole4.updatePoleLocation();
		pole40.updatePoleLocation();
		pole5.updatePoleLocation();
		pole50.updatePoleLocation();
		
		if(gameOver) {
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Game Over", 700-150, 350);
			
		}
		
		g2.dispose();
	}
}
