package game;

public class Pawn extends ChessPiece {

	public boolean firstMove = true;
	
	public Pawn(Player player) {
		super(player);
	}

	@Override
	public String type() {
		return "Pawn";

	}

	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// --- Variable Declarations -------------------------//
		
		/* The result of the computation. */
		boolean result = false;

		/* The current row position of the pawn. */
		int row;

		/* The current column position of the pawn. */
		int col;

		/* The move to row position. */
		int toRow = move.toRow;

		/* The move to column position. */
		int toCol = move.toColumn;
		
		
		
		// --- Main Routine -----------------------------------//

		// Perform the generic background check.
		boolean validmove = super.isValidMove(move, board);

		// continue with checking for piece specifics.
		if (validmove) {
			// Get row position values.
			row = move.fromRow;
			col = move.fromColumn;
			toRow = move.toRow;
			toCol = move.toColumn;

			// Determine which direction we should be moving.
			if (player() == Player.WHITE) {
				
				if (row == 6) {
					firstMove = true;
				}
				
				// Check for a first move instance.
				if (firstMove && col == toCol && toRow == row - 2 && board[move.toRow][move.toColumn] == null) {
					result = true;
					// Make sure if the first move cleared that we turn it off.
					firstMove = false;
				}

				// If they called a north west move.
				else if (toRow == row - 1 && toCol == col - 1) {
					// Check that the opponent has a piece there.
					if (!(board[toRow][toCol] == null || board[toRow][toCol].player().equals(Player.WHITE)))
						result = true;
				}

				// If they called a north east move.
				else if (toRow == row - 1 && toCol == col + 1) {
					// Check that the opponent has a piece there.
					if (!(board[toRow][toCol] == null || board[toRow][toCol].player().equals(Player.WHITE)))
						result = true;
				}

				// If they called a due north move.
				else if (toRow == row - 1 && toCol == col && board[toRow][toCol] == null)
					result = true;
			}

			else {
				// -- Check what move call was performed. -- //

				if (row == 1) {
					firstMove = true;
				}
				
				// Check for a first move instance.
				if (firstMove && col == toCol && toRow == row + 2 && board[move.toRow][move.toColumn] == null) {
					result = true;
					// Make sure if the first move cleared that we turn it off.
					firstMove = false;
				}

				// If they called a north west move.
				else if (toRow == row + 1 && toCol == col - 1) {
					// Check that the opponent has a piece there.
					if (!(board[toRow][toCol] == null || board[toRow][toCol].player().equals(Player.BLACK)))
						result = true;
				}

				// If they called a north east move.
				else if (toRow == row + 1 && toCol == col + 1) {
					// Check that the opponent has a piece there.
					if (!(board[toRow][toCol] == null || board[toRow][toCol].player().equals(Player.BLACK)))
						result = true;
				}

				// If they called a due north move.
				else if (toRow == row + 1 && toCol == col && board[toRow][toCol] == null)
					result = true;
			}
		}
		return result;

	}
}
