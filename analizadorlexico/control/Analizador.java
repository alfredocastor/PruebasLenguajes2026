package analizadorlexico.control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList; 
import java.util.List; 
import javax.swing.JOptionPane;//agregue esto

public class Analizador {

    private int contador;
    private String textoProcesado;
    private String identificadores;
    private int contadorNumeros;
    private String numeros;
    private boolean hayErrores;

    public void analizarCodigo(String codigoOriginal) {
        contador = 0;
        contadorNumeros = 0;
        StringBuilder sbTexto = new StringBuilder();
        StringBuilder sbResultados = new StringBuilder();
        hayErrores = false;//agregue esto
        boolean ignorarErrores = false;//agregue esto

        // La expresión regular con tu regla específica para los números
       String regex = "([A-Za-z]\\w*)|([1-9]\\d*|0)|(==|!=|<=|>=|<|>|=)|(\\+|-|\\*)|(\\.|,|;|\\(|\\)|:)|([^\\s])"; //agregue esto
        List<String> listaLexemas = new ArrayList<>();
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);

            while (matcher.find()) {
                String lexemaEncontrado = matcher.group(); 
                listaLexemas.add(lexemaEncontrado); 

                if (matcher.group(1) != null) { // Es un Identificador
                    contador++;
                    sbResultados.append("ID: ").append(matcher.group(1)).append("\n");
                    System.out.println("ID: " + matcher.group(1)); //cambie este
                } else if (matcher.group(2) != null) { // Es un Número
                    contadorNumeros++;
                    sbResultados.append("NUM: ").append(matcher.group(2)).append("\n");
                    System.out.println("NUM: " + matcher.group(2)); //cambie este
                } else if (matcher.group(3) != null) { // Op. Relacionales o Asignación //este tambien se va agregar
                    sbResultados.append("OP_REL/ASIG: ").append(matcher.group(3)).append("\n"); //cambie este
                    System.out.println("OP_REL/ASIG: " + matcher.group(3));
                } else if (matcher.group(4) != null) { // Op. Aritméticos               //este tambien se va agregar
                    System.out.println("OP_ARIT: " + matcher.group(4));
                    sbResultados.append("OP_ARIT: ").append(matcher.group(4)).append("\n"); //cambie este
                } else if (matcher.group(5) != null) { // Puntuación                   //este tambien se va agregar
                    sbResultados.append("PUNTUACION: ").append(matcher.group(5)).append("\n"); //cambie este
                    System.out.println("PUNTUACION: " + matcher.group(5));
                }else if (matcher.group(6) != null) { // Es un Error (ej. % , /) //agregue esto
    hayErrores = true;
    if (!ignorarErrores) {
        // Preguntamos al usuario qué hacer
        int opcion = JOptionPane.showConfirmDialog(null, 
            "Error Léxico: Carácter no reconocido '" + matcher.group(6) + "'.\n¿Desea continuar y marcar todos los errores?", 
            "Error encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

        if (opcion == JOptionPane.NO_OPTION) {
            sbResultados.append("ANÁLISIS DETENIDO POR ERROR: ").append(matcher.group(6)).append("\n");
            break; // Rompe el ciclo y detiene el análisis
        } else {
            ignorarErrores = true; // Ya no volverá a preguntar, solo los marcará
        }
    }
    sbResultados.append("ERROR: [").append(matcher.group(6)).append("]\n");
    System.out.println("ERROR: " + matcher.group(6));
}//aqui termina el grupo 6

                sbTexto.append(linea).append("\n");
            }

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
    public boolean tieneErrores() {//agregue esto
    return hayErrores; //agregue esto
}//agregue esto
}
