package Game.Board;

import java.util.LinkedList;

import Game.Board.Pieces.Piece;

public class ViewChessBoard {
	private Boolean playerIsWhite;
	private int gameid;
	private LinkedList<Piece> chessBoard;
	public ViewChessBoard(Boolean playerIsWhite, int gameid, LinkedList<Piece> chessBoard) {
		super();
		this.playerIsWhite = playerIsWhite;
		this.gameid = gameid;
		this.chessBoard = chessBoard;
	}
	
	public ViewChessBoard() {
		
	}

	public Boolean getPlayerIsWhite() {
		return playerIsWhite;
	}

	public void setPlayerIsWhite(Boolean playerIsWhite) {
		this.playerIsWhite = playerIsWhite;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public LinkedList<Piece> getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(LinkedList<Piece> chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	
}
