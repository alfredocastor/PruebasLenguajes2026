package pruebacom;

import pruebacom.control.Analizador;
import pruebacom.control.GestorArchivos;
import pruebacom.vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Analizador ana = new Analizador();
        GestorArchivos ges = new GestorArchivos();
    VentanaPrincipal ventana = new VentanaPrincipal();
    ventana.setVisible(true);
    }
}