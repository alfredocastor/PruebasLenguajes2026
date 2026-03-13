/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

/**
 *
 * @author Alfredo
 */
public class Lexemas {

  
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



}
