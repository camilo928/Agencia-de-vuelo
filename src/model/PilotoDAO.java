package model;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PilotoDAO {

     public List<Piloto> obtenerPiloto() {
        List<Piloto> Piloto = new ArrayList<>();
        String sql = "SELECT identificacion, nombre, apellido, correo, telefono, avion, fechaContrato FROM piloto";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String identificacion = rs.getString("identificacion");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String avion = rs.getString("avion");

                Piloto piloto = new Piloto(identificacion, nombre, apellido, correo, telefono, avion);
                Piloto.add(piloto);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener pilotos: " + e.getMessage());
        }

        return Piloto;
    }
    
    public void contratarPiloto(String identificacion, String nombre, String apellido, String correo, String telefono,String avion, LocalDate fechaContrato) {
   //String codigo = generarCodigoPiloto();

    String sql = "INSERT INTO piloto (identificacion, nombre, apellido, correo, telefono, avion, fechaContrato) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = ConexionDB.conectar();
         PreparedStatement pstmt = con.prepareStatement(sql)) {

        pstmt.setString(1, identificacion);
        pstmt.setString(2, nombre);
        pstmt.setString(3, apellido);
        pstmt.setString(4, correo);
        pstmt.setString(5, telefono);
        pstmt.setString(6, avion);
        pstmt.setDate(7, java.sql.Date.valueOf(fechaContrato));

        pstmt.executeUpdate();
        System.out.println("Piloto insertado con exito.");
        
    } catch (SQLException e) {
        System.err.println("Error al insertar el piloto: " + e.getMessage());
    }
}
    public void EliminarPiloto(String identificacion){
      String sql = "DELETE FROM piloto WHERE identificacion = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, identificacion);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Piloto eliminado con éxito.");
            } else {
                System.out.println("No se encontró el pioto con identificacion: " + identificacion);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar piloto: " + e.getMessage());
        }
    }


   /* private String generarCodigoPiloto() {
        String codigo = "PIL01"; 
        String sql = "SELECT Codigo_piloto FROM piloto ORDER BY Codigo_piloto DESC LIMIT 1";  

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                String ultimoCodigo = rs.getString("Codigo_piloto");
                int numero = Integer.parseInt(ultimoCodigo.substring(4));  
                numero++;
                codigo = String.format("PIL%03d", numero);
            }
        } catch (SQLException e) {
            System.err.println("Error al generar el codigo de profesor: " + e.getMessage());
        }

        return codigo;
    }*/
}
