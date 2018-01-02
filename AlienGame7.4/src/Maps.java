////////////////////////////////////////////////////////////////////////////////
////								MAPS	     				    		////
////////////////////////////////////////////////////////////////////////////////

import static java.lang.Math.*;

/**
 * class Maps: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * KLassenvariablen:
 *  map					:		Spielfeld
 *  maxXY				:		Maximum fuer x und y
 *  aliensMax			:		Anfangswert der Aliens
 *  playerType			:		Playertyp
 *  fmt					:		Format zur Koordinatenausgabe
 *
 * Instantiation:
 *  soldat				:		Soldiers
 *  sniper				:		Snipers
 *  tank				:		Tanks
 *  rifleman			:		Riflemen
 *  grenadier			:		Grenadiers
 *  heavy				:		Heavies 
 *  alien				:		Aliens
 *  inCon				:		inputControls
 *  UTY					:		Utils
 *  
 * Methods:
 *  Maps (Constructor)	:		Ausfuehrung der Methoden
 *  game				:		Spielmethode
 *  alienUpdate			:		aktuelle Information von Aliens
 *  playerUpdate		:		aktuelle Information vom Player
 *  gameEnd				:		Spielende
 *  mapInit			 	:		Spielfeldinitalisierung
 *  mapElemPlayer		:		Hinzufuegen eines Players	
 *  mapElemAliens		:		Hinzufuegen von Aliens
 *  mapElem				:		Hinzufuegen von Elementen 
 *  mapPrint			:		Zeigt Spielfeld auf dem Bildschirm	
 *  mapLimitHoriz		:		Horizontale Spielfeldgrenze
 *  mapLimtVert			:		Vertikale Spielfeldgrenze
 * 
 * Properties:
 *  toString			:		Override toString Methode
 * 
 * @version				:		00.06.00
 * 
 * @author				:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Maps implements AlienGameConstants {
	private char[][] map;
	private int[] maxXY = new int[2];
	private int aliensMax;
	private Players player; 
	private Aliens[] alien;


///////////////////////////// Maps(Constructor) ////////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der class Maps.
	* Alle Methoden der Klasse werden vom Konstruktor aus aufgerufen.
	* Das ist dazu da, damit man in der Hauptklasse eine einfache
	* Schreibweise hat und um schnell ein Override mit der toString
	* Methode zu machen. Damit werden alle Methoden aus dem Konstruktor
	* jedes mal, wenn ein Override ausgefuehrt wird, aufgerufen.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  
	* @param			pMaxXY			xxx
	* @param			pAliensMax		xxx	
	* @param			pPlayer			xxx
	* @param			pAlien			xxx	
	*/
	public Maps(int[] pMaxXY, int pAliensMax, Players pPlayer, Aliens[] pAlien) {
		int i;
		
		maxXY[0] = pMaxXY[0];
		maxXY[1] = pMaxXY[1];
		aliensMax = pAliensMax;

		// Player
		player = pPlayer;

		// Aliens
		alien = new Aliens[aliensMax];						// Speicheradresse
		for (i = 0; i < aliensMax; i++) {	
			alien[i] = new Aliens();
			alien[i] = pAlien[i];
		}
		
		// Spielfeld Dimension
		map = new char[(maxXY[1] + 1)][(maxXY[0] + 1)];		// Zuweisung
		
		// Spielfeld konfigurieren
		mapInit();											// Spielfeldinitialisierung mit leerer Zelle
		
		// Spieler
		mapElemPlayer();									// Platzierung eines zufaelligen Players
		
		// Aliens
		mapElemAliens();									// Platzierung der Anzahl von Aliens (args[2])		
	}
	
////////////////////////////////////////////////////////////////////////////////
//// 				 			BATTLEFIELD									////
////////////////////////////////////////////////////////////////////////////////

