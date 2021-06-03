package modelos;

import java.util.ArrayList;

public class Propietario extends Persona{
    private String nit;
    private ArrayList<Sucursal> sucursales;
    
    public Propietario() {
        
    }

    public Propietario(String nit, int idPersona, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(idPersona, dui, nombre, apellido, direccion, telefono);
        this.nit = nit;
    }

    public Propietario(String nit, String dui, String nombre, String apellido, String direccion, String telefono) {
        super(dui, nombre, apellido, direccion, telefono);
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
