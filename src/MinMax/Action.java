package MinMax;

import ChessGameRepresentation.ChessGameRepresentationSolo;

public class Action {
	private String pieceId;
	private int nextX;
	private int nextY;
	private int whiteScore;
	private int originX;
	private int originY;
	
	
	
	public int getOriginX() {
		return originX;
	}
	public void setOriginX(int originX) {
		this.originX = originX;
	}
	public int getOriginY() {
		return originY;
	}
	public void setOriginY(int originY) {
		this.originY = originY;
	}
	public int getWhiteScore() {
		return whiteScore;
	}
	public void setWhiteScore(int whiteScore) {
		this.whiteScore = whiteScore;
	}
	
	public Action(String pieceId, int nextX, int nextY, int originX, int originY) {
		super();
		this.pieceId = pieceId;
		this.nextX = nextX;
		this.nextY = nextY;
		this.originX = originX;
		this.originY = originY;
		this.whiteScore=ChessGameRepresentationSolo.getInstance().evaluate();

	}

	public String getPieceId() {
		return pieceId;
	}
	public void setPieceId(String pieceId) {
		this.pieceId = pieceId;
	}
	public int getNextX() {
		return nextX;
	}
	public void setNextX(int nextX) {
		this.nextX = nextX;
	}
	public int getNextY() {
		return nextY;
	}
	public void setNextY(int nextY) {
		this.nextY = nextY;
	}

	
	@Override
	public String toString() {
		return "Action [pieceId=" + pieceId + ", nextX=" + nextX + ", nextY=" + nextY + ", whiteScore=" + whiteScore
				+ ", originX=" + originX + ", originY=" + originY + "]";
	}
	public Action Clone() {
		Action newAction=new Action(pieceId,nextX,nextY,originX,originY);

		return newAction;
	}
	
}
