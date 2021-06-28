package controlador;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import ejemplocompleto.utilidades.Encriptacion;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Categoria;
import modelos.Cliente;
import modelos.DetalleFactura;
import modelos.Empleado;
import modelos.Factura;
import modelos.Producto;
import modelos.ProductoEstado;
import modelos.Proveedor;
import modelos.Usuario;
import modelos.dao.CategoriaDao;
import modelos.dao.ClienteDao;
import modelos.dao.DetalleFacturaDao;
import modelos.dao.EmpleadoDao;
import modelos.dao.FacturaDao;
import modelos.dao.ProductoDao;
import modelos.dao.ProductoEstadoDao;
import modelos.dao.ProveedorDao;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import utilidades.JFreeCharts;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.ConfirmDialog;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalEmpleado;
import vistas.modulos.ModalFactura;
import vistas.modulos.ModalFacturaProducto;
import vistas.modulos.ModalProducto;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaEmpleado;
import vistas.modulos.VistaFactura;
import vistas.modulos.VistaProducto;
import vistas.modulos.VistaUsuario;
import vistas.modulos.VistasProveedores;

public class Controlador implements MouseListener, KeyListener, ItemListener, FocusListener {

    DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private String principalOn = "";
    private String modalOn = "";
    ConfirmDialog confirmDialog;
    
    /* DASHBOARD */
    JFreeCharts barChart = new JFreeCharts();
    Dashboard dashboard;
    
    /* CONTROL DE USUARIOS */
    Usuario usuario = new Usuario();
    Usuario usuarioSelected = null;
    UsuarioDao usuarioDao = new UsuarioDao();
    VistaUsuario vistaUsuario;
    ModalUsuario modalUsuario;

    /* PROVEEDOR */
    Proveedor proveedor = new Proveedor();
    Proveedor proveedorSelected = null;
    ProveedorDao proveedorDao = new ProveedorDao();
    VistasProveedores vistaProveedor;
    
    /* PRODUCTOS */
    Producto producto = null;
    Producto productoSelected = null;
    ProductoDao productoDao = new ProductoDao();
    ProductoEstadoDao productoEstadoDao = new ProductoEstadoDao();
    VistaProducto vistaProducto;
    ModalProducto modalProducto;
    int estado;
    String rutaFoto = "src/img/stock_product.png";
    String fieldActivo = null;
    int itemSeleccionado = 1;
    int limpiarField = 0;

    /* CATEGORIAS */
    CategoriaDao categoriaDao = new CategoriaDao();

    /* FACTURAS */
    Factura factura = new Factura();
    Factura facturaSelected = null;
    FacturaDao facturaDao = new FacturaDao();
    VistaFactura vistaFactura;
    ModalFactura modalFactura;
    ModalFacturaProducto modalFacturaProducto;
    ArrayList<DetalleFactura> detalles = new ArrayList();
    int cantidadProducto = 0;
    
    /* DETALLE FACTURA */
    DetalleFacturaDao detalleFacturaDao = new DetalleFacturaDao();
    
     /* EMPLEADOS */
    Empleado empleado = new Empleado();
    Empleado empleadoSelected = null;
    EmpleadoDao empleadoDao = new EmpleadoDao(); 
    VistaEmpleado vistaEmpleado; 
    ModalEmpleado modalEmpleado;
    private String nickname;
    
