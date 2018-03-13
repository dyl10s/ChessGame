package game;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JOptionPane;

public class ChessModel implements IChessModel {
private IChessPiece[][] board;
private Player player;

public ChessModel() {
  
	//8x8 Chess board
	board = new IChessPiece[8][8];
	
	player = Player.WHITE;
	
	for (int x = 0; x < 8; x++){
		board[x][1] = new Pawn(player.BLACK);
		board[x][6] = new Pawn(player.WHITE);
	}
	
	board[1][0] = new Knight(player.BLACK);
	board[6][0] = new Knight(player.BLACK);
	board[2][0] = new Bishop(player.BLACK);
	board[5][0] = new Bishop(player.BLACK);
	board[4][0] = new King(player.BLACK);
	//board[0][0] = new Rook(player.BLACK);
	//board[7][0] = new Rook(player.BLACK);
	//board[3][0] = new Queen(player.BLACK);
	
	board[1][7] = new Knight(player.WHITE);
	board[6][7] = new Knight(player.WHITE);
	board[2][7] = new Bishop(player.WHITE);
	board[5][7] = new Bishop(player.WHITE);
	board[4][7] = new King(player.WHITE);
	//board[0][7] = new Rook(player.BLACK);
	//board[7][7] = new Rook(player.BLACK);	
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
	
	if (isComplete()){
		JOptionPane.showMessageDialog(null, "Winner");	
	}else if (inCheck(player)){
		JOptionPane.showMessageDialog(null, player.toString() + " is in check!");
	}
}

}
