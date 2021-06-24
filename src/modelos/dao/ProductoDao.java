package modelos.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Categoria;
import modelos.Conexion;
import modelos.Producto;
import modelos.Proveedor;

public class ProductoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public ProductoDao(){
        
    }
    
    public ArrayList<Producto> selectAll() {
        String sql = "select * from producto";
        return select(sql);
    }
    
    public ArrayList<Producto> selectAllOrderBy() {
        String sql = "select * from producto order by cod_producto desc";
        return select(sql);
    }
    
    public ArrayList<Producto> selectAllTo(String atributo, String condicion) {
        String sql = "select * from producto where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ArrayList<Producto> buscar(String dato) {
        String sql = "select * from producto where  cod_producto like '" + dato + "%' or  descripcion_producto like '" + dato + "%'";
        return select(sql);
    }
    
    public ArrayList<Producto> selectId(int id) {
        String sql = "select * from producto where cod_producto=" + id;
        return select(sql);
    } 
    
    public boolean insert(Producto obj){
        String sql = "insert into producto(foto_producto,descripcion_producto,cod_proveedor1,id_categoria1)values(?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public boolean update(Producto obj) {
        String sql = "update producto set foto_producto =?, descripcion_producto =?, cod_proveedor1 =?, id_categoria1 =? where cod_producto=" + obj.getCodProducto();
        return alterarRegistro(sql, obj);
    }

    private ArrayList<Producto> select(String sql){
        ArrayList<Producto> lista = new ArrayList();
        Producto obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Producto();
                obj.setCodProducto(rs.getInt("cod_producto"));
                obj.setBdFoto(rs.getBlob("foto_producto"));
                obj.setDescripcion(rs.getString("descripcion_producto"));
                obj.setProveedor(new Proveedor(rs.getInt("cod_proveedor1")));
                obj.setCategoria(new Categoria(rs.getInt("id_categoria1")));

                lista.add(obj);
            }
            
        }catch(Exception e) {
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, Producto obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            FileInputStream fis = null;
            
            File file = new File(obj.getRutaFoto());
            fis = new FileInputStream(file);
            
            ps.setBlob(1, fis, (int)file.length());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getProveedor().getCodProveedor());
            ps.setInt(4, obj.getCategoria().getIdCategoria());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {
//            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
//            DesktopNotify.showDesktopMessage("Error en SQL", "Un error ha ocurrido en la consulta sql.", DesktopNotify.WARNING, 10000);       
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
    
    public boolean delete(Producto obj) {
        String sql = "delete from producto where cod_producto='" + obj.getCodProducto() + "'";
        
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