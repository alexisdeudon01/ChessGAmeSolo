package Game.Chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Singleton.Game;
import Singleton.Sock;

public class ChatGamePanel extends JPanel implements ActionListener {
	private JFrame mainFrame;
	private JTextArea mainTextArea;
	private JTextField newMessage;
	private JButton submitButton;
	private BoxLayout chatArea;
	private ChatThread chatThread;
	private Thread runningChatThread;
	private int gameid;

	public ChatGamePanel(JFrame mainFrame, int gameid) {
		super();
		this.gameid = gameid;
		this.mainFrame = mainFrame;
		// Configure panel
		setSize((int) (this.mainFrame.getWidth() * 0.25), this.mainFrame.getHeight());
		// create text area
		this.mainTextArea = new JTextArea();
		this.mainTextArea.setLineWrap(true); 
		this.mainTextArea.setMaximumSize(new Dimension(400, 100));
		this.chatArea = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(chatArea);
		Border border = BorderFactory.createBevelBorder(1);
		this.mainTextArea.setBorder(border);
		this.mainTextArea.setEditable(false);
		Font font = new Font("Segoe Script", Font.BOLD, 14);
		mainTextArea.setFont(font);
		mainTextArea.setForeground(Color.BLUE);
		// Creating jscrollbar
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setViewportView(this.mainTextArea);
		add(scrollBar);
		// creating text field
		this.newMessage = new JTextField();
		newMessage.setMaximumSize(new Dimension(400, 400));
		this.submitButton = new JButton("Submit");
		this.submitButton.setMaximumSize(new Dimension(400, 20));
		this.submitButton.addActionListener(this);
		// Adding components
		add(this.newMessage);
		add(this.submitButton);
		// Set border
		mainTextArea.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 208), 2));
		newMessage.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 208), 2));
		// starting thread
		System.out.println("Starting thread chat");

		//startThread();
	}

	public void actionPerformed(ActionEvent e) {
		// Player created the game
		if (Game.getInstance().isGameCreation()) {
			// send new message
			ChatEntity msg = new ChatEntity(Game.getInstance().getGameid(), this.newMessage.getText(),
					Game.getInstance().getPlayerAsking());
			Sock.getInstance().sendMessageChat(msg);
		}
		// player join the game
		else {
			// send new message
			ChatEntity msg = new ChatEntity(Game.getInstance().getGameid(), this.newMessage.getText(),
					Game.getInstance().getPlayerRequested());
			Sock.getInstance().sendMessageChat(msg);
		}

		// Erase message
		this.newMessage.setText("");

	}

	public JTextArea getMainTextArea() {
		return mainTextArea;
	}

	public void setMainTextArea(JTextArea mainTextArea) {
		this.mainTextArea = mainTextArea;
	}

	public JTextField getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(JTextField newMessage) {
		this.newMessage = newMessage;
	}

	public void startThread() {
		this.chatThread = new ChatThread(this, gameid);
		this.runningChatThread = new Thread(this.chatThread);
		this.runningChatThread.start();
	}

	public void interruptThread() {
		this.chatThread.terminate();
	}
	


}
