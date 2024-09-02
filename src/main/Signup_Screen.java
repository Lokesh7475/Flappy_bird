package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup_Screen extends JPanel implements ActionListener{

	HashMap<String, String> userInfo = new HashMap<>();

	JLabel titleLabel = new JLabel("Sign up");
	JLabel userIdLabel = new JLabel("user id : ");
	JLabel passwordLabel = new JLabel("password : ");
	JLabel confirmedPasswordLabel = new JLabel("confirm password : ");
	JLabel alreadyHaveAnAccountLabel = new JLabel("Already have an Account ?");
	JLabel invalidCredentialsLabel = new JLabel("password and confirmed password should be same!!!");
	
	JTextField idTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField confirmPasswordField = new JPasswordField();
	
	JButton signUpButton = new JButton("SignUp");
	JButton loginButton = new JButton("login");
	
	JLabel outputLabel;
	JLabel pwoutputLabel;
	
	Font arcade75;
	Font arcade18;
	
	ImageIcon background;
	JLabel pic;
	Signup_Screen()
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
		
		titleLabel.setBounds(550, 100, 300, 100);
		titleLabel.setFont(arcade75);
		
		alreadyHaveAnAccountLabel.setBounds(595, 450, 300, 25);
		alreadyHaveAnAccountLabel.setFocusable(false);
		alreadyHaveAnAccountLabel.setFont(arcade18);
		
		invalidCredentialsLabel.setBounds(575, 400, 300, 25);
		invalidCredentialsLabel.setFocusable(false);
		invalidCredentialsLabel.setFont(arcade18);
		
		userIdLabel.setBounds(550, 200, 300, 25);
		userIdLabel.setFont(arcade18);
		
		idTextField.setBounds(550, 225, 300, 25);
		idTextField.setFont(arcade18);
		
		passwordLabel.setBounds(550, 250, 300, 25);
		passwordLabel.setFont(arcade18);
		passwordField.setBounds(550, 275, 300, 25);
//		passwordField.setFont(arcade18);
		
		confirmedPasswordLabel.setBounds(550, 300, 300, 25);
		confirmedPasswordLabel.setFont(arcade18);
		confirmPasswordField.setBounds(550, 325, 300, 25);
//		confirmPasswordField.setFont(arcade18);
		
		signUpButton.setBounds(650, 375, 100, 25);
		signUpButton.setFont(arcade18);
		signUpButton.setFocusable(false);
		
		signUpButton.addActionListener(this);
		
		loginButton.setBounds(650, 475, 100, 25);
		loginButton.setFont(arcade18);
		loginButton.setFocusable(false);
		
		loginButton.addActionListener(this);
		
		background = new ImageIcon("src\\main\\Login_bg.png");
		pic = new JLabel();
		pic.setIcon(background);
		pic.setBounds(0,-50 ,1400, 700);
		
		outputLabel = new JLabel();
		pwoutputLabel = new JLabel();
		
		
		outputLabel.setFont(new Font(null, Font.PLAIN, 25));
		outputLabel.setBounds(1000,200, 100, 100);
		outputLabel.setFont(arcade18);
		
		pwoutputLabel.setFont(new Font(null, Font.PLAIN, 25));
		pwoutputLabel.setBounds(1000,400, 100, 100);
		pwoutputLabel.setFont(arcade18);
		

		invalidCredentialsLabel.setVisible(false);
		this.add(invalidCredentialsLabel);
		this.add(alreadyHaveAnAccountLabel);
		this.add(outputLabel);
		this.add(pwoutputLabel);
		
		this.add(titleLabel);
		this.add(userIdLabel);
		this.add(idTextField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(confirmedPasswordLabel);
		this.add(confirmPasswordField);
		this.add(signUpButton);
		this.add(loginButton);
		
		this.add(pic);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 700));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = idTextField.getText();
		String p = String.valueOf(passwordField.getPassword());
		String cp = String.valueOf(confirmPasswordField.getPassword());
		if(e.getSource() == signUpButton && match(s, p, cp))
		{
			try {
				if(Frame.db.insertPlayer(s, p))
				{
					Frame.i = -4;
					Frame.currentPlayerName = s;
					Frame.db.getLevelBooleans();
					Frame.db.setStart();
					idTextField.setText("");
					passwordField.setText("");
				}
				else
				{
					invalidCredentialsLabel.setText("Name not available");
					invalidCredentialsLabel.setVisible(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			outputLabel.setText(s);
//			pwoutputLabel.setText(p);
		}
		else if(e.getSource() == loginButton) {
			Frame.i = -2;
		}
		else
		{
			invalidCredentialsLabel.setVisible(true);	// change the message and logic
		}
	}
	
	public boolean match(String s, String p, String cp)
	{
		if(userInfo.containsKey(s))
			return false;

		if(!p.equals(cp))
			return false;
		userInfo.put(s, p);
		return true;
	}
}
