////////////////////////////////////////////////////////////////////////////////
//// 							Characters									////
////////////////////////////////////////////////////////////////////////////////

import static java.lang.Math.*;

/**
 * class Characters: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse enthaelt alle Methoden fuer Player und Aliens,
 * denn an diese wird die Methoden werden dann an die Klassen 
 * vererbt. Ausserdem enthaelt diese Klasse alle Eigenschaften 
 * von Aliens und den unterschiedliche Playertypen, sodass in 
 * Maps und anderern Klassen nur eine Instanz von dieser Characters
 * Klasse gemacht werden muss. Diese Vermischung von Aliens und 
 * Player kann man machen, da sie sehr aehnliche Eigenschaften
 * besitzen.
 *  
 * Klassenvariablen:
 *  sym						:		Symbol vom Spieler/Alien
 *  symDead					:		Symbol fuer toten Spieler/Alien
 *  numLives				:		Anzahl der Leben von Spieler/Alien
 *  effect					:		Effektivitaet von Spieler/Alien
 *  xy						:		Position von Spieler/Alien
 *  
 * Methods:
 *  Characters (Constructor):		Initialsierung von Klassenvariablen		
 *  attack					:		Spieler/Alien tot oder lebendig
 *  killing					:		Wahrscheinlichkeit ob Spieler/Alien getoetet wurde
 *  charDist				:		Berechnet die Distanz zwischen Spieler und Alien
 * 
 * Properties:
 *  getSym					:		bekommt Symbol von Spieler/Alien
 *  setSym					:		setzt das Symbol von Spieler/Alien fest
 *  getSymDead				:		bekommt Symbol fuer einen toten Spieler/Alien
 *  setSymDead				:		setzt Symbol fuer toten Spieler/Alien fest
 *  getNumLives				:		bekommt Anzahl von Leben (Spieler/Alien)
 *  setNumLives				:		setzt die Anzahl von Leben fest (Spieler/Alien)
 *  getEffect				:		bekommt Effektivitaet von Spieler/Alien
 *  setEffect				:		setzt Effektivitaet von Spieler/Alien fest
 *  getXY					:		bekommt Koordinaten von Spieler/Alien
 *  setXY					:		setzt die Koordinaten von Spieler/Alien fest
 * 
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public abstract class Characters implements Movable {
	private char sym;
	private char symDead;
	private int numLives;
	private double effect;
	private int shotRadius;
	private int moveMax;
	private int[] xy;

//////////////////////// Characters(Constructor) ///////////////////////////////
   /**
	* Diese Methode ist der Konstruktor der class Characters
	* In diesem Konstruktor wird folgender Parameter initialisiert
	* 
	* Parameter:
	*  xy			:		Koordinaten von Spieler/Alien			
	*/
	public Characters() {	
		xy = new int[2];
	}
	
/////////////////////////////// attack ////////////////////////////////////////
   /**
	* Diese Methode ermoeglicht eine Aussage ueber einen Angriff des
	* Players auf ein Alien, und Angriffe von allen Aliens auf den 
	* Payer zu machen, denn wenn der boolean true ist, dann wurde der
	*  Spieler/Alien getoetet, wenn nicht, dann hat der Player/Alien
	*  ueberlebt.
	* 
	* Parameter:
	*  dead			:		wenn true, dann tot
	*  
	* @param			maxXY			Spielfeld
	* @param			pXY				x und y Koordinate des Players
	* @param 			aXY				x und y Koordinate des Aliens
	* @param 			cEffect			Effektivitaet des Spielers/Aliens
	*  	
	* @return			dead
	*/	
	public boolean attack(int[] maxXY, int[] pXY, int[] aXY, double cEffect) {
		boolean dead;
		
		if (killing(maxXY, pXY, aXY, cEffect)) {
			// getoetet
			dead = true;
		} else {
			// ueberlebt
			dead = false;
		}
		
		return (dead);
	}

/////////////////////////////// killing ///////////////////////////////////////
   /** 
	* Diese Methode berechnet die Wahrscheinlichkeit dafuer, ob ein Player/Alien
	* getoetet wurde. Dazu werden einige Parameter benoetigt, ausserdem wird zur
	* Berechnung der Wahrscheinlichkeit die Funktion f(x)=x^k verwendet.
	* k = 1	:	normale Wahrscheinlichkeit zu toeten
	* k < 1	:	grosse Wahrscheinlichkeit zu toeten
	* k > 1 	:	kleine Wahrscheinlichkeit zu toeten
	* 
	* Parameter:
	*  dist			:		Distanz von Spieler und Alien
	*  rnd				:		Zufallszahl
	*  probSurvive		:		Wahrscheinlichkeit zu ueberleben
	*  probKill		:		Wahrscheinlichkeit zu toeten
	*  dead			:		wenn true, getoetet, sonst nicht
	*  
	* @param			maxXY			Spielfeld
	* @param			pXY				x und y Koordinate des Players
	* @param 			aXY				x und y Koordinate des Aliens
	* @param 			cEffect			Effektivitaet des Spielers/Aliens
	*  
	* @return			dead
	*/
	private boolean killing(int[] maxXY, int[] pXY, int[] aXY, double cEffect) {	
		double dist;
		double rnd;
		double probSurvive;
		double probKill;
		boolean dead;
	
		// Distanz und Wahrscheinlichkeit zu Ueberleben
		dist = (double) charDist(pXY, aXY);
		probSurvive = dist / (maxXY[0] + maxXY[1]);
		
		// Random Probability	[0.000000 , 1.000000]
		rnd = (double) (random() / RND_PREC);

		// General Function y = x^k
		probKill = pow(rnd, cEffect);
		
		if (probKill >= probSurvive) {
			dead = true;
		} else {
			dead = false;
		}
		
		return (dead);
	}

