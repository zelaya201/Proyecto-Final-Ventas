
package controlador;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import modelos.Usuario;
import utilidades.CambiaPanel;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.NuevoUsuario;
import vistas.modulos.VistaUsuario;

public class Controlador implements MouseListener{
    private Menu menu;
    private Login login;
    private String principalOn = "";
    private String modalOn = "";
    
    /* CONTROL DE USUARIOS */
    Usuario usuario = new Usuario();
    Usuario usuarioSelected = null;
    VistaUsuario vistaUsuario;
    NuevoUsuario nuevoUsuario;
    

    public Controlador(Menu menu) {
        this.menu = menu;
        mostrarVista("Menu");
    }

    public Controlador(Login login) {
        this.login = login;
    }
    
    private void mostrarVista(String vista){
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
        }else if(vista.equals("AgregarUsuario")){
            nuevoUsuario = new NuevoUsuario(new JFrame(), true, vistaUsuario);
            nuevoUsuario.iniciar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*BOTONES DEL MENU*/
        if(e.getSource().equals(menu.btnDashboard)){
            mostrarVista("Menu");
        }else if(e.getSource().equals(menu.btnUsuarios)){
            mostrarVista("Usuarios");
        }else if(e.getSource().equals(vistaUsuario.btnNuevo)){
            mostrarVista("AgregarUsuario");
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

    
    

}
