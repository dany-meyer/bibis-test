package de.hnu.bibis.ausleihe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Übung 3a – Warm-up für JUnit.
 * Der erste Test ist fertig. Ergänzen Sie TODO 1 bis 3 nach dem Muster
 * Arrange – Act – Assert, bis alle Tests grün sind.
 */
class AusleiheTest {

    private Ausleihe ausleihe;

    @BeforeEach
    void setUp() {
        // Arrange für alle Tests: eine frische Ausleihe, fällig am 02.11.2026
        ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
    }

    @Test
    void neueAusleiheIstNochNichtVerlaengert() {
        assertEquals(0, ausleihe.getAnzahlVerlaengerungen());
    }

    @Test
    void merktSichTitelUndFaelligkeitsdatum() {
        // TODO 1: Prüfen Sie getTitelId() und getFaelligAm() mit assertEquals
        fail("TODO 1 noch offen");
    }

    @Test
    void verlaengerungSetztNeuesDatumUndZaehltMit() {
        // TODO 2: Verlängern Sie bis zum 30.11.2026 (verlaengertBis)
        //         und prüfen Sie das neue Fälligkeitsdatum und den Zähler
        fail("TODO 2 noch offen");
    }

    @Test
    void zweiVerlaengerungenWerdenGezaehlt() {
        // TODO 3: Verlängern Sie zweimal – welches Datum und welchen Zähler erwarten Sie?
        fail("TODO 3 noch offen");
    }
}
