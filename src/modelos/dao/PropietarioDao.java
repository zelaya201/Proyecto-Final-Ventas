package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Propietario;
import modelos.Usuario;

public class PropietarioDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public PropietarioDao(){
        
    }
    
    public ArrayList<Propietario> selectAll() {
        String sql = "select * from propietario";
        return select(sql);
    }
    
    public ArrayList<Propietario> selectAllTo(String atributo, String condicion) {
        String sql = "select * from propietario where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ArrayList<Propietario> buscar(String dato) {
        String sql = "select * from cliente where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }
    
    public ArrayList<Propietario> selectId(int id) {
        String sql = "select * from propietario where id_propietario=" + id;
        return select(sql);
    } 
    
    public boolean insert(Propietario obj){
        String sql = "insert into propietario(id_propietario,dui_propietario,nit_propietario,tel_propietario,nom_propietario,ape_propietario,id_usuario2)VALUES(?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Propietario obj) {
        String sql = "update propietario set id_propietario =?, dui_propietario =?, nit_propietario =?, tel_propietario =?, nom_propietario =?, ape_propietario =? where idPropietario=" + obj.getIdPersona();
        alterarRegistro(sql, obj);
    }
   
    private ArrayList<Propietario> select(String sql){
        ArrayList<Propietario> lista = new ArrayList();
        Propietario obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Propietario();
                obj.setIdPersona(rs.getInt("id_propietario"));
                obj.setDui(rs.getString("dui_propietario"));
                obj.setNit(rs.getString("nit_propietario"));
                obj.setTelefono(rs.getString("tel_propietario"));
                obj.setNombre(rs.getString("nom_propietario"));
                obj.setApellido(rs.getString("ape_propietario"));
                obj.setUsuario(new Usuario(rs.getInt("id_usuario2")));

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
    
    private boolean alterarRegistro(String sql, Propietario obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNit());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getNombre());
            ps.setString(5, obj.getApellido());
            ps.setInt(6, obj.getUsuario().getIdUsuario());
 
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
    
    public boolean delete(Propietario obj) {
        String sql = "delete from propietario where id_propietario='" + obj.getIdPersona()+ "'";
        
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
