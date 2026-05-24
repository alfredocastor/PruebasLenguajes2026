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
public class ASintaxis {
    ArrayList <Lexema> lexema; 
    private int indice;
    private Lexema lexemaActual;
    private int tok;
    
    private StringBuilder mensajesSintaxis;
    private boolean hayErroresSintacticos;

    public ASintaxis(ArrayList<Lexema> lexema) {
        this.lexema = lexema;
        this.indice = -1;
        this.mensajesSintaxis = new StringBuilder();
        this.hayErroresSintacticos= false;
    }
    public String getMensajesSintaxis() {
        return mensajesSintaxis.toString();
    }

    private int getNextToken() {
        indice++;
        if (indice < lexema.size()) {
            lexemaActual = lexema.get(indice);
            return lexemaActual.getToken();
        }
        return -1; // Fin de archivo
    }

    private void error(String mensaje) {
        hayErroresSintacticos = true;
        String dato = (lexemaActual != null) ? lexemaActual.getDato() : "EOF";
       String textoError = "Error Sintactico cerca de '" + dato + "': " + mensaje;
       System.out.println(textoError);
        mensajesSintaxis.append(textoError).append("\n");
    }

    // <programa> -> <Bloque> .
    public void programa() {
        tok = getNextToken();
        bloque();
        
        if (tok != ALexico.PUNTO) {
            error("Se esperaba '.' al final del programa");
            
        }
        if(!hayErroresSintacticos){
       String mensajeExito = ("Analisis Sintactico completado con exito. El codigo fuente cumple con la gramatica"); 
       System.out.println(mensajeExito);
        mensajesSintaxis.append(mensajeExito).append("\n");
        }
    }

    // <Bloque> -> <Lin1> <Lin2> <Lin3> <proposicion>
    public void bloque() {
        lin1();
        lin2();
        lin3();
        proposicion(); 
    }

    // <Lin1> -> const <cicl1> ; | Ø
    public void lin1() {
        if (tok != 10) return; // const 

        tok = getNextToken();
        cicl1();
        
        if (tok != ALexico.PUNTO_Y_COMA) {
            error("Se esperaba ';' despues de las constantes");
            return;
        }
        tok = getNextToken();
    }

    // <cicl1> -> Id = Num <cicl1_prima>
    public void cicl1() {
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un identificador");
            return;
        }
        
        tok = getNextToken();
        if (tok != ALexico.ASIGNACION) {
            error("Se esperaba '='");
            return;
        }
        
        tok = getNextToken();
        if (tok != ALexico.NUMERO) {
            error("Se esperaba un valor numerico");
            return;
        }
        
