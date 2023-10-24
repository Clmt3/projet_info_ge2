package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant la relation entre une Machine et un Type d'Opération
public class Realise {
    private int id_machine;
    private int id_type;
    private double duree;

    public Realise(int id_machine, int id_type, double duree) {
        this.id_machine = id_machine;
        this.id_type = id_type;
        this.duree = duree;
    }

   

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
}

