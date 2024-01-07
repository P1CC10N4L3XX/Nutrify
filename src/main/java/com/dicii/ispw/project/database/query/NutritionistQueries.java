package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.models.UserCredentials;

import java.sql.*;

public class NutritionistQueries extends Queries{
        public static boolean insertIntoNutritionist(Statement statement, UserCredentials nutritionist) throws SQLException {
            String sql = String.format("INSERT INTO nutrizionista (Email,Password) values('%s','%s')",nutritionist.getEmail(),nutritionist.getPassword());
            return statement.execute(sql);
        }
        public static void updateNutritionistAll(Statement statement,Nutritionist nutritionist) throws SQLException{
            String sql = String.format("UPDATE nutrizionista SET Nome = '%s', Cognome = '%s', DataDiNascita = '%s', Iban = '%s', Iva = '%s', CostoMensile = '%s', Descrizione = '%s' WHERE Email = '%s' ",nutritionist.getName(),nutritionist.getSurname(),nutritionist.getDateOfBirth(),nutritionist.getIban(),nutritionist.getIva(),nutritionist.getCosto(),nutritionist.getDescription(),nutritionist.getEmail());
            statement.executeUpdate(sql);
        }

}





