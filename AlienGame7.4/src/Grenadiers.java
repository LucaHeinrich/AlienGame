////////////////////////////////////////////////////////////////////////////////
////								GRENADIERS		    					////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Grenadiers: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Grenadiers zu erzeugen.
 *  
 * Eigenschaften:
 *  mittlere Lebensanzahl
 *  geringe Trefferwahrscheinlichkeit
 *  trifft meherer Felder (9)
 *   
 * Methods:
 *  Grenadiers (Constructor):		Initialisierung von Parametern
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Grenadiers extends Players {
	
//////////////////////// Grenadiers(Constructor) ////////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der Klasse Grenadiers.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_G);
	*	setNumLives(PLAYER_G_LIFE);
	*	setEffect(PLAYER_G_EFFECT);
	*/
	public Grenadiers() {
		setSym(PLAYER_G);
		setNumLives(PLAYER_G_LIFE);	
		setEffect(PLAYER_G_EFFECT);
		setShotRadius(PLAYER_G_SHOT_RADIUS);
	}
}
