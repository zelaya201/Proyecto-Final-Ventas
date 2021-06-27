
package controlador;

import utilidades.JFreeCharts;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Empleado;
import modelos.Factura;
import modelos.Producto;
import modelos.Usuario;
import modelos.dao.EmpleadoDao;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalEmpleado;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaUsuario;
import vistas.modulos.VistaEmpleado;


public class Controlador extends MouseAdapter implements MouseListener, KeyListener, ItemListener{
    DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private String principalOn = "";
    private String modalOn = "";
    
       /* DASHBOARD */
    JFreeCharts barChart = new JFreeCharts();
    Dashboard dashboard;
    
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
    VistaEmpleado vistaEmpleado; 
    ModalEmpleado modalEmpleado;
    private String nickname;

    public Controlador(Menu menu) {
        this.menu = menu;
        mostrarModulos("Menu");
    }

    public Controlador(Login login) {
        this.login = login;
    }
    
    private void mostrarModulos(String vista){
        /* - - - - MOSTRAR MODULOS - - - - */
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
        }else if(vista.equals("Empleados")){
            vistaEmpleado= new VistaEmpleado();
            vistaEmpleado.setControlador(this);
            principalOn = "Empleados";
            new CambiaPanel(menu.body, vistaEmpleado);
            mostrarDatos(vistaEmpleado.tablaEmpleados);
        }
    }
    
    private void mostrarModals(String modal){
        /* - - - - MOSTRAR MODALS - - - - */
        modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
        /* CONTROL DE USUARIOS */
        if(modal.equals("nuevoUsuario") && principalOn.equals("Usuarios")){
            usuarioSelected = null;
            modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            modalUsuario.iniciar();
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
            
            modalUsuario.setSize(482, 346); //Width - Height
            //llenarComboBox();
            modalUsuario.iniciar();
        }else if(modal.equals("eliminarUsuario") && principalOn.equals("Usuarios")){
            if(usuarioSelected != null){
                usuarioSelected.setEstado(0);
                if(usuarioDao.update(usuarioSelected)){
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Usuario eliminado", "El usuario ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                    mostrarDatos(vistaUsuario.tablaUsuarios);
                }
                
                 usuarioSelected = null;
                
            }
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
        
        
          /* CONTROL DE EMPLEADOS */
        else if(modal.equals("nuevoEmpleado") && principalOn.equals("Empleados")){
            empleadoSelected = null;
            modalEmpleado = new ModalEmpleado(new JFrame(), true, vistaEmpleado);
            modalEmpleado.setControlador(this);
            modalOn = "modalEmpleado";
            modalEmpleado.iniciar();
        }else if(modal.equals("editarEmpleado") && principalOn.equals("Empleados")){
           modalEmpleado = new ModalEmpleado(new JFrame(), true, vistaEmpleado);
            modalEmpleado.setControlador(this);
            modalOn = "modalEmpleado";
            
              modalEmpleado.jPanel1.remove(modalEmpleado.jtDui);
                modalEmpleado.jPanel1.remove(modalEmpleado.jtNombre);
                  modalEmpleado.jPanel1.remove(modalEmpleado.jtApellido);
            modalEmpleado.jPanel1.remove(modalEmpleado.jtTelefono);
            modalEmpleado.jPanel1.remove(modalEmpleado.jtEdad);
             // modalEmpleado.jPanel1.remove(modalEmpleado.jtEstado);
             
               modalEmpleado.jPanel1.remove(modalEmpleado.jtEmail);
                modalEmpleado.jPanel1.remove(modalEmpleado.jtDireccion);
                  modalEmpleado.jPanel1.remove(modalEmpleado.jtSalario);
                   
                      //modalEmpleado.jPanel1.remove(modalEmpleado.cbUsuario);
                    
           // modalEmpleado.form.remove(modalEmpleado.jtPassRepet);
           // modalEmpleado.form.remove(modalEmpleado.iconPass);
            
           modalEmpleado.header.setText("Editar Empleado");
      
                 
            modalEmpleado.jtDui.setText(empleadoSelected.getDui()); 
            
            modalEmpleado.setSize(482, 346); //Width - Height
          // llenarComboBox();
            modalEmpleado.iniciar();
            
        }else if(modal.equals("eliminarEmpleado") && principalOn.equals("Empleados")){
            if(empleadoSelected != null){
                empleadoSelected.setEstado(0);
                if(empleadoDao.update(empleadoSelected)){
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Empleado eliminado", "El Empleado ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                    mostrarDatos(vistaEmpleado.tablaEmpleados);
                }
                 
                empleadoSelected = null;
                
            }
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
                
                if(x.getEstado() > 0){
                    modelo.addRow(new Object[]{i, x.getNickname(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete, lbImg_change});
                    i++;
                }
               
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }
            
            tabla.setModel(modelo);
        }
          /* CONTROL DE Empleados */
        else if(principalOn.equals("Empleados")){
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(6).setCellRenderer(diseño);
             tabla.getColumnModel().getColumn(7).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(8).setCellRenderer(diseño);
             tabla.getColumnModel().getColumn(9).setCellRenderer(diseño);
       
            
            ArrayList<Empleado> empleados = empleadoDao.selectAll();
            int i = 1;
            
            for(Empleado x : empleados){

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

              
                
                if(x.getEstado() > 0){
                    modelo.addRow(new Object[]{i,x.getDui(),x.getNombre(), x.getApellido(), x.getGenero(),x.getEdad(), x.getEmail(), x.getTelefono(), x.getDireccion(), x.getSalario() ,lbImg_edit, lbImg_delete});
                    i++;
                }
               
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
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
                
                if(x.getEstado() > 0){
                    modelo.addRow(new Object[]{i, x.getNickname(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete, lbImg_change});
                    i++;
                }

            }
            
            if(modelo.getRowCount() < 1){
                mostrarDatos(vistaUsuario.tablaUsuarios);
            }else{
                tabla.setModel(modelo);
            }
            
            
        }
        
          /* CONTROL DE EMPLEADOS */
        if(principalOn.equals("Empleados")){
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(6).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(7).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(8).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(9).setCellRenderer(diseño);
          
            
            int i = 1;
            
            for(Object obj : lista){
                
                Empleado x = (Empleado) obj;
                
                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

              
                
                if(x.getEstado() > 0){
                     modelo.addRow(new Object[]{i,x.getDui(),x.getNombre(), x.getApellido(), x.getGenero(),x.getEdad(), x.getEmail(), x.getTelefono(), x.getDireccion(), x.getSalario() ,lbImg_edit, lbImg_delete});
                    i++;
                }

            }
            
            if(modelo.getRowCount() < 1){
                mostrarDatos(vistaEmpleado.tablaEmpleados);
            }else{
                tabla.setModel(modelo);
            }
            
            
        }
          
    }
    
    public void eventosBotones(String btn){
        
        /* CONTROL DE USUARIOS */
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

                            ArrayList<Usuario> existeUser = usuarioDao.selectAllTo("usuario_nick", modalUsuario.jtUser.getText());
                            //ArrayList<Usuario> existeReferencia = usuarioDao.selectAllTo("referencia", dui);

                            if(existeUser.isEmpty()){
  
                                 Usuario usuario = new Usuario(modalUsuario.jtUser.getText(), clave, modalUsuario.cbRol.getSelectedItem().toString(), 1);
                                 
                                if(usuarioDao.insert(usuario)){
                                     //Mensaje Guardado
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                }
                                
                                modalOn = "";
                                modalUsuario.dispose();
                                
                            }else{
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Usuario " + modalUsuario.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                            }
                        }else{
                            //Contraseñas diferentes
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                        }
                        
                    }else{
                        
          
                        
                        //Modificar
                        ArrayList<Usuario> existeUser = usuarioDao.selectAllTo("usuario_nick", modalUsuario.jtUser.getText());
                        
                        if(existeUser.isEmpty()){
                            
                            usuarioSelected.setNickname(modalUsuario.jtUser.getText());
                            
                            if(usuarioDao.update(usuarioSelected)){ //Guardado
                                //Mensaje de modificado
                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                usuarioSelected = null;
                                modalUsuario.dispose();
                            }else{ //Ocurrio un error
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                            }
                        }else{
                            
                            if(existeUser.get(0).getNickname().equals(usuarioSelected.getNickname())){ //Dejo mismo nombre de usuario
                                usuarioSelected = null;
                                modalUsuario.dispose();
                            }else{ //Usuario ya existe
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Usuario " + modalUsuario.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                            }
                        }
    
                    }
                                        
                }else if(usuarioSelected != null && !modalUsuario.jtPass.getText().isEmpty() && !modalUsuario.jtPassRepet.getText().isEmpty()){
                    //Cambiar contraseña
                    if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){
                        
                        String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                        String dui = "";
                        
                        usuarioSelected.setClave(clave);
                        
                        if(usuarioDao.update(usuarioSelected)){
                            //Mensaje de contraseña modificado
                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                            DesktopNotify.showDesktopMessage("Contraseña modificada", "Su contraseña ha sido modificada exitosamente.", DesktopNotify.SUCCESS, 8000);
                            usuarioSelected = null;
                            modalUsuario.dispose();
                        }else{
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Error", "Contraseña no modificada.", DesktopNotify.FAIL, 8000);
                        } 

                    }else{
                        //Contraseñas diferentes
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                    }
                }else{
                    //Campos incompletos
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
                
                mostrarDatos(vistaUsuario.tablaUsuarios);
                
            }
            
        }
         /* CONTROL DE EMPLEADOS */
         if(principalOn.equals("Empleados") && modalOn.equals("modalEmpleado")){
                   if(btn.equals("Agregar")){
                if(!modalEmpleado.jtDui.getText().isEmpty()&& !modalEmpleado.jtNombre.getText().isEmpty()&&!modalEmpleado.jtApellido.getText().isEmpty()&&!modalEmpleado.jtEdad.getText().isEmpty()&&!modalEmpleado.jtEmail.getText().isEmpty()&&!modalEmpleado.jtTelefono.getText().isEmpty()&&!modalEmpleado.jtDireccion.getText().isEmpty()&&!modalEmpleado.jtSalario.getText().isEmpty()){
                    
                   if(empleadoSelected == null && !modalEmpleado.jtDui.getText().isEmpty()&& !modalEmpleado.jtNombre.getText().isEmpty()&& !modalEmpleado.jtApellido.getText().isEmpty()&& !modalEmpleado.jtEdad.getText().isEmpty()&& !modalEmpleado.jtEmail.getText().isEmpty()&& !modalEmpleado.jtTelefono.getText().isEmpty()&& !modalEmpleado.jtDireccion.getText().isEmpty()&& !modalEmpleado.jtSalario.getText().isEmpty()){

                        //if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){

                         //   String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                       //     String dui = "";

                           // if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                               // String v[] = modalUsuario.cbEmpleado.getSelectedItem().toString().split(" / ");

                               // ArrayList<Empleado> empleados = empleadoDao.buscar(v[1]);
                               // empleado = empleados.get(0);
                              //  dui = empleados.get(0).getDui();
                                
                            //}
                             Empleado empleado = new Empleado(modalEmpleado.jtDui.getText(), modalEmpleado.jtNombre.getText(),modalEmpleado.jtApellido.getText(),modalEmpleado.jtEdad.getText(),modalEmpleado.jtEmail.getText(),modalEmpleado.jtTelefono.getText(),modalEmpleado.jtDireccion.getText(),modalEmpleado.jtSalario.getText());
                                
                           ArrayList<Empleado> existeUser = empleadoDao.selectAllTo("dui_empleado", modalEmpleado.jtDui.getText());
                          //  ArrayList<Usuario> existeReferencia = usuarioDao.selectAllTo("usuario_referencia", dui);

                           if(existeUser.isEmpty() ){

                               
                                 
                               if(empleadoDao.insert(empleado)){
                                     
                                   // ArrayList<Empleado> empleados = empleadoDao.selectAllTo("dui_empleado", empleado.getDui());
                                  // Empleado empleadoRecuperado = empleados.get(0); 
                                     
                                   // if(usuarioRecuperado.getRol().equals("Empleado") || usuarioRecuperado.getRol().equals("Administrador")){
                                       // empleado.setUsuario(usuarioRecuperado);
                                      //  empleadoDao.updateUsuario(empleado);
                                        
                                        //Mensaje de guardado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Empleado guardado", "El empleado ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                //    }
                                    
                               }
                                
                               modalOn = "";
                              modalEmpleado.dispose();
                                
                            }
                          
                          //else{
                                
                                //if(!existeUser.isEmpty()){
                                   // DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                 //   DesktopNotify.showDesktopMessage("Usuario " + modalUsuario.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                               // }else{
                                //    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                              //      DesktopNotify.showDesktopMessage("Empleado ya asignado", "El empleado ya tiene asignada una cuenta de usuario.", DesktopNotify.WARNING, 10000);
                            //    }
                                                                
                          //  }
                        //}else{
                            //Contraseñas diferentes
                          //  DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        //    DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                      //  }
                      
                        
                   }else{
                        
                        if(empleadoSelected != null){
                            //Modificar
                              //Empleado empleado= new Empleado(modalEmpleado.jtDui.getText(),modalEmpleado.jtNombre.getText());                       
                                 ArrayList<Empleado> existeUser = empleadoDao.selectAllTo("nom_empleado", modalEmpleado.jtNombre.getText());

                            if(existeUser.isEmpty()){

                                empleadoSelected.setNombre(modalEmpleado.jtNombre.getText());

                                if(empleadoDao.update(empleadoSelected)){ //Guardado
                                    //Mensaje de modificado
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Empleado actualizado", "El empleado ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    empleadoSelected = null;
                                    modalEmpleado.dispose();
                                }else{ //Ocurrio un error
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Error", "Empleado no actualizado", DesktopNotify.FAIL, 8000);
                                }
                            }else{

                                if(existeUser.get(0).getNombre().equals(empleadoSelected.getNombre())){ //Dejo mismo nombre de usuario
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Empleado actualizado", "El empleado ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    empleadoSelected = null;
                                    modalEmpleado.dispose();
                                }else{ //Usuario ya existe
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Empleado " + modalEmpleado.jtNombre.getText() +  " ya existe", "El nuevo nombre de empleado debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                }
                            }
                        }
                        else{
                            //Campos incompletos
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                        }
    
                   }
                                        
               // }//else if(usuarioSelected != null && !modalUsuario.jtPass.getText().isEmpty() && !modalUsuario.jtPassRepet.getText().isEmpty()){
                    //Cambiar contraseña
                   // if(modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())){
                        
                     //   String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                       // String dui = "";
                        
                    //    usuarioSelected.setClave(clave);
                        
                      //  if(usuarioDao.update(usuarioSelected)){
                            //Mensaje de contraseña modificado
                          //  DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                           //DesktopNotify.showDesktopMessage("Contraseña modificada", "Su contraseña ha sido modificada exitosamente.", DesktopNotify.SUCCESS, 8000);
                         //   usuarioSelected = null;
                         //   modalUsuario.dispose();
                     //   }else{
                           // DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                         //   DesktopNotify.showDesktopMessage("Error", "Contraseña no modificada.", DesktopNotify.FAIL, 8000);
                        //} 

                   // }else{
                        //Contraseñas diferentes
                      //  DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                     //   DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                   // }
                }else{
                    //Campos incompletos
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
                
                mostrarDatos(vistaEmpleado.tablaEmpleados);
                
            }
            
        }
            }
            
        
    
        
   public void llenarComboBox(){
        
     //   if(modalOn.equals("modalUsuario")){
           // modalUsuario.cbEmpleado.removeAllItems();
            //modalUsuario.cbEmpleado.addItem("Seleccione");
            //String dato = "";
            
            //if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                
                //ArrayList<Empleado> empleados = empleadoDao.selectAll();
                
                //for(Empleado x : empleados){
                   // modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
                    //if(x.getDui().equals(usuarioSelected.getReferencia())){
                  //      dato = x.getNombre() + " / " + x.getDui();
                //    }
              //  }
                
            //    modalUsuario.cbEmpleado.setSelectedItem(dato);
                
          //  }
        //}
        
        //EMPLEADO BOX
             //  if(modalOn.equals("modalEmpleado")){
           // modalEmpleado.cbUsuario.removeAllItems();
            //modalEmpleado.cbUsuario.addItem("Seleccione");
            //String dato = "";
            
            //if(modalEmpleado.cbUsuario.getSelectedItem().toString().equals("Administrador") || modalEmpleado.cbUsuario.getSelectedItem().toString().equals("Empleado")){
                
              //  ArrayList<Usuario> usuarios = usuarioDao.selectAll();
                
                //for(Usuario x : usuarios){
                  //  modalEmpleado.cbUsuario.addItem(x.getNickname() + " / " + x.getIdUsuario());
                    //if(x.getNickname().equals(empleadoSelected.getUsuario())){
                      //  dato = x.getNickname() + " / " + x.getNickname();
                   // }
                //}
                
              //  modalEmpleado.cbUsuario.setSelectedItem(dato);
                
           // }
       // }
       
    }

    public void mostrarChart(){
        
        int cant[] = new int[12];
        int j = 0;
        int u = 0;
        int e = 0;
        
       // ArrayList<Factura> facturas = facturaDao.selectAll();
        //ArrayList<Producto> productos = productoDao.selectAll();
        ArrayList<Usuario> usuarios = usuarioDao.selectAll();
        ArrayList<Empleado> empleados = empleadoDao.selectAll();
        
    //    for(int i = 0; i < 12; i++){
      //      for(Factura x : facturas){
        //        String f[] = x.getFecha().toString().split("-");

          //      if((Integer.parseInt(f[1]) - 1) == i){
            //        j++;
              //  }
                
            //}
            
           // cant[i] = j;
          //  j = 0;
       // }
        
        //barChart.getBarChart(dashboard.pChart, cant); //Mostrar grafica
        
        for(Usuario x : usuarios){
            if(x.getEstado() > 0){
                u++;
            }
        }

        for(Empleado x : empleados){
            if(x.getEstado() > 0){
                e++;
            }
        }
        
        
        /* TOTALES */  
    //    dashboard.totalVents.setText(String.valueOf(facturas.size()));
      //  dashboard.totalProduct.setText(String.valueOf(productos.size()));
     //   dashboard.totalUsers.setText(String.valueOf(u));
       // dashboard.totalEmp.setText(String.valueOf(e));
        
       // mostrarDatos(dashboard.tablaUltimosProductos);
    }

      @Override
    public void mousePressed(MouseEvent e) {
        
        
            if(!principalOn.equals("Login")){
                    /* - - - BOTONES DEL MENU Y MODULOS - - - -*/
                if(e.getSource().equals(menu.btnDashboard)){
                    mostrarModulos("Menu");
              //  }else if(e.getSource().equals(menu.btnUsuarios)){
                //    mostrarModulos("Usuarios");
                //}else if(e.getSource().equals(vistaUsuario.btnNuevo)){
                  //  mostrarModals("nuevoUsuario");
                //}else if(e.getSource().equals(modalUsuario.btnGuardar)){
                  //  eventosBotones("Agregar");
                   //empleados
            }else if(e.getSource().equals(menu.btnEmpleados)){
                mostrarModulos("Empleados");
            }else if(e.getSource().equals(vistaEmpleado.btnNuevo)){
                mostrarModals("nuevoEmpleado");
            }else if(e.getSource().equals(modalEmpleado.btnGuardar)){
                eventosBotones("Agregar");
            }
            }else{
                /* LOGIN */
                if(e.getSource().equals(login.btnEntrar)){
                   // verificarCredenciales(); 
                }
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
          /* CONTROL DE EMPLEADOS */
        if(principalOn.equals("Empleados")){
            ArrayList<Empleado> lista = empleadoDao.buscar(vistaEmpleado.tfBusqueda.getText() + e.getKeyChar());
            if(lista.isEmpty()){
                mostrarDatos(vistaEmpleado.tablaEmpleados);
            }else{
                mostrarBusqueda(lista, vistaEmpleado.tablaEmpleados);
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
        //itemStateChanged EMPLEADO//
       //  if(modalOn.equals("modalEmpleado")){
         //   modalEmpleado.cbUsuario.setEnabled(true);
           // modalEmpleado.cbUsuario.removeAllItems();
           // modalEmpleado.cbUsuario.addItem("Asignar usuario");
            
           // if(modalEmpleado.cbUsuario.getSelectedItem().toString().equals("Usuario") || modalEmpleado.cbUsuario.getSelectedItem().toString().equals("Usuario")){
                
             //   ArrayList<Usuario> usuarios = usuarioDao.selectAll();

               // for(Usuario x : usuarios){
                 //   modalEmpleado.cbUsuario.addItem(x.getNickname() + " / " + x.getIdUsuario());
               // }
          //  }
        //}
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(principalOn.equals("Usuarios") && e.getSource() == vistaUsuario.tablaUsuarios){

            int columna = vistaUsuario.tablaUsuarios.getSelectedColumn();
            try{
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
            }catch(Exception ex){
                
            }
       
        }
        
        //MOUSECLICKED EMPLEADO//
        
       else if(principalOn.equals("Empleados") && e.getSource() == vistaEmpleado.tablaEmpleados){

            int columna =vistaEmpleado.tablaEmpleados.getSelectedColumn();
            try{
                if(columna == 12){
                    int fila = vistaUsuario.tablaUsuarios.getSelectedRow();
                    String dui = vistaUsuario.tablaUsuarios.getValueAt(fila, 1).toString();
                    ArrayList<Usuario> lista = usuarioDao.selectAllTo("dui_empleado", dui);
                    usuarioSelected = lista.get(0);
                    mostrarModals("editarEmpleado");
                }else if(columna == 13){
                  int fila = vistaUsuario.tablaUsuarios.getSelectedRow();
                    String dui = vistaUsuario.tablaUsuarios.getValueAt(fila, 1).toString();
                    ArrayList<Usuario> lista = usuarioDao.selectAllTo("dui_empleado", dui);
                    usuarioSelected = lista.get(0);
                    mostrarModals("eliminarEmpleado");
                }
            }catch(Exception ex){
                
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
