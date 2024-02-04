package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.IlnessesQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IlnessesDao {

    public IlnessesDao(){}


    public static List<Ilnesses> displayIlnesses() throws DuplicatedUserException {
        ArrayList<Ilnesses> ilnesses = new ArrayList<>() ;
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = IlnessesQueries.displayIlnesses(statement)) {

            while (resultSet.next()) {

                Ilnesses illness = createIlnesses(resultSet);
                ilnesses.add(illness);

            }
        }catch(SQLException e){
            throw new DuplicatedUserException(e.getMessage());
        }

        return ilnesses;

    }

    public static Ilnesses createIlnesses(ResultSet resultSet) throws SQLException {

        String name = resultSet.getString("Nome");

        return new Ilnesses(name);

    }





}
