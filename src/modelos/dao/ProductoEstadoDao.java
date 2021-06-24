package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Conexion;
import modelos.Producto;
import modelos.ProductoEstado;

public class ProductoEstadoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ProductoEstadoDao(){
        
    }
    
    public ArrayList<ProductoEstado> selectAll() {
        String sql = "select * from producto_estado";
        return select(sql);
    }
    
    public ArrayList<ProductoEstado> selectAllTo(String atributo, String condicion) {
        String sql = "select * from producto_estado where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<ProductoEstado> buscar(String dato) {
        String sql = "select * from producto_estado where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/

    public boolean insert(ProductoEstado obj){
        String sql = "insert into producto_estado(precio_compra_producto, precio_venta_producto, stock_producto, estado_producto, ganancia_producto, cod_producto2) values (?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public boolean update(ProductoEstado obj) {
        String sql = "update producto_estado set precio_compra_producto =?, precio_venta_producto =?, stock_producto =?, estado_producto =?, ganancia_producto =?, cod_producto2 =? where cod_producto2=" + obj.getProducto().getCodProducto();
        return alterarRegistro(sql, obj);
    }

    private ArrayList<ProductoEstado> select(String sql){
        ArrayList<ProductoEstado> lista = new ArrayList();
        ProductoEstado obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new ProductoEstado();
                obj.setPrecioCompra(rs.getDouble("precio_compra_producto"));
                obj.setPrecioVenta(rs.getDouble("precio_venta_producto"));
                obj.setStock(rs.getInt("stock_producto"));
                obj.setEstado(rs.getInt("estado_producto"));
                obj.setGanancia(rs.getDouble("ganancia_producto"));
                obj.setProducto(new Producto(rs.getInt("cod_producto2")));

                lista.add(obj);
            }
            
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, ProductoEstado obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getPrecioCompra());
            ps.setDouble(2, obj.getPrecioVenta());
            ps.setInt(3, obj.getStock());
            ps.setInt(4, obj.getEstado());  
            ps.setDouble(5, obj.getGanancia());
            ps.setInt(6, obj.getProducto().getCodProducto());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
}
