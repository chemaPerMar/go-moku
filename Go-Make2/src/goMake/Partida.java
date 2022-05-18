package goMake;

import java.util.Random;
import java.util.Scanner;

public class Partida {

	Tablero tablero;
	Jugador player1;
	Jugador player2;
	Jugador a;
	Jugador b;
	int turn = 1;
	boolean gameOver = false;
	
	public void gameStart() {
		
		tablero = new Tablero();
		tablero.fillBoard();
		champSelect();
		whoIsFirst();
		tablero.printBoard();
		
		while(gameOver==false) {
			if(turn%2 != 0) {
				
				System.out.printf("Turno de %s",player1.getName());
				
				if(player1 instanceof Human) {
					player1.humanPlaceToken(tablero, (Human) player1);
					tablero.gameLastMove = player1.getLastMove();
				}
				else if(player1 instanceof Robot) {
					player1.botPlaceToken(tablero, (Robot) player1);
					tablero.gameLastMove = player1.getLastMove();
				}
				
			}
			else if(turn%2 == 0) {
				
				System.out.printf("Turno de %s",player2.getName());
				
				if(player2 instanceof Human) {
					player2.humanPlaceToken(tablero, (Human) player2);
					tablero.gameLastMove = player2.getLastMove();
				}
				else if(player2 instanceof Robot){
					player2.botPlaceToken(tablero, (Robot) player2);
					tablero.gameLastMove = player2.getLastMove();
				}
				
			}
			gameOver = tablero.winCondition();
			turn++;
		}
		if(turn%2 == 0) {
			victory(player1);
		}
		else if(turn%2 != 0) {
			victory(player2);
		}
	}
	public void victory(Jugador player) {
		System.out.println("Ha ganao " + player + ", un saludo jaja te me cuidas");
	}
	
	public void champSelect() {
		
		Scanner keyboard = new Scanner(System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		
		System.out.println("Decida el tipo de partida");
		System.out.println("1) Humano  vs  Humano");
		System.out.println("2) Máquina vs  Máquina");
		System.out.println("3) Humano  vs  Máquina");
		
		int game = input.readIntInRange(1,3);
		
		switch(game) {
			case 1:
				a = new Human();
				a.setName(input.readString());
				b = new Human();
				b.setName(input.readString());
				
				break;
			case 2:
				a = new Robot();
				((Robot) a).nameSelect();
				b = new Robot();
				((Robot) b).nameSelect();
				break;
			case 3:
				a = new Human();
				a.setName(input.readString());
				b = new Robot();
				((Robot) b).nameSelect();
		}
		
	}
	
	public void whoIsFirst() {
		
		Random randomFace = new Random();
		Scanner keyboard = new Scanner(System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		String cara = "Cara";
		String cruz = "Cruz";
				
		int faceCoin = randomFace.nextInt(2);
				
		System.out.println("El primer turno será decidido a cara o cruz");
		System.out.println("¿Quién apuesta por cara?");
		System.out.printf("1) %s\n2) %s",a.getName(),b.getName());
		int mainDecision = input.readIntInRange(1,2);
				
		switch(mainDecision) {
			case 1:
				a.setBet(cara);
				b.setBet(cruz);
			case 2:
				a.setBet(cruz);
				b.setBet(cara);
		}
				
		if(faceCoin == 0){
			
			System.out.println("Cara");
			if(a.getBet()==cara) {
				player1=a;
				player2=b;
			}
			else {
				player1=b;
				player2=a;
			}
		}
		else{
			System.out.println("Cruz");
			if(a.getBet()==cruz) {
				player1=a;
				player2=b;
			}
			else {
				player1=b;
				player2=a;
			}
		}
		giveTokens(tablero);
	}
	
	public void giveTokens(Tablero tablero) {
		player1.setToken(tablero.greenCross);
		player2.setToken(tablero.greenCircle);
	}
}
