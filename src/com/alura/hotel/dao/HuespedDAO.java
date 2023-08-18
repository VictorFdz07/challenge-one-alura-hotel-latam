package com.alura.hotel.dao;


import com.alura.hotel.modelo.Huesped;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {

    final private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huesped huesped) {
        try{
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO huespedes"
                            + "(nombre,apellido,fechaNacimiento, nacionalidad, telefono, idReserva)"
                            + " VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            try(statement){
                ejecutaRegistro(huesped, statement);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ejecutaRegistro(Huesped huesped, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, huesped.getNombre());
        statement.setString(2, huesped.getApellido());
        statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
        statement.setString(4, huesped.getNacionalidad());
        statement.setString(5, huesped.getTelefono());
        statement.setInt(6, huesped.getIdReserva());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet){
            while(resultSet.next()) {
                huesped.setId(resultSet.getInt(1));
                JOptionPane.showMessageDialog(null, "El huesped fue registrada con el id " +
                        huesped.getId(), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public List<Huesped> listarPorApellido(String apellido) {
        List<Huesped> resultado = new ArrayList<>();

        try{

            final PreparedStatement statement = con
                    .prepareStatement("SELECT id,nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva "
                            + "FROM huespedes "
                            + "WHERE apellido = ?");

            try(statement){
                statement.setString(1, apellido);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while(resultSet.next()) {
                        Huesped fila = new Huesped(resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fechaNacimiento").toLocalDate(),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("idReserva"));

                        resultado.add(fila);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try{

            final PreparedStatement statement = con.prepareStatement("SELECT id,nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva "+
                    "FROM huespedes");

            try(statement){

                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while(resultSet.next()) {
                        Huesped fila = new Huesped(resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fechaNacimiento").toLocalDate(),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("idReserva"));

                        resultado.add(fila);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int modificar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
        try{
            final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
                    + "nombre = ?"
                    + ", apellido = ?"
                    + ", fechaNacimiento = ?"
                    + ", nacionalidad = ?"
                    + ", telefono = ?"
                    + ", idReserva = ?"
                    + " WHERE ID = ?");

            try(statement){

                statement.setString(1, nombre);
                statement.setString(2,apellido);
                statement.setDate(3, Date.valueOf(fechaNacimiento));
                statement.setString(4,nacionalidad);
                statement.setString(5,telefono);
                statement.setInt(6, idReserva);
                statement.setInt(7, id);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
