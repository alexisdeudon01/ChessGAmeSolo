package ChessGameRepresentation;

import java.util.ArrayList;
import java.util.LinkedList;

import Game.Board.Pieces.PieceSolo;

public class CareTaker {
	private static CareTaker single_instance;
	private LinkedList<ChessGameRepresentationSolo> savedChessGameRepresentation = new LinkedList<ChessGameRepresentationSolo>();

	public void clear() {
		this.savedChessGameRepresentation.clear();
	}
	public int addChessRepresentation(ChessGameRepresentationSolo chessGameRepresentationSolo) {
		savedChessGameRepresentation.add(chessGameRepresentationSolo);
		return savedChessGameRepresentation.indexOf(chessGameRepresentationSolo);
	}
	public ChessGameRepresentationSolo getChessGameRepresentationSolo(int index) {
		return savedChessGameRepresentation.get(index);
	}
	private CareTaker() {

	}

	// static method to create instance of Singleton class
	public static CareTaker getInstance() {
		if (single_instance == null)
			single_instance = new CareTaker();

		return single_instance;
	}
}
