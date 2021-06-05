package modelos;

import java.io.FileInputStream;
import java.sql.Blob;
import java.util.ArrayList;

public class Producto {
    private int codProducto;
    private Blob bdFoto;
    private FileInputStream fileFoto;
    private String descripcion;
    private Sucursal sucursal;
    private Proveedor proveedor;
    private Categoria categoria;
    private ArrayList<ProductoEstado> estadosProducto;
    private ArrayList<DetalleFactura> detalles;
    
    public Producto() {
        
    }

    public Producto(int codProducto) {
        this.codProducto = codProducto;
    }

    public Producto(int codProducto, FileInputStream fileFoto, String descripcion) {
        this.codProducto = codProducto;
        this.fileFoto = fileFoto;
        this.descripcion = descripcion;
    }

    public Producto(FileInputStream fileFoto, String descripcion) {
        this.fileFoto = fileFoto;
        this.descripcion = descripcion;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public Blob getBdFoto() {
        return bdFoto;
    }

    public void setBdFoto(Blob bdFoto) {
        this.bdFoto = bdFoto;
    }

    public FileInputStream getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(FileInputStream fileFoto) {
        this.fileFoto = fileFoto;
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
