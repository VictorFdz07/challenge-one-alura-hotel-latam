package com.alura.hotel.controller;

import com.alura.hotel.dao.UsuarioDAO;
import com.alura.hotel.factory.ConnectionFactory;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperaConexion());
    }

    public int login(String usuario, String clave) {
        return usuarioDAO.login(usuario,clave);
    }
}
