package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Proveedor;
import modelos.Sucursal;

public class ProveedorDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ProveedorDao(){
        
    }
    
    public ArrayList<Proveedor> selectAll() {
        String sql = "select * from proveedor";
        return select(sql);
    }
    
    public ArrayList<Proveedor> selectAllTo(String atributo, String condicion) {
        String sql = "select * from proveedor where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Proveedor> buscar(String dato) {
        String sql = "select * from proveedor where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Proveedor> selectId(int id) {
        String sql = "select * from proveedor where cod_proveedor=" + id;
        return select(sql);
    } 
    
    public boolean insert(Proveedor obj){
        String sql = "insert into proveedor(nom_proveedor,tel_proveedor,dir_proveedor,estado_proveedor,id_sucursal2)VALUES(?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    /*public void update(Proveedor obj) {
        String sql = "update factura set iva_factura =?, fecha_factura =?, total_factura =? where idProveedor=" + obj.getNoProveedor();
        alterarRegistro(sql, obj);
    }*/
   
    private ArrayList<Proveedor> select(String sql){
        ArrayList<Proveedor> lista = new ArrayList();
        Proveedor obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Proveedor();
                obj.setCodProveedor(rs.getInt("cod_proveedor"));
                obj.setNombre(rs.getString("nom_proveedor"));
                obj.setTelefono(rs.getString("tel_proveedor"));
                obj.setDireccion(rs.getString("dir_proveedor"));
                obj.setEstado(rs.getInt("estado_proveedor"));
                obj.setSucursal(new Sucursal(rs.getInt("id_sucursal2")));

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
    
    private boolean alterarRegistro(String sql, Proveedor obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getTelefono());
            ps.setString(3, obj.getDireccion());
            ps.setInt(4, obj.getEstado());
            ps.setInt(5, obj.getSucursal().getIdSucursal());
 
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
    
    public boolean delete(Proveedor obj) {
        String sql = "delete from proveedor where cod_proveedor='" + obj.getCodProveedor()+ "'";
        
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
