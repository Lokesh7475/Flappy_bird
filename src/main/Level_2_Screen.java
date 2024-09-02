package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Level_2_Screen extends JPanel implements Runnable{
	Thread level2Thread;
	int t = 0;
	boolean gameOver;
	boolean levelFinished;
	long l;
	int score;
	
	KeyHandler kh;
	Random rn;
	
	PriorityQueueUsingLinkedList pq;
	
	ArrayList<Image_Object> poles;
	
//	Image_Object spaceBackground;
//	Image_Object spaceDebrisSmall1;
//	Image_Object spaceDebrisSmall2;
//	Image_Object spaceDebrisBig1;
//	Image_Object spaceDebrisBig2;
	
	Image_Object birdAlive;
	Image_Object birdDead;
	
	final int FPS = 60;
	
	Level_2_Screen(){
		pq = new PriorityQueueUsingLinkedList();
		kh = new KeyHandler();
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		
		this.addKeyListener(kh);
		
		this.setBackground(Color.yellow);
		this.setDoubleBuffered(true);
		
		this.setFocusable(true);
		this.setVisible(true);
		innit();
	}
	
	public void innit()
	{
		rn = new Random();
		poles = new ArrayList<>();
		pq.add(new Image_Object("space","SPACE_FIGMA_01.png",0,0,1400,700,10));
		pq.add(new Image_Object("space deb big","SPACE_DEBRIS_BIG_FIGMA_03.png",0,0,2800,700,9));
		pq.add(new Image_Object("space deb big" ,"SPACE_DEBRIS_BIG_FIGMA_03.png",2800,0,2800,700,9));
		pq.add(new Image_Object("space deb small","SPACE_DEBRIS_SMALL_FIGMA_02.png",0,0,1400,700,8));
		pq.add(new Image_Object("space deb small","SPACE_DEBRIS_SMALL_FIGMA_02.png",1400,0,1400,700,8));
		

		birdAlive = new Image_Object("bird alive","BIRD_ALIVE_FIGMA_02.png", 100, 100, 50, 50,5);
		birdDead = new Image_Object("bird dead","BIRD_DEAD_FIGMA_02.png", birdAlive.x, birdAlive.y, 50, 50,5);
		
		int x = 0;
		int y = 700+200;
		for(int i = 0; i<10; i++)
		{
			int t1 = -500 + rn.nextInt(100);
			poles.add(new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+x, t1, 70, 700, 7));
			poles.add(new Image_Object("metal pole","METAL_POLE_FIGMA_02.png", 500+x, t1+y, 70, 700, 7));
			x += 400+70;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub	
			long lastTime = System.nanoTime();
			double nsPerTick = 1000000000.0 / FPS;
			double delta = 0;
			
			
			while (level2Thread != null) {
				long now = System.nanoTime();
				delta += (now - lastTime) / nsPerTick;
				lastTime = now;
				while (delta >= 1) {
					if(t >= 180)
	        			stopLevel2Screen();	
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
	
	public void update(){
//		System.out.println("update");
		l++;
		
		if(kh.space)
			birdAlive.y -= 3;
		else
			birdAlive.y += 2;
		
//		if(l%6 == 0)
//		{
//			spaceDebrisBig1.x -= 1;
//			spaceDebrisBig2.x -= 1;
//		}
//		if(l%2 == 0)
//		{
//			spaceDebrisSmall1.x -= 1;
//			spaceDebrisSmall2.x -= 1;
//		}
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			
			if(l%6 == 0 && imgO.name.equals("space deb big"))
				imgO.x--;
			if(l%2 == 0 && imgO.name.equals("space deb small"))
				imgO.x--;
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
		if(score >= 10)
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
	
	public void startLevel2Screen() {
		level2Thread = new Thread(this);
		level2Thread.start();
	}
	
	public void stopLevel2Screen() {
		level2Thread = null;
		Frame.i = -10;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			g2.drawImage(imgO.img, imgO.x, imgO.y, null);
			
			if(imgO.name.equals("space deb big") || imgO.name.equals("space deb small"))
				imgO.updateImageLocation();
		}
//		g2.drawImage(spaceBackground.img, spaceBackground.x, spaceBackground.y, null);
//		g2.drawImage(spaceDebrisBig1.img, spaceDebrisBig1.x, spaceDebrisBig1.y, null);
//		g2.drawImage(spaceDebrisBig2.img, spaceDebrisBig2.x, spaceDebrisBig2.y, null);
//		g2.drawImage(spaceDebrisSmall1.img, spaceDebrisSmall1.x, spaceDebrisSmall1.y, null);
//		g2.drawImage(spaceDebrisSmall2.img, spaceDebrisSmall2.x, spaceDebrisSmall2.y, null);
		
		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			g2.drawImage(pole.img, pole.x, pole.y , null);
		}
		
		g2.drawImage(birdAlive.img, birdAlive.x, birdAlive.y, null);
		
//		spaceDebrisBig1.updateImageLocation();
//		spaceDebrisBig2.updateImageLocation();
//		spaceDebrisSmall1.updateImageLocation();
//		spaceDebrisSmall2.updateImageLocation();
		
		if(gameOver) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Game Over", 700-150, 350);
			
		}
		
		if(levelFinished) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Level Finished", 700-150, 350);
			Frame.isLevel_2_Cleared = true;
			try {
				Frame.db.setLevelBooleans();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		g2.dispose();
	}
}
