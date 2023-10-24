package com.insa.projet_info.projet_ge2_2023.modele;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// Classe représentant une Commande
public class Commande {
    private int id;

    public Commande(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}