package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    final private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public boolean login(String usuario, String clave) {
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT nombre,clave FROM usuarios WHERE nombre = ? and clave = ?");

            try (statement) {
                statement.setString(1, usuario);
                statement.setString(2, clave);
                boolean updateCount = statement.execute();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
