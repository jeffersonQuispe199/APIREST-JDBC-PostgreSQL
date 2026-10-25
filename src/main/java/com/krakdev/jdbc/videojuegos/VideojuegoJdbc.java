package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

    private static final Logger logger = LoggerFactory.getLogger(VideojuegoJdbc.class);

    // Método insertar: usa INSERT, PreparedStatement y retorna un Videojuego[cite: 6]
    public Videojuego insertar(Videojuego videojuego) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO videojuegos (codigo, nombre, plataforma, precio, disponible, genero) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, videojuego.getCodigo());
            ps.setString(2, videojuego.getNombre());
            ps.setString(3, videojuego.getPlataforma());
            ps.setDouble(4, videojuego.getPrecio());
            ps.setBoolean(5, videojuego.isDisponible());
            ps.setString(6, videojuego.getGenero());

            ps.executeUpdate();
            logger.info("Videojuego insertado");
        } catch (SQLException e) {
            logger.error("Error al insertar videojuego: " + e.getMessage());
            throw e;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return videojuego;
    }

    // Método listar: retorna List<Videojuego> usando ResultSet[cite: 6]
    public List<Videojuego> listar() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre, plataforma, precio, disponible, genero FROM videojuegos";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String plataforma = rs.getString("plataforma");
                double precio = rs.getDouble("precio");
                boolean disponible = rs.getBoolean("disponible");
                String genero = rs.getString("genero");

                Videojuego v = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
                lista.add(v);
            }
        } catch (SQLException e) {
            logger.error("Error al listar videojuegos: " + e.getMessage());
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return lista;
    }

    // Método buscar: busca por código utilizando WHERE[cite: 6]
    public Videojuego buscar(String codigoBusqueda) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Videojuego videojuego = null;
        String sql = "SELECT codigo, nombre, plataforma, precio, disponible, genero FROM videojuegos WHERE codigo = ?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoBusqueda);
            rs = ps.executeQuery();

            if (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String plataforma = rs.getString("plataforma");
                double precio = rs.getDouble("precio");
                boolean disponible = rs.getBoolean("disponible");
                String genero = rs.getString("genero");

                videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
            }
        } catch (SQLException e) {
            logger.error("Error al buscar videojuego: " + e.getMessage());
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return videojuego;
    }

    // Método actualizar: actualiza registros y retorna el Videojuego actualizado[cite: 6, 9]
    public Videojuego actualizar(Videojuego videojuego) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE videojuegos SET nombre = ?, plataforma = ?, precio = ?, disponible = ?, genero = ? WHERE codigo = ?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getPlataforma());
            ps.setDouble(3, videojuego.getPrecio());
            ps.setBoolean(4, videojuego.isDisponible());
            ps.setString(5, videojuego.getGenero());
            ps.setString(6, videojuego.getCodigo());

            ps.executeUpdate();
            logger.info("Videojuego actualizado con éxito");
        } catch (SQLException e) {
            logger.error("Error al actualizar");
            throw e;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return videojuego;
    }

    // Método eliminar: elimina registros y retorna un boolean[cite: 6, 9]
    public boolean eliminar(String codigo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean eliminado = false;
        String sql = "DELETE FROM videojuegos WHERE codigo = ?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                logger.info("Videojuego eliminado con éxito");
            }
        } catch (SQLException e) {
            logger.error("Error al eliminar");
            throw e;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return eliminado;
    }
}