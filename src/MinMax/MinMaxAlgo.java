package MinMax;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.swing.Timer;

import ChessGameRepresentation.CareTaker;
import ChessGameRepresentation.ChessGameRepresentationSolo;
import Game.Board.Pieces.Cell;
import Game.Board.Pieces.Piece;
import Game.Board.Pieces.PieceSolo;
import Singleton.SoloGame;

public class MinMaxAlgo {
	private Timer timer;
	private Node treeAction;
	private int currentChessGameSaved;
	private int nbrSaved = 0;
	private int index;

	public MinMaxAlgo() {
		// this.treeAction=new TreeNode(p);
		this.currentChessGameSaved = 0;
		this.index = 0;

	}

	public int evaluateGame() {
		return 0;
	}

	public Node getTreeAction() {
		return treeAction;
	}

	public Action findOptimalValue(boolean playerMaximized, int depth, Node currentNode) {
		if (depth <= 0) {
			return currentNode.getVal();
		}
		if (playerMaximized) {

			Action maxEval = new Action("", 1, -1, -1, -1);
			maxEval.setWhiteScore(-1000000000);
			boolean find=false;
			for (Node child : currentNode.getChildren()) {
				int saveDepth = depth;
				depth--;
				Action tmp = findOptimalValue(false, depth, child);
				depth = saveDepth;
				if (tmp.getWhiteScore() > maxEval.getWhiteScore()) {

						maxEval = child.getVal().Clone();
						maxEval.setWhiteScore(tmp.getWhiteScore());
						find=true;
					

				}
			}
			if(currentNode.getChildren().size()<=0) {
				return currentNode.getVal();
				
			}
			if(!find) {
				Random rand=new Random();
				return currentNode.getChildren().get(rand.nextInt(currentNode.getChildren().size())).getVal();
			}
			return maxEval;
		} else {
			boolean find=false;
			Action minEval = new Action("", -1, -1, -1, -1);// +
			minEval.setWhiteScore(1000000000);
			for (Node child : currentNode.getChildren()) {
				int saveDepth = depth;
				depth--;
				Action tmp = findOptimalValue(true, depth, child);
				depth = saveDepth;
				if (tmp.getWhiteScore() < minEval.getWhiteScore()) {
				
						minEval = child.getVal().Clone();
						minEval.setWhiteScore(tmp.getWhiteScore());
						find=true;
					

				}
			}
			if(currentNode.getChildren().size()<=0) {
				return currentNode.getVal();
				
			}
			if(!find) {

				Random rand=new Random();
				return currentNode.getChildren().get(rand.nextInt(currentNode.getChildren().size())).getVal();
			}
			return minEval;
		}

	}

	public void buildTreeRecu(boolean whiteDS, int depth, Node rootNode) {
		if (depth <= 0) {
			return;
		}
		if (whiteDS == false) {
			for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
				// If we are the white we continue
				if (p.getWhite() || p.isEated()) {
					continue;
				}
				// Get allmoves
				LinkedList<Cell> results = new LinkedList<Cell>();
				LinkedList<Cell> allLegalMoves = p.getLegalMoves();
				LinkedList<Cell> allAttackMoves = p.getAttackMoves();
				results.addAll(allLegalMoves);
				results.addAll(allAttackMoves);
				// Save before move
				for (Cell move : results) {
					int saveX = p.getXp();
					int saveY = p.getYp();
					// Make move
					PieceSolo check = p.move(move.getX(), move.getY());
					//

					Node next = new Node(new Action(p.getUniqueid(), p.getXp(), p.getYp(), saveX, saveY));

					if (ChessGameRepresentationSolo.getInstance().CheckBlackIsChess()) {
						p.setXp(saveX);
						p.setYp(saveY);
						if (check != null) {
							check.setEated(false);
						}
						continue;
					}

					// Create new action
					rootNode.addChild(next);
					// Save representation
					// Call resursively
					int saveDepth = depth;
					depth--;

					buildTreeRecu(true, depth, next);
					depth = saveDepth;
					// depth=saveDepth;
					// buildTree(false, depth2);
					// Rollback everything
					// Decrement counter
					p.setXp(saveX);
					p.setYp(saveY);
					if (check != null) {
						check.setEated(false);
					}
				}
			}
		} else {
			for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
				// If we are the white we continue
				if (!p.getWhite() || p.isEated()) {
					continue;
				}
				// Get allmoves
				LinkedList<Cell> results = new LinkedList<Cell>();
				LinkedList<Cell> allLegalMoves = p.getLegalMoves();
				LinkedList<Cell> allAttackMoves = p.getAttackMoves();
				results.addAll(allLegalMoves);
				results.addAll(allAttackMoves);
				// Save before move
				// Save before move
				for (Cell move : results) {
					int saveX = p.getXp();
					int saveY = p.getYp();
					// Make move
					PieceSolo check = p.move(move.getX(), move.getY());
					// Create new action
					Node next = new Node(new Action(p.getUniqueid(), p.getXp(), p.getYp(), saveX, saveY));
					if (ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {
						p.setXp(saveX);
						p.setYp(saveY);
						if (check != null) {
							check.setEated(false);
						}
						continue;
					}
					// Create new action
					rootNode.addChild(next);
					// Save representation
					// Call resursively
					int saveDepth = depth;

					depth--;
					buildTreeRecu(false, depth, next);
					depth = saveDepth;

					// buildTree(false, depth2);
					// Rollback everything
					p.setXp(saveX);
					p.setYp(saveY);
					if (check != null) {
						check.setEated(false);
					}

				}
			}
		}
	}

}
