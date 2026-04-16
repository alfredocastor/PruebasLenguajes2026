package analizadorlexico.control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Analizador {

    private int contador;
    private String textoProcesado;
    private String identificadores;
    private int contadorNumeros;
    private String numeros;
    private boolean hayErrores;
    // Método auxiliar para enumerar palabras reservadas
    private int esReservada(String palabra) {
        switch (palabra.toLowerCase()) {
            case "const": return 10;
            case "var": return 11;
            case "proced": return 12;
            case "begin": return 13;
            case "end": return 14;
            case "write": return 15;
            case "read": return 16;
            case "call": return 17;
            case "if": return 18;
            case "then": return 19;
            case "while": return 20;
            case "do": return 21;
            case "for": return 22;
            case "to": return 23;
            case "down": return 24;
            default: return 1; // ID - 1
        }
    }

    // Método auxiliar para obtener tokens de operadores
    private int obtenerTokenOp(String op) {
        switch (op) {
            case "==": return 30;
            case "!=": return 31;
            case "<": return 32;
            case ">": return 33;
            case "<=": return 34;
            case ">=": return 35;
            case "=": return 40;
            case "+": return 41;
            case "-": return 42;
            case "*": return 43;
            case "/": return 44;
            default: return 0;
        }
    }

    public void analizarCodigo(String codigoOriginal) {
        contador = 0;
        contadorNumeros = 0;
        StringBuilder sbTexto = new StringBuilder();
        StringBuilder sbResultados = new StringBuilder();
        hayErrores = false;
        boolean ignorarErrores = false;

        // La expresión regular con tu regla específica para los números
        String regex = "([A-Za-z]\\w*)|" +//
                "([1-9]\\d*|0)|" +//
                "(==|!=|<=|>=|<|>|=)|" +//
                "(\\+|-|\\*|/)|" +//
                "(\\.|,|;|\\(|\\)|:)|"+//
                "([^\\s])" +//
                "(.)";
        List<String> listaLexemas = new ArrayList<>();
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);

            while (matcher.find()) {
                String lexemaEncontrado = matcher.group();
                listaLexemas.add(lexemaEncontrado);

                if (matcher.group(1) != null) { // Es un Identificador o Palabra Reservada
                    contador++;
                    int token = esReservada(lexemaEncontrado);
                    String tipo = (token == 1) ? "ID" : "PR";
                    String out = String.format("[%s\t%s\t%d]\n", lexemaEncontrado, tipo, token);
                    sbResultados.append(out);
                    System.out.print(out);
                    continue; 
                } 
                
                if (matcher.group(2) != null) { // Es un Número
                    contadorNumeros++;
                    String out = String.format("[%s\tNUM\t2]\n", lexemaEncontrado); // Token NUM es 2
                    sbResultados.append(out);
                    System.out.print(out);
                    continue; 
                } 
                
                if (matcher.group(3) != null) { // Op. Relacionales o Asignación
                    int token = obtenerTokenOp(lexemaEncontrado);
                    String out = String.format("[%s\tOP_REL/ASIG\t%d]\n", lexemaEncontrado, token);
                    sbResultados.append(out);
                    System.out.print(out);
                    continue; 
                } 
                
                if (matcher.group(4) != null) { // Op. Aritméticos
                    int token = obtenerTokenOp(lexemaEncontrado);
                    String out = String.format("[%s\tOP_ARIT\t%d]\n", lexemaEncontrado, token);
                    sbResultados.append(out);
                    System.out.print(out);
                    continue; 
                } 
                
                if (matcher.group(5) != null) { // Puntuación 
                    String out = String.format("[%s\tPUNTUACION\t0]\n", lexemaEncontrado);
                    sbResultados.append(out);
                    System.out.print(out);
                    continue; 
                } 
                
                if (matcher.group(6) != null) { // Es un Error
                    hayErrores = true;
                    if (!ignorarErrores) {
                        int opcion = JOptionPane.showConfirmDialog(null,
                                "Error Léxico: Carácter no reconocido '" + matcher.group(6) + "'.\n¿Desea continuar y marcar todos los errores?",
                                "Error encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                        if (opcion == JOptionPane.NO_OPTION) {
                            sbResultados.append("ANÁLISIS DETENIDO POR ERROR: ").append(matcher.group(6)).append("\n");
                            break; 
                        } else {
                            ignorarErrores = true; 
                        }
                    }
                    String out = String.format("[%s\tError\t0]\n", matcher.group(6));
                    sbResultados.append(out);
                    System.out.print(out);
                    continue;
                }
            }
            sbTexto.append(linea).append("\n");
            textoProcesado = sbTexto.toString();
            identificadores = sbResultados.toString();
        }
    }// <-- Le pasamos los textos guardados a la variable de

    public int getContador() {
        return contador;
    }

    public String getTextoProcesado() {
        return textoProcesado;
    }

    public String getIdentificadores() {
        return identificadores;
    }

    public int getContadorNumeros() {
        return contadorNumeros;
    }

    public String getNumeros() {
        return numeros;
    }

    public boolean tieneErrores() {
        return hayErrores; 
    }
}
