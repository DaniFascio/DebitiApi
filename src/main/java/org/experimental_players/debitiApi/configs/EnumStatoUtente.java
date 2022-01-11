package org.experimental_players.debitiApi.configs;

public enum EnumStatoUtente {


    /**
     1	attivo
     2	bannato
     3	cancellato
     */


    ATTIVO(1,"attivo"),
    BLOCCATO(2,"bannato"),
    CANCELLATO(3,"cancellato");


    private final Integer id;
    private final String key;

    EnumStatoUtente(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public Integer getId() {
        return this.id;
    }


}
