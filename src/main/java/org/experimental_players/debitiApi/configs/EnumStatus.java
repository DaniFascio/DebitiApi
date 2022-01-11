package org.experimental_players.debitiApi.configs;

public enum EnumStatus {


    OK("OK"),
    ERR_GENERICO("ERRORE GENERICO"),
    ERR_AUTHORIZATION_USERNAME("USERNAME ERRATO"),
    ERR_AUTHORIZATION_PASSWORD("PASSWORD ERRATA"),
    ERR_DATA_INTEGRITY_VIOLATION("ERRORE INTREGRITÀ DATI"),
    ERR_CREDENZIALI("CREDENZIALI ERRATE"),
    WARN_NO_SUCH_ELEMENT("ELEMENTO NON TROVATO");


    private final String key;

    EnumStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
