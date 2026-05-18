package dao;

import modelo.Evento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventoDAO {
    private String url = "jdbc:sqlite:prueba.sqlite";

    public void insertarEvento(Evento ev) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO eventos (nombre, ubicacion, fecha, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ev.getNombre());
            ps.setString(2, ev.getUbicacion());
            ps.setString(3, ev.getFecha());
            ps.setDouble(4, ev.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());
        }
    }

    public void actualizarEvento(Evento ev) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "UPDATE evento SET nombre = ?, ubicacion = ?, precio = ? WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ev.getNombre());
            ps.setString(2, ev.getUbicacion());
            ps.setDouble(3, ev.getPrecio());
            ps.setInt(4, ev.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());
        }
    }

    public void borradoEvento(int id) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "DELETE FROM evento WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database" + e.getMessage());
        }
    }
    public void obtenerEventosTotalAsistentes() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT "
        }
    }
}




