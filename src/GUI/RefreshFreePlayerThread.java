package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.http.client.ClientProtocolException;

import Singleton.Game;
import Singleton.Sock;
import Singleton.User;


public class RefreshFreePlayerThread implements Runnable{
	private DefaultTableModel model;
	private JTable mainTable;
	private JFrame mainFrame;
	private Boolean running=true;
	//To avoid multiple game creation, this variable has been put as lock
	private Boolean gameAlreadycreated=false;
	private String playerAsking;
	public RefreshFreePlayerThread(DefaultTableModel model,JTable mainTable, JFrame mainFrame, String playerAsking) {
		this.model=model;
		this.mainTable=mainTable;
		this.mainFrame=mainFrame;
		this.playerAsking=playerAsking;
	}
	public void addListenner() {
		mainTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = mainTable.rowAtPoint(evt.getPoint());
		        int col = mainTable.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0 && gameAlreadycreated==false) {// select a column
		        	/////////////////////////////////////////////////
		        	////////////CREATE A NEW GAME////////////////////
		        	/////////////////////////////////////////////////
		        	if(mainTable.getValueAt(row, col).equals("Click here to create a new game")) {
		        		mainFrame.setVisible(false);
		        		gameAlreadycreated=true;
		        		System.out.println("You asked to create a new game");
		        		int id=Sock.getInstance().createGame();
		        		System.out.println("Game with id "+id+" has been created");
		        		//Stopping thread
		        		running=false; 
		        		//Create Gameboard
		        		Game game=Game.getInstance(id,true);
		        		//Hide choose game frame

		        		game.setPlayerAsking(playerAsking);

		        	}
		        	else {
			        	/////////////////////////////////////////////////
			        	////////////JOIN A GAME//////////////////////////
			        	/////////////////////////////////////////////////
		        		try {
		        			mainFrame.setVisible(false);
		        			String string= mainTable.getValueAt(row, col).toString();
		        			int gameid = Integer.parseInt(string);
							String opponent=Sock.getInstance().join(gameid);
							Game game=Game.getInstance(gameid,false);
							game.setPlayerAsking(opponent);
							game.setPlayerRequested(User.getInstance().getUsername());
							game.setTitle(game.getTitle()+" "+game.getPlayerAsking()+ " vs "+game.getPlayerRequested());
							running=false;
							
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		//JOptionPane.showMessageDialog(null, mainTable.getValueAt(row, col)); // get the value of a row and column.
		        	}
		        }
		    }
		});
		
	}
	public void killThreadAndOpenGame() {
		
	}

	public DefaultTableModel getModel() {
		return model;
	}
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public JTable getMainTable() {
		return mainTable;
	}
	public void setMainTable(JTable mainTable) {
		this.mainTable = mainTable;
	}
	public JFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public Boolean getRunning() {
		return running;
	}
	public void setRunning(Boolean running) {
		this.running = running;
	}
	public Boolean getGameAlreadycreated() {
		return gameAlreadycreated;
	}
	public void setGameAlreadycreated(Boolean gameAlreadycreated) {
		this.gameAlreadycreated = gameAlreadycreated;
	}
	public String getPlayerAsking() {
		return playerAsking;
	}
	public void setPlayerAsking(String playerAsking) {
		this.playerAsking = playerAsking;
	}
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(2000);
				String[] headers= {"Choose a game"};
			
					this.model.setDataVector(Sock.getInstance().getAvailableGameplay(), headers);
					model.addRow(new Object[] {("Click here to create a new game")});
					addListenner();

				
			} catch (Exception e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		}

		
	}

}
