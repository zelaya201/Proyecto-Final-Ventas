package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Categoria;
import modelos.Usuario;

public class CategoriaDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public CategoriaDao(){
        
    }
    
    public ArrayList<Categoria> selectAll() {
        String sql = "select * from categoria";
        return select(sql);
    }
    
    public ArrayList<Categoria> selectAllTo(String atributo, String condicion) {
        String sql = "select * from categoria where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Categoria> buscar(String dato) {
        String sql = "select * from cliente where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Categoria> selectId(int id) {
        String sql = "select * from categoria where id_categoria=" + id;
        return select(sql);
    } 
    
    public boolean insert(Categoria obj){
        String sql = "insert into categoria(nom_categoria)VALUES(?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Categoria obj) {
        String sql = "update categoria set nom_categoria =? where id_categoria=" + obj.getIdCategoria();
        alterarRegistro(sql, obj);
    }
   
    private ArrayList<Categoria> select(String sql){
        ArrayList<Categoria> lista = new ArrayList();
        Categoria obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Categoria();
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setNombre(rs.getString("nom_categoria"));

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
    
    private boolean alterarRegistro(String sql, Categoria obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getNombre());
 
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
    
    public boolean delete(Categoria obj) {
        String sql = "delete from categoria where id_categoria='" + obj.getIdCategoria()+ "'";
        
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
