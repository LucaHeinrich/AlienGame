////////////////////////////////////////////////////////////////////////////////
//// 							GAMES										////
////////////////////////////////////////////////////////////////////////////////

/**
 * class Games: 
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
public class Games implements AlienGameConstants {
	// Spielfeld 
	private int[] maxXY = new int[2];
	private int aliensMax;
	
	// Player
	private Players player; 
	
	// Aliens
	private Aliens[] alien;
	
	// SpielFeld
	private Maps map;

	// local
	private String fmt;
	private int playerType;

///////////////////////////// Games (Constructor) ////////////////////////////////
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
	*  maxDigit		:		maximale Zahlenstelle
	*/
	public Games() {
		int i;	
		int maxDigit;
		
		maxXY[0] = AlienGame.getXmax();
		maxXY[1] = AlienGame.getYmax();
		aliensMax = AlienGame.getAliensNum();
		
		if (maxXY[0] > maxXY[1]) {
			maxDigit = String.valueOf(maxXY[0]).length();
		} else {
			maxDigit = String.valueOf(maxXY[1]).length();			
		}
		
		fmt = UTY.strDigit("0", maxDigit);
		
		// Spielertyp
		Players xPlayer = new Players();						// Zufallige Auswahl eines Players
		xPlayer.playerType();
		playerType = xPlayer.getPlayerType();
		
		// Player
		// case 0 :	Soldat 
		// case 1 :	Sniper
		// case 2 :	Tank
		// case 3 :	Rifleman 
		// case 4 :	Grenadier
		// case 5 :	Heavy
		switch (playerType) {
			case 1 :
				player = new Snipers();
				break;		
			case 2 :
				player = new Tanks();
				break;		
			case 3 :
				player = new Riflemen();
				break;		
			case 4 :
				player = new Grenadiers();
				break;		
			case 5 :
				player = new Heavies();
				break;		
			default : 	
				player = new Soldiers();
				break;
		}
		
		// Aliens
		alien = new Aliens[aliensMax];						// Speicheradresse
		for (i = 0; i < aliensMax; i++) {	
			alien[i] = new Aliens();						// Zuweisung 
		}
		
		// map Generierung 
		map = new Maps(maxXY, aliensMax, player, alien);										// Zuweisung
	}

////////////////////////////////////////////////////////////////////////////////
////							Spielen									  	 ///
////////////////////////////////////////////////////////////////////////////////

///////////////////////////////// game /////////////////////////////////////////
   /**
	* Diese Methode steuert die Interaktionen mit Player und Alien.
	* Ausserdem werden hier alle Angriffsmethoden aushefuehrt. 
	* Hier werden relevante Returns der anderen Methoden ausgewertet,
	* das bedeutet werden die Returns benoetigt um eine interaktive   
	* Spielsituation zu schaffen. Zudem wird hier die besondere
	* Eigenschaft der Grenadier und Tanks erzeugt (mehrere Felder
	* treffen).
	* Und es wird eine Vorschlag gegeben, welches Alien man am 
	* besten angreifen sollte. Diese Alien hat die kuerzeste 
	* Distanz zum Player und somit besteht auch die grosste Chance
	* dieses Alien zu toeten.
	* Zuerst ist also der Spieler am Zug, er macht seinen Angriff
	* und nun muss kontrolliert werden, ob es noch ein lebendiges 
	* Alien gibt, wenn ja, dann wird das Spiel fortgesetzt und die 
	* Aliens sind am Zug, wenn es jedoch kein lebendiges Alien mehr 
	* gibt, hat der Player gewonnen und das Spiel wird beendet.
	* 
	* Parameter:
	*  i			:		Laufvariable
	*  xy			:		Koordinaten Player/Aliens
	*  xyGT			:		Koordinaten Grenadier/Tank
	*  iMin			:		naehstes Alien
	*  dist			:		Distanz zwischen 
	*  distMin		:		Niedrigste Distanz
	*  alienLives	:		ein Alien lebendig
	*  dead			:		true  : tot
	*  						false : lebendig
	*  
	* Instantiation:
	*  cha				:		Characters
	*   
	* @return			gameEnd(alienLives) 
	*/
	public boolean game() {
		int i;
		int[] xy = new int[2];
		int iMin;
		long dist;
		long distMin;
		boolean alienLives;
		
		// Spielfeld ausdrucken
		map.mapPrint();										// direkte Methode 
		
		// Player ist am Zug
		UTY.prtLn("");	
		UTY.prtLn("Position des Spielers	:  (" + UTY.numFmt(fmt, player.getXY()[0]) + ", " 
												  + UTY.numFmt(fmt, player.getXY()[1]) + ")");
		UTY.prtLn("Leben des Spielers 	:  " + player.getNumLives());
		
		// Movement
		UTY.prtLn("move : " + player.move(map));

		// Attack
		UTY.prtLn("");
		UTY.prtLn("Welches Alien wollen Sie angreifen?");
		
		// Naehstes Alien
		iMin = 0;
		distMin = 999999;
		for (i = 0; i < aliensMax; i++) {
			dist = player.charDist(player.getXY(), alien[i].getXY());
			if (alien[i].getNumLives() > 0) {
				if (dist < distMin) {
					distMin = dist;
					iMin = i;
				}	
			}
		}
		
		UTY.prtLn("Vorschlag	:  Alien (" + UTY.numFmt(fmt, alien[iMin].getXY()[0]) + ", "
											+ UTY.numFmt(fmt, alien[iMin].getXY()[1]) + ")");		
		
		// Coordinaten fuer attack
		xy = INCON.inputCoord(map);
		
		// player attack (Einschlag von Schuss abhaengig vom Radius)
		playerAttack(xy);
				
		// alien attack
		alienLives = alienAttack();
		
		// Spielende, Wer ist der Gewinner?
		return (gameEnd(alienLives));
	}

