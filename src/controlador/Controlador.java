
package controlador;


import ejemplocompleto.utilidades.Encriptacion;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Empleado;
import modelos.Propietario;
import modelos.Usuario;
import modelos.dao.EmpleadoDao;
import modelos.dao.PropietarioDao;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaUsuario;

public class Controlador extends MouseAdapter implements MouseListener, KeyListener, ItemListener{
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
    
    /* EMPLEADOS */
    Empleado empleado = new Empleado();
    Empleado empleadoSelected = null;
    EmpleadoDao empleadoDao = new EmpleadoDao();    

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
        }else if(modal.equals("editarUsuario") && principalOn.equals("Usuarios")){
            modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            
            modalUsuario.form.remove(modalUsuario.jtPass);
            modalUsuario.form.remove(modalUsuario.jtPassRepet);
            modalUsuario.form.remove(modalUsuario.iconPass);
            
            modalUsuario.header.setText("Editar Usuario");
            modalUsuario.cbRol.setSelectedItem(usuarioSelected.getRol());
            
            modalUsuario.cbEmpleado.addItem("Adonay / 01234567-8");
            modalUsuario.cbEmpleado.setSelectedItem("Adonay / 01234567-8");
                        
            modalUsuario.cbEmpleado.setEnabled(false);
            modalUsuario.cbRol.setEnabled(false);
            modalUsuario.jtUser.setText(usuarioSelected.getNickname());
            
            modalUsuario.setSize(482, 350);
            //llenarComboBox();
            modalUsuario.iniciar();
        }else if(modal.equals("eliminarUsuario") && principalOn.equals("Usuarios")){
            
        }else if(modal.equals("changePassUsuario") && principalOn.equals("Usuarios")){
            modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            
            modalUsuario.form.remove(modalUsuario.cbEmpleado);
            modalUsuario.form.remove(modalUsuario.iconEmpleado);
            modalUsuario.form.remove(modalUsuario.cbRol);
            modalUsuario.form.remove(modalUsuario.iconRol);
            
            modalUsuario.header.setText("Cambiar contraseña");
            
            modalUsuario.jtUser.setEnabled(false);
            modalUsuario.jtUser.setText(usuarioSelected.getNickname());
            
            modalUsuario.setSize(482, 285); //Width - Height
            modalUsuario.iniciar();
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
                        && modalUsuario.cbEmpleado.getSelectedIndex() > 0 
                        && modalUsuario.cbRol.getSelectedIndex() > 0){
                    
                    if(usuarioSelected == null && !modalUsuario.jtPass.getText().isEmpty() && !modalUsuario.jtPassRepet.getText().isEmpty()){

                        if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){

                            String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                            String dui = "";

                            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Gerente") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                                String v[] = modalUsuario.cbEmpleado.getSelectedItem().toString().split(" / ");

                                ArrayList<Empleado> empleados = empleadoDao.buscar(v[1]);
                                empleado = empleados.get(0);
                                dui = empleados.get(0).getDui();
                            }

                            ArrayList<Usuario> existeUser = usuarioDao.selectAllTo("usuario", modalUsuario.jtUser.getText());
                            ArrayList<Usuario> existeClave = usuarioDao.selectAllTo("clave", clave);
                            ArrayList<Usuario> existeReferencia = usuarioDao.selectAllTo("referencia", dui);

                            if(existeUser.isEmpty() && existeClave.isEmpty() && existeReferencia.isEmpty()){
  
                                 Usuario usuario = new Usuario(modalUsuario.jtUser.getText(), clave, modalUsuario.cbRol.getSelectedItem().toString(), 1);
                                 
                                 if(usuarioDao.insert(usuario)){
                                     //Mensaje Guardado
                                 }
                                 
                                modalOn = "";
                                modalUsuario.dispose();
                            }
                        }else{
                            //Contraseñas diferentes
                        }
                        
                    }else{
                        //Modificar
                        usuarioSelected.setNickname(modalUsuario.jtUser.getText());
                        
                        if(usuarioDao.update(usuarioSelected)){
                            //Mensaje de modificado
                        }else{
                            JOptionPane.showMessageDialog(null, "No modificado" + usuarioSelected.getIdUsuario(), "Error", 0);
                        }                      
                    }
                    
                    
                    
                }else if(usuarioSelected != null && !modalUsuario.jtPass.getText().isEmpty() && !modalUsuario.jtPassRepet.getText().isEmpty()){
                    if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){
                        
                        String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                        String dui = "";
                        
                        usuarioSelected.setClave(clave);
                        
                        if(usuarioDao.update(usuarioSelected)){
                            //Mensaje de modificado
                        }else{
                            JOptionPane.showMessageDialog(null, "Contraseña no cambiada xd" + usuarioSelected.getIdUsuario(), "Error", 0);
                        } 

                    }else{
                        //Contraseñas diferentes
                    }
                }else{
                    //Campos incompletos
                    System.out.println("Campos incompletos");
                }
                
                mostrarDatos(vistaUsuario.tablaUsuarios);
                usuarioSelected = null;
                modalUsuario.dispose();
                
            }
            
        }
    }
    
    public void llenarComboBox(){
        
//        if(modalOn.equals("modalUsuario")){
//            modalUsuario.cbEmpleado.removeAllItems();
//            modalUsuario.cbEmpleado.addItem("Seleccione");
//            String dato = "";
//            
//            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Gerente") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
//                
//                ArrayList<Empleado> empleados = empleadoDao.selectAll();
//                
//                for(Empleado x : empleados){
//                    modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
//                    if(x.getDui().equals(usuarioSeleccionado.getReferencia())){
//                        dato = x.getNombre() + " / " + x.getDui();
//                    }
//                }
//                
//                vistaUsuario.cbEmpleados.setSelectedItem(dato);
//                
//            }else if(vistaUsuario.cbRol.getSelectedItem().toString().equals("Profesor")){
//                ArrayList<Profesor> profesores = daoProfesor.selectAll();
//                
//                for(Profesor x : profesores){
//                    vistaUsuario.cbEmpleados.addItem(x.getNombre() + " / " + x.getDui());
//                    if(x.getDui().equals(usuarioSeleccionado.getReferencia())){
//                        dato = x.getNombre() + " / " + x.getDui();
//                    }
//                    
//                    vistaUsuario.cbEmpleados.setSelectedItem(dato);
//                }
//            }
//        }
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try{
                /* - - - BOTONES DEL MENU Y MODULOS - - - -*/
            if(e.getSource().equals(menu.btnDashboard)){
                mostrarModulos("Menu");
            }else if(e.getSource().equals(menu.btnUsuarios)){
                mostrarModulos("Usuarios");
            }else if(e.getSource().equals(vistaUsuario.btnNuevo)){
                mostrarModals("nuevoUsuario");
            }else if(e.getSource().equals(modalUsuario.btnGuardar)){
                eventosBotones("Agregar");
            }
        }catch(Exception ex){
            
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
    public void itemStateChanged(ItemEvent e) {
        
        if(modalOn.equals("modalUsuario")){
            modalUsuario.cbEmpleado.setEnabled(true);
            modalUsuario.cbEmpleado.removeAllItems();
            modalUsuario.cbEmpleado.addItem("Asignar empleado");
            
            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Gerente") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                
                ArrayList<Empleado> empleados = empleadoDao.selectAll();

                for(Empleado x : empleados){
                    modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
                }
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(principalOn.equals("Usuarios") && e.getSource() == vistaUsuario.tablaUsuarios){

            int columna = vistaUsuario.tablaUsuarios.getSelectedColumn();
 
            if(columna == 4){
                int fila = vistaUsuario.tablaUsuarios.getSelectedRow();
                String nick = vistaUsuario.tablaUsuarios.getValueAt(fila, 1).toString();
                ArrayList<Usuario> lista = usuarioDao.selectAllTo("usuario_nick", nick);
                usuarioSelected = lista.get(0);
                mostrarModals("editarUsuario");
            }else if(columna == 5){
                int fila = vistaUsuario.tablaUsuarios.getSelectedRow();
                String nick = vistaUsuario.tablaUsuarios.getValueAt(fila, 1).toString();
                ArrayList<Usuario> lista = usuarioDao.selectAllTo("usuario_nick", nick);
                usuarioSelected = lista.get(0);
                mostrarModals("eliminarUsuario");
            }else if(columna == 6){
                int fila = vistaUsuario.tablaUsuarios.getSelectedRow();
                String nick = vistaUsuario.tablaUsuarios.getValueAt(fila, 1).toString();
                ArrayList<Usuario> lista = usuarioDao.selectAllTo("usuario_nick", nick);
                usuarioSelected = lista.get(0);
                mostrarModals("changePassUsuario");
            }
                   
        }
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
