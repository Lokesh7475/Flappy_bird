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

public class Delete_Screen extends JPanel implements ActionListener{

	JLabel titleLabel;
	JLabel confirmTextLabel;
	JButton confirmButton;
	JButton backButton;
	
	Font arcade18 = Arcade_Font.getArcade18();
	Font arcade50 = Arcade_Font.getArcade50();

	Delete_Screen()
	{

		titleLabel = new JLabel(" Delete Account");
		titleLabel.setBounds(700-192, 120, 385, 50);
		titleLabel.setFont(arcade50);
		
		confirmTextLabel = new JLabel("Press Confirm to Delete the Account");
		confirmTextLabel.setBounds(550, 350-50, 400, 50);
		confirmTextLabel.setFont(arcade18);
		
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
		
		this.setPreferredSize(new Dimension(1400, 732));
		this.setSize(getPreferredSize());
		this.setLayout(null);
		this.setBackground(Color.yellow);
		
		this.add(titleLabel);
		this.add(confirmTextLabel);
		this.add(backButton);
		this.add(confirmButton);
		
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == backButton)
		{
			Frame.i = -8;
			System.out.println("back");
		}
		
		if(e.getSource() == confirmButton)
		{
//			System.out.println("confirm");
			Frame.i = 14;
			try {
				Frame.db.delete();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
