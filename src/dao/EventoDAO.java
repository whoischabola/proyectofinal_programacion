package dao;

import modelo.Evento;

import java.sql.*;
import java.util.Map;

public class EventoDAO {
    private String url = "jdbc:sqlite:prueba.sqlite";

    // a.Insertar ---------
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
    // b.Actualizar ---------
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
    // c.Borrar---------
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
    // d.Obtener número total de asistentes---------
    public Map<Evento, Integer> obtenerTodosConAsistentes() {
        String sql = "SELECT e.*, COUNT(i.id_asistente) + e.precio AS total " + "FROM evento e" + "LEFT JOIN inscripcion i ON e.id = i.id_evento " + "GROUP BY e.id";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); {
                while (rs.next()) {
                    System.out.println(rs.getString("nombre: " + rs.getInt("total") + "asistentes"));

                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Map.of();
    }
    // e. Obtener todos los asistentes segun su ID---------
    public void obtenerAsistentesPorEvento(int id) throws SQLException {
        String sql = "SELECT a.* FROM asistente a " + "JOIN inscripcion i ON a.id = i.id_asistente " + "WHERE i.id_evento = ?";
        try (Connection conn = DriverManager.getConnection(url)) {
        PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " - " + rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
        // f.Eventos con más de 2 asistentes
        public void obtenerEventosConMasDe2Asistentes() throws SQLException {
        String sql = "SELECT e.nombre, COUNT(i.id_asistente) AS total " + "FROM evento e" + "JOIN inscripcion i ON e.id = i.id_evento " + "GROUP BY e.id" + "HAVING COUNT(i.id_asistente) > 2";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); {
        while (rs.next()) {
            System.out.println(rs.getString("nombre") + " → " + rs.getInt("total") + " asistentes");

        }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
        // g.Top 3 eventos con más ingresos---------
        public void obtenerTop3Ingresos() {
            String sql = "SELECT e.nombre, COUNT(i.id_asistente) * e.precio AS ingresos "
                    + "FROM evento e "
                    + "LEFT JOIN inscripcion i ON e.id = i.id_evento "
                    + "GROUP BY e.id "
                    + "ORDER BY ingresos DESC "
                    + "LIMIT 3";
            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println(rs.getString("nombre") + " → " + rs.getDouble("ingresos") + " €");
                }

            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        // h. Evento más caro de una ubicación---------
        public void obtenerMasCaroPorUbicacion(String ubicacion) {
        String sql = "SELECT * FROM evento WHERE ubicacion = ? ORDER BY precio DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, ubicacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("nombre" + rs.getDouble("precio") + " €"));

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        }

    public void insertarEvento(String eventoTest, String date, String sevilla, double v, int i) {
    }

    public void actualizarEvento(int i, String eventoTestActualizado, String date, String malaga, double v, int i1) {
    }
}










