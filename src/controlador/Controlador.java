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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelos.Categoria;
import modelos.Producto;
import modelos.ProductoEstado;
import modelos.Proveedor;
import modelos.Usuario;
import modelos.dao.CategoriaDao;
import modelos.dao.ProductoDao;
import modelos.dao.ProductoEstadoDao;
import modelos.dao.ProveedorDao;
import modelos.dao.UsuarioDao;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalProducto;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaProducto;
import vistas.modulos.VistaUsuario;

public class Controlador implements MouseListener, KeyListener, ItemListener, FocusListener {

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

    /* PROVEEDOR */
    ProveedorDao proveedorDao = new ProveedorDao();

    public Controlador(Menu menu) {
        this.menu = menu;
        mostrarModulos("Menu");
    }

    public Controlador(Login login) {
        this.login = login;
    }

    private void mostrarModulos(String vista) {
        /* - - - - MOSTRAR MODULOS - - - */
        if (vista.equals("Menu")) {
            menu.setControlador(this);
            menu.iniciar();
            principalOn = "Menu";
            new CambiaPanel(menu.body, new Dashboard());
        } else if (vista.equals("Usuarios")) {
            vistaUsuario = new VistaUsuario();
            vistaUsuario.setControlador(this);
            principalOn = "Usuarios";
            new CambiaPanel(menu.body, vistaUsuario);
            mostrarDatos(vistaUsuario.tablaUsuarios);
        } else if (vista.equals("Productos")) {
            vistaProducto = new VistaProducto();
            vistaProducto.setControlador(this);
            principalOn = "Productos";
            fieldActivo = "";
            new CambiaPanel(menu.body, vistaProducto);
            mostrarDatos(vistaProducto.tbProductos);
        }
    }

    private void mostrarModals(String modal) {
        /* - - - - MOSTRAR MODALS - - - - */
        DecimalFormat id = new DecimalFormat("000000");

        /* PRODUCTOS */
        if (modal.equals("nuevoProducto") && principalOn.equals("Productos")) {
            modalProducto = new ModalProducto(new JFrame(), true, vistaProducto);
            modalProducto.setControlador(this);
            modalOn = "modalProducto";
            rsscalelabel.RSScaleLabel.setScaleLabel(modalProducto.lbFoto, "src/img/stock_product.png");
            llenarComboBox();
            int index = productoDao.getNextId();
            modalProducto.tfCodigo.setText(String.valueOf(id.format(index)));
            modalProducto.cbEstado.setSelectedIndex(1);
            modalProducto.cbEstado.setEnabled(false);
            modalProducto.iniciar();
            productoSelected = null;
        } else if (modal.equals("editarProducto") && principalOn.equals("Productos")) {
            try {
                modalProducto = new ModalProducto(new JFrame(), true, vistaProducto);
                modalProducto.setControlador(this);
                modalOn = "modalProducto";

                ArrayList<ProductoEstado> lista = productoEstadoDao.selectAllTo("cod_producto2", String.valueOf(productoSelected.getCodProducto()));
                ProductoEstado estado = null;

                if (lista.size() > 1) {
                    for (ProductoEstado x: lista) {
                        if (x.getEstado() == itemSeleccionado) {
                            estado = x;
                        }
                    }
                }else {
                    estado = lista.get(0);
                }

                modalProducto.header.setText("Editar Producto");
                modalProducto.tfCodigo.setText(String.valueOf(id.format(productoSelected.getCodProducto())));
                modalProducto.tfCodigo.setEnabled(false);
                
                llenarComboBox();

                if (estado.getEstado() == 0) {
                    modalProducto.cbEstado.setSelectedIndex(2);
                } else if (estado.getEstado() == 1) {
                    modalProducto.cbEstado.setSelectedIndex(1);
                } else if (estado.getEstado() == 2) {
                    modalProducto.cbEstado.setSelectedIndex(3);
                }
                
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
            if (productoSelected != null) {
                System.out.println(itemSeleccionado);
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
                }

                productoSelected = null;
            }
        }

        /* CONTROL DE USUARIOS */
        if (modal.equals("nuevoUsuario") && principalOn.equals("Usuarios")) {
            modalUsuario = new ModalUsuario(new JFrame(), true, vistaUsuario);
            modalUsuario.setControlador(this);
            modalOn = "modalUsuario";
            modalUsuario.iniciar();
            usuarioSelected = null;
        }
    }

