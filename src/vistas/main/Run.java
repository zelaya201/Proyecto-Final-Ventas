package vistas.main;

import controlador.Controlador;
import vistas.main.Menu;

public class Run {
    public static void main(String[] args) {
        Menu vMenu = new Menu();
        Controlador control = new Controlador(vMenu);
//
//        Login login = new Login();
//        Controlador control = new Controlador(login);
        
    }
}
