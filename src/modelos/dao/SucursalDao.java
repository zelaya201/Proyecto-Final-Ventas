package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Propietario;
import modelos.Sucursal;

public class SucursalDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public SucursalDao(){
        
    }
    
    public ArrayList<Sucursal> selectAll() {
        String sql = "select * from sucursal";
        return select(sql);
    }
    
    public ArrayList<Sucursal> selectAllTo(String atributo, String condicion) {
        String sql = "select * from sucursal where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Sucursal> buscar(String dato) {
        String sql = "select * from sucursal where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Sucursal> selectId(int id) {
        String sql = "select * from sucursal where id_sucursal=" + id;
        return select(sql);
    } 
    
    public boolean insert(Sucursal obj){
        String sql = "insert into sucursal(sucursal_dir,sucursal_tel,sucursal_nom,id_propietario1)VALUES(?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Sucursal obj) {
        String sql = "update sucursal set sucursal_dir =?, sucursal_tel =?, sucursal_nom =?, id_propietario1 =? where idSucursal=" + obj.getIdSucursal();
        alterarRegistro(sql, obj);
    }
    
    private ArrayList<Sucursal> select(String sql){
        ArrayList<Sucursal> lista = new ArrayList();
        Sucursal obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Sucursal();
                obj.setIdSucursal(rs.getInt("idSucursal"));
                obj.setDireccion(rs.getString("sucursal_dir"));
                obj.setTelefono(rs.getString("sucursal_tel"));
                obj.setNombre(rs.getString("sucursal_nom"));
                obj.setPropietario(new Propietario(rs.getInt("id_propietario1")));

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
    
    private boolean alterarRegistro(String sql, Sucursal obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDireccion());
            ps.setString(2, obj.getTelefono());
            ps.setString(3, obj.getNombre());
            ps.setInt(4, obj.getPropietario().getIdPersona());
            
            
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
    
    public boolean delete(Sucursal obj) {
        String sql = "delete from sucursal where id_sucursal='" + obj.getIdSucursal() + "'";
        
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
