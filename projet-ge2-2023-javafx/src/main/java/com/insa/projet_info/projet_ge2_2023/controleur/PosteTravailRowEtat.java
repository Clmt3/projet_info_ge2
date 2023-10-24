package com.insa.projet_info.projet_ge2_2023.controleur;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

public class PosteTravailRowEtat extends PosteTravailRow {
    private Timestamp debut;
    private Timestamp fin;

    private String etat = "En attente";

    public PosteTravailRowEtat(
            String nomPrenom,
            String referenceMachine,
            int idOperation,
            String descriptionOperation,
            String referenceProduit) {
        super(nomPrenom, referenceMachine, idOperation, descriptionOperation, referenceProduit);
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