///////////////////////////////// playerAttack ///////////////////////////////////
   /**
	* Diese Methode aktualisiert ein Alien. Entweder das Alien
	* wurde getroffen und ist tot, dann aendert sich das Symbol 
	* auf dem Spielfeld fuer ein Alien und das Alienobjekt wird 
	* vernichtet, oder das Alien hat ueberlebt und es aendert 
	* sich nichts. Aber das Alien, welches vom Player angegriffen
	* wird, wird auf dem Bildschirm mit Koordinaten ausgegeben.
	*  
	* Parameter:
	*  i				:		Laufvariable
	*  
	* @param			dead			true  : tot
	* 									false :	lebendig	
	* 
	* @param			xy				Koordinaten des Players
	* 						
	* @return				
	*/
	private void playerAttack(int[] aXY) { 
		int rad;
		int[] xy = new int[2];
		boolean dead;

		rad = player.getShotRadius();
	
		for (xy[0] = aXY[0] - rad; xy[0] <= aXY[0] + rad; xy[0]++) {
			for (xy[1] = aXY[1] - rad; xy[1] <= aXY[1] + rad; xy[1]++) {
				if (xy[0] >= 0 &&  xy[0] <= maxXY[0] && xy[1] >= 0 && xy[1] <= maxXY[1]) {
					if (map.getCell(xy) == ALIEN) {
						dead = player.attack(maxXY, player.getXY(), xy, player.getEffect());		
						alienUpdate(dead, xy);
					}
				}
			}
		}

		return;
	}

///////////////////////////////// alienAttack ///////////////////////////////////
   /**
	* Diese Methode aktualisiert ein Alien. Entweder das Alien
	* wurde getroffen und ist tot, dann aendert sich das Symbol 
	* auf dem Spielfeld fuer ein Alien und das Alienobjekt wird 
	* vernichtet, oder das Alien hat ueberlebt und es aendert 
	* sich nichts. Aber das Alien, welches vom Player angegriffen
	* wird, wird auf dem Bildschirm mit Koordinaten ausgegeben.
	*  
	* Parameter:
	*  i				:		Laufvariable
	*  
	* @param			dead			true  : tot
	* 									false :	lebendig	
	* 
	* @param			xy				Koordinaten des Players
	* 						
	* @return				
	*/
	private boolean alienAttack() {
		int i;
		boolean alienLives;
		boolean dead;

		// Kontrolle ob Aliens noch leben, wenn ja Angriff
		alienLives = false;
		for (i = 0; i < aliensMax; i++) {
			if (alien[i].getNumLives() > 0) {
				alienLives = true;
				
				// Movement
				UTY.prtLn("move : " + alien[i].move(map));

				// Attack
				// ein Alien ist am Zug (Schleife fuer alle Aliens)
				dead = alien[i].attack(maxXY, player.getXY(), alien[i].getXY(), alien[i].getEffect());
				playerUpdate(dead, i);
				if (player.getNumLives() <= 0) {
					break;
				}
			}	
		}
		
		return (alienLives);
	}

