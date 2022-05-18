package goMake;

public class Tablero {
	
	private String[][] board= new String [17][17];
	
		public void fillBoard() {
		
		int upLetter = 65;
		int lowLetter = 65;
		int leftNumber = 1;
		int rightNumber = 1;
		
		for (int i = 0; i < getBoard().length; i++ ) {
			
			for (int j = 0; j < getBoard()[i].length; j++) {
				
				if(i==0 && j!=0 && j!=16) {
					getBoard()[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + ((char)upLetter) + " " + Colors.RESET;
					upLetter++;
				}
				else if(i==16 && j!=0 && j!=16) {
					getBoard()[i][j] =  Colors.WHITE_BACKGROUND + Colors.BLACK + " " + ((char)lowLetter) + " " + Colors.RESET;
					lowLetter++;
				}
				else if(j==0 && i!=0 && i!=16){
					if(leftNumber>=0 && leftNumber<10) {
						getBoard()[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + "0" + leftNumber + " " + Colors.RESET;
					}
					else {
						getBoard()[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + leftNumber + " " + Colors.RESET;
					}
					leftNumber++;
				}
				else if(j==16 && i!=0 && i!=16){
					if(rightNumber>=0 && rightNumber<10) {
						getBoard()[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + "0" + rightNumber + " " + Colors.RESET;
					}
					else {
						getBoard()[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + rightNumber + " " + Colors.RESET;
					}
					rightNumber++;
				}
				else if(i==0 && j==0 || i==16 && j==16 || i==16 && j==0 || i==0 && j==16) {
					getBoard()[i][j]= Colors.WHITE_BACKGROUND + "    " + Colors.RESET;
				}
				else {
					getBoard()[i][j] = Colors.BOLD + " · " + Colors.RESET;
				}
			}
			
		}
	}
		
		public void setToken(String numero, char letra, String token) {
			board[Integer.parseInt(numero)][Integer.valueOf(letra)-64] = token;
		}
		public void printBoard() {
			String fila;
			for(int i = 0; i < getBoard().length; i++) {
				fila="";
				for(int j = 0; j < getBoard()[i].length; j++) {
					fila += getBoard()[i][j];
				}
				System.out.println(fila);
			}
		}
		public String[][] getBoard() {
			return board;
		}
		public void setBoard(String[][] board) {
			this.board = board;
		}
	

}
