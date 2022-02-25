package Game.Board.Pieces;

import java.util.List;

import javax.swing.JTable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import Singleton.Game;

public class Piece {
	int xp;
	int yp;
	boolean white;
	//LinkedList<Piece> ps;
	public String name;

	public Piece(int xp, int yp, boolean white, String n) {
		this.xp = xp;
		this.yp = yp;
		this.white = white;
		//this.ps = ps;
		name = n;

	}
	
	

	public void move(int xp, int yp) {
		// Error cause below when a piece move on a cell containing another piece

//		ps.stream().filter((p) -> (p.xp == xp && p.yp == yp)).forEachOrdered((p) -> {
		// p.kill();
		// });
		this.xp = xp;
		this.yp = yp;

	}

	public void kill() {
		Game.getInstance().getChessPanel().deletePieceXY(xp, yp);
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







	public boolean isWhite() {
		return white;
	}



	public void setWhite(boolean white) {
		this.white = white;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean checkIfEnnemyPieceOnCell(int x, int y) {
		Piece p = Game.getInstance().getChessPanel().GetPieceXY(x, y);
		if (p == null) {
			return false;
		}
		if (p.isWhite() != this.white) {
			return true;
		}
		return false;
	}

	public boolean checkIfSameTeamPieceOnCell(int x, int y) {
		Piece p = Game.getInstance().getChessPanel().GetPieceXY(x, y);
		if (p == null) {
			return false;
		}
		if (p.isWhite() == this.white) {
			return true;
		}
		return false;
	}

	public boolean checkIfPieceOnCell(int x, int y) {
		Piece p = Game.getInstance().getChessPanel().GetPieceXY(x, y);
		if (p == null) {
			return false;
		}
		return true;
	}

	public boolean checkIfCoordinateValid(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			return true;
		}
		return false;
	}

	public LinkedList<Cell> getAttackMoves() {
		int alpha = 127; // 50% transparent166,16,30
		Color attackMove = new Color(166, 16, 30, alpha);
		LinkedList<Cell> result = new LinkedList<Cell>();
		if (name.equalsIgnoreCase("king")) {
////////////////1 deplacement////////////////////////
			int x1 = xp;
			int y1 = yp - 1;
			if (checkIfCoordinateValid(x1, y1) && checkIfEnnemyPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, attackMove));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 1;
			if (checkIfCoordinateValid(x2, y2) && checkIfEnnemyPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, attackMove));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 1;
			int y3 = yp;
			if (checkIfCoordinateValid(x3, y3) && checkIfEnnemyPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, attackMove));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 1;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && checkIfEnnemyPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, attackMove));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp;
			int y5 = yp + 1;
			if (checkIfCoordinateValid(x5, y5) && checkIfEnnemyPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, attackMove));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 1;
			if (checkIfCoordinateValid(x6, y6) && checkIfEnnemyPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, attackMove));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 1;
			int y7 = yp;
			if (checkIfCoordinateValid(x7, y7) && checkIfEnnemyPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, attackMove));
			}
////////////////8 deplacement////////////////////////
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
////////////////1 deplacement////////////////////////
			int x1 = xp - 1;
			int y1 = yp - 2;
			if (checkIfCoordinateValid(x1, y1) && checkIfEnnemyPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, attackMove));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 2;
			if (checkIfCoordinateValid(x2, y2) && checkIfEnnemyPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, attackMove));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 2;
			int y3 = yp - 1;
			if (checkIfCoordinateValid(x3, y3) && checkIfEnnemyPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, attackMove));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 2;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && checkIfEnnemyPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, attackMove));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp + 1;
			int y5 = yp + 2;
			if (checkIfCoordinateValid(x5, y5) && checkIfEnnemyPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, attackMove));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 2;
			if (checkIfCoordinateValid(x6, y6) && checkIfEnnemyPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, attackMove));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 2;
			int y7 = yp + 1;
			if (checkIfCoordinateValid(x7, y7) && checkIfEnnemyPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, attackMove));
			}
////////////////8 deplacement////////////////////////
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
		if (name.equalsIgnoreCase("pawn")) {
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
				int x2 = xp - 1;
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

	public LinkedList<Cell> getLegalMoves() {
		int alpha = 127; // 50% transparent
		Color transparent = new Color(0, 218, 255, alpha);
		LinkedList<Cell> result = new LinkedList<Cell>();
		if (name.equalsIgnoreCase("king")) {
////////////////1 deplacement////////////////////////
			int x1 = xp;
			int y1 = yp - 1;
			if (checkIfCoordinateValid(x1, y1) && !checkIfPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, transparent));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 1;
			if (checkIfCoordinateValid(x2, y2) && !checkIfPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, transparent));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 1;
			int y3 = yp;
			if (checkIfCoordinateValid(x3, y3) && !checkIfPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, transparent));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 1;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && !checkIfPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, transparent));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp;
			int y5 = yp + 1;
			if (checkIfCoordinateValid(x5, y5) && !checkIfPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, transparent));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 1;
			if (checkIfCoordinateValid(x6, y6) && !checkIfPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, transparent));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 1;
			int y7 = yp;
			if (checkIfCoordinateValid(x7, y7) && !checkIfPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, transparent));
			}
