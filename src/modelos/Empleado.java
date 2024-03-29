package modelos;

public class Empleado extends Persona{
    private String cargo;
    private String genero;
    private int edad;
    private String email;
    private double salario;
    private double afp;
    private double isss;
    private double renta;
    private int estado;
    private Empleado responsable;
    private Usuario usuario;
            
    public Empleado() {
        
    }

    public Empleado(int idPersona) {
        super(idPersona);
    }

    public Empleado(String cargo, String genero, int edad, String email, double salario, double afp, double isss, double renta, int estado, int idPersona, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(idPersona, dui, nombre, apellido, direccion, telefono);
        this.cargo = cargo;
        this.genero = genero;
        this.edad = edad;
        this.email = email;
        this.salario = salario;
        this.afp = afp;
        this.isss = isss;
        this.renta = renta;
        this.estado = estado;
    }

    public Empleado(String cargo, String genero, int edad, String email, double salario, double afp, double isss, double renta, int estado, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(dui, nombre, apellido, direccion, telefono);
        this.cargo = cargo;
        this.genero = genero;
        this.edad = edad;
        this.email = email;
        this.salario = salario;
        this.afp = afp;
        this.isss = isss;
        this.renta = renta;
        this.estado = estado;
    }

    public Empleado(int idPersona, String nombre, String apellido) {
        super(idPersona, nombre, apellido);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public double getAfp() {
        return afp;
    }

    public void setAfp(double afp) {
        this.afp = afp;
    }

    public double getIsss() {
        return isss;
    }

    public void setIsss(double isss) {
        this.isss = isss;
    }

    public double getRenta() {
        return renta;
    }

    public void setRenta(double renta) {
        this.renta = renta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}