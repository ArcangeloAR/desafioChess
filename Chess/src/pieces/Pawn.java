package pieces;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.Cell;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece{
	
	//COnstructors
	public Pawn(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//Move Function Overridden
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//Pawn can move only one step except the first chance when it may move 2 steps
		//It can move in a diagonal fashion only for attacking a piece of opposite color
		//It cannot move backward or move forward to attact a piece
		
		possiblemoves.clear();
		if(getcolor()==0)
		{
			if(x==0)
				return possiblemoves;
			if(state[x-1][y].getpiece()==null)
			{
				possiblemoves.add(state[x-1][y]);
				if(x==6)
				{
					if(state[4][y].getpiece()==null)
						possiblemoves.add(state[4][y]);
				}
			}
			if((y>0)&&(state[x-1][y-1].getpiece()!=null)&&(state[x-1][y-1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x-1][y-1]);
			if((y<7)&&(state[x-1][y+1].getpiece()!=null)&&(state[x-1][y+1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x-1][y+1]);
		}
		else
		{
			if(x==8)
				return possiblemoves;
			if(state[x+1][y].getpiece()==null)
			{
				possiblemoves.add(state[x+1][y]);
				if(x==1)
				{
					if(state[3][y].getpiece()==null)
						possiblemoves.add(state[3][y]);
				}
			}
			if((y>0)&&(state[x+1][y-1].getpiece()!=null)&&(state[x+1][y-1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x+1][y-1]);
			if((y<7)&&(state[x+1][y+1].getpiece()!=null)&&(state[x+1][y+1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x+1][y+1]);
		}
		return possiblemoves;
	}
	
	public void promote(final Cell c, JFrame frame) 
	{
		final JDialog dialog = new JDialog(frame, "Promoção", true);
		JPanel panel = new JPanel();
		final JComboBox<String> combo = new JComboBox<String>(new String[]{"Rook", "Knight", "Bishop", "Queen"});
		Button confirm = new Button("Confirm");
		confirm.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    if(combo.getSelectedItem().toString() == "Rook") {
			    	c.setPiece(new Rook(getId()+"Rook", getcolor() == 0 ? "White_Rook.png" : "Black_Rook.png", getcolor()));
			    } else if(combo.getSelectedItem().toString() == "Knight") {
			    	c.setPiece(new Knight(getId()+"Knight", getcolor() == 0 ? "White_Knight.png" : "Black_Knight.png", getcolor()));
			    } else if(combo.getSelectedItem().toString() == "Bishop") {
			    	c.setPiece(new Bishop(getId()+"Bishop", getcolor() == 0 ? "White_Bishop.png" : "Black_Bishop.png", getcolor()));
			    } else if(combo.getSelectedItem().toString() == "Queen") {
			    	c.setPiece(new Queen(getId()+"Queen", getcolor() == 0 ? "White_Queen.png" : "Black_Queen.png", getcolor()));
			    }
			    dialog.setVisible(false);
			  } 
		} );
		panel.add(combo);
		panel.add(confirm);
		dialog.getContentPane().add(panel);
		dialog.pack();
		dialog.setVisible(true);
		
	}
	
}
