package analizadorlexico.control;

public class Prueba {
    public static void main(String[] args) {
        System.out.println("Prueba de la clase Lexemas:");
        System.out.println("Token para Identificador: " + Lexemas.Tokens.IDENTIFICADOR);
        System.out.println("Token para Número: " + Lexemas.Tokens.NUMERO);
        System.out.println("Token para Igualdad (==): " + Lexemas.Tokens.IGUALDAD);
        System.out.println("Token para Diferencia (!=): " + Lexemas.Tokens.DIFERENCIA);
        System.out.println("Token para Menor que (<): " + Lexemas.Tokens.MENOR_QUE);
    }
}
