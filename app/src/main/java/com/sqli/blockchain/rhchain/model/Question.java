package com.sqli.blockchain.rhchain.model;

/**
 * Created by gunicolas on 26/04/17.
 */

public class Question {

    public int id;
    public String hash;
    public String libelle;

    public Question(int id, String hash, String libelle) {
        this.id = id;
        this.hash = hash;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", label='" + libelle + '\'' +
                '}';
    }
}
