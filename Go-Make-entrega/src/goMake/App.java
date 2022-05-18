package goMake;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		
		Partida game;
		boolean end = false;
		int num;
		
		while(end==false) {
			
			game = new Partida();
			game.gameStart();
			
			System.out.printf("¿Quieres jugar otra partida?\n1) Sí\n2) No");
			num=input.readIntInRange(1,2);
			
			switch(num) {
			
			case 1:
				break;
				
			case 2:
				end=true;
				
			}
		}
		
	}
}
