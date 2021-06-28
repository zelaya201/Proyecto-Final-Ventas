package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import modelos.Conexion;
import modelos.Empleado;
import modelos.Factura;

public class FacturaDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public FacturaDao(){
        
    }
    
    public ArrayList<Factura> selectAll() {
        String sql = "select * from factura inner join cliente on id_cliente = id_cliente1 inner join empleado on id_empleado = id_empleado2 order by no_factura asc";
        return select(sql);
    }
    
    public ArrayList<Factura> selectAllOrderBy() {
        String sql = "select * from factura inner join cliente on id_cliente = id_cliente1 inner join empleado on id_empleado = id_empleado2 order by fecha_factura desc";
        return select(sql);
    }
    
    public boolean insert(Factura obj){
        String sql = "insert into factura(iva_factura,fecha_factura,total_factura,id_empleado2,id_cliente1) values (?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public int getNextId() {
        int id = 0;
        try {
            con = conectar.getConexion();
            String sql = "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'db_proyecto_ventas' and TABLE_NAME = 'factura'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                id = rs.getInt("AUTO_INCREMENT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return id;
    }
    
    private ArrayList<Factura> select(String sql){
        ArrayList<Factura> lista = new ArrayList();
        Factura obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
         
            while(rs.next()) {
                obj = new Factura();
                obj.setNoFactura(rs.getInt("no_factura"));
                obj.setIva(rs.getDouble("iva_factura"));
                obj.setFecha(rs.getString("fecha_factura"));
                obj.setTotal(rs.getDouble("total_factura"));
                obj.setVendedor(new Empleado(rs.getInt("id_empleado2")));
                obj.setCliente(new Cliente(rs.getInt("id_cliente1")));

                lista.add(obj);
            }
            
        }catch(Exception e) {
            System.out.println(e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, Factura obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getIva());
            ps.setString(2, obj.getFecha());
            ps.setDouble(3, obj.getTotal());
            ps.setInt(4, obj.getVendedor().getIdPersona());
            ps.setInt(5, obj.getCliente().getIdPersona());
 
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
