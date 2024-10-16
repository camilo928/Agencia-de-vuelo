    package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/usuarios";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error en la conexion: " + e.getMessage());
        }
        return conn;
    }
    

    public List<Usuario> obtenerUsuario() {
        List<Usuario> Usuario = new ArrayList<>();
        String sql = "SELECT identificacion, nombre, apellido, email, telefono, us, contraseña FROM usuario";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String identificacion = rs.getString("identificacion");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String user = rs.getString("us");
                String contraseña = rs.getString("contraseña");

                Usuario usuario = new Usuario(identificacion, nombre, apellido, email, telefono, user, contraseña);
                Usuario.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener pilotos: " + e.getMessage());
        }

        return Usuario;
    }
    public void EliminarUsuario(String identificacion){
      String sql = "DELETE FROM usuario WHERE identificacion = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, identificacion);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("No se encontró el usuario con identificacion: " + identificacion);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
    }
    }
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM usuario WHERE us = ? AND contraseña = ?";  
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario: " + e.getMessage());
            return false;
        }
    }
}
