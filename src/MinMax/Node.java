package MinMax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ChessGameRepresentation.ChessGameRepresentationSolo;
import Game.Board.Pieces.PieceSolo;
public  class Node{
	Action val;
    List<Node> children = new LinkedList<>();
    public Node() {
    	
    }
    public void addChild(Node child) {
    	this.children.add(child);
    }
    public Node(Action data){
        this.val = data;
        this.children=children;
    }
 

    
    public Action getVal() {
		return val;
	}



	public void setVal(Action val) {
		this.val = val;
	}



	public List<Node> getChildren() {
		return children;
	}



	public void setChildren(List<Node> children) {
		this.children = children;
	}


}