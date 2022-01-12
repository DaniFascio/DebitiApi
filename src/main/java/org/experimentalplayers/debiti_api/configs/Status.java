package org.experimentalplayers.debiti_api.configs;

public enum Status {

//  OK MESSAGES
    OK("OK"),

//  ERROR MESSAGES
    ERR_GENERICO("ERRORE GENERICO"),
    ERR_UPDATE("ERRORE UPDATE"),
    ERR_INSERT("ERRORE INSERIMENTO"),
    ERR_AUTHORIZATION_USERNAME("USERNAME ERRATO"),
    ERR_AUTHORIZATION_PASSWORD("PASSWORD ERRATA"),
    ERR_DATA_INTEGRITY_VIOLATION("ERRORE INTREGRITÀ DATI"),
    ERR_CREDENZIALI("CREDENZIALI ERRATE"),


//  WARNING MESSAGES
    WARN_NO_SUCH_ELEMENT("ELEMENTO NON TROVATO");


    private final String key;

    Status(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
