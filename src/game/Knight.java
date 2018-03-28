package game;

public class Knight extends ChessPiece{

	public Knight(Player player) {
		super(player);
	}
	
	public String type(){
		return "Knight";
	}

	public boolean isValidMove(Move move,IChessPiece[][] board) {
		
		if (!super.isValidMove(move,board))
            return false;
        boolean valid = false;
		
        int changeY = Math.abs(move.fromRow - move.toRow);
        int changeX = Math.abs(move.fromColumn - move.toColumn);
        
        if (changeX == 2 && changeY == 1){
        	valid = true;
        }
        
        if (changeX == 1 && changeY == 2){
        	valid = true;
        }
        
        return valid;
		
	}
	
}
