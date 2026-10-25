package com.krakdev.jdbc.videojuegos.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.jdbc.videojuegos.services.ServicioVideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojuegos")
public class VideojuegoJdbcController {

    @Autowired
    private ServicioVideojuegoJdbc servicio;

    @PostMapping
    public Videojuego insertar(@RequestBody Videojuego videojuego) {
        try {
            return servicio.crear(videojuego);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    public List<Videojuego> listar() {
        try {
            return servicio.listar();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{codigo}")
    public Videojuego buscarPorCodigo(@PathVariable String codigo) {
        try {
            return servicio.buscarPorCodigo(codigo);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{codigo}")
    public Videojuego actualizar(@PathVariable String codigo, @RequestBody Videojuego videojuego) {
        try {
            videojuego.setCodigo(codigo);
            return servicio.actualizar(videojuego);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{codigo}")
    public boolean eliminar(@PathVariable String codigo) {
        try {
            return servicio.eliminar(codigo);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}