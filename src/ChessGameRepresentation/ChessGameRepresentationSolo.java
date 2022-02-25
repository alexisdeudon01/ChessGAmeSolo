package ChessGameRepresentation;

import java.util.LinkedList;


import Game.Board.Pieces.Cell;
import Game.Board.Pieces.Piece;
import Game.Board.Pieces.PieceSolo;
import Singleton.SoloGame;

public class ChessGameRepresentationSolo {
	private static ChessGameRepresentationSolo single_instance;
	// Contains list of white pieces
	private LinkedList<PieceSolo> allPieces;
	// Containing list of cells selected by the user
	private LinkedList<Cell> cellsSelected;
	// Containing list of cells with possibe attack
	private LinkedList<Cell> attackCells;
	// Cell that contain the piece selected
	private Cell pieceSelected;
	// Boolean that is true if user has selected a cell
	private Boolean isPieceSlected = false;
	public boolean CheckBlackIsChess() {
		
		//Iter on all white pieces
		for (PieceSolo p: this.allPieces) {
			//If piece are white
			if(p.getWhite() && !p.isEated()) {
				LinkedList<Cell> attack=p.getAttackMoves();
				for (Cell c: attack) {
					if(GetPieceXY(c.getX(), c.getY()).getName().equals("king")) {
						return true;
						
					}
				}
			}
		}
		//Check all attack moves
		return false;
	}
	public boolean CheckWhiteIsChess() {
		
		//Iter on all white pieces
		for (PieceSolo p: this.allPieces) {
			//If piece are black and one of them puts the king in chess
			if(!p.getWhite() && !p.isEated()) {
				LinkedList<Cell> attack=p.getAttackMoves();
				for (Cell c: attack) {
					if(GetPieceXY(c.getX(), c.getY()).getName().equals("king")) {
						return true;
						
					}
				}
			}
		}
		//Check all attack moves
		return false;
	}

	
	private ChessGameRepresentationSolo() {
		this.allPieces = new LinkedList<PieceSolo>();
		this.cellsSelected = new LinkedList<Cell>();
		this.attackCells = new LinkedList<Cell>();
		// Create representation
		PieceSolo brook = new PieceSolo(0, 0, false, "rook");
		this.allPieces.add(brook);
		PieceSolo bkinght = new PieceSolo(1, 0, false, "knight");
		this.allPieces.add(bkinght);
		PieceSolo bbishop = new PieceSolo(2, 0, false, "bishop");
		this.allPieces.add(bbishop);
		PieceSolo bqueen = new PieceSolo(3, 0, false, "queen");
		this.allPieces.add(bqueen);
		PieceSolo bking = new PieceSolo(4, 0, false, "king");
		this.allPieces.add(bking);
		PieceSolo bbishop2 = new PieceSolo(5, 0, false, "bishop");
		this.allPieces.add(bbishop2);
		PieceSolo bkight2 = new PieceSolo(6, 0, false, "knight");
		this.allPieces.add(bkight2);
		PieceSolo brook2 = new PieceSolo(7, 0, false, "rook");
		this.allPieces.add(brook2);
		PieceSolo bpawn1 = new PieceSolo(1, 1, false, "pawn");
		this.allPieces.add(bpawn1);
		PieceSolo bpawn2 = new PieceSolo(2, 1, false, "pawn");
		this.allPieces.add(bpawn2);
		PieceSolo bpawn3 = new PieceSolo(3, 1, false, "pawn");
		this.allPieces.add(bpawn3);
		PieceSolo bpawn4 = new PieceSolo(4, 1, false, "pawn");
		this.allPieces.add(bpawn4);
		PieceSolo bpawn5 = new PieceSolo(5, 1, false, "pawn");
		this.allPieces.add(bpawn5);
		PieceSolo bpawn6 = new PieceSolo(6, 1, false, "pawn");
		this.allPieces.add(bpawn6);
		PieceSolo bpawn7 = new PieceSolo(7, 1, false, "pawn");
		this.allPieces.add(bpawn7);
		PieceSolo bpawn8 = new PieceSolo(0, 1, false, "pawn");
		this.allPieces.add(bpawn8);

		PieceSolo wrook = new PieceSolo(0, 7, true, "rook");
		this.allPieces.add(wrook);
		PieceSolo wkinght = new PieceSolo(1, 7, true, "knight");
		this.allPieces.add(wkinght);
		PieceSolo wbishop = new PieceSolo(2, 7, true, "bishop");
		this.allPieces.add(wbishop);
		PieceSolo wqueen = new PieceSolo(3, 7, true, "queen");
		this.allPieces.add(wqueen);
		PieceSolo wking = new PieceSolo(4, 7, true, "king");
		this.allPieces.add(wking);
		PieceSolo wbishop2 = new PieceSolo(5, 7, true, "bishop");
		this.allPieces.add(wbishop2);
		PieceSolo wkight2 = new PieceSolo(6, 7, true, "knight");
		this.allPieces.add(wkight2);
		PieceSolo wrook2 = new PieceSolo(7, 7, true, "rook");
		this.allPieces.add(wrook2);
		PieceSolo wpawn1 = new PieceSolo(1, 6, true, "pawn");
		this.allPieces.add(wpawn1);
		PieceSolo wpawn2 = new PieceSolo(2, 6, true, "pawn");
		this.allPieces.add(wpawn2);
		PieceSolo wpawn3 = new PieceSolo(3, 6, true, "pawn");
		this.allPieces.add(wpawn3);
		PieceSolo wpawn4 = new PieceSolo(4, 6, true, "pawn");
		this.allPieces.add(wpawn4);
		PieceSolo wpawn5 = new PieceSolo(5, 6, true, "pawn");
		this.allPieces.add(wpawn5);
		PieceSolo wpawn6 = new PieceSolo(6, 6, true, "pawn");
		this.allPieces.add(wpawn6);
		PieceSolo wpawn7 = new PieceSolo(7, 6, true, "pawn");
		this.allPieces.add(wpawn7);
		PieceSolo wpawn8 = new PieceSolo(0, 6, true, "pawn");
		this.allPieces.add(wpawn8);
	}

	public static ChessGameRepresentationSolo getInstance() {
		if (single_instance == null)
			single_instance = new ChessGameRepresentationSolo();

		return single_instance;
	}

	public static void recreateGame() {
		single_instance=new ChessGameRepresentationSolo();
	}
	public LinkedList<Cell> getCellsSelected() {
		return cellsSelected;
	}

	public void setCellsSelected(LinkedList<Cell> cellsSelected) {
		this.cellsSelected = cellsSelected;
	}

	public LinkedList<Cell> getAttackCells() {
		return attackCells;
	}

	public void setAttackCells(LinkedList<Cell> attackCells) {
		this.attackCells = attackCells;
	}

	public Cell getPieceSelected() {
		return pieceSelected;
	}

	public void setPieceSelected(Cell pieceSelected) {
		this.pieceSelected = pieceSelected;
	}

	public Boolean getIsPieceSlected() {
		return isPieceSlected;
	}

	public void setIsPieceSlected(Boolean isPieceSlected) {
		this.isPieceSlected = isPieceSlected;
	}

	public PieceSolo GetPieceXY(int x, int y) {
		for (PieceSolo p : this.allPieces) {
			if (p.getXp() == x && p.getYp() == y && !p.isEated()) {
				return p;
			}
		}
		return null;
	}



	public boolean checkIfPieceOnCell(int x, int y) {
		PieceSolo p1 = ChessGameRepresentationSolo.getInstance().GetPieceXY(x, y);
		if (p1 != null) {
			if(p1.isEated()) {
				return false;
			}
			return true;
		}
		return false;
	}

	public void deletePiece(int x, int y) {
		PieceSolo p1 = GetPieceXY(x, y);
		if (p1 != null) {
			p1.setEated(true);
			SoloGame.getInstance().repaint();
			return;
		}
	}
	public int evaluateWhitePlayer() {
		int score=0;
		for(PieceSolo p: allPieces) {
			if(!p.getWhite() || p.isEated()) { 
				continue;
			}
			if (p.getName().equalsIgnoreCase("king")) {
				score = score + 1000;
			}
			if (p.getName().equalsIgnoreCase("queen")) {
				score = score + 10;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				score = score + 4;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				score = score + 5;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				score = score + 3;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				score = score + 1;
			}
		}
		return score;
	}
	public int evaluateBlackPlayer() {
		int score=0;
		for(PieceSolo p: allPieces) {
			if(p.getWhite() || p.isEated()) { 
				continue;
			}
			if (p.getName().equalsIgnoreCase("king")) {
				score = score + 1000;
			}
			if (p.getName().equalsIgnoreCase("queen")) {
				score = score + 10;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				score = score + 4;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				score = score + 5;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				score = score + 3;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				score = score + 1;
			}
		}
		return score;
	}
	public int evaluate() {
		int whiteScore = 0;
		if(SoloGame.getInstance().checkIfBlackIsChessMate()) {
			whiteScore=whiteScore+100000;
		}
		if(CheckWhiteIsChess()) {
			whiteScore=whiteScore-50;
		}
		for (PieceSolo p : allPieces) {
			if(!p.getWhite() || p.isEated()) {
				continue;
			}
			/*if (p.getName().equalsIgnoreCase("king")) {
				whiteScore = whiteScore + 1000;
			}*/
			if (p.getName().equalsIgnoreCase("queen")) {
				whiteScore = whiteScore + 9;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				whiteScore = whiteScore + 4;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				whiteScore = whiteScore + 4;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				whiteScore = whiteScore + 5;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				whiteScore = whiteScore + 1;
			}

		}
		int blackScore=0;
		if(SoloGame.getInstance().checkIfWhiteIsChessMate()) {
			blackScore=blackScore+100000;
		}
		if(CheckBlackIsChess()) {
			blackScore=blackScore-50;
		}
		for(PieceSolo p: allPieces) {
			if(p.getWhite() || p.isEated()) { 
				continue;
			}
			/*if (p.getName().equalsIgnoreCase("king")) {
				blackScore = blackScore + 1000;
			}*/
			if (p.getName().equalsIgnoreCase("queen")) {
				blackScore = blackScore + 9;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				blackScore = blackScore + 4;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				blackScore = blackScore + 4;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				blackScore = blackScore + 5;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				blackScore = blackScore + 1;
			}
		}
		return whiteScore-blackScore;
	}

	public LinkedList<PieceSolo> getAllPieces() {
		return allPieces;
	}

	public void setAllPieces(LinkedList<PieceSolo> allPieces) {
		this.allPieces = allPieces;
		SoloGame.getInstance().repaint();
	}
	public PieceSolo GetById(String id) {
		for (PieceSolo p: allPieces) {
			if(p.getUniqueid().equals(id)) {
				return p;
			}
		}
		return null;
	}
	public LinkedList<PieceSolo> getCloneRepresentation(){
		LinkedList<PieceSolo> result = new LinkedList<PieceSolo>();
		for (PieceSolo p: allPieces) {
			PieceSolo tmp=p.Clone();
			result.add(tmp);
		}
		return result;
	}
}
