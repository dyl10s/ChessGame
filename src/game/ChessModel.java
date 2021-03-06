/*A class used to control all game logic of the game of 
chess during the users interactions with the game. */

package game;

import java.time.chrono.IsoChronology;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class ChessModel implements IChessModel {
	private IChessPiece[][] board;
	private Player player;

	public ChessModel() {

		// 8x8 Chess board
		board = new IChessPiece[8][8];

		player = Player.WHITE;

		for (int x = 0; x < 8; x++) {
			board[1][x] = new Pawn(Player.BLACK);
			board[6][x] = new Pawn(Player.WHITE);
		}

		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][0] = new Rook(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);

		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][0] = new Rook(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);

	}

	public boolean isComplete() {

		boolean isComplete = true;

		IChessPiece King = null;
		int kingX = 0;
		int kingY = 0;

		int newKingX = 0;
		int newKingY = 0;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				if (board[x][y] != null) {
					if (board[x][y].player().equals(player) && board[x][y].type() == "King") {
						King = board[x][y];
						kingX = x;
						kingY = y;
					}
				}

			}
		}

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				IChessPiece[][] origBoard = new IChessPiece[8][8];

				for (int i = 0; i < board.length; i++)
					for (int j = 0; j < board[i].length; j++)
						origBoard[i][j] = board[i][j];

				if (board[x][y] != null && board[x][y].player().equals(player)) {

					for (int xMove = 0; xMove < board.length; xMove++) {
						for (int yMove = 0; yMove < board[xMove].length; yMove++) {

							Move tempMove = new Move(x, y, xMove, yMove);

							if (board[x][y].isValidMove(tempMove, board)) {
								move(tempMove);
								if (inCheck(player) == false) {

									for (int i = 0; i < board.length; i++)
										for (int j = 0; j < board[i].length; j++)
											board[i][j] = origBoard[i][j];
									return false;
								}
							}

							for (int i = 0; i < board.length; i++)
								for (int j = 0; j < board[i].length; j++)
									board[i][j] = origBoard[i][j];

						}
					}

				}

			}
		}

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				Move tryMove = new Move(kingX, kingY, x, y);

				if (isValidMove(tryMove)) {
					if (inCheckOtherDirection(player, kingX, kingY, x - kingX, y - kingY) == false) {
						return false;
					}
				}

			}
		}

		return isComplete;
	}

	public boolean inCheckOtherDirection(Player p, int x, int y, int changeX, int changeY) {

		Move move;

		for (int xStep = 0; xStep < board.length; xStep++) {
			for (int yStep = 0; yStep < board[x].length; yStep++) {

				if (board[xStep][yStep] != null) {
					if (board[xStep][yStep].player().equals(p) == false) {

						move = new Move(xStep, yStep, x + changeX, y + changeY);
						if (board[xStep][yStep].isValidMove(move, board)) {
							return true;
						}

					}
				}

			}
		}

		return false;
	}

	public boolean isValidMove(Move move) {
		return board[move.fromRow][move.fromColumn].isValidMove(move, board);
	}

	public void move(Move move) {

		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;

	}

	public boolean inCheck(Player p) {

		IChessPiece King;
		int kingX = 0;
		int kingY = 0;

		Move move;

		boolean inCheck = false;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				if (board[x][y] != null) {
					if (board[x][y].player().equals(p) == true && board[x][y].type() == "King") {

						King = board[x][y];
						kingX = x;
						kingY = y;

					}
				}
			}
		}

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				if (board[x][y] != null) {
					if (board[x][y].player().equals(p) == false) {

						move = new Move(x, y, kingX, kingY);
						if (board[x][y].isValidMove(move, board)) {
							inCheck = true;
						}

					}
				}

			}
		}

		return inCheck;
	}

	public Player currentPlayer() {
		return player;
	}

	public int numRows() {
		return board.length;
	}

	public int numColumns() {
		return board[0].length;
	}

	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

