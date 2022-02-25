package Singleton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.foundPlayerThread;
import Game.Board.ChessGamePanel;
import Game.Board.Pieces.Piece;
import Game.Chat.ChatGamePanel;

public class Game extends JFrame implements ActionListener {
	private Thread foundPlayer;
	private ChessGamePanel chessPanel;
	private ChatGamePanel chatPanel;
	private BorderLayout mainBorderLayout;
	private String playerAsking;
	private String playerRequested;
	private int gameid;
	private foundPlayerThread foundThread;
	private Thread foundThreadRunningThread;
	private static Game single_instance = null; 
	// If true you are the white
	private boolean gameCreation = true;


	private Game(int id, boolean gameCreation) {
		super();
		
		System.out.println("Singleton game has been created");
		this.gameCreation = gameCreation;

		this.gameid = id;
		// configurating layout
		this.mainBorderLayout = new BorderLayout();
		// Configure frame
		setSize(675, 565);
		setLayout(mainBorderLayout);

		// setUndecorated(true);
		setTitle("Chess Game - ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creating chess panel
		this.chessPanel = new ChessGamePanel(this);
		add(chessPanel, BorderLayout.CENTER);
		// creating chat panel
		this.chatPanel = new ChatGamePanel(this, this.gameid);

		add(chatPanel, BorderLayout.EAST);
		if (this.gameCreation) {
			// start thread
			startThread();
		}
		// Render frame
		setVisible(true);
		// Disable resizing
		setResizable(false);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               Sock.getInstance().exitGamePlay();
            }

        });
	}

	// Return game when we knowns that object has been already created
	public static Game getInstance() {
		return single_instance;
	}

	public static Game getInstance(int id, boolean gameCreation) {
		if (single_instance == null)
			single_instance = new Game(id, gameCreation);

		return single_instance;
	}

	public void startThread() {
		this.foundThread = new foundPlayerThread(this);
		foundThreadRunningThread = new Thread(this.foundThread);
		foundThreadRunningThread.start();
	}

	public void stopThread() {
		this.foundThread.interrupt();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public Thread getFoundPlayer() {
		return foundPlayer;
	}

	public void setFoundPlayer(Thread foundPlayer) {
		this.foundPlayer = foundPlayer;
	}

	public ChessGamePanel getChessPanel() {
		return chessPanel;
	}

	public void setChessPanel(ChessGamePanel chessPanel) {
		this.chessPanel = chessPanel;
	}

	public ChatGamePanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatGamePanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	public BorderLayout getMainBorderLayout() {
		return mainBorderLayout;
	}

	public void setMainBorderLayout(BorderLayout mainBorderLayout) {
		this.mainBorderLayout = mainBorderLayout;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getPlayerAsking() {
		return playerAsking;
	}

	public void setPlayerAsking(String playerAsking) {
		this.playerAsking = playerAsking;
	}

	public String getPlayerRequested() {
		return playerRequested;
	}

	public void setPlayerRequested(String playerRequested) {
		this.playerRequested = playerRequested;
	}

	public boolean isGameCreation() {
		return gameCreation;
	}

}
