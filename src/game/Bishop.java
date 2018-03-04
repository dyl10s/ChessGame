package game;

public class Bishop extends ChessPiece{

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}
	
    public boolean isValidMove(Move move,IChessPiece[][] board) {
        if (!super.isValidMove(move,board))
            return false;
        boolean valid = false;
        
        int changeX = Math.abs(move.fromRow - move.toRow);
        int changeY = Math.abs(move.fromColumn - move.toColumn);
        
        int xSign = changeX / Math.abs(changeX);
        int ySign = changeY / Math.abs(changeY);
        
        if (changeX == changeY){
        	
        	boolean inWay = false;
        	
        	for (int x = 0; x < changeX; x++){
        		
        		if(board[x * xSign + move.fromRow][x * ySign + move.fromColumn] != null){
        			inWay = true;
        		}
        			
        	}
        	
        	if (inWay == false && board[move.toRow][move.toColumn] == null){
        		valid = true;
        	}
        }
        
        return valid;
    }

}
