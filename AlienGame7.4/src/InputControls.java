////////////////////////////////////////////////////////////////////////////////
////							INPUTKONTROLLEN			    				////
////////////////////////////////////////////////////////////////////////////////

/**
 * class InputControls: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.
 * 
 * Diese Klasse kontrolliert Eingabeparameter auf richtige Eingabe
 * und betaetigt Inputs.
 * Somit hat man alle Inputs und alle Kontrollen auf einen Blick
 * und kann falls noetig schnell, unkompliziert und ohne den 
 * ganzen Code veraendern zu muessen Kontrollen veraendern, so wie 
 * es noetig ist.
 * 
 * Instantiation:
 *  UTY						:		Utils
 *  INP						:		Scanner
 *  
 * Methods:
 *  showArgs				:		Anzeigen von Eingabebefehl
 *  inputArgs				:		Fragt nach Eingabe von args
 *  ctrlArgs				:		Kontrolliert Werte von args
 *  confArgs				:		Bestaetigung vom Anwender
 *  inputCoord				:		Kontrolliert die Eingabe der Koordinaten
 *  inputMove				:
 *  inputInteger			:		Nutzereingabe fuer Angriffskoordinaten
 *  isNumber				:		ueberpruefen ob Eingabe eine Nummer ist
 *	
 * @version					:		00.06.00
 * 
 * @author					:		Luca Heinrich 4911584 Gruppe 6a
 */
public class InputControls implements AlienGameConstants {	
				
////////////////////////////////////////////////////////////////////////////////
//// 					TRANSFER PARAMETER CONTROL							////
////////////////////////////////////////////////////////////////////////////////

///////////////////////////// showArgs /////////////////////////////////
   /**
	* Diese Methode zeigt den Eingabebefehl auf den Bilschirm nochmal an.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  exeCommands		:		String zum Anzeigen der Eingabe
	*  
	* @param			pArgs			vom Nutzer eingegeben	
	* 
	* @return		
	*/
	public void showArgs(String[] pArgs) {
		int i;													// beliebige Laufvariable
		String exeCommands;										// Anzeigen von Eingabebefehl

		UTY.prtLn("");
		exeCommands = "java " + PROGRAM;						// Stringinitialisierung mit Name des Programms
		for (i = 1; i <= 3; i++) {								// Schleife um in den String den Programmname mit den args zu verknuepfen
			if (pArgs.length >= i) {							
				exeCommands = exeCommands + " " + pArgs[i - 1]; 
			}
		}
		UTY.prtLn(exeCommands);
				
		return; 
	}
	
///////////////////////////// argsInput ////////////////////////////////////
   /**
	* Diese Methode fragt nach einer Eingabe, wenn nicht schon direkt bei
	* der execution die drei args eingegeben wurden. In diesem Fall ist
	* argsOK bei der Eingabe false, da die args nicht "OK" sind. Diese
	* Methode wird dann solange ausgefuehrt bis die drei args korrekt 
	* eingegeben wurden. Danach ist argsOK true.
	* Ausserdem werden die args noch an ein Koordinatensystem angepasst,
	* das bedeutet wenn man z.B. 5 eingibt erhaelt man Zahlen von 0 - 4.
	* Dies ist sehr wichtig, denn unser Spielfeld hat die erste Zelle
	* bei x = 0 und y = 0. Also muessen alle Eingaben dementsprechend 
	* angepasst werden.
	* 
	*  @param			argsOK			
	*  
	*  @return			argsOK		
	*/
	public boolean[] argsInput(boolean[] argsOK) {
		int value;
			
		// x
		if (!argsOK[0]) {
			value = inputInteger("Spielfeldbreite (x)  :    ");
			argsOK[0] = ctrlArgs(0, String.valueOf(value));
		}
		
		// y
		if (argsOK[0] && !argsOK[1]) {
			value = inputInteger("Spielfeldhoehe  (y)  :    ");			
			argsOK[1] = ctrlArgs(1, String.valueOf(value));
		}
		
		// numAliens
		if (argsOK[0] && argsOK[1] && !argsOK[2]) {
			value = inputInteger("Anzahl der Aliens    :    ");
			argsOK[2] = ctrlArgs(2, String.valueOf(value));
		}
		
		return (argsOK);
	}	

///////////////////////////// argsControl ////////////////////////////////////
   /**
	* Diese Methode kontrolliert ob alle args Regelkonform eingegeben wurden
	* Die Regeln sind:
	*  1. Wenn AlienGame.getxMax() <= 0 ist, ist es nicht gueltig
	*  2. Wenn AlienGame.getyMax() <= 0 ist, ist es nicht gueltig
	*  3. AlienGame.getaliensNum() darf maximal AlienGame.getxMax() * 
	*     AlienGame.getyMax() - 1 sein (es gibt auch einen Spieler)
	*  => Wenn eine dieser Regeln falsch ist, dann wird eine Nachricht angezeigt
	*     und false wird zurueck gegeben.
	* 
	* @param			iArg			xxx
	* 					sArg			xxx
	* 			
	* 
	* @return			pArgsOK	 		true  : args korrekt eingegeben
	* 									false : Error, args nochmal eingegeben
	*/
	public boolean ctrlArgs(int iArg, String sArg) {
		boolean pArgsOK;									// kontrollieren von args
		int value;

		value = 0;
		pArgsOK = true;
		try {
			value = Integer.valueOf(sArg);
		} catch (NumberFormatException e) {
			pArgsOK = false;
		}

		if (pArgsOK && (value < 1 || value > 32000)) {
			pArgsOK = false;
		}

		switch (iArg) {
			case 0 :
				if (pArgsOK) {
					AlienGame.setXmax(value - 1);
				} else {
					AlienGame.setXmax(-1);
					UTY.prtLn("Breite nicht gueltig.");
				}
				break;

			case 1 :
				if (pArgsOK) {
					AlienGame.setYmax(value - 1);
				} else {
					AlienGame.setYmax(-1);
					UTY.prtLn("Hoehe nicht gueltig.");
				}
				break;

			default :
				if (pArgsOK && AlienGame.getXmax() >= 0 && AlienGame.getYmax() >= 0) {
					if (value >= (AlienGame.getXmax() + 1) * (AlienGame.getYmax() + 1)) {
						pArgsOK = false;
					}
				}

				if (pArgsOK) {
					AlienGame.setAliensNum(value);
				} else {
					UTY.prtLn("Anzahl der Aliens nicht gueltig.");
				}
		}

		return (pArgsOK);		
	}
	
///////////////////////////// confArgs /////////////////////////////////////
   /**
	* Diese Methode erbittet eine Bestaetigung und zeigt args nochmals
	* auf dem Bildschirm an. Wenn man sich also bei der Eingabe der args 
	* vertan hat, so kann man das Spiel beenden und nochmals ausfuehren, 
	* wenn man aber sich mit der Eingabe sicher ist, so kann das Spiel
	* losgehen.
	* 
	* Parameter:
	*  correct			:		Bestaetigungsstring (j,q, oder Enter)
	*  pResult			:		true wenn bestaetigt, false wenn falsch oder q
	*  
	* @return			pResult			true  : bestaetigt
	* 									false : nicht bestaetigt, nochmal ausfuehren
	*/
	public boolean confArgs() {
		String correct;														// Eingabe fuer Bestaetigung
		boolean pResult;													// Methode Ergebnis
		
		pResult = true;									
		if (CONFIRMATION) {
			UTY.prtLn("");
			UTY.prtLn("Bitte bestaetigen...");
			UTY.prtLn("");
			UTY.prtLn("Breite	: " + (AlienGame.getXmax() + 1));
			UTY.prtLn("Hoehe	: " + (AlienGame.getYmax() + 1));
			UTY.prtLn("Aliens	: " + AlienGame.getAliensNum());
			UTY.prtLn("");
			
			do {															// Do-while Schleife 
				UTY.prt("<j>/q	: ");
				correct = INP.nextLine();
				if (correct.isEmpty()) {
					correct = "j";
				} else {
					correct = correct.toLowerCase();
					correct = correct.substring(0, 1);	
				}
			} while (!correct.equals("j") && !correct.equals("q"));								// Wenn nicht richtig nochmal
			
			if (correct.equals("q")) {										// Keine Bestaetigung wenn q und beenden
				pResult = false;
				UTY.prtLn("Bitte nochmal ausfuehren mit neuen Werten...");		
			}
		}		
		return (pResult);
	}

////////////////////////////// inputCoord //////////////////////////////////
   /**
	* Diese Methode fragt nach den Koordinaten des Aliens, welches 
	* angegriffen werden soll und kontrolliert sie.
	* Wenn die Koordinaten korrekt eingegeben wurden, dann ist 
	* isCorrect true und das SPiel wird fortgesetzt. Eine Eingabe 
	* einer Koordinate ist nicht korrekt, wenn sich in dem Feld, 
	* welches eingegeben wurde, kein Alien befindet oder wenn die 
	* Eingabe ausserhalb des Spielfeldes befindet, oder einfach 
	* gar keine Zahl ist. Trifft einer dieser Faelle zu, so wird
	* nochmal nach einer Eingabe gefragt, solange bis ein Alien 
	* ausgewahlt wurde. 
	* 
	* Parameter:
	*  xy				:		Angriffskoordinaten
	*  isCorrect		:		true  : Eingabe korrekt
	*  						false : Eingabe falsch
	*  
	* @param			pMap			Spielfeld
	* 
	* @return			xy
	*/
	public int[] inputCoord(Maps pMap) {
		int[] xy = new int[2];
		boolean isCorrect;
			
		do {
			isCorrect = true;

			xy[0] = inputInteger("X-Koordinate	:  ");
			
			// Check max fuer X
			if (xy[0] > AlienGame.getXmax()) {
				UTY.prtLn("X-Koordinate nicht im Spielfeld!");
				isCorrect = false;
			}
			
			// Check max fuer Y
			if (isCorrect)  {
				xy[1] = inputInteger("Y-Koordinate	:  ");
				if (xy[1] > AlienGame.getYmax()) {
					UTY.prtLn("Y-Koordinate nicht im Spielfeld!");
					isCorrect = false;
				}
			}
			UTY.prtLn("");
			
			// Check in Map
			if (isCorrect)  {
				if (pMap.getCell(xy) != ALIEN) {
					UTY.prtLn("Kein Alien. Versuch es nochmal!");
					isCorrect = false;
				}
			}
		} while (!isCorrect);		
		
		return (xy);
	}

//////////////////////////////inputMove //////////////////////////////////
   /**
	* Diese Methode fragt nach den Koordinaten des Aliens, welches 
	* angegriffen werden soll und kontrolliert sie.
	* Wenn die Koordinaten korrekt eingegeben wurden, dann ist 
	* isCorrect true und das SPiel wird fortgesetzt. Eine Eingabe 
	* einer Koordinate ist nicht korrekt, wenn sich in dem Feld, 
	* welches eingegeben wurde, kein Alien befindet oder wenn die 
	* Eingabe ausserhalb des Spielfeldes befindet, oder einfach 
	* gar keine Zahl ist. Trifft einer dieser Faelle zu, so wird
	* nochmal nach einer Eingabe gefragt, solange bis ein Alien 
	* ausgewahlt wurde. 
	* 
	* Parameter:
	*  xy				:		Angriffskoordinaten
	*  isCorrect		:		true  : Eingabe korrekt
	*  							false : Eingabe falsch
	*  
	* @param			stepNum			xxx
	* 
	* @return			movement
	*/
	public String inputMove(int stepNum) {
		String movement; 
		
		do {															// Do-while Schleife 
			UTY.prt("Schritt " + stepNum + "	: ");

			movement = INP.nextLine();
			if (movement.isEmpty()) {
				movement = "";
			} else {
				movement = movement.toLowerCase();
			}
		} while (!movement.equals("w") && !movement.equals("a") 
				&& !movement.equals("s") && !movement.equals("d")
				&& !movement.equals(""));
		
		return (movement);
	}
	
////////////////////////////// inputInteger //////////////////////////////////
   /**
	* Diese Methode liesst den Input von der Tastutur ein, welcher
	* fuer den Angriif von dem Player auf einen Alien benoetigt
	* wird (X und Y-Koordinate fuer den Angriff des Players).
	* 
	* Parameter:
	*  strInput		:		Eingabestring
	*  intInput		:		Kontrollinteger
	*  isValid			:		true  : korrekte Nummer
	*  						false : keine gueltige Nummer
	*  		
	* @param			pDesc			Was auf dem Bildschirm gezeigt wird		
	* 
	* @return			intInput	
	*/
	private int inputInteger(String pDesc) {
		String strInput;
		int intInput;
		boolean isValid;
		
		intInput = 0;
		do {
			UTY.prt(pDesc);
			strInput = INP.nextLine();
			
			isValid = isNumber(strInput);
			
			try {
				intInput = Integer.valueOf(strInput);
			} catch (NumberFormatException e) {
				isValid = false;
			}
			
			if (!isValid) {
				UTY.prtLn("Keine gueltige Nummer!");
			}
		} while (!isValid);
		
		return (intInput);
	}

	
////////////////////////////// isNumber ///////////////////////////////////////
   /** 
	* Diese Methode macht einen formale Check, ob in dem Eingabestring
	* eine Nummer steht. Wenn nicht dann nochmal eingeben.
	* Wenn jedoch true, dann ist dei EIngabe eine Zahl und wird dann
	* in den anderen Methoden noch auf andere Bedingungen kontrolliert.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  chrInput		:		Eingabestring
	*  lenInput		:		Anzahl von Digits
	*  isValid			:		true  : Nummer
	*  						false : keine Nummer
	*  
	* @param			pString				Kontrollstring		
	* 
	* @return			isValid
	*/
	private boolean isNumber(String pString) {
		int i;
		String chrInput;
		int lenInput;
		boolean isValid;

		lenInput = pString.length();
		if (lenInput > 0) {
			isValid = true;
			for (i = 0; i < lenInput; i++) {
				chrInput = pString.substring(i, i + 1);
				if (chrInput.compareTo("0") < 0 || chrInput.compareTo("9") > 0) {
					isValid = false;
					break;
				}
			}
		} else {
			isValid = false;
		}
		
		return (isValid);
	}
}
