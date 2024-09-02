package main;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable{
	
	Home_Screen hs = new Home_Screen();
	
//	LvL_01 l1 = new LvL_01();
	
	Mode_Selection_Screen_1 modeSelectionScreen = new Mode_Selection_Screen_1();
	Level_Selection_1 levelSelectionScreen = new Level_Selection_1();
	
	Login_Screen loginScreen = new Login_Screen();
	Signup_Screen signupScreen = new Signup_Screen();
	Options_Screen optionsScreen = new Options_Screen();
	Change_Password_Screen changePasswordScreen = new Change_Password_Screen();
	Change_Username_Screen changeUsernameScreen = new Change_Username_Screen();
	Delete_Screen deleteScreen = new Delete_Screen();
	Level_1_Screen level1Screen = new Level_1_Screen();
	Level_2_Screen level2Screen = new Level_2_Screen();
	Level_3_Screen level3Screen = new Level_3_Screen();
	Freemode_Screen freemodeScreen = new Freemode_Screen();
	Freemode_Screen_1 freemodeScreen1 = new Freemode_Screen_1();

	static Database db = new Database();
	
	static boolean isLevel_1_Cleared = false;
	static boolean isLevel_2_Cleared = false;
	static boolean isLevel_3_Cleared = false;
	
	static String currentPlayerName;
	static int currentSessionId;
	
	static int i = 0;
	int t = Integer.MIN_VALUE;
	
	Thread fThread;
	
	final int FRAME_WIDTH = 1400;
	final int FRAME_HEIGHT = 700;
	
	final int FPS = 60;
	
	Frame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		this.addWindowListener(new WindowAdapter(){

		    public void windowClosing(WindowEvent e){
		        // The window is closing
		    	System.out.println("closing");
		    	try {
					db.setEnd();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }

		});
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));	
		this.setSize(getPreferredSize());									
		this.setTitle("F Bird");											
		this.setResizable(false);											
//		this.add(loginScreen);
//		this.add(new Test_imgIcon());
//		this.add(signupScreen);

//		addLevel1Screen();
//		addLevel_Selection();
//		addOptions_Screen();
//		addHome_Screen();
		addLogin_Screen();
//		addSignup_Screen();
//		freemodeScreen.startFreemode_Screen();
//		this.add(freemodeScreen);
//		this.add(new Change_Password_Screen());
//		this.add(new Change_Username_Screen());
//		this.add(new Delete_Screen());
//		level1Screen.startLevel_1();
//		this.add(level1Screen);
//		level2Screen.startLevel2Screen();
//		this.add(level2Screen);
//		level3Screen.startLevel3Screen();
//		this.add(level3Screen);
		this.setVisible(true);												
	}
	
	void startFrame()
	{
		fThread = new Thread(this);											
		fThread.start();													
	}
	
	void stopFrame()
	{
		fThread = null;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;
        
        while (fThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                update(); // Update game logic
                delta--;
            }
        }
		
