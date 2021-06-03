package modelos;

public class DetalleFactura {
    private int cantidadProducto;
    private double subtotal;
    private Producto producto;
    private Factura factura;
    
    public DetalleFactura() {
        
    }

    public DetalleFactura(int cantidadProducto, double subtotal) {
        this.cantidadProducto = cantidadProducto;
        this.subtotal = subtotal;
    }

    public DetalleFactura(int cantidadProducto, double subtotal, Producto producto, Factura factura) {
        this.cantidadProducto = cantidadProducto;
        this.subtotal = subtotal;
        this.producto = producto;
        this.factura = factura;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    
}
