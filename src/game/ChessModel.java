package game;

public class ChessModel implements IChessModel {
private IChessPiece[][] board;
private Player player;

public ChessModel() {
  
	//8x8 Chess board
	board = new IChessPiece[8][8];
	board[5][5] = new Knight(player.BLACK);
	board[1][3] = new Knight(player.WHITE);
	board[1][7] = new Bishop(player.WHITE);
	board[1][1] = new King(player.WHITE);
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
// add other public or helper methods as needed
}
