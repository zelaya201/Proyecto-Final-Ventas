package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Conexion;
import modelos.Empleado;
import modelos.Usuario;

public class UsuarioDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public UsuarioDao(){
        
    }
    
    public ArrayList<Usuario> selectAll() {
        String sql = "select * from usuario";
        return select(sql);
    }
    
    public ArrayList<Usuario> selectAllTo(String atributo, String condicion) {
        String sql = "select * from usuario where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ArrayList<Usuario> buscar(String dato) {
        String sql = "select * from usuario where usuario_nick like '" + dato + "%'";
        return select(sql);
    }
    
    public ArrayList<Usuario> selectId(int id) {
        String sql = "select * from usuario where id_usuario=" + id;
        return select(sql);
    } 
    
    public boolean insert(Usuario obj){
        String sql = "insert into usuario(usuario_nick,usuario_clave,usuario_rol,usuario_estado)VALUES(?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    

    public boolean update(Usuario obj) {
        String sql = "update usuario set usuario_nick = ?, usuario_clave = ?, usuario_rol = ?, usuario_estado = ? where id_usuario = " + obj.getIdUsuario();
        return alterarRegistro(sql, obj);
    }

    private ArrayList<Usuario> select(String sql){
        ArrayList<Usuario> lista = new ArrayList();
        Usuario obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Usuario();
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNickname(rs.getString("usuario_nick"));
                obj.setClave(rs.getString("usuario_clave"));
                obj.setRol(rs.getString("usuario_rol"));
                obj.setEstado(rs.getInt("usuario_estado"));
                
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
    
    private boolean alterarRegistro(String sql, Usuario obj){
        try{
            
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getNickname());
            ps.setString(2, obj.getClave());
            ps.setString(3, obj.getRol());
            ps.setInt(4, obj.getEstado());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error consulta", "Error", 0);
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