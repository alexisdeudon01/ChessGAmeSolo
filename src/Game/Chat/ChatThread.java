package Game.Chat;

import javax.swing.JPanel;

import Singleton.Sock;

public class ChatThread implements Runnable {
	private Boolean running=true;
	private ChatGamePanel ChatBox;
	private int gameid;
	public ChatThread(ChatGamePanel ChatBox,int gameid) {
		super();
		this.ChatBox=ChatBox;
		this.gameid=gameid;
	}
	public void terminate() {
		running=false;
	}
	public void run() {
		while(running) {
			//ask to refresh the chat
			try {
				ChatEntity msg=Sock.getInstance().getLastMessage(gameid);
				this.ChatBox.getMainTextArea().append(msg.getSender()+" : "+msg.getMessage()+"\n**\n");
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