///////////////////////////////// alienUpdate ///////////////////////////////////
   /**
	* Diese Methode aktualisiert ein Alien. Entweder das Alien
	* wurde getroffen und ist tot, dann aendert sich das Symbol 
	* auf dem Spielfeld fuer ein Alien und das Alienobjekt wird 
	* vernichtet, oder das Alien hat ueberlebt und es aendert 
	* sich nichts. Aber das Alien, welches vom Player angegriffen
	* wird, wird auf dem Bildschirm mit Koordinaten ausgegeben.
	*  
	* Parameter:
	*  i				:		Laufvariable
	*  
	* @param			dead			true  : tot
	* 									false :	lebendig	
	* 
	* @param			xy				Koordinaten des Players
	* 						
	* @return				
	*/
	private void alienUpdate(boolean dead, int[] xy) {
		int i;
		
		UTY.prt("Spieler (" + UTY.numFmt(fmt, player.getXY()[0]) + ", "
					+ UTY.numFmt(fmt, player.getXY()[1]) + ")  :  Alien " 
					+ "(" + xy[0] + ", " + xy[1] + ")");
		
		if (dead) {
			UTY.prtLn(" getroffen!");
			
			// Alien wird veraendert
			for (i = 0; i < aliensMax; i++) {
				if (alien[i].getXY()[0] == xy[0] && alien[i].getXY()[1] == xy[1]) { 
					alien[i].setNumLives(alien[i].getNumLives() - 1);
					
					if (alien[i].getNumLives() <= 0) {
						// Map wird veraendert
						map.setCell(xy, ALIEN_DEAD);
					}		
					break;
				}
			}
		} else {
			UTY.prtLn(" verfehlt!");
		}		

		return;
	}
	
///////////////////////////////// playerUpdate ///////////////////////////////////
   /**
	* Diese Methode aktualisiert den Player. Entweder der Player
	* wurde getroffen und ist tot, naturlich erst wenn er keine
	* Leben mehr hat, dann aendert sich das Symbol auf dem Spielfeld
	* fuer den Player und das Playerobjekt wird vernichtet, oder der
	* Player hat ueberlebt und es aendert sich nichts. Also das Spiel
	* geht weiter. Aber jedes ALien was den Player angegriffen hat
	* wird auf dem Bildschirm angezeigt.
	*  
	* @param			dead			true  : tot
	* 									false :	lebendig
	* 	
	* @param			iAlien			bestimmtes Alien
	* 						
	* @return				
	*/	
	private void playerUpdate(boolean dead, int iAlien) {	
		UTY.prt("Alien   (" + UTY.numFmt(fmt, alien[iAlien].getXY()[0]) + ", " 
		+ UTY.numFmt(fmt, alien[iAlien].getXY()[1]) + ")  :  Spieler ");
		if (dead) {
			UTY.prtLn("getroffen!");
			
			// Player wird veraendert
			player.setNumLives(player.getNumLives() - 1);
			if (player.getNumLives() <= 0) {
				// Map wird veraendert
				map.setCell(player.getXY(), player.getSymDead());
			}
		} else {
			UTY.prtLn("verfehlt!");
		}
		
		return;
	}
	
///////////////////////////////// gameEnd ///////////////////////////////////
   /**
	* Diese Methode entscheidet ob das Spiel beendet ist oder
	* noch fortgestzt wird. Ein Spiel ist beendet, wenn entweder
	* alle Aliens tot sind oder der Player tot ist. Das Spiel 
	* wird solange gespielt, bis einer dieser beiden Faelle 
	* eingetreten ist.
	* 
	* Parameter:
	*  gameOver		Spielendvariable	true  : Spiel vorbei
	*  									false : Spiel geht weiter
	*  
	* @param			alienLives			true  : lebendiges Alien
	* 										false : totes Alien 
	* 
	* @return			gameOver			
	*/
	private boolean gameEnd(boolean alienLives) {
		boolean gameOver;
		
		gameOver = false;
		
		if (player.getNumLives() > 0 && !alienLives) {
			UTY.prtLn("");
			UTY.prtLn("Alle Aliens sind Tod, der Spieler hat gewonnen!!! :-)");
			gameOver = true;
		} 
		
		if (player.getNumLives() <= 0 && alienLives) {
			UTY.prtLn("");
			UTY.prtLn("Der Spieler ist gestorben, die Aliens haben gewonnen!!! :-(");
			gameOver = true;
		}
		
		// if end, print field
		if (gameOver) {
			// Spielfeld ausdrucken
			map.toString(); 
		}

		return (gameOver);
	}
}