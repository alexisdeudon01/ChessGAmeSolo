package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import ChessGameRepresentation.ChessGameRepresentationSolo;
import Singleton.SoloGame;

public class WinningPopUp extends javax.swing.JFrame implements ActionListener{
	//True if white win false otherwise
	public WinningPopUp(boolean white) {
		
		super();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		JPanel panel=new JPanel(new BorderLayout());
		setSize(500, 100);
		JButton button = new JButton();
		//button.setBounds(100, 80, 165, 25);
		button.setText("Click here to play again");
		button.setSize(50, 50);
		JLabel label;
		if(white) {
			label=new JLabel("Checkmate! The white player has won the game! ");
		}
		else {
			label=new JLabel("Checkmate! The black player has won the game! ");

		}
		
		button.addActionListener(this);
		panel.add(label,BorderLayout.NORTH);
		panel.add(button,BorderLayout.SOUTH);
		add(panel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		SoloGame.getInstance().getRootPane().getParent().setVisible(false);
		ChessGameRepresentationSolo.recreateGame();
		Login login=new Login();
		login.showLoginPage();
		
	}
	
	
}
