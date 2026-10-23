package de.hnu.bibis.ausleihe;

/** Wird geworfen, wenn eine Ausleihe nicht verlängert werden darf. */
public class VerlaengerungNichtMoeglichException extends Exception {

    public VerlaengerungNichtMoeglichException(String grund) {
        super(grund);
    }
}
