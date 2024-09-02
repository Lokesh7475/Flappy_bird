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

public class Options_Screen extends JPanel implements Runnable{
	
	Image_Object background;
	BufferedImage change_username_us_01;
	BufferedImage change_username_s_01;
	BufferedImage change_password_us_01;
	BufferedImage change_password_s_01;
	BufferedImage delete_account_us_01;
	BufferedImage delete_account_s_01;
	BufferedImage back_text_us_01;
	BufferedImage back_text_s_01;
	
	Thread osThread;
	
	KeyHandler kh = new KeyHandler();
	MouseHandler mh = new MouseHandler();
	
	final int FPS = 60;
	
	int onButton = -1;
	
	Options_Screen()
	{
		this.setLayout(null);
		
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		this.setBackground(Color.cyan);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(kh);
		this.addMouseListener(mh);
		setImage();
		this.setVisible(true);
	}
	
	public void setImage(){
		background = new Image_Object("opitons bg","SPACE_FIGMA_01.png", 0,0, 1400, 700,10);
		InputStream is1 = getClass().getResourceAsStream("CHANGE_USERNAME_UNSELECTED_FIGMA_01.png");
		try {
			change_username_us_01 = ImageIO.read(is1);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is10 = getClass().getResourceAsStream("CHANGE_USERNAME_SELECTED_FIGMA_01.png");
		try {
			change_username_s_01 = ImageIO.read(is10);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is2 = getClass().getResourceAsStream("CHANGE_PASSWORD_UNSELECTED_FIGMA_01.png");
		try {
			change_password_us_01 = ImageIO.read(is2);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is20 = getClass().getResourceAsStream("CHANGE_PASSWORD_SELECTED_FIGMA_01.png");
		try {
			change_password_s_01 = ImageIO.read(is20);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is3 = getClass().getResourceAsStream("DELETE_ACCOUNT_UNSELECTED_FIGMA_01.png");
		try {
			delete_account_us_01 = ImageIO.read(is3);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is30 = getClass().getResourceAsStream("DELETE_ACCOUNT_SELECTED_FIGMA_01.png");
		try {
			delete_account_s_01 = ImageIO.read(is30);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is4 = getClass().getResourceAsStream("BACK_TEXT_UNSELECTED_FIGMA_01.png");
		try {
			back_text_us_01 = ImageIO.read(is4);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		InputStream is40 = getClass().getResourceAsStream("BACK_TEXT_SELECTED_FIGMA_01.png");
		try {
			back_text_s_01 = ImageIO.read(is40);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
//        System.out.println("a");
        
        while (osThread != null) {
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
	}
	
	public void update() {
		if(mh.back_os)
		{
			onButton = 3;
			back();
		}
		
		if(mh.changePassword_os)
		{
			onButton = 1;
			changePassword();
		}
		
		if(mh.changeUsername_os)
		{
			onButton = 0;
			changeUsername();
		}
		
		if(mh.delete_os)
		{
			onButton = 2;
			delete();
		}
		
		if(kh.up)
		{
			if(onButton == -1)
			{
				onButton = 0;
			}
			else if(onButton == 0)
			{
				onButton = 3;
			}
			else
				onButton--;
			kh.up = false;
		}
		
		if(kh.down)
		{
			if(onButton == -1)
			{
				onButton = 0;
			}
			else if(onButton == 3)
			{
				onButton = 0;
			}
			else
				onButton++;
			kh.down = false;
		}
		
		if(kh.enter) {
			switch(onButton) {
				case 0:
					changeUsername();
					break;
				case 1:
					changePassword();
					break;
				case 2:
					delete();
					break;
				case 3:
					back();
					break;
			}
			kh.enter = false;
		}
	}
	
	public void startOptions_Screen() {
		osThread = new Thread(this);
		osThread.start();
	}
	public void stopOptions_Screen() {
		osThread = null;
	}
	
	public void back() {
		Frame.i = 5;
		mh.back_os = false;
		stopOptions_Screen();
	}
	
	public void changePassword() {
		Frame.i = 6;
		mh.changePassword_os = false;
		stopOptions_Screen();
	}
	
	public void changeUsername() {
		Frame.i = 7;
		mh.changeUsername_os = false;
		stopOptions_Screen();
	}
	
	public void delete() {
		Frame.i = 8;
		mh.delete_os = false;
		stopOptions_Screen();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background.img, background.x, background.y, null);
		
		if(onButton == 0)
			g2.drawImage(change_username_s_01, 500, 75, null);
		else
			g2.drawImage(change_username_us_01, 500, 75, null);
		
		if(onButton == 1)
			g2.drawImage(change_password_s_01, 500, 75+100+50, null);
		else
			g2.drawImage(change_password_us_01, 500, 75+100+50, null);
		
		if(onButton == 2)
			g2.drawImage(delete_account_s_01, 500, 75+100+50+100+50, null);
		else
			g2.drawImage(delete_account_us_01, 500, 75+100+50+100+50, null);
		
		if(onButton == 3)
			g2.drawImage(back_text_s_01, 500, 75+100+50+100+50+100+50, null);
		else
			g2.drawImage(back_text_us_01, 500, 75+100+50+100+50+100+50, null);
		
		g2.dispose();
	}
}
