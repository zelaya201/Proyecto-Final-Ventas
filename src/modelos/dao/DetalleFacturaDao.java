package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Conexion;
import modelos.DetalleFactura;
import modelos.Factura;
import modelos.Producto;
import modelos.Usuario;

public class DetalleFacturaDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public DetalleFacturaDao(){
        
    }
    
    public ArrayList<DetalleFactura> selectAll() {
        String sql = "select * from detalle_factura";
        return select(sql);
    }
    
    public ArrayList<DetalleFactura> selectAllTo(String atributo, String condicion) {
        String sql = "select * from detalle_factura where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public boolean insert(DetalleFactura obj){
        String sql = "insert into detalle_factura(cant_producto,subtotal_factura,cod_producto1,no_factura1)VALUES(?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    /*public void update(DetalleFactura obj) {
        String sql = "update detalle_factura set iva_detalle_factura =?, fecha_detalle_factura =?, total_detalle_factura =? where idDetalleFactura=" + obj.getNoDetalleFactura();
        alterarRegistro(sql, obj);
    }*/
   
    private ArrayList<DetalleFactura> select(String sql){
        ArrayList<DetalleFactura> lista = new ArrayList();
        DetalleFactura obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new DetalleFactura();
                obj.setCantidadProducto(rs.getInt("cant_producto"));
                obj.setSubtotal(rs.getDouble("subtotal_factura"));
                obj.setProducto(new Producto(rs.getInt("cod_producto1")));
                obj.setFactura(new Factura(rs.getInt("no_factura1")));

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
    
    private boolean alterarRegistro(String sql, DetalleFactura obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, obj.getCantidadProducto());
            ps.setDouble(2, obj.getSubtotal());
            ps.setInt(3, obj.getProducto().getCodProducto());
            ps.setInt(4, obj.getFactura().getNoFactura());
 
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
    
    /*public boolean delete(DetalleFactura obj) {
        String sql = "delete from detalle_factura where id_detalle='" + obj.getId()+ "'";
        
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
                conectar.closeConexion(con);
            } catch (Exception ex) {
            }
        }

        return false;
    }*/
}