public void runAI(Player p) {
	
	IChessPiece[][] origBoard = new IChessPiece[8][8];
	
	for(int i=0; i<board.length; i++)
		  for(int j=0; j<board[i].length; j++)
			  origBoard[i][j]=board[i][j];
	
	//Block Check
	if (inCheck(p)) {
		
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++){
				
				for (int moveX = 0; moveX < board.length; moveX++){
					for (int moveY = 0; moveY < board[moveX].length; moveY++){
						
						if (board[x][y] != null && board[x][y].player().equals(player)) {
							
							Move tempMove = new Move(x, y, moveX, moveY);
							
							if (isValidMove(tempMove)) {
								move(tempMove);
								if (inCheck(p) == false) {
									return;
								}else {
									for(int i=0; i<board.length; i++)
										  for(int j=0; j<board[i].length; j++)
											  board[i][j]=origBoard[i][j];
								}
							}
							
						}
						
					}
				}
				
			}
		}
		
	}
		
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++){
				
				for (int moveX = 0; moveX < board.length; moveX++){
					for (int moveY = 0; moveY < board[moveX].length; moveY++){
						
						if (board[x][y] != null && board[x][y].player().equals(player)) {
							Move tempMove = new Move(x, y, moveX, moveY);
							
							if (isValidMove(tempMove)) {
								move(tempMove);
								if (inCheck(p.next()) == true) {
									return;
								}else {
									for(int i=0; i<board.length; i++)
										  for(int j=0; j<board[i].length; j++)
											  board[i][j]=origBoard[i][j];
								}
							}
						}
						
					}
				}
				
			}
		}
		
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[x].length; y++){
				
				for (int moveX = 0; moveX < board.length; moveX++){
					for (int moveY = 0; moveY < board[moveX].length; moveY++){
						
						if (board[x][y] != null && board[x][y].player().equals(player)) {
							int startingCount = countColors(p.next());
							
							Move tempMove = new Move(x, y, moveX, moveY);
							
							if (isValidMove(tempMove)) {
								move(tempMove);
							
							if (startingCount > countColors(p.next()) && inCheck(player) == false) {
									return;
							}
							
							for(int i=0; i<board.length; i++)
								  for(int j=0; j<board[i].length; j++)
									  board[i][j]=origBoard[i][j];
							
						}
						
					}
				}
				
			}
		}
		}
		
		boolean foundPeice = false;
		boolean movedPeice = false;
		int pX = 0;
		int pY = 0;
		int attemptCounter = 0;
		
		while (foundPeice == false) {
			attemptCounter++;
			pX = (int) Math.round(Math.random() * 7);
			pY = (int) Math.round(Math.random() * 7);
			
			if (board[pX][pY] != null && board[pX][pY].player().equals(player)) {
				
				for(int i=0; i<board.length; i++) {
					  for(int j=0; j<board[i].length; j++) {
						  if (isValidMove(new Move(pX, pY, i, j))) {
								foundPeice = true;
						  }
					  }
				}
						  
			}
		
		while (movedPeice == false && foundPeice == true) {
			
			int mX = (int) Math.round(Math.random() * 7);
			int mY = (int) Math.round(Math.random() * 7);
			Move randomMove = new Move(pX, pY, mX, mY);
			
			if (isValidMove(randomMove)) {
				move(randomMove);
				if (inCheck(player) || attemptCounter > 10000) {
					for(int i=0; i<board.length; i++)
						  for(int j=0; j<board[i].length; j++)
							  board[i][j]=origBoard[i][j];
					foundPeice = false;
					
				}else {
					return;
				}
			}
			
		}
		
	}
	
}

	public int countColors(Player p) {

		int count = 0;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null) {
					if (board[x][y].player().equals(p)) {
						count += 1;
					}
				}
			}
		}

		return count;

	}

	public void nextPlayer() {
		
		if (inCheck(player) && isComplete()) {
			JOptionPane.showMessageDialog(null, player.next().toString() + " is the Winner");
			System.exit(0);
		}else if(inCheck(player)){
			JOptionPane.showMessageDialog(null, player.next().toString() + " is the Winner");
			System.exit(0);
		}
		
		player = player.next();

		if (inCheck(player) && isComplete()) {
			JOptionPane.showMessageDialog(null, player.toString() + " is the Winner");
			System.exit(0);
		} else if (inCheck(player)) {
			JOptionPane.showMessageDialog(null, player.toString() + " is in check!");
		}
		
	}

}