////////////////8 deplacement////////////////////////
			int x8 = xp - 1;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8) && !checkIfPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, transparent));
			}
		}
		if (name.equalsIgnoreCase("queen")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp--;
			}
			///////////////// 5 deplacement////////////////////////
			xtmp = xp - 1;
			ytmp = yp;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 6 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 7 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 8 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp++;
			}
		}
		if (name.equalsIgnoreCase("bishop")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp + 1;
			int ytmp = yp + 1;
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp++;
			}
			xtmp = xp + 1;
			ytmp = yp - 1;
			///////////////// 2 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
				ytmp--;
			}
			xtmp = xp - 1;
			ytmp = yp + 1;
			///////////////// 3 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp++;
			}
			xtmp = xp - 1;
			ytmp = yp - 1;
			///////////////// 4 deplacement////////////////////////
			while (checkIfCoordinateValid(xtmp, ytmp) && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
				ytmp--;
			}
		}
		if (name.equalsIgnoreCase("knight")) {
////////////////1 deplacement////////////////////////
			int x1 = xp - 1;
			int y1 = yp - 2;
			if (checkIfCoordinateValid(x1, y1) && !checkIfPieceOnCell(x1, y1)) {
				result.add(new Cell(x1, y1, transparent));
			}
////////////////2 deplacement////////////////////////
			int x2 = xp + 1;
			int y2 = yp - 2;
			if (checkIfCoordinateValid(x2, y2) && !checkIfPieceOnCell(x2, y2)) {
				result.add(new Cell(x2, y2, transparent));
			}
////////////////3 deplacement////////////////////////
			int x3 = xp + 2;
			int y3 = yp - 1;
			if (checkIfCoordinateValid(x3, y3) && !checkIfPieceOnCell(x3, y3)) {
				result.add(new Cell(x3, y3, transparent));
			}
////////////////4 deplacement////////////////////////
			int x4 = xp + 2;
			int y4 = yp + 1;
			if (checkIfCoordinateValid(x4, y4) && !checkIfPieceOnCell(x4, y4)) {
				result.add(new Cell(x4, y4, transparent));
			}
////////////////5 deplacement////////////////////////
			int x5 = xp + 1;
			int y5 = yp + 2;
			if (checkIfCoordinateValid(x5, y5) && !checkIfPieceOnCell(x5, y5)) {
				result.add(new Cell(x5, y5, transparent));
			}
////////////////6 deplacement////////////////////////
			int x6 = xp - 1;
			int y6 = yp + 2;
			if (checkIfCoordinateValid(x6, y6) && !checkIfPieceOnCell(x6, y6)) {
				result.add(new Cell(x6, y6, transparent));
			}
////////////////7 deplacement////////////////////////
			int x7 = xp - 2;
			int y7 = yp + 1;
			if (checkIfCoordinateValid(x7, y7) && !checkIfPieceOnCell(x7, y7)) {
				result.add(new Cell(x7, y7, transparent));
			}
////////////////8 deplacement////////////////////////
			int x8 = xp - 2;
			int y8 = yp - 1;
			if (checkIfCoordinateValid(x8, y8) && !checkIfPieceOnCell(x8, y8)) {
				result.add(new Cell(x8, y8, transparent));
			}

		}
		if (name.equalsIgnoreCase("rook")) {
			///////////////// 1 deplacement////////////////////////
			int xtmp = xp - 1;
			int ytmp = yp;
			while (xtmp >= 0 && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp--;
			}
			xtmp = xp + 1;
			ytmp = yp;
			///////////////// 2 deplacement////////////////////////
			while (xtmp <= 7 && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				xtmp++;
			}
			xtmp = xp;
			ytmp = yp - 1;
			///////////////// 3 deplacement////////////////////////
			while (ytmp >= 0 && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp--;
			}
			xtmp = xp;
			ytmp = yp + 1;
			///////////////// 4 deplacement////////////////////////
			while (ytmp <= 7 && !checkIfPieceOnCell(xtmp, ytmp)) {
				result.add(new Cell(xtmp, ytmp, transparent));
				ytmp++;
			}
		}
		if (name.equalsIgnoreCase("pawn")) {
			if (yp - 1 != 0) {
				if (!checkIfPieceOnCell(xp, yp - 1)) {
					Cell c = new Cell(xp, yp - 1, transparent);
					result.add(c);
				}

			}
		}
		return result;
	}



	@Override
	public String toString() {
		return "Piece [xp=" + xp + ", yp=" + yp + ", isWhite=" + white + ", name=" + name + "]";
	}
	

}