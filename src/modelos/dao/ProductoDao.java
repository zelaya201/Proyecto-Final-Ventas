package modelos.dao;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    
    public ArrayList<Producto> selectAllTo(String atributo, String condicion) {
        String sql = "select * from producto where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ArrayList<Producto> buscar(String dato) {
        String sql = "select * from producto where cod_producto like '" + dato + "%' or  descripcion_producto like '" + dato + "%'";
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
    
    public boolean updateBlob(Producto obj) {
        String sql = "update producto set foto_producto =?, descripcion_producto =?, cod_proveedor1 =?, id_categoria1 =? where cod_producto=" + obj.getCodProducto();
        return alterarRegistroBlob(sql, obj);
    }
    
    public int getNextId() {
        int id = 0;
        try {
            con = conectar.getConexion();
            String sql = "select `AUTO_INCREMENT` from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA = 'db_proyecto_ventas' and TABLE_NAME = 'producto'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                id = rs.getInt("AUTO_INCREMENT");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return id;
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
            BufferedImage img;
            FileInputStream fis = null;
            
            File file = new File(obj.getRutaFoto());
            img = ImageIO.read(file);
            
            int alto = img.getHeight();
            int ancho = img.getWidth();
            
            img = resizeImg(img, 100, (100*alto)/ancho);
            ImageIO.write(img, "jpg", file);
            
            fis = new FileInputStream(file);
            
            ps.setBlob(1, fis, (int)file.length());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getProveedor().getCodProveedor());
            ps.setInt(4, obj.getCategoria().getIdCategoria());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {      
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
    
    private boolean alterarRegistroBlob(String sql, Producto obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            InputStream in = obj.getBdFoto().getBinaryStream();
            
            ps.setBlob(1, in);
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getProveedor().getCodProveedor());
            ps.setInt(4, obj.getCategoria().getIdCategoria());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {      
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
    
    private static BufferedImage resizeImg(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}
