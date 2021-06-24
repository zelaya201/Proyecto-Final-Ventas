
package controlador;


import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import ejemplocompleto.utilidades.Encriptacion;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Cliente;
import modelos.Empleado;
import modelos.Factura;
import modelos.Producto;
import modelos.ProductoEstado;
import modelos.Usuario;
import modelos.dao.ClienteDao;
import modelos.dao.EmpleadoDao;
import modelos.dao.FacturaDao;
import modelos.dao.ProductoDao;
import modelos.dao.ProductoEstadoDao;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import utilidades.JFreeCharts;
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
    
    
    /* DASHBOARD */
    JFreeCharts barChart = new JFreeCharts();
    Dashboard dashboard;
    
    /* CONTROL DE USUARIOS */
    Usuario usuario = new Usuario();
    Usuario usuarioSelected = null;
    UsuarioDao usuarioDao = new UsuarioDao();
    VistaUsuario vistaUsuario;
    ModalUsuario modalUsuario;
    
    /* PRODUCTOS */
    Producto producto = null;
    Producto productoSelected = null;
    ProductoDao productoDao = new ProductoDao();
    ProductoEstadoDao productoEstadoDao = new ProductoEstadoDao();
//    VistaProducto vistaProducto;
//    ModalProducto modalProducto;
    int estado;
    String rutaFoto = "src/img/stock_product.png";
    int itemSeleccionado = 1;
    
    /* EMPLEADOS */
    Empleado empleado = new Empleado();
    Empleado empleadoSelected = null;
    EmpleadoDao empleadoDao = new EmpleadoDao(); 
    
    /* FACTURAS */
    Factura factura = new Factura();
    Factura facturaSelected = null;
    FacturaDao facturaDao = new FacturaDao();
    
    /* CLIENTES */
    ClienteDao clienteDao = new ClienteDao();

    public Controlador(Menu menu) {
        this.menu = menu;
        mostrarModulos("Menu");
    }

    public Controlador(Login login) {
        this.login = login;
        mostrarModulos("Login");
    }
    
    private void mostrarModulos(String vista){
        /* - - - - MOSTRAR MODULOS - - - - */
        if(vista.equals("Login")){
            login.setControlador(this);
            login.iniciar();
            principalOn = "Login";
        }else if(vista.equals("Menu")){
            dashboard = new Dashboard();
            menu.setControlador(this);
            menu.iniciar();
            principalOn = "Menu";
            
            new CambiaPanel(menu.body, dashboard);
            mostrarChart();
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
                        
            modalUsuario.cbEmpleado.setEnabled(false);
            modalUsuario.cbRol.setEnabled(false);
            modalUsuario.jtUser.setText(usuarioSelected.getNickname());
            
            modalUsuario.setSize(482, 346); //Width - Height
            llenarComboBox();
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
        
        if(principalOn.equals("Menu")){
            
            /* Tabla ultimos productos */
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            DecimalFormat id = new DecimalFormat("000000");

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);

            ArrayList<Producto> productos = productoDao.selectAllOrderBy();
            ArrayList<ProductoEstado> productoEstados = productoEstadoDao.selectAll();
            int i = 0;
            
                for (Producto x : productos) {
                    for (ProductoEstado y : productoEstados) {
                                              
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 1) {
                    
                            try {
                                Blob blob = x.getBdFoto();
                                byte[] data = blob.getBytes(1, (int) blob.length());
                                BufferedImage img = null;

                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                } catch (IOException ex) {
                                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 1 && i < 6) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), y.getStock()});
                                    i++;
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                }
       
            tabla.setModel(modelo);
            modelo = (DefaultTableModel)dashboard.tablaUltimasVentas.getModel();
            modelo.setRowCount(0);
            
            /* Tabla ultimas ventas */
            dashboard.tablaUltimasVentas.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            dashboard.tablaUltimasVentas.getColumnModel().getColumn(1).setCellRenderer(diseño);
            dashboard.tablaUltimasVentas.getColumnModel().getColumn(2).setCellRenderer(diseño);
            dashboard.tablaUltimasVentas.getColumnModel().getColumn(3).setCellRenderer(diseño);
            
            ArrayList<Factura> facturas = facturaDao.selectAllOrderBy();
            ArrayList<Cliente> clientes = clienteDao.selectAll();
            i = 0;
            
            for(Factura x : facturas){
                
                for(Cliente k : clientes){
                    if(x.getCliente().getIdPersona() == k.getIdPersona() && i < 10){
                        modelo.addRow(new Object[]{id.format(x.getNoFactura()), k.getNombre() + " " + k.getApellido(), x.getFecha(), x.getTotal()});
                        i++;
                    }
                }
                
                
            }
            
            dashboard.tablaUltimasVentas.setModel(modelo);
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
          
    }
    
    public void verificarCredenciales(){
        
        if(principalOn.equals("Login")){
            
            if(!login.jtUser.getText().isEmpty() && !login.jtPassword.getText().isEmpty()){
                
                ArrayList<Usuario> usuarios = usuarioDao.buscar(login.jtUser.getText());
                
                if(!usuarios.isEmpty() && usuarios.get(0).getEstado() == 1){
                    String clave = Encriptacion.getStringMessageDigest(login.jtPassword.getText(), Encriptacion.SHA256);
                    
                    if(clave.equals(usuarios.get(0).getClave())){
                        
                        dashboard = new Dashboard();
                        this.usuario = usuarios.get(0);
                        this.menu = new Menu();
                        this.menu.setControlador(this);
                        
                        if(usuario.getRol().equals("Administrador")){
  
                            ArrayList<Empleado> empleados = empleadoDao.selectAllTo("dui_empleado", usuario.getReferencia());
                            
                            String n[] = empleados.get(0).getNombre().split(" ");
                            String a[] = empleados.get(0).getApellido().split(" ");
                            
                            menu.lbUserName.setText(n[0] + " " + a[0]);
                            new CambiaPanel(menu.body, dashboard);
                            principalOn = "Menu";
                            mostrarChart();
                            
                        }else{
                            ArrayList<Empleado> empleados = empleadoDao.selectAllTo("dui_empleado", usuario.getReferencia());
                            
                            String n[] = empleados.get(0).getNombre().split(" ");
                            String a[] = empleados.get(0).getApellido().split(" ");
                            
                            menu.lbUserName.setText(n[0] + " " + a[0]); //Nombre de Usuario
                            
                            //Eliminar Modulos
                            menu.modulos.remove(menu.btnDashboard);
                            menu.modulos.remove(menu.btnUsuarios);
                            menu.modulos.remove(menu.btnEmpleados);
                            menu.modulos.remove(menu.btnProveedores);

                        }

                        menu.iniciar();
                        DesktopNotify.setDefaultTheme(NotifyTheme.LightBlue);
                        DesktopNotify.showDesktopMessage("¡Bienvenido/a " + usuario.getNickname() + "!", "Espero disfrutes del sistema, ten un buen día.", DesktopNotify.INFORMATION, 10000);
                        
                        login.dispose();
                    }else{
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                    }
                }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Usuario incorrecto", "Asegúrese de que el usuario digitado sea correcto.", DesktopNotify.WARNING, 8000);
                }
            }else{
                //Campos vacios
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
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

                            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                                String v[] = modalUsuario.cbEmpleado.getSelectedItem().toString().split(" / ");

                                ArrayList<Empleado> empleados = empleadoDao.buscar(v[1]);
                                empleado = empleados.get(0);
                                dui = empleados.get(0).getDui();
                                
                            }

                            ArrayList<Usuario> existeUser = usuarioDao.selectAllTo("usuario_nick", modalUsuario.jtUser.getText());
                            ArrayList<Usuario> existeReferencia = usuarioDao.selectAllTo("usuario_referencia", dui);

                            if(existeUser.isEmpty() && existeReferencia.isEmpty()){

                                 Usuario usuario = new Usuario(modalUsuario.jtUser.getText(), clave, modalUsuario.cbRol.getSelectedItem().toString(), 1, dui);
                                 
                                if(usuarioDao.insert(usuario)){
                                     
                                    ArrayList<Usuario> usuarios = usuarioDao.selectAllTo("usuario_referencia", usuario.getReferencia());
                                    Usuario usuarioRecuperado = usuarios.get(0); 
                                     
                                    if(usuarioRecuperado.getRol().equals("Empleado") || usuarioRecuperado.getRol().equals("Administrador")){
                                        empleado.setUsuario(usuarioRecuperado);
                                        empleadoDao.updateUsuario(empleado);
                                        
                                        //Mensaje de guardado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    }
                                    
                                }
                                
                                modalOn = "";
                                modalUsuario.dispose();
                                
                            }else{
                                
                                if(!existeUser.isEmpty()){
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario " + modalUsuario.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                }else{
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Empleado ya asignado", "El empleado ya tiene asignada una cuenta de usuario.", DesktopNotify.WARNING, 10000);
                                }
                                                                
                            }
                        }else{
                            //Contraseñas diferentes
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                        }
                        
                    }else{
                        
                        if(usuarioSelected != null){
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
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    usuarioSelected = null;
                                    modalUsuario.dispose();
                                }else{ //Usuario ya existe
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario " + modalUsuario.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                }
                            }
                        }else{
                            //Campos incompletos
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
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
    }
    
    public void llenarComboBox(){
        
        if(modalOn.equals("modalUsuario")){
            modalUsuario.cbEmpleado.removeAllItems();
            modalUsuario.cbEmpleado.addItem("Seleccione");
            String dato = "";
            
            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                
                ArrayList<Empleado> empleados = empleadoDao.selectAll();
                
                for(Empleado x : empleados){
                    modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
                    if(x.getDui().equals(usuarioSelected.getReferencia())){
                        dato = x.getNombre() + " / " + x.getDui();
                    }
                }
                
                modalUsuario.cbEmpleado.setSelectedItem(dato);
                
            }
        }
       
    }

    public void mostrarChart(){
        
        int cant[] = new int[12];
        int j = 0;
        int u = 0;
        int e = 0;
        
        ArrayList<Factura> facturas = facturaDao.selectAll();
        ArrayList<Producto> productos = productoDao.selectAll();
        ArrayList<Usuario> usuarios = usuarioDao.selectAll();
        ArrayList<Empleado> empleados = empleadoDao.selectAll();
        
        for(int i = 0; i < 12; i++){
            for(Factura x : facturas){
                String f[] = x.getFecha().toString().split("-");

                if((Integer.parseInt(f[1]) - 1) == i){
                    j++;
                }
                
            }
            
            cant[i] = j;
            j = 0;
        }
        
        barChart.getBarChart(dashboard.pChart, cant); //Mostrar grafica
        
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
        dashboard.totalVents.setText(String.valueOf(facturas.size()));
        dashboard.totalProduct.setText(String.valueOf(productos.size()));
        dashboard.totalUsers.setText(String.valueOf(u));
        dashboard.totalEmp.setText(String.valueOf(e));
        
        mostrarDatos(dashboard.tablaUltimosProductos);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        try{
            if(!principalOn.equals("Login")){
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
            }else{
                /* LOGIN */
                if(e.getSource().equals(login.btnEntrar)){
                    verificarCredenciales(); 
                }
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
            
            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                
                ArrayList<Empleado> empleados = empleadoDao.selectAll();

                for(Empleado x : empleados){
                    modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
                }
                
//                if(modalUsuario.cbEmpleado.getItemCount() < 0){
//                    modalUsuario.cbEmpleado.addItem("No se encontro empleado");
//                }
                
            }else{
                modalUsuario.cbEmpleado.setEnabled(false);
            }
        }
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
