package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

	boolean play_hs = false;
	boolean options_hs = false;
	boolean exit_hs = false;
	boolean back_ms_ls = false;
	boolean back_os = false;
	boolean changePassword_os = false;
	boolean changeUsername_os = false;
	boolean delete_os = false;
	boolean goToLevelSelection = false;
	boolean goToFreeMode = false;
	boolean lvl_1_ls = false;
	boolean lvl_2_ls = false;
	boolean lvl_3_ls = false;
	boolean up_arrow_freemode = false;
	boolean down_arrow_freemode = false;
	boolean left_arrow_freemode = false;
	boolean right_arrow_freemode = false;
	boolean play_freemode = false;
	boolean back_freemode = false;
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("md");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("mm");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int x = e.getX();
		int y = e.getY();
		System.out.print("x = "+x+" y = "+y+" \n");
		
		if(x >= 600 && x <= 800 && y >= 375 && y <= 425)
			play_hs = true;
		if(x >= 600 && x <= 800 && y >= 450 && y<= 500)
			options_hs = true;
		if(x >= 600 && x <= 800 && y >= 525 && y <= 575)
			exit_hs = true;
		
		if(x >= 10 && x <= 160 && y >= 10 && y <= 85)
			back_ms_ls = true;
		
		if(x >= 500 && x <= 900 && y >= 525 && y <= 625)
			back_os = true;
		
		if(x >= 100 && x <= 600 && y >= 150 && y <= 550)
			goToLevelSelection = true;
		if(x >= 800 && x <= 1300 && y >= 150 && y <= 550)
			goToFreeMode = true;
			
		if(x >= 500 && x <= 900 && y >= 225 && y <= 325)
			changePassword_os = true;
		if(x >= 500 && x <= 900 && y >= 75 && y <= 175)
			changeUsername_os = true;
		if(x >= 500 && x <= 900 && y >= 375 && y <= 475)
			delete_os = true;
		
		if(x >= 125 && x<= 425 && y >= 250 && y <= 450)
			lvl_1_ls = true;
		if(x >= 125+300+125 && x<= 425+300+125 && y >= 250 && y <= 450)
			lvl_2_ls = true;
		if(x >= 125+300+125+300+125 && x<= 425+300+125+300+125 && y >= 250 && y <= 450)
			lvl_3_ls = true;

		if(x >= 990 && x <= 1040 && y >= 205 && y <= 255)
			up_arrow_freemode = true;
		if(x >= 990 && x <= 1040 && y >= 605 && y <= 655)
			down_arrow_freemode = true;
		
		if(x >= 1065 && x <= 1115 && y >= 330 && y <= 380)
			left_arrow_freemode = true;
		if(x >= 1315 && x <= 1365 && y >= 330 && y <= 380)
			right_arrow_freemode = true;
		if(x >= 1065 && x <= 1365 && y >= 405 && y <= 455)
			play_freemode = true;
		if(x >= 1065 && x <= 1365 && y >= 605 && y <= 655)
			back_freemode = true;
		
//		System.out.println(back_os);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("mp");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("mr");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
