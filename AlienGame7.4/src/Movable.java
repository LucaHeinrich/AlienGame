////////////////////////////////////////////////////////////////////////////////
////						MOVABLE      				    				////
////////////////////////////////////////////////////////////////////////////////

/**
 * Interface AlienGameConstants: 
 * Interface fuer Projekt AlienGame.
 * Basiert auf X-Com.
 * 
 * Dieses Interface ist dafuer da um alle Konstanten im gesamten
 * Projekt zu deklarieren.
 * Somit kann man schnell Aenderungen vornehmen, wie zum Beispiel
 * die Zeichen fuer Spieler und Aliens aendern oder die Zeichen 
 * fuer die Grenze, etc.
 * 
 * @version			:		00.06.00
 * 
 * @author			:		Luca Heinrich 4911584 Gruppe 6a
 */
interface Movable extends AlienGameConstants {

////////////////////////////move ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @param			map			xxx
	*
	* @return
	*/
	String move(Maps map); 		// abstract

////////////////////////////move ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @param			map			xxx
	* @param			cXY			xxx
	* 
	* @return			pos
	*/
	default String[] canMove(Maps map, int[] cXY) {
		String[] pos = new String[4];
		int[] maxXY = new int[2];
		int[] xy = new int[2];
		int i;
		
		maxXY[0] = AlienGame.getXmax();
		maxXY[1] = AlienGame.getYmax();
		
		i = 0;
		
		// W
		xy[0] = cXY[0];
		xy[1] = cXY[1] + 1;
		if (cellCheck(map, maxXY, xy)) {
			pos[i] = "w";
			i++;
		}
		
		// S
		xy[0] = cXY[0];
		xy[1] = cXY[1] - 1;
		if (cellCheck(map, maxXY, xy)) {
			pos[i] = "s";
			i++;
		}
		
		// A
		xy[0] = cXY[0] - 1;
		xy[1] = cXY[1];
		if (cellCheck(map, maxXY, xy)) {
			pos[i] = "a";
			i++;
		}
		
		// D
		xy[0] = cXY[0] + 1;
		xy[1] = cXY[1];
		if (cellCheck(map, maxXY, xy)) {
			pos[i] = "d";
			i++;
		}

		return (pos);
	}

////////////////////////////move ////////////////////////////////////////
   /**
	* Diese Methode generiert zufaellig eine Zahl zwischen 0 und 
	* der Anzahl der unterschiedlichen PLlayertypen - 1, und diese 
	* Zahl wird dann in der Mapsklasse einem Spieler zugewiesen.
	* 
	* @param			map			xxx
	* @param			maxXY		xxx
	* @param			xy			xxx
	* 
	* @return			pos
	*/
	static boolean cellCheck(Maps map, int[] maxXY, int[] xy) {
		boolean pos;
		
		if (xy[0] >= 0 &&  xy[0] <= maxXY[0] && xy[1] >= 0 
			&& xy[1] <= maxXY[1] && map.getCell(xy) == FIELD) {
			pos = true;
		} else {
			pos = false;
		}
		
		return (pos);
	}
}
