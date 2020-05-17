package uebung_4;

import java.util.Scanner;
import java.time.Year;

public class haus{

  public static void main (String[] args){
    haus haus1 = new haus(5,1,1950,false,true);
    //haus1.WohnungenBewohnt(wohnungen, Anzahlwohnungen);
    //haus1.WreckingBall(Baujahr, wohnungen, Anzahlwohnungen, gebaut);
  }

  // Attribute
  static int Anzahlwohnungen;//static nur solange bis diese Variablen nicht mehr direkt von der mainfunction aufgerufen werden
  int Hausnummer;//Grundstücknummer
  static int Baujahr;
  boolean Hausbewohnt;// true ist bewohnt, false ist leer
  static boolean gebaut;//true ist gebaut
  static boolean[] wohnungen;//sh. Anzahlwohnungen
  //evtl Strasse

  //Konstruktor Klasse Haus
  public haus(int AW ,int HN, int BJ, boolean HL, boolean geb) {
    this.Anzahlwohnungen = AW;//So wird der Variable Hausnummer im Konstruktor ein wert zugeordnet
	  this.Hausnummer = HN;
	  this.Baujahr = BJ;
  	this.Hausbewohnt = HL;
  	this.gebaut = geb;
  	this.wohnungen = defwohnungen(Anzahlwohnungen);
  }

    //Funktion zur eingabe von welche Wohnungen besetzt sind
    public static boolean[] defwohnungen(int Anzahlwohnungen){
      boolean[] wng = new boolean[Anzahlwohnungen];
      System.out.println("Geben sie true oder false ein um die Wohnunge zu besetzen");
      for (int i = 0; i < Anzahlwohnungen; i++){
          System.out.println("Wohnung " + i);
          Scanner scanner = new Scanner(System.in);
          String input = scanner.nextLine();
          if (input.contains("t") == true){
            wng[i] = true;
          }else if(input.contains("f") == true){
            wng[i] = false;
          }
      }
      return wng;
    }

    public static boolean WohnungenBewohnt(boolean[] wohnungen, int Anzahlwohnungen){
      int bewohnt = 0;
      boolean leer = false;
      for (int i = 0; i <= wohnungen.length -1; i++){
        if (wohnungen[i] == true){
          bewohnt++;
        }
      }
      System.out.println("Es sind " + bewohnt + " von " + Anzahlwohnungen + " bewohnt");
      if (bewohnt == 0){
        leer = true;
      }
      return leer;
    }

    public static boolean WreckingBall(int Baujahr, boolean[] wohnungen, int Anzahlwohnungen, boolean gebaut){
  	  int jahr = Year.now().getValue();
  	  if ((WohnungenBewohnt(wohnungen, Anzahlwohnungen) == true) && ((jahr - Baujahr) >= 60) && gebaut == true){
  	    gebaut = false;
  	  }
  	  return gebaut;
    }
  }