package modelos;

import java.util.ArrayList;
import modelos.dao.ClienteDao;
import modelos.dao.DetalleFacturaDao;
import modelos.dao.EmpleadoDao;

public class Factura {
    private int noFactura;
    private double iva;
    private String fecha;
    private double total;
    private Empleado vendedor;
    private Cliente cliente;
    private ArrayList<DetalleFactura> detalles;
    
    public Factura() {
        
    }

    public Factura(int noFactura) {
        this.noFactura = noFactura;
    }

    public Factura(int noFactura, double iva, String fecha, double total) {
        this.noFactura = noFactura;
        this.iva = iva;
        this.fecha = fecha;
        this.total = total;
    }

    public Factura(double iva, String fecha, double total) {
        this.iva = iva;
        this.fecha = fecha;
        this.total = total;
    }

    public Factura(double iva, String fecha, double total, Empleado vendedor, Cliente cliente) {
        this.iva = iva;
        this.fecha = fecha;
        this.total = total;
        this.vendedor = vendedor;
        this.cliente = cliente;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Empleado getVendedor() {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        
        vendedor = empleadoDao.selectAllTo("id_empleado2", String.valueOf(vendedor.getIdPersona())).get(0);

        return vendedor;
    }

    public void setVendedor(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        ClienteDao clienteDao = new ClienteDao();
        
        cliente = clienteDao.selectAllTo("id_cliente1", String.valueOf(cliente.getIdPersona())).get(0);
        
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        DetalleFacturaDao detalleFacturaDao = new DetalleFacturaDao();
        
        detalles = detalleFacturaDao.selectAllTo("no_factura1", String.valueOf(this.getNoFactura()));
        
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
