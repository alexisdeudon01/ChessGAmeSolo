package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.http.client.ClientProtocolException;

import Singleton.Sock;
import Singleton.SoloGame;
import Singleton.User;
public class Login extends JFrame implements ActionListener{
	private JPanel mainPanel;
	private JLabel labelUserName;
	private JLabel labelPassword;
	private JLabel labelBadCredentials;
	private JLabel labelBadConnection;
	private JTextField userTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private JButton createAccountButton;
	private JLabel hyperlink;
	public Login() {
		//configurating object
		setSize(300, 220);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setTitle("Login");
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
		this.labelBadCredentials=new JLabel("Wrong username and/or password");
		this.labelBadCredentials.setBounds(10,150,250,25);
		this.labelBadCredentials.setForeground(Color.RED);
		this.labelBadCredentials.setVisible(false);
		this.labelBadConnection=new JLabel("Error while attempting to connect the server");
		this.labelBadConnection.setBounds(10,150,250,25);
		this.labelBadConnection.setForeground(Color.RED);
		this.labelBadConnection.setVisible(false);
		//Adding labels
		this.mainPanel.add(labelUserName);
		this.mainPanel.add(labelPassword);
		this.mainPanel.add(labelBadCredentials);
		this.mainPanel.add(labelBadConnection);
		//Creating text fields
		this.userTextField=new JTextField();
		this.userTextField.setBounds(100, 20, 165, 25);
		this.passwordTextField=new JPasswordField();
		this.passwordTextField.setBounds(100,50,165,25);
		//Ading text fields
		this.mainPanel.add(userTextField);
		this.mainPanel.add(passwordTextField);
		//Creating buttons
		this.loginButton=new JButton("Login");
		this.loginButton.setBounds(100, 80, 165, 25);
		this.loginButton.addActionListener(this);;
		this.createAccountButton=new JButton("Create Account");
		this.createAccountButton.setBounds(100,115,165,25);
		this.createAccountButton.addActionListener(this);
		//Adding buttons
		this.mainPanel.add(this.loginButton);
		this.mainPanel.add(this.createAccountButton);
		//Create hyperlink to play solo
		this.hyperlink=new JLabel("Play solo");
		this.hyperlink.setBounds(100, 140, 100, 20);
		hyperlink.setForeground(Color.BLUE.darker());
		hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	JFrame soloFrame=new JFrame();

		    	soloFrame.setSize(675, 565);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				soloFrame.setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		    	soloFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        SoloGame soloGame=SoloGame.getInstance();
		        soloFrame.add(soloGame);
		        soloFrame.setVisible(true);
		        setVisible(false);
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    }
		});
		//Adding hyperlink
		this.mainPanel.add(this.hyperlink);
	}
	
	public void showLoginPage() {
		setVisible(true);
	}
	
	public void hideLoginPage() {
		setVisible(false);
	}

	


	public void actionPerformed(ActionEvent e) {
		this.labelBadCredentials.setVisible(false);
		if(e.getSource()==this.loginButton) {
			Sock singletonSock=Sock.getInstance();
			try {
			if(singletonSock.login(this.userTextField.getText(), this.passwordTextField.getText())) {
				User user=User.getInstance(this.userTextField.getText(),this.passwordTextField.getText());
				user.setAvailable(true);
				FreePlayer freePlayer=new FreePlayer(user);
				setVisible(false);
				freePlayer.setVisible(true);
				try {
					Sock.getInstance().getAvailableGameplay();
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				this.labelBadCredentials.setVisible(true);
			}
			}
			catch(Exception e1) {
				System.out.println("Catch 2");
				this.labelBadConnection.setVisible(true);
			}
			
		}
		if(e.getSource()==this.createAccountButton) {
			CreateAccount createAccount=new CreateAccount();
			createAccount.showCreatingAccount();
		
		}

	}
}
