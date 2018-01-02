////////////////////////////////////////////////////////////////////////////////
////								RIFLEMEN		    					////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Riflemen: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Riflemen zu erzeugen.
 *  
 * Eigenschaften:
 *  mittlere Lebensanzahl
 *  gute Trefferwahrscheinlichkeit
 *
 * Methods:
 *  Riflemen (Constructor)	:		Initialisierung von Parametern
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Riflemen extends Players {
	
/////////////////////// Riflemen(Constructor) /////////////////////////////////	
   /**
	* Diese Methode ist der Konstruktor der Klasse Riflemen.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_R);
	*	setNumLives(PLAYER_R_LIFE);
	*	setEffect(PLAYER_R_EFFECT);
	*/
	public Riflemen() {
		setSym(PLAYER_R);
		setNumLives(PLAYER_R_LIFE);	
		setEffect(PLAYER_R_EFFECT);
		setShotRadius(PLAYER_R_SHOT_RADIUS);
	}
}
