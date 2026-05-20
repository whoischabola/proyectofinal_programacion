import dao.AsistenteDAO;
import dao.EventoDAO;
import modelo.Evento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
    // Evento
        EventoDAO eventoDAO = new EventoDAO();
        Evento e = new Evento("Tour Museo del Prado", "Madrid", "2026-06-02", 25.00);


                EventoDAO eventoDAO1 = new EventoDAO();
                AsistenteDAO asistenteDAO = new AsistenteDAO();

                // ════════════════════════════════════════════════
                //("EVENTO");

                // Insert → update → delete del mismo evento
                eventoDAO.insertarEvento("Tour Museo del Prado", "2026-01-01", "Sevilla", 40.0, 100);
                eventoDAO.actualizarEvento(6, "Evento Test ACTUALIZADO", "2026-01-01", "Malaga", 60.0, 100);
                eventoDAO.borradoEvento(6);

                // ════════════════════════════════════════════════
                //("ASISTENTE");

                // Insert → update → delete del mismo asistente
                asistenteDAO.insertarAsistente("Marina", "marina@marina.com", "2000-05-10");
                asistenteDAO.actualizar(7, "Maria", "marina@marina.com", "2000-05-10");
                asistenteDAO.borrar(7);

                // ════════════════════════════════════════════════
                //(("INSCRIPCION");


                asistenteDAO.inscribirAsistente(5, 2, "2026-01-10");
                asistenteDAO.eliminarInscripcion(5, 2);

                // ════════════════════════════════════════════════
                //("CONSULTAS EVENTO");

                System.out.println("Todos los eventos con numero de asistentes:");
                eventoDAO.obtenerTodosConAsistentes();

                System.out.println("Asistentes del evento id=1:");
                eventoDAO.obtenerAsistentesPorEvento(1);

                System.out.println("Eventos con mas de 2 asistentes:");
                eventoDAO.obtenerEventosConMasDe2Asistentes();

                System.out.println("Top 3 eventos por ingresos:");
                eventoDAO.obtenerTop3Ingresos();

                System.out.println("Evento mas caro en Madrid:");
                eventoDAO.obtenerMasCaroPorUbicacion("Madrid");

                // ════════════════════════════════════════════════
                //("CONSULTAS ASISTENTE");

                System.out.println("Todos los asistentes con gasto total:");
                asistenteDAO.obtenerTodosConGastoTotal();

                System.out.println("Edad media de los asistentes:");
                asistenteDAO.obtenerEdadMediaEdad();

                System.out.println("Asistentes sin ninguna inscripcion:");
                asistenteDAO.obtenerSinInscripcion();
            }

            }





