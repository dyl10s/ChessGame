/* Class for the chest piece: Rook. 
The rook is only allowed to move in linear directions */
package game;


public class Rook extends ChessPiece {
	
  	public Rook (Player player) {
		super(player);
		
	}
  	
  	@Override
  	public String type () {
    	return "Rook";
    
  	}
 
	@Override
	public boolean isValidMove(Move move, IChessPiece[ ][ ] board) {
		

		boolean result = true;
		
		/* The lesser value of the rook's position. */
		int lesser;
		
		/* The greater value of the rook's position. */
		int greater;
		
		/* The position in the array to be pointing. */
		int pos;

		// Reform the generic background check.
		result = super.isValidMove(move, board);
		
		// continue with checking for piece specifics.
		if (result)
		{
			// Check if it is horizontal.
			if (move.fromRow == move.toRow) 
			{
				// Determine which part of the move is greater.
				if ( move.fromColumn > move.toColumn) 
				{
					greater = move.fromColumn;
					lesser = move.toColumn;
				}
				else
				{
					greater = move.toColumn;
					lesser = move.fromColumn;
				}
				
				//  Find if there are any pieces in between the rows.
				for ( pos = lesser + 1; pos < greater; pos++ )
				{
					if (board [move.toRow] [pos] != null) 
					{	
						result = false;
						break;
					}
				}
			}
			
			// Check if it is vertical.
			else if (move.fromColumn == move.toColumn) 
			{
				// Determine which part of the move is greater.
				if ( move.fromRow > move.toRow) 
				{
					greater = move.fromRow;
					lesser = move.toRow;				}
				else
				{
					greater = move.toRow;
					lesser = move.fromRow;
				}
				
				//  Find if there are any pieces in between the rows.
				for ( pos = lesser + 1; pos < greater; pos++ )
				{
					if (board [pos] [move.toColumn] != null) 
					{	
						result = false;
						break;
					}
				}
			}
			
			else 
				result = false;
		}
		
		// Return the result of the operation.
		return result;
		
	}
	

}
