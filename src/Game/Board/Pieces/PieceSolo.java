package Game.Board.Pieces;

import java.awt.Color;
import java.util.LinkedList;

import ChessGameRepresentation.ChessGameRepresentationSolo;
import Singleton.Game;
import Singleton.SoloGame;

public class PieceSolo implements Cloneable {
	private int xp;
	private int yp;
	private String name;
	private boolean  white;
	private boolean eated=false;
	private int score;
	private boolean firstMove;
	public String uniqueid;
	
	public PieceSolo(int xp, int yp, boolean white, String name) {
		super();
		this.xp = xp;
		this.yp = yp;
		this.name = name;
		this.white = white;
		this.uniqueid=name+":"+white;
		this.firstMove=true;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getYp() {
		return yp;
	}

	public void setYp(int yp) {
		this.yp = yp;
	}

	public void moveAI(Cell newDest) {

	}

	public boolean getWhite() {
		return white;
	}

	public void setWhite(Boolean white) {
		this.white = white;
	}

	public boolean isEated() {
		return eated;
	}

	public void setEated(boolean eated) {
		this.eated = eated;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////// MOVE
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// PIECE/////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public LinkedList<Cell> getLegalMoves() {
		int alpha = 127; // 50% transparent
		Color transparent = new Color(0, 218, 255, alpha);
		LinkedList<Cell> result = new LinkedList<Cell>();
		if (name.equalsIgnoreCase("king")) {
////////////////1 deplacement////////////////////////
			int x1 = xp;
			int y1 = yp - 1;
			if (checkIfCoordinateValid(x1, y1)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, transparent));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 1;
			if (checkIfCoordinateValid(x2, y2)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, transparent));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 1;
			int y3 = yp;
			if (checkIfCoordinateValid(x3, y3)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, transparent));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 1;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, transparent));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp;
			int y5 = yp + 1;
			if (checkIfCoordinateValid(x5, y5)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, transparent));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 1;
			if (checkIfCoordinateValid(x6, y6)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, transparent));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 1;
			int y7 = yp;
			if (checkIfCoordinateValid(x7, y7)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, transparent));
			}
////////////////8 deplacement////////////////////////
			int x8 = xp - 1;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, transparent));
			}
		}
		if (name.equalsIgnoreCase("queen")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp--;
			}
			///////////////// 5 deplacement////////////////////////
			xtmp = xp - 1;
			ytmp = yp;
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 6 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 7 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 8 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp++;
			}
		}
		if (name.equalsIgnoreCase("bishop")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp--;
			}
		}
		if (name.equalsIgnoreCase("knight")) {
////////////////1 deplacement////////////////////////
			int x1 = xp - 1;
			int y1 = yp - 2;
			if (checkIfCoordinateValid(x1, y1)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, transparent));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 2;
			if (checkIfCoordinateValid(x2, y2)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, transparent));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 2;
			int y3 = yp - 1;
			if (checkIfCoordinateValid(x3, y3)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, transparent));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 2;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, transparent));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp + 1;
			int y5 = yp + 2;
			if (checkIfCoordinateValid(x5, y5)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, transparent));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 2;
			if (checkIfCoordinateValid(x6, y6)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, transparent));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 2;
			int y7 = yp + 1;
			if (checkIfCoordinateValid(x7, y7)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, transparent));
			}
