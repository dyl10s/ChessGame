/*A class used to control all game logic of the game of 
chess during the users interactions with the game. */

package game;

import javax.swing.JOptionPane;

public class ChessModel implements IChessModel {
private IChessPiece[][] board;
private Player player;

public ChessModel() {
  
	//8x8 Chess board
	board = new IChessPiece[8][8];
	
	player = Player.WHITE;
	
	for (int x = 0; x < 8; x++){
		board[1][x] = new Pawn(Player.BLACK);
		board[6][x] = new Pawn(Player.WHITE);
	}
	
	board[0][1] = new Knight(Player.BLACK);
	board[0][6] = new Knight(Player.BLACK);
	board[0][2] = new Bishop(Player.BLACK);
	board[0][5] = new Bishop(Player.BLACK);
	board[0][4] = new King(Player.BLACK);
	board[0][0] = new Rook(Player.BLACK);
	board[0][7] = new Rook(Player.BLACK);
	//board[3][0] = new Queen(player.BLACK);
	
	board[7][1] = new Knight(Player.WHITE);
	board[7][6] = new Knight(Player.WHITE);
	board[7][2] = new Bishop(Player.WHITE);
	board[7][5] = new Bishop(Player.WHITE);
	board[7][4] = new King(Player.WHITE);
	board[7][0] = new Rook(Player.WHITE);
	board[7][7] = new Rook(Player.WHITE);	
	//board[3][0] = new Queen(player.WHITE);
	
}

public boolean isComplete() {
	
	boolean isComplete = false;
	boolean foundSafeSpace = false;
	
	IChessPiece King = null;
	int kingX = 0;
	int kingY = 0;
	
	for (int x = 0; x < board.length; x++){
		for (int y = 0; y < board[x].length; y++){
			
			if (board[x][y] != null){
				if(board[x][y].player().equals(player) && board[x][y].type() == "King"){
					King = board[x][y];
					kingX = x;
					kingY = y;
				}
			}
			
		}
	}
	
	for (int x = 0; x < board.length; x++){
		for (int y = 0; y < board[x].length; y++){
			Move m = new Move(kingX, kingY, x, y);
			
			if(King.isValidMove(m, board)){
				if (InCheckWithLocation(player, x, y) == false){
					foundSafeSpace = true;
				}
			}
			
		}	
	}
	
	
	if (foundSafeSpace == false){
		isComplete = true;
	}
	
	return isComplete;
}

public boolean isValidMove(Move move) {
	return board[move.fromRow][move.fromColumn].isValidMove(move, board);
}

public void move(Move move) {
	
	board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
	board[move.fromRow][move.fromColumn] = null;

}

public boolean InCheckWithLocation(Player p, int xLoc, int yLoc){
	
	Move move;
	
	boolean inCheck = false;
	
	
	for (int x = 0; x < board.length; x++){
		for (int y = 0; y < board[x].length; y++){
		
			if (board[x][y] != null){
				if (board[x][y].player().equals(p) == false){
					
					move = new Move(x, y, xLoc, yLoc);
					if (board[x][y].isValidMove(move, board)){
						inCheck = true;
					}
				
				}
			}
			
		}
	}
	
	return inCheck;

	
}

public boolean inCheck(Player p) {
	
	IChessPiece King;
	int kingX = 0;
	int kingY = 0;
	
	Move move;
	
	boolean inCheck = false;
	
	
	for (int x = 0; x < board.length; x++){
		for (int y = 0; y < board[x].length; y++){
			
			if (board[x][y] != null){
				if (board[x][y].player().equals(p) == true && board[x][y].type() == "King"){
					
					King = board[x][y];
					kingX = x;
					kingY = y;
					
				}	
			}
		}
	}
	
	for (int x = 0; x < board.length; x++){
		for (int y = 0; y < board[x].length; y++){
		
			if (board[x][y] != null){
				if (board[x][y].player().equals(p) == false){
					
					move = new Move(x, y, kingX, kingY);
					if (board[x][y].isValidMove(move, board)){
						inCheck = true;
					}
				
				}
			}
			
		}
	}
	
	return inCheck;
}

public Player currentPlayer() {
	return player;
}

public int numRows() {
	return board.length;
}

public int numColumns() {
	return board[0].length;
}

public IChessPiece pieceAt(int row, int column) {
	return board[row][column];
}

public void nextPlayer(){
	player = player.next();
	
	if (inCheck(player) && isComplete()){
		JOptionPane.showMessageDialog(null, "Winner");	
	}else if (inCheck(player)){
		JOptionPane.showMessageDialog(null, player.toString() + " is in check!");
	}
}

}
