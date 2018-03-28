/*
Presents the GUI, listens and reacts to user interaction
with the chess board. Checks for valid moves, moves the pieces,
finds the next player, and announces game status to the user
*/

package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//Constructor that creates the board and creates the panel//
@SuppressWarnings("serial")
public class ChessPanel extends JPanel {

	private JButton[][] board;
	private ChessModel model;
	
	boolean hasSelected = false;
	Move m = new Move();
	
	GridBagLayout gbLayout = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
	// declare other instance variables as needed
	private ButtonListener buttonListener = new ButtonListener();

	public ChessPanel() {

		
		this.setSize(600, 600);
		
		this.setLayout(gbLayout);
		//New ChessModel object//
		model = new ChessModel();
		board = new JButton[model.numRows()][model.numColumns()];

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				c.gridx = x;
				c.gridy = y;
				
				this.board[x][y] = new JButton(x + " , " + y);
				board[x][y].setPreferredSize(new Dimension(75, 75));
				this.add(board[x][y], c);
				board[x][y].addActionListener(buttonListener);
			}
		}

		displayBoard();

	}

	// method that updates the board
	private void displayBoard() {
		
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (model.pieceAt(x, y) == null){
					board[x][y].setText("");
					board[x][y].setIcon(null);
				}else{
					
					String imgName1 = model.pieceAt(x, y).type();
					String imgName2 = "";
					
					if (model.pieceAt(x, y).player() == Player.BLACK){
						imgName2 = "_Black";
					}else{
						imgName2 = "_White";
					}
					
					board[x][y].setIcon(new ImageIcon(imgName1 + imgName2 + ".png"));
					board[x][y].setText("");
					
				}
		
				if (x % 2 == 1 && y % 2 == 1){
					board[x][y].setBackground(Color.gray);
				}else if (x % 2 == 0 && y % 2 == 0){
					board[x][y].setBackground(Color.gray);
				}else{
					board[x][y].setBackground(Color.lightGray);
				}
				
			}
		}
		
	}

	// add other helper methods as needed
	// inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			for (int x = 0; x < board.length; x++) {
				for (int y = 0; y < board[x].length; y++) {
					
					if (event.getSource() == board[x][y]){
						
						if (hasSelected == false){
							if (model.pieceAt(x, y) != null && model.currentPlayer() == model.pieceAt(x, y).player()){
								m.fromRow = x;
								m.fromColumn = y;
								hasSelected = true;
							}
						}else{
							m.toRow = x;
							m.toColumn = y;
							hasSelected = false;
							if (model.isValidMove(m)){
								model.move(m);
								model.nextPlayer();
							}
						}
						
						displayBoard();
					}
					
				}
			}
			
		}
	}
}
