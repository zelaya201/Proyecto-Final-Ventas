package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Cliente;
import modelos.Sucursal;
import modelos.Usuario;

public class ClienteDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ClienteDao(){
        
    }
    
    public ArrayList<Cliente> selectAll() {
        String sql = "select * from cliente";
        return select(sql);
    }
    
    public ArrayList<Cliente> selectAllTo(String atributo, String condicion) {
        String sql = "select * from cliente where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Cliente> buscar(String dato) {
        String sql = "select * from cliente where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Cliente> selectId(int id) {
        String sql = "select * from cliente where id_cliente=" + id;
        return select(sql);
    } 
    
    public boolean insert(Cliente obj){
        String sql = "insert into cliente(dui_cliente,nom_cliente,ape_cliente,dir_cliente,tel_cliente,email_cliente)VALUES(?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Cliente obj) {
        String sql = "update cliente set dui_cliente =?, nom_cliente =?, ape_cliente =?, dir_cliente =?, tel_cliente =?, email_cliente =? where id_cliente=" + obj.getIdPersona();
        alterarRegistro(sql, obj);
    }
   
    private ArrayList<Cliente> select(String sql){
        ArrayList<Cliente> lista = new ArrayList();
        Cliente obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Cliente();
                obj.setIdPersona(rs.getInt("id_cliente"));
                obj.setDui(rs.getString("dui_cliente"));
                obj.setNombre(rs.getString("nom_cliente"));
                obj.setApellido(rs.getString("ape_cliente"));
                obj.setDireccion(rs.getString("dir_cliente"));
                obj.setTelefono(rs.getString("tel_cliente"));
                obj.setEmail(rs.getString("email_cliente"));

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
    
    private boolean alterarRegistro(String sql, Cliente obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
            ps.setString(4, obj.getDireccion());
            ps.setString(5, obj.getTelefono());
            ps.setString(6, obj.getEmail());
 
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
    
    public boolean delete(Cliente obj) {
        String sql = "delete from cliente where id_cliente='" + obj.getIdPersona() + "'";
        
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
