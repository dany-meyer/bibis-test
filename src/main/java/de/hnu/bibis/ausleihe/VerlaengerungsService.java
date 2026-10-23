package de.hnu.bibis.ausleihe;

import java.time.LocalDate;

/**
 * Anwendungsfall „Ausleihe verlängern“.
 * Hängt vom {@link VormerkungsRepository} ab – im Unit-Test wird es gemockt.
 */
public class VerlaengerungsService {

    private final VormerkungsRepository vormerkungen;
    private final Verlaengerungsregel regel;

    public VerlaengerungsService(VormerkungsRepository vormerkungen, Verlaengerungsregel regel) {
        this.vormerkungen = vormerkungen;
        this.regel = regel;
    }

    /**
     * Verlängert die Ausleihe, falls die Regeln das erlauben.
     *
     * @return das neue Fälligkeitsdatum
     * @throws VerlaengerungNichtMoeglichException wenn das Maximum erreicht ist oder eine Vormerkung besteht
     */
    public LocalDate verlaengere(Ausleihe ausleihe) throws VerlaengerungNichtMoeglichException {
        if (ausleihe.getAnzahlVerlaengerungen() >= Verlaengerungsregel.MAX_VERLAENGERUNGEN) {
            // Datenbank muss gar nicht erst gefragt werden
            throw new VerlaengerungNichtMoeglichException("Maximale Anzahl an Verlängerungen erreicht");
        }
        boolean vorgemerkt = vormerkungen.existiertVormerkung(ausleihe.getTitelId());
        if (!regel.darfVerlaengern(ausleihe.getAnzahlVerlaengerungen(), vorgemerkt)) {
            throw new VerlaengerungNichtMoeglichException("Titel ist vorgemerkt");
        }
        LocalDate neu = regel.neuesFaelligkeitsdatum(ausleihe.getFaelligAm());
        ausleihe.verlaengertBis(neu);
        return neu;
    }
}
