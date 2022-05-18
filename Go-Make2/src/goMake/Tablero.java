package goMake;

public class Tablero {
	
	private String[][] board= new String [17][17];
	String blueCross = Colors.BLUE + " x " + Colors.RESET;
	String greenCross = Colors.GREEN + " x " + Colors.RESET ;
	String yellowCircle = Colors.YELLOW + " o " + Colors.RESET;
	String greenCircle = Colors.GREEN + " o " + Colors.RESET;
	String gameLastMove;
	
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
					if(board[i][j] == blueCross) {
						board[i][j] = greenCross;
					}
					else if(board[i][j] == yellowCircle) {
						board[i][j] = greenCircle;
					}
					fila += getBoard()[i][j];
				}
				System.out.println(fila);
			}
		}
		
		public boolean winCondition() {
			
			int y=Integer.valueOf(Integer.valueOf(gameLastMove.charAt(1)) + "" + Integer.valueOf(gameLastMove.charAt(2)));
			int x=Integer.valueOf(gameLastMove.charAt(0))-64;
			int b=y-x;
			
			int decreasingDiagonalCrossCount=0;
			int decreasingDiagonalCircleCount=0;
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(b == i+j) {
						if(board[i][j] == blueCross || board[i][j] == greenCross){
							decreasingDiagonalCrossCount++;
							if(decreasingDiagonalCrossCount==5) {
								return true;
							}
							i++;
							j=1;
						}
						else {
							decreasingDiagonalCrossCount=0;
						}
					}
				}
			}
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(b == i+j) {
						if(board[i][j] == yellowCircle || board[i][j] == greenCircle){
							decreasingDiagonalCircleCount++;
							if(decreasingDiagonalCircleCount==5) {
								return true;
							}
							i++;
							j=1;
						}
						else {
							decreasingDiagonalCircleCount=0;
						}
					}
				}
			}
			int increasingDiagonalCrossCount=0;
			int increasingDiagonalCircleCount=0;
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(b == i-j) {
						if(board[i][j] == blueCross || board[i][j] == greenCross){
							increasingDiagonalCrossCount++;
							if(increasingDiagonalCrossCount==5) {
								return true;
							}
							i++;
							j=1;
						}
						else {
							increasingDiagonalCrossCount=0;
						}
					}
				}
			}
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(b == i-j) {
						if(board[i][j] == yellowCircle || board[i][j] == greenCircle){
							increasingDiagonalCircleCount++;
							if(increasingDiagonalCircleCount==5) {
								return true;
							}
							i++;
							j=1;
						}
						else {
							increasingDiagonalCircleCount=0;
						}
					}
				}
			}
			int verticalCrossCount=0;
			int verticalCircleCount=0;
			for(int i = 1; i <= 15; i++) {
				if(board[i][x] == blueCross || board[i][x] == greenCross){
					verticalCrossCount++;
					if(verticalCrossCount==5) {
						return true;
					}
				}
				else {
					verticalCrossCount=0;
				}
			}
			for(int i = 1; i <= 15; i++) {
				if(board[i][x] == yellowCircle || board[i][x] == greenCircle){
					verticalCircleCount++;
					if(verticalCircleCount==5) {
						return true;
					}
				}
				else {
					verticalCircleCount=0;
				}
			}
			
			int horizontalCrossCount=0;
			int horizontalCircleCount=0;
			for(int i = 1; i <= 15; i++) {
				if(board[y][i] == blueCross || board[y][i] == greenCross){
					horizontalCrossCount++;
					if(horizontalCrossCount==5) {
						return true;
					}
				}
				else {
					horizontalCrossCount=0;
				}
			}
			for(int i = 1; i <= 15; i++) {
				if(board[y][i] == yellowCircle || board[y][i] == greenCircle){
					horizontalCircleCount++;
					if(horizontalCircleCount==5) {
						return true;
					}
				}
				else {
					horizontalCircleCount=0;
				}
			}
			return false;
		}
		
		public String[][] getBoard() {
			return board;
		}
		public void setBoard(String[][] board) {
			this.board = board;
		}
		public String getGameLastMove() {
			return gameLastMove;
		}
	

}
