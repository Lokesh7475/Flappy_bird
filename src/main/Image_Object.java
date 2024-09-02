package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image_Object {
	BufferedImage img;
	InputStream is;
	String name;
	int x;
	int y;
	int width;
	int height;
	int depth;
	
	boolean crossed;
	
	Image_Object(String name, String location, int x, int y, int width, int height, int depth)
	{
		is = getClass().getResourceAsStream(location);
		try {
			img = ImageIO.read(is);
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	void updateImageLocation(){
		if(x+width <= 0)
			x = width;
	}
	
	void updatePoleLocation() {
		if(x+width <= 0) {
			x = 1780;
			crossed = false;
		}
	}
	
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof Image_Object))
			return false;
		
		Image_Object imgO = (Image_Object) o;
		return imgO.img == img && imgO.width == width && imgO.height == height && imgO.depth == depth;
	}
}
