package de.hnu.bibis.ausleihe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Übung 5 – VerlaengerungsService mit Mock testen.
 * Das VormerkungsRepository (später die Datenbank) gibt es noch nicht –
 * Mockito ersetzt es durch einen Mock. Ergänzen Sie TODO 1 bis 3.
 */
@ExtendWith(MockitoExtension.class)
class VerlaengerungsServiceTest {

    @Mock
    VormerkungsRepository vormerkungen;   // Stellvertreter für die Datenbank

    /** Erzeugt den Service mit dem Mock und der echten Verlängerungsregel. */
    private VerlaengerungsService service() {
        return new VerlaengerungsService(vormerkungen, new Verlaengerungsregel());
    }

    @Test
    void verlaengertWennKeineVormerkung() throws Exception {
        // Arrange
        Ausleihe ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
        when(vormerkungen.existiertVormerkung("T-4711")).thenReturn(false);   // Stubbing
        // Act
        LocalDate neu = service().verlaengere(ausleihe);
        // Assert
        // TODO 1: Prüfen Sie das neue Datum, den Zähler der Ausleihe
        //         und mit verify(…), dass das Repository gefragt wurde
        fail("TODO 1 noch offen");
    }

    @Test
    void lehntAbWennVorgemerkt() {
        // Arrange
        Ausleihe ausleihe = new Ausleihe("T-4711", LocalDate.of(2026, 11, 2));
        // TODO 2: Der Mock soll eine Vormerkung melden (when … thenReturn).
        //         Dann: assertThrows(VerlaengerungNichtMoeglichException.class, …)
        //         und prüfen, dass Datum und Zähler unverändert sind
        fail("TODO 2 noch offen");
    }

    @Test
    void fragtDatenbankNichtWennMaximumErreicht() throws Exception {
        // TODO 3: Dreimal verlängern, die vierte Verlängerung muss scheitern –
        //         und das Repository darf nur dreimal gefragt worden sein (verify, times)
        fail("TODO 3 noch offen");
    }
}
