package modelos;

public class Empleado extends Persona{

    private String genero;
    private int edad;
    private String email;
    private double salario;
    private int estado;
    private Usuario usuario;
   

            
    public Empleado() {
        
    }

    public Empleado(int idPersona) {
        super(idPersona);
    }

    public Empleado( String genero, int edad, String email, double salario, int estado, int idPersona, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(idPersona, dui, nombre, apellido, direccion, telefono);
       
        this.genero = genero;
        this.edad = edad;
        this.email = email;
        this.salario = salario;
        this.estado = estado;
    }

    public Empleado(String genero, int edad, String email, double salario, int estado, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(dui, nombre, apellido, direccion, telefono);
   
        this.genero = genero;
        this.edad = edad;
        this.email = email;
        this.salario = salario;
        this.estado = estado;
    }

    
  
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getGmail() {
        return email;
    }

    public void setGmail(String gmail) {
        this.email = gmail;
    }



    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   

   


    

}
