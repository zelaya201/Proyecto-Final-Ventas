package modelos;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
    private int noFactura;
    private double iva;
    private Date fecha;
    private double total;
    private Empleado vendedor;
    private Cliente cliente;
    private ArrayList<DetalleFactura> detalles;
    
    public Factura() {
        
    }

    public Factura(int noFactura) {
        this.noFactura = noFactura;
    }

    public Factura(int noFactura, double iva, Date fecha, double total) {
        this.noFactura = noFactura;
        this.iva = iva;
        this.fecha = fecha;
        this.total = total;
    }

    public Factura(double iva, Date fecha, double total) {
        this.iva = iva;
        this.fecha = fecha;
        this.total = total;
    }

    public int getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(int noFactura) {
        this.noFactura = noFactura;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Empleado getVendedor() {
        return vendedor;
    }

    public void setVendedor(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
