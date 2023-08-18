package com.alura.hotel.controller;

import com.alura.hotel.dao.HuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;

import java.time.LocalDate;
import java.util.List;

public class HuespedController {

    private HuespedDAO huespedDAO;

    public HuespedController(){
        this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Huesped huesped){
        huespedDAO.guardar(huesped);
    }

    public List<Huesped> listarPorApellido(String apellido){
        return huespedDAO.listarPorApellido(apellido);
    }

    public List<Huesped> listar(){
        return huespedDAO.listar();
    }

    public int modificar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id) {
        return huespedDAO.modificar(nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva,id);
    }
}
