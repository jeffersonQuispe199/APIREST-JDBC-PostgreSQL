package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {

    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);

    // Parámetros de conexión a PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
    private static final String USER = "postgres";
    private static final String PASS = "199210"; // Reemplaza con tu contraseña de pgAdmin / PostgreSQL

    public static Connection conectar() throws SQLException {
        Connection connection = null;
        try {
            // Registrar y obtener la conexión
            connection = DriverManager.getConnection(URL, USER, PASS);
            logger.info("Conexión realizada");
        } catch (SQLException e) {
            logger.error("Error al conectar a la base de datos: " + e.getMessage());
            throw e;
        }
        return connection;
    }
}