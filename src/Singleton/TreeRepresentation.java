package Singleton;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import MinMax.Node;

public class TreeRepresentation extends JFrame {
	private JPanel mainPanel;
	private static TreeRepresentation singleInstance;
	private DefaultMutableTreeNode mainTree;
	private Node myTree;
	private TreeRepresentation(Node myTree) {
		setSize(1000, 1000);
		this.mainPanel=new JPanel();
		add(this.mainPanel);
		setVisible(true);
		this.myTree=myTree;
		this.mainTree=new DefaultMutableTreeNode("RootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRootRoot");
		JTree tree=new JTree(this.mainTree);
		tree.setSize(500, 500);
		JScrollPane t=new JScrollPane(tree);
		t.setSize(500, 500);
		setLayout(new BorderLayout());
		tree.setBounds(new Rectangle(4, 31, 106, 129));
		this.mainPanel.add(t,BorderLayout.WEST);


	}
	public void buildTree(DefaultMutableTreeNode child, Node rootNode) {
		Iterator it=rootNode.getChildren().iterator();
		while(it.hasNext()) {
			Node a=(Node) it.next();
			DefaultMutableTreeNode next=new DefaultMutableTreeNode(a.getVal().toString());
			child.add(next);
			buildTree(next,a);
		}
	}
	
	public DefaultMutableTreeNode getMainTree() { 
		return mainTree;
	}
	public void setMainTree(DefaultMutableTreeNode mainTree) {
		this.mainTree = mainTree;
	}
	public static TreeRepresentation getInstance(Node m1) {
		if(singleInstance==null) {
			singleInstance=new TreeRepresentation(m1);
			return singleInstance;
		}
		
		return singleInstance;
		
	}
}
