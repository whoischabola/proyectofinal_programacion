import dao.EventoDAO;
import modelo.Evento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EventoDAO eventoDAO = new EventoDAO();
        Evento e = new Evento("Tour Museo del Prado", "Madrid", "2026-06-02", 25.00);
        eventoDAO.insertarEvento(e);

        }
        }