        tok = getNextToken();
        cicl1Prima();
    }

    public void cicl1Prima() {
        if (tok == ALexico.COMA) {
            tok = getNextToken();
            cicl1();
        }
    }

    // <Lin2> -> var <cicl2> ; | Ø
    public void lin2() {
        if (tok != 11) return; // var 

        tok = getNextToken();
        cicl2();
        
        if (tok != ALexico.PUNTO_Y_COMA) {
            error("Se esperaba ';' despues de las variables");
            return;
        }
        tok = getNextToken();
    }

    public void cicl2() {
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un identificador");
            return;
        }
        tok = getNextToken();
        cicl2Prima();
    }

    public void cicl2Prima() {
        if (tok == ALexico.COMA) {
            tok = getNextToken();
            cicl2();
        } 
    }

    // <Lin3> -> Proced Id ; <Bloque> ; <Lin3> | Ø
    public void lin3() {
        if (tok != 12) return; // proced 
        tok = getNextToken();
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un nombre para el procedimiento");
            return;
        }
        
        tok = getNextToken();
        if (tok != ALexico.PUNTO_Y_COMA) {
            error("Se esperaba ';' después del nombre del procedimiento");
            return;
        }
        
        tok = getNextToken();
        bloque();
        
        if (tok != ALexico.PUNTO_Y_COMA) {
            error("Se esperaba ';' después del bloque del procedimiento");
            return;
        }
        
        tok = getNextToken();
        lin3();
    }

    public void proposicion() {
        multLin(); 
    }

    public void multLin() {
        switch (tok) {
            case 13: linBeg(); break;   // begin
            case 15: linWrite(); break; // write
            case 16: linRead(); break;  // read
            case 17: linCall(); break;  // call
            case 18: linIf(); break;    // if
            case 20: linWhile(); break; // while
            case 22: linFor(); break;   // for
            case ALexico.IDENTIFICADOR: linExp(); break;
            default: error("Inicio de proposicion no valido");
        }
    }

    public void linBeg() {
        tok = getNextToken();
        ciclProp();
        
        if (tok != 14) { // end
            error("Se esperaba 'end'"); 
            return;
        }
        tok = getNextToken();
    }

    public void ciclProp() {
        proposicion();
        ciclPropPrima(); 
    }

    public void ciclPropPrima() {
        if (tok == ALexico.PUNTO_Y_COMA) {
            tok = getNextToken();
            ciclProp();
        } 
    }

    public void linExp() {
        tok = getNextToken();
        
        if (tok != ALexico.ASIGNACION) {
            error("Se esperaba '='");
            return;
        }
        
        tok = getNextToken();
        expresion();
    }

    public void linWrite() {
        tok = getNextToken();
        disNum(); 
    }

    public void disNum() {
        if (tok != ALexico.IDENTIFICADOR && tok != ALexico.NUMERO) {
            error("Se esperaba ID o NUM"); 
            return;
        }
        tok = getNextToken();
    }

    public void linRead() {
        tok = getNextToken();
        
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un identificador para lectura"); 
            return;
        }
        tok = getNextToken();
    }

    public void linCall() {
        tok = getNextToken();
        
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un nombre de procedimiento."); 
            return;
        }
        tok = getNextToken();
    }

    public void linIf() {
        tok = getNextToken();
        condicion();
        
        if (tok != 19) { // then
            error("Se esperaba 'then'");
            return;
        }
        
        tok = getNextToken();
        proposicion();
    }

    public void linWhile() {
        tok = getNextToken();
        condicion();
        
        if (tok != 21) { // do
            error("Se esperaba 'do'"); 
            return;
        }
        
        tok = getNextToken();
        proposicion();
    }

    public void linFor() {
        tok = getNextToken();
        
        if (tok != ALexico.IDENTIFICADOR) {
            error("Se esperaba un identificador"); 
            return;
        }
        
        tok = getNextToken();
        if (tok != ALexico.ASIGNACION) {
            error("Se esperaba '='");
            return;
        }
        
        tok = getNextToken();
        expresion();
        disDown();
        expresion();
        
        if (tok != 21) { // do
            error("Se esperaba 'do'");
            return;
        }
        
        tok = getNextToken();
        proposicion();
    }

    public void disDown() {
        if (tok != 23 && tok != 24) { // to | down
            error("Se esperaba 'to' o 'down'"); 
            return;
        }
        tok = getNextToken();
    }

    public void condicion() {
        expresion();
        mult();
        expresion(); 
    }

    public void mult() {
        if (tok < 30 || tok > 35) { // ==, !=, <, >, <=, >=
            error("Se esperaba un operador relacional"); 
            return;
        }
        tok = getNextToken();
    }

    public void expresion() {
        termino();
        expPrima(); 
    }

    public void expPrima() {
        if (tok == ALexico.SUMA || tok == ALexico.RESTA) {
            tok = getNextToken();
            expresion();
        } 
    }

    public void termino() {
        factor();
        terminoPrima(); 
    }

    public void terminoPrima() {
        if (tok == ALexico.MULTIPLICACION || tok == ALexico.DIVISION) {
            tok = getNextToken();
            termino();
        } 
    }

    public void factor() {
        switch (tok) {
            case ALexico.IDENTIFICADOR:
            case ALexico.NUMERO:
                tok = getNextToken();
                break;
            case ALexico.PARENTESIS_ABRE:
                tok = getNextToken();
                expresion();
                if (tok != ALexico.PARENTESIS_CIERRA) {
                    error("Se esperaba ')'");
                    return;
                }
                tok = getNextToken();
                break;
            default: 
                error("Se esperaba identificador, numero o '('");
        }
    }
}