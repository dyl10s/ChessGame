/*Class that creates a piece for King. The king piece can only 
move in a single place at a time*/

package game;

public class King extends ChessPiece{
/*The primary constructor for the king piece. It creates a king
 for the given player specified. */
	public King(Player player) {
		super(player);
	}

//Return type of piece//
	public String type() {
		return "King";
	}

    public boolean isValidMove(Move move,IChessPiece[][] board) {
        if (!super.isValidMove(move,board))
            return false;
        boolean valid = false;
     
        int changeX = Math.abs(move.fromRow - move.toRow);
        int changeY = Math.abs(move.fromColumn - move.toColumn);
        
        if (changeX < 2 && changeY < 2){
        	valid = true;
        }
        
        return valid;
        
    }

}
