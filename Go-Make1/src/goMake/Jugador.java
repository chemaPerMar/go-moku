package goMake;

import java.util.Scanner;

public class Jugador {
	
	private String name;
	private String lastMove;
	private String token;
	private boolean isFirst;
	
	
	
	public void placeToken(Tablero tablero, Jugador player) {
		
		Scanner keyboard = new Scanner (System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		
		System.out.println(name+"introduzca las coordenadas de su próximo movimiento.");
		
		lastMove = String.valueOf(input.readChar("abcdefghijklmnoABCDEFGHIJKLMNO")).toUpperCase() + String.valueOf(input.readTwoDigits());
		
		String numero=String.valueOf(lastMove.charAt(1)) + String.valueOf(lastMove.charAt(2));
		char letra=lastMove.charAt(0);
		
		tablero.setToken(numero,letra,token);
		tablero.printBoard();
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public boolean isFirst() {
		return isFirst;
	}



	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}



	public String getLastMove() {
		return lastMove;
	}



	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}
}
