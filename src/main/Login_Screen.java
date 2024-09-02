package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class Login_Screen extends JPanel implements ActionListener{
	
	BufferedImage bg;
	
	JLabel userLabel = new JLabel("User id : ");
	JLabel passwordLabel = new JLabel("Password : ");
	JLabel titleLabel = new JLabel("Login");
	
	JLabel outputLabel;
	JLabel pwoutputLabel;
	
	JLabel dontHaveAnAccountLabel = new JLabel("Don't have an Account?");
	JLabel invalidCredentialsLabel = new JLabel("Invalid username or password!!!");
	
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	
	JButton signupButton = new JButton("Signup");
	
	BufferedImage img;
	
	Font arcade75;
	Font arcade18;
	
	JLabel pic;
	ImageIcon background;
	
	Login_Screen()
	{	
//		try {
//			arcade75 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\ARCADE.ttf")).deriveFont(75f);
//			arcade18 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\ARCADE.ttf")).deriveFont(18f);
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(arcade75);
//			ge.registerFont(arcade18);
//		} catch (FontFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		arcade75 = Arcade_Font.getArcade75();
		arcade18 = Arcade_Font.getArcade18();
		
		userTextField.setBounds(550, 250, 300, 25);
		userTextField.setFont(arcade18);
		passwordField.setBounds(550, 325, 300, 25);
		
		loginButton.setBounds(650, 375, 100, 25);
		loginButton.setFont(arcade18);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		signupButton.setBounds(650, 475, 100, 25);
		signupButton.setFont(arcade18);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		
		userLabel.setBounds(550, 225, 300, 25);
		userLabel.setFont(arcade18);
		passwordLabel.setBounds(550, 300, 300, 25);
		passwordLabel.setFont(arcade18);
		
		titleLabel.setFont(arcade75);
		titleLabel.setBounds(550, 100, 300, 100);
		
		dontHaveAnAccountLabel.setBounds(605, 450, 200, 25);
		dontHaveAnAccountLabel.setFont(arcade18);
		dontHaveAnAccountLabel.setFocusable(false);
		
		invalidCredentialsLabel.setBounds(570, 400, 300, 25);
		invalidCredentialsLabel.setFont(arcade18);
		invalidCredentialsLabel.setFocusable(false);
		invalidCredentialsLabel.setVisible(false);

		background = new ImageIcon("src\\main\\Login_bg.png");
		pic = new JLabel();
		pic.setBounds(0, -50, 1400, 700);
		pic.setIcon(background);
		pic.setVisible(true);
		
		outputLabel = new JLabel();
		pwoutputLabel = new JLabel();
		
		outputLabel.setFont(new Font(null, Font.PLAIN, 25));
		outputLabel.setBounds(1000,200, 100, 100);
		
		pwoutputLabel.setFont(new Font(null, Font.PLAIN, 25));
		pwoutputLabel.setBounds(1000,400, 100, 100);
		
		this.add(invalidCredentialsLabel);
		this.add(dontHaveAnAccountLabel);
		this.add(signupButton);
		
		this.add(outputLabel);
		this.add(pwoutputLabel);
		

//		titleLabel.setVisible(true);
		this.add(userTextField);
		this.add(passwordField);
		
		
		this.add(userLabel); 
		this.add(passwordLabel);
		
		this.add(titleLabel);
		
//		this.setBackground(Color.cyan);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		
		this.add(loginButton);
		this.add(pic);
		this.setVisible(true);
		
		initializeInfo();
	}
	
	public void initializeInfo()
	{
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = userTextField.getText();
		String p = String.valueOf(passwordField.getPassword());
		System.out.println(passwordField.getPassword().hashCode());
		try {
			if(e.getSource() == loginButton && Frame.db.isValid(s, p))
			{
				Frame.i = -3;
				Frame.currentPlayerName = s;
				Frame.db.getLevelBooleans();
				Frame.db.setStart();
				userTextField.setText("");
				passwordField.setText("");
			}
			else if(e.getSource() == signupButton)
				Frame.i = -1;
			else
				invalidCredentialsLabel.setVisible(true);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
