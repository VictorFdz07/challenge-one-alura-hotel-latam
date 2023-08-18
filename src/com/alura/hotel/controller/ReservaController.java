package com.alura.hotel.controller;

import com.alura.hotel.dao.ReservaDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Reserva reserva){
        reservaDAO.guardar(reserva);
    }

    public List<Reserva> listarPorNoReserva(int id){
        return reservaDAO.listarPorNoReserva(id);
    }
    public List<Reserva> listar(){
        return reservaDAO.listar();
    }

    public int modificar(LocalDate fechaEntrada, LocalDate fechaSalida, float valor, String formaPago, Integer id) {
        return reservaDAO.moficar(fechaEntrada,fechaSalida,valor,formaPago,id);
    }
}
