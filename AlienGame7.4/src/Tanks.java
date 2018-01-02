////////////////////////////////////////////////////////////////////////////////
////								TANKS			    					////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Tanks: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Tanks zu erzeugen.
 *  
 * Eigenschaften:
 *  sehr hohe Lebensanzahl
 *  sehr hohe Trefferwahrscheinlichkeit
 *  trifft meherer Felder (9)

 * Methods:
 *  Tanks (Constructor)		:		Initialisierung von Parametern
 * 
 * @version				  	:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Tanks extends Players {

/////////////////////////// Tanks(Constructor) /////////////////////////////////	
   /**
	* Diese Methode ist der Konstruktor der Klasse Tanks.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_T);
	*	setNumLives(PLAYER_T_LIFE);
	*	setEffect(PLAYER_T_EFFECT);
	*/
	public Tanks() {
		setSym(PLAYER_T);
		setNumLives(PLAYER_T_LIFE);	
		setEffect(PLAYER_T_EFFECT);
		setShotRadius(PLAYER_T_SHOT_RADIUS);
	}
}
