package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    final private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public int login(String usuario, String clave) {
        int encontrado = 0;
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT nombre,clave FROM usuarios WHERE nombre = ? and clave = ?");

            try (statement) {
                statement.setString(1, usuario);
                statement.setString(2, clave);

                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while(resultSet.next()) {
                        encontrado++;
                    }
                }

                return encontrado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
