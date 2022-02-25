package Game.Board;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import Game.Board.Pieces.Cell;
import Game.Board.Pieces.Piece;
import Singleton.Game;
import Singleton.Sock;
public class ChessGamePanel extends JPanel {
	private Game mainFrame;
	private BorderLayout borderLayout;
	// Array containing pieces
	private LinkedList<Piece> ps;
	// Image array containing piece
	private Image imgs[];
	//Containing list of cells selected by the user
	private LinkedList<Cell> cellsSelected;
	//Containing list of cells with possibe attack
	private LinkedList<Cell> attackCells;
	//Cell that contain the piece selected
	private Cell pieceSelected;
	//Boolean that is true if user has selected a cell
	private Boolean isPieceSlected=false;
	//Class for next view
	private ChessGameThread chessGameThread;
	//Thread for next view
	private Thread chessGameThreadActive;
	//True if it is your turn
	private Boolean myTurn;
	public ChessGamePanel(Game mainFrame) {
		super();
		
		this.mainFrame = mainFrame;
		// Creating list for the pieces
		this.ps = new LinkedList<>();
		// Configure panel
		setBackground(Color.red);
		setSize((int) (this.mainFrame.getWidth() * 0.75), this.mainFrame.getHeight());
		this.borderLayout = new BorderLayout();
		setLayout(borderLayout);
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
		this.cellsSelected = new LinkedList<>();
		this.attackCells=new LinkedList<>();
//////////////////////////////////////////////////////////////////////////
///////////////////IF YOU CREATE THE GAME YOU ARE THE WHITE///////////////
//////////////////////////////////////////////////////////////////////////
		if(this.mainFrame.isGameCreation()) {
			this.myTurn=true;
			createPieceWhiteView();
			//start thread
			createNextViewThread();
			
		}
//////////////////////////////////////////////////////////////////////////
///////////////////IF YOU JOIN THE GAME YOU ARE THE BLACK/////////////////
//////////////////////////////////////////////////////////////////////////
		else {
			this.myTurn=false;
			createPieceBlackView();
			//start thread
			createNextViewThread();
			
		}
	}
	
