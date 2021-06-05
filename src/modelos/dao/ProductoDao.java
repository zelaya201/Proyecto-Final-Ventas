package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Categoria;
import modelos.Conexion;
import modelos.Producto;
import modelos.Proveedor;
import modelos.Sucursal;

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
    
    public ArrayList<Producto> selectAllTo(String atributo, String condicion) {
        String sql = "select * from producto where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    /*public ArrayList<Producto> buscar(String dato) {
        String sql = "select * from producto where carnet like '" + dato + "%' or  nombre like '" + dato + "%' or apellido like '" + dato + "%'";
        return select(sql);
    }*/
    
    public ArrayList<Producto> selectId(int id) {
        String sql = "select * from producto where cod_producto=" + id;
        return select(sql);
    } 
    
    public boolean insert(Producto obj){
        String sql = "insert into producto(foto_producto,descripcion_producto,id_sucursal3,cod_proveedor1,id_categoria1)VALUES(?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public void update(Producto obj) {
        String sql = "update producto set foto_producto =?, descripcion_producto =?, id_categoria1 =? where idProducto=" + obj.getCodProducto();
        alterarRegistro(sql, obj);
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
                obj.setSucursal(new Sucursal(rs.getInt("id_sucursal3")));
                obj.setProveedor(new Proveedor(rs.getInt("cod_proveedor1")));
                obj.setCategoria(new Categoria(rs.getInt("id_categoria1")));

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
    
    private boolean alterarRegistro(String sql, Producto obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setBinaryStream(1, obj.getFileFoto());
            ps.setString(2, obj.getDescripcion());
            
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
