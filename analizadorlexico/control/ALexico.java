/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

import java.util.ArrayList;

/**
 *
 * @author Alfredo
 */
public class ALexico {

  
    // Identificadores y Literales
    public static final int IDENTIFICADOR = 1;
    public static final int NUMERO = 2;
    //palabras reservadas
    public static final int CONST = 10;
    public static final int VAR = 11;
    public static final int PROCED = 12;
    public static final int BEGIN = 13;
    public static final int END = 14;
    public static final int WRITE = 15;
    public static final int READ = 16;
    public static final int CALL = 17;
    public static final int IF = 18;
    public static final int THEN = 19;
    public static final int WHILE = 20;
    public static final int DO = 21;
    public static final int FOR = 22;
    public static final int TO = 23;
    public static final int DOWN = 24;

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

    //palabras reservadas. estos son ejemplos de como poder hacerlos
    public static String[] palabrasReservadaString = {"Const","begin","for","while"};
    public static ArrayList<String> palabrasRes= new ArrayList<String>();
    public static void llenaPalRes(){
        palabrasRes.add("Do");
        palabrasRes.add("Then");
        palabrasRes.add("for");
        palabrasRes.add("while");
    }




}
