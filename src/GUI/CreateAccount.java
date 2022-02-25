package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.http.client.ClientProtocolException;

import Singleton.Sock;
import Singleton.User;

public class CreateAccount extends JFrame implements ActionListener {
	private JPanel mainPanel;
	private JLabel labelUserName;
	private JLabel labelPassword;
	private JLabel labelRepeatPassword;
	private JLabel labelPasswordNotMatch;
	private JLabel labelUsernameAlreadyTaken;
	private JLabel labelSuccessCreation;
	private JTextField userTextField;
	private JPasswordField passwordTextField;
	private JPasswordField repeatPasswordTextField;
	private JButton createAccountButton;

	public CreateAccount() {
		//Configurate
		setSize(370, 260);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setTitle("Creating Account");
		//Create JPanel and configuring
		this.mainPanel=new JPanel();
		//Adding panel to frame
		this.add(this.mainPanel);
		//Configuration of the panel
		this.mainPanel.setLayout(null);
		//Creating labels
		this.labelUserName=new JLabel("Username");
		this.labelUserName.setBounds(10, 20, 80, 25);
		this.labelPassword=new JLabel("Password");
		this.labelPassword.setBounds(10, 50, 80, 25);
		this.labelRepeatPassword=new JLabel("Repeat Password");
		this.labelRepeatPassword.setBounds(10,75,120,25);
		this.labelPasswordNotMatch=new JLabel("Password doesn't match");
		this.labelPasswordNotMatch.setBounds(100, 140, 165, 25);
		this.labelPasswordNotMatch.setForeground(Color.RED);
		this.labelPasswordNotMatch.setVisible(false);
		this.labelUsernameAlreadyTaken=new JLabel("Username already took, please take another one");
		this.labelUsernameAlreadyTaken.setBounds(100, 140, 265, 25);
		this.labelUsernameAlreadyTaken.setForeground(Color.RED);
		this.labelUsernameAlreadyTaken.setVisible(false);
		this.labelSuccessCreation=new JLabel();
		this.labelSuccessCreation.setBounds(100, 140, 265, 25);
		this.labelSuccessCreation.setForeground(new Color(0,153,0));
		this.labelSuccessCreation.setVisible(false);
		//Adding labels
		this.mainPanel.add(labelUserName);
		this.mainPanel.add(labelPassword);
		this.mainPanel.add(labelRepeatPassword);
		this.mainPanel.add(labelPasswordNotMatch);
		this.mainPanel.add(labelUsernameAlreadyTaken);
		this.mainPanel.add(labelSuccessCreation);
		//Creating text fields
		this.userTextField=new JTextField();
		this.userTextField.setBounds(140, 20, 165, 25);
		this.passwordTextField=new JPasswordField();
		this.passwordTextField.setBounds(140,50,165,25);
		this.repeatPasswordTextField=new JPasswordField();
		this.repeatPasswordTextField.setBounds(140, 80, 165, 25);
		//Ading text fields
		this.mainPanel.add(userTextField);
		this.mainPanel.add(passwordTextField);
		this.mainPanel.add(repeatPasswordTextField);
		//Creating buttons
		this.createAccountButton=new JButton("Create Account");
		this.createAccountButton.setBounds(140,115,165,25);
		this.createAccountButton.addActionListener(this);
		//Adding buttons
		this.mainPanel.add(this.createAccountButton);
		
	}

	public void showCreatingAccount() {
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		this.labelPasswordNotMatch.setVisible(false);
		this.labelUsernameAlreadyTaken.setVisible(false);
		this.labelSuccessCreation.setVisible(false);
		if (!this.passwordTextField.getText().equals(this.repeatPasswordTextField.getText())) {
			this.labelPasswordNotMatch.setVisible(true);
		} else {
			User user = User.getInstance(this.userTextField.getText(), this.passwordTextField.getText());
			Sock sock = Sock.getInstance();
			try {
				if (!sock.createUser(user)) {
					this.labelUsernameAlreadyTaken.setVisible(true);
					
				} else {
					this.labelSuccessCreation.setText("The user "+user.getUsername()+" has been successfully created ");
					this.labelSuccessCreation.setVisible(true);
				}
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