///////////////////////////////// mapInit //////////////////////////////////////
   /**
	* Diese Methode macht eine Spielfeldinitialisierung. Das 
	* bedeutet in jede Zelle des Spielfeldes wird ein leeres
	* Feld initialisiert.
	* 
	* Parameter:
	*  column			:		Laufvariable fuer Spaltenanzahl
	*  row				:		Laufvariable fuer Zeilenanzahl
	*  
	* @return				
	*/
	private void mapInit() {
		int column;				// Laufvariable fuer maxXY[0]
		int row;				// Laufvariable fuer maxXY[1]	

		for (row = 0; row <= maxXY[1]; row++) {				// Positionierung fuer alle Zellen
			for (column = 0; column <= maxXY[0]; column++) {
				map[row][column] = FIELD;						// Im gesamten Spielfeld Leertaste
			}
		}
		
		return;
	}	

///////////////////////////////// mapElemPlayer ////////////////////////////////
   /**
	* Diese Methode fuegt das Playerelement in das Spielfeld
	* hinzu. Das jeweilige Playersymbol wird dafuer verwendet.
	* 
	* @return				
	*/
	private void mapElemPlayer() {
		mapElem(player.getSym(), true, 1);
		
		return;
	}

///////////////////////////////// mapElemAliens ////////////////////////////////
   /**
	* Diese Methode fuegt das Alienelement in das Spielfeld
	* hinzu. Das Symbol fuer ein Alien wird dafuer verwendet.
	* Alle Aliens haben das selbe Symbol.
	* 
	* @return				
	*/
	private void mapElemAliens() {
		mapElem(ALIEN, false, aliensMax);
		
		return;
	}

///////////////////////////////// mapElem //////////////////////////////////////
   /**
	* Diese Methode fuegt Elemente ins Spielfeld hinzu (z.B. ALIENS 
	* oder PLAYER). Diese Elemente werden zufaellig auf dem Spielfeld
	* platziert.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  xRandom			:		Zufallskoordinate fuer maxXY[0]
	*  yRandom			:		Zufallskoordinate fuer maxXY[1]
	*  pXY				:		Koordinaten des hinzufuegenden Elements
	*  
	* @param			pElem			konkretes Elemet
	* @param			pPlayer			true  : Player
	* 									false : Alien
	* @param			pNum			Anzahl der Elemente
	* 
	* @return				
	*/
	private void mapElem(char pElem, boolean pPlayer, int pNum) {
		int i;												
		int xRandom;										
		int yRandom;									
		int[] pXY;
		pXY = new int[2];

		for (i = 1; i <= pNum; i++) {							// Platzieren pNum Elemente	
			do {
				xRandom = (int) (random() * (maxXY[0] + 1));	// Berechnung eines Zufallwerts fuer x- Koordinate	
				yRandom = (int) (random() * (maxXY[1] + 1));	// Berechnung eines Zufallwerts fuer y- Koordinate
			} while (map[yRandom][xRandom] != FIELD);			// Wiederholen wenn die Zufallskoordinate nicht leer ist
			
			map[yRandom][xRandom] = pElem;						// Platzieren der Elemente
			
			pXY[0] = xRandom;
			pXY[1] = yRandom;
			
			if (pPlayer) {
				player.setXY(pXY);
			} else {
				alien[i - 1].setXY(pXY);
			}
		}		
		
		return;
	}

///////////////////////////////// mapPrint /////////////////////////////////////
   /**
	* Diese Methode zeigt das Spielfeld auf dem Bildschirm an.
	* Dazu werden die beiden Begrenzungsmethoden benutzt.
	* 
	* @return
	*/
	public void mapPrint() {

		UTY.prtLn("");
		UTY.prtLn("Spielfeld:");
		mapLimitHoriz(false);	
		mapLimitVert();
		mapLimitHoriz(true);
		
		return;
	}

