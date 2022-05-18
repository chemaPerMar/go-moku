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
	
//BYTE----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public byte readByte(){
		return keyboard.nextByte();
	}
	public byte readByteLessThan(byte upperBound) {
		byte num=keyboard.nextByte();
		return (num<upperBound)? num:readByteLessThan(upperBound);
	}
	public byte readByteLessOrEqualThan(byte upperBound) {
		byte num=keyboard.nextByte();
		return (num<=upperBound)? num:readByteLessOrEqualThan(upperBound);
	}
	public byte readByteGreaterThan(byte lowerBound) {
		byte num=keyboard.nextByte();
		return(num>lowerBound)?num:readByteGreaterThan(lowerBound);
	}
	public byte readByteGreaterOrEqualThan(byte lowerBound) {
		byte num=keyboard.nextByte();
		return(num>=lowerBound)?num:readByteGreaterOrEqualThan(lowerBound);
	}
	public byte readByteInRange(byte lowerBound,byte upperBound) {
		byte num=keyboard.nextByte();
		return(num>=lowerBound&&num<=upperBound)?num:readByteInRange(lowerBound, upperBound);
	}
	
//SHORT----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public short readShort() {
		return keyboard.nextShort();
	}
	public short readShortLessThan(short upperBound) {
		short num=keyboard.nextShort();
		return(num<upperBound)?num:readShortLessThan(upperBound);
	}
	public short readShortLessOrEqualThan(short upperBound) {
		short num=keyboard.nextShort();
		return(num<=upperBound)?num:readShortLessOrEqualThan(upperBound);
	}
	public short readShortGreaterThan(short lowerBound) {
		short num=keyboard.nextShort();
		return(num>lowerBound)?num:readShortGreaterThan(lowerBound);
	}
	public short readShortGreaterOrEqualThan(short lowerBound) {
		short num=keyboard.nextShort();
		return(num>=lowerBound)?num:readShortGreaterOrEqualThan(lowerBound);
	}
	public short readShortInRange(short lowerBound,short upperBound) {
		short num=keyboard.nextShort();
		return(num>=lowerBound&&num<=upperBound)?num:readShortInRange(lowerBound,upperBound);
	}
	
//INT----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public int readInt() {
		return keyboard.nextInt();
	}
	public int readIntLessThan(int upperBound) {
		int num=keyboard.nextInt();
		return (num<upperBound)?num:readIntLessThan(upperBound);
	}
	public int readIntLessOrEqualThan( int upperBound) {
		int num=keyboard.nextInt();
		return(num<=upperBound)?num:readIntLessOrEqualThan(upperBound);
	}
	public int readIntGreaterThan(int lowerBound ) {
		int num=keyboard.nextInt();
		return(num>lowerBound)?num:readIntGreaterThan(lowerBound);
	}
	public int readIntGreaterOrEqualThan(int lowerBound) {
		int num=keyboard.nextInt();
		return(num>=lowerBound)?num:readIntGreaterOrEqualThan(lowerBound);
	}
	public int readIntInRange(int lowerBound,int upperBound) {
		int num=keyboard.nextInt();
		return (num>=lowerBound&&num<=upperBound)?num:readIntInRange(lowerBound,upperBound);	}

//LONG----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public long readLong() {
		return keyboard.nextLong();
	}
	public long readLongLessThan(long upperBound) {
		long num=keyboard.nextLong();
		return(num<upperBound)?num:readLongLessThan(upperBound);
	}
	public long readLongLessOrEqualThan(long upperBound) {
		long num=keyboard.nextLong();
		return (num<=upperBound)?num:readLongLessOrEqualThan(upperBound);
	}
	public long readLongGreaterThan(long lowerBound) {
		long num=keyboard.nextLong();
		return(num>lowerBound)?num:readLongGreaterThan(lowerBound);
	}
	public long readLongGreaterOrEqualThan(long lowerBound) {
		long num=keyboard.nextLong();
		return (num>=lowerBound)?num:readLongGreaterOrEqualThan(lowerBound);
	}
	public long readLongInRange(long lowerBound, long upperBound) {
		long num=keyboard.nextLong();
		return (num>=lowerBound&&num<=upperBound)?num:readLongInRange(lowerBound,upperBound);
	}
	
