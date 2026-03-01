package analizadorlexico;

import analizadorlexico.control.Analizador;
import analizadorlexico.control.GestorArchivos;
import analizadorlexico.vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        try {
        javax.swing.UIManager.setLookAndFeel(
            "javax.swing.plaf.nimbus.NimbusLookAndFeel"
        );
    } catch (Exception e) { }
        Analizador ana = new Analizador();
        GestorArchivos ges = new GestorArchivos();
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}
