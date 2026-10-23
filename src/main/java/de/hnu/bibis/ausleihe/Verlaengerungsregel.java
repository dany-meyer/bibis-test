package de.hnu.bibis.ausleihe;

import java.time.LocalDate;

/**
 * Fachliche Regeln des BibIS für das Verlängern einer Ausleihe.
 *
 * <ul>
 *   <li>Eine Ausleihe darf höchstens {@value #MAX_VERLAENGERUNGEN}-mal verlängert werden.</li>
 *   <li>Ist der Titel vorgemerkt, ist keine Verlängerung möglich.</li>
 *   <li>Jede Verlängerung verschiebt das Fälligkeitsdatum um {@value #LEIHFRIST_TAGE} Tage,
 *       gerechnet ab dem bisherigen Fälligkeitsdatum.</li>
 * </ul>
 */
public class Verlaengerungsregel {

    public static final int MAX_VERLAENGERUNGEN = 3;
    public static final int LEIHFRIST_TAGE = 28;

    /**
     * Prüft, ob eine Ausleihe verlängert werden darf.
     *
     * @param bisherigeVerlaengerungen wie oft die Ausleihe schon verlängert wurde (ab 0)
     * @param vorgemerkt               ob für den Titel eine Vormerkung besteht
     * @return {@code true}, wenn weniger als 3 Verlängerungen vorliegen und keine Vormerkung besteht
     * @throws IllegalArgumentException wenn {@code bisherigeVerlaengerungen} negativ ist
     */
    public boolean darfVerlaengern(int bisherigeVerlaengerungen, boolean vorgemerkt) {
        if (bisherigeVerlaengerungen < 0) {
            throw new IllegalArgumentException("Anzahl der Verlängerungen darf nicht negativ sein");
        }
        return bisherigeVerlaengerungen < MAX_VERLAENGERUNGEN && !vorgemerkt;
    }

    /**
     * Berechnet das neue Fälligkeitsdatum nach einer Verlängerung.
     *
     * @param faelligAm bisheriges Fälligkeitsdatum
     * @return bisheriges Fälligkeitsdatum plus 28 Tage
     */
    public LocalDate neuesFaelligkeitsdatum(LocalDate faelligAm) {
        return faelligAm.plusDays(LEIHFRIST_TAGE);
    }
}
