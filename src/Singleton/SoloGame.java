package Singleton;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.http.impl.BHttpConnectionBase;

import MinMax.Node;

import ChessGameRepresentation.CareTaker;
import ChessGameRepresentation.ChessGameRepresentationSolo;
import GUI.WinningPopUp;
import Game.Board.ChessGamePanel;
import Game.Board.Pieces.Cell;
import Game.Board.Pieces.Piece;
import Game.Board.Pieces.PieceSolo;
import MinMax.Action;
import MinMax.MinMaxAlgo;

public class SoloGame extends JPanel implements ActionListener {
	// Image array containing piece
	private Image imgs[];
	// True if it is your turn
	private Boolean myTurn;
	// Single instance for singleton
	private static SoloGame single_instance;
	private MinMaxAlgo algo;
	//will be true if white win

	public static SoloGame getInstance() {
		if (single_instance == null)
			single_instance = new SoloGame();

		return single_instance;
	}
	public static void deleteInstance() {
		single_instance = new SoloGame();
	}
	public boolean checkIfBlackIsChessMate() {
		// If white is not chess return false
		if (!ChessGameRepresentationSolo.getInstance().CheckBlackIsChess()) {
			return false;
		}
		// Otherwise check if one possible move can get him of this situation
		else {
			for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
				// If black
				if (!p.getWhite() && !p.isEated()) {
					LinkedList<Cell> movesCell = p.getLegalMoves();
					LinkedList<Cell> attackCell = p.getAttackMoves();
					for (Cell move : movesCell) {
						// save coordinate
						int saveX = p.getXp();
						int saveY = p.getYp();
						// try the move
						p.move(move.getX(), move.getY());
						// Check if white still chess mate
						if (!ChessGameRepresentationSolo.getInstance().CheckBlackIsChess()) {
							// Undo change
							p.move(saveX, saveY);
							return false;
						}
						// Undo change
						p.move(saveX, saveY);
					}
					for (Cell move : attackCell) {
						// save coordinate
						int saveX = p.getXp();
						int saveY = p.getYp();
						// Save piece
						PieceSolo eatPiece = ChessGameRepresentationSolo.getInstance().GetPieceXY(move.getX(),
								move.getY());
						// try the move
						p.move(move.getX(), move.getY());
						// Check if white still chess mate
						if (!ChessGameRepresentationSolo.getInstance().CheckBlackIsChess()) {
							// Undo change
							p.move(saveX, saveY);
							// Uneat piece
							eatPiece.setEated(false);
							return false;
						}
						// Undo change
						p.move(saveX, saveY);
						// Uneat piece
						eatPiece.setEated(false);
					}
				}
			}
			return true;
		}
	}

	public boolean checkIfWhiteIsChessMate() {
		// If white is not chess return false
		if (!ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {
			return false;
		}
		// Otherwise check if one possible move can get him of this situation
		else {
			for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
				// If white
				if (p.getWhite() && !p.isEated()) {
					LinkedList<Cell> movesCell = p.getLegalMoves();
					LinkedList<Cell> attackCell = p.getAttackMoves();
					for (Cell move : movesCell) {
						// save coordinate
						int saveX = p.getXp();
						int saveY = p.getYp();
						// try the move
						p.move(move.getX(), move.getY());
						// Check if white still chess mate
						if (!ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {
							// Undo change
							p.move(saveX, saveY);
							return false;
						}
						// Undo change
						p.move(saveX, saveY);
					}
					for (Cell move : attackCell) {
						// save coordinate
						int saveX = p.getXp();
						int saveY = p.getYp();
						// Save piece
						PieceSolo eatPiece = ChessGameRepresentationSolo.getInstance().GetPieceXY(move.getX(),
								move.getY());
						// try the move
						p.move(move.getX(), move.getY());
						// Check if white still chess mate
						if (!ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {
							// Undo change
							p.move(saveX, saveY);
							// Uneat piece
							eatPiece.setEated(false);
							return false;
						}
						// Undo change
						p.move(saveX, saveY);
						// Uneat piece
						eatPiece.setEated(false);
					}
				}
			}
			return true;
		}
	}

	private int min = 10000;
	private int max = -20000;

	private SoloGame() {
		super();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		// configurating object
		setSize(800, 565);

		// Configure panel
		setBackground(Color.red);
		addMouseListener(new Adapter(this));
		BufferedImage all = null;
		// Loading image
		try {
			all = ImageIO.read(new File("C:\\chess.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Creating 12 image, each for one piece
		this.imgs = new Image[12];
		int ind = 0;
		// Load every single image
		for (int y = 0; y < 400; y += 200) {
			for (int x = 0; x < 1200; x += 200) {
				imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
				ind++;
			}
		}

		this.myTurn = true;
		setVisible(true);

		// Testing
		BorderLayout l = new BorderLayout();
		setLayout(l);
		algo = new MinMaxAlgo();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////GRAPHIC FUNCTIONNALITIES///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void paint(Graphics g) {
		boolean white = true;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				boolean find = false;
				for (Cell c : ChessGameRepresentationSolo.getInstance().getCellsSelected()) {
					if (c.getX() == x && c.getY() == y) {
						g.setColor(c.getColor());
						find = true;
					}
				}

				for (Cell c : ChessGameRepresentationSolo.getInstance().getAttackCells()) {
					if (c.getX() == x && c.getY() == y) {
						g.setColor(c.getColor());
						find = true;
					}
				}
				if (!find) {
					if (white) {
						g.setColor(new Color(235, 235, 208));
					} else {
						g.setColor(new Color(119, 148, 85));

					}
				}
				g.fillRect(x * 64, y * 64, 64, 64);
				white = !white;
			}
			white = !white;
		}
		if (ChessGameRepresentationSolo.getInstance().getIsPieceSlected()) {
			g.setColor(ChessGameRepresentationSolo.getInstance().getPieceSelected().getColor());
			g.fillRect(ChessGameRepresentationSolo.getInstance().getPieceSelected().getX() * 64,
					ChessGameRepresentationSolo.getInstance().getPieceSelected().getY() * 64, 64, 64);
		}
		// Color white pieces
		for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
			if (!p.getWhite() || p.isEated()) {
				continue;
			}
			int ind = 0;
			if (p.getName().equalsIgnoreCase("king")) {
				ind = 0;
			}
			if (p.getName().equalsIgnoreCase("queen")) {
				ind = 1;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				ind = 2;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				ind = 3;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				ind = 4;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				ind = 5;
			}
			g.drawImage(imgs[ind], p.getXp() * 64, p.getYp() * 64, this);

		}
		// Color black pieces
		for (PieceSolo p : ChessGameRepresentationSolo.getInstance().getAllPieces()) {
			if (p.getWhite() || p.isEated()) {
				continue;
			}
			int ind = 6;
			if (p.getName().equalsIgnoreCase("king")) {
				ind = 0;
			}
			if (p.getName().equalsIgnoreCase("queen")) {
				ind = 1;
			}
			if (p.getName().equalsIgnoreCase("bishop")) {
				ind = 2;
			}
			if (p.getName().equalsIgnoreCase("knight")) {
				ind = 3;
			}
			if (p.getName().equalsIgnoreCase("rook")) {
				ind = 4;
			}
			if (p.getName().equalsIgnoreCase("pawn")) {
				ind = 5;
			}
			ind = ind + 6;
			g.drawImage(imgs[ind], p.getXp() * 64, p.getYp() * 64, this);

		}

	}

	public void ColorizePieceSelected(int x, int y, Color color) {
		ChessGameRepresentationSolo.getInstance().setPieceSelected(new Cell(x, y, color));
		ChessGameRepresentationSolo.getInstance().setIsPieceSlected(true);
		this.repaint();
	}

	public void ColorizeListSelectedCell(LinkedList<Cell> cell) {
		for (Cell c : cell) {
			ChessGameRepresentationSolo.getInstance().getCellsSelected().add(c);
		}
		this.repaint();
	}

	public void ColorizeListAttackCell(LinkedList<Cell> cell) {
		for (Cell c : cell) {
			ChessGameRepresentationSolo.getInstance().getAttackCells().add(c);
		}
		this.repaint();
	}

	public void clearSelection() {
		ChessGameRepresentationSolo.getInstance().getAttackCells().clear();
		ChessGameRepresentationSolo.getInstance().getCellsSelected().clear();
		ChessGameRepresentationSolo.getInstance().setIsPieceSlected(false);

		this.repaint();
	}

	public Boolean getMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean myTurn) {
		this.myTurn = myTurn;
	}

	public int evaluationGame(boolean white) {
		// To be implemented
		return 0;
	}

	public int minmax(int depth, boolean white, LinkedList<PieceSolo> position) {
		return 0;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Adapter extends MouseAdapter {
	private SoloGame ChessPanel;
	private final int depth = 3;

	public Adapter(SoloGame chessPanel) {
		super();
		ChessPanel = chessPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////// Check if it is user my
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// turn/////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (!this.ChessPanel.getMyTurn()) {
			System.out.println("Not your turn");

		}
		int x = e.getX() / 64;
		int y = e.getY() / 64;
		PieceSolo p = ChessGameRepresentationSolo.getInstance().GetPieceXY(x, y);
		if (p != null) {
			if (!p.getWhite()) {
				p = null;
			}
		}

		boolean chessWhite = SoloGame.getInstance().checkIfWhiteIsChessMate();
		boolean chessBlack = SoloGame.getInstance().checkIfBlackIsChessMate();
		if (chessBlack) {
			System.out.println("##########################################");
			System.out.println("#####################BLACK LOOSE##########");
			System.out.println("##########################################");
			WinningPopUp popup=new WinningPopUp(true);

		}
		if (chessWhite) {
			System.out.println("##########################################");
			System.out.println("#####################WHITE LOOSE##########");
			System.out.println("##########################################");
			WinningPopUp popup=new WinningPopUp(false);
		}
		System.out.println(chessWhite + "->" + chessBlack);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////// Check if the user has already selected a
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// cell/////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (ChessGameRepresentationSolo.getInstance().getIsPieceSlected()) {
			// Check if user choose one of the legal move
			for (Cell p1 : ChessGameRepresentationSolo.getInstance().getCellsSelected()) {
				if (p1.getX() == x && p1.getY() == y) {
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////// YOUR
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// TURN!!!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// Get the piece
					PieceSolo tmp = ChessGameRepresentationSolo.getInstance().GetPieceXY(
							ChessGameRepresentationSolo.getInstance().getPieceSelected().getX(),
							ChessGameRepresentationSolo.getInstance().getPieceSelected().getY());
					int saveX = tmp.getXp();
					int saveY = tmp.getYp();
					// Move the piece
					tmp.move(x, y);
					// If chess mate the user cannot move the pice, he must move another one
					if (ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {
						// On itere sur toutes les combinaisons possibles pour voir si le joueur peut se
						// sortir de la situation
						tmp.move(saveX, saveY);
						// Vérifie si on est échec et mat
						// Check if white is chess mate

						break;
					}
					
					//Move has been done
					if(tmp.getUniqueid().contains("pawn")) {
						tmp.setFirstMove(false);
					}
					// Crreation first action
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////// AI
					// PART!!!!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						CareTaker.getInstance().clear();
						// Create MinMax object
						MinMaxAlgo algo = new MinMaxAlgo();
						Action a = new Action(tmp.getUniqueid(), tmp.getXp(), tmp.getYp(), saveX, saveY);
						Node node = new Node(a);
						algo.buildTreeRecu(false, depth, node);
						// TreeRepresentation.getInstance(node).buildTree(TreeRepresentation.getInstance(node).getMainTree(),node);
						Action bestMove = algo.findOptimalValue(false, depth, node);
						System.out.println("Next best move is " + bestMove + "vs" + ChessGameRepresentationSolo.getInstance().evaluate());
						// Play the black
						PieceSolo tmp2 = ChessGameRepresentationSolo.getInstance().GetPieceXY(bestMove.getOriginX(),
								bestMove.getOriginY());
						tmp2.move(bestMove.getNextX(), bestMove.getNextY());
						//Move has been done
						if(tmp2.getUniqueid().contains("pawn")) {
							tmp.setFirstMove(false);
						}
				}
			}
			// Check if user choose one of the attack move
			for (Cell p2 : ChessGameRepresentationSolo.getInstance().getAttackCells()) {
				if (p2.getX() == x && p2.getY() == y) {
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////// YOUR
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// TURN!!!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// Check that white piece are not in chess mate
					ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess();
					// Get the piece
					PieceSolo tmp = ChessGameRepresentationSolo.getInstance().GetPieceXY(
							ChessGameRepresentationSolo.getInstance().getPieceSelected().getX(),
							ChessGameRepresentationSolo.getInstance().getPieceSelected().getY());
					// Save coordinate
					int saveX = tmp.getXp();
					int saveY = tmp.getYp();
					// Move the piece
					PieceSolo eaten = tmp.move(x, y);
					// If chess mate the user cannot move the pice, he must move another one
					if (ChessGameRepresentationSolo.getInstance().CheckWhiteIsChess()) {

						tmp.move(saveX, saveY);
						eaten.setEated(false);

						// restore piece eated
						break;
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////// AI
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					CareTaker.getInstance().clear();
					// Create MinMax object
					MinMaxAlgo algo = new MinMaxAlgo();
					Action a = new Action(tmp.getUniqueid(), tmp.getXp(), tmp.getYp(), saveX, saveY);
					Node node = new Node(a);
					algo.buildTreeRecu(false, depth, node);
					// TreeRepresentation.getInstance(node).buildTree(TreeRepresentation.getInstance(node).getMainTree(),node);
					Action bestMove = algo.findOptimalValue(false, depth, node);
					System.out.println("Next best move is " + bestMove + "vs" + ChessGameRepresentationSolo.getInstance().evaluate());
					// Play the black
					PieceSolo tmp2 = ChessGameRepresentationSolo.getInstance().GetPieceXY(bestMove.getOriginX(),
							bestMove.getOriginY());
					tmp2.move(bestMove.getNextX(), bestMove.getNextY());
				}
			}

			ChessPanel.clearSelection();

		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////// Check if the cell that user selected has a piece on
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// it////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (p == null) {
			System.out.println("You clicked on empty cell");
			return;
		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////// Get legal move & attack
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// possible//////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// If you created the game you are the white and you are just allowed to move
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// white//////////////////////////////////////

		// Get legal move
		LinkedList<Cell> selectedCell = p.getLegalMoves();
		// Get attack move
		LinkedList<Cell> attackCell = p.getAttackMoves();
		// Colorize legal move
		this.ChessPanel.ColorizeListSelectedCell(selectedCell);
		// Colorize attack cell
		this.ChessPanel.ColorizeListAttackCell(attackCell);
		// Colorize the piece selected
		ChessPanel.ColorizePieceSelected(x, y, Color.BLUE);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}