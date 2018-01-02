////////////////////////////////////////////////////////////////////////////////
////								UTILS			    					////
////////////////////////////////////////////////////////////////////////////////

import java.text.DecimalFormat;

/**
 * class Utils: 
 * Nebenklasse fuer Projekt AlienGame.
 * Basierend auf X-Com.		
 * 
 * Diese Klasse enthaelt Methods um das Schreiben in anderen
 * Klassen zu vereinfachen.
 * 
 * Klassenvariablen:
 *  DecimalFormat		:		Objekt von import
 *  
 * Utilities:
 *  prtLn				:		Abkuerzung fuer System.out.println
 *  prt					:		Abkuerzung fuer System.out.print
 *  format				:					
 *  strDigit			:
 * 
 * @version				:		00.06.00
 * 
 * @author				:		Luca Heinrich 4911584 Gruppe 6a
 */
public final class Utils {
	private static DecimalFormat myFmt;

/////////////////////////////// prtLn //////////////////////////////////////////
   /**
	* Diese Methode drueckt einen String (param) aus und schreibt ihn 
	* auf den Bildschirm und geht danach in eine neuen Zeile.
	* Nicht notwendig, aus Bequemlichkeit
	* 
	* @param 			pText			auszudruckener String
	* 
	* @return	
	*/
	public void prtLn(String pText) {			
		System.out.println(pText);
		
		return;
	}

/////////////////////////////// prt ////////////////////////////////////////////
   /**
	* Diese Methode drueckt einen String (param) aus und schreibt ihn 
	* auf den Bildschirm und bleibt danach stehen.
	* Nicht notwendig, aus Bequemlichkeit
	* 
	* @param 			pText			auszudruckener String
	* 
	* @return
	*/
	public void prt(String pText) {
		System.out.print(pText);
		
		return;
	}
	
/////////////////////////////// format //////////////////////////////////////////
   /**
	* Diese Methode gibt einen String aus um das richtige Format zur 
	* Ausgabe von Koordinaten (in diesem Fall) zu bestimmen.
	* Hierzu benoetigt man einen Import des Befehls "DecimalFormat".
	* Und damit wird dann das richtige Format fuer eine Zahl erstellt.
	* 
	* @param			pFmt			Wahl des Formats
	* @param			pNum			Eingabe einer Zahl
	* 
	* @return			myFmt.format(pNum)
	*/	
	public String numFmt(String pFmt, double pNum) {
		myFmt = new DecimalFormat(pFmt);
		
		return (myFmt.format(pNum));
	}

/////////////////////////////// strDigit ////////////////////////////////////////
   /**
	* Diese Methode ist fuer die Ausgabe der Koordinaten von Spieler
	* und Alien mit verantwortlich, denn sie gibt der Ausgabe Struktur.
	* Damit alles bei der Ausgabe symmetrisch aussieht muessen die 
	* Koordinaten so geschrieben werden, das alle Zahlen gleich viele 
	* Zahlenstellen besitzen. Dies wird wird in Abhaengigkeit der 
	* maximalen Zahl bestimmt.
	* 
	* Parameter:
	*  i				:		Laufvariable
	*  strOut			:		String zur Ausgabe benoetigter Zahlenstellen
	* 
	* @param			pChar			0, kann aber auch geandert werden
	* @param 			nChar			maxmale Zahl (Zahlenstelle(Digit))
	* 
	* @return			strOut
	*/	
	public String strDigit(String pChar, int nChar) {
		int i;
		String strOut;
		
		strOut = "";
		for (i = 1; i <= nChar; i++) {
			strOut = strOut + pChar;
		}		
		
		return (strOut);
	}
}
