package goMake;

import java.util.Scanner;

public class Jugador {
	
	protected String name;
	protected String lastMove="A01";
	protected String token;
	protected boolean isFirst;
	protected String bet;

	
	public void humanPlaceToken(Tablero tablero, Human player) {
			
			Scanner keyboard = new Scanner (System.in);
			ConsoleInput input = new ConsoleInput(keyboard);
			
			System.out.println("Introduzca las coordenadas de su próximo movimiento:");
			
			lastMove = validMove(tablero);
			
			int num=Integer.valueOf(String.valueOf(lastMove.charAt(1))+String.valueOf(lastMove.charAt(2)));
			String letter=String.valueOf(lastMove.charAt(0)).toUpperCase();
			
			tablero.placeToken(num,letter,token);
			
	}
	
	public void botPlaceToken(Tablero tablero, Robot bot) {
		
		lastMove = bot.decisionMaking(tablero);
		
		int num=Integer.valueOf(String.valueOf(lastMove.charAt(1))+String.valueOf(lastMove.charAt(2)));
		String letter=String.valueOf(lastMove.charAt(0)).toUpperCase();
		
		tablero.placeToken(num, letter, token);

	}
	
	public String validMove(Tablero tablero) {
		
		Scanner keyboard= new Scanner(System.in);
		
		String coord=keyboard.nextLine();
		
		if(coord.length()>3) {
			System.out.println(Colors.RED+"Coordenada inválida, la coordenada se compone por letra + número."+Colors.RESET);
			System.out.println(Colors.RED+"Solo son válidos los valores que se ven en el tablero"+Colors.RESET);
			return validMove(tablero);
		}
		if(coord.length()==2) {
			coord=coord.charAt(0)+"0"+coord.charAt(1);
		}
	
		coord=coord.toUpperCase();
		int num=Integer.valueOf(String.valueOf(coord.charAt(1))+String.valueOf(coord.charAt(2)));
		int letter=Integer.valueOf(coord.charAt(0))-64;
		
		
		
		if(!(((letter <= 15) && (letter > 0)) && ((num <= 15) && (num > 0))))  {
			System.out.println(Colors.RED+"Coordenada inválida, la coordenada se compone por letra + número."+Colors.RESET);
			System.out.println(Colors.RED+"Solo son válidos los valores que se ven en el tablero"+Colors.RESET);
			return validMove(tablero);
		}
		else if(!(tablero.getBoard()[num][letter].equals(tablero.empty))) {
			System.out.println(Colors.RED+"Coordenada inválida, aquí ya hay una ficha."+Colors.RESET);
			return validMove(tablero);
		}
		else {
			return coord;
		}
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

	public void setBet(String b) {
		this.bet = b;
	}
}
