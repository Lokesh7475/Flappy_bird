package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Arcade_Font {
	static Font arcade75;
	static Font arcade50;
	static Font arcade18;
	static {
		try {
			arcade75 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\ARCADE.ttf")).deriveFont(75f);
			arcade50 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\ARCADE.ttf")).deriveFont(50f);
			arcade18 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\ARCADE.ttf")).deriveFont(18f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(arcade75);
			ge.registerFont(arcade50);
			ge.registerFont(arcade18);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Font getArcade75()
	{
		return arcade75;
	}
	static Font getArcade50()
	{
		return arcade50;
	}
	static Font getArcade18()
	{
		return arcade18;
	}
}
