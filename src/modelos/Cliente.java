package modelos;

public class Cliente extends Persona{
    private String email;

    public Cliente(){

    }

    public Cliente(int idPersona) {
        super(idPersona);
    }

    public Cliente(String email, int idPersona, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(idPersona, dui, nombre, apellido, direccion, telefono);
        this.email = email;
    }

    public Cliente(String email, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(dui, nombre, apellido, direccion, telefono);
        this.email = email;
    }

    public Cliente(int idPersona, String nombre, String apellido) {
        super(idPersona, nombre, apellido);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}