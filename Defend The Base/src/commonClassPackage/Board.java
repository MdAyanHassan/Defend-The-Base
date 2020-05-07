package commonClassPackage;

public class Board {
	public int rowSize;
	public int colSize;
	public String[][] board;
	Board(int rowSize, int colSize) {
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.board = new String[rowSize][colSize];
	}
	
	public void initalizeBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				board[i][j] = ". ";
			}
		}
	}
	public void displayBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}
