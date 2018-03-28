package game;

public class King extends ChessPiece{

	public King(Player player) {
		super(player);
	}


	public String type() {
		return "King";
	}

    public boolean isValidMove(Move move,IChessPiece[][] board) {
        if (!super.isValidMove(move,board))
            return false;
        boolean valid = false;
     
        int changeY = Math.abs(move.fromRow - move.toRow);
        int changeX = Math.abs(move.fromColumn - move.toColumn);
        
        if (changeX < 2 && changeY < 2){
        	valid = true;
        }
        
        return valid;
        
    }

}
