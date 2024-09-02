package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

public class Freemode_Screen extends JPanel implements Runnable{

	Thread freemodeThread;
	final int FPS = 60;
	Image_Object background;
	int mapIndex = 0;
	String mapString = "Space";
	MouseHandler mh = new MouseHandler();
	int mapStringX = 1150;
	int mapStringY = 375;
	Image_Object city_map;
	Image_Object space_map;
	Image_Object mars_map;
	
	String srNo = "--";
	String name = "--";
	String score = "--";
	

	boolean first5 = true;
	
	Freemode_Screen(){
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		
		this.addMouseListener(mh);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		
		innit();
	}
	
	public void innit() {
		background = new Image_Object("background","FREE_MODE_03.png", -10, -20, 1400, 700, 10);
		city_map = new Image_Object("city","LEVEL_1_BANNER.png", 1065, 105, 300, 200, 9);
		space_map = new Image_Object("space","LEVEL_2_BANNER.png", 1065, 105, 300, 200, 9);
		mars_map = new Image_Object("mars","LEVEL_3_BANNER.png", 1065, 105, 300, 200, 9);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        
        while (freemodeThread != null) {
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
	
	public void startFreemode_Screen() {
		freemodeThread = new Thread(this);
		freemodeThread.start();
	}
	
	public void stopFreemode_Screen() {
		freemodeThread = null;
	}
	
	public void update() {
		if(mh.up_arrow_freemode) {
//			System.out.println("up");
			first5 = true;
			mh.up_arrow_freemode = false;
		}
		if(mh.down_arrow_freemode) {
//			System.out.println("down");
			first5 = false;
			mh.down_arrow_freemode = false;
		}
		if(mh.right_arrow_freemode) {
			System.out.println("right");
			mh.right_arrow_freemode = false;
			
			if(mapIndex == 2)
				mapIndex = 0;
			else
				mapIndex++;
		}
		if(mh.left_arrow_freemode) {
			System.out.println("left");
			mh.left_arrow_freemode = false;
			
			if(mapIndex == 0)
				mapIndex = 2;
			else
				mapIndex--;
		}
		if(mh.play_freemode){
			Frame.i = 13;
			Freemode_Screen_1.map = mapIndex;
			mh.play_freemode = false;
			stopFreemode_Screen();
			
		}
		if(mh.back_freemode){
			Frame.i = -12;
			mh.back_freemode = false;
			stopFreemode_Screen();
		}
		
		switch(mapIndex) {
			case 0:
				mapString = "City";
				mapStringX = 1170;
				break;
			case 1:
				mapString = "Space";
				mapStringX = 1150;
				break;
			case 2:
				mapString = "Mars";
				mapStringX = 1170;
				break;
		}
	}
	
//	int y = 75;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background.img, background.x, background.y, null);
		
		g2.setColor(Color.cyan);
		g2.setFont(Arcade_Font.arcade75);
		g2.drawString("Free Mode", 30, 75);
		g2.drawString("Leader Board", 30, 175);
		
		ResultSet rs = null;
		
		try {
			if(mapIndex == 0)
				rs = Frame.db.getTriesFromMap0();
			else if(mapIndex == 1)
				rs = Frame.db.getTriesFromMap1();
			else
				rs = Frame.db.getTriesFromMap2();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		g2.setFont(Arcade_Font.arcade50);
		g2.drawString("Sr No", 30, 265);
		g2.drawString("Name", 300, 265);
		g2.drawString("Score", 800, 265);
		
		int infoStringY = 265;
		
		try {
			if(first5 && rs != null)
			{
				for(int i = 0; i<5; i++)
				{
					boolean isNotNull = rs.next();
					if(!isNotNull)
						break;
					srNo = ""+(i+1);
					infoStringY += 75;
					g2.drawString("  "+srNo, 30, infoStringY);
					if(isNotNull)
					{
						name = Frame.db.getName(rs.getInt(1));
						score = ""+rs.getInt(2);
						g2.drawString(" "+name, 300, infoStringY);
						g2.drawString("  "+score, 800, infoStringY);
					}
					else
						break;
				}
			}
			else if(rs != null){
				for(int i = 0; i<10; i++)
				{
					boolean isNotNull = rs.next();
					if(!isNotNull)
						break;
					srNo = ""+(i+1);
					if(isNotNull && i > 5)
					{
						infoStringY += 75;
						g2.drawString("  "+srNo, 30, infoStringY);
						name = Frame.db.getName(rs.getInt(1));
						score = ""+rs.getInt(2);
						g2.drawString(" "+name, 300, infoStringY);
						g2.drawString("  "+score, 800, infoStringY);
					}
					else if(rs != null)
						continue;
					else
						break ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g2.drawString(mapString, mapStringX, mapStringY);
		g2.drawString("Play", 1170, 450);
		g2.drawString("Back", 1170, 650);
		
		g2.fillRect(1065, 105, 300, 200);
		
		if(mapIndex == 0)
			g2.drawImage(city_map.img, city_map.x, city_map.y, null);
		else if(mapIndex == 1)
			g2.drawImage(space_map.img, space_map.x, space_map.y, null);
		else
			g2.drawImage(mars_map.img, mars_map.x, mars_map.y, null);
			
		
//		g2.fillRect(990, 205, 50, 50);
//		g2.fillRect(990, 605, 50, 50);
//		g2.fillRect(1065, 330, 50, 50);
//		g2.fillRect(1315, 330, 50, 50);
		
//		g2.fillRect(1065, 605, 300, 50);
		
		g2.dispose();
	}
}
