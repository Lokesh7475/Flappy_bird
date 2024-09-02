package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Level_3_Screen extends JPanel implements Runnable{
	int t = 0;
	boolean gameOver;
	boolean levelFinished;
	Thread level3Thread;
	Random rn;
	boolean b1 = false;
	final int FPS = 60;
	KeyHandler kh;
	int score;
	
	PriorityQueueUsingLinkedList pq;
	
	ArrayList<Image_Object> poles = new ArrayList<>();
	
//	Image_Object space;
//	Image_Object desert;
//	Image_Object desert2;
//	Image_Object desert3;
//	Image_Object desertPyramid;
	
	Image_Object birdAlive;
	Image_Object birdDead;
	
	long l;
	
	Level_3_Screen(){
		pq = new PriorityQueueUsingLinkedList();
		kh = new KeyHandler();
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		this.setBackground(Color.CYAN);
		
		this.addKeyListener(kh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		
		innit();
	}
	
	void innit() {
		rn = new Random();
		pq.add(new Image_Object("space","SPACE_FIGMA_01.png", 0, 0, 1400, 700, 10));
		pq.add(new Image_Object("desert","DESERT_BLANK_FIGMA_02.png", 0, 0, 1400, 700, 9));
		pq.add(new Image_Object("desert pyramid","DESERT_PYRAMIDS_FIGMA_03.png", 1400, 0, 1400, 700, 9));
		pq.add(new Image_Object("desert u","DESERT_BLANK_FIGMA_02.png", 2800, 0, 1400, 700, 9));
		pq.add(new Image_Object("desert u","DESERT_BLANK_FIGMA_02.png", 4200, 0, 1400, 700, 9));

		birdAlive = new Image_Object("bird alive","BIRD_ALIVE_FIGMA_02.png", 100, 100, 50, 50,5);
		birdDead = new Image_Object("bird dead","BIRD_DEAD_FIGMA_02.png", birdAlive.x, birdAlive.y, 50, 50,5);
		
		int x = 0;
		int y = 700+200;
		for(int i = 0; i<15; i++)
		{
			int t1 = -500 + rn.nextInt(100);
			poles.add(new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+x, t1, 70, 700, 7));
			poles.add(new Image_Object("pyramid pole","PYRAMID_POLE_FIGMA_01.png", 500+x, t1+y, 70, 700, 7));
			x += 300+70;
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / FPS;
		double delta = 0;
		
		
		while (level3Thread != null) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (delta >= 1) {
				if(t >= 180)
        			stopLevel3Screen();	
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
	
	public void update()
	{
		l++;
		if(kh.space)
		{
			birdAlive.y -= 3;
			
		}
		else
			birdAlive.y += 2;
		
//		if(l%2 == 0) {
//			desert.x--;
//			desert2.x--;
//			desert3.x--;
//			desertPyramid.x--;
//		}
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			
			if(l%2 == 0 && (imgO.name.equals("desert u") || imgO.name.equals("desert")|| imgO.name.equals("desert pyramid")))
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
		if(score >= 15)
		{
			levelFinished = true;
		}
	}
	
	public boolean Collide()
	{
		if(birdAlive.y < 0 || (birdAlive.y+birdAlive.height) >= 700) {
			return true;
		}
		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			if((birdAlive.x+birdAlive.width>pole.x && birdAlive.x<pole.x+pole.width) && 
					(birdAlive.y+birdAlive.width > pole.y &&  birdAlive.y < pole.y + pole.height))
				return true;
		}
		return false;
	}
	
	public void startLevel3Screen() {
		level3Thread = new Thread(this);
		level3Thread.start();
	}
	
	public void stopLevel3Screen() {
		level3Thread = null;
		Frame.i = -11;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		Iterator_pq itr = pq.getIterator();
		while(itr.hasNext())
		{
			Image_Object imgO = itr.next();
			g2.drawImage(imgO.img, imgO.x, imgO.y, null);
			
			if(imgO.name.equals("desert u"))
				imgO.updateImageLocation();
		}
		
//		g2.drawImage(space.img, space.x, space.y, null);
//		g2.drawImage(desert.img, desert.x, desert.y, null);
//		g2.drawImage(desert2.img, desert2.x, desert2.y, null);
//		g2.drawImage(desert3.img, desert3.x, desert3.y, null);
//		g2.drawImage(desertPyramid.img, desertPyramid.x, desertPyramid.y, null);
		
		for(int i = 0; i<poles.size(); i++)
		{
			Image_Object pole = poles.get(i);
			g2.drawImage(pole.img, pole.x, pole.y , null);
		}

		g2.drawImage(birdAlive.img, birdAlive.x, birdAlive.y, null);
//		g2.draw3DRect(100, 100, 50, 50, false);
		
//		desert2.updateImageLocation();
//		desert3.updateImageLocation();
		
		if(gameOver) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Game Over", 700-150, 350);
		}
		
		if(levelFinished) {
			g2.setColor(Color.cyan);
			g2.setFont(Arcade_Font.getArcade75());
			g2.drawString("Level Finished", 700-150, 350);
			Frame.isLevel_3_Cleared = true;
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
