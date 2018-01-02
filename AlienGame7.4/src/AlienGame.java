////////////////////////////////////////////////////////////////////////////////
////						ALIENGAME      				    				////
////////////////////////////////////////////////////////////////////////////////

/**
 * class AlienGame: 
 * Hauptklasse fuer Projekt AlienGame.
 * Basiert auf X-Com.
 * 
 * Von dieser Klasse aus wird das gesamte Projekt ausgefuehrt
 * 
 * Klassenvariablen:
 *  xMax			:		1. args = Maximum der X-Achse
 *  yMax			:		2. args = Maximum der Y-Achse
 *  aliensNum		:    	3. args = Anzahl der Aliens
 *  
 * Instantiation:
 *  UTY				:		Utils
 *    
 * Methods:
 *  main			:		Hauptmethode des Projekts AlienGame
 *  legend			:		Legende zur Erleichterung
 * 
 * Properties:
 *  getXmax			:		bekommt maximale Spielfeldbreite
 *  setXmax			:		setzt maximale Breite fest
 *  getYmax			:		bekommt maximale Spielfeldhoehe
 *  setYmax			:		setzt maximale Hoehe fest
 *  getAliensNum	:		bekommt Anzahl der ALiens
 *  setAliensNum	:		setzt die Anzahl der Aliens fest
 *  		  
 * @version			:		00.06.00
 * 
 * @author			:		Luca Heinrich 4911584 Gruppe 6a
 */
public class AlienGame implements AlienGameConstants {
	private static int xMax;								// Spielfeld Breite
	private static int yMax;								// Spielfeld Hohe
	private static int aliensNum;							// Anzahl der Aliens

///////////////////////////////// main /////////////////////////////////////////	
/**
 * Diese Methode ist die Hauptmethode.
 * Sie ist der Startpunkt fuer das Projekt.
 * Diese Hauptmethode wird automatisch ausgefuehrt.
 * Von dieser Methode werden alle anderen Klassen und Methoden ausgefuehrt
 * Durch Eingabe der drei Argumente (args), erhalten wir auf 
 * dem Bildschirm ein Spielfeld mit Aliens, einem Spieler und leere Felder.
 * 
 * Parameter:
 *  gameOver		:		true, wenn das Spiel beendet ist
 *  argsOK			:		Array von boolean um alle args einzelnd zu kontrollieren
 *  
 * Instantiation:
 *  inCon			:		InputControls
 *  player			:		Players
 *  map				:		Maps
 *  
 * Arguments sind:
 * 					args[0]			Breite des Spielfeldes (x-Achse, oder in Matrix Spalte)
 * 					args[1]			Hoehe des Spielfeldes (y-Achse, oder in Matrix Zeile)
 * 					args[2]			Anzahl der Aliens im Spiel				
 * 
 * @param			args			Uebergabeparameter (Breite, Hoehe, Anzahl der Aliens) vom Nutzer eingegeben
 * 
 * @return 
 */
	public static void main(String[] args) {		
		boolean gameOver;									// Flag fuer Spielende
		boolean[] argsOK = new boolean[3];					// Array von Args, ob bestaetigt
		
		// Kontrollieren der Eingabeparameter
		InputControls inCon = new InputControls();
		
		
//		args = new String[3];
//		args[0] = "10";
//		args[1] = "10";
//		args[2] = "xx";

		
		
		
		// Kontrolliere Anzahl args
		if (args.length == 3) {								// Korrekte Ausfuehrung wenn args = 3
			argsOK[0] = inCon.ctrlArgs(0, args[0]);
			argsOK[1] = inCon.ctrlArgs(1, args[1]);
			argsOK[2] = inCon.ctrlArgs(2, args[2]);
		} else {											// Anfrage zur Parametereingabe
			argsOK[0] = false;		
			argsOK[1] = false;		
			argsOK[2] = false;		
		}
		
		// Nach Eingabe fragen
		do {												// Solange fragen, bis alle Parameter bestaetigt sind
			argsOK = inCon.argsInput(argsOK);
		} while (!argsOK[0] || !argsOK[1] || !argsOK[2]); 

		// Bestaetigung
		if (!inCon.confArgs())	{ 						// Wenn der Benutzer nicht Bestaetigt, dann beende
			return;
		}	

		// Eingabebefehl anzeigen
		inCon.showArgs(args);

		// Legende											// Legende anzeigen
		legend();
		
		// Spieldfeld initalisieren und implementieren
		Games game = new Games();
		
		// Schleife fuer Spielverlauf (Player + Aliens)
		do {
			gameOver = game.game();
		} while (!gameOver);
		
		// Ende
		return;
	}

//////////////////////////////////// legend ////////////////////////////////////
   /**
	* Diese Methode druckt eine Legende auf dem Spielfeld aus
	* 
	* @return		
	*/
	private static void legend() {
		UTY.prtLn("");
		UTY.prtLn("Legende:");
		UTY.prtLn("	Spielertypen:");
		UTY.prtLn("		S  :  Sniper");
		UTY.prtLn("		T  :  Tank");
		UTY.prtLn("		R  :  Rifleman");
		UTY.prtLn("		G  :  Grenadier");
		UTY.prtLn("		H  :  Heavy");
		UTY.prtLn("		N  :  Soldat");
		UTY.prtLn("		Y  :  Spieler ist tot");
		UTY.prtLn("	Alientypen:");
		UTY.prtLn("		A  :  Alien");
		UTY.prtLn("		X  :  Alien ist tot");
		return;
	}
	
////////////////////////////////////////////////////////////////////////////////
////							Properties									////
////////////////////////////////////////////////////////////////////////////////

//////////////////////////////// getXmax ///////////////////////////////////////
   /**
	* Diese Methode bekommt das Maximum der X-Achse
	* 
	* @return			xMax				
	*/
	public static int getXmax() {
		
		return (xMax);
	}

//////////////////////////////// setXmax ///////////////////////////////////////
   /**
	* Diese Methode setzt den Wert von xMax fest
	* 
	* @param			pxMax			Maximum der X-Achse 
	* 	
	* @return				
	*/
	public static void setXmax(int pxMax) {
		xMax = pxMax;
		
		return;
	}

//////////////////////////////// getYmax ///////////////////////////////////////
   /**
	* Diese Methode bekommt das Maximum der Y-Achse
	* 
	* @return			yMax				
	*/
	public static int getYmax() {
		
		return (yMax);
	}

//////////////////////////////// setYmax ///////////////////////////////////////
   /**
	* Diese Methode setzt den Wert von yMax fest
	* 
	* @param			pyMax			Maximum der Y-Achse 
	* 	
	* @return				
	*/
	public static void setYmax(int pyMax) {
		yMax = pyMax;
		
		return;
	}

///////////////////////////// getAliensNum /////////////////////////////////////
   /**
	* Diese Methode bekommt die Anzahl der Aliens
	* 
	* @return			aliensNum				
	*/
	public static int getAliensNum() {
		
		return (aliensNum);
	}

///////////////////////////// setAliensNum /////////////////////////////////////
   /**
	* Diese Methode setzt die Anzahl der der Aliens fest
	* 
	* @param			paliensNum			Anzahl Aliens
	* 	
	* @return				
	*/
	public static void setAliensNum(int paliensNum) {
		aliensNum = paliensNum;
		
		return;
	}
}
