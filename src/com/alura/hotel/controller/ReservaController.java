package com.alura.hotel.controller;

import com.alura.hotel.dao.ReservaDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Reserva;

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
}
