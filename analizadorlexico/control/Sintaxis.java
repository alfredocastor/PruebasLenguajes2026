/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

import java.util.List;

/**
 *
 * @author Alfredo
 */
public class Sintaxis {
    private List<Lexema> lexemas; 
    private int indice;
    private Lexema lexemaActual;
    private int tok;

    public Sintaxis(List<Lexema> lexemas) {
        this.lexemas = lexemas;
        this.indice = -1;
    }

    private int getNextToken() {
        indice++;
        if (indice < lexemas.size()) {
            lexemaActual = lexemas.get(indice);
            return lexemaActual.getToken();
        }
        return -1; // Fin de archivo
    }

    private void error(String mensaje) {
        String dato = (lexemaActual != null) ? lexemaActual.getDato() : "EOF";
        System.out.println("Error Sintáctico cerca de '" + dato + "': " + mensaje);
    }

    // <programa> -> <Bloque> .
    public void programa() {
        tok = getNextToken();
        bloque();
        if (tok == ALexico.PUNTO) {
            System.out.println("Compilación exitosa."); 
        } else {
            error("Se esperaba '.' al final del programa.");
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
        if (tok == 10) { // const
            tok = getNextToken();
            cicl1();
            if (tok == ALexico.PUNTO_Y_COMA) {
                tok = getNextToken();
            } else { error("Se esperaba ';' después de las constantes."); }
        } 
    }

    // <cicl1> -> Id = Num <cicl1_prima>
    public void cicl1() {
        if (tok == ALexico.IDENTIFICADOR) {
            tok = getNextToken();
            if (tok == ALexico.ASIGNACION) {
                tok = getNextToken();
                if (tok == ALexico.NUMERO) {
                    tok = getNextToken();
                    cicl1Prima();
                } else { error("Se esperaba un valor numérico."); }
            } else { error("Se esperaba '='."); }
        } else { error("Se esperaba un identificador.");
        } 
    }

    public void cicl1Prima() {
        if (tok == ALexico.COMA) {
            tok = getNextToken();
            cicl1();
        }
    }

    // <Lin2> -> var <cicl2> ; | Ø
    public void lin2() {
        if (tok == 11) { // var
            tok = getNextToken();
            cicl2();
            if (tok == ALexico.PUNTO_Y_COMA) {
                tok = getNextToken();
            } else { error("Se esperaba ';' después de las variables."); }
        } 
    }

    public void cicl2() {
        if (tok == ALexico.IDENTIFICADOR) {
            tok = getNextToken();
            cicl2Prima();
        } 
    }

    public void cicl2Prima() {
        if (tok == ALexico.COMA) {
            tok = getNextToken();
            cicl2();
        } 
    }

    // <Lin3> -> Proced Id ; <Bloque> ; <Lin3> | Ø
    public void lin3() {
        if (tok == 12) { // proced
            tok = getNextToken();
            if (tok == ALexico.IDENTIFICADOR) {
                tok = getNextToken();
                if (tok == ALexico.PUNTO_Y_COMA) {
                    tok = getNextToken();
                    bloque();
                    if (tok == ALexico.PUNTO_Y_COMA) {
                        tok = getNextToken();
                        lin3();
                    } else { error("Se esperaba ';' después del bloque del procedimiento."); }
                } else { error("Se esperaba ';' después del nombre del procedimiento."); }
            } else { error("Se esperaba un nombre para el procedimiento."); }
        } 
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
            default: error("Inicio de proposición no válido.");
        }
    }

    public void linBeg() {
        tok = getNextToken();
        ciclProp();
        if (tok == 14) { // end
            tok = getNextToken();
        } else { error("Se esperaba 'end'."); 
        } 
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
        if (tok == ALexico.ASIGNACION) {
            tok = getNextToken();
            expresion();
        } else { error("Se esperaba '='."); } 
    }

    public void linWrite() {
        tok = getNextToken();
        disNum(); 
    }

    public void disNum() {
        if (tok == ALexico.IDENTIFICADOR || tok == ALexico.NUMERO) {
            tok = getNextToken();
        } else { error("Se esperaba ID o NUM."); 
        } 
    }

    public void linRead() {
        tok = getNextToken();
        if (tok == ALexico.IDENTIFICADOR) {
            tok = getNextToken();
        } else { error("Se esperaba un identificador para lectura."); 
        } 
        
    }

    public void linCall() {
        tok = getNextToken();
        if (tok == ALexico.IDENTIFICADOR) {
            tok = getNextToken();
        } else { error("Se esperaba un nombre de procedimiento."); 
        } 
    }

    public void linIf() {
        tok = getNextToken();
        condicion();
        if (tok == 19) { // then
            tok = getNextToken();
            proposicion();
        } else { error("Se esperaba 'then'.");
        } 
    }

    public void linWhile() {
        tok = getNextToken();
        condicion();
        if (tok == 21) { // do
            tok = getNextToken();
            proposicion();
        } else { error("Se esperaba 'do'."); 
        } 
    }

    public void linFor() {
        tok = getNextToken();
        if (tok == ALexico.IDENTIFICADOR) {
            tok = getNextToken();
            if (tok == ALexico.ASIGNACION) {
                tok = getNextToken();
                expresion();
                disDown();
                expresion();
                if (tok == 21) { // do
                    tok = getNextToken();
                    proposicion();
                } else { error("Se esperaba 'do'."); }
            } else { error("Se esperaba '='."); }
        } else { error("Se esperaba un identificador."); 
        } 
    }

    public void disDown() {
        if (tok == 23 || tok == 24) { // to | down
            tok = getNextToken();
        } else { error("Se esperaba 'to' o 'down'."); 
        } 
    }

    public void condicion() {
        expresion();
        mult();
        expresion(); 
    }

    public void mult() {
        if (tok >= 30 && tok <= 35) { // ==, !=, <, >, <=, >=
            tok = getNextToken();
        } else { error("Se esperaba un operador relacional."); 
        }
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
                if (tok == ALexico.PARENTESIS_CIERRA) {
                    tok = getNextToken();
                } else { error("Se esperaba ')'."); }
                break;
            default: error("Se esperaba identificador, número o '('."); 
        }
    }
}
