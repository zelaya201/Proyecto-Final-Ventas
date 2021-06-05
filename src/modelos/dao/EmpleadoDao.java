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
    
    /*public ArrayList<Empleado> buscar(String dato) {
        String sql = "select * from empleado where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Empleado> selectId(int id) {
        String sql = "select * from empleado where id_empleado=" + id;
        return select(sql);
    } 
    
    public boolean insert(Empleado obj){
        String sql = "insert into empleado(dui_empleado,nom_empleado,ape_empleado,cargo_empleado,genero_empleado,edad_empleado,email_empleado,tel_empleado,dir_empleado,salario_empleado,afp_empleado,isss_empleado,renta_empleado,estado_empleado,id_empleado1,id_sucursal1)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Empleado obj) {
        String sql = "update empleado set dui_empleado =?, nom_empleado =?, ape_empleado =?, cargo_empleado =?, genero_empleado =?, edad_empleado =?, email_empleado =?, tel_empleado =?, dir_empleado =?, salario_empleado =?, afp_empleado =?, isss_empleado =?, renta_empleado =?, estado_empleado =?, id_empleado1 =?, id_sucursal1 =? where id_empleado=" + obj.getIdPersona();
        alterarRegistro(sql, obj);
    }
    
    public void updateUsuario(Empleado obj) {
        String sql = "update empleado set dui_empleado =?, nom_empleado =?, ape_empleado =?, cargo_empleado =?, genero_empleado =?, edad_empleado =?, email_empleado =?, tel_empleado =?, dir_empleado =?, salario_empleado =?, afp_empleado =?, isss_empleado =?, renta_empleado =?, estado_empleado =?, id_empleado1 =?, id_usuario1 =?, id_sucursal1 =? where id_empleado=" + obj.getIdPersona();
        alterarRegistroUsuario(sql, obj);
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
                obj.setCargo(rs.getString("cargo_empleado"));
                obj.setGenero(rs.getString("genero_empleado"));
                obj.setEdad(rs.getInt("edad_empleado"));
                obj.setEmail(rs.getString("email_empleado"));
                obj.setTelefono(rs.getString("tel_empleado"));
                obj.setDireccion(rs.getString("dir_empleado"));
                obj.setSalario(rs.getDouble("salario_empleado"));
                obj.setAfp(rs.getDouble("afp_empleado"));
                obj.setIsss(rs.getDouble("isss_empleado"));
                obj.setRenta(rs.getDouble("renta_empleado"));
                obj.setEstado(rs.getInt("estado_empleado"));
                obj.setResponsable(new Empleado(rs.getInt("id_empleado1")));
                if (rs.getInt("id_usuario1") > 0) {
                    obj.setUsuario(new Usuario(rs.getInt("id_usuario1")));
                }
                obj.setSucursal(new Sucursal(rs.getInt("id_sucursal1")));

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
    
    private boolean alterarRegistro(String sql, Empleado obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
            ps.setString(4, obj.getCargo());
            ps.setString(5, obj.getGenero());
            ps.setInt(6, obj.getEdad());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getTelefono());
            ps.setString(9, obj.getDireccion());
            ps.setDouble(10, obj.getSalario());
            ps.setDouble(11, obj.getAfp());
            ps.setDouble(12, obj.getIsss());
            ps.setDouble(13, obj.getRenta());
            ps.setInt(14, obj.getEstado());
            ps.setInt(15, obj.getResponsable().getIdPersona());
            ps.setInt(17, obj.getSucursal().getIdSucursal());
            
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
            ps.setString(4, obj.getCargo());
            ps.setString(5, obj.getGenero());
            ps.setInt(6, obj.getEdad());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getTelefono());
            ps.setString(9, obj.getDireccion());
            ps.setDouble(10, obj.getSalario());
            ps.setDouble(11, obj.getAfp());
            ps.setDouble(12, obj.getIsss());
            ps.setDouble(13, obj.getRenta());
            ps.setInt(14, obj.getEstado());
            ps.setInt(15, obj.getResponsable().getIdPersona());
            ps.setInt(16, obj.getUsuario().getIdUsuario());
            ps.setInt(17, obj.getSucursal().getIdSucursal());
            
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
