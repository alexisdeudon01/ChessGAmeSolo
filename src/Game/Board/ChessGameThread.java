package Game.Board;

import Singleton.Game;
import Singleton.Sock;
public class ChessGameThread implements Runnable {
	private ChessGamePanel chessGamePanel;
	private Boolean running = true;
	
	public ChessGameThread(ChessGamePanel chessGamePanel) {
		super();
		this.chessGamePanel = chessGamePanel;
	}

	public void terminate() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			//Call sock whith block request
			//Update list of pieces ps in ChessGamePanel
			this.chessGamePanel.setPs(Sock.getInstance().getNewViewChessBoard(this.chessGamePanel.getMainFrame().getGameid(),this.chessGamePanel.getMainFrame().isGameCreation()));
			//Give the hand to me
			this.chessGamePanel.setMyTurn(true);
			//Repaint Board
			this.chessGamePanel.repaint();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("new");
		}
	}

}
