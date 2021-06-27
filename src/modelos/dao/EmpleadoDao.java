package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Conexion;
import modelos.Empleado;
import modelos.Sucursal;
import modelos.Usuario;

public class EmpleadoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public EmpleadoDao(){
        
    }
    
    public ArrayList<Empleado> selectAll() {
        String sql = "select * from empleado";
        return select(sql);
    }
    
    public ArrayList<Empleado> selectAllTo(String atributo, String condicion) {
        String sql = "select * from empleado where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ArrayList<Empleado> buscar(String dato) {
        String sql = "select * from empleado where dui_empleado like '" + dato + "%' or  nom_empleado like '" + dato + "%' or ape_empleado like '" + dato + "%'";
        return select(sql);
    }
    
    public ArrayList<Empleado> selectId(int id) {
        String sql = "select * from empleado where id_empleado=" + id;
        return select(sql);
    } 
    
    public boolean insert(Empleado obj){
        String sql = "insert into empleado(dui_empleado,nom_empleado,genero_empleado,edad_empleado,email_empleado,tel_empleado,dir_empleado,renta_empleado,estado_empleado,id_empleado1,id_usuario1)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public boolean update(Empleado obj) {
        String sql = "update empleado set dui_empleado =?, nom_empleado =?, ape_empleado =?, genero_empleado =?, edad_empleado =?, email_empleado =?, tel_empleado =?, dir_empleado =?, salario_empleado =?,  renta_empleado =?, estado_empleado =?,  id_usuario1 =? where id_empleado=" + obj.getIdPersona();
      return  alterarRegistro(sql, obj);
    }
    
    public boolean updateEmpleado(Empleado obj) {
        String sql = "update empleado set dui_empleado =?, nom_empleado =?, ape_empleado =?, genero_empleado =?, edad_empleado =?, email_empleado =?, tel_empleado =?, dir_empleado =?, salario_empleado =?, estado_empleado =?, id_usuario1 =? where id_empleado=" + obj.getIdPersona();
        return alterarRegistroUsuario(sql, obj);
    }

    private ArrayList<Empleado> select(String sql){
        ArrayList<Empleado> lista = new ArrayList();
        Empleado obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Empleado();
                obj.setIdPersona(rs.getInt("id_empleado"));
                obj.setDui(rs.getString("dui_empleado"));
                obj.setNombre(rs.getString("nom_empleado"));
                obj.setApellido(rs.getString("ape_empleado"));
             
                obj.setGenero(rs.getString("genero_empleado"));
                obj.setEdad(rs.getInt("edad_empleado"));
                obj.setEmail(rs.getString("email_empleado"));
                obj.setTelefono(rs.getString("tel_empleado"));
                obj.setDireccion(rs.getString("dir_empleado"));
                obj.setSalario(rs.getDouble("salario_empleado"));
            
                obj.setEstado(rs.getInt("estado_empleado"));
            
                if (rs.getInt("id_usuario1") > 0) {
                    obj.setUsuario(new Usuario(rs.getInt("id_usuario1")));
                }
           

                lista.add(obj);
            }
            
        }catch(Exception e) {
            System.out.println("Error consulta");
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, Empleado obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
    
            ps.setString(5, obj.getGenero());
            ps.setInt(6, obj.getEdad());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getTelefono());
            ps.setString(9, obj.getDireccion());
            ps.setDouble(10, obj.getSalario());
        
         
         
        
            
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
    
    private boolean alterarRegistroUsuario(String sql, Empleado obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
        
            ps.setString(5, obj.getGenero());
            ps.setInt(6, obj.getEdad());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getTelefono());
            ps.setString(9, obj.getDireccion());
            ps.setDouble(10, obj.getSalario());
          
       
            
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
    
    public boolean delete(Empleado obj) {
        String sql = "delete from empleado where id_empleado='" + obj.getIdPersona() + "'";
        
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