//FLOAT----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public float readFloat(){
		return keyboard.nextFloat();
	}
	public float readFloatLessThan(float upperBound) {
		float num=keyboard.nextFloat();
		return(num<upperBound)?num:readFloatLessThan(upperBound);
	}
	public float readFloatLessOrEqualThan(float upperBound) {
		float num=keyboard.nextFloat();
		return (num<=upperBound)?num:readFloatLessOrEqualThan(upperBound);
	}
	public float readFloatGreaterThan(float lowerBound) {
		float num=keyboard.nextFloat();
		return(num>lowerBound)?num:readFloatGreaterThan(lowerBound);
	}
	public float readFloatGreaterOrEqualThan(float lowerBound) {
		float num=keyboard.nextFloat();
		return(num>=lowerBound)?num:readFloatGreaterOrEqualThan(lowerBound);
		}
	public float readFloatInRange(float lowerBound, float upperBound) {
		float num=keyboard.nextFloat();
		return(num>=lowerBound&&num<=upperBound)?num:readFloatInRange(lowerBound,upperBound);
	}
	
//DOUBLE----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public double readDouble() {
		return keyboard.nextDouble();
	}
	public double readDoubleLessThan(double upperBound) {
		double num=keyboard.nextDouble();
		return(num<upperBound)?num:readDoubleLessThan(upperBound);
	}
	public double readDoubleLessOrEqualThan(double upperBound) {
		double num=keyboard.nextDouble();
		return(num<=upperBound)?num:readDoubleLessOrEqualThan(upperBound);
		}
	public double readDoubleGreaterThan(double lowerBound) {
		double num=keyboard.nextDouble();
		return (num>lowerBound)?num:readDoubleGreaterThan(lowerBound);
	}
	public double readDoubleGreaterOrEqualThan(double lowerBound) {
		double num=keyboard.nextDouble();
		return (num>=lowerBound)?num:readDoubleGreaterOrEqualThan(lowerBound);
	}
	public double readDoubleInRange(double lowerBound, double upperBound) {
		double num=keyboard.nextDouble();
		return (num>=lowerBound&&num<=upperBound)?num:readDoubleInRange(lowerBound, upperBound);
	}
	
//CHAR Y STRING----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public char readChar() {
		cleanInput();
		System.out.println("Letra:");
		String userChar=keyboard.nextLine();
		return (userChar.length()<2&&userChar.length()>0)?userChar.charAt(0):readChar();
	}
	public char readChar(String validCharacters) {
		char userChar=readChar();
		for(int i=0;i<validCharacters.length();i++) {
			if(validCharacters.charAt(i)==userChar) {
				return userChar;
			}
		}
		return readChar(validCharacters);
	}
	public char readVowel() {
		return readChar("aeiouAEIOU");
	}
	public char readDigit() {
		return readChar("1234567890");
	}
	public String readTwoDigits() {
		cleanInput();
		System.out.println("Número:");
		String twoDigits = String.valueOf(keyboard.nextInt());
		if(twoDigits==null) {
			return readTwoDigits();
		}
		if(twoDigits.length()<2) {
			twoDigits="0"+twoDigits;
		}
		if(twoDigits.length()==2 && Integer.valueOf(twoDigits)<=15 && Integer.valueOf(twoDigits)>0) {
			return twoDigits;
		}
		System.out.println("Numero inválido, introduzca un número entre el 1 y el 15.");
		return readTwoDigits();
	}
	public char readLowerCase() {
		return readChar("abcdefghijklmnñopqrstuvwxyz");
	}
	public char readUpperCase() {
		return readChar("abcdefghijklmnñopqrstuvwxyz".toUpperCase());
	}
	public String readString() {
		cleanInput();
		return keyboard.nextLine();
	}
	public String readString(int maxLength) {
		String stringUser=readString();
		return(stringUser.length()<maxLength)?stringUser:readString(maxLength);
	}
	public boolean readBooleanUsingChar(char affirmativeValue) {
		return(Character.toUpperCase(readChar())==Character.toUpperCase(affirmativeValue))?true:false;
	}
	public boolean readBooleanUsingChar() {
		char affirmativeValue='s';
		return(readChar()==Character.toUpperCase(affirmativeValue))?true:false;
	}
	public boolean readBooleanUsingChar(char affirmativeValue,char negativeValue) {
		if(Character.toUpperCase(readChar())==Character.toUpperCase(affirmativeValue)) {
			return true;
		}
		return (Character.toUpperCase(readChar())==Character.toUpperCase(negativeValue))?false:readBooleanUsingChar(affirmativeValue,negativeValue);
	}
	public boolean readBooleanUsingInt() {
		return(readInt()==1)?true:false;
	}
	public boolean readBooleanUsingInt(int affirmativeValue,int negativeValue) {
		int num=readInt();
		if (num==affirmativeValue) {
			return true;
		}
		return(num==negativeValue)?false:readBooleanUsingInt(affirmativeValue,negativeValue);
	}
}
