package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
        String sql = "select * from factura";
        return select(sql);
    }
    
    public ArrayList<Factura> selectAllOrderBy() {
        String sql = "select * from factura order by fecha_factura desc";
        return select(sql);
    }
    
    public ArrayList<Factura> selectAllTo(String atributo, String condicion) {
        String sql = "select * from factura where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Factura> buscar(String dato) {
        String sql = "select * from cliente where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Factura> selectId(int id) {
        String sql = "select * from factura where no_factura=" + id;
        return select(sql);
    } 
    
    public boolean insert(Factura obj){
        String sql = "insert into factura(iva_factura,fecha_factura,total_factura,id_empleado2,id_cliente1)VALUES(?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    /*public void update(Factura obj) {
        String sql = "update factura set iva_factura =?, fecha_factura =?, total_factura =? where idFactura=" + obj.getNoFactura();
        alterarRegistro(sql, obj);
    }*/
   
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
                obj.setFecha(rs.getDate("fecha_factura"));
                obj.setTotal(rs.getDouble("total_factura"));
                obj.setVendedor(new Empleado(rs.getInt("id_empleado2")));
                obj.setCliente(new Cliente(rs.getInt("id_cliente1")));

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
    
    private boolean alterarRegistro(String sql, Factura obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getIva());
            ps.setDate(2, new java.sql.Date(((java.util.Date) obj.getFecha()).getTime()));
            ps.setDouble(3, obj.getTotal());
            ps.setInt(4, obj.getVendedor().getIdPersona());
            ps.setInt(5, obj.getCliente().getIdPersona());
 
            ps.execute();
            
            return true;
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
    
    public boolean delete(Factura obj) {
        String sql = "delete from factura where no_factura='" + obj.getNoFactura()+ "'";
        
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
    }
}
