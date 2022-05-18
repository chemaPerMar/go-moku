package goMake;

import java.util.Random;
import java.util.Scanner;

public class Partida {

	protected Tablero tablero;
	protected Jugador player1;
	protected Jugador player2;
	protected Jugador a;
	protected Jugador b;
	protected int turn = 1;
	protected boolean gameOver = false;
	
	public void gameStart() {
		
		System.out.println(Colors.PURPLE+"    /@@@@@(     (@@@@@@/        &@@@    ,@@@#    #@@&     @@%    @@@/&@@@@@@@@( \r\n"
				+ "  @@@     @@@ /@@#    #@@*      &@@@@   @@@@#   /@@@@#    @@% .@@@   &@@        \r\n"
				+ " %@@          @@#      %@@      &@%@@* #@&@@#  ,@@  @@/   @@@@@@     &@@&&&&&&  \r\n"
				+ " (@@    &&&@@ @@#      %@@ ////.&@%.@@ @@ @@#  @@/  *@@.  @@@.(@@&   &@@        \r\n"
				+ "  #@@#   /@@@ .@@@   .@@@.      &@% #@@@, @@# @@&,,,,#@@  @@%   @@@, &@@        \r\n"
				+ "     (@@%, //    ,%@@%*         &@%  &@%  &@,&@%      &@% &@%     &@,*&@@&&&&&"+Colors.RESET);
		
		tablero = new Tablero();
		tablero.fillBoard();
		champSelect();
		whoIsFirst();
		tablero.printBoard();
		
		while(gameOver==false) {
			if(turn%2 != 0) {
				
				System.out.printf("Turno de %s\n",player1.getName());
				
				if(player1 instanceof Human) {
					player1.humanPlaceToken(tablero, (Human) player1);
					tablero.gameLastMove = player1.getLastMove();
					tablero.printBoard();
				}
				else if(player1 instanceof Robot) {
					player1.botPlaceToken(tablero, (Robot) player1);
					tablero.gameLastMove = player1.getLastMove();
					tablero.printBoard();
				}
				
			}
			else if(turn%2 == 0) {
				
				System.out.printf("Turno de %s\n",player2.getName());
				
				if(player2 instanceof Human) {
					player2.humanPlaceToken(tablero, (Human) player2);
					tablero.gameLastMove = player2.getLastMove();
					tablero.printBoard();
				}
				else if(player2 instanceof Robot){
					player2.botPlaceToken(tablero, (Robot) player2);
					tablero.gameLastMove = player2.getLastMove();
					tablero.printBoard();
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
		System.out.println(Colors.GREEN+"\n   .%&%.  ,%&#.%&%.  #&&&&&&#,%&&&&&&%, #&&&&&&%.  #&&&&&&%, (&%,  /&%*.%&%.\r\n"
				+ "   ,%&(  ,%%( (&%,  %&%. *&%/  .%&%    %&%. .%&#  *&&/  %&%. (&%, #&%. (&%, \r\n"
				+ "   /%%, ,%&( ,%&(  /%%*  //*   %&%,   (&%,  #&%. .%&%  /&%*  (&%*%&#  ,%&/  \r\n"
				+ "   #&%.*%%/ .%&%. ,%&#        *%&/   ,%&(  *%%*  #&%, ,%&#   (&&&%*   %&#   \r\n"
				+ "  .%&#*%%*  (&%,  %&%.       .%&%    %&%. .%&#  *%&&&&&%,    (&&%.   *&%.   \r\n"
				+ "  ,%&#%%,  ,%%(  /%%*        %&%.   (&%,  #&%. .%&% *&%,    .%&%.   .%%,    \r\n"
				+ "  (%&&%,  .%&%  ,%&#  #&%.  /&&/   ,%&(  *%%*  #&%, (&%.    (%%*     .      \r\n"
				+ "  %&&%.   (&%,  *%&&&&%%.  .%&%    /%&&&&&%,  *%&/  #&%.   ,&&#    (&%,     \n"+Colors.RESET);
		System.out.println("Ha ganado " + player.getName()+".");
		System.out.println("¡Enhorabuena!");
	}
	
	public void champSelect() {
		
		Scanner keyboard = new Scanner(System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		
		System.out.println("\n|-_-_--_-_--_-_-GameMode-_-_--_-_--_-|");
		System.out.println("|1)-_-_-Humano-_-_-vs-_-_-Humano-_-_-|");
		System.out.println("|2)-_-_-Máquina-_-_vs-_-_-Máquina-_-_|");
		System.out.println("|3)-_-_-Humano-_-_-vs-_-_-Máquina-_-_|");
		System.out.println("|_--_--_-_--_-_--_-_--_-_--_-_--_-_-_|");
		
		int game = input.readIntInRange(1,3);
		
		switch(game) {
			case 1:
				a = new Human();
				System.out.println("Introduzca nombre de jugador:");
				a.setName(keyboard.nextLine());
				
				b = new Human();
				System.out.println("Introduzca nombre del otro jugador:");
				b.setName(keyboard.nextLine());
						
				break;
			case 2:
				a = new Robot();
				((Robot) a).nameSelect();
				b = new Robot();
				((Robot) b).nameSelect();
				break;
			case 3:
				a = new Human();
				a.setName(keyboard.nextLine());
				b = new Robot();
				((Robot) b).nameSelect();
		}
		
	}
	
	public void whoIsFirst() {
		
		Random randomFace = new Random();
		Scanner keyboard = new Scanner(System.in);
		ConsoleInput input = new ConsoleInput(keyboard);
		String cruz = Colors.YELLOW+"                                    ..,.....                                    \r\n"
				+ "                         .*****#@@@@@@&&&@@@@@@@&(****,                         \r\n"
				+ "                    ****%@@(,,,,,,,,,,,,,,,,,,,,,,,**&@@#**,                    \r\n"
				+ "                .**(@@*,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,%,,*#@@*/,                \r\n"
				+ "             .**#@(,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.(,,%,,,*%@#/*             \r\n"
				+ "           ***@(,,,,,,,,,,,,,/*,,,,,,,,,,,,,,,,,,,/,**,,##/,.,***&@//           \r\n"
				+ "         /**@,,,,,,,,,,,,/,,,,,,,.,,#*,,,,,,,,,,,,,,,,%/,#**(..,,*,/@//         \r\n"
				+ "       ,//@*,,,,,,,,,,,,,,,,.,#***,,&,,,,,,,,,,,,,,,,,%,,*/,,(/&,#,*,/@/(       \r\n"
				+ "      //@/,,,,,,,,,/,,@@@@%*,,,,,,,/,,,,,,,,,,,,,,,,,,%,,&//@*//.*#*#**%&/,     \r\n"
				+ "    .*/@*,,,,,,,,**,,,,,,,,,*,,,,,,(,,,,,,,,,,,,,,,,,.#/(**%,*/%(/*,*,,**@//    \r\n"
				+ "   .**@,,,,,,,,,,,,,,,,,,,,,*,,,,,@,,,,,,,,,,,,,,,,,,*,%/%*,#/**/,*/&*,***@(/   \r\n"
				+ "   //@,,,,,,,,*,,,,,,,,,,,,%,,,,,*,,,,,,,,,,,,,,,,,*/*&*//&,,,,,,*((/#,,*,*@/*  \r\n"
				+ "  //@*,,,,,,,*,,,,,,,,,,,,,,,,,,,(,,,,,,,,,,/,%,,,,,,,,%*@*#,,*//#*#/#*,*,,(#/  \r\n"
				+ " .*/@*,,,,,***,*,*,,,,,,,,**,,,,@,,,,,,,,,,#*%&,,,,,,&,(#*,,,%/%&#,(**%*,,,,@(/ \r\n"
				+ " //(/**,,***(,,,,,,,,,,,,#*,,,,/,,,,,,,*(%/*,/#(,,,...,,,,*//#((*/***##(,,,,%(( \r\n"
				+ " //%********(****,,*,*,,***,,,*/,,,*,,,,,,,/////*,,,#,///*//*******,/*.%/,,,/#( \r\n"
				+ " (/%,*******(*********,,/****,@*,***,,,,,,,&%***@&,*#*,(%/(#/(,/,//*/@@@*#/,/#( \r\n"
				+ " (/((******/#*********,%****/(******,**,,,,,*#*,,.#/((%(#*%**/@#@/*/,#*,*#/*%(# \r\n"
				+ "  //@*////////********,,****,(******,,,*#*,*..,**.,@@@*%(#@&,./,&,.(@#///@**@#/ \r\n"
				+ "  #(#(*///////*//****,,/*/*/(,********#(%#%/*/@@****(*/&/#,*/%/(@@@@#/*%***#(#  \r\n"
				+ "   #(&,//(//(/****,**///*//(/*,,****/#(#/#**&/*(****/*****#/*/&&@***##%&***((,  \r\n"
				+ "    ((&,//(/(/#/(/****,,,,,,,,,,,,/%/((&%(/(***********/#,*****/@///%(///*((*   \r\n"
				+ "     #(#/*/(//((#((////////////////////,*,,****///****%**%*(***//,//#/**(/(,    \r\n"
				+ "      %((@,/(((/(((/(/////////////////////*///////////%**&/(/*#/#%#/&/*@(#      \r\n"
				+ "       .((/%,/((((##((*///////**///**//*//*////////*//&(/&/%/(#@(%//*&/#/       \r\n"
				+ "         ,#(*&,/((#((#%%#//////****/*///////*/*///*//(#//*(#@(#(//*&/##         \r\n"
				+ "           .%((%,*(#####%%&%###/*(/*///////////((*(((*#*&@/(#((/,((#(           \r\n"
				+ "              (#(/%**(#########%%%%%%%%#%%#%####(((((#*##&////((#%              \r\n"
				+ "                 /%##*&(,/(#########%%%#####(((((((((((/,/%*(#%                 \r\n"
				+ "                     *&###/(@/.,*///(((((((///***, /@//#%%%                     \r\n"
				+ "                           (%((((((/*,,,**,,*/(((##%#.\n"+Colors.RESET;
		String cara = Colors.YELLOW+"                                     ..,,,,                                     \r\n"
				+ "                          (#(######(###&&&&%%%%%%%%%%#.                         \r\n"
				+ "                    ,#(###*#(********************,*#&&%%%&(,                    \r\n"
				+ "                 ((((/%***************/////**************&%%%%&.                \r\n"
				+ "             ,(/((%*****/****************#*************,*****&%%%&,             \r\n"
				+ "          .#(##%******,((*,****************************(%,,**,*/@#%&(,          \r\n"
				+ "         ((%&/*****************,,,,,,,,,,,,,,,,,,*****************&#(%%*        \r\n"
				+ "       /(#&***************,,,,,,,,,,,,,,,,,,,,,,,,,,,,*************,%((%/,      \r\n"
				+ "     %((&(************,,..%,,,,,,,**,,*(,,,/***.*///(,,,.,**********,*%/#&/,    \r\n"
				+ "    #/&&**,,(*******,,,%/,,./,*,*/((/*,/((/*////////*/((..,,*****,,(,,,(/#&(/.  \r\n"
				+ "   #/@@****/#(****,,.%#/,*.,,,,*.,,,,*,*////*/(////(/////(.,,***,*/##*,*#/#@#/. \r\n"
				+ "  #/@%**********,,./*,,.,,,,,.,,*****,,////*//**////*///*/(%.,.*********,%(#&&/ \r\n"
				+ " ./#&**********,,,,.,#,,,,,,,,,*******////*//*//*///**///(((,,,,**********&(%@(,\r\n"
				+ " (/@(*********,,,,,,,,,,,,.*.,********//////*//*/*/////*///(*,,,.*******,*/(#&&*\r\n"
				+ " /%&**********.,//.,,,,,,,,(#(//(///***//////////////////(((,,,,,,*******,,%#%%*\r\n"
				+ "*/&&****#*****,,...,,,,,,.*/**//*///////(/*/.(//(/**/////(((.,,,,,*******,,&%&&*\r\n"
				+ "**%%****/#****,(,*.,,,,.,//*,,*////////((/*/(/*//(/*/////(%(.,,,,,****,/%,,&&&%*\r\n"
				+ " *#&**********,,,.*(,,,#(###///////*******/.//*#///*///((#...*/,,,*****,*,,&&&%*\r\n"
				+ " //@**********,,,*(&,,,,,,***/////*/*///////((/////////((#.,,((,,,******,,,&&&#*\r\n"
				+ " (/%/**********,,# *@,,,,,, ,///////////////**///////(/(/**************,,,#@&&%/\r\n"
				+ "  /(@************,.,&.****,**///////////////////////((*(&((((((/,*****,,,/&@@%**\r\n"
				+ "  *//&***************,**#//////////////////////////////(@%%####,,,**,,,,,&@&%(/.\r\n"
				+ "   ,(/@/**(////****((%(((####%%/////////////////////((@&%%%%%*,*,//*(***&&&%(// \r\n"
				+ "    ,/((#***********,##%%%%%%%%&&%&#////////////&&&&&&&&&&#*****/*****/&@%#///. \r\n"
				+ "     .#(/&,*/((((##(#(/*#%%%&&&&&&&&&**/****/&&&&&&&&&&&/(#####(((/**&&&#(//*   \r\n"
				+ "       .#((@,*##%%&&&&%&&%#(&&&&&&&&&&**(&&&&&&&&&&&#%%&&&&&&%%%#(/&&&##///     \r\n"
				+ "         .##(&(*%&&&&&&/(&&&&&&%%#%&&&&&&&&&&%%%&&&&&&&/&&&&&%%(#&&&(%///.      \r\n"
				+ "           ,#%((@//&&&&/(@@@&&&&&&&&&&&&&&&&&&&&&&@@@@%//&&&%(&&&%(#///,        \r\n"
				+ "              ,*##(/&%/%%&&&@@@@@@@@@&****@@@@@@@@@@&&&%(#&&&%(#(///*           \r\n"
				+ "                 ,/####((&&((%&&&&&&&&%&&&&&&&&&&&#/%&&&&#/((//(/*              \r\n"
				+ "                    ,/*(%%%%##((#&&&%######%%%%%&&&&&#*((/////*                 \r\n"
				+ "                         .*///***/(#####((/**/////*/////*,\r\n"+Colors.RESET
				+ "                                                                               ";
				
		int faceCoin = randomFace.nextInt(2);
				
		System.out.println("El primer turno será decidido a cara o cruz");
		System.out.println("¿Quién apuesta por cara?");
		System.out.printf("1) %s\n2) %s\n",a.getName(),b.getName());
		int mainDecision = input.readIntInRange(1,2);
				
		if(mainDecision==1) {
			a.setBet(cara);
			b.setBet(cruz);
		}
		else if(mainDecision==2) {
			b.setBet(cara);
			a.setBet(cruz);
		}
		
		if(faceCoin==0) {
			System.out.println(cara);
			if(a.bet==cara) {
				player1=a;
				player2=b;
			}
			else {
				player2=a;
				player1=b;
			}
		}
		else if(faceCoin==1) {
			System.out.println(cruz);
			if(a.bet==cruz) {
				player1=a;
				player2=b;
			}
			else {
				player2=a;
				player1=b;
			}
		}
		player1.setName(Colors.BLUE +player1.getName()+Colors.RESET);
		player2.setName(Colors.YELLOW +player2.getName()+Colors.RESET);
		giveTokens(tablero);
	}
	
	public void giveTokens(Tablero tablero) {
		player1.setToken(tablero.blueCross);
		player2.setToken(tablero.yellowCircle);
	}
}
