package Game.Board;

public class RequestChessBoardMessage {
	public int gameid;
	private Boolean whiteView;
	public RequestChessBoardMessage() {
		
	}
	public RequestChessBoardMessage(int gameid, Boolean whiteView) {
		super();
		this.gameid = gameid;
		this.whiteView = whiteView;
	}
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public Boolean getWhiteView() {
		return whiteView;
	}
	public void setWhiteView(Boolean whiteView) {
		this.whiteView = whiteView;
	}
	
	
}
