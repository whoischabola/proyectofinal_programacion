package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AsistenteDAO {
    private String url = "jdbc:sqlite:prueba.sqlite";

    // a. Insertar---------
    public void insertarAsistente(String nombre, String email, String fechaNacimiento, int edad){
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
    public void actualizar(int id, String nombre, String email, String fechaNacimiento, int edad) {
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
}
