package game;

public class Bishop extends ChessPiece{

	// The primary constructor for the bishop peice. It creates a bishop//
	 for the given player specified.
	public Bishop(Player player) {
		super(player);
	}
	//Returns the type of chess piece//
  		public String type() {
		return "Bishop";
	}
	//Validates that the move being chosen is accepted//
    public boolean isValidMove(Move move,IChessPiece[][] board) {
        if (!super.isValidMove(move,board))
            return false;
        boolean valid = false;
        
        int changeY = Math.abs(move.fromRow - move.toRow);
        int changeX = Math.abs(move.fromColumn - move.toColumn);
        
        if(Math.abs(changeX) == 0 || Math.abs(changeY) == 0)
        {
        	return false;
        }
        
        int xSign = -(move.fromRow - move.toRow) / Math.abs(changeX);
        int ySign = -(move.fromColumn - move.toColumn) / Math.abs(changeY);
        
        
        if (changeX == changeY){
        	
        	boolean inWay = false;
        	
        	for (int x = 1; x < changeX; x++){
        		
        		if(board[x * xSign + move.fromRow][x * ySign + move.fromColumn] != null){
        			inWay = true;
        		}
        			
        	}
        	
        	if (inWay == false){
        		valid = true;
        	}
        }
        
        return valid;
    }

}