///////////////////////////////// mapLimitHoriz ///////////////////////////////
   /**
	* Diese Methode gibt unsere Spielfeldbegrenzung in
	* horizontaler Richtung an, oben und unten.
	* Ausserdem eine X-Achsenbeschriftung zur Orientierung.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  j				:		Laufvariable
	*  jString			:
	*  rowString		:
	*  columnString	:
	*  rowLength		:
	*  columnLength	:
	*  
	* @param			pCoord			
	* 
	* @return		
	*/
	private void mapLimitHoriz(boolean pCoord) {
		int i;			 
		int j;			 
		String jString;
		String rowString;
		String columnString;
		int rowLength;
		int columnLength;
		
		rowString = String.valueOf(maxXY[1]);
		rowLength = rowString.length();
		
		columnString = String.valueOf(maxXY[0]);
		columnLength = columnString.length();
		
		UTY.prt(String.format("%" + (rowLength + 1) + "s", " "));				// Space vor Ecke
		UTY.prt(String.valueOf(CORNER));											// Linke Ecke
		for (i = 0; i <= maxXY[0]; i++) {											// Horizontale Line, Anzahl an Zeichen
			UTY.prt(String.valueOf(HORIZ));
		}
		UTY.prtLn(String.valueOf(CORNER));										// Rechte Ecke

		if (pCoord) {
			for (i = columnLength - 1; i >= 0; i--) {
				UTY.prt(String.format("%" + (rowLength + 2) + "s", " "));		// Space vor Ecke
				for (j = 0; j <= maxXY[0]; j++) {
					jString = String.valueOf(j);
					jString = String.format("%-" + columnLength + "s", jString);
					UTY.prt(jString.substring(columnLength - 1 - i, columnLength - i));			
				}
				UTY.prtLn("");	
			}
		}	
		
		return;
	}

///////////////////////////////// mapLimitVert ////////////////////////////////
   /**
	* Diese Methode gibt unsere Spielfeldbegrenzung in
	* vertikaler Richtung an, links und rechts.
	* Ausserdem eine Y-Achsenbeschriftung zur Orientierung.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  column			:		Spaltenlaufvariable fuer maxXY[0]
	*  row				:		Zeilenvariable fuer maxXY[1]
	*  rowLength		:		Zeilenlaenge
	*  rowString		:		String der Zeilenanzahl
	*  
	* @return		
	*/
	private void mapLimitVert() {
		int i;
		int column;													
		int row;													
		int rowLength;
		String rowString;
				
		rowString = String.valueOf(maxXY[1]);
		rowLength = rowString.length();

		for (row = maxXY[1]; row >= 0; row--) {						// feste Nummer von Durchlauefen
			
			for (i = 1; i < rowLength; i++) {
				if (row < pow(10, i)) {
					UTY.prt(" ");
				}
			}
			
			UTY.prt(row + " ");
						
			UTY.prt(String.valueOf(VERT));							// Linke Grenze
			for (column = 0; column <= maxXY[0]; column++) {
				UTY.prt(String.valueOf(map[row][column]));					
			}
			UTY.prtLn(String.valueOf(VERT));						// Rechte Grenze
		}
		
		return;		
	}

///////////////////////////// get cell /////////////////////////////////////////
	/**
	* Diese Methode ueberschreibt das Spielfeld mit der aktuellen Map.
	* Dies ist eine spezielle Methode von Java.
	* 
	* @param			cellXY			xxx
	* 
	* @return			cell
	*/
	public char getCell(int[] cellXY) {
		char cell;
		
		cell = map[cellXY[1]][cellXY[0]];
		
		return (cell);
	}

///////////////////////////// set cell /////////////////////////////////////////
   /**
	* Diese Methode ueberschreibt das Spielfeld mit der aktuellen Map.
	* Dies ist eine spezielle Methode von Java.
    *
	* @param			cellXY			xxx
	* @param			cell			xxx
	* 	 
	* @return
	*/
	public void setCell(int[] cellXY, char cell) {
		map[cellXY[1]][cellXY[0]] = cell;
		
		return;
	}

////////////////////////////////////////////////////////////////////////////////
////							Properties									////
////////////////////////////////////////////////////////////////////////////////	

///////////////////////////// toString /////////////////////////////////////////
   /**
	* Diese Methode ueberschreibt das Spielfeld mit der aktuellen Map.
	* Dies ist eine spezielle Methode von Java.
	* 
	* @return			""
	*/
	public String toString() {
		this.mapPrint();
		
		return ("");
	}
}
