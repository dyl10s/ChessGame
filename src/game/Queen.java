/*Class that creates the chess peice for the queen
The Queen is allowed to move both in straight and diagonal lines */

package game;

public class Queen extends ChessPiece {

  	public Queen (Player player) {
		super (player);

  	@Override
  	public String type () {
        return "Queen";
    
  	}
  		@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// --- Variable Declarations  -------------------------//

		/* The result of the computation. */
		boolean result = true;

		/* The lesser row value of the queen's position. */
		int lesser_row;
		
		/* The lesser col value of the queen's position. */
		int lesser_col;
		
		/* The greater row value of the queen's position. */
		int greater_row;
		
		/* The greater col value of the queen's position. */
		int greater_col;
		
		/* The position in the array to be pointing. */
		int pos;

		// --- Main Routine -----------------------------------//

		// Perform the generic background check.
		result = super.isValidMove(move, board);

		// continue with checking for piece specifics.
		if (result)
		{
		    	// Check if it is horizontal.
			if (move.fromRow == move.toRow)
			{
				// Determine which part of the move is greater.
				if ( move.fromColumn > move.toColumn )
				{
					greater_row = move.fromColumn;
					lesser_row = move.toColumn;
				}
				else
				{
					greater_row = move.toColumn;
					lesser_row = move.fromColumn;
				}
				
				// Find if there are any pieces in between the rows.
				for ( pos = lesser_row + 1; pos < greater_row; pos++ )
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
					greater_col = move.fromRow;
					lesser_col = move.toRow;
				}
				else
				{
					greater_col = move.toRow;
					lesser_col = move.fromRow;
				}
				
				// Find if there are any pieces in between the rows.
				for ( pos = lesser_col + 1; pos < greater_col; pos++ )
				{
					if (board [pos] [move.toColumn] != null)
					{	
						result = false;
						break;
					}
				}
			}
			
			// Check if it is on the positive slope diagonal.
			else if ((move.fromColumn - move.toColumn) ==
			-(move.fromRow - move.toRow))
			{
				// Determine which part of the move is greater.
				if ( move.fromRow > move.toRow )
				{
					greater_row = move.fromRow;
					lesser_row = move.toRow;
				}
				else
				{
					greater_row = move.toRow;
					lesser_row = move.fromRow;
				}
				
				// Determine which part of the move is greater.
				if ( move.fromColumn > move.toColumn )
				{
					greater_col = move.fromColumn;
					lesser_col = move.toColumn;
				}
				else
				{
					greater_col = move.toColumn;
					lesser_col = move.fromColumn;
				}
				
				
				// Find if there are any pieces in between the rows.
				for ( pos = 1; pos < (greater_row-lesser_row); pos++ )
				{
					if (board [greater_row - pos] [lesser_col + pos] != null)
					{	
						result = false;
						break;
					}
				}
			}
			
				else if ((move.fromColumn - move.toColumn) ==
			(move.fromRow - move.toRow))
			{
				// Determine which part of the move is greater.
				if ( move.fromRow > move.toRow )
				{
					greater_row = move.fromRow;
					lesser_row = move.toRow);
				}
				else
				{
					greater_row = move.toRow;
					lesser_row = move.fromRow;
				}
				
				// Determine which part of the move is greater.
				if ( move.fromColumn > move.toColumn)
				{
					greater_col = move.fromColumn;
					lesser_col = move.toColumn;
				}
				else
				{
					greater_col = move.toColumn;
					lesser_col = move.fromColumn;
				}
								
				// Find if there are any pieces in between the rows.
				for ( pos = 1; pos < (greater_row-lesser_row); pos++ )
				{
					if (board [lesser_row + pos] [lesser_col + pos] != null)
					{	
						result = false;
						break;
					}
				}
			}
			
			else
				result = false;
		}

				return result;

	}

}
