package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Home_Screen extends JPanel implements Runnable{
	
	Thread hsThread;
	Image_Object hs_bg;
	Image_Object play_us_img;
	Image_Object play_s_img;
	Image_Object options_us_img;
	Image_Object options_s_img;
	Image_Object exit_us_img;
	Image_Object exit_s_img;
	
	KeyHandler kh = new KeyHandler();
	MouseHandler mh = new MouseHandler();
	
//	int ballX = 100;
//	int ballY = 100;
	
	final int FPS = 60;
	
	int onButton = -1;
	Home_Screen(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 700));
		this.setBackground(Color.BLUE);
		
		this.addKeyListener(kh);
		this.addMouseListener(mh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		setImage();
		this.setVisible(true);
		
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        
        while (hsThread != null) {
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
		
//		while(hsThread!=null)
//		{
//			update();
//			
//		}
	}
	
	public void update() {
//		System.out.println("home");
//		ballX++;
		
//		System.out.println(kh);
		if(mh.play_hs) {
			onButton = 0;
			play();
		}
		
		if(mh.options_hs)
		{
			onButton = 1;
			options();	
		}
		
		if(mh.exit_hs){
			onButton = 2;
			exit();
		}
//		System.out.println(kh.up);
		if(kh.up)
		{
			if(onButton == -1)
				onButton = 0;
			else if(onButton == 0)
				onButton = 2;
			else
				onButton--;
			kh.up = false;
		}
		if(kh.down)
		{
			if(onButton == -1)
				onButton = 0;
			else if(onButton == 2)
				onButton = 0;
			else
				onButton++;
			kh.down = false;
		}
		
		if(kh.enter)
		{
			switch(onButton) {
			case 0:
				play();
				break;
			case 1:
				options();
				break;
			case 2:
				exit();
				break;
			}
			kh.enter = false;
		}
			
	}
	
	public void play()
	{
		Frame.i = 1;
		mh.play_hs = false;
		stopHs();
	}
	
	public void options()
	{
//		logic for options
		Frame.i = -5;
		mh.options_hs = false;
		stopHs();
	}
	
	public void exit()
	{
		mh.exit_hs = false;
		stopHs();
		System.out.println("closing 2");
		try {
			Frame.db.setEnd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void startHs()
	{
		hsThread = new Thread(this);
		hsThread.start();
	}
	
	public void stopHs()
	{
		hsThread = null;
	}
	
	public void setImage()
	{
		hs_bg = new Image_Object("home screen","HOME_SCREEN_BG_FIGMA_01.png", 0,0, 1400, 700,10);
//		InputStream is1 = getClass().getResourceAsStream();
//		try {
//			hs_bg = ImageIO.read(is1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		play_us_img = new Image_Object("play us", "PLAY_UNSELECTED_FIGMA_02.png", 600, 375, 200, 50,10);
//		InputStream is3 = getClass().getResourceAsStream("enter_fb.png");
//		InputStream is3 = getClass().getResourceAsStream("PLAY_UNSELECTED_FIGMA_02.png");
//		try {
//			play_us_img = ImageIO.read(is3);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		play_s_img = new Image_Object("play s", "PLAY_SELECTED_FIGMA_01.png", 600, 375, 200, 50,10);
//		InputStream is30 = getClass().getResourceAsStream("PLAY_SELECTED_FIGMA_01.png");
//		try {
//			play_s_img = ImageIO.read(is30);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
		options_us_img = new Image_Object("options us", "OPTIONS_UNSELECTED_FIGMA_01.png", 600, 450, 200, 50,10);
//		InputStream is4 = getClass().getResourceAsStream("OPTIONS_UNSELECTED_FIGMA_01.png");
//		try {
//			options_us_img = ImageIO.read(is4);
//		}catch(IOException e){
//			e.printStackTrace();
//		}

		options_s_img = new Image_Object("options s", "OPTIONS_SELECTED_FIGMA_01.png", 600, 450, 200, 50,10);
//		InputStream is40 = getClass().getResourceAsStream("OPTIONS_SELECTED_FIGMA_01.png");
//		try {
//			options_s_img = ImageIO.read(is40);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		
		exit_us_img = new Image_Object("exit us", "EXIT_UNSELECTED_FIGMA_01.png", 600, 525, 200, 50,10);
//		InputStream is5 = getClass().getResourceAsStream("EXIT_UNSELECTED_FIGMA_01.png");
//		try {
//			exit_us_img = ImageIO.read(is5);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}

		exit_s_img = new Image_Object("exit s", "EXIT_SELECTED_FIGMA_01.png", 600, 525, 200, 50,10);
//		InputStream is50 = getClass().getResourceAsStream("EXIT_SELECTED_FIGMA_01.png");
//		try {
//			exit_s_img = ImageIO.read(is50);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(mh.exit_hs)
			return ;
		Graphics2D g2 = (Graphics2D) g;
//		System.out.println(onButton);
		g2.drawImage(hs_bg.img, hs_bg.x, hs_bg.y, null);
		g2.setColor(Color.green);
//		g2.fillOval(ballX, ballY, 50, 50);
		if(onButton == 0)
			g2.drawImage(play_s_img.img, play_s_img.x, play_s_img.y, null);
		else
			g2.drawImage(play_us_img.img, play_us_img.x, play_us_img.y, null);
		
		if(onButton == 1)
			g2.drawImage(options_s_img.img, options_s_img.x, options_s_img.y, null);
		else
			g2.drawImage(options_us_img.img, options_us_img.x, options_us_img.y, null);
		
		if(onButton == 2)
			g2.drawImage(exit_s_img.img, exit_s_img.x, exit_s_img.y, null);
		else
			g2.drawImage(exit_us_img.img, exit_us_img.x, exit_us_img.y, null);
		
		g2.dispose();
	}
}
