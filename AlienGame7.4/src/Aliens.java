////////////////////////////////////////////////////////////////////////////////
//// 							ALIENS										////
////////////////////////////////////////////////////////////////////////////////

import static java.lang.Math.random;

/**
 * class Aliens: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Charactersklasse.
 * Sie deklariert in einem Konstruktur mehrere Parameter um
 * in Maps die jeweiligen Konstanten zu bekommen, indem man ein 
 * Objekt erstellt.
 * 
 * Methoden:
 *  Aliens (Constructor)	:		Initialsierung von Parametern		
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Aliens extends Characters {

////////////////////////// Aliens(Constructor) /////////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der Klasse Aliens.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(ALIEN);
	*	setSymDead(ALIEN_DEAD);
	*	setNumLives(ALIEN_LIFE);
	*	setEffect(ALIEN_EFFECT);
	*/
	public Aliens() {
		setSym(ALIEN);
		setSymDead(ALIEN_DEAD);
		setNumLives(ALIEN_LIFE);
		setEffect(ALIEN_EFFECT);
		setMoveMax(ALIEN_MOVE_MAX);
	}
	
////////////////////////////move ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @param		map			xxx
	* 
	* @return		movement.toString()
	*/
	public String move(Maps map) {	
		int stepMax;
		int stepNum;
		int step;
		StringBuilder movement = new StringBuilder("");
		String[] pos = new String[4];
		
		stepMax = (int) (random() * ALIEN_MOVE_MAX) + 1;
		pos = canMove(map, this.getXY());	
		stepNum = 1;
		while (pos.length > 0 && stepNum <= stepMax) {
			step = (int) (random() * pos.length);
			movement.append(pos[step]);
			
			// move
			switch (pos[step]) {
				case "w" :
					
			}
			
			pos = canMove(map, this.getXY());		
			stepNum++;
		}
		
		return (movement.toString());
	}
}
