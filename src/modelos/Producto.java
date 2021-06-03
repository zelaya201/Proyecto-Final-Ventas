package modelos;

import java.util.ArrayList;

public class Producto {
    private int codProducto;
    private String foto;
    private String descripcion;
    private Sucursal sucursal;
    private Proveedor proveedor;
    private Categoria categoria;
    private ArrayList<ProductoEstado> estadosProducto;
    private ArrayList<DetalleFactura> detalles;
    
    public Producto() {
        
    }

    public Producto(int codProducto, String foto, String descripcion) {
        this.codProducto = codProducto;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public Producto(String foto, String descripcion) {
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<ProductoEstado> getEstadosProducto() {
        return estadosProducto;
    }

    public void setEstadosProducto(ArrayList<ProductoEstado> estadosProducto) {
        this.estadosProducto = estadosProducto;
    }

    public ArrayList<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
