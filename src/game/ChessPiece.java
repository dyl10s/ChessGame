package game;

public abstract class ChessPiece implements IChessPiece
{
   
    private Player owner;

    /**
     * Constructor for objects of class ChessPiece
     */
    public ChessPiece(Player player)
    {
        // initialise instance variables
        this.owner = player;
    }

    public abstract String type();
    
    public Player player() {
        return owner;
    }
    
    public boolean isValidMove(Move move,IChessPiece[][] board) {
       if (move.fromRow == move.toRow && move.fromColumn == move.toColumn) {
           return false;
        }
        
        if ( this != board[move.fromRow][move.fromColumn] ) {
            return false;
        }
        
        if ( board[move.toRow][move.toColumn].player() == owner ) {
            return false;
        }
        
        return true;
        
    }
}
