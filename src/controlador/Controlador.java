
package controlador;


import ejemplocompleto.utilidades.Encriptacion;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Producto;
import modelos.Usuario;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalUsuario;
import vistas.modulos.NuevoProducto;
import vistas.modulos.VistaProducto;
import vistas.modulos.VistaUsuario;

public class Controlador implements MouseListener, KeyListener{
    DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private String principalOn = "";
    private String modalOn = "";
    
    
    /* CONTROL DE USUARIOS */
    Usuario usuario = new Usuario();
    Usuario usuarioSelected = null;
    UsuarioDao usuarioDao = new UsuarioDao();
    VistaUsuario vistaUsuario;
    ModalUsuario modalUsuario;
    
    /* PRODUCTOS */
    Producto producto = new Producto();
    Producto productoSelected = null;
    VistaProducto vistaProducto;
    NuevoProducto nuevoProducto;
    

    public Controlador(Menu menu) {
        this.menu = menu;
        mostrarModulos("Menu");
    }

    public Controlador(Login login) {
        this.login = login;
    }
    
    private void mostrarModulos(String vista){
        /* - - - - MOSTRAR MODULOS - - - */
        if(vista.equals("Menu")){
            menu.setControlador(this);
            menu.iniciar();
            principalOn = "Menu";
            new CambiaPanel(menu.body, new Dashboard());
        }else if(vista.equals("Usuarios")){
            vistaUsuario = new VistaUsuario();
            vistaUsuario.setControlador(this);
            principalOn = "Usuarios";
            new CambiaPanel(menu.body, vistaUsuario);
            mostrarDatos(vistaUsuario.tablaUsuarios);
        }else if(vista.equals("Productos")){
            vistaProducto = new VistaProducto();
            vistaProducto.setControlador(this);
            principalOn = "Productos";
            new CambiaPanel(menu.body, vistaProducto);
        }
    }
    
    private void mostrarModals(String modal){
        /* - - - - MOSTRAR MODALS - - - - */
        
        /* CONTROL DE USUARIOS */
        if(modal.equals("nuevoUsuario") && principalOn.equals("Usuarios")){
            modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            modalUsuario.iniciar();
            usuarioSelected = null;
        }else if(modal.equals("AgregarProducto")){
            nuevoProducto = new NuevoProducto(new JFrame(), true, vistaProducto);
            nuevoProducto.iniciar();
        }
    }
    
    public void mostrarDatos(JTable tabla){
          
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);

        /* CONTROL DE USUARIOS */
        if(principalOn.equals("Usuarios")){
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            
            ArrayList<Usuario> usuarios = usuarioDao.selectAll();
            int i = 1;
            
            for(Usuario x : usuarios){
                
                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                ImageIcon img_change = new ImageIcon(getClass().getResource("/img/key_22px.png"));
                JLabel lbImg_change = new JLabel(new ImageIcon(img_change.getImage()));
                
                modelo.addRow(new Object[]{i, x.getNickname(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete, lbImg_change});
                i++;
            }
            
            tabla.setModel(modelo);
        }
    }
    
    public void mostrarBusqueda(ArrayList lista, JTable tabla){
        
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);
        
        /* CONTROL DE USUARIOS */
        if(principalOn.equals("Usuarios")){
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            
            int i = 1;
            
            for(Object obj : lista){
                
                Usuario x = (Usuario) obj;
                
                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                ImageIcon img_change = new ImageIcon(getClass().getResource("/img/key_22px.png"));
                JLabel lbImg_change = new JLabel(new ImageIcon(img_change.getImage()));
                
                modelo.addRow(new Object[]{i, x.getNickname(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete, lbImg_change});
                i++;
            }
            
            tabla.setModel(modelo);
        }
          
    }
    
    public void eventosBotones(String btn){
        if(principalOn.equals("Usuarios") && modalOn.equals("modalUsuario")){
            if(btn.equals("Agregar")){
                if(!modalUsuario.jtUser.getText().isEmpty()
                        && !modalUsuario.jtPass.getText().isEmpty()
                        && !modalUsuario.jtPassRepet.getText().isEmpty()
                        && modalUsuario.cbEmpleado.getSelectedIndex() > 0 
                        && modalUsuario.cbRol.getSelectedIndex() > 0){
                    
                    if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){
                        String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                        String dui = "";
                        
                        if(modalUsuario.cbRol.getSelectedItem().toString().equals("Gerente") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                            //String v[] = modalUsuario.cbEmpleado.getSelectedItem().toString().split(" / ");
                            //ArrayList<Empleado> empleados = daoEmpleado.buscar(v[1]);
                            //empleado = empleados.get(0);
                            //dui = empleados.get(0).getDui();
                        }
                        
                    }else{
                        //Contraseñas diferentes
                    }
                    
                }else{
                    //Campos incompletos
                }
            }else{
                //Modificar
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* - - - BOTONES DEL MENU Y MODULOS - - - -*/
        if(e.getSource().equals(menu.btnDashboard)){
            mostrarModulos("Menu");
        }else if(e.getSource().equals(menu.btnUsuarios)){
            mostrarModulos("Usuarios");
//        }else if(e.getSource().equals(menu.btnProductos)){
//            mostrarVista("Productos");
//        }else if(e.getSource().equals(vistaProducto.btnNuevoProducto)){
//            mostrarVista("AgregarProducto");
        }else if(e.getSource().equals(vistaUsuario.btnNuevo)){
            mostrarModals("nuevoUsuario");
        }else if(e.getSource().equals(modalUsuario.btnGuardar)){
            eventosBotones("Agregar");
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        /* - - - - BUSQUEDA - - - - */
        
        /* CONTROL DE USUARIOS */
        if(principalOn.equals("Usuarios")){
            ArrayList<Usuario> lista = usuarioDao.buscar(vistaUsuario.tfBusqueda.getText() + e.getKeyChar());
            if(lista.isEmpty()){
                mostrarDatos(vistaUsuario.tablaUsuarios);
            }else{
                mostrarBusqueda(lista, vistaUsuario.tablaUsuarios);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }

    
    

}
