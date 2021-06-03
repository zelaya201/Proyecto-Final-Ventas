package modelos;

public class ProductoEstado {
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private int estado;
    private double ganancia;
    private Producto producto;
    
    public ProductoEstado(){
        
    }

    public ProductoEstado(double precioCompra, double precioVenta, int stock, int estado, double ganancia) {
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.estado = estado;
        this.ganancia = ganancia;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
