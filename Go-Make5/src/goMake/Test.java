package goMake;

import java.util.Random;
import java.util.Scanner;


public class Test {

	
	public static void main(String[] args) {
		/*
		
		printBoard(tablero);
		System.out.println(gameLastMove+" "+letter+" "+num);
		
		Tablero tablero=new Tablero();
		fillBoard(tablero);
		tablero.printBoard();
		if(winCondition(tablero)==true) {
			System.out.println("ganao");
		}
		else {
			System.out.println("perdio");
		}
		int letra =65;
		char letter=(char)65;
		System.out.println(letter);
		*/
		
		Partida game = new Partida();
		game.gameStart();
	}
}
