package de.hnu.bibis.statistik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Übung „Statische Analyse“: Diese Klasse kompiliert, enthält aber mehrere
 * typische Fehler und Schwachstellen. Finden Sie sie mit
 * Code → Inspect Code… (IntelliJ) – noch bevor Sie den Code ausführen.
 */
public class AusleiheStatistik {

    private List<String> titel = new ArrayList<>();
    private int gesamtTage;

    /** Liest eine CSV-Datei mit Zeilen der Form „Titel;Ausleihtage“. */
    public void ladeAusDatei(String pfad) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfad));
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                titel.add(teile[0]);
                gesamtTage += Integer.parseInt(teile[1]);
            }
        } catch (IOException e) {
        }
    }

    /** Durchschnittliche Ausleihdauer in Tagen. */
    public double durchschnittlicheDauer() {
        return gesamtTage / titel.size();
    }

    /** Wie oft wurde ein bestimmter Titel ausgeliehen? */
    public int anzahlAusleihen(String gesuchterTitel) {
        int anzahl = 0;
        int index = 0;
        for (String t : titel) {
            if (t == gesuchterTitel) {
                anzahl++;
            }
        }
        return anzahl;
    }

    /** Gibt den Titel zurück, der am häufigsten ausgeliehen wurde. */
    public String beliebtesterTitel() {
        String bester = null;
        int max = 0;
        for (String t : titel) {
            int n = anzahlAusleihen(t);
            if (n > max) {
                max = n;
                bester = t;
            }
        }
        return bester.toUpperCase();
    }

    public boolean istLeer() {
        if (titel.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


}
