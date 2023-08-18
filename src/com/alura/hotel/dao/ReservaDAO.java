package com.alura.hotel.dao;

import com.alura.hotel.modelo.Reserva;

import java.sql.*;

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
                System.out.println(String.format("Fue insertado el producto de ID %s", reserva));
            }
        }

    }
}
