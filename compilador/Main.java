package compilador;

public class Main {
    public static void main(String[] args) {
        Analizador ana = new Analizador();
        GestorArchivos ges = new GestorArchivos();
        Ventana ventana = new Ventana(ana, ges);
        ventana.setVisible(true);
    }
}