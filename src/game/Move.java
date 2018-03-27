//Class that contains all 4 movements on the board//
package game;

public class Move {
	/*fromRow-The row to move from.
	*fromColumn-The column to move from.
	*toRow-The row to move to.
	*toColumn-The column to move to. */
	
	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
	}
	//Constructor//
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
	
	
}
