package goMake;

import java.util.Random;

public class Robot extends Jugador {
	
	public String decisionMaking(Tablero tablero) {
		
		String result="";
		result=tryWin(tablero);
		if(result!="") {
			return result;
		}
		result=defendCloseWin(tablero);
		if(result!="") {
			return result;
		}
		result=defend(tablero);
		if(result!="") {
			return result;
		}
		result=attack(tablero);
		if(result!="") {
			return result;
		}
		result=startAttack(tablero);
		if(result!="") {
			return result;
		}
		
		return randomAttack(tablero);
		
	}
	public String tryWin(Tablero tablero) {
		
		int y = 16 - (Integer.valueOf(String.valueOf(lastMove.charAt(1))+String.valueOf(lastMove.charAt(2))));
		int x = Integer.valueOf(lastMove.charAt(0))-64;
		int bIncreasing= y - x;
		int bDecreasing= y + x;
		int upperX=15;
		int upperY=15;
		int emptyCount=0;
		int emptyX=0;
		int emptyY=0;
		
		if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64]==tablero.greenCircle) { 
			
			int decreasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bDecreasing == (16-i)+j) {
						if(tablero.board[i][j] == tablero.blueCross){		
							decreasingDiagonalCrossCount++;
							if(decreasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(decreasingDiagonalCrossCount==4) {
								if(emptyCount==1) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if(tablero.board[upperY-1][upperX-1]==tablero.empty) {
									return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j+1]==tablero.empty) {
									return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								decreasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							decreasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int increasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bIncreasing == (16-i)-j) {
						if(tablero.board[i][j] == tablero.blueCross){
							increasingDiagonalCrossCount++;
							if(increasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(increasingDiagonalCrossCount==4) {
								if(emptyCount==1) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if(tablero.board[upperY-1][upperX+1]==tablero.empty) {
									return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j-1]==tablero.empty) {
									return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								increasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							increasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int verticalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[i][x] == tablero.blueCross){
					verticalCrossCount++;
					if(verticalCrossCount==1) {
						upperX=x;
						upperY=i;
					}
					if(verticalCrossCount==4) {
						if(emptyCount==1) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if(tablero.board[upperY-1][upperX]==tablero.empty) {
							return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
						}
						else if(tablero.board[i+1][x]==tablero.empty) {
							return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
						}
					}
				}
				else if(tablero.board[i][x] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						verticalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					verticalCrossCount=0;
					emptyCount=0;
				}
			}
			emptyCount=0;
			
			int horizontalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[16-y][i] == tablero.blueCross){
					horizontalCrossCount++;
					if(horizontalCrossCount==1) {
						upperX=i;
						upperY=16-y;
					}
					else if(horizontalCrossCount==4) {
						if(emptyCount==1) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if(tablero.board[16-y][upperX-1]==tablero.empty) {
							return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
						}
						else if(tablero.board[16-y][i+1]==tablero.empty) {
							return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
						}
					}
					
				}
				else if(tablero.board[i][x] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						horizontalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					horizontalCrossCount=0;
					emptyCount=0;
				}
			}
		}
		else if(tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64]==tablero.greenCross){
			
			int decreasingDiagonalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					for(int j = 1; j<=15; j++) {
						if(bDecreasing == (16-i)+j) {
							if(tablero.board[i][j] == tablero.yellowCircle){		
								decreasingDiagonalCircleCount++;
								if(decreasingDiagonalCircleCount==1) {
									upperX=j;
									upperY=i;
								}
								if(decreasingDiagonalCircleCount==4) {
									if(emptyCount==1) {
										return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
									}
									else if(tablero.board[upperY-1][upperX-1]==tablero.empty) {
										return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
									}
									else if(tablero.board[i+1][j+1]==tablero.empty) {
										return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
									}
								}
								i++;
								j=1;
							}
							else if(tablero.board[i][j] == tablero.empty) {
								emptyCount++;
								emptyX=j;
								emptyY=i;
								if(emptyCount>1) {
									decreasingDiagonalCircleCount=0;
									emptyCount=0;
								}
							}
							else {
								decreasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
					}
				}
				emptyCount=0;
				
				int increasingDiagonalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					for(int j = 1; j<=15; j++) {
						if(bIncreasing == (16-i)-j) {
							if(tablero.board[i][j] == tablero.yellowCircle){
								increasingDiagonalCircleCount++;
								if(increasingDiagonalCircleCount==1) {
									upperX=j;
									upperY=i;
								}
								if(increasingDiagonalCircleCount==4) {
									if(emptyCount==1) {
										return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
									}
									else if(tablero.board[upperY-1][upperX+1]==tablero.empty) {
										return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
									}
									else if(tablero.board[i+1][j-1]==tablero.empty) {
										return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
									}
								}
								i++;
								j=1;
							}
							else if(tablero.board[i][j] == tablero.empty) {
								emptyCount++;
								emptyX=j;
								emptyY=i;
								if(emptyCount>1) {
									increasingDiagonalCircleCount=0;
									emptyCount=0;
								}
							}
							else {
								increasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
					}
				}
				emptyCount=0;
				
				int verticalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					if(tablero.board[i][x] == tablero.yellowCircle){
						verticalCircleCount++;
						if(verticalCircleCount==1) {
							upperX=x;
							upperY=i;
						}
						if(verticalCircleCount==4) {
							if(emptyCount==1) {
								return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
							}
							else if(tablero.board[upperY-1][upperX]==tablero.empty) {
								return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
							}
							else if(tablero.board[i+1][x]==tablero.empty) {
								return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
							}
						}
					}
					else if(tablero.board[i][x] == tablero.empty) {
						emptyCount++;
						emptyX=x;
						emptyY=i;
						if(emptyCount>1) {
							verticalCircleCount=0;
							emptyCount=0;
						}
					}
					else {
						verticalCircleCount=0;
						emptyCount=0;
					}
				}
				emptyCount=0;
				
				int horizontalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					if(tablero.board[16-y][i] == tablero.yellowCircle){
						horizontalCircleCount++;
						if(horizontalCircleCount==1) {
							upperX=i;
							upperY=16-y;
						}
						else if(horizontalCircleCount==4) {
							if(emptyCount==1) {
								return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
							}
							else if(tablero.board[16-y][upperX-1]==tablero.empty) {
								return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
							}
							else if(tablero.board[16-y][i+1]==tablero.empty) {
								return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
							}
						}
						
					}
					else if(tablero.board[16-y][i] == tablero.empty) {
						emptyCount++;
						emptyX=x;
						emptyY=i;
						if(emptyCount>1) {
							horizontalCircleCount=0;
							emptyCount=0;
						}
					}
					else {
						horizontalCircleCount=0;
						emptyCount=0;
					}
				}
			}
		return "";
		}	
	

	public String defendCloseWin(Tablero tablero) {
		
		int y = 16 - (Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2))));
		int x = Integer.valueOf(lastMove.charAt(0))-64;
		int bIncreasing= y - x;
		int bDecreasing= y + x;
		int upperX=15;
		int upperY=15;
		int emptyCount=0;
		int emptyX=0;
		int emptyY=0;
		
		if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCross) { 
			
			int decreasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bDecreasing == (16-i)+j) {
						if(tablero.board[i][j] == tablero.blueCross ||tablero.board[i][j] ==tablero.greenCross){		
							decreasingDiagonalCrossCount++;
							if(decreasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(decreasingDiagonalCrossCount==4) {
								if(emptyCount==1) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if(tablero.board[upperY-1][upperX-1]==tablero.empty) {
									return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j+1]==tablero.empty) {
									return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								decreasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							decreasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int increasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bIncreasing == (16-i)-j) {
						if(tablero.board[i][j] == tablero.blueCross ||tablero.board[i][j] ==tablero.greenCross){
							increasingDiagonalCrossCount++;
							if(increasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(increasingDiagonalCrossCount==4) {
								if(emptyCount==1) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if(tablero.board[upperY-1][upperX+1]==tablero.empty) {
									return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j-1]==tablero.empty) {
									return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								increasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							increasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int verticalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[i][x] == tablero.blueCross ||tablero.board[i][x] ==tablero.greenCross){
					verticalCrossCount++;
					if(verticalCrossCount==1) {
						upperX=x;
						upperY=i;
					}
					if(verticalCrossCount==4) {
						if(emptyCount==1) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if(tablero.board[upperY-1][upperX]==tablero.empty) {
							return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
						}
						else if(tablero.board[i+1][x]==tablero.empty) {
							return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
						}
					}
				}
				else if(tablero.board[i][x] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						verticalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					verticalCrossCount=0;
					emptyCount=0;
				}
			}
			emptyCount=0;
			
			int horizontalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[16-y][i] == tablero.blueCross ||tablero.board[16-y][i] ==tablero.greenCross){
					horizontalCrossCount++;
					if(horizontalCrossCount==1) {
						upperX=i;
						upperY=16-y;
					}
					else if(horizontalCrossCount==4) {
						if(emptyCount==1) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if(tablero.board[16-y][upperX-1]==tablero.empty) {
							return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
						}
						else if(tablero.board[16-y][i+1]==tablero.empty) {
							return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
						}
					}
					
				}
				else if(tablero.board[16-y][i] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						horizontalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					horizontalCrossCount=0;
					emptyCount=0;
				}
			}
		}
		else if(tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64]==tablero.greenCircle){
			
			int decreasingDiagonalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					for(int j = 1; j<=15; j++) {
						if(bDecreasing == (16-i)+j) {
							if(tablero.board[i][j] == tablero.yellowCircle || tablero.board[i][j]==tablero.greenCircle){		
								decreasingDiagonalCircleCount++;
								if(decreasingDiagonalCircleCount==1) {
									upperX=j;
									upperY=i;
								}
								if(decreasingDiagonalCircleCount==4) {
									if(emptyCount==1) {
										return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
									}
									else if(tablero.board[upperY-1][upperX-1]==tablero.empty) {
										return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
									}
									else if(tablero.board[i+1][j+1]==tablero.empty) {
										return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
									}
								}
								i++;
								j=1;
							}
							else if(tablero.board[i][j] == tablero.empty) {
								emptyCount++;
								emptyX=j;
								emptyY=i;
								if(emptyCount>1) {
									decreasingDiagonalCircleCount=0;
									emptyCount=0;
								}
							}
							else {
								decreasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
					}
				}
				emptyCount=0;
				
				int increasingDiagonalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					for(int j = 1; j<=15; j++) {
						if(bIncreasing == (16-i)-j) {
							if(tablero.board[i][j] == tablero.yellowCircle || tablero.board[i][j]==tablero.greenCircle){
								increasingDiagonalCircleCount++;
								if(increasingDiagonalCircleCount==1) {
									upperX=j;
									upperY=i;
								}
								if(increasingDiagonalCircleCount==4) {
									if(emptyCount==1) {
										return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
									}
									else if(tablero.board[upperY-1][upperX+1]==tablero.empty) {
										return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
									}
									else if(tablero.board[i+1][j-1]==tablero.empty) {
										return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
									}
								}
								i++;
								j=1;
							}
							else if(tablero.board[i][j] == tablero.empty) {
								emptyCount++;
								emptyX=j;
								emptyY=i;
								if(emptyCount>1) {
									increasingDiagonalCircleCount=0;
									emptyCount=0;
								}
							}
							else {
								increasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
					}
				}
				emptyCount=0;
				
				int verticalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					if(tablero.board[i][x] == tablero.yellowCircle || tablero.board[i][x]==tablero.greenCircle){
						verticalCircleCount++;
						if(verticalCircleCount==1) {
							upperX=x;
							upperY=i;
						}
						if(verticalCircleCount==4) {
							if(emptyCount==1) {
								return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
							}
							else if(tablero.board[upperY-1][upperX]==tablero.empty) {
								return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
							}
							else if(tablero.board[i+1][x]==tablero.empty) {
								return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
							}
						}
					}
					else if(tablero.board[i][x] == tablero.empty) {
						emptyCount++;
						emptyX=x;
						emptyY=i;
						if(emptyCount>1) {
							verticalCircleCount=0;
							emptyCount=0;
						}
					}
					else {
						verticalCircleCount=0;
						emptyCount=0;
					}
				}
				emptyCount=0;
				
				int horizontalCircleCount=0;
				
				for(int i = 1; i <= 15; i++) {
					if(tablero.board[16-y][i] == tablero.yellowCircle || tablero.board[16-y][i]==tablero.greenCircle){
						horizontalCircleCount++;
						if(horizontalCircleCount==1) {
							upperX=i;
							upperY=16-y;
						}
						else if(horizontalCircleCount==4) {
							if(emptyCount==1) {
								return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
							}
							else if(tablero.board[16-y][upperX-1]==tablero.empty) {
								return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
							}
							else if(tablero.board[16-y][i+1]==tablero.empty) {
								return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
							}
						}
						
					}
					else if(tablero.board[16-y][i] == tablero.empty) {
						emptyCount++;
						emptyX=x;
						emptyY=i;
						if(emptyCount>1) {
							horizontalCircleCount=0;
							emptyCount=0;
						}
					}
					else {
						horizontalCircleCount=0;
						emptyCount=0;
					}
				}
			}
		return "";
		}	
	
	public String defend(Tablero tablero) {
		
		int y = 16 - (Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2))));
		int x = Integer.valueOf(lastMove.charAt(0))-64;
		int bIncreasing= y - x;
		int bDecreasing= y + x;
		int upperX=15;
		int upperY=15;
		int emptyCount=0;
		int emptyX=0;
		int emptyY=0;
		
		if((16-y)==1||(16-y)==15||x==1||x==15) {
			return"";
		}
		
		if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCross) { 
			
			int decreasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bDecreasing == (16-i)+j) {
						if(tablero.board[i][j] == tablero.blueCross || tablero.board[i][j] == tablero.greenCross){		
							decreasingDiagonalCrossCount++;
							if(decreasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(decreasingDiagonalCrossCount==3) {
								if(emptyCount==1&&(tablero.board[upperY-1][upperX-1])==tablero.empty || tablero.board[i+1][j+1]==tablero.empty) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if((tablero.board[upperY-1][upperX-1]==tablero.empty && tablero.board[upperY-2][upperX-2]==tablero.empty) || (tablero.board[upperY-1][upperX-1]==tablero.empty && tablero.board[i+1][j+1]==tablero.empty)) {
									return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j+1]==tablero.empty && tablero.board[i+2][j+2]==tablero.empty ) {
									return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								decreasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							decreasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;

			
			int increasingDiagonalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bIncreasing == (16-i)-j) {
						if(tablero.board[i][j] == tablero.blueCross ||tablero.board[i][j] ==tablero.greenCross){
							increasingDiagonalCrossCount++;
							if(increasingDiagonalCrossCount==1) {
								upperX=j;
								upperY=i;
							}
							if(increasingDiagonalCrossCount==3) {
								if(emptyCount==1&&(tablero.board[upperY-1][upperX+1])==tablero.empty || tablero.board[i+1][j-1]==tablero.empty) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if((tablero.board[upperY-1][upperX+1]==tablero.empty&&tablero.board[upperY-2][upperX+2]==tablero.empty)||(tablero.board[upperY-1][upperX+1]==tablero.empty&&tablero.board[i+1][j-1]==tablero.empty)) {
									return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j-1]==tablero.empty&&tablero.board[i+2][j-2]==tablero.empty) {
									return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								increasingDiagonalCrossCount=0;
								emptyCount=0;
							}
						}
						else {
							increasingDiagonalCrossCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int verticalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[i][x] == tablero.blueCross ||tablero.board[i][x] ==tablero.greenCross){
					verticalCrossCount++;
					if(verticalCrossCount==1) {
						upperX=x;
						upperY=i;
					}
					if(verticalCrossCount==3) {
						if(emptyCount==1&&(tablero.board[upperY-1][upperX]==tablero.empty||tablero.board[i+1][x]==tablero.empty)) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if((tablero.board[upperY-1][upperX]==tablero.empty&&tablero.board[upperY-2][upperX]==tablero.empty)||(tablero.board[upperY-1][upperX]==tablero.empty&&tablero.board[i+1][x]==tablero.empty)) {
							return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
						}
						else if(tablero.board[i+1][x]==tablero.empty&&tablero.board[i+2][x]==tablero.empty) {
							return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
						}
					}
				}
				else if(tablero.board[i][x] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						verticalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					verticalCrossCount=0;
					emptyCount=0;
				}
			}
			emptyCount=0;
			
			int horizontalCrossCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[16-y][i] == tablero.blueCross ||tablero.board[16-y][i] ==tablero.greenCross){
					horizontalCrossCount++;
					if(horizontalCrossCount==1) {
						upperX=i;
						upperY=16-y;
					}
					else if(horizontalCrossCount==3) {
						if(emptyCount==1&&(tablero.board[16-y][upperX-1]==tablero.empty||tablero.board[16-y][i+1]==tablero.empty)) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if((tablero.board[16-y][upperX-1]==tablero.empty&&tablero.board[16-y][upperX-2]==tablero.empty)||(tablero.board[16-y][upperX-1]==tablero.empty&&tablero.board[16-y][i+1]==tablero.empty)) {
							return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
						}
						else if(tablero.board[16-y][i+1]==tablero.empty&&tablero.board[16-y][i+2]==tablero.empty) {
							return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
						}
					}
					
				}
				else if(tablero.board[16-y][i] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						horizontalCrossCount=0;
						emptyCount=0;
					}
				}
				else {
					horizontalCrossCount=0;
					emptyCount=0;
				}
			}
		}
		else if(tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64]==tablero.greenCircle){
			
			int decreasingDiagonalCircleCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bDecreasing == (16-i)+j) {
						if(tablero.board[i][j] == tablero.yellowCircle || tablero.board[i][j] == tablero.greenCircle){		
							decreasingDiagonalCircleCount++;
							if(decreasingDiagonalCircleCount==1) {
								upperX=j;
								upperY=i;
							}
							if(decreasingDiagonalCircleCount==3) {
								if(emptyCount==1&&(tablero.board[upperY-1][upperX-1])==tablero.empty || tablero.board[i+1][j+1]==tablero.empty) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if((tablero.board[upperY-1][upperX-1]==tablero.empty && tablero.board[upperY-2][upperX-2]==tablero.empty) || (tablero.board[upperY-1][upperX-1]==tablero.empty && tablero.board[i+1][j+1]==tablero.empty)) {
									return((upperY-1)<10)? (char)((upperX-1)+64)+"0"+(upperY-1) : (char)((upperX-1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j+1]==tablero.empty && tablero.board[i+2][j+2]==tablero.empty ) {
									return((i+1)<10)? (char)((j+1)+64)+"0"+(i+1) : (char)((j+1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								decreasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
						else {
							decreasingDiagonalCircleCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;

			
			int increasingDiagonalCircleCount=0;
			
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(bIncreasing == (16-i)-j) {
						if(tablero.board[i][j] == tablero.yellowCircle ||tablero.board[i][j] ==tablero.greenCircle){
							increasingDiagonalCircleCount++;
							if(increasingDiagonalCircleCount==1) {
								upperX=j;
								upperY=i;
							}
							if(increasingDiagonalCircleCount==3) {
								if(emptyCount==1&&(tablero.board[upperY-1][upperX+1])==tablero.empty || tablero.board[i+1][j-1]==tablero.empty) {
									return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
								else if((tablero.board[upperY-1][upperX+1]==tablero.empty&&tablero.board[upperY-2][upperX+2]==tablero.empty)||(tablero.board[upperY-1][upperX+1]==tablero.empty&&tablero.board[i+1][j-1]==tablero.empty)) {
									return((upperY-1)<10)? (char)((upperX+1)+64)+"0"+(upperY-1) : (char)((upperX+1)+64)+""+(upperY-1);
								}
								else if(tablero.board[i+1][j-1]==tablero.empty&&tablero.board[i+2][j-2]==tablero.empty) {
									return((i+1)<10)? (char)((j-1)+64)+"0"+(i+1) : (char)((j-1)+64)+""+(i+1);
								}
							}
							i++;
							j=1;
						}
						else if(tablero.board[i][j] == tablero.empty) {
							emptyCount++;
							emptyX=j;
							emptyY=i;
							if(emptyCount>1) {
								increasingDiagonalCircleCount=0;
								emptyCount=0;
							}
						}
						else {
							increasingDiagonalCircleCount=0;
							emptyCount=0;
						}
					}
				}
			}
			emptyCount=0;
			
			int verticalCircleCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[i][x] == tablero.yellowCircle ||tablero.board[i][x] ==tablero.greenCircle){
					verticalCircleCount++;
					if(verticalCircleCount==1) {
						upperX=x;
						upperY=i;
					}
					if(verticalCircleCount==3) {
						if(emptyCount==1&&(tablero.board[upperY-1][upperX]==tablero.empty||tablero.board[i+1][x]==tablero.empty)) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if((tablero.board[upperY-1][upperX]==tablero.empty&&tablero.board[upperY-2][upperX]==tablero.empty)||(tablero.board[upperY-1][upperX]==tablero.empty&&tablero.board[i+1][x]==tablero.empty)) {
							return ((upperY-1)<10)?(char)(upperX+64)+"0"+(upperY-1) : (char)(upperX+64)+""+(upperY-1); 
						}
						else if(tablero.board[i+1][x]==tablero.empty&&tablero.board[i+2][x]==tablero.empty) {
							return ((i+1)<10)? (char)x+"0"+(i+1):(char)x+""+(i+1);
						}
					}
				}
				else if(tablero.board[i][x] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						verticalCircleCount=0;
						emptyCount=0;
					}
				}
				else {
					verticalCircleCount=0;
					emptyCount=0;
				}
			}
			emptyCount=0;
			
			int horizontalCircleCount=0;
			
			for(int i = 1; i <= 15; i++) {
				if(tablero.board[16-y][i] == tablero.yellowCircle ||tablero.board[16-y][i] ==tablero.greenCircle){
					horizontalCircleCount++;
					if(horizontalCircleCount==1) {
						upperX=i;
						upperY=16-y;
					}
					else if(horizontalCircleCount==3) {
						if(emptyCount==1&&(tablero.board[16-y][upperX-1]==tablero.empty||tablero.board[16-y][i+1]==tablero.empty)) {
							return(emptyY<10)? (char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
						else if((tablero.board[16-y][upperX-1]==tablero.empty&&tablero.board[16-y][upperX-2]==tablero.empty)||(tablero.board[16-y][upperX-1]==tablero.empty&&tablero.board[16-y][i+1]==tablero.empty)) {
							return((16-y)<10)?(char)((upperX-1)+64)+"0"+(16-y):(char)((upperX-1)+64)+""+(16-y);
						}
						else if(tablero.board[16-y][i+1]==tablero.empty&&tablero.board[16-y][i+2]==tablero.empty) {
							return((16-y)<10)?(char)(i+1+64)+"0"+(16-y) : (char)(i+1+64)+""+(16-y);
						}
					}
					
				}
				else if(tablero.board[16-y][i] == tablero.empty) {
					emptyCount++;
					emptyX=x;
					emptyY=i;
					if(emptyCount>1) {
						horizontalCircleCount=0;
						emptyCount=0;
					}
				}
				else {
					horizontalCircleCount=0;
					emptyCount=0;
				}
			}
		}
		return "";
		}	
	
	public String attack(Tablero tablero) {
		
		int allyCount;
		int emptyY=99;
		int emptyX=99;
//												COMPRUEBA LOS CIRCULOS EN TURNO DE CIRCULOS PARA VER ATAQUES
		if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCross) {
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(tablero.board[i][j]==tablero.greenCircle||tablero.board[i][j]==tablero.yellowCircle) {
						
						if(tablero.board[i-1][j-1]==tablero.greenCircle||tablero.board[i-1][j-1]==tablero.yellowCircle) {			//DIAGONAL ARRIBA IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j-(2+z)]==tablero.greenCircle||tablero.board[i-(2+z)][j-(2+z)]==tablero.yellowCircle||tablero.board[i-(2+z)][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j-(2+z)]==tablero.empty) {
									emptyY=i-(2+z);
									emptyX=j-(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j+(1+z)]==tablero.greenCircle||tablero.board[i+(1+z)][j+(1+z)]==tablero.yellowCircle||tablero.board[i+(1+z)][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j+(1+z)]==tablero.empty) {
									emptyY=i+(1+z);
									emptyX=j+(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i-1][j]==tablero.greenCircle||tablero.board[i-1][j]==tablero.yellowCircle) {			//ARRIBA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j]==tablero.greenCircle||tablero.board[i-(2+z)][j]==tablero.yellowCircle||tablero.board[i-(2+z)][j]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j]==tablero.empty) {
									emptyY=i-(2+z);
									emptyX=j;
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j]==tablero.greenCircle||tablero.board[i+(1+z)][j]==tablero.yellowCircle||tablero.board[i+(1+z)][j]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j]==tablero.empty) {
									emptyY=i+(1+z);
									emptyX=j;
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i-1][j+1]==tablero.greenCircle||tablero.board[i-1][j+1]==tablero.yellowCircle) {		//DIAGONAL ARRIBA DERECHA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j+(2+z)]==tablero.greenCircle||tablero.board[i-(2+z)][j+(2+z)]==tablero.yellowCircle||tablero.board[i-(2+z)][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j+(2+z)]==tablero.empty) {
									emptyY=i-(2+z);
									emptyX=j+(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j-(1+z)]==tablero.greenCircle||tablero.board[i+(1+z)][j-(1+z)]==tablero.yellowCircle||tablero.board[i+(1+z)][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j-(1+z)]==tablero.empty) {
									emptyY=i+(1+z);
									emptyX=j-(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							emptyY=99;
							emptyX=99;
							
							
						}
						else if(tablero.board[i][j-1]==tablero.greenCircle||tablero.board[i][j-1]==tablero.yellowCircle) {			//IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i][j-(2+z)]==tablero.greenCircle||tablero.board[i][j-(2+z)]==tablero.yellowCircle||tablero.board[i][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i][j-(2+z)]==tablero.empty) {
									emptyY=i;
									emptyX=j-(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i][j+(1+z)]==tablero.greenCircle||tablero.board[i][j+(1+z)]==tablero.yellowCircle||tablero.board[i][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i][j+(1+z)]==tablero.empty) {
									emptyY=i;
									emptyX=j+(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i][j+1]==tablero.greenCircle||tablero.board[i][j+1]==tablero.yellowCircle) {			//DERECHA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i][j+(2+z)]==tablero.greenCircle||tablero.board[i][j+(2+z)]==tablero.yellowCircle||tablero.board[i][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i][j+(2+z)]==tablero.empty) {
									emptyY=i;
									emptyX=j+(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i][j-(1+z)]==tablero.greenCircle||tablero.board[i][j-(1+z)]==tablero.yellowCircle||tablero.board[i][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i][j-(1+z)]==tablero.empty) {
									emptyY=i;
									emptyX=j-(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i+1][j-1]==tablero.greenCircle||tablero.board[i+1][j-1]==tablero.yellowCircle) {		//DIAGONAL ABAJO IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j-(2+z)]==tablero.greenCircle||tablero.board[i+(2+z)][j-(2+z)]==tablero.yellowCircle||tablero.board[i+(2+z)][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j-(2+z)]==tablero.empty) {
									emptyY=i+(2+z);
									emptyX=j-(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j+(1+z)]==tablero.greenCircle||tablero.board[i-(1+z)][j+(1+z)]==tablero.yellowCircle||tablero.board[i-(1+z)][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j+(1+z)]==tablero.empty) {
									emptyY=i-(1+z);
									emptyX=j+(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i+1][j]==tablero.greenCircle||tablero.board[i+1][j]==tablero.yellowCircle) {			//ABAJO
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j]==tablero.greenCircle||tablero.board[i+(2+z)][j]==tablero.yellowCircle||tablero.board[i+(2+z)][j]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j]==tablero.empty) {
									emptyY=i+(2+z);
									emptyX=j;
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j]==tablero.greenCircle||tablero.board[i-(1+z)][j]==tablero.yellowCircle||tablero.board[i-(1+z)][j]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j]==tablero.empty) {
									emptyY=i-(1+z);
									emptyX=j;
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							
							emptyY=99;
							emptyX=99;
							
						}
						else if(tablero.board[i+1][j+1]==tablero.greenCircle||tablero.board[i+1][j+1]==tablero.yellowCircle) {		//DIAGONAL ABAJO DERECHA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j+(2+z)]==tablero.greenCircle||tablero.board[i+(2+z)][j+(2+z)]==tablero.yellowCircle||tablero.board[i+(2+z)][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j+(2+z)]==tablero.empty) {
									emptyY=i+(2+z);
									emptyX=j+(2+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j-(1+z)]==tablero.greenCircle||tablero.board[i-(1+z)][j-(1+z)]==tablero.yellowCircle||tablero.board[i-(1+z)][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
								if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j-(1+z)]==tablero.empty) {
									emptyY=i-(1+z);
									emptyX=j-(1+z);
								}
								if(allyCount>=3) {
									return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
								}
							}
							
							emptyY=99;
							emptyX=99;
							
						}
					}
				}
			}
			
		}