////////////////8 deplacement////////////////////////
			int x8 = xp - 2;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8)
					&& !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, transparent));
			}

		}
		if (name.equalsIgnoreCase("rook")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp - 1;
			int ytmp = yp;
			while (xtmp >= 0 && !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 2 deplacement////////////////////////
			while (xtmp <= 7 && !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 3 deplacement////////////////////////
			while (ytmp >= 0 && !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 4 deplacement////////////////////////
			while (ytmp <= 7 && !ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp++;
			}
		}

		// If piece is white
		if (name.equalsIgnoreCase("pawn") && white) {
			//Si coordonne valide -1
			if (checkIfCoordinateValid(xp, yp - 1)) {
					if (!ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xp, yp - 1)) {
						Cell c = new Cell(xp, yp - 1, transparent);
						result.add(c);
					}
				
			}
			//Si coordonne valide -2
			if (checkIfCoordinateValid(xp, yp - 2) && firstMove) {
				if (!ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xp, yp - 2)) {
					Cell c = new Cell(xp, yp - 2, transparent);
					result.add(c);
				}
			
		}
		}

		// If piece is black

		if (name.equalsIgnoreCase("pawn") && !white) {
			if (checkIfCoordinateValid(xp, yp + 1)) {
				if (!ChessGameRepresentationSolo.getInstance().checkIfPieceOnCell(xp, yp + 1)) {
					Cell c = new Cell(xp, yp + 1, transparent);
					result.add(c);
				}

			}
		}

		return result;

	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public LinkedList<Cell> getAttackMoves() {
		int alpha = 127; // 50% transparent166,16,30
		Color attackMove = new Color(166, 16, 30, alpha);
		LinkedList<Cell> result = new LinkedList<Cell>();
		if (name.equalsIgnoreCase("king")) {
			//////////////// 1 deplacement////////////////////////
			int x1 = xp;
			int y1 = yp - 1;
			if (checkIfCoordinateValid(x1, y1) && checkIfEnnemyPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, attackMove));
			}
			//////////////// 2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 1;
			if (checkIfCoordinateValid(x2, y2) && checkIfEnnemyPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, attackMove));
			}
			//////////////// 3 deplacement////////////////////////
			int x3 = xp + 1;
			int y3 = yp;
			if (checkIfCoordinateValid(x3, y3) && checkIfEnnemyPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, attackMove));
			}
			//////////////// 4 deplacement////////////////////////
			int x4 = xp + 1;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && checkIfEnnemyPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, attackMove));
			}
			//////////////// 5 deplacement////////////////////////
			int x5 = xp;
			int y5 = yp + 1;
			if (checkIfCoordinateValid(x5, y5) && checkIfEnnemyPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, attackMove));
			}
			//////////////// 6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 1;
			if (checkIfCoordinateValid(x6, y6) && checkIfEnnemyPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, attackMove));
			}
			//////////////// 7 deplacement////////////////////////
			int x7 = xp - 1;
			int y7 = yp;
			if (checkIfCoordinateValid(x7, y7) && checkIfEnnemyPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, attackMove));
			}
			//////////////// 8 deplacement////////////////////////
			int x8 = xp - 1;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8) && checkIfEnnemyPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, attackMove));
			}
		}
		if (name.equalsIgnoreCase("queen")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
				ytmp--;
			}
			///////////////// 5 deplacement////////////////////////
			xtmp = xp - 1;
			ytmp = yp;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 6 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;

			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 7 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 8 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				ytmp++;
			}
		}
		if (name.equalsIgnoreCase("bishop")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
				ytmp--;
			}
		}
		if (name.equalsIgnoreCase("knight")) {
			//////////////// 1 deplacement////////////////////////
			int x1 = xp - 1;
			int y1 = yp - 2;
			if (checkIfCoordinateValid(x1, y1) && checkIfEnnemyPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, attackMove));
			}
			//////////////// 2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 2;
			if (checkIfCoordinateValid(x2, y2) && checkIfEnnemyPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, attackMove));
			}
			//////////////// 3 deplacement////////////////////////
			int x3 = xp + 2;
			int y3 = yp - 1;
			if (checkIfCoordinateValid(x3, y3) && checkIfEnnemyPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, attackMove));
			}
			//////////////// 4 deplacement////////////////////////
			int x4 = xp + 2;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && checkIfEnnemyPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, attackMove));
			}
			//////////////// 5 deplacement////////////////////////
			int x5 = xp + 1;
			int y5 = yp + 2;
			if (checkIfCoordinateValid(x5, y5) && checkIfEnnemyPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, attackMove));
			}
			//////////////// 6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 2;
			if (checkIfCoordinateValid(x6, y6) && checkIfEnnemyPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, attackMove));
			}
			//////////////// 7 deplacement////////////////////////
			int x7 = xp - 2;
			int y7 = yp + 1;
			if (checkIfCoordinateValid(x7, y7) && checkIfEnnemyPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, attackMove));
			}
			//////////////// 8 deplacement////////////////////////
			int x8 = xp - 2;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8) && checkIfEnnemyPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, attackMove));
			}
		}
		if (name.equalsIgnoreCase("rook")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp - 1;
			int ytmp = yp;
			while (xtmp >= 0 && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 2 deplacement////////////////////////
			while (xtmp <= 7 && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				xtmp++;
			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 3 deplacement////////////////////////
			while (ytmp >= 0 && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 4 deplacement////////////////////////
			while (ytmp <= 7 && !checkIfSameTeamPieceOnCell(xtmp, ytmp)) {
				if (checkIfEnnemyPieceOnCell(xtmp, ytmp)) {
					result.add(new Cell(xtmp, ytmp, attackMove));
					break;
				}
				ytmp++;
			}
		}
		// If piece is white
		if (name.equalsIgnoreCase("pawn") && white) {
			if (name.equalsIgnoreCase("pawn")) {
				// First possibility of attack
				int x1 = xp - 1;
				int y1 = yp - 1;
				if (checkIfCoordinateValid(x1, y1)) {
					if (checkIfEnnemyPieceOnCell(x1, y1)) {
						Cell c = new Cell(x1, y1, attackMove);
						result.add(c);
					}

				}
				// Second possibility of attack
				int x2 = xp + 1;
				int y2 = yp - 1;
				if (checkIfCoordinateValid(x2, y2)) {
					if (checkIfEnnemyPieceOnCell(x2, y2)) {
						Cell c = new Cell(x2, y2, attackMove);
						result.add(c);
					}

				}
			}
		}
		// Piece is black

		if (name.equalsIgnoreCase("pawn") && !white) {
			if (name.equalsIgnoreCase("pawn")) {
				// First possibility of attack
				int x1 = xp - 1;
				int y1 = yp + 1;
				if (checkIfCoordinateValid(x1, y1)) {
					if (checkIfEnnemyPieceOnCell(x1, y1)) {
						Cell c = new Cell(x1, y1, attackMove);
						result.add(c);
					}

				}
				// Second possibility of attack
				int x2 = xp + 1;
				int y2 = yp + 1;
				if (checkIfCoordinateValid(x2, y2)) {
					if (checkIfEnnemyPieceOnCell(x2, y2)) {
						Cell c = new Cell(x2, y2, attackMove);
						result.add(c);
					}

				}
			}
		}

		return result;
	}

	public boolean checkIfEnnemyPieceOnCell(int x, int y) {

		PieceSolo piece = ChessGameRepresentationSolo.getInstance().GetPieceXY(x, y);
		if (piece == null) {
			return false;
		}
		if (piece.getWhite() != white) {
			return true;
		}
		return false;

	}

	public boolean checkIfSameTeamPieceOnCell(int x, int y) {
		PieceSolo piece = ChessGameRepresentationSolo.getInstance().GetPieceXY(x, y);
		if (piece == null) {
			return false;
		}
		if (piece.getWhite() == white) {
			return true;
		}
		return false;
	}

	public boolean checkIfCoordinateValid(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			return true;
		}
		return false;
	}
	public LinkedList<Cell> getAllMoves(){
		LinkedList<Cell> results = new LinkedList<Cell>();
		LinkedList<Cell> allLegalMoves = getLegalMoves();
		LinkedList<Cell> allAttackMoves =getAttackMoves();
		results.addAll(allLegalMoves);
		results.addAll(allAttackMoves);
		return results;
	}
	
	public boolean checkIfLegalMove (Cell move) {
		for(Cell moveA: getAllMoves()) {
			if(moveA.getX()==move.getX() && moveA.getY()==move.getY()) {
				return true;
			}
		}
		return false;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////// MOVE
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// FUNCTION/////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public PieceSolo move(int x, int y) {
		//Check if we can attack
		boolean pieceEnnemy=checkIfEnnemyPieceOnCell(x, y);
		if(pieceEnnemy) {
			PieceSolo tmp=ChessGameRepresentationSolo.getInstance().GetPieceXY(x, y);
			if(!tmp.isEated()) {
				ChessGameRepresentationSolo.getInstance().deletePiece(x, y);
				this.xp = x;
				this.yp = y;
				SoloGame.getInstance().repaint();
				return tmp;
			}

		}
		this.xp = x;
		this.yp = y;
		SoloGame.getInstance().repaint();
		return null;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////// AI
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// FUNCTIONALITIES////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void moveAI(int x, int y) {
		// Move piece
		// Incldue thread sleep
	}

	public void unmoveAI() {
		// TBD
	}

	// Test all the possibilies attack and return the best one
	// Need to move temporary the piece
	// And immediately rollback after
	public int getMinimumScoreMinMAx() {
		return 0;
	}

	public int getMaximumScoreMinMAx() {

		return 0;
	}
	public PieceSolo Clone() {
		return new PieceSolo(xp, yp, white, name);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////// TO
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// STRING/////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "PieceSolo [xp=" + xp + ", yp=" + yp + ", name=" + name + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PieceSolo newPiece = new PieceSolo(xp, yp, white, name);
		return newPiece;
	}

}
