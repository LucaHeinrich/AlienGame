//////////////////////////////////////////////////////////////////////////////////
////								SNIPERS			    					//////
//////////////////////////////////////////////////////////////////////////////////

/**
 * class Snipers: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse erbt von der Playersklasse welche wiederrum von der 
 * Characterklasse erbt.
 * Sie deklariert in einem Konstruktor mehrere Parameter, welche 
 * in der Maps Klasse dann benoetigt werden um die Eigenschaften 
 * eines Snipers zu erzeugen.
 *  
 * Eigenschaften:
 *  niedrige Lebensanzahl
 *  sehr hohe Trefferwahrscheinlichkeit
 *
 * Methods:
 *  Snipers (Constructor)	:		Initialisierung von Parametern
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class Snipers extends Players {

///////////////////////// Snipers(Constructor) /////////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der Klasse Snipers.
	* In diesem Konstruktor werden folgende Parameter aus
	* dem Interface AlienGameConstants initialisiert:
	* 
	* Parameter:
	*  setSym(PLAYER_S);
	*	setNumLives(PLAYER_S_LIFE);
	*	setEffect(PLAYER_S_EFFECT);
	*/
	public Snipers() {
		setSym(PLAYER_S);
		setNumLives(PLAYER_S_LIFE);	
		setEffect(PLAYER_S_EFFECT);
		setShotRadius(PLAYER_S_SHOT_RADIUS);
	}
}