    /* CLIENTE */
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
            login = new Login();
            login.setControlador(this);
            login.iniciar();
            principalOn = "Login";
        }else if(vista.equals("Menu")){
            dashboard = new Dashboard();
//            menu.setControlador(this);
            menu.iniciar();
            principalOn = "Menu";
            new CambiaPanel(menu.body, dashboard);
            mostrarChart();
        } else if (vista.equals("Productos")) {
            vistaProducto = new VistaProducto();
            vistaProducto.setControlador(this);
            principalOn = "Productos";
            fieldActivo = "";
            
            if(usuario.getRol().equals("Empleado")){
                vistaProducto.header.remove(vistaProducto.btnNuevoProducto);
                vistaProducto.header.remove(vistaProducto.cbMostrar);
            }
            
            new CambiaPanel(menu.body, vistaProducto);
            mostrarDatos(vistaProducto.tbProductos);
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
        }else if(vista.equals("Proveedor")){
            vistaProveedor = new VistasProveedores();
            vistaProveedor.setControlador(this);
            principalOn = "Proveedor";
            new CambiaPanel(menu.body, vistaProveedor);
            mostrarDatos(vistaProveedor.tablaProveedor);    
        }else if(vista.equals("Factura")){
            vistaFactura = new VistaFactura();
            vistaFactura.setControlador(this);
            principalOn = "Factura";
            new CambiaPanel(menu.body, vistaFactura);
            mostrarDatos(vistaFactura.tablaFactura);  
        }
    }

    private void mostrarModals(String modal) {
        /* - - - - MOSTRAR MODALS - - - - */
 
        modalUsuario = new ModalUsuario(new JFrame(), true);
        confirmDialog = new ConfirmDialog(new JFrame(), true);

        DecimalFormat id = new DecimalFormat("000000");
       
        /* PRODUCTOS */
        if (modal.equals("nuevoProducto") && principalOn.equals("Productos")) {
            productoSelected = null;
            modalProducto = new ModalProducto(new JFrame(), true, vistaProducto);
            modalProducto.setControlador(this);
            modalOn = "modalProducto";
            modalProducto.cbEstado.setSelectedItem("Activo");
            modalProducto.cbEstado.setEnabled(false);
            rsscalelabel.RSScaleLabel.setScaleLabel(modalProducto.lbFoto, "src/img/stock_product.png");
            llenarComboBox();
            int index = productoDao.getNextId();
            modalProducto.tfCodigo.setText(String.valueOf(id.format(index)));
            modalProducto.cbEstado.setSelectedIndex(1);
            modalProducto.cbEstado.setEnabled(false);
            modalProducto.iniciar();
        } else if (modal.equals("editarProducto") && principalOn.equals("Productos")) {
            try {
                modalProducto = new ModalProducto(new JFrame(), true, vistaProducto);
                modalProducto.setControlador(this);
                modalOn = "modalProducto";

                ArrayList<ProductoEstado> lista = productoEstadoDao.selectAllTo("cod_producto2", String.valueOf(productoSelected.getCodProducto()));
                ProductoEstado estado = null;

                if (lista.size() > 1) {
                    for (ProductoEstado x: lista) {
                        if (itemSeleccionado == x.getEstado()) {
                            estado = x;
                        }
                    }
                }else {
                    estado = lista.get(0);
                }

                modalProducto.header.setText("Editar Producto");
                modalProducto.tfCodigo.setText(String.valueOf(id.format(productoSelected.getCodProducto())));
                modalProducto.tfCodigo.setEnabled(false);

            
                modalProducto.cbEstado.setSelectedItem("Activo");
                modalProducto.cbEstado.setEnabled(false);
                
                llenarComboBox();
                modalProducto.cbEstado.setEnabled(false);
                modalProducto.cbProveedor.setSelectedItem(productoSelected.getProveedor().getNombre());
                modalProducto.cbCategoria.setSelectedItem(productoSelected.getCategoria().getNombre());
                modalProducto.taDescripcion1.setText(productoSelected.getDescripcion());
                modalProducto.tfPrecioCompra.setText(String.valueOf(estado.getPrecioCompra()));
                modalProducto.tfPorcentaje.setText(String.valueOf(estado.getGanancia()));
                modalProducto.tfPrecioVenta.setText(String.valueOf(estado.getPrecioVenta()));
                modalProducto.tfStock.setText(String.valueOf(estado.getStock()));

                Blob blob = productoSelected.getBdFoto();
                byte[] data = blob.getBytes(1, (int) blob.length());
                BufferedImage img = null;

                try {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }

                ImageIcon imgIco = new ImageIcon(img);
                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(modalProducto.lbFoto.getWidth(), modalProducto.lbFoto.getHeight(), Image.SCALE_DEFAULT));
                modalProducto.lbFoto.setIcon(imgIco2);
                modalProducto.setSize(1300, 625);
                modalProducto.iniciar();
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (modal.equals("eliminarProducto") && principalOn.equals("Productos")) {
            confirmDialog = new ConfirmDialog(new JFrame(), true);
            confirmDialog.setControlador(this);
            modalOn = "modalDialog";

            confirmDialog.header.setText("Eliminar ");
            confirmDialog.textDialog.setText("<html>¿Estás seguro que quieres eliminar el producto <b>" + productoSelected.getDescripcion() + "</b>? </html>");
            confirmDialog.btnEliminar.setText("Eliminar");
            confirmDialog.iniciar();
            
        }
        
        /* FACTURAS */
        if(modal.equals("nuevoFactura")){
            modalFactura = new ModalFactura(new JFrame(), true, vistaFactura);
            modalFactura.setControlador(this);
            modalOn = "modalFactura";
            llenarComboBox();
            int index = facturaDao.getNextId();
            modalFactura.numFactura.setText("N° de Factura: " + String.valueOf(id.format(index)));
            String fecha = obtenerFechaFormateada(new Date());
            modalFactura.jtFecha.setText(fecha);
            modalFactura.jtFecha.setEnabled(false);
            facturaSelected = new Factura(index);
            
            modalFactura.iniciar();
        }else if(modal.equals("addProducto")){

            modalFacturaProducto = new ModalFacturaProducto(new JFrame(), true, modalFactura);
            modalFacturaProducto.setControlador(this);
            modalOn = "modalFactura";
            mostrarProductos(modalFacturaProducto.tbAddProducto);
            modalFacturaProducto.iniciar();

        }
        
        /* CONTROL DE USUARIOS */
        if(modal.equals("nuevoUsuario")){
            usuarioSelected = null;
            
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            modalUsuario.iniciar();
        }else if(modal.equals("editarUsuario")){
            modalUsuario = new ModalUsuario(new JFrame(), true);
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
        }else if(modal.equals("eliminarUsuario")){
            confirmDialog = new ConfirmDialog(new JFrame(), true);
            confirmDialog.setControlador(this);
            modalOn = "modalDialog";

            confirmDialog.header.setText("Eliminar ");
            confirmDialog.textDialog.setText("<html>¿Estás seguro que quieres eliminar el usuario <b>" + usuarioSelected.getNickname() + "</b>? </html>");
            confirmDialog.btnEliminar.setText("Eliminar");
            confirmDialog.iniciar();
        }else if(modal.equals("changePassUsuario")){
            modalUsuario = new ModalUsuario(new JFrame(), true);
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
            modalEmpleado = new ModalEmpleado(new JFrame(), true);
            modalEmpleado.setControlador(this);
            modalOn = "modalEmpleado";
            modalEmpleado.iniciar();
        }else if(modal.equals("editarEmpleado") && principalOn.equals("Empleados")){
           modalEmpleado = new ModalEmpleado(new JFrame(), true);
            modalEmpleado.setControlador(this);
            modalOn = "modalEmpleado";
       
             modalEmpleado.header.setText("Editar Empleado");
      
                 
            modalEmpleado.jtDui.setText(empleadoSelected.getDui());
            modalEmpleado.jtDui.setEnabled(false);
               modalEmpleado.jtNombre.setText(empleadoSelected.getNombre());
               modalEmpleado.jtApellido.setText(empleadoSelected.getApellido());
               modalEmpleado.jcGenero.setSelectedItem(empleadoSelected.getGenero());
               modalEmpleado.jtEdad.setText(String.valueOf(empleadoSelected.getEdad()));
               modalEmpleado.jtEmail.setText(empleadoSelected.getGmail());
                 modalEmpleado.jtTelefono.setText(empleadoSelected.getTelefono());
                   modalEmpleado.jtDireccion.setText(empleadoSelected.getDireccion());
                       modalEmpleado.jtSalario.setText(String.valueOf(empleadoSelected.getSalario()));
          
            modalEmpleado.iniciar();
            
        }else if(modal.equals("eliminarEmpleado") && principalOn.equals("Empleados")){
            confirmDialog = new ConfirmDialog(new JFrame(), true);
            confirmDialog.setControlador(this);
            modalOn = "modalDialog";

            confirmDialog.header.setText("Eliminar ");
            confirmDialog.textDialog.setText("<html>¿Estás seguro que quieres eliminar el empleado <b>" + empleadoSelected.getNombre() + " " + empleadoSelected.getApellido() + "</b>? </html>");
            confirmDialog.btnEliminar.setText("Eliminar");
            confirmDialog.iniciar();
        }
    }
    
    public String obtenerFechaFormateada(Date fecha) {
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy"); //Formato de fecha
        return formatFecha.format(fecha); //retorna la fecha formateada
    }
    
    public void mostrarDatos(JTable tabla){

        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        DecimalFormat id = new DecimalFormat("000000");
        
        /* PRODUCTOS */
        if (principalOn.equals("Productos")) {
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);

            ArrayList<Producto> productos = productoDao.selectAll();
            ArrayList<ProductoEstado> productoEstados = productoEstadoDao.selectAll();

            if (itemSeleccionado == 1) {
                
                if(usuario.getRol().equals("Empleado")){
                    tabla.getColumnModel().getColumn(7).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(7).setMinWidth(0);
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
                    
                    tabla.getColumnModel().getColumn(8).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(8).setMinWidth(0);
                    tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
                }else{
                    tabla.getColumnModel().getColumn(7).setMaxWidth(tabla.getColumnModel().getColumn(6).getMaxWidth());
                    tabla.getColumnModel().getColumn(7).setMinWidth(tabla.getColumnModel().getColumn(6).getMinWidth());
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(tabla.getColumnModel().getColumn(6).getPreferredWidth());
                    
                    tabla.getColumnModel().getColumn(8).setMaxWidth(tabla.getColumnModel().getColumn(6).getMaxWidth());
                    tabla.getColumnModel().getColumn(8).setMinWidth(tabla.getColumnModel().getColumn(6).getMinWidth());
                    tabla.getColumnModel().getColumn(8).setPreferredWidth(tabla.getColumnModel().getColumn(6).getPreferredWidth());
                }

                /* PRODUCTOS ACTIVOS */
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

                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoActivo = new ImageIcon(getClass().getResource("/img/activo.png"));
                                JLabel lbActivo = new JLabel(new ImageIcon(icoActivo.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);
                    
                                if (y.getEstado() == 1) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbActivo, lbImg_edit, lbImg_delete});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 0) {
                try {

                    tabla.getColumnModel().getColumn(7).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(7).setMinWidth(0);
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
                    
                    tabla.getColumnModel().getColumn(8).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(8).setMinWidth(0);
                    tabla.getColumnModel().getColumn(8).setPreferredWidth(0);

                }catch(Exception e) {
                    
                }
                /* PRODUCTOS INACTIVOS */
                for (Producto x : productos) {
                    for (ProductoEstado y : productoEstados) {
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 0) {
                            try {
                                Blob blob = x.getBdFoto();
                                byte[] data = blob.getBytes(1, (int) blob.length());
                                BufferedImage img = null;

                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                } catch (IOException ex) {
                                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoInactivo = new ImageIcon(getClass().getResource("/img/inactivo.png"));
                                JLabel lbInactivo = new JLabel(new ImageIcon(icoInactivo.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 0) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbInactivo, lbImg_edit});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 2) {
                
                if(usuario.getRol().equals("Empleado")){
                    tabla.getColumnModel().getColumn(7).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(7).setMinWidth(0);
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
                    
                    tabla.getColumnModel().getColumn(8).setMaxWidth(0);
                    tabla.getColumnModel().getColumn(8).setMinWidth(0);
                    tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
                }else{
                    tabla.getColumnModel().getColumn(7).setMaxWidth(tabla.getColumnModel().getColumn(6).getMaxWidth());
                    tabla.getColumnModel().getColumn(7).setMinWidth(tabla.getColumnModel().getColumn(6).getMinWidth());
                    tabla.getColumnModel().getColumn(7).setPreferredWidth(tabla.getColumnModel().getColumn(6).getPreferredWidth());
                    
                    tabla.getColumnModel().getColumn(8).setMaxWidth(tabla.getColumnModel().getColumn(6).getMaxWidth());
                    tabla.getColumnModel().getColumn(8).setMinWidth(tabla.getColumnModel().getColumn(6).getMinWidth());
                    tabla.getColumnModel().getColumn(8).setPreferredWidth(tabla.getColumnModel().getColumn(6).getPreferredWidth());
                }
                
                /* PRODUCTOS EN ALMACEN */
                for (Producto x : productos) {
                    for (ProductoEstado y : productoEstados) {
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 2) {
                            try {
                                Blob blob = x.getBdFoto();
                                byte[] data = blob.getBytes(1, (int) blob.length());
                                BufferedImage img = null;

                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                } catch (IOException ex) {
                                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoAlmacen = new ImageIcon(getClass().getResource("/img/almacen.png"));
                                JLabel lbAlmacen = new JLabel(new ImageIcon(icoAlmacen.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 2) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbAlmacen, lbImg_edit, lbImg_delete});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                
                tabla.setModel(modelo);
            }
        }

        /* CONTROL DE USUARIOS */
        if (principalOn.equals("Usuarios")) {
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
        
        /* DASHBOARD */
        if(principalOn.equals("Menu")){
            
            /* Tabla ultimos productos */
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

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
            ArrayList<Factura> facturas = facturaDao.selectAllOrderBy();
            i = 0;
            
            for(Factura x : facturas){
         
                if(i < 10){
                    modelo.addRow(new Object[]{id.format(x.getNoFactura()), x.getCliente().getNombre() + " " + x.getCliente().getApellido(), x.getFecha(), x.getFecha()});
                    i++;
                }

                
            }
            
            dashboard.tablaUltimasVentas.setModel(modelo);
          
        }
        
        /* PROVEEDORES */
        if(principalOn.equals("Proveedor")){
          
            ArrayList<Proveedor> proveedor = proveedorDao.selectAll();
     
            for(Proveedor x : proveedor){

            modelo.addRow(new Object[]{x.getCodProveedor(), x.getNombre(), x.getTelefono(), x.getDireccion()});
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }
            
            tabla.setModel(modelo);
            
        }
        
        /* FACTURA */
        if(principalOn.equals("Factura")){
            ArrayList<Factura> factura = facturaDao.selectAll();
     
            for(Factura x : factura){
                modelo.addRow(new Object[]{id.format(x.getNoFactura()), x.getCliente().getNombre() + " " + x.getCliente().getApellido(), x.getFecha(), x.getVendedor().getNombre() + " " + x.getVendedor().getApellido(), x.getTotal()});
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }

            tabla.setModel(modelo);
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
       
            
            ArrayList<Empleado> empleados = empleadoDao.selectAll();
            int i = 1;
            
            for(Empleado x : empleados){

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

              
                
                if(x.getEstado() > 0){
                    modelo.addRow(new Object[]{i,x.getDui(),x.getNombre(), x.getApellido(), x.getGenero(),x.getEdad(), x.getEmail(), x.getTelefono(), x.getDireccion(), "$ "+x.getSalario() ,lbImg_edit, lbImg_delete});
                    i++;
                }
               
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }
            
            tabla.setModel(modelo);
        }
        
    }

    public void mostrarBusqueda(ArrayList lista, JTable tabla) {

        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        DecimalFormat id = new DecimalFormat("000000");
        
        /* PRODUCTOS */
        if (principalOn.equals("Productos")) {
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);

            ArrayList<ProductoEstado> productoEstados = productoEstadoDao.selectAll();

            if (itemSeleccionado == 1) {
                for (Object obj : lista) {
                    Producto x = (Producto) obj;
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
                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoActivo = new ImageIcon(getClass().getResource("/img/activo.png"));
                                JLabel lbActivo = new JLabel(new ImageIcon(icoActivo.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 1) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbActivo, lbImg_edit, lbImg_delete});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 0) {
                for (Object obj : lista) {
                    Producto x = (Producto) obj;
                    for (ProductoEstado y : productoEstados) {
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 0) {
                            try {
                                Blob blob = x.getBdFoto();
                                byte[] data = blob.getBytes(1, (int) blob.length());
                                BufferedImage img = null;

                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                } catch (IOException ex) {
                                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoInactivo = new ImageIcon(getClass().getResource("/img/inactivo.png"));
                                JLabel lbInactivo = new JLabel(new ImageIcon(icoInactivo.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 0) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbInactivo, lbImg_edit, lbImg_delete});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 2) {
                for (Object obj : lista) {
                    Producto x = (Producto) obj;
                    for (ProductoEstado y : productoEstados) {
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 2) {
                            try {
                                Blob blob = x.getBdFoto();
                                byte[] data = blob.getBytes(1, (int) blob.length());
                                BufferedImage img = null;

                                try {
                                    img = ImageIO.read(new ByteArrayInputStream(data));
                                } catch (IOException ex) {
                                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                                ImageIcon icoAlmacen = new ImageIcon(getClass().getResource("/img/almacen.png"));
                                JLabel lbAlmacen = new JLabel(new ImageIcon(icoAlmacen.getImage()));

                                ImageIcon imgIco = new ImageIcon(img);
                                ImageIcon imgIco2 = new ImageIcon(img.getScaledInstance(60, 60, Image.SCALE_DEFAULT));
                                JLabel lbImg = new JLabel(imgIco2);

                                if (y.getEstado() == 2) {
                                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), lbImg, x.getDescripcion(), x.getCategoria().getNombre(), "$ " + formateador.format(y.getPrecioVenta()), y.getStock(), lbAlmacen, lbImg_edit, lbImg_delete});
                                }

                                tabla.setRowHeight(65);
                            } catch (SQLException ex) {
                                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                if(modelo.getRowCount() < 1){
                    modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
                }
                tabla.setModel(modelo);
            }
        }
        
        /* CONTROL DE USUARIOS */
        if (principalOn.equals("Usuarios")) {
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);

            int i = 1;

            for (Object obj : lista) {

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
        
        /* PROVEEDORES */
        if(principalOn.equals("Proveedor")){
           
            for(Object obj : lista){
                
                Proveedor x = (Proveedor) obj;
              
                if(x.getEstado() > 0){
                    modelo.addRow(new Object[]{x.getCodProveedor(), x.getNombre(), x.getTelefono(), x.getDireccion()});
                }
            }
            
            if(modelo.getRowCount() < 1){
                mostrarDatos(vistaProveedor.tablaProveedor);
            }else{
                tabla.setModel(modelo);
            } 
        }
        
         /* FACTURAS */
        if(principalOn.equals("Factura")){
           
            for(Object obj : lista){
                
                Factura x = (Factura) obj;
             
                modelo.addRow(new Object[]{id.format(x.getNoFactura()), x.getCliente().getNombre() + " " + x.getCliente().getApellido(), x.getFecha(),  x.getVendedor().getNombre() + " " + x.getVendedor().getApellido(), x.getIva(), x.getTotal()});
               
            }
            
            if(modelo.getRowCount() < 1){
                mostrarDatos(vistaFactura.tablaFactura);
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
    
    public void verificarCredenciales(MouseEvent e){ 
        if(principalOn.equals("Login")){ 
            if(!login.jtUser.getText().isEmpty() && !login.jtPassword.getText().isEmpty()){
                
                ArrayList<Usuario> usuarios = usuarioDao.selectAllTo("usuario_nick", login.jtUser.getText());
                
                if(!usuarios.isEmpty() && usuarios.get(0).getEstado() == 1){
                    String clave = Encriptacion.getStringMessageDigest(login.jtPassword.getText(), Encriptacion.SHA256);
                    
                    if(clave.equals(usuarios.get(0).getClave())){
                        
                        dashboard = new Dashboard();
                        vistaProducto = new VistaProducto();
                   
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
                            principalOn = "Productos";
                            
                            vistaProducto.header.remove(vistaProducto.btnNuevoProducto);
                            vistaProducto.header.remove(vistaProducto.cbMostrar);
                            new CambiaPanel(menu.body, vistaProducto);
                            mostrarDatos(vistaProducto.tbProductos);
                            
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
        
        if(e.getSource() == menu.btnSalir){
            usuarioSelected = null; //Por si esta seleccionado un usuario
            usuario = null; //Igualamos a null usuario logueado
            menu.dispose(); //Cerramos el menu
            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
            DesktopNotify.showDesktopMessage("Sesión cerrada", "Se ha cerrado sesión con exito.", DesktopNotify.SUCCESS, 8000); //8 seg
            mostrarModulos("Login"); //Iniciamos el login
        }
    }
    
    public void eventosBotones(String btn){
        /* Agregar Producto */
        if (principalOn.equals("Productos") && modalOn.equals("modalProducto")) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
            JFileChooser fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false); //Deshabilitar todos los archivos
            fc.setFileFilter(filter); //Solamente aceptar imagenes
            fc.setMultiSelectionEnabled(false);
            fc.setDialogTitle("Buscar Imagen");
            
            if (btn.equals("AgregarImg")) {
                if (fc.showOpenDialog(modalProducto) == JFileChooser.APPROVE_OPTION) {
                        rutaFoto = fc.getSelectedFile().getAbsolutePath();
                        rsscalelabel.RSScaleLabel.setScaleLabel(modalProducto.lbFoto, rutaFoto);
                }
            } else if (btn.equals("Agregar")) {
                ArrayList<Producto> productos = productoDao.selectAllTo("descripcion_producto", modalProducto.taDescripcion1.getText());
                ArrayList<ProductoEstado> productosEstados = productoEstadoDao.selectAll();
                ArrayList<Producto> existeProducto = new ArrayList();
                
                for (Producto x: productos) {
                    for (ProductoEstado y: productosEstados) {
                        if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 1) {
                            if (modalProducto.taDescripcion1.getText().toLowerCase().equals(x.getDescripcion().toLowerCase())) {
                                existeProducto.add(x);
                            }
                        } 
                    }
                }
                
                if (modalProducto.cbEstado.getSelectedIndex() > 0
                        && modalProducto.cbProveedor.getSelectedIndex() > 0
                        && modalProducto.cbCategoria.getSelectedIndex() > 0
                        && !modalProducto.taDescripcion1.getText().isEmpty()
                        && !modalProducto.tfPrecioCompra.getText().isEmpty()
                        && !modalProducto.tfPorcentaje.getText().isEmpty()
                        && !modalProducto.tfPrecioVenta.getText().isEmpty()
                        && !modalProducto.tfStock.getText().isEmpty()) {
                    if (existeProducto.isEmpty() && productoSelected == null) {
                        if (modalProducto.cbEstado.getSelectedItem().toString().equals("Inactivo")) {
                            estado = 0;
                        } else if (modalProducto.cbEstado.getSelectedItem().toString().equals("Activo")) {
                            estado = 1;
                        } else if (modalProducto.cbEstado.getSelectedItem().toString().equals("En Almacen")) {
                            estado = 2;
                        }

                        ArrayList<Proveedor> existeProveedor = proveedorDao.selectAllTo("nom_proveedor", modalProducto.cbProveedor.getSelectedItem().toString());
                        Proveedor proveedor = existeProveedor.get(0);

                        ArrayList<Categoria> existeCategoria = categoriaDao.selectAllTo("nom_categoria", modalProducto.cbCategoria.getSelectedItem().toString());
                        Categoria categoria = existeCategoria.get(0);

                        if (!rutaFoto.equals("")) {
                            producto = new Producto(rutaFoto, modalProducto.taDescripcion1.getText(), proveedor, categoria);
                        }

                        if (productoDao.insert(producto)) {
                            existeProducto = productoDao.selectAllTo("descripcion_producto", producto.getDescripcion());
                            Producto productoObtenido = existeProducto.get(0);

                            ProductoEstado productoEstado = new ProductoEstado(Double.parseDouble(modalProducto.tfPrecioCompra.getText()), Double.parseDouble(modalProducto.tfPrecioVenta.getText()), Integer.parseInt(modalProducto.tfStock.getText()), estado, Double.parseDouble(modalProducto.tfPorcentaje.getText()), productoObtenido);

                            if (productoEstadoDao.insert(productoEstado)) {
                                //Mensaje Guardado
                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                DesktopNotify.showDesktopMessage("Producto guardado", "El producto ha sido almacenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                            }
                            modalOn = "";
                            modalProducto.dispose();
                        }
                    }else if (productoSelected != null) {
                        // Modificar producto
                        ArrayList<ProductoEstado> lista = productoEstadoDao.selectAllTo("cod_producto2", String.valueOf(productoSelected.getCodProducto()));
                        ProductoEstado estados = null;

                        Categoria categoria = categoriaDao.selectAllTo("nom_categoria", modalProducto.cbCategoria.getSelectedItem().toString()).get(0);
                        Proveedor proveedor = proveedorDao.selectAllTo("nom_proveedor", modalProducto.cbProveedor.getSelectedItem().toString()).get(0);

                        String descripcion = modalProducto.taDescripcion1.getText();
                        double precioCompra = Double.parseDouble(modalProducto.tfPrecioCompra.getText());
                        double ganancia = Double.parseDouble(modalProducto.tfPorcentaje.getText());
                        double precioVenta = Double.parseDouble(modalProducto.tfPrecioVenta.getText());
                        int stock = Integer.parseInt(modalProducto.tfStock.getText());
                        
                        if (modalProducto.cbEstado.getSelectedItem().toString().equals("Inactivo")) {
                            estado = 0;
                            System.out.println(estado);
                        } else if (modalProducto.cbEstado.getSelectedItem().toString().equals("Activo")) {
                            estado = 1;
                            System.out.println(estado);
                        } else if (modalProducto.cbEstado.getSelectedItem().toString().equals("En Almacen")) {
                            estado = 2;
                            System.out.println(estado);
                        }
                        
                        if (lista.size() > 1) {
                            for (ProductoEstado x: lista) {
                                if (itemSeleccionado == x.getEstado()) {
                                    estados = x;
                                }
                            }
                        }else {
                            estados = lista.get(0);
                        }

                        if (precioCompra == estados.getPrecioCompra() && estados.getEstado() == itemSeleccionado) {
                            if (estado != estados.getEstado() || proveedor.getCodProveedor() != productoSelected.getProveedor().getCodProveedor()
                                    || categoria.getIdCategoria() != productoSelected.getCategoria().getIdCategoria()
                                    || !descripcion.equals(productoSelected.getDescripcion()) || precioCompra != estados.getPrecioCompra()
                                    || ganancia != estados.getGanancia() || precioVenta != estados.getPrecioVenta()
                                    || stock != estados.getStock()) {
                                productoSelected.setDescripcion(descripcion);
                                productoSelected.setCategoria(categoria);
                                productoSelected.setProveedor(proveedor);
                                productoSelected.setRutaFoto(rutaFoto);

                                estados.setEstado(estado);
                                estados.setPrecioCompra(precioCompra);
                                estados.setGanancia(ganancia);
                                estados.setPrecioVenta(precioVenta);
                                estados.setStock(stock);

                                if (rutaFoto.equals("src/img/stock_product.png")) {
                                    Blob blob = productoSelected.getBdFoto();
                                    productoSelected.setBdFoto(blob);
                                    if (productoDao.updateBlob(productoSelected)) {
                                        if (estados.getEstado() != itemSeleccionado) {
                                            if (productoEstadoDao.update(estados)) {
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Producto actualizado", "El producto ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                productoSelected = null;
                                                modalProducto.dispose();
                                            } else {
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Producto no actualizado.", DesktopNotify.FAIL, 8000);
                                            }
                                        }else {
                                            if (productoEstadoDao.updateEspecifico(estados)) {
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Producto actualizado", "El producto ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                productoSelected = null;
                                                modalProducto.dispose();
                                            } else {
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Producto no actualizado.", DesktopNotify.FAIL, 8000);
                                            }
                                        }

                                        modalOn = "";
                                        modalProducto.dispose();
                                    }
                                }else {
                                    productoSelected.setRutaFoto(rutaFoto);
                                    if (productoDao.update(productoSelected)) {
                                        if (estados.getEstado() != itemSeleccionado) {
                                            if (productoEstadoDao.update(estados)) {
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Producto actualizado", "El producto ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                productoSelected = null;
                                                modalProducto.dispose();
                                            } else {
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Producto no actualizado.", DesktopNotify.FAIL, 8000);
                                            }
                                        }else {
                                            if (productoEstadoDao.updateEspecifico(estados)) {
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Producto actualizado", "El producto ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                productoSelected = null;
                                                modalProducto.dispose();
                                            } else {
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Producto no actualizado.", DesktopNotify.FAIL, 8000);
                                            }
                                        }
                                        modalOn = "";
                                        modalProducto.dispose();
                                    }
                                } 
                            }else {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Producto no actualizado", "No se ha modificado ningun campo.", DesktopNotify.FAIL, 8000);
                            }      
                        } else {
                            ProductoEstado productoEstado = new ProductoEstado(precioCompra, precioVenta, stock, 2, ganancia, productoSelected);

                            if (productoEstadoDao.insert(productoEstado)) {
                                //Mensaje Guardado
                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                DesktopNotify.showDesktopMessage("Producto guardado en almacen", "El producto ha sido almacenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                            }
                            modalOn = "";
                            modalProducto.dispose();
                        }
                    }else {
                        //Producto ya existe
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Error", "El producto ya existe.", DesktopNotify.FAIL, 8000);
                    }
                    mostrarDatos(vistaProducto.tbProductos);
                }else {
                    //Campos vacios
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
            } 
        }
        
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
                                        DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido almacenado exitosamente.", DesktopNotify.SUCCESS, 8000);
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
        
        /* FACTURA */
        if(principalOn.equals("Factura") && modalOn.equals("modalFactura")){
            if(btn.equals("Guardar")){
                
                if(!modalFactura.jtFecha.getText().isEmpty() && !modalFactura.jtApeClien.getText().isEmpty()
                        && !modalFactura.jFDui.getText().isEmpty()  && !modalFactura.jtDirec.getText().isEmpty()
                        && !modalFactura.jtImail.getText().isEmpty() && !modalFactura.jFTelef.getText().isEmpty()
                        && !modalFactura.jtFecha.getText().isEmpty() && modalFactura.cbRol.getSelectedIndex() > 0){
                        System.out.println("Estoy en evento");
                        double iva = 0;
                        double subtotal = 0;
                        double total = 0;
                        
                        for (DetalleFactura x: detalles) {
                            subtotal += x.getSubtotal();
                        }
                        
                        String v[] = modalFactura.cbRol.getSelectedItem().toString().split(" / ");

                        ArrayList<Empleado> empleados = empleadoDao.buscar(v[1]);
                        empleado = empleados.get(0);
                        Cliente objCliente = new Cliente(modalFactura.jtImail.getText(),modalFactura.jFDui.getText(),modalFactura.jtNomClien.getText(),modalFactura.jtApeClien.getText(),modalFactura.jtDirec.getText(),modalFactura.jFTelef.getText());
                        Cliente cliente = null;
                        if (clienteDao.insert(objCliente)) {
                            cliente = clienteDao.selectAllTo("id_cliente", String.valueOf(objCliente.getIdPersona())).get(0);
                        }else {
                            //Error de guardado
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Error", "No se ha podido almacenar la factura.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                        
                        iva = subtotal * 0.13;
                        total = subtotal + iva;

                        Factura factura = new Factura(iva,modalFactura.jtFecha.getText(),total,empleado, cliente);
                                 
                        if(facturaDao.insert(factura)){
                            for (DetalleFactura x: detalles) {
                                detalleFacturaDao.insert(x);
                            }
                            
                            //Mensaje de guardado
                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                            DesktopNotify.showDesktopMessage("Factura guardada", "La factura ha sido almacenada exitosamente.", DesktopNotify.SUCCESS, 8000);
                        }else {
                            //Error de guardado
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Error", "No se ha podido almacenar la factura.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                                
                    modalOn = "";
                    modalFactura.dispose();
                    detalles.clear();
                }else {
                    //Campos incompletos
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
                mostrarDatos(vistaFactura.tablaFactura);
            }
        }
            

        //Eliminar
        if(btn.equals("Eliminar")){
            if (modalOn.equals("modalDialog")){
                if(principalOn.equals("Productos")) {
                    if (productoSelected != null) {
                        ArrayList<ProductoEstado> lista = productoEstadoDao.selectAllTo("cod_producto2", String.valueOf(productoSelected.getCodProducto()));
                        ProductoEstado estados = null;
                        if (lista.size() > 1) {
                            for (ProductoEstado x: lista) {
                                if (x.getEstado() == itemSeleccionado) {
                                    System.out.println(x.getEstado());
                                    estados = x;
                                    System.out.println(estados.getEstado());
                                }
                            }
                        }else {
                            estados = lista.get(0);
                        }
                        estados.setEstado(0);
                        if (productoEstadoDao.updatePorEstadoAnterior(estados, itemSeleccionado)) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Producto eliminado", "El producto ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                            mostrarDatos(vistaProducto.tbProductos);
                            confirmDialog.dispose();
                        }

                        productoSelected = null;
                    }
                }else if (principalOn.equals("Usuarios")){
                    if(!usuarioSelected.getNickname().equals(usuario.getNickname())){
                        usuarioSelected.setEstado(0);
                        
                        ArrayList<Empleado> lista = empleadoDao.selectAllTo("dui_empleado", usuarioSelected.getReferencia());
                        empleado = lista.get(0);
                        empleado.setUsuario(null);

                        empleadoDao.updateUsuario(empleado);
                        
                        if(usuarioDao.update(usuarioSelected)){
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Usuario eliminado", "El usuario ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                            mostrarDatos(vistaUsuario.tablaUsuarios);
                            confirmDialog.dispose();

                        }
                    }else{
                        confirmDialog.dispose();
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("No puede eliminar este usuario", "No es posible eliminar el usuario logueado actualmente.", DesktopNotify.INFORMATION, 8000);
                        mostrarDatos(vistaProducto.tbProductos);
                        
                    }

                    usuarioSelected = null;
                }else if (principalOn.equals("Empleados")) {
                    if(empleadoSelected != null){
                        empleadoSelected.setEstado(0);
                        if(empleadoDao.update(empleadoSelected)){
                            //empleadoSelected=null;
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Empleado eliminado", "El Empleado ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                            mostrarDatos(vistaEmpleado.tablaEmpleados);
                            confirmDialog.dispose();
                        }

                        empleadoSelected = null;
                    }
                }
            }
        }
        
         /* CONTROL DE EMPLEADOS */
         if(principalOn.equals("Empleados") && modalOn.equals("modalEmpleado")){
             
                if(btn.equals("Agregar")){
                    
                    if(!modalEmpleado.jtDui.getText().isEmpty()&& 
                            !modalEmpleado.jtNombre.getText().isEmpty()&&
                            !modalEmpleado.jtApellido.getText().isEmpty() && 
                             modalEmpleado.jcGenero.getSelectedIndex()> 0 &&
                            !modalEmpleado.jtEdad.getText().isEmpty()&&
                            !modalEmpleado.jtEmail.getText().isEmpty()&&
                            !modalEmpleado.jtTelefono.getText().isEmpty()&&
                            !modalEmpleado.jtDireccion.getText().isEmpty()&&
                            !modalEmpleado.jtSalario.getText().isEmpty()){
                      
                          
                        if(empleadoSelected == null ){
                    //String genero, int edad, String email, double salario, int estado, String dui, String nombre, String apellido, String direccion, String telefono
                                Empleado empleado = new Empleado(
                                        modalEmpleado.jcGenero.getSelectedItem().toString(),
                                        Integer.parseInt(modalEmpleado.jtEdad.getText()),
                                        modalEmpleado.jtEmail.getText(),
                                        Double.parseDouble(modalEmpleado.jtSalario.getText()),
                                        1,
                                        modalEmpleado.jtDui.getText(),
                                        modalEmpleado.jtNombre.getText(), 
                                        modalEmpleado.jtApellido.getText(),
                                        modalEmpleado.jtDireccion.getText(),
                                        modalEmpleado.jtTelefono.getText());
                                 
                                if(empleadoDao.insert(empleado)){
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Empleado guardado", "El empleado se ha guardo correctamente.", DesktopNotify.WARNING, 8000);
                                }
                                modalEmpleado.dispose();

                        }else
                         if(empleadoSelected != null ){
                             
                              empleadoSelected.setDui(modalEmpleado.jtDui.getText());
                                empleadoSelected.setNombre(modalEmpleado.jtNombre.getText());
                               empleadoSelected.setApellido(modalEmpleado.jtApellido.getText());
                                   empleadoSelected.setGenero(modalEmpleado.jcGenero.getSelectedItem().toString());
                                    empleadoSelected.setEdad(Integer.parseInt(modalEmpleado.jtEdad.getText()));
                                     empleadoSelected.setEmail(modalEmpleado.jtEmail.getText());
                                     empleadoSelected.setTelefono(modalEmpleado.jtTelefono.getText());
                                       empleadoSelected.setDireccion(modalEmpleado.jtDireccion.getText());
                                    empleadoSelected.setSalario(Double.parseDouble(modalEmpleado.jtSalario.getText()));
                                    
                                   
                             if(empleadoDao.update(empleadoSelected)){
                                 empleadoSelected=null;
                                 DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                               DesktopNotify.showDesktopMessage("Actualizado correctamente", "Se actualizo el empleado.", DesktopNotify.SUCCESS, 8000); //8 seg
            
                             }
                            
                                  modalEmpleado.dispose();                             

                                   }

                    }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
             
                    }
                                        
                }else{
                    //Campos incompletos
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
                
                mostrarDatos(vistaEmpleado.tablaEmpleados);
                
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
        
        if (modalOn.equals("modalProducto")) {
            ArrayList<Categoria> categorias = categoriaDao.selectAll();

            modalProducto.cbCategoria.setEnabled(true);
            modalProducto.cbCategoria.removeAllItems();
            modalProducto.cbCategoria.addItem("Seleccione una categoria");

            for (Categoria x : categorias) {
                modalProducto.cbCategoria.addItem(x.getNombre());
            }

            ArrayList<Proveedor> proveedores = proveedorDao.selectAll();

            modalProducto.cbProveedor.setEnabled(true);
            modalProducto.cbProveedor.removeAllItems();
            modalProducto.cbProveedor.addItem("Seleccione un proveedor");

            for (Proveedor x : proveedores) {
                if (x.getEstado() == 1) {
                    modalProducto.cbProveedor.addItem(x.getNombre());
                }
            }
        }
        
        if (modalOn.equals("modalFactura")) {
            ArrayList<Empleado> empleados = empleadoDao.selectAll();
            String dato = "";
            modalFactura.cbRol.setEnabled(true);
            modalFactura.cbRol.removeAllItems();
            modalFactura.cbRol.addItem("Seleccione un vendedor");

            for (Empleado x : empleados) {
                modalFactura.cbRol.addItem(x.getNombre() + " / " + x.getDui());
                if(x.getDui().equals(usuario.getReferencia())){
                        dato = x.getNombre() + " / " + x.getDui();
                }
            }
            modalFactura.cbRol.setSelectedItem(dato);
        }
       
    }

    public void mostrarChart(){
        
        int cant[] = new int[12];
        int j = 0, u = 0, e = 0, s = 0, p = 0, a = 0, tp = 0;
        
        ArrayList<Factura> facturas = facturaDao.selectAll();
        ArrayList<Producto> productos = productoDao.selectAll();
        ArrayList<Usuario> usuarios = usuarioDao.selectAll();
        ArrayList<Empleado> empleados = empleadoDao.selectAll();
        ArrayList<ProductoEstado> productoEstados = productoEstadoDao.selectAll();
 
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
        
        dashboard.totalUsers.setText(String.valueOf(u));
        dashboard.totalEmp.setText(String.valueOf(e));
        
        mostrarDatos(dashboard.tablaUltimosProductos);
        
        /* ALERTAS */
        for (Producto x : productos) {
            for (ProductoEstado y : productoEstados) {
                
                if(x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 1){
                    tp++;
                }
                
                if (x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 1 && y.getStock() < 1) {
                        s++;
                }
                
                if(x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 1 && y.getStock() <= 1 && y.getStock() > 0){
                    p++;
                }
                
                if(x.getCodProducto() == y.getProducto().getCodProducto() && y.getEstado() == 2){
                    a++;
                }
            }
        }
        
        dashboard.totalProduct.setText(String.valueOf(tp));
        dashboard.sinStock.setText(String.valueOf(s));
        dashboard.porAcabarse.setText(String.valueOf(p));
        dashboard.enEspera.setText(String.valueOf(a));
    }
    
    public void mostrarProductos(JTable tabla) {
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        
        DecimalFormat id = new DecimalFormat("000000");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
        
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

        tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
        tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);

        ArrayList<Producto> productos = productoDao.selectAll();
        
        for (Producto x : productos) {
            for (ProductoEstado y : x.getEstadosProducto()) {
                if (y.getEstado() == 1) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/img/add_shopping_cart_22px.png"));
                    ImageIcon img2 = new ImageIcon(img.getImage());
                    JLabel lbImg = new JLabel(img2);

                    modelo.addRow(new Object[]{id.format(x.getCodProducto()), x.getDescripcion(), "$ " + formateador.format(y.getPrecioVenta()), lbImg});
                }
            }
        }

        if(modelo.getRowCount() < 1){
            modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
        }

        tabla.setModel(modelo);  
    }
    
    public void mostrarDetalles(JTable tabla) {
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        
        DecimalFormat id = new DecimalFormat("000000");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
        
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

        tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
        tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
        
        for (DetalleFactura x : detalles) {
            for (ProductoEstado y: x.getProducto().getEstadosProducto()) {
                if (y.getEstado() == 1) {
                    ImageIcon img = new ImageIcon(getClass().getResource("/img/add_shopping_cart_22px.png"));
                    ImageIcon img2 = new ImageIcon(img.getImage());
                    JLabel lbImg = new JLabel(img2);

                    modelo.addRow(new Object[]{id.format(x.getProducto().getCodProducto()), x.getCantidadProducto(), x.getProducto().getDescripcion(), "$ " + formateador.format(y.getPrecioVenta()), "$ " + formateador.format(x.getSubtotal()), lbImg});
                }
            }
        }

        if(modelo.getRowCount() < 1){
            modelo.addRow(new Object[]{"", "", "Ningún producto agregado"});
        }

        tabla.setModel(modelo);  
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
      
        //JLabel labelReference = (JLabel) event.getSource();

        if(!principalOn.equals("Login")){
            /* - - - BOTONES DEL MENU Y MODULOS - - - -*/

            try{
                if(event.getSource().equals(menu.btnDashboard)){
                    mostrarModulos("Menu");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(menu.btnProductos)) {
                    mostrarModulos("Productos");
                }
            }catch(Exception e){

            }

            try{
                if (event.getSource().equals(vistaProducto.btnNuevoProducto)) {
                    mostrarModals("nuevoProducto");
                }
            }catch(Exception e){

            }

            try{
                if (event.getSource().equals(modalProducto.btnImg)) {
                    eventosBotones("AgregarImg");
                }
            }catch(Exception e){

            }

            try{
                if (event.getSource().equals(modalProducto.btnGuardar)) {
                    eventosBotones("Agregar");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(menu.btnFacturas)){
                    mostrarModulos("Factura");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(vistaFactura.btnNuevo)){
                    mostrarModals("nuevoFactura");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(menu.btnProveedores)){
                    mostrarModulos("Proveedor");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(menu.btnUsuarios)){
                    mostrarModulos("Usuarios");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(vistaUsuario.btnNuevo)){
                    mostrarModals("nuevoUsuario");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(modalUsuario.btnGuardar)){
                    eventosBotones("Agregar");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(confirmDialog.btnEliminar)){
                    eventosBotones("Eliminar");
                }
            }catch(Exception e){

            }

            try{
                if(event.getSource().equals(menu.btnSalir)){
                    verificarCredenciales(event);
                }
            }catch(Exception e){

            }
            
            try{
                if(event.getSource().equals(menu.btnEmpleados)){
                    mostrarModulos("Empleados");
                }
            }catch(Exception e){

            }
            
            try{
                if(event.getSource().equals(vistaEmpleado.btnNuevoEmpleado)){
                    mostrarModals("nuevoEmpleado");
                }
            }catch(Exception e){

            }
            
            try{
                if(event.getSource().equals(modalEmpleado.btnGuardarEmpleado)){
                    eventosBotones("Agregar");
                }
            }catch(Exception e){

            }
          

        }else{
            /* LOGIN */
            if(event.getSource().equals(login.btnEntrar)){
                verificarCredenciales(event); 
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        /* - - - - BUSQUEDA - - - - */
        
        /* PRODUCTOS */
        if (principalOn.equals("Productos")) {
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
            if (fieldActivo.equals("busqueda")) {
                ArrayList<Producto> lista = productoDao.buscar(vistaProducto.tfBusqueda.getText() + e.getKeyChar());
                if (lista.isEmpty()) {
                    mostrarDatos(vistaProducto.tbProductos);
                } else {
                    mostrarBusqueda(lista, vistaProducto.tbProductos);
                }
            }else if (modalOn.equals("modalProducto")) {
                try {
                    if (fieldActivo.equals("precioCompra")) {
                        if (!modalProducto.tfPorcentaje.getText().isEmpty()) {
                            double precioCompra = Double.parseDouble(modalProducto.tfPrecioCompra.getText() + e.getKeyChar());
                            double porcentaje = Double.parseDouble(modalProducto.tfPorcentaje.getText());
                            if (porcentaje <= 100) {
                                double ganancia = precioCompra * (porcentaje / 100);

                                modalProducto.tfPrecioVenta.setText(String.valueOf(formateador.format(precioCompra + ganancia)));
                            }
                            
                            if (Double.parseDouble(String.valueOf((modalProducto.tfPrecioCompra.getText() + e.getKeyChar()).charAt(0))) == precioCompra) {
                                limpiarField++;
                            }
                            
                            /* A medias */

                            if(limpiarField == 2) {
                                modalProducto.tfPrecioVenta.setText("");
                                limpiarField = 0;
                            }
                        }else { 
                            double precioCompra = Double.parseDouble(modalProducto.tfPrecioCompra.getText() + e.getKeyChar());
                            modalProducto.tfPrecioVenta.setText(String.valueOf(formateador.format(precioCompra)));
                            
                            if (Double.parseDouble(String.valueOf((modalProducto.tfPrecioCompra.getText() + e.getKeyChar()).charAt(0))) == precioCompra) {
                                limpiarField++;
                            }
                            
                            if(limpiarField == 2) {
                                modalProducto.tfPrecioVenta.setText("");
                                limpiarField = 0;
                            }
                            
                        }
                    }
                    
                    if (fieldActivo.equals("ganancia")) {                       
                        if (!modalProducto.tfPrecioCompra.getText().isEmpty()) {
                            double precioCompra = Double.parseDouble(modalProducto.tfPrecioCompra.getText());
                            double porcentaje = Double.parseDouble(modalProducto.tfPorcentaje.getText() + e.getKeyChar());
                            if (porcentaje <= 100) {
                                double ganancia = precioCompra * (porcentaje / 100);

                                modalProducto.tfPrecioVenta.setText(String.valueOf(formateador.format(precioCompra + ganancia)));
                            }
                            
                            if (Double.parseDouble(String.valueOf((modalProducto.tfPorcentaje.getText() + e.getKeyChar()).charAt(0))) == porcentaje) {
                                limpiarField++;
                            }
                                                        
                            if(limpiarField == 2) {
                                modalProducto.tfPrecioVenta.setText(String.valueOf(precioCompra));
                                limpiarField = 0;
                            }
                        }else {
                            double precioCompra = 0;
                            double porcentaje = Double.parseDouble(modalProducto.tfPorcentaje.getText() + e.getKeyChar());
                            if (porcentaje <= 100) {
                                double ganancia = precioCompra * (porcentaje / 100);

                                modalProducto.tfPrecioVenta.setText(String.valueOf(formateador.format(precioCompra + ganancia)));
                            }
                        }
                    }
                }catch (Exception ex) {
                    
                }
            }
        }
        
        /* CONTROL DE USUARIOS */
        if (principalOn.equals("Usuarios")) {
            ArrayList<Usuario> lista = usuarioDao.buscar(vistaUsuario.tfBusqueda.getText() + e.getKeyChar());
            if (lista.isEmpty()) {
                mostrarDatos(vistaUsuario.tablaUsuarios);
            } else {
                mostrarBusqueda(lista, vistaUsuario.tablaUsuarios);
            }
        }
        
        /* CONTROL DE PROVEEDOR */
        if(principalOn.equals("Proveedor")){
            ArrayList<Proveedor> lista = proveedorDao.buscar(vistaProveedor.tfBusqueda.getText() + e.getKeyChar());
            if(lista.isEmpty()){
                mostrarDatos(vistaProveedor.tablaProveedor);
            }else{
                mostrarBusqueda(lista, vistaProveedor.tablaProveedor);
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
            
            if(modalUsuario.cbRol.getSelectedItem().toString().equals("Administrador") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")){
                
                ArrayList<Empleado> empleados = empleadoDao.selectAll();

                for(Empleado x : empleados){
                    if(x.getEstado() > 0){
                        modalUsuario.cbEmpleado.addItem(x.getNombre() + " / " + x.getDui());
                    }
                    

                }
                
            }else{
                modalUsuario.cbEmpleado.setEnabled(false);
            }
        }
        
        if (principalOn.equals("Productos")) {
            if (vistaProducto.cbMostrar.getSelectedItem().equals("Mostrar") || vistaProducto.cbMostrar.getSelectedItem().equals("Activos")) {
                itemSeleccionado = 1;
                mostrarDatos(vistaProducto.tbProductos);
            } else if (vistaProducto.cbMostrar.getSelectedItem().equals("Inactivos")) {
                itemSeleccionado = 0;
                mostrarDatos(vistaProducto.tbProductos);
            } else if (vistaProducto.cbMostrar.getSelectedItem().equals("Almacen")) {
                itemSeleccionado = 2;
                mostrarDatos(vistaProducto.tbProductos);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //JTable tablaReference = (JTable) e.getSource();
        
        if(principalOn.equals("Usuarios") && e.getSource() == vistaUsuario.tablaUsuarios){

            int columna = vistaUsuario.tablaUsuarios.getSelectedColumn();
            //try{
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
            //}catch(Exception ex){
                
            //}
       
        }
        
          else if(principalOn.equals("Empleados") && e.getSource() == vistaEmpleado.tablaEmpleados){

            int columna = vistaEmpleado.tablaEmpleados.getSelectedColumn();
            try{
                if(columna == 10){
                    int fila = vistaEmpleado.tablaEmpleados.getSelectedRow();
                    String dui = vistaEmpleado.tablaEmpleados.getValueAt(fila, 1).toString();
                    ArrayList<Empleado> lista = empleadoDao.selectAllTo("dui_empleado", dui);
                    empleadoSelected = lista.get(0);
                    mostrarModals("editarEmpleado");
                }else if(columna == 11){
                  int fila = vistaEmpleado.tablaEmpleados.getSelectedRow();
                    String dui = vistaEmpleado.tablaEmpleados.getValueAt(fila, 1).toString();
                    ArrayList<Empleado> lista = empleadoDao.selectAllTo("dui_empleado", dui);
                    empleadoSelected = lista.get(0);
                    mostrarModals("eliminarEmpleado");
                }
            }catch(Exception ex){
                
            }
       
        }
        
        if (principalOn.equals("Productos") && e.getSource() == vistaProducto.tbProductos) {

            int columna = vistaProducto.tbProductos.getSelectedColumn();
            try {
                if (columna == 7) {
                    int fila = vistaProducto.tbProductos.getSelectedRow();
                    String codigo = vistaProducto.tbProductos.getValueAt(fila, 0).toString();
                    ArrayList<Producto> lista = productoDao.selectAllTo("cod_producto", codigo);
                    productoSelected = lista.get(0);
                    mostrarModals("editarProducto");
                } else if (columna == 8) {
                    int fila = vistaProducto.tbProductos.getSelectedRow();
                    String codigo = vistaProducto.tbProductos.getValueAt(fila, 0).toString();
                    ArrayList<Producto> lista = productoDao.selectAllTo("cod_producto", codigo);
                    productoSelected = lista.get(0);
                    mostrarModals("eliminarProducto");
                }
            } catch (Exception ex) {

            }

        }
        
        if (principalOn.equals("Factura") && modalOn.equals("modalFactura") && e.getSource() == modalFacturaProducto.tbAddProducto) {
            int columna = modalFacturaProducto.tbAddProducto.getSelectedColumn();
            
                if (columna == 3){
                    int fila = modalFacturaProducto.tbAddProducto.getSelectedRow();
                    String codigo = modalFacturaProducto.tbAddProducto.getValueAt(fila, 0).toString();
                    ArrayList<Producto> lista = productoDao.selectAllTo("cod_producto", codigo);
                    Producto productoS = lista.get(0);
                    ProductoEstado estadoProductoS = null;
                    
                    for (ProductoEstado x: productoS.getEstadosProducto()) {
                        if (x.getEstado() == 1) {
                            estadoProductoS = x;
                        }
                    }
                    
                    int cantidad = Integer.valueOf(JOptionPane.showInputDialog("Ingrese la cantidad a registrar de " + String.valueOf(productoS.getDescripcion() + ": ")));
                    double subtotal = cantidad * estadoProductoS.getPrecioVenta();
                    
                    DetalleFactura detalle = new DetalleFactura(cantidad, subtotal, productoS, facturaSelected);
                    
                    detalles.add(detalle);
                    for (DetalleFactura x: detalles) {
                        System.out.println(x.getCantidadProducto());
                        System.out.println(x.getProducto().getDescripcion());
                        System.out.println(x.getSubtotal());
                        System.out.println(x.getFactura().getNoFactura());
                    }
                    mostrarDetalles(modalFactura.tbProducto);
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
        if (principalOn.equals("Productos") && modalOn.equals("modalProducto")) {
            if(fieldActivo.equals("ganancia")) {
                try {
                    if(!Character.isDigit(ke.getKeyChar()) && ke.getKeyChar() != '.') {
                        ke.consume();
                    }

                    if(ke.getKeyChar() == '.' && modalProducto.tfPorcentaje.getText().contains(".")) {
                        ke.consume();
                    }
                    
                    if (Double.parseDouble(modalProducto.tfPorcentaje.getText() + ke.getKeyChar()) > 100) {
                        ke.consume();
                    }
                }catch(Exception e) {
                    
                }
            }
            
            if (fieldActivo.equals("precioCompra")) {
                if(!Character.isDigit(ke.getKeyChar()) && ke.getKeyChar() != '.') {
                    ke.consume();
                }

                if(ke.getKeyChar() == '.' && modalProducto.tfPrecioCompra.getText().contains(".")) {
                    ke.consume();
                }
            }
            
            if (fieldActivo.equals("stock")) {
                if((ke.getKeyChar() < '0' || ke.getKeyChar() > '9')){
                    ke.consume();
                }
            }
        }
        
        if (principalOn.equals("Factura") && modalOn.equals("modalFactura")) {
            char val = ke.getKeyChar();
            if((val<'a' || val>'z') && (val<'A' || val>'Z') && (val != ' ') && (val !='ñ')&& (val !='Ñ')&&(val<'á'|| val>'ú')&&(val<'Á'||val>'Ú')){
                ke.consume();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void focusGained(FocusEvent fe) {
        
        if (modalProducto.tfPrecioCompra.isFocusOwner()) {
            modalProducto.tfPorcentaje.setEnabled(true);
            fieldActivo = "precioCompra";
        }else if (modalProducto.tfPorcentaje.isFocusOwner()){
            fieldActivo = "ganancia";
        }else if (modalProducto.tfStock.isFocusOwner()){
            fieldActivo = "stock";
        }else if (vistaProducto.tfBusqueda.isFocusOwner()){
            fieldActivo = "busqueda";
        }  
    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (!modalProducto.tfPrecioCompra.isFocusOwner()) {
            limpiarField = 1;
        }
    }
}
