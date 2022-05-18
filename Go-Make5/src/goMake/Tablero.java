package goMake;

public class Tablero {
	
	protected String[][] board= new String [17][17];
	protected String blueCross = Colors.BLUE + " x " + Colors.RESET;
	protected String greenCross = Colors.GREEN + " x " + Colors.RESET ;
	protected String yellowCircle = Colors.YELLOW + " o " + Colors.RESET;
	protected String greenCircle = Colors.GREEN + " o " + Colors.RESET;
	protected String empty = Colors.BOLD + " · " + Colors.RESET;
	protected String gameLastMove = "Z99";
	
		public void fillBoard() {
		
		int upLetter = 65;
		int lowLetter = 65;
		int leftNumber = 1;
		int rightNumber = 1;
		
		for (int i = 0; i < board.length; i++ ) {
			
			for (int j = 0; j < board[i].length; j++) {
				
				if(i==0 && j!=0 && j!=16) {
					board[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + ((char)upLetter) + " " + Colors.RESET;
					upLetter++;
				}
				else if(i==16 && j!=0 && j!=16) {
					board[i][j] =  Colors.WHITE_BACKGROUND + Colors.BLACK + " " + ((char)lowLetter) + " " + Colors.RESET;
					lowLetter++;
				}
				else if(j==0 && i!=0 && i!=16){
					if(leftNumber>=0 && leftNumber<10) {
						board[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + "0" + leftNumber + " " + Colors.RESET;
					}
					else {
						board[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + leftNumber + " " + Colors.RESET;
					}
					leftNumber++;
				}
				else if(j==16 && i!=0 && i!=16){
					if(rightNumber>=0 && rightNumber<10) {
						board[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + "0" + rightNumber + " " + Colors.RESET;
					}
					else {
						board[i][j] = Colors.WHITE_BACKGROUND + Colors.BLACK + " " + rightNumber + " " + Colors.RESET;
					}
					rightNumber++;
				}
				else if(i==0 && j==0 || i==16 && j==16 || i==16 && j==0 || i==0 && j==16) {
					board[i][j]= Colors.WHITE_BACKGROUND + "    " + Colors.RESET;
				}
				else {
					board[i][j] = empty;
				}
			}
			
		}
	}
		
		public void placeToken(int numero, String letra, String token) {
			board[numero][Integer.valueOf(letra.charAt(0))-64] = token;
		}
		public void printBoard() {
			String fila;
			for(int i = 0; i < board.length; i++) {
				fila="";
				for(int j = 0; j < board[i].length; j++) {
					if((board[i][j]==blueCross) && (j == Integer.valueOf(gameLastMove.charAt(0)-64)) && (i == Integer.valueOf(String.valueOf(gameLastMove.charAt(1))+String.valueOf(gameLastMove.charAt(2))))) {
						board[i][j] = greenCross;
					}
					else if(board[i][j]==greenCross && ((j != Integer.valueOf(String.valueOf(gameLastMove.charAt(1))+String.valueOf(gameLastMove.charAt(2))) || (i != Integer.valueOf(gameLastMove.charAt(0)-64))))) {
						board[i][j] = blueCross;
					}
					if((board[i][j]==yellowCircle) && (j == Integer.valueOf(gameLastMove.charAt(0)-64)) && (i == Integer.valueOf(String.valueOf(gameLastMove.charAt(1))+String.valueOf(gameLastMove.charAt(2))))) {
						board[i][j] = greenCircle;
					}
					else if((board[i][j]==greenCircle) && ((j != Integer.valueOf(gameLastMove.charAt(0)-64)) || (i != Integer.valueOf(String.valueOf(gameLastMove.charAt(1))+String.valueOf(gameLastMove.charAt(2)))))) {
						board[i][j] = yellowCircle;
					}
					fila += board[i][j];
				}
				System.out.println(fila);
			}
		}
		
		public boolean winCondition() {
			
			int y = 16 - (Integer.valueOf(String.valueOf(gameLastMove.charAt(1))+String.valueOf(gameLastMove.charAt(2))));
			int x = Integer.valueOf(gameLastMove.charAt(0))-64;
			int bIncreasing= y - x;
			int bDecreasing= y + x;
			
			int decreasingDiagonalCrossCount=0;
			int decreasingDiagonalCircleCount=0;
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bDecreasing == (16-i)+j) {
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
					if(bDecreasing == (16-i)+j) {
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
					if(bIncreasing == (16-i)-j) {
						if(board[i][j] == blueCross || board[i][j] == greenCross){
							increasingDiagonalCrossCount++;
							if(increasingDiagonalCrossCount==5) {
								return true;
							}

						}
						else {
							increasingDiagonalCrossCount=0;
						}
					}
				}
			}
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bIncreasing == (16-i)-j) {
						if(board[i][j]== yellowCircle || board[i][j] == greenCircle){
							increasingDiagonalCircleCount++;
							if(increasingDiagonalCircleCount==5) {
								return true;
							}

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
				if(board[16-y][i] == blueCross || board[16-y][i] == greenCross){
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
				if(board[16-y][i] == yellowCircle || board[16-y][i] == greenCircle){
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
