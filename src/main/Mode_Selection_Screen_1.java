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

public class Mode_Selection_Screen_1 extends JPanel implements Runnable{

	Thread ts1;
	final int FPS = 60;
	
	BufferedImage back_img;
	BufferedImage m1_img;
	BufferedImage m2_img;
	
	MouseHandler mh = new MouseHandler();
	Mode_Selection_Screen_1()
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 700));
		this.setBackground(Color.BLACK);
		
		this.addMouseListener(mh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void startS1()
	{
		ts1 = new Thread(this);
		ts1.start();
	}
	public void stopS1()
	{
		ts1 = null;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        
        while (ts1 != null) {
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

	public void update()
	{
//		System.out.println("screen 1");
		
		if(mh.back_ms_ls) {
			Frame.i = 2;
			mh.back_ms_ls = false;
			stopS1();
		}
		
		if(mh.goToLevelSelection)
		{
			Frame.i = 3;
			mh.goToLevelSelection = false;
			stopS1();
		}
		
		if(mh.goToFreeMode)
		{
			Frame.i = 12;
			mh.goToFreeMode = false;
			stopS1();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		InputStream is1 = getClass().getResourceAsStream("BACK_FIGMA_02.png");
		try {
			back_img = ImageIO.read(is1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream is2 = getClass().getResourceAsStream("LEVELS_M1_FIGMA_01.png");
		try {
			m1_img = ImageIO.read(is2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream is3 = getClass().getResourceAsStream("FREEMODE_M2_FIGMA_01.png");
		try {
			m2_img = ImageIO.read(is3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g2.drawImage(back_img, 10, 10, null);
		g2.drawImage(m1_img, 100, 150, null);
		g2.drawImage(m2_img, 800, 150, null);
		g2.dispose();
		
	}
}
