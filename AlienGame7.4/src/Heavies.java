////////////////////////////////////////////////////////////////////////////////
////								HEAVIES			    					////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Heavies: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Heavies zu erzeugen.
 *  
 * Eigenschaften:
 *  sehr hohe Lebensanzahl
 *  sehr geringe Trefferwahrscheinlichkeit
 *  
 * Methods:
 *  Heavies (Constructor)	:		Initialisierung von Parametern
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Heavies extends Players {
	
/////////////////////// Heavies(Constructor) /////////////////////////////////	
   /**
	* Diese Methode ist der Konstruktor der Klasse Heavies.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_H);
	*	setNumLives(PLAYER_H_LIFE);
	*	setEffect(PLAYER_H_EFFECT);
	*/
	public Heavies() {
		setSym(PLAYER_H);
		setNumLives(PLAYER_H_LIFE);	
		setEffect(PLAYER_H_EFFECT);
		setShotRadius(PLAYER_H_SHOT_RADIUS);
	}
}
