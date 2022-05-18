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
		result=attack(tablero);
		if(result!="") {
			return result;
		}
		result=defend(tablero);
		if(result!="") {
			return result;
		}
		result=randomAttack(tablero);
		if(result!="") {
			return result;
		}
		
		return "Y98";
		
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
				if(tablero.board[y-16][i] == tablero.blueCross ||tablero.board[y-16][i] ==tablero.greenCross){
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
	
	public void attack(Tablero tablero) {
		
	}
	public void defend(Tablero tablero) {
		
	}
	public void randomAttack(Tablero tablero) {
		
	}
	
	public void nameSelect() {
		
		Random random = new Random();
		
		int num=random.nextInt(5);
		String name = "";
		
		switch(num) {
		case 0: name="Lelouch";
		break;
		
		case 1: name="Schneizel";
		break;
		
		case 2: name="Cornelia";
		break;
		
		case 3: name="Chema";
		break;
		
		case 4: name="NeburOMG";
		break;
	}
		setName(name);
	}

}