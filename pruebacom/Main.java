package pruebacom;

public class Main {
    public static void main(String[] args) {
        Analizador analizador = new Analizador();
        GestorArchivos gestor = new GestorArchivos();
        Ventana ventana = new Ventana(analizador, gestor);
        ventana.setVisible(true);
    }
}