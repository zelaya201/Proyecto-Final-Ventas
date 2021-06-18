package vistas.main;

import controlador.Controlador;
import javax.swing.UIManager;

public class Run {
    public static void main(String[] args) {
        Menu vMenu = new Menu();
        Controlador control = new Controlador(vMenu);
    }
}
