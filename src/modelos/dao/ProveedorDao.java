package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Proveedor;

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
    
    public ArrayList<Proveedor> buscar(String dato) {
        String sql = "select * from proveedor where cod_proveedor like '" + dato + "%' or  nom_proveedor like '" + dato + "%'";
        return select(sql);
    }
    
    public ArrayList<Proveedor> selectId(int id) {
        String sql = "select * from proveedor where cod_proveedor=" + id;
        return select(sql);
    } 
   
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
    
}
