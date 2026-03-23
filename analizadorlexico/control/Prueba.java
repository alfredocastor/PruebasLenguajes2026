package analizadorlexico.control;

public class Prueba {
    public static void main(String[] args) {
      //  System.out.println("Prueba de la clase Lexemas:");
        //System.out.println("Token para Identificador: " + Lexemas.IDENTIFICADOR);
        //System.out.println("Token para Número: " + Lexemas.NUMERO);
        //System.out.println("Token para Igualdad (==): " + Lexemas.IGUALDAD);
        //System.out.println("Token para Diferencia (!=): " + Lexemas.DIFERENCIA);
        //System.out.println("Token para Menor que (<): " + Lexemas.MENOR_QUE);
        System.out.println(Lexemas.palabrasReservadaString[0]);
        System.out.println(Lexemas.palabrasReservadaString[1]);
        System.out.println(Lexemas.palabrasReservadaString[2]);
        System.out.println(Lexemas.palabrasReservadaString[3]);
        if (Lexemas.palabrasReservadaString[0].equals("Const")) {
            System.out.println("son iguales");
        }//termina if equals
        Lexemas.llenaPalRes();
        if (Lexemas.palabrasRes.contains("Do")) {
            System.out.println("si lo contiene");
        }
    }
}
