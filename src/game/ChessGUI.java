//A class that builds a JFrame, that contains a new ChessPanel object//

package game;

import javax.swing.JFrame;

public class ChessGUI {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		frame.add(panel);
		frame.setSize(600, 600);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}
