package dao;

import java.sql.*;

public class AsistenteDAO {
    private String url = "jdbc:sqlite:prueba.sqlite";

    // a. Insertar---------
    public void insertarAsistente(String nombre, String email, String fechaNacimiento){
        String sql = "INSERT INTO asistentes (nombre, email, fechaNacimiento, edad) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, fechaNacimiento);
            ps.setInt(4, edad);
            ps.executeUpdate();
            System.out.println("Asistente insertado " + nombre);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // b.Actualizar---------
    public void actualizar(int id, String nombre, String email, String fechaNacimiento) {
        String sql = "UPDATE asistentes SET nombre=?, email=?, fecha_nacimiento=?, edad=? WHERE id=?";
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, fechaNacimiento);
            ps.setInt(4, edad);
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Asistente actualizado: " + nombre);

        } catch (SQLException e) {
            System.err.println("Error actualizar asistente: " + e.getMessage());
        }
    }
    // c.Eliminar---------
    public void borrar(int id) {
        String sql = "DELETE FROM asistentes WHERE id=?";
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Asistente borrado con id=" + id);

        } catch (SQLException e) {
            System.err.println("Error borrar asistente: " + e.getMessage());
        }
    }
    // d.Inscribir asistente a evento---------
    public void inscribirAsistente(int idAsistente, int idEvento, String fecha) {
        String sql = "INSERT INTO inscripciones (evento_id, asistente_id, fecha_inscripcion) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, idEvento);
            ps.setInt(2, idAsistente);
            ps.setString(3, fecha);
            ps.executeUpdate();
            System.out.println("Asistente inscribido con id=" + idAsistente);


        } catch (SQLException e) {
            System.err.println("Error inscribir asistente: " + e.getMessage());
        }
    }
    // e. Eliminar a un asistente de un evento
    public void eliminarInscripcion(int idAsistente, int idEvento)  {
    String sql = "DELETE FROM inscripciones WHERE asistente_id=? AND evemto_id=?";
    try (Connection conn = DriverManager.getConnection(url)) {
        PreparedStatement ps = conn.prepareStatement(sql); {

            ps.setInt(1, idAsistente);
            ps.setInt(2, idEvento);
            ps.executeUpdate();
            System.out.println("Asistente eliminado con id=" + idAsistente);

        }
    } catch (SQLException e) {
        System.err.println("Error al eliminar asistente: " + e.getMessage());
    }
    }
    // f.Todos los asistentes con su gasto total
    public void obtenerTodosConGastoTotal() {
        String sql = "SELECT a.nombre, SUM(e.precio) AS gasto_total " +
                "FROM asistente a " + "JOIN inscripciones i ON a.id = i.id_asistente " +
                "JOIN evento e ON i.id_evento = e.id " + "GROUP BY a.id";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); {

                while (rs.next()) {
                    System.out.println(rs.getString("nombre") + rs.getDouble("gasto_total") + " €");

                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // g. Edad media de los asitentes
    public void obtenerEdadMediaEdad() {
            String sql = "SELECT fecha_nacimiento FROM asistente";
            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                int sumaEdades = 0;
                int contador = 0;
                int anioActual = 2026;

                while (rs.next()) {
                    String fecha = rs.getString("fecha_nacimiento"); // "1990-03-15"
                    int anioNacimiento = Integer.parseInt(fecha.substring(0, 4));
                    int edad = anioActual - anioNacimiento;
                    sumaEdades += edad;
                    contador++;
                }

                if (contador > 0) {
                    System.out.println("Edad media: " + (sumaEdades / contador) + " años");
                }

            } catch (SQLException e) {
                System.err.println("Error obtenerEdadMedia: " + e.getMessage());
            }
        }
        // h. Asistentes sin ninguna inscripción
        public void obtenerSinInscripcion() {
        String sql = "SELECT a.nombre FROM asistentes a " + "LEFT JOIN inscripciones i ON a.id = i.id_asistente " +
                "WHERE i.id_evento IS NULL";

            try (Connection con = DriverManager.getConnection(url);
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    System.out.println(rs.getString("nombre"));
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