//		COMPRUEBA LAS CRUCES EN TURNO DE CRUCES PARA VER ATAQUES
if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCircle) {
	for(int i = 1; i <= 15; i++) {
		for(int j = 1; j<=15; j++) {
			if(tablero.board[i][j]==tablero.greenCross||tablero.board[i][j]==tablero.blueCross) {
				
				if(tablero.board[i-1][j-1]==tablero.greenCross||tablero.board[i-1][j-1]==tablero.blueCross) {			//DIAGONAL ARRIBA IZQUIERDA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i-(2+z)][j-(2+z)]==tablero.greenCross||tablero.board[i-(2+z)][j-(2+z)]==tablero.blueCross||tablero.board[i-(2+z)][j-(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j-(2+z)]==tablero.empty) {
							emptyY=i-(2+z);
							emptyX=j-(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i+(1+z)][j+(1+z)]==tablero.greenCross||tablero.board[i+(1+z)][j+(1+z)]==tablero.blueCross||tablero.board[i+(1+z)][j+(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j+(1+z)]==tablero.empty) {
							emptyY=i+(1+z);
							emptyX=j+(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i-1][j]==tablero.greenCross||tablero.board[i-1][j]==tablero.blueCross) {			//ARRIBA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i-(2+z)][j]==tablero.greenCross||tablero.board[i-(2+z)][j]==tablero.blueCross||tablero.board[i-(2+z)][j]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j]==tablero.empty) {
							emptyY=i-(2+z);
							emptyX=j;
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i+(1+z)][j]==tablero.greenCross||tablero.board[i+(1+z)][j]==tablero.blueCross||tablero.board[i+(1+z)][j]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j]==tablero.empty) {
							emptyY=i+(1+z);
							emptyX=j;
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i-1][j+1]==tablero.greenCross||tablero.board[i-1][j+1]==tablero.blueCross) {		//DIAGONAL ARRIBA DERECHA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i-(2+z)][j+(2+z)]==tablero.greenCross||tablero.board[i-(2+z)][j+(2+z)]==tablero.blueCross||tablero.board[i-(2+z)][j+(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(2+z)][j+(2+z)]==tablero.empty) {
							emptyY=i-(2+z);
							emptyX=j+(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i+(1+z)][j-(1+z)]==tablero.greenCross||tablero.board[i+(1+z)][j-(1+z)]==tablero.blueCross||tablero.board[i+(1+z)][j-(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(1+z)][j-(1+z)]==tablero.empty) {
							emptyY=i+(1+z);
							emptyX=j-(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					emptyY=99;
					emptyX=99;
					
					
				}
				else if(tablero.board[i][j-1]==tablero.greenCross||tablero.board[i][j-1]==tablero.blueCross) {			//IZQUIERDA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i][j-(2+z)]==tablero.greenCross||tablero.board[i][j-(2+z)]==tablero.blueCross||tablero.board[i][j-(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i][j-(2+z)]==tablero.empty) {
							emptyY=i;
							emptyX=j-(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i][j+(1+z)]==tablero.greenCross||tablero.board[i][j+(1+z)]==tablero.blueCross||tablero.board[i][j+(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i][j+(1+z)]==tablero.empty) {
							emptyY=i;
							emptyX=j+(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i][j+1]==tablero.greenCross||tablero.board[i][j+1]==tablero.blueCross) {			//DERECHA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i][j+(2+z)]==tablero.greenCross||tablero.board[i][j+(2+z)]==tablero.blueCross||tablero.board[i][j+(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i][j+(2+z)]==tablero.empty) {
							emptyY=i;
							emptyX=j+(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i][j-(1+z)]==tablero.greenCross||tablero.board[i][j-(1+z)]==tablero.blueCross||tablero.board[i][j-(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i][j-(1+z)]==tablero.empty) {
							emptyY=i;
							emptyX=j-(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i+1][j-1]==tablero.greenCross||tablero.board[i+1][j-1]==tablero.blueCross) {		//DIAGONAL ABAJO IZQUIERDA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i+(2+z)][j-(2+z)]==tablero.greenCross||tablero.board[i+(2+z)][j-(2+z)]==tablero.blueCross||tablero.board[i+(2+z)][j-(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j-(2+z)]==tablero.empty) {
							emptyY=i+(2+z);
							emptyX=j-(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i-(1+z)][j+(1+z)]==tablero.greenCross||tablero.board[i-(1+z)][j+(1+z)]==tablero.blueCross||tablero.board[i-(1+z)][j+(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j+(1+z)]==tablero.empty) {
							emptyY=i-(1+z);
							emptyX=j+(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i+1][j]==tablero.greenCross||tablero.board[i+1][j]==tablero.blueCross) {			//ABAJO
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i+(2+z)][j]==tablero.greenCross||tablero.board[i+(2+z)][j]==tablero.blueCross||tablero.board[i+(2+z)][j]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j]==tablero.empty) {
							emptyY=i+(2+z);
							emptyX=j;
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i-(1+z)][j]==tablero.greenCross||tablero.board[i-(1+z)][j]==tablero.blueCross||tablero.board[i-(1+z)][j]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j]==tablero.empty) {
							emptyY=i-(1+z);
							emptyX=j;
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					
					emptyY=99;
					emptyX=99;
					
				}
				else if(tablero.board[i+1][j+1]==tablero.greenCross||tablero.board[i+1][j+1]==tablero.blueCross) {		//DIAGONAL ABAJO DERECHA
					
					allyCount=0;
					for(int z=0; z<2 && (tablero.board[i+(2+z)][j+(2+z)]==tablero.greenCross||tablero.board[i+(2+z)][j+(2+z)]==tablero.blueCross||tablero.board[i+(2+z)][j+(2+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i+(2+z)][j+(2+z)]==tablero.empty) {
							emptyY=i+(2+z);
							emptyX=j+(2+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					for(int z=0; z<2 && (tablero.board[i-(1+z)][j-(1+z)]==tablero.greenCross||tablero.board[i-(1+z)][j-(1+z)]==tablero.blueCross||tablero.board[i-(1+z)][j-(1+z)]==tablero.empty);z++) {
						allyCount++;
						if(emptyY==99&&emptyX==99&&tablero.board[i-(1+z)][j-(1+z)]==tablero.empty) {
							emptyY=i-(1+z);
							emptyX=j-(1+z);
						}
						if(allyCount>=3) {
							return(emptyY<10)?(char)(emptyX+64)+"0"+emptyY:(char)(emptyX+64)+""+emptyY;
						}
					}
					
					emptyY=99;
					emptyX=99;
					
				}
			}
		}
	}
}
		return "";
	}
		
	public String startAttack(Tablero tablero) {
		
		int allyCount;
//												COMPRUEBA LOS CIRCULOS EN TURNO DE CIRCULOS PARA VER ATAQUES
		if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCross) {
			for(int i = 1; i <= 15; i++) {
				for(int j = 1; j<=15; j++) {
					if(tablero.board[i][j]==tablero.greenCircle||tablero.board[i][j]==tablero.yellowCircle) {
						
						if(tablero.board[i-1][j-1]==tablero.empty) {			//ARRIBA IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j-(2+z)]==tablero.greenCircle||tablero.board[i-(2+z)][j-(2+z)]==tablero.yellowCircle||tablero.board[i-(2+z)][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j-1+64)+"0"+(i-1):(char)(j-1+64)+""+(i-1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j+(1+z)]==tablero.greenCircle||tablero.board[i+(1+z)][j+(1+z)]==tablero.yellowCircle||tablero.board[i+(1+z)][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j-1+64)+"0"+(i-1):(char)(j-1+64)+""+(i-1);
								}
							}
							
						}
						else if(tablero.board[i-1][j]==tablero.empty) {			//ARRIBA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j]==tablero.greenCircle||tablero.board[i-(2+z)][j]==tablero.yellowCircle||tablero.board[i-(2+z)][j]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j+64)+"0"+(i-1):(char)(j+64)+""+(i-1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j]==tablero.greenCircle||tablero.board[i+(1+z)][j]==tablero.yellowCircle||tablero.board[i+(1+z)][j]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j+64)+"0"+(i-1):(char)(j+64)+""+(i-1);
								}
							}
							
						}
						else if(tablero.board[i-1][j+1]==tablero.empty) {		//ARRIBA DERECHA
								
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i-(2+z)][j+(2+z)]==tablero.greenCircle||tablero.board[i-(2+z)][j+(2+z)]==tablero.yellowCircle||tablero.board[i-(2+z)][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j+1+64)+"0"+(i-1):(char)(j+1+64)+""+(i-1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i+(1+z)][j-(1+z)]==tablero.greenCircle||tablero.board[i+(1+z)][j-(1+z)]==tablero.yellowCircle||tablero.board[i+(1+z)][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i-1)<10)?(char)(j+1+64)+"0"+(i-1):(char)(j+1+64)+""+(i-1);
								}
							}
							
						}
							
						else if(tablero.board[i][j-1]==tablero.empty) {			//IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i][j-(2+z)]==tablero.greenCircle||tablero.board[i][j-(2+z)]==tablero.yellowCircle||tablero.board[i][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i)<10)?(char)(j-1+64)+"0"+(i):(char)(j-1+64)+""+(i);
								}
							}
							for(int z=0; z<2 && (tablero.board[i][j+(1+z)]==tablero.greenCircle||tablero.board[i][j+(1+z)]==tablero.yellowCircle||tablero.board[i][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i)<10)?(char)(j-1+64)+"0"+(i):(char)(j-1+64)+""+(i);
								}
							}
							
						}
						else if(tablero.board[i][j+1]==tablero.empty) {			//DERECHA
							
							allyCount=0;
							
							for(int z=0; z<2 && (tablero.board[i][j+(2+z)]==tablero.greenCircle||tablero.board[i][j+(2+z)]==tablero.yellowCircle||tablero.board[i][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i)<10)?(char)(j+1+64)+"0"+(i):(char)(j+1+64)+""+(i);
								}
							}
							for(int z=0; z<2 && (tablero.board[i][j-(1+z)]==tablero.greenCircle||tablero.board[i][j-(1+z)]==tablero.yellowCircle||tablero.board[i][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i)<10)?(char)(j+1+64)+"0"+(i):(char)(j+1+64)+""+(i);
								}
							}
							
							
						}
						else if(tablero.board[i+1][j-1]==tablero.empty) {		//ABAJO IZQUIERDA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j-(2+z)]==tablero.greenCircle||tablero.board[i+(2+z)][j-(2+z)]==tablero.yellowCircle||tablero.board[i+(2+z)][j-(2+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j-1+64)+"0"+(i+1):(char)(j-1+64)+""+(i+1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j+(1+z)]==tablero.greenCircle||tablero.board[i-(1+z)][j+(1+z)]==tablero.yellowCircle||tablero.board[i-(1+z)][j+(1+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j-1+64)+"0"+(i+1):(char)(j-1+64)+""+(i+1);
								}
							}
							
							
						}
						else if(tablero.board[i+1][j]==tablero.empty) {			//ABAJO
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j]==tablero.greenCircle||tablero.board[i+(2+z)][j]==tablero.yellowCircle||tablero.board[i+(2+z)][j]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j+64)+"0"+(i+1):(char)(j+64)+""+(i+1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j]==tablero.greenCircle||tablero.board[i-(1+z)][j]==tablero.yellowCircle||tablero.board[i-(1+z)][j]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j+64)+"0"+(i+1):(char)(j+64)+""+(i+1);
								}
							}
							
							
						}
						else if(tablero.board[i+1][j+1]==tablero.empty) {		//ABAJO DERECHA
							
							allyCount=0;
							for(int z=0; z<2 && (tablero.board[i+(2+z)][j+(2+z)]==tablero.greenCircle||tablero.board[i+(2+z)][j+(2+z)]==tablero.yellowCircle||tablero.board[i+(2+z)][j+(2+z)]==tablero.empty);z++) {
								allyCount++;
								
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j+1+64)+"0"+(i+1):(char)(j+1+64)+""+(i+1);
								}
							}
							for(int z=0; z<2 && (tablero.board[i-(1+z)][j-(1+z)]==tablero.greenCircle||tablero.board[i-(1+z)][j-(1+z)]==tablero.yellowCircle||tablero.board[i-(1+z)][j-(1+z)]==tablero.empty);z++) {
								allyCount++;
							
								if(allyCount>=3) {
									return((i+1)<10)?(char)(j+1+64)+"0"+(i+1):(char)(j+1+64)+""+(i+1);
								}
							}
						}
					}
				}
			}
		}
				 
			if (tablero.board[Integer.valueOf(String.valueOf(tablero.gameLastMove.charAt(1))+String.valueOf(tablero.gameLastMove.charAt(2)))][Integer.valueOf(tablero.gameLastMove.charAt(0))-64] == tablero.greenCircle) {
				for(int i = 1; i <= 15; i++) {
					for(int j = 1; j<=15; j++) {
						if(tablero.board[i][j]==tablero.greenCross||tablero.board[i][j]==tablero.blueCross) {
							
							if(tablero.board[i-1][j-1]==tablero.empty) {			//ARRIBA IZQUIERDA
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i-(2+z)][j-(2+z)]==tablero.greenCross||tablero.board[i-(2+z)][j-(2+z)]==tablero.blueCross||tablero.board[i-(2+z)][j-(2+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j-1+64)+"0"+(i-1):(char)(j-1+64)+""+(i-1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i+(1+z)][j+(1+z)]==tablero.greenCross||tablero.board[i+(1+z)][j+(1+z)]==tablero.blueCross||tablero.board[i+(1+z)][j+(1+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j-1+64)+"0"+(i-1):(char)(j-1+64)+""+(i-1);
									}
								}
								
							}
							else if(tablero.board[i-1][j]==tablero.empty) {			//ARRIBA
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i-(2+z)][j]==tablero.greenCross||tablero.board[i-(2+z)][j]==tablero.blueCross||tablero.board[i-(2+z)][j]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j+64)+"0"+(i-1):(char)(j+64)+""+(i-1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i+(1+z)][j]==tablero.greenCross||tablero.board[i+(1+z)][j]==tablero.blueCross||tablero.board[i+(1+z)][j]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j+64)+"0"+(i-1):(char)(j+64)+""+(i-1);
									}
								}
								
							}
							else if(tablero.board[i-1][j+1]==tablero.empty) {		//ARRIBA DERECHA
									
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i-(2+z)][j+(2+z)]==tablero.greenCross||tablero.board[i-(2+z)][j+(2+z)]==tablero.blueCross||tablero.board[i-(2+z)][j+(2+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j+1+64)+"0"+(i-1):(char)(j+1+64)+""+(i-1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i+(1+z)][j-(1+z)]==tablero.greenCross||tablero.board[i+(1+z)][j-(1+z)]==tablero.blueCross||tablero.board[i+(1+z)][j-(1+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i-1)<10)?(char)(j+1+64)+"0"+(i-1):(char)(j+1+64)+""+(i-1);
									}
								}
								
							}
								
							else if(tablero.board[i][j-1]==tablero.empty) {			//IZQUIERDA
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i][j-(2+z)]==tablero.greenCross||tablero.board[i][j-(2+z)]==tablero.blueCross||tablero.board[i][j-(2+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i)<10)?(char)(j-1+64)+"0"+(i):(char)(j-1+64)+""+(i);
									}
								}
								for(int z=0; z<2 && (tablero.board[i][j+(1+z)]==tablero.greenCross||tablero.board[i][j+(1+z)]==tablero.blueCross||tablero.board[i][j+(1+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i)<10)?(char)(j-1+64)+"0"+(i):(char)(j-1+64)+""+(i);
									}
								}
								
							}
							else if(tablero.board[i][j+1]==tablero.empty) {			//DERECHA
								
								allyCount=0;
								
								for(int z=0; z<2 && (tablero.board[i][j+(2+z)]==tablero.greenCross||tablero.board[i][j+(2+z)]==tablero.blueCross||tablero.board[i][j+(2+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i)<10)?(char)(j+1+64)+"0"+(i):(char)(j+1+64)+""+(i);
									}
								}
								for(int z=0; z<2 && (tablero.board[i][j-(1+z)]==tablero.greenCross||tablero.board[i][j-(1+z)]==tablero.blueCross||tablero.board[i][j-(1+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i)<10)?(char)(j+1+64)+"0"+(i):(char)(j+1+64)+""+(i);
									}
								}
								
								
							}
							else if(tablero.board[i+1][j-1]==tablero.empty) {		//ABAJO IZQUIERDA
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i+(2+z)][j-(2+z)]==tablero.greenCross||tablero.board[i+(2+z)][j-(2+z)]==tablero.blueCross||tablero.board[i+(2+z)][j-(2+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j-1+64)+"0"+(i+1):(char)(j-1+64)+""+(i+1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i-(1+z)][j+(1+z)]==tablero.greenCross||tablero.board[i-(1+z)][j+(1+z)]==tablero.blueCross||tablero.board[i-(1+z)][j+(1+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j-1+64)+"0"+(i+1):(char)(j-1+64)+""+(i+1);
									}
								}
								
								
							}
							else if(tablero.board[i+1][j]==tablero.empty) {			//ABAJO
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i+(2+z)][j]==tablero.greenCross||tablero.board[i+(2+z)][j]==tablero.blueCross||tablero.board[i+(2+z)][j]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j+64)+"0"+(i+1):(char)(j+64)+""+(i+1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i-(1+z)][j]==tablero.greenCross||tablero.board[i-(1+z)][j]==tablero.blueCross||tablero.board[i-(1+z)][j]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j+64)+"0"+(i+1):(char)(j+64)+""+(i+1);
									}
								}
								
								
							}
							else if(tablero.board[i+1][j+1]==tablero.empty) {		//ABAJO DERECHA
								
								allyCount=0;
								for(int z=0; z<2 && (tablero.board[i+(2+z)][j+(2+z)]==tablero.greenCross||tablero.board[i+(2+z)][j+(2+z)]==tablero.blueCross||tablero.board[i+(2+z)][j+(2+z)]==tablero.empty);z++) {
									allyCount++;
									
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j+1+64)+"0"+(i+1):(char)(j+1+64)+""+(i+1);
									}
								}
								for(int z=0; z<2 && (tablero.board[i-(1+z)][j-(1+z)]==tablero.greenCross||tablero.board[i-(1+z)][j-(1+z)]==tablero.blueCross||tablero.board[i-(1+z)][j-(1+z)]==tablero.empty);z++) {
									allyCount++;
								
									if(allyCount>=3) {
										return((i+1)<10)?(char)(j+1+64)+"0"+(i+1):(char)(j+1+64)+""+(i+1);
									}
								}
							}
						}
					}
				}
		}
		return "";
	}		 
		
	public String randomAttack(Tablero tablero) {
		
		Random random = new Random();
		
		int number=(random.nextInt(15)+1);
		int letter=(random.nextInt(15)+1);
		
		while(tablero.board[number][letter]!=tablero.empty) {
			number=(random.nextInt(15)+1);
			letter=(random.nextInt(15)+1);
		}
		return (number<10)?(char)(letter+64)+"0"+number:(char)(letter+64)+""+number;
	}
	
	public void nameSelect() {
		
		Random random = new Random();
		
		int num=random.nextInt(5);
		String name = "";
		
		switch(num) {
		case 0: name="botDeLelouch";
		break;
		
		case 1: name="botDeSchneizel";
		break;
		
		case 2: name="botDeCornelia";
		break;
		
		case 3: name="botDeChema";
		break;
		
		case 4: name="botDeNeburOMG";
		break;
	}
		setName(name);
	}

}
