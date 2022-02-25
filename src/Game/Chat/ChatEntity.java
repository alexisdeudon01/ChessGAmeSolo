package Game.Chat;

public class ChatEntity {
	private int gameid;
	private String message;
	private String sender;
	public ChatEntity(int gameid, String message, String sender) {
		super();
		this.gameid = gameid;
		this.message = message;
		this.sender=sender;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
