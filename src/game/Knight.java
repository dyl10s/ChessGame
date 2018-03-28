/*Class that creates the game piece for Knight.
The Knight is allowed to move only in L shaped paterns across the game board */
package game;

public class Knight extends ChessPiece{
	//Creates a Knight for specificed player//
	public Knight(Player player) {
		super(player);
	}
	//Returns the piece type//
	public String type(){
		return "Knight";
	}
	//Method to verify the requested move is valid//
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