//		while(fThread != null)
//		{
//			update();
//		}
	}
	
	public void update()
	{
//		System.out.println("frame");
//		switch case for panel selection
//System.out.println(" ");
		
		switch(i)
		{
			case -13:
				addFreeModeScreen();
				removeFreeModeScreen1();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -12:
				addmodeSelectionScreen_Screen();
				removeFreeModeScreen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case -11:
				addLevel_Selection();
				removeLevel3Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -10:
				addLevel_Selection();
				removeLevel2Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -9:
				addLevel_Selection();
				removeLevel1Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -8:
				addOptions_Screen();
				removeDeleteScreen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			
			case -7:
				addOptions_Screen();
				removeChangeUsername_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -6:
				addOptions_Screen();
				removeChangePassword_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -5:
				addOptions_Screen();
				removeHome_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -4:
				addHome_Screen();
				removeSignup_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -3:
				addHome_Screen();
				removeLogin_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -2:
				addLogin_Screen();
				removeSignup_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case -1:
				addSignup_Screen();
				removeLogin_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 0:
				break ;
			case 1:
				addmodeSelectionScreen_Screen();
				removeHome_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 2:
				addHome_Screen();
				removemodeSelectionScreen_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 3:
				addLevel_Selection();
				removemodeSelectionScreen_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 4:
				addmodeSelectionScreen_Screen();
				removeLevel_Selection();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 5:
				addHome_Screen();
				removeOptions_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 6:
				addChangePassword_Screen();
				removeOptions_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 7:
				addChangeUsername_Screen();
				removeOptions_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 8:
				addDeleteScreen();
				removeOptions_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 9:
				addLevel1Screen();
				removeLevel_Selection();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 10:
				addLevel2Screen();
				removeLevel_Selection();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 11:
				addLevel3Screen();
				removeLevel_Selection();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 12:
				addFreeModeScreen();
				removemodeSelectionScreen_Screen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break ;
			case 13:
				addFreeModeScreen1();
				removeFreeModeScreen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
			case 14:
				addLogin_Screen();
				removeDeleteScreen();
				this.getContentPane().validate();
				this.getContentPane().repaint();
				i = 0;
				break;
		}
	}
	
	public void addLogin_Screen(){
		this.add(loginScreen);
		this.setVisible(true);
	}
	
	public void removeLogin_Screen(){
		this.remove(loginScreen);
		this.setVisible(true);
	}
	
	public void addSignup_Screen(){
		this.add(signupScreen);
		this.setVisible(true);
	}
	
	public void removeSignup_Screen(){
		this.remove(signupScreen);
		this.setVisible(true);
	}
	
	public void addHome_Screen() {
		hs.startHs();
		this.add(hs);
		this.setVisible(true);
	}
	
	public void removeHome_Screen() {
		this.remove(hs);
		this.setVisible(true);
	}
	
	public void addmodeSelectionScreen_Screen() {
		this.add(modeSelectionScreen);
		modeSelectionScreen.startS1();
		this.setVisible(true);
	}
	
	public void removemodeSelectionScreen_Screen() {
		this.remove(modeSelectionScreen);
		this.setVisible(true);
	}
	
	public void addLevel_Selection()
	{
		this.add(levelSelectionScreen);
		levelSelectionScreen.start_level_selection();
		this.setVisible(true);
	}
	
	public void removeLevel_Selection()
	{
		this.remove(levelSelectionScreen);
		this.setVisible(true);
	}
	
	public void addOptions_Screen() {
		this.add(optionsScreen);
		optionsScreen.startOptions_Screen();
		this.setVisible(true);
	}
	
	public void removeOptions_Screen() {
		this.remove(optionsScreen);
		this.setVisible(true);
	}
	
	public void addChangePassword_Screen() {
		this.add(changePasswordScreen);
		this.setVisible(true);
	}
	
	public void removeChangePassword_Screen() {
		this.remove(changePasswordScreen);
		this.setVisible(true);
	}
	
	public void addChangeUsername_Screen() {
		this.add(changeUsernameScreen);
		this.setVisible(true);
	}
	
	public void removeChangeUsername_Screen() {
		this.remove(changeUsernameScreen);
		this.setVisible(true);
	}
	
	public void addDeleteScreen() {
		this.add(deleteScreen);
		this.setVisible(true);
	}
	
	public void removeDeleteScreen() {
		this.remove(deleteScreen);
		this.setVisible(true);
	}
	
	public void addLevel1Screen() {
		level1Screen = new Level_1_Screen();
		level1Screen.startLevel_1();
		this.add(level1Screen);
		this.setVisible(true);
	}
	
	public void removeLevel1Screen() {
		this.remove(level1Screen);
		this.setVisible(true);
	}
	
	public void addLevel2Screen() {
		level2Screen = new Level_2_Screen();
		level2Screen.startLevel2Screen();
		this.add(level2Screen);
		this.setVisible(true);
	}
	
	public void removeLevel2Screen() {
		this.remove(level2Screen);
		this.setVisible(true);
	}
	
	public void addLevel3Screen() {
		level3Screen = new Level_3_Screen();
		level3Screen.startLevel3Screen();
		this.add(level3Screen);
		this.setVisible(true);
	}
	
	public void removeLevel3Screen() {
		this.remove(level3Screen);
		this.setVisible(true);
	}
	
	public void addFreeModeScreen() {
		this.add(freemodeScreen);
		freemodeScreen.startFreemode_Screen();
		this.setVisible(true);
	}

	public void removeFreeModeScreen() {
		this.remove(freemodeScreen);
		this.setVisible(true);
	}
	
	public void addFreeModeScreen1() {
		freemodeScreen1 = new Freemode_Screen_1();
		this.add(freemodeScreen1);
		freemodeScreen1.startFreemode1_Screen();
		this.setVisible(true);
	}

	public void removeFreeModeScreen1() {
		this.remove(freemodeScreen1);
		this.setVisible(true);
	}
}
