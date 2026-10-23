package de.hnu.bibis.ausleihe;

/**
 * Zugriff auf die Vormerkungen (in der echten Anwendung: Datenbank).
 * Für die Übung gibt es bewusst noch keine Implementierung.
 */
public interface VormerkungsRepository {

    /** @return {@code true}, wenn für den Titel mindestens eine Vormerkung wartet */
    boolean existiertVormerkung(String titelId);
}
