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
import javax.swing.JTextField;

public class Change_Username_Screen extends JPanel implements ActionListener {

	JLabel titleLabel;
	JLabel enterNewNameLabel;
	JLabel messageLabel;
	
	JTextField enterNewNameTextField;
	
	JButton confirmButton;
	JButton backButton;
	
	Font arcade18 = Arcade_Font.getArcade18();
	Font arcade50 = Arcade_Font.getArcade50();

	
	Change_Username_Screen()
	{
		titleLabel = new JLabel("Change Username");
		titleLabel.setBounds(700-192, 120, 385, 50);
		titleLabel.setFont(arcade50);
		
		enterNewNameLabel = new JLabel("Enter name");
		enterNewNameLabel.setBounds(550, 120+50+25+100, 300, 25);
		enterNewNameLabel.setFont(arcade18);
		
		enterNewNameTextField = new JTextField();
		enterNewNameTextField.setBounds(550, 120+50+25+25+100, 300, 25);
		enterNewNameTextField.setFont(arcade18);
		
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
		this.setBackground(Color.gray);
		this.setLayout(null);
		
		this.add(titleLabel);
		this.add(enterNewNameLabel);
		this.add(enterNewNameTextField);
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
			Frame.i = -7;
			System.out.println("back");
		}
		
		if(e.getSource() == confirmButton)
		{
//			System.out.println("confirm");
			String newName = enterNewNameTextField.getText();
			if(newName == null || newName.equals(""))
			{
				messageLabel.setText("Not a valid name");
				messageLabel.setVisible(true);
				return ;
			}
			try {
				if(Frame.db.isNameAvailable(newName))
				{
					Frame.db.setName(newName);
					messageLabel.setText("Username Changed Successfully");
					enterNewNameTextField.setText("");
					messageLabel.setVisible(true);
					return ;
				}
				else
				{
					messageLabel.setText("username taken");
					messageLabel.setVisible(true);
					return ;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
