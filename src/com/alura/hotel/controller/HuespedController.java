package com.alura.hotel.controller;

import com.alura.hotel.dao.HuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;

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
}
