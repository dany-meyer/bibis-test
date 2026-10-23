package de.hnu.bibis.ausleihe;

import java.time.LocalDate;

/**
 * Eine Ausleihe eines Exemplars durch eine nutzende Person.
 */
public class Ausleihe {

    private final String titelId;
    private LocalDate faelligAm;
    private int anzahlVerlaengerungen;

    public Ausleihe(String titelId, LocalDate faelligAm) {
        this.titelId = titelId;
        this.faelligAm = faelligAm;
        this.anzahlVerlaengerungen = 0;
    }

    public String getTitelId() {
        return titelId;
    }

    public LocalDate getFaelligAm() {
        return faelligAm;
    }

    public int getAnzahlVerlaengerungen() {
        return anzahlVerlaengerungen;
    }

    /** Setzt das neue Fälligkeitsdatum und zählt die Verlängerung mit. */
    void verlaengertBis(LocalDate neuesDatum) {
        this.faelligAm = neuesDatum;
        this.anzahlVerlaengerungen++;
    }
}
