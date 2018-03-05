package game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
				}else{
					board[x][y].setText(model.pieceAt(x, y).type());	
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
