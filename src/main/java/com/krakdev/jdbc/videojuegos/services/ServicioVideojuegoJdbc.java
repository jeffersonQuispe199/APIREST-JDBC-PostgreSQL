package com.krakdev.jdbc.videojuegos.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {

    private VideojuegoJdbc videojuegoJdbc = new VideojuegoJdbc();

    public Videojuego crear(Videojuego videojuego) throws SQLException {
        return videojuegoJdbc.insertar(videojuego);
    }

    public List<Videojuego> listar() throws SQLException {
        return videojuegoJdbc.listar();
    }

    public Videojuego buscarPorCodigo(String codigo) throws SQLException {
        return videojuegoJdbc.buscar(codigo);
    }

    public Videojuego actualizar(Videojuego videojuego) throws SQLException {
        return videojuegoJdbc.actualizar(videojuego);
    }

    public boolean eliminar(String codigo) throws SQLException {
        return videojuegoJdbc.eliminar(codigo);
    }
}