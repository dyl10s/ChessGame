/*A class used to control all game logic of the game of 
chess during the users interactions with the game. */

package game;

public class ChessModel implements IChessModel {
private IChessPiece[][] board;
private Player player;

public ChessModel() {
  
	//8x8 Chess board
	board = new IChessPiece[8][8];
	
	player = Player.WHITE;
	
	for (int x = 0; x < 8; x++){
		//board[x][1] = new Pawn(player.BLACK);
		//board[x][6] = new Pawn(player.WHITE);
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
 return false;
}

public boolean isValidMove(Move move) {
	return board[move.fromRow][move.fromColumn].isValidMove(move, board);
}

public void move(Move move) {
	
	board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
	board[move.fromRow][move.fromColumn] = null;

}

public boolean inCheck(Player p) {
 return false;
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
}

}
