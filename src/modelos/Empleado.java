package modelos;

public class Empleado extends Persona{
    private String cargo;
    private String genero;
    private int edad;
    private String gmail;
    private double salario;
    private double afp;
    private double isss;
    private double renta;
    private int estado;
    private Empleado responsable;
    private Usuario usuario;
    private Sucursal sucursal;
            
    public Empleado() {
        
    }

    public Empleado(int idPersona) {
        super(idPersona);
    }

    public Empleado(String cargo, String genero, int edad, String gmail, double salario, double afp, double isss, double renta, int estado, int idPersona, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(idPersona, dui, nombre, apellido, direccion, telefono);
        this.cargo = cargo;
        this.genero = genero;
        this.edad = edad;
        this.gmail = gmail;
        this.salario = salario;
        this.afp = afp;
        this.isss = isss;
        this.renta = renta;
        this.estado = estado;
    }

    public Empleado(String cargo, String genero, int edad, String gmail, double salario, double afp, double isss, double renta, int estado, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(dui, nombre, apellido, direccion, telefono);
        this.cargo = cargo;
        this.genero = genero;
        this.edad = edad;
        this.gmail = gmail;
        this.salario = salario;
        this.afp = afp;
        this.isss = isss;
        this.renta = renta;
        this.estado = estado;
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
        return gmail;
    }

    public void setEmail(String email) {
        this.gmail = gmail;
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
