package de.hnu.bibis.ausleihe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Übung 4 – VerlaengerungsService mit Mock testen.
 * Das VormerkungsRepository (später die Datenbank) gibt es noch nicht –
 * Mockito ersetzt es durch einen Mock.
 *
 * Zwei Tests sind fertig: Lesen Sie sie und führen Sie sie aus.
 * Ergänzen Sie dann den TODO im ersten Test.
 */
@ExtendWith(MockitoExtension.class)
class VerlaengerungsServiceTest {

    @Mock
    VormerkungsRepository vormerkungen;   // Attrappe für die Datenbank

    VerlaengerungsService service;        // Klasse unter Test

    @BeforeEach
    void setUp() {
        // vor jedem Test neu: Service mit der Attrappe und der echten Verlängerungsregel
        service = new VerlaengerungsService(vormerkungen, new Verlaengerungsregel());
    }

    // ---------------------------------------------------------------
    // Beispiel 1 (siehe Vorlesung):
    // Titel ist vorgemerkt → Verlängerung wird abgelehnt
    // ---------------------------------------------------------------
    @Test
    void lehntAbWennVorgemerkt() {
        // Arrange
        Ausleihe ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
        when(vormerkungen.existiertVormerkung("T-4711")).thenReturn(true);    // Stubbing
        // Act + Assert
        assertThrows(VerlaengerungNichtMoeglichException.class,
                () -> service.verlaengere(ausleihe));
        // Assert: Ausleihe bleibt unverändert
        assertEquals(LocalDate.of(2026, 11, 2), ausleihe.getFaelligAm());
        assertEquals(0, ausleihe.getAnzahlVerlaengerungen());
    }

    // ---------------------------------------------------------------
    // Beispiel 2 (fertig): Maximum erreicht
    // ---------------------------------------------------------------
    @Test
    void fragtDatenbankNichtWennMaximumErreicht() throws Exception {
        // Arrange: Ausleihe ohne Vormerkung, dreimal erlaubt verlängern
        Ausleihe ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
        when(vormerkungen.existiertVormerkung("T-4711")).thenReturn(false);
        service.verlaengere(ausleihe);
        service.verlaengere(ausleihe);
        service.verlaengere(ausleihe);

        // Act + Assert: Die vierte Verlängerung muss scheitern
        assertThrows(VerlaengerungNichtMoeglichException.class,
                () -> service.verlaengere(ausleihe));

        // Assert: Das Repository wurde nur bei den drei erlaubten Verlängerungen gefragt,
        //         beim vierten Versuch nicht mehr
        verify(vormerkungen, times(3)).existiertVormerkung("T-4711");
    }

    // ---------------------------------------------------------------
    // Ihre Aufgabe: Keine Vormerkung → Verlängerung klappt
    // ---------------------------------------------------------------
    @Test
    void verlaengertWennKeineVormerkung() throws Exception {
        // Arrange: Ausleihe anlegen und festlegen, was der Mock antwortet
        Ausleihe ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
        when(vormerkungen.existiertVormerkung("T-4711")).thenReturn(false);   // Stubbing

        // Act
        LocalDate neu = service.verlaengere(ausleihe);

        // Assert
        // TODO: 1. Das neue Datum ist der 30.11.2026 (assertEquals mit neu).
        //       2. Die Ausleihe hat jetzt 1 Verlängerung (getAnzahlVerlaengerungen).
        //       3. Der Service hat das Repository gefragt:
        //          verify(vormerkungen).existiertVormerkung("T-4711");
        fail("TODO noch offen");
    }

}
