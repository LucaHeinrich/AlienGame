////////////////////////////////////////////////////////////////////////////////
////						ALIENGAMECONSTANTS      		   				////
////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

/**
 * Interface AlienGameConstants: 
 * Interface fuer Projekt AlienGame.
 * Basiert auf X-Com.
 * 
 * Dieses Interface ist dafuer da um alle Konstanten im gesamten
 * Projekt zu deklarieren.
 * Somit kann man schnell Aenderungen vornehmen, wie zum Beispiel
 * die Zeichen fuer Spieler und Aliens aendern oder die Zeichen 
 * fuer die Grenze, etc.
 * 
 * Konstanten:
 * 	PROGRAM			:		Projektname, Standard "AlienGame"
 * 	FIELD			:		Zeichen fuer eine leere Zelle im Spielfeld
 *  PLAYER_TYPE_MAX		:		Anzahl Spielertypen
 * 	PLAYER_S		:		Zeichen im Spielfeld SNIPER
 *  PLAYER_S_LIFE	:		Lebensanzahl SNIPER
 *  PLAYER_S_EFFECT	:		Effektivitaet zu toeten SNIPER
 * 	PLAYER_T		:		Zeichen im Spielfeld TANK
 *  PLAYER_T_LIFE	:		Lebensanzahl TANK
 *  PLAYER_T_EFFECT	:		Effektivitaet zu toeten TANK
 * 	PLAYER_R		:		Zeichen im Spielfeld RIFLEMAN
 *  PLAYER_R_LIFE	:		Lebensanzahl RIFLEMAN
 *  PLAYER_R_EFFECT	:		Effektivitaet zu toeten RIFLEMAN
 *  PLAYER_N		:		Zeichen im Spielfeld SOLDAT
 *  PLAYER_N_LIFE	:		Lebensanzahl SOLDAT
 *  PLAYER_N_EFFECT	:		Effektivitaet zu toeten SOLDAT
 *  PLAYER_G		:		Zeichen im Spielfeld GRENADIENER
 *  PLAYER_G_LIFE	:		Lebensanzahl GRENADIENER	
 *  PLAYER_G_EFFECT	:		Effektivitaet zu toeten GRENADIER
 * 	PLAYER_H		:		Zeichen im Spielfeld HEAVY
 *  PLAYER_H_LIFE	:		Lebensanzahl HEAVY
 *  PLAYER_H_EFFECT	:		Effektivitaet zu toeten HEAVY
 *  PLAYER_DEAD		:		Zeichen fuer toten Spieler
 * 	ALIEN			:		Zeichen fuer Aliens im Spielfeld
 *  ALIEN_LIFE		:		Lebensanzahl ALIENS
 *  ALIEN_EFFECT	:		Effektivitaet zu toeten ALIEN
 *  ALIEN_DEAD		:		Zeichen fuer ein totes ALien
 * 	CORNER			:		Zeichen fuer die Ecke der Spielfeldbegrenzung
 * 	HORIZ			:		Zeichen fuer die horizontale Spielfeldbegrenzung
 * 	VERT			:		Zeichen fuer die vertikale Spielfeldbegrenzung
 * 	CONFIRMATION	:		Wenn CONFIRMATION = true, es gibt eine Bestaetigung
 * 							Wenn CONFIRMATION = false, es gibt keine Bestaetigung		
 * 	RND_PREC		: 		Praezision der Wahrscheinlichkeit
 * 
 * @version			:		00.06.00
 * 
 * @author			:		Luca Heinrich 4911584 Gruppe 6a
 */
interface AlienGameConstants {
	
	String PROGRAM			= "AlienGame";
	
	// Players
	int PLAYER_TYPE_MAX	 	 = 6;
	char PLAYER_DEAD 		 = 'Y';
	int PLAYER_MOVE_MAX      = 3;
		
	char PLAYER_S		 	 = 'S';
	int PLAYER_S_LIFE		 = 10;
	double PLAYER_S_EFFECT 	 = 0.050;
	int PLAYER_S_SHOT_RADIUS = 0;
	
	char PLAYER_T 			 = 'T';	
	int PLAYER_T_LIFE		 = 30;
	double PLAYER_T_EFFECT	 = 0.125;
	int PLAYER_T_SHOT_RADIUS = 2;
	
	char PLAYER_R 			 = 'R';	
	int PLAYER_R_LIFE		 = 10;
	double PLAYER_R_EFFECT 	 = 0.500;
	int PLAYER_R_SHOT_RADIUS = 0;

	char PLAYER_N			 = 'N';	
	int PLAYER_N_LIFE		 = 15;
	double PLAYER_N_EFFECT   = 1.000;
	int PLAYER_N_SHOT_RADIUS = 0;

	char PLAYER_G			 = 'G';	
	int PLAYER_G_LIFE		 = 15;		
	double PLAYER_G_EFFECT   = 1.875;
	int PLAYER_G_SHOT_RADIUS = 1;

	char PLAYER_H			 = 'H';	
	int PLAYER_H_LIFE		 = 20;
	double PLAYER_H_EFFECT 	 = 2.625;
	int PLAYER_H_SHOT_RADIUS = 0;

	// Alien
	char ALIEN_DEAD 		= 'X';
	int ALIEN_MOVE_MAX      = 2;
	
	char ALIEN 				= 'A';
	int ALIEN_LIFE 			= 1;
	double ALIEN_EFFECT 	= 1.125;
	
	// Spielfeldbegrenzung
	char FIELD				= ' ';
	char CORNER 			= '+';
	char HORIZ 				= '-';
	char VERT 				= '|';
	
	boolean CONFIRMATION 	= true;
	double RND_PREC 		= 0.999999; 
	
	// Common Objects
	Scanner INP = new Scanner(System.in);
	Utils UTY = new Utils();
	InputControls INCON = new InputControls();
}