////////////////////////////// charDist ///////////////////////////////////////
   /** 
	* Diese Methode berechnet die Distanz zwischen Spieler und Alien.
	* 
	* Parameter:
	*  dist			:			Distanz zwischen Spieler und Alien
	*							
	* @param			pXY			x und y Koordinate Player
	* @param 		 	aXY			x und y Koordinate Alien 
	*  
	* @return			dist
	*/
	public long charDist(int[] pXY, int[] aXY) {
		long dist;
		
		dist = abs(pXY[0] - aXY[0]) + abs(pXY[1] - aXY[1]); 

		return (dist);
	}
	
////////////////////////////////////////////////////////////////////////////////
//// 							Properties									////
////////////////////////////////////////////////////////////////////////////////

//////////////////////////////// getSym ////////////////////////////////////////
   /**
	* Diese Methode bekommt das Symbol fuer einen Player/Alien
	* 
	* @return			sym				
	*/
	public char getSym() {
		
		return (sym);
	}

//////////////////////////////// setSym ////////////////////////////////////////
   /**
	* Diese Methode setzt das Symbol fuer einen Player/Alien fest
	* 
	* @param			pSym		Symbol im Feld							
	* 	
	* @return				
	*/
	public void setSym(char pSym) {
		this.sym = pSym;
		
		return;
	}

////////////////////////////// getSymDead ///////////////////////////////////////
   /**
	* Diese Methode bekommt das Symbol fuer einen toten Player/Alien
	* 
	* @return			symDead				
	*/
	public char getSymDead() {
		
		return (symDead);
	}

////////////////////////////// setSymDead ///////////////////////////////////////
   /**
	* Diese Methode setzt das Symbol fuer einen toten Player/Alien fest
	* 
	* @param			pSymDead		Symbol fuer toten Player/Alien
	* 	
	* @return				
	*/
	public void setSymDead(char pSymDead) {
		this.symDead = pSymDead;
		
		return;
	}

//////////////////////////// getNumLives ///////////////////////////////////////
   /**
	* Diese Methode bekommt die Anzahl von Leben eines Players/Aliens
	* 
	* @return			numLives				
	*/
	public int getNumLives() {
		int numLives;
		
		numLives = this.numLives;
		
		return (numLives);
	}

//////////////////////////// setNumLives ///////////////////////////////////////
   /**
	* Diese Methode setzt die Anzahl von Leben eines Players/Aliens fest
	* 
	* @param			pNumLives		Anzahl der Leben eines Players/Aliens
	* 	
	* @return				
	*/
	public void setNumLives(int pNumLives) {
		this.numLives = pNumLives;
		
		return;
	}

//////////////////////////// getEffect /////////////////////////////////////////
   /**
	* Diese Methode bekommt die Effektivitaet eines Players/Aliens
	* 
	* @return			effect				
	*/
	public double getEffect() {
		
		return (effect);
		
	}

//////////////////////////// setEffect /////////////////////////////////////////
   /**
	* Diese Methode setzt die Effektivitaet eines Players/Aliens fest
	* 
	* @param			pEffect			Anzahl der Leben eines Players/Aliens
	* 	
	* @return				
	*/
	public void setEffect(double pEffect) {
		this.effect = pEffect;
		
		return;
	}

//////////////////////////// getShotRadius /////////////////////////////////////////
   /**
	* Diese Methode bekommt die Effektivitaet eines Players/Aliens
	* 
	* @return			shotRadius				
	*/
	public int getShotRadius() {
	
		return (shotRadius);
	
	}

//////////////////////////// setShotRadius /////////////////////////////////////////
   /**
	* Diese Methode setzt die Effektivitaet eines Players/Aliens fest
	* 
	* @param			pShotRadius			Anzahl der Leben eines Players/Aliens
	* 	
	* @return				
	*/
	public void setShotRadius(int pShotRadius) {
		this.shotRadius = pShotRadius;
	
		return;
	}

	
////////////////////////////getMoveMax /////////////////////////////////////////
   /**
	* Diese Methode bekommt die Effektivitaet eines Players/Aliens
	* 
	* @return			moveMax				
	*/
	public int getMoveMax() {
	
		return (moveMax);
	}

////////////////////////////setMoveMax /////////////////////////////////////////
   /**
	* Diese Methode setzt die Effektivitaet eines Players/Aliens fest
	* 
	* @param			pMoveMax			Anzahl der Leben eines Players/Aliens
	* 	
	* @return				
	*/
	public void setMoveMax(int pMoveMax) {
		this.moveMax = pMoveMax;
		
		return;
	}
	
////////////////////////////// getXY ///////////////////////////////////////////
   /**
	* Diese Methode bekommt die Koordinaten eines Players/Aliens
	* 
	* @return			xy				
	*/
	public int[] getXY() {
		int[] xy = new int[2];
		
		xy[0] = this.xy[0];
		xy[1] = this.xy[1];
		
		return (xy);
	}
	
///////////////////////////// setXY ////////////////////////////////////////////
   /**
	* Diese Methode setzt die Koordinaten eines Players/Aliens fest
	* 
	* @param			pXY				Koordinaten eines ALiens
	* 
	* @return							
	*/
	public void setXY(int[] pXY) {
		this.xy[0] = pXY[0];
		this.xy[1] = pXY[1];
		
		return;
	}
}
