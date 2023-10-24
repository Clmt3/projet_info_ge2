package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant un État Machine Possible
public class EtatMachinePossible {
    private int id_etat_machine;
    private String reference;

    public EtatMachinePossible(int id_etat_machine, String reference) {
        this.id_etat_machine = id_etat_machine;
        this.reference = reference;
    }

   

    public int getId_etat_machine() {
        return id_etat_machine;
    }

    public void setId_etat_machine(int id_etat_machine) {
        this.id_etat_machine = id_etat_machine;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}