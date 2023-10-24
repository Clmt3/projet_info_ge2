package com.insa.projet_info.projet_ge2_2023.modele;

import com.insa.projet_info.projet_ge2_2023.controleur.OperationRow;
import com.insa.projet_info.projet_ge2_2023.controleur.PosteTravailRow;
import com.insa.projet_info.projet_ge2_2023.controleur.PosteTravailRowEtat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://92.222.25.165:3306/m3_cmassotte01";
    private static final String USERNAME = "m3_cmassotte01";
    private static final String PASSWORD = "32d93c42";

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Méthode pour ajouter un utilisateur à la base de données
    public static boolean ajouterUtilisateur(ActionEvent event, String nom, String prenom, String email, String motdepasse) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO utilisateur (nom, prenom, email, motdepasse) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, motdepasse);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour obtenir la liste des utilisateurs (excluant l'admin)
    public static List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try {
            Connection connection = getConnection(); // Obtenez la connexion à la base de données

            // Écrivez votre requête SQL pour récupérer les utilisateurs (excluant l'admin)
            String sql = "SELECT * FROM Utilisateur WHERE nom <> 'admin'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Récupérez les données de chaque utilisateur
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String motdepasse = resultSet.getString("motdepasse");

                // Créez un objet Utilisateur et ajoutez-le à la liste
                Utilisateur utilisateur = new Utilisateur(id, nom, prenom, email, motdepasse);
                utilisateurs.add(utilisateur);
            }

            // Fermez les ressources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    public static boolean modifierOperateur(int operateurId, String nom, String prenom, String email, String motdepasse) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, motdepasse = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, motdepasse);
            preparedStatement.setInt(5, operateurId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimerOperateur(int operateurId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "DELETE FROM utilisateur WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, operateurId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean ajouterMachine(String reference, String description, int puissance) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "INSERT INTO Machine (reference, description, puissance) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, reference);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, puissance);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modifierMachine(int machineId, String reference, String description, int puissance) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "UPDATE Machine SET reference = ?, description = ?, puissance = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, reference);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, puissance);
            preparedStatement.setInt(4, machineId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimerMachine(int machineId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "DELETE FROM Machine WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, machineId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Machine> getMachines() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Machine> machines = new ArrayList<>();

        try {
            conn = getConnection();

            // Requête SQL pour récupérer toutes les machines
            String sql = "SELECT * FROM Machine";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Parcourez les résultats de la requête et créez des objets Machine
            while (rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String description = rs.getString("description");
                int puissance = rs.getInt("puissance");

                // Créez une instance de Machine et ajoutez-la à la liste
                Machine machine = new Machine(id, reference, description, puissance);
                machines.add(machine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return machines;
    }

    // Méthode pour obtenir l'ID de la machine à partir de la base de données en fonction de l'ID de l'opération
    public static int getIdMachineFromDatabase(int idOperation) {
        int idMachine = -1; // Valeur par défaut si aucune correspondance n'est trouvée

        try (Connection connection = getConnection()) {
            String sql = "SELECT Machine.id FROM Machine " +
                    "JOIN Realise ON Realise.id_machine = Machine.id " +
                    "WHERE Realise.id_operation = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idOperation);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        idMachine = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idMachine;
    }

    public static boolean ajouterTypeOperation(String description) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "INSERT INTO TypeOperation (description) VALUES (?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, description);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modifierTypeOperation(int id, String nouvelleDescription) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "UPDATE TypeOperation SET description = ? WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nouvelleDescription);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimerTypeOperation(int id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();

            String sql = "DELETE FROM TypeOperation WHERE id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<TypeOperation> getTypesOperations() {
        List<TypeOperation> typesOperations = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.createStatement();

            String sql = "SELECT * FROM TypeOperation";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                TypeOperation typeOperation = new TypeOperation(id, description);
                typesOperations.add(typeOperation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typesOperations;
    }

    // Méthode pour ajouter un produit
    public static boolean ajouterProduit(String reference, String description) {
                try {
                    Connection conn = getConnection();

            String query = "INSERT INTO produit (reference, description) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, reference);
                preparedStatement.setString(2, description);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour modifier un produit
    public static boolean modifierProduit(int id, String nouvelleReference, String nouvelleDescription) {
                try {
            Connection conn = getConnection();

            String query = "UPDATE produit SET reference = ?, description = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, nouvelleReference);
                preparedStatement.setString(2, nouvelleDescription);
                preparedStatement.setInt(3, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour supprimer un produit
    public static boolean supprimerProduit(int id) {
                try {
                    Connection conn = getConnection();

            String query = "DELETE FROM produit WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour récupérer la liste des produits depuis la base de données
    public static List<Produit> getProduits() {
        List<Produit> produits = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM produit";
            try (Statement statement = conn.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String reference = resultSet.getString("reference");
                        String description = resultSet.getString("description");
                        produits.add(new Produit(id, reference, description));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    public static boolean ajouterOperation(int idProduit, int idTypeOperation) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO Operation (id_produit, id_type_operation) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idProduit);
            stmt.setInt(2, idTypeOperation);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supprimerOperation(int idOperation) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "DELETE FROM Operation WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idOperation);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ObservableList<OperationRow> getOperations() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ObservableList<OperationRow> operations = FXCollections.observableArrayList();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String query = "SELECT Operation.id, Produit.reference, TypeOperation.description " +
                    "FROM Operation " +
                    "INNER JOIN Produit ON Operation.id_produit = Produit.id " +
                    "INNER JOIN TypeOperation ON Operation.id_type_operation = TypeOperation.id";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String referenceProduit = rs.getString("reference");
                String descriptionTypeOperation = rs.getString("description");
                OperationRow operationRow = new OperationRow(id, referenceProduit, descriptionTypeOperation);
                operations.add(operationRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
    }

    // Methode pour obtenir la liste des produits

    public static List<Produit> getListeProduits() {
        List<Produit> produits = new ArrayList<>();

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Produit";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String reference = resultSet.getString("reference");
                String description = resultSet.getString("description");
                Produit produit = new Produit(id, reference, description);
                produits.add(produit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    public static List<OperationRow> getOperations(int id_produit) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OperationRow> operationRows = new ArrayList<>();

        try {
            conn = getConnection();
            String query = "SELECT Operation.id, Produit.reference, TypeOperation.description " +
                    "FROM Operation " +
                    "INNER JOIN Produit ON Operation.id_produit = Produit.id " +
                    "INNER JOIN TypeOperation ON Operation.id_type_operation = TypeOperation.id " +
                    "WHERE Produit.id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_produit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String referenceProduit = rs.getString("reference");
                String descriptionTypeOperation = rs.getString("description");
                OperationRow operationRow = new OperationRow(id, referenceProduit, descriptionTypeOperation);
                operationRows.add(operationRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operationRows;
    }

    public static List<Machine> getMachinesNonAffectees() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Machine> machinesNonAffectees = new ArrayList<>();

        try {
            conn = getConnection();
            String query = "SELECT * FROM Machine WHERE id NOT IN (SELECT id_machine FROM PosteDeTravail)";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String description = rs.getString("description");
                int puissance = rs.getInt("puissance");
                Machine machine = new Machine(id, reference, description, puissance);
                machinesNonAffectees.add(machine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return machinesNonAffectees;
    }

    public static int ajouterPosteDeTravail(int idUtilisateur, int idMachine, String reference, String description) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        int idPosteDeTravail = -1;

        try {
            conn = getConnection();
            String query = "INSERT INTO PosteDeTravail (id_utilisateur, id_machine, reference, description) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idUtilisateur);
            stmt.setInt(2, idMachine);
            stmt.setString(3, reference);
            stmt.setString(4, description);
            stmt.executeUpdate();

            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idPosteDeTravail = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPosteDeTravail;
    }

    public static void ajouterUtilise(int idUtilisateur, int idPosteDeTravail) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO Utilise (id_utilisateur, id_poste_de_travail) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idUtilisateur);
            stmt.setInt(2, idPosteDeTravail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static void ajouterRealise(int idMachine, int idOperation, int statut) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "INSERT INTO Realise (id_machine, id_operation, statut) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idMachine);
            stmt.setInt(2, idOperation);
            stmt.setInt(3, statut);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<PosteTravailRow> listePosteTravails() {
        List<PosteTravailRow> posteTravailRows = new ArrayList<>();

        String query = "SELECT CONCAT(Utilisateur.nom, ' ', Utilisateur.prenom) AS nomPrenom, " +
                "Machine.id AS idMachine, " +
                "Utilisateur.id AS idUtilisateur, " +
                "Machine.reference AS referenceMachine, " +
                "Operation.id AS idOperation, " +
                "TypeOperation.description AS descriptionOperation, " +
                "Produit.reference AS referenceProduit, " +
                "PosteDeTravail.id AS id " +
                "FROM PosteDeTravail " +
                "INNER JOIN Utilisateur ON PosteDeTravail.id_utilisateur = Utilisateur.id " +
                "INNER JOIN Machine ON PosteDeTravail.id_machine = Machine.id " +
                "INNER JOIN Realise ON PosteDeTravail.id_machine = Realise.id_machine " +
                "INNER JOIN Operation ON Realise.id_operation = Operation.id " +
                "INNER JOIN Produit ON Operation.id_produit = Produit.id " +
                "INNER JOIN TypeOperation ON Operation.id_type_operation = TypeOperation.id";


        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nomPrenom = rs.getString("nomPrenom");
                String referenceMachine = rs.getString("referenceMachine");
                String referenceProduit = rs.getString("referenceProduit");
                int idOperation = rs.getInt("idOperation");
                String descriptionOperation = rs.getString("descriptionOperation");
                PosteTravailRow posteTravailRow = new PosteTravailRow(nomPrenom, referenceMachine, idOperation, descriptionOperation, referenceProduit);
                posteTravailRow.setId(rs.getInt("id"));
                posteTravailRow.setIdMachine(rs.getInt("idMachine"));
                posteTravailRow.setIdUtilisateur(rs.getInt("idUtilisateur"));
                posteTravailRows.add(posteTravailRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posteTravailRows;
    }

    public static void supprimerRealise(int idMachine, int idOperation) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "DELETE FROM Realise WHERE id_machine = ? AND id_operation = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idMachine);
            stmt.setInt(2, idOperation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerUtilise(int idUtilisateur, int idPosteDeTravail) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "DELETE FROM Utilise WHERE id_utilisateur = ? AND id_poste_de_travail = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idUtilisateur);
            stmt.setInt(2, idPosteDeTravail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerPosteDeTravail(int idPosteDeTravail) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String query = "DELETE FROM PosteDeTravail WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idPosteDeTravail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<PosteTravailRowEtat> listePosteTravailRowEtats() {
        List<PosteTravailRowEtat> posteTravailRowEtats = new ArrayList<>();

        try {
            Connection connection = getConnection(); // Obtenez votre connexion à la base de données

            // Construisez votre requête SQL pour récupérer les données nécessaires des tables
            String query = "SELECT " +
                    "CONCAT(Utilisateur.nom, ' ', Utilisateur.prenom) AS nomPrenom, " +
                    "Machine.reference AS referenceMachine, " +
                    "Machine.id AS idMachine, " +
                    "Operation.id AS idOperation, " +
                    "TypeOperation.description AS descriptionOperation, " +
                    "Produit.reference AS referenceProduit " +
                    "FROM PosteDeTravail " +
                    "INNER JOIN Utilisateur ON PosteDeTravail.id_utilisateur = Utilisateur.id " +
                    "INNER JOIN Machine ON PosteDeTravail.id_machine = Machine.id " +
                    "INNER JOIN Realise ON Machine.id = Realise.id_machine " +
                    "INNER JOIN Operation ON Realise.id_operation = Operation.id " +
                    "INNER JOIN TypeOperation ON Operation.id_type_operation = TypeOperation.id " +
                    "INNER JOIN Produit ON Operation.id_produit = Produit.id";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nomPrenom = resultSet.getString("nomPrenom");
                String referenceMachine = resultSet.getString("referenceMachine");
                int idOperation = resultSet.getInt("idOperation");
                String descriptionOperation = resultSet.getString("descriptionOperation");
                String referenceProduit = resultSet.getString("referenceProduit");

                PosteTravailRowEtat posteTravailRowEtat = new PosteTravailRowEtat(nomPrenom, referenceMachine, idOperation, descriptionOperation, referenceProduit);

                EtatMachine etatMachine = getEtatMachineById(resultSet.getInt("idMachine"));
                if(etatMachine != null) {
                    posteTravailRowEtat.setDebut(etatMachine.getDebut());
                    posteTravailRowEtat.setFin(etatMachine.getFin());

                    posteTravailRowEtat.setEtat("En production");

                    if(etatMachine.getFin().before(new Timestamp(System.currentTimeMillis()))) {
                        posteTravailRowEtat.setEtat("Production Terminé");
                    }
                }
                posteTravailRowEtats.add(posteTravailRowEtat);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posteTravailRowEtats;
    }

    public static Machine getMachine(String referenceMachine) {
        Machine machine = null;
        
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM Machine WHERE reference = ?");
            preparedStatement.setString(1, referenceMachine);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String description = resultSet.getString("description");
                    int puissance = resultSet.getInt("puissance");
                    
                    machine = new Machine(id, referenceMachine, description, puissance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return machine;
    }

    public static EtatMachine getEtatMachineById(int idMachine) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EtatMachine etatMachine = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM etat_machine WHERE id_machine = ? ORDER BY fin DESC LIMIT 1";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idMachine);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Timestamp debut = rs.getTimestamp("debut");
                Timestamp fin = rs.getTimestamp("fin");
                etatMachine = new EtatMachine(debut, fin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etatMachine;
    }


    public static void ajouterEtatMachine(EtatMachine etatMachine) {
        // Define your SQL query for inserting data
        String insertQuery = "INSERT INTO Etat_Machine (id_machine, debut, fin) VALUES (?, ?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            // Set values for the PreparedStatement
            preparedStatement.setInt(1, etatMachine.getId_machine());
            preparedStatement.setTimestamp(2, etatMachine.getDebut());
            preparedStatement.setTimestamp(3, etatMachine.getFin());

            // Execute the SQL query to insert the data
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during the database operation
        }
    }
}
