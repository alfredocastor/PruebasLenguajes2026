/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

/**
 *
 * @author Alfredo
 */
public class Estaticos {

    public static final int MAX_CALIF = 100;
    public static final int MIN_CALIF = 0;
    public static final int MIN_APROB = 70;
    public static final int MENOR_QUE = 16;
    public static final int MAYOR_QUE = 33;

    public static String mensaje() {
        String mensaje = "Hola, soy un metodo estatico";
        return mensaje;
    }

    public String mensajeNS() {
        String mensaje = "Hola, soy un metodo no estatico";
        return mensaje;
    }
    public static int esReservada(String palabra){
        int res= -1;
        String[] reservadas ={"const","var","while"};
        palabra= palabra.toLowerCase();
        for (int i = 0; i < reservadas.length; i++) {
            if (palabra.equals(reservadas[i])) {
                return i+1;
            }
        }
        return res;
    }
}
