package goMake;

import java.util.Scanner;

public class ConsoleInput {
	
	private final Scanner keyboard;
	
	public ConsoleInput(Scanner keyboard) {
		this.keyboard=keyboard;
	}
	public void cleanInput() {
		keyboard.nextLine();
	}
	

	
//INT----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public int readIntInRange(int lowerBound,int upperBound) {
		int num=keyboard.nextInt();
		if(num>=lowerBound&&num<=upperBound) {
			cleanInput();
			return num;
		}
		return readIntInRange(lowerBound,upperBound);	}


//CHAR Y STRING----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean isValidChar(String userChar, String validCharacters) {
		for(int i=0;i<validCharacters.length();i++) {
			if(String.valueOf(validCharacters.charAt(i)).equals(userChar)) {
				return true;
			}
		}
		return false;
	}
	public boolean isValidNumber(String userNumber, String[] validNumbers) {
		for(int i=0;i<validNumbers.length;i++) {
			if(validNumbers[i].equals(userNumber)) {
				return true;
			}
		}
		return false;
	}
}