package com.insa.projet_info.projet_ge2_2023.modele;

// Classe représentant une Opération Effective
public class OperationEffective {
    private int id_machine;
    private int id_operation;
    private int statut;

    public OperationEffective(int id_machine, int id_operation, int statut) {
        this.id_machine = id_machine;
        this.id_operation = id_operation;
        this.statut = statut;
    }

   

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public int getId_operation() {
        return id_operation;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}