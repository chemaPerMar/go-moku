package goMake;

import java.util.Scanner;

public class Jugador {
	
	protected String name;
	protected String lastMove;
	protected String token;
	protected boolean isFirst;
	protected String bet;

	
	public void humanPlaceToken(Tablero tablero, Human player) {
			
			Scanner keyboard = new Scanner (System.in);
			ConsoleInput input = new ConsoleInput(keyboard);
			
			System.out.println(name+"introduzca las coordenadas de su próximo movimiento.");
			
			lastMove = String.valueOf(input.readChar("abcdefghijklmnoABCDEFGHIJKLMNO")).toUpperCase() + String.valueOf(input.readTwoDigits());
			
			String num=Integer.valueOf(lastMove.charAt(1)) + "" + Integer.valueOf(lastMove.charAt(2));
			char letter=lastMove.charAt(0);
			
			tablero.setToken(num,letter,token);
			tablero.printBoard();
			
	}
	
	public void botPlaceToken(Tablero tablero, Robot bot) {
		
		lastMove = bot.decisionMaking(tablero);
		
		String num=String.valueOf(lastMove.charAt(1)) + String.valueOf(lastMove.charAt(2));
		char letter=lastMove.charAt(0);
		
		tablero.setToken(num, letter, token);
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

	public String getBet() {
		return bet;
	}

	public void setBet(String bet) {
		this.bet = bet;
	}
}
