package Game;

import Singleton.Game;
import Singleton.Sock;

public class foundPlayerThread implements Runnable{
	private Boolean running=true;
	private Game game;
	public foundPlayerThread(Game game) {
		super();
		this.game=game;

	}

	public void run() {
		while(running) {

			String found=Sock.getInstance().checkIfOpponentFound(game.getGameid());
			//System.out.println("echo "+found);
			if(found==null){
				
			}
			else {
				System.out.println("We found a player->"+found);
				game.setPlayerRequested(found);
				game.setTitle(game.getTitle()+" "+game.getPlayerAsking()+" vs "+game.getPlayerRequested());
				interrupt();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void interrupt() {
		this.running=false;
		
	}

}