    public void mostrarDatos(JTable tabla) {
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

        /* PRODUCTOS */
        if (principalOn.equals("Productos")) {
            //tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            DecimalFormat id = new DecimalFormat("000000");
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
                if (tabla.getColumnCount() == 7) {
                    TableColumn tc1 = new TableColumn();
                    TableColumn tc2 = new TableColumn();
                    tc1.setHeaderValue("Editar");
                    tc2.setHeaderValue("Eliminar");
                    tabla.addColumn(tc1);
                    tabla.addColumn(tc2);
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
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 0) {
                try {
                    tabla.removeColumn(tabla.getColumnModel().getColumn(7));
                    tabla.removeColumn(tabla.getColumnModel().getColumn(8));
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
                tabla.setModel(modelo);
            } else if (itemSeleccionado == 2) {
                if (tabla.getColumnCount() == 7) {
                    TableColumn tc1 = new TableColumn();
                    TableColumn tc2 = new TableColumn();
                    tc1.setHeaderValue("Editar");
                    tc2.setHeaderValue("Eliminar");
                    tabla.addColumn(tc1);
                    tabla.addColumn(tc2);
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
                tabla.setModel(modelo);
            }
        }

        /* CONTROL DE USUARIOS */
        if (principalOn.equals("Usuarios")) {
            //tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);

            ArrayList<Usuario> usuarios = usuarioDao.selectAll();
            int i = 1;

            for (Usuario x : usuarios) {

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

    public void mostrarBusqueda(ArrayList lista, JTable tabla) {

        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        /* PRODUCTOS */
        if (principalOn.equals("Productos")) {
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            DecimalFormat id = new DecimalFormat("0000");
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

                modelo.addRow(new Object[]{i, x.getNickname(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete, lbImg_change});
                i++;
            }

            tabla.setModel(modelo);
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
                                DesktopNotify.showDesktopMessage("Producto guardado", "El producto ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
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
                                            if (productoEstadoDao.updatePorEstadoAnterior(estados, itemSeleccionado)) {
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

        if (principalOn.equals("Usuarios") && modalOn.equals("modalUsuario")) {
            if (btn.equals("Agregar")) {
                if (!modalUsuario.jtUser.getText().isEmpty()
                        && !modalUsuario.jtPass.getText().isEmpty()
                        && !modalUsuario.jtPassRepet.getText().isEmpty()
                        && modalUsuario.cbEmpleado.getSelectedIndex() > 0
                        && modalUsuario.cbRol.getSelectedIndex() > 0) {

                    if (modalUsuario.jtPass.getText().equals(modalUsuario.jtPassRepet.getText())) {
                        String clave = Encriptacion.getStringMessageDigest(modalUsuario.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave
                        String dui = "";

                        if (modalUsuario.cbRol.getSelectedItem().toString().equals("Gerente") || modalUsuario.cbRol.getSelectedItem().toString().equals("Empleado")) {
                            //String v[] = modalUsuario.cbEmpleado.getSelectedItem().toString().split(" / ");
                            //ArrayList<Empleado> empleados = daoEmpleado.buscar(v[1]);
                            //empleado = empleados.get(0);
                            //dui = empleados.get(0).getDui();
                        }
                    } else {
                        //Contraseñas diferentes
                    }
                } else {
                    //Campos incompletos
                }
            } else {
                //Modificar
            }
        }
    }

    public void cambiarEstadoProducto() {
        ArrayList<Producto> productos = productoDao.selectAll();
        ArrayList<ProductoEstado> estadosProductos = productoEstadoDao.selectAll();
        int estadoAnteriorActivo = 0;
        int estadoAnteriorAlmacen = 0;
        ProductoEstado estadoActivo = new ProductoEstado();
        ProductoEstado estadoAlmacen = new ProductoEstado();
        
        for (Producto x: productos) {
            for (ProductoEstado y: estadosProductos) {
                if (x.getCodProducto() == y.getProducto().getCodProducto()) {
                    if (y.getEstado() == 1 && y.getStock() == 0) {
                        estadoActivo = y;
                        estadoAnteriorActivo = y.getEstado();
                    }
                    
                    if (y.getEstado() == 2 && y.getStock() > 0) {
                        estadoAlmacen = y;
                        estadoAnteriorAlmacen = y.getEstado();
                    }
                }
            }
        }
        
        estadoActivo.setEstado(0);

        estadoAlmacen.setEstado(1);
        
        productoEstadoDao.updatePorEstadoAnterior(estadoActivo, estadoAnteriorActivo);
        productoEstadoDao.updatePorEstadoAnterior(estadoAlmacen, estadoAnteriorAlmacen);
        
        mostrarDatos(vistaProducto.tbProductos);
    }
    public void llenarComboBox() {
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* - - - BOTONES DEL MENU Y MODULOS - - - -*/
        try{ 
            if (e.getSource().equals(menu.btnDashboard)) {
            mostrarModulos("Menu");
            } else if (e.getSource().equals(menu.btnUsuarios)) {
                mostrarModulos("Usuarios");
            } else if (e.getSource().equals(menu.btnProductos)) {
                mostrarModulos("Productos");
            } else if (e.getSource().equals(vistaProducto.btnNuevoProducto)) {
                mostrarModals("nuevoProducto");
            } else if (e.getSource().equals(modalProducto.btnImg)) {
                eventosBotones("AgregarImg");
            } else if (e.getSource().equals(modalProducto.btnGuardar)) {
                eventosBotones("Agregar");
            } else if (e.getSource().equals(vistaUsuario.btnNuevo)) {
                mostrarModals("nuevoUsuario");
            } else if (e.getSource().equals(modalUsuario.btnGuardar)) {
                eventosBotones("Agregar");
            }
        }catch (Exception ex){
            
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
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
