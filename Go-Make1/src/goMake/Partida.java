package goMake;

import java.util.Random;

public class Partida {

	Tablero tablero;
	Jugador player1;
	Jugador player2;
	String gameLastMove;
	int turn = 1;
	boolean gameOver = false;
	
	public void gameStart() {
		
		tablero = new Tablero();
		
		while(gameOver==false) {
			if(turn%2 != 0) {
				player1.placeToken(tablero, player1);
				gameLastMove = player1.getLastMove();
			}
			else if(turn%2 == 0) {
				player2.placeToken(tablero, player2);
				gameLastMove = player2.getLastMove();
			}
			turn++;
		}
		if(turn%2 != 0) {
			victory(player1);
		}
		else if(turn%2 == 0) {
			victory(player2);
		}
	}
	public void victory(Jugador player) {
		System.out.println("Ha ganao " + player + ", un saludo jaja te me cuidas");
	}
	
	public void whoIsFirst() {
		Random randomFace = new Random();
				
				int faceCoin = randomFace.nextInt(2);
				
				if(faceCoin == 0)
				{
					System.out.println("Cara");
				}
				else
				{
					System.out.println("Cruz");
				}
	}
}
