/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alfredo
 */
public class ALexico {

  
    // Identificadores y Literales
    public static final int IDENTIFICADOR = 1;
    public static final int NUMERO = 2;
    

    // Operadores Relacionales
    public static final int IGUALDAD = 30; // ==
    public static final int DIFERENCIA = 31; // !=
    public static final int MENOR_QUE = 32; // <
    public static final int MAYOR_QUE = 33; // >
    public static final int MENOR_IGUAL_QUE = 34; // <=
    public static final int MAYOR_IGUAL_QUE = 35; // >=

    // Operadores Aritméticos y Asignación
    public static final int ASIGNACION = 40; // =
    public static final int SUMA = 41; // +
    public static final int RESTA = 42; // -
    public static final int MULTIPLICACION = 43; // *
    public static final int DIVISION = 44; // /

    // Símbolos de Puntuación
    public static final int PUNTO = 50; // .
    public static final int COMA = 51; // ,
    public static final int PUNTO_Y_COMA = 52; // ;
    public static final int PARENTESIS_ABRE = 53; // (
    public static final int PARENTESIS_CIERRA = 54; // )
    
    //cambios aun no puestos en el principlas, quitar cuando se agreguen
    private static final Map<String, Integer> palabrasReservadas = new HashMap<>();
    static {
        palabrasReservadas.put("const", 10);
        palabrasReservadas.put("var", 11);
        palabrasReservadas.put("proced", 12);
        palabrasReservadas.put("begin", 13);
        palabrasReservadas.put("end", 14);
        palabrasReservadas.put("write", 15);
        palabrasReservadas.put("read", 16);
        palabrasReservadas.put("call", 17);
        palabrasReservadas.put("if", 18);
        palabrasReservadas.put("then", 19);
        palabrasReservadas.put("while", 20);
        palabrasReservadas.put("do", 21);
        palabrasReservadas.put("for", 22);
        palabrasReservadas.put("to", 23);
        palabrasReservadas.put("down", 24);
    }
    //palabras reservadas
     public static int esReservada(String palabra) {
       palabra = palabra.toLowerCase();
       return palabrasReservadas.getOrDefault(palabra, IDENTIFICADOR);
             }
      // Método auxiliar para obtener tokens de operadores
     public static int obtenerTokenOp(String op) {
        switch (op) {
            case "==": return IGUALDAD;
            case "!=": return DIFERENCIA;
            case "<": return MENOR_QUE;
            case ">": return MAYOR_QUE;
            case "<=": return MENOR_IGUAL_QUE;
            case ">=": return MAYOR_IGUAL_QUE;
            case "=": return ASIGNACION;
            case "+": return SUMA;
            case "-": return RESTA;
            case "*": return MULTIPLICACION;
            case "/": return DIVISION;
            case ".": return PUNTO;
            case ",": return COMA;
            case ";": return PUNTO_Y_COMA;
            case "(": return PARENTESIS_ABRE;
            case ")": return PARENTESIS_CIERRA;
            default: return 0;
        }
    }
}
