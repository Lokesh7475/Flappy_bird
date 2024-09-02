package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	boolean space = false;
	
	boolean enter = false;
	boolean tab = false;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
//		System.out.println(code);
		if(code == KeyEvent.VK_UP)
			up = true;
		if(code == KeyEvent.VK_RIGHT)
			right = true;
		if(code == KeyEvent.VK_LEFT)
			left = true;
		if(code == KeyEvent.VK_DOWN)
			down = true;
		
		if(code == KeyEvent.VK_ENTER)
			enter = true;
		
		if(code == KeyEvent.VK_TAB)
			tab = true;
		
		if(code == KeyEvent.VK_SPACE)
			space = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP)
			up = false;
		if(code == KeyEvent.VK_RIGHT)
			right = false;
		if(code == KeyEvent.VK_LEFT)
			left = false;
		if(code == KeyEvent.VK_DOWN)
			down = false;

		if(code == KeyEvent.VK_ENTER)
			enter = false;
		
		if(code == KeyEvent.VK_TAB)
			tab = false;

		if(code == KeyEvent.VK_SPACE)
			space = false;
	}	
}
