package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.http.client.ClientProtocolException;

import Singleton.Sock;
import Singleton.User;

public class FreePlayer extends JFrame implements ActionListener {
	private User user;
	private JPanel mainPanel;
	private JTable playersTable;
	private Thread thread;
	private DefaultTableModel model;
	private RefreshFreePlayerThread t1;
	public FreePlayer(User user) {
		this.user = user;
		// Configure
		setTitle("Choose a game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 500, 500);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {

			}
		});

		// Create panel
		this.mainPanel = new JPanel(new GridLayout());
		// Creating column name
		String[] columnName = { "Choose a game" }; 
		Object[][] data = null;
		// Getting list of free players
		try {
			data = Sock.getInstance().getAvailableGameplay();
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// create table
		this.playersTable = new JTable(data, columnName);
		this.playersTable.setBounds(10, 10, getSize().width, getSize().height);
		// Create custom model to disable editing
		this.model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}

		};
		model.setDataVector(data, columnName);
		model.addRow(new Object[] { ("Click here to create a new game") });
		this.playersTable.setRowHeight(30);
		this.playersTable.setModel(model);
		// Adding panel
		this.mainPanel.add(new JScrollPane(this.playersTable));
		add(this.mainPanel);
		createRefreshPlayerThread();
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void createRefreshPlayerThread() {
		this.t1=new RefreshFreePlayerThread(model, this.playersTable,this, this.user.getUsername());

		this.thread = new Thread(this.t1);
		this.thread.start();
		
	}

}
