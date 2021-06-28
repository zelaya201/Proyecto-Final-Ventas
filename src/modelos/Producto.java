package modelos;

import java.sql.Blob;
import java.util.ArrayList;
import modelos.dao.CategoriaDao;
import modelos.dao.ProductoEstadoDao;
import modelos.dao.ProveedorDao;

public class Producto {
    private int codProducto;
    private Blob bdFoto;
    private String rutaFoto;
    private String descripcion;
    private Proveedor proveedor;
    private Categoria categoria;
    private ArrayList<ProductoEstado> estadosProducto;
    private ArrayList<DetalleFactura> detalles;
    
    public Producto() {
        
    }

    public Producto(int codProducto) {
        this.codProducto = codProducto;
    }

    public Producto(int codProducto, String rutaFoto, String descripcion) {
        this.codProducto = codProducto;
        this.rutaFoto = rutaFoto;
        this.descripcion = descripcion;
    }

    public Producto(String rutaFoto, String descripcion, Proveedor proveedor, Categoria categoria) {
        this.rutaFoto = rutaFoto;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
        this.categoria = categoria;
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

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getProveedor() {
        ProveedorDao proveedorDao = new ProveedorDao();
        proveedor = proveedorDao.selectAllTo("cod_proveedor", String.valueOf(proveedor.getCodProveedor())).get(0);

        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        CategoriaDao categoriaDao = new CategoriaDao();
        categoria = categoriaDao.selectAllTo("id_categoria", String.valueOf(categoria.getIdCategoria())).get(0);

        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<ProductoEstado> getEstadosProducto() {
        ProductoEstadoDao productoEstadoDao = new ProductoEstadoDao();
        
        this.estadosProducto = productoEstadoDao.selectAllTo("cod_producto2", String.valueOf(this.getCodProducto()));
        
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
