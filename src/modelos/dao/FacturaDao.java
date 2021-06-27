package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Cliente;
import modelos.Conexion;
import modelos.Empleado;
import modelos.Factura;

public class FacturaDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public FacturaDao(){
        
    }
    
    public ArrayList<Factura> selectAll() {
        String sql = "select * from factura inner join cliente on id_cliente = id_cliente1 inner join empleado on id_empleado = id_empleado2 order by no_factura asc";
        return select(sql);
    }
    
    public ArrayList<Factura> selectAllOrderBy() {
        String sql = "select * from factura inner join cliente on id_cliente = id_cliente1 inner join empleado on id_empleado = id_empleado2 order by fecha_factura desc";
        return select(sql);
    }
 
        
    public ArrayList<Factura> buscar(String dato) {
        String sql = "select * from factura inner join cliente on id_cliente = id_cliente1 inner join empleado on id_empleado = id_empleado2 where cliente.nom_cliente like '" + dato + "%' or  empleado.nom_empleado like '" + dato + "%' or no_factura like '" + dato + "%'or fecha_factura like '" + dato + "%'";
        return select(sql);
    }
    
    public boolean insert(Factura obj){
        String sql = "insert into factura(iva_factura,fecha_factura,total_factura,id_empleado2,id_cliente1)VALUES(?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    private ArrayList<Factura> select(String sql){
        ArrayList<Factura> lista = new ArrayList();
        Factura obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
         
            while(rs.next()) {
                obj = new Factura();
                obj.setNoFactura(rs.getInt("no_factura"));
                obj.setIva(rs.getDouble("iva_factura"));
                obj.setFecha(rs.getDate("fecha_factura"));
                obj.setTotal(rs.getDouble("total_factura"));
                obj.setVendedor(new Empleado(rs.getInt("id_empleado2"), rs.getString("empleado.nom_empleado"), rs.getString("empleado.ape_empleado")));
                obj.setCliente(new Cliente(rs.getInt("id_cliente1"),rs.getString("cliente.nom_cliente"), rs.getString("cliente.ape_cliente")));

                lista.add(obj);
            }
            
        }catch(Exception e) {
            System.out.println(e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, Factura obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getIva());
            ps.setDate(2, new java.sql.Date(((java.util.Date) obj.getFecha()).getTime()));
            ps.setDouble(3, obj.getTotal());
            ps.setInt(4, obj.getVendedor().getIdPersona());
            ps.setInt(5, obj.getCliente().getIdPersona());
 
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
    
}
