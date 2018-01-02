////////////////////////////////////////////////////////////////////////////////
////						 	PLAYERS    				    				////
////////////////////////////////////////////////////////////////////////////////

import static java.lang.Math.random;

/**
 * class Players: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Characterklasse und vererbt wiederrum an die 
 * jeweiligen Spielertypenklassen. 
 * Sie berechnet welcher Spielertyp verwendet werden soll.
 * 
 * Klassenvariablen:
 *  playerType				:		Berechneter Spielertyp
 *  
 * Methoden:
 *  Players (Constructor)	:		Initialisierung von Parametern
 *  playerType				:		Zufaellige Berechnung zur Auswal eines Spielertyps
 * 
 * Properties:		
 *  getPlayerType			:		bekommt playerType 
 *  
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Players extends Characters {
	private int playerType;

///////////////////////// Players(Constructor) /////////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der Klasse Players.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSymDead(PLAYER_DEAD);
	*/
	public Players() {
		setSymDead(PLAYER_DEAD);
		setMoveMax(PLAYER_MOVE_MAX);
	}
	
//////////////////////////// playerType ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @return
	*/
	public void  playerType() {	
		playerType = (int) (random() * PLAYER_TYPE_MAX);
		
		return;
	}

//////////////////////////// move ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @param			map			xxx
	* 
	* @return			movement.toString()
	*/
	public String move(Maps map) {		// Players
		int stepNum;
		String step;
		StringBuilder movement = new StringBuilder("");
		
		UTY.prtLn("");
		
		stepNum = 0;
		do {
			stepNum++;
			step = INCON.inputMove(stepNum);
			movement.append(step);
		} while ((!step.equals("")) && (stepNum < PLAYER_MOVE_MAX));
		
		return (movement.toString());
	}

////////////////////////////////////////////////////////////////////////////////
////							Properties									////
////////////////////////////////////////////////////////////////////////////////

////////////////////////// getPlayerType ///////////////////////////////////////
   /**
	* Diese Methode bekommt die berechneten PlayerType Zahl
	* 
	* @return			playerType				
	*/
	public int getPlayerType() {
		
		return (playerType);
	}
}