	public void createNextViewThread() {
		System.out.println("Creating object for the thread");
		this.chessGameThread=new ChessGameThread(this);
		System.out.println("Injecting object into the thread");
		this.chessGameThreadActive=new Thread(this.chessGameThread);
		System.out.println("Starting the thread");
		this.chessGameThreadActive.start();
		System.out.println("Go on");
	}
	public void addNewPiece(Piece p) {
		this.ps.add(p);
	}
	public void createPieceWhiteView() {
		Piece brook = new Piece(0, 0, false, "rook");
		this.ps.add(brook);
		Piece bkinght = new Piece(1, 0, false, "knight");
		this.ps.add(bkinght);
		Piece bbishop = new Piece(2, 0, false, "bishop");
		this.ps.add(bbishop);
		Piece bqueen = new Piece(3, 0, false, "queen");
		this.ps.add(bqueen);
		Piece bking = new Piece(4, 0, false, "king");
		this.ps.add(bking);
		Piece bbishop2 = new Piece(5, 0, false, "bishop");
		this.ps.add(bbishop2);
		Piece bkight2 = new Piece(6, 0, false, "knight");
		this.ps.add(bkight2);
		Piece brook2 = new Piece(7, 0, false, "rook");
		this.ps.add(brook2);
		Piece bpawn1 = new Piece(1, 1, false, "pawn");
		this.ps.add(bpawn1);
		Piece bpawn2 = new Piece(2, 1, false, "pawn");
		this.ps.add(bpawn2);
		Piece bpawn3 = new Piece(3, 1, false, "pawn");
		this.ps.add(bpawn3);
		Piece bpawn4 = new Piece(4, 1, false, "pawn");
		this.ps.add(bpawn4);
		Piece bpawn5 = new Piece(5, 1, false, "pawn");
		this.ps.add(bpawn5);
		Piece bpawn6 = new Piece(6, 1, false, "pawn");
		this.ps.add(bpawn6);
		Piece bpawn7 = new Piece(7, 1, false, "pawn");
		this.ps.add(bpawn7);
		Piece bpawn8 = new Piece(0, 1, false, "pawn");
		this.ps.add(bpawn8);
		
		Piece wrook = new Piece(0, 7, true, "rook");
		this.ps.add(wrook);
		Piece wkinght = new Piece(1, 7, true, "knight");
		this.ps.add(wkinght);
		Piece wbishop = new Piece(2, 7, true, "bishop");
		this.ps.add(wbishop);
		Piece wqueen = new Piece(3, 7, true, "queen"); 
		this.ps.add(wqueen);
		Piece wking = new Piece(4, 7, true, "king");
		this.ps.add(wking);
		Piece wbishop2 = new Piece(5, 7, true, "bishop");
		this.ps.add(wbishop2);
		Piece wkight2 = new Piece(6, 7, true, "knight");
		this.ps.add(wkight2);
		Piece wrook2 = new Piece(7, 7, true, "rook");
		this.ps.add(wrook2);
		Piece wpawn1 = new Piece(1, 6, true, "pawn");
		this.ps.add(wpawn1);
		Piece wpawn2 = new Piece(2, 6, true, "pawn");
		this.ps.add(wpawn2);
		Piece wpawn3 = new Piece(3, 6, true, "pawn");
		this.ps.add(wpawn3);
		Piece wpawn4 = new Piece(4, 6, true, "pawn");
		this.ps.add(wpawn4);
		Piece wpawn5 = new Piece(5, 6, true, "pawn");
		this.ps.add(wpawn5);
		Piece wpawn6 = new Piece(6, 6, true, "pawn");
		this.ps.add(wpawn6);
		Piece wpawn7 = new Piece(7, 6, true, "pawn");
		this.ps.add(wpawn7);
		Piece wpawn8 = new Piece(0, 6, true, "pawn");
		this.ps.add(wpawn8);

	}
	public void createPieceBlackView() {
		Piece brook = new Piece(0, 0, true, "rook");
		this.ps.add(brook);
		Piece bkinght = new Piece(1, 0, true, "knight");
		this.ps.add(bkinght);
		Piece bbishop = new Piece(2, 0, true, "bishop");
		this.ps.add(bbishop);
		Piece bqueen = new Piece(4, 0, true, "queen");
		this.ps.add(bqueen);
		Piece bking = new Piece(3, 0, true, "king");
		this.ps.add(bking);
		Piece bbishop2 = new Piece(5, 0, true, "bishop");
		this.ps.add(bbishop2);
		Piece bkight2 = new Piece(6, 0, true, "knight");
		this.ps.add(bkight2);
		Piece brook2 = new Piece(7, 0, true, "rook");
		this.ps.add(brook2);
		Piece bpawn1 = new Piece(1, 1, true, "pawn");
		this.ps.add(bpawn1);
		Piece bpawn2 = new Piece(2, 1, true, "pawn");
		this.ps.add(bpawn2);
		Piece bpawn3 = new Piece(3, 1, true, "pawn");
		this.ps.add(bpawn3);
		Piece bpawn4 = new Piece(4, 1, true, "pawn");
		this.ps.add(bpawn4);
		Piece bpawn5 = new Piece(5, 1, true, "pawn");
		this.ps.add(bpawn5);
		Piece bpawn6 = new Piece(6, 1, true, "pawn");
		this.ps.add(bpawn6);
		Piece bpawn7 = new Piece(7, 1, true, "pawn");
		this.ps.add(bpawn7);
		Piece bpawn8 = new Piece(0, 1, true, "pawn");
		this.ps.add(bpawn8);
		Piece wrook = new Piece(0, 7, false, "rook");
		this.ps.add(wrook);
		Piece wkinght = new Piece(1, 7, false, "knight");
		this.ps.add(wkinght);
		Piece wbishop = new Piece(2, 7, false, "bishop");
		this.ps.add(wbishop);
		Piece wqueen = new Piece(4, 7, false, "queen");
		this.ps.add(wqueen);
		Piece wking = new Piece(3, 7, false, "king");
		this.ps.add(wking);
		Piece wbishop2 = new Piece(5, 7, false, "bishop");
		this.ps.add(wbishop2);
		Piece wkight2 = new Piece(6, 7, false, "knight");
		this.ps.add(wkight2);
		Piece wrook2 = new Piece(7, 7, false, "rook");
		this.ps.add(wrook2);
		Piece wpawn1 = new Piece(1, 6, false, "pawn");
		this.ps.add(wpawn1);
		Piece wpawn2 = new Piece(2, 6, false, "pawn");
		this.ps.add(wpawn2);
		Piece wpawn3 = new Piece(3, 6, false, "pawn");
		this.ps.add(wpawn3);
		Piece wpawn4 = new Piece(4, 6, false, "pawn");
		this.ps.add(wpawn4);
		Piece wpawn5 = new Piece(5, 6, false, "pawn");
		this.ps.add(wpawn5);
		Piece wpawn6 = new Piece(6, 6, false, "pawn");
		this.ps.add(wpawn6);
		Piece wpawn7 = new Piece(7, 6, false, "pawn");
		this.ps.add(wpawn7);
		Piece wpawn8 = new Piece(0, 6, false, "pawn");
		this.ps.add(wpawn8);


	}
	@Override
	public void paint(Graphics g) {
		boolean white = true;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				boolean find = false;
				for (Cell c : this.cellsSelected) {
					if (c.getX() == x && c.getY() == y) {
						g.setColor(c.getColor());
						find = true;
					}
				}
				
				for (Cell c : this.attackCells) {
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
		if(isPieceSlected) {
			g.setColor(pieceSelected.getColor());
			g.fillRect(pieceSelected.getX()*64, pieceSelected.getY() * 64, 64, 64);
		}
		for (Piece p : ps) {
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
			if (!p.isWhite()) {
				ind += 6;
			}

			g.drawImage(imgs[ind], p.getXp() * 64, p.getYp() * 64, this);


		}

	}

	public LinkedList<Piece> getPs() {
		System.out.println("New move detetected, ps is "+this.ps.toString());
		return ps;
	}

	public void setPs(LinkedList<Piece> ps) {
		this.ps = ps;
	}

	public void ColorizePieceSelected(int x, int y, Color color) {
		this.pieceSelected=new Cell(x, y, color);
		this.isPieceSlected=true;
		this.repaint();
	}

	public void ColorizeListSelectedCell(LinkedList<Cell> cell) {
		for (Cell c : cell) {
			this.cellsSelected.add(c);
		}
		this.repaint();
	}
	public void ColorizeListAttackCell(LinkedList<Cell> cell) {
		for (Cell c : cell) {
			this.attackCells.add(c);
		}
		this.repaint();
	}

	public void clearSelection() {
		this.attackCells.clear();
		this.cellsSelected.clear();
		this.repaint();
	}
	
	public Piece GetPieceXY(int x, int y) {
		for(Piece p: this.ps) {
			if (p.getXp()==x && p.getYp()==y) {
				return p;
			}
		}
		return null;
	}

	public Game getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(Game mainFrame) {
		this.mainFrame = mainFrame;
	}

	public Boolean getIsPieceSlected() {
		return isPieceSlected;
	}

	public void setIsPieceSlected(Boolean isPieceSlected) {
		this.isPieceSlected = isPieceSlected;
	}

	public LinkedList<Cell> getCellsSelected() {
		return cellsSelected;
	}

	public void setCellsSelected(LinkedList<Cell> cellsSelected) {
		this.cellsSelected = cellsSelected;
	}

	public Cell getPieceSelected() {
		return pieceSelected;
	}

	public void setPieceSelected(Cell pieceSelected) {
		this.pieceSelected = pieceSelected;
	}

	public LinkedList<Cell> getAttackCells() {
		return attackCells;
	}

	public void setAttackCells(LinkedList<Cell> attackCells) {
		this.attackCells = attackCells;
	}
	
	public void deletePieceXY(int X, int Y) {
		for (Piece p: this.ps) {
			if(p.getXp()==X && p.getYp()==Y) {
				this.ps.remove(p);
				return;
			}
		}
	}

	public Boolean getMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean myTurn) {
		this.myTurn = myTurn;
	}
	
	
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Adapter extends MouseAdapter {
	private ChessGamePanel ChessPanel;

	public Adapter(ChessGamePanel chessPanel) {
		super();
		ChessPanel = chessPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////Check if it is user my turn/////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(!this.ChessPanel.getMyTurn()) {
			System.out.println("Not your turn");
			return;
		}
		int x = e.getX() / 64;
		int y = e.getY() / 64;
		System.out.println("You cliked on "+x+":"+y);
		Piece p=this.ChessPanel.GetPieceXY(x, y);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////Check if the user has already selected a cell/////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(ChessPanel.getIsPieceSlected()) {
			//Check if user choose one of the legal move
			for(Cell p1: ChessPanel.getCellsSelected()) {
				if(p1.getX()==x && p1.getY()==y) {
					System.out.println("Move is legal");
					//Move the piece
					this.ChessPanel.GetPieceXY(this.ChessPanel.getPieceSelected().getX(), this.ChessPanel.getPieceSelected().getY()).move(x, y);
					//Send update to server
					Sock.getInstance().sendUpdateGame(this.ChessPanel.getPs());
					//Give the hand to the other player
					this.ChessPanel.setMyTurn(false);
				}
			}
			//Check if user choose one of the attack move
			for(Cell p2: ChessPanel.getAttackCells()) {
				if(p2.getX()==x && p2.getY()==y) {
					System.out.println("Attack is legal");
					//Kill the piece
					this.ChessPanel.deletePieceXY(x, y);
					//Move the piece
					this.ChessPanel.GetPieceXY(this.ChessPanel.getPieceSelected().getX(), this.ChessPanel.getPieceSelected().getY()).move(x, y);
					//Send update to server
					Sock.getInstance().sendUpdateGame(this.ChessPanel.getPs());
					//Give the hand to the other player
					this.ChessPanel.setMyTurn(false);
				}
			}
			ChessPanel.clearSelection();
			ChessPanel.setIsPieceSlected(false);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////Check if the cell that user selected has a piece on it////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(p==null) {
			System.out.println("You clicked on empty cell");
			return;
		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////Get legal move & attack possible//////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//If you created the game you are the white and you are just allowed to move white//////////////////////////////////////
		if(this.ChessPanel.getMainFrame().isGameCreation()) {
			if(p.isWhite()) {
				//Get legal move
				LinkedList<Cell> selectedCell=p.getLegalMoves();
				//Get attack move
				LinkedList<Cell> attackCell=p.getAttackMoves();
				//Colorize legal move
				this.ChessPanel.ColorizeListSelectedCell(selectedCell);
				//Colorize attack cell
				this.ChessPanel.ColorizeListAttackCell(attackCell);
				//Colorize the piece selected
				ChessPanel.ColorizePieceSelected(x, y, Color.BLUE);

			}

		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////Get legal move & attack possible//////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//If you joined the game you are the black and you are just allowed to move black//////////////////////////////////////
		else {
			if(!p.isWhite()) {
				//Get legal move
				LinkedList<Cell> selectedCell=p.getLegalMoves();
				//Get attack move
				LinkedList<Cell> attackCell=p.getAttackMoves();
				//Colorize legal move
				this.ChessPanel.ColorizeListSelectedCell(selectedCell);
				//Colorize attack cell
				this.ChessPanel.ColorizeListAttackCell(attackCell);
				//Colorize the piece selected
				ChessPanel.ColorizePieceSelected(x, y, Color.BLUE);

			}
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}