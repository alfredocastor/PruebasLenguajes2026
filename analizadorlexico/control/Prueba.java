package analizadorlexico.control;

public class Prueba {
    public static void main(String[] args) {
        System.out.println("Prueba de la clase Lexemas:");
        System.out.println("Token para Identificador: " + Lexemas.IDENTIFICADOR);
        System.out.println("Token para Número: " + Lexemas.NUMERO);
        System.out.println("Token para Igualdad (==): " + Lexemas.IGUALDAD);
        System.out.println("Token para Diferencia (!=): " + Lexemas.DIFERENCIA);
        System.out.println("Token para Menor que (<): " + Lexemas.MENOR_QUE);
    }
}
