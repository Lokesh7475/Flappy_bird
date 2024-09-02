package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Change_Password_Screen extends JPanel implements ActionListener {

	JLabel titleLabel;
	JLabel enterOldPasswordLabel;
	JLabel enterNewPasswordLabel;
	JLabel confirmNewPasswordLabel;
	JLabel messageLabel;

	JTextField enterOldPasswordTextField;
	
	JPasswordField enterNewPasswordPasswordField;
	JPasswordField confirmNewPasswordPasswordField;
	
	JButton confirmButton;
	JButton backButton;
	
	Font arcade18 = Arcade_Font.getArcade18();
	Font arcade50 = Arcade_Font.getArcade50();

	Change_Password_Screen()
	{
		
		titleLabel = new JLabel("Change Password");
		titleLabel.setBounds(700-192, 120, 385, 50);
		titleLabel.setFont(arcade50);
		
		enterOldPasswordLabel = new JLabel("Enter Old Password");
		enterOldPasswordLabel.setBounds(550, 120+50+25, 200, 25);
		enterOldPasswordLabel.setFont(arcade18);
		
		enterOldPasswordTextField = new JTextField();
		enterOldPasswordTextField.setBounds(550, 120+50+25+25, 300, 25);
		enterOldPasswordTextField.setFont(arcade18);
		
		enterNewPasswordLabel = new JLabel("Enter New Password");
		enterNewPasswordLabel.setBounds(550, 120+50+25+25+25+25, 200, 25);
		enterNewPasswordLabel.setFont(arcade18);
		
		enterNewPasswordPasswordField = new JPasswordField();
		enterNewPasswordPasswordField.setBounds(550, 120+50+25+25+25+25+25, 300, 25);
		
		confirmNewPasswordLabel = new JLabel("Enter Confirm Password");
		confirmNewPasswordLabel.setBounds(550, 120+50+25+25+25+25+25+25+25, 200, 25);
		confirmNewPasswordLabel.setFont(arcade18);
		
		confirmNewPasswordPasswordField = new JPasswordField();
		confirmNewPasswordPasswordField.setBounds(550, 120+50+25+25+25+25+25+25+25+25, 300, 25);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(700+20, 120+50+25+25+25+25+25+25+25+25+25+25+25, 110, 35);
		confirmButton.setFont(arcade18);
		confirmButton.addActionListener(this);
		confirmButton.setFocusable(false);

		backButton = new JButton("Back");
		backButton.setBounds(700-150+20, 120+50+25+25+25+25+25+25+25+25+25+25+25, 110, 35);
		backButton.setFont(arcade18);
		backButton.addActionListener(this);
		backButton.setFocusable(false);
		
		messageLabel = new JLabel("");
		messageLabel.setBounds(700-130, 120+50+25+25+25+25+25+25+25+25+25+25+25+35+25, 260, 25);
		messageLabel.setFont(arcade18);
		
		this.setPreferredSize(new Dimension(1400, 700));
		this.setSize(getPreferredSize());
		this.setBackground(Color.red);
		this.setLayout(null);
		
		this.add(titleLabel);
		this.add(enterOldPasswordLabel);
		this.add(enterOldPasswordTextField);
		this.add(enterNewPasswordLabel);
		this.add(enterNewPasswordPasswordField);
		this.add(confirmNewPasswordLabel);
		this.add(confirmNewPasswordPasswordField);
		this.add(confirmButton);
		this.add(backButton);
		this.add(messageLabel);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == backButton)
		{
			System.out.println("back");
			Frame.i = -6;
		}
		
		if(e.getSource() == confirmButton)
		{
			String oldPass = enterOldPasswordTextField.getText();
			String currentPass = "";
			try {
				currentPass = Frame.db.getPassword();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			System.out.println("confirm");
			if(!currentPass.equals(oldPass)) {
				enterOldPasswordTextField.setText("");
				enterNewPasswordPasswordField.setText("");
				confirmNewPasswordPasswordField.setText("");
				messageLabel.setText("Incorrect old Password");
				messageLabel.setVisible(true);
				return ;
			}
			
			String newPass = String.valueOf(enterNewPasswordPasswordField.getPassword());
			String newConfPass = String.valueOf(confirmNewPasswordPasswordField.getPassword());
			if(newPass.equals(newConfPass))
			{
				try {
					Frame.db.setPassword(newConfPass);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				messageLabel.setText("Password Changed Successfully");
				enterOldPasswordTextField.setText("");
				enterNewPasswordPasswordField.setText("");
				confirmNewPasswordPasswordField.setText("");
				messageLabel.setVisible(true);
				return;
			}
			else {
				messageLabel.setText("incorrect conf password");
				messageLabel.setVisible(true);
			}
		}
	}

}
