package com.alura.hotel.dao;

import com.alura.hotel.modelo.Reserva;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    final private Connection con;
    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reserva reserva) {
        try{
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO reservas"
                            + "(fechaEntrada,fechaSalida,valor, formaPago)"
                            + " VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            try(statement){
                ejecutaRegistro(reserva, statement);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ejecutaRegistro(Reserva reserva, PreparedStatement statement)
            throws SQLException {

        statement.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
        statement.setDate(2, Date.valueOf(reserva.getFechaSalida()));
        statement.setFloat(3, reserva.getValor());
        statement.setString(4, reserva.getFormaPago());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try(resultSet){
            while(resultSet.next()) {
                reserva.setId(resultSet.getInt(1));
                System.out.println(String.format("Fue insertado el producto de ID %s", reserva.getId()));
                JOptionPane.showMessageDialog(null, "La reserva fue registrada con el id " +
                        reserva.getId(), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public List<Reserva> listarPorNoReserva(int id) {
        List<Reserva> resultado = new ArrayList<>();

        try{

            final PreparedStatement statement = con
                    .prepareStatement("SELECT id,fechaEntrada,fechaSalida,valor,formaPago "
                            + "FROM reservas "
                            + "WHERE id = ?");

            try(statement){
                statement.setInt(1, id);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while(resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("id"),
                                resultSet.getDate("fechaEntrada").toLocalDate(),
                                resultSet.getDate("fechaSalida").toLocalDate(),
                                resultSet.getFloat("valor"),
                                resultSet.getString("formaPago"));

                        resultado.add(fila);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try{

            final PreparedStatement statement = con.prepareStatement("SELECT id,fechaEntrada,fechaSalida,valor,formaPago "+
                    "FROM reservas");

            try(statement){

                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try(resultSet){
                    while(resultSet.next()) {
                        Reserva fila = new Reserva(resultSet.getInt("id"),
                                resultSet.getDate("fechaEntrada").toLocalDate(),
                                resultSet.getDate("fechaSalida").toLocalDate(),
                                resultSet.getFloat("valor"),
                                resultSet.getString("formaPago"));

                        resultado.add(fila);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}