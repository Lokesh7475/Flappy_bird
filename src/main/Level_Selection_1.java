package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Level_Selection_1 extends JPanel implements Runnable{

	Thread lsThread;
	Image_Object l1_img;
	Image_Object l2_img;
	Image_Object l3_img;
	Image_Object lock_img;
	
	Image_Object back_img;
	
//	int ballX = 0;
//	int ballY = 100;
	
	MouseHandler mh = new MouseHandler();
	
	final int FPS = 60;
	
	Level_Selection_1()
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 700));
		this.setBackground(Color.black);
		
		this.addMouseListener(mh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		
		innit();
	}
	
	void innit(){
		l1_img = new Image_Object("l1b", "LEVEL_1_BANNER.png", 125, 250, 300, 200, 10);
		l2_img = new Image_Object("l2b", "LEVEL_2_BANNER.png", 125+300+125, 250, 300, 200, 10);
		l3_img = new Image_Object("l3b", "LEVEL_3_BANNER.png", 125+300+125+300+125, 250, 300, 200, 10);
		lock_img = new Image_Object("lock", "LOCK_FIGMA_02.png", 125+300+125, 250, 300, 200, 10);
		
		back_img = new Image_Object("back", "BACK_FIGMA_02.png", 0, 0, 300, 200, 10);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        
        while (lsThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                update(); // Update game logic
                delta--;
            }
            repaint(); // Render game graphics
		}
        
        repaint();
		
//		while(true) {
//			update();
//			repaint();
//		}
	}
	
	public void update(){
//		System.out.println("level selection ");
//		ballX++;
		
		if(mh.back_ms_ls)
		{
			Frame.i = 4;
			stop_level_selection();
			mh.back_ms_ls = false;
		}
		
		if(mh.lvl_1_ls)
		{
			Frame.i = 9;
			stop_level_selection();
			mh.lvl_1_ls = false;
		}
		
		if(mh.lvl_2_ls && Frame.isLevel_1_Cleared)
		{
			Frame.i = 10;
			stop_level_selection();
			mh.lvl_2_ls = false;
		}
		else if(mh.lvl_2_ls)
		{
			mh.lvl_2_ls = false;
			System.out.println("Complete level 1");
		}			
		
		if(mh.lvl_3_ls && Frame.isLevel_2_Cleared)
		{
			Frame.i = 11;
			stop_level_selection();
			mh.lvl_3_ls = false;
		}
		else if(mh.lvl_3_ls)
		{
			mh.lvl_3_ls = false;
			System.out.println("Complete level 2");
		}
	}
	
	public void start_level_selection()
	{
		lsThread = new Thread(this);
		lsThread.start();
	}
	
	public void stop_level_selection()
	{
		lsThread = null;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
//		InputStream is1 = getClass().getResourceAsStream("LEVEL_1_BANNER.png");
//		try {
//			l1_img = ImageIO.read(is1);
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		
//		InputStream is2 = getClass().getResourceAsStream("LEVEL_2_BANNER.png");
//		try {
//			l2_img = ImageIO.read(is2);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		InputStream is3 = getClass().getResourceAsStream("LEVEL_3_BANNER.png");
//		try {
//			l3_img = ImageIO.read(is3);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		InputStream is4 = getClass().getResourceAsStream("BACK_FIGMA_02.png");
//		try {
//			back_img = ImageIO.read(is4);
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		
//		InputStream is5 = getClass().getResourceAsStream("LOCK_FIGMA_02.png");
//		try {
//			lock_img = ImageIO.read(is5);
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
		
		g2.drawImage(l1_img.img, l1_img.x, l1_img.y,null);
		g2.drawImage(l2_img.img, l2_img.x, l2_img.y,null);
		g2.drawImage(l3_img.img, l3_img.x, l3_img.y,null);
		
		g2.drawImage(back_img.img, back_img.x, back_img.y, null);
		
		if(!Frame.isLevel_1_Cleared) {
			g2.drawImage(lock_img.img, 125+300+125, 250,null);
		}
		
		if(!Frame.isLevel_2_Cleared) {
			g2.drawImage(lock_img.img, 125+300+125+300+125, 250,null);
		}
		
//		g2.fillOval(ballX, ballY, 50, 50);
		
		g2.dispose();
	}
}
