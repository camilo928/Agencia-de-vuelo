package model;

public class Piloto {
    private String Identificacion;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Telefono;
    private String Avion;


    public Piloto(String Identificacion, String Nombre, String Apellido, String Correo, String Telefono, String Avion) {
        this.Identificacion = Identificacion;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.Avion = Avion;
    }

    // Getters
    public String getIdentificacion() {
        return Identificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getavion() {
        return Avion;
    }
}
