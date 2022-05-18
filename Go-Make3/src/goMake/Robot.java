package goMake;

import java.util.Random;

public class Robot extends Jugador {
	
	public String decisionMaking(Tablero tablero) {
		
		String result="";
		String[][] board=tablero.getBoard();
		
		for(int i = 0; i < board.length && result==""; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == " · ") {
					result = (char)(j+64) + "" + i;
				}
			}
		}
		
		return result;
		
	}
	
	public void nameSelect() {
		
		Random random = new Random();
		
		int num=random.nextInt(5);
		String name = "";
		
		switch(num) {
		case 0: name="Lelouch";
		break;
		
		case 1: name="Schneizel";
		break;
		
		case 2: name="Cornelia";
		break;
		
		case 3: name="Chema";
		break;
		
		case 4: name="NeburOMG";
		break;
	}
		setName(name);
	}

}
