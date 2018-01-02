////////////////////////////////////////////////////////////////////////////////
////								SOLDIERS		    					////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Soldiers: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Soldiers zu erzeugen.
 *  
 * Eigenschaften:
 *  mittlere Lebensanzahl
 *  mitllere Trefferwahrscheinlichkeit
 *
 * Methods:
 *  Soldiers (Constructor)	:		Initialisierung von Parametern
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Soldiers extends Players {
	
/////////////////////// Soldiers(Constructor) /////////////////////////////////	
   /**
	* Diese Methode ist der Konstruktor der Klasse Soldiers.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_N);
	*	setNumLives(PLAYER_N_LIFE);
	*	setEffect(PLAYER_N_EFFECT);
	*/
	public Soldiers() {
		setSym(PLAYER_N);
		setNumLives(PLAYER_N_LIFE);	
		setEffect(PLAYER_N_EFFECT);
		setShotRadius(PLAYER_N_SHOT_RADIUS);
	}
}
