package analizadorlexico.control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Analizador {

    private int contador;
    private String textoProcesado;
    private String identificadores;
    private int contadorNumeros;
    private String numeros;

   public void analizarCodigo(String codigoOriginal) {
        contador = 0;
        contadorNumeros = 0; // Reiniciamos contador de números
        StringBuilder sbTexto = new StringBuilder();
        StringBuilder sbResultados = new StringBuilder(); // <-- Regresamos el constructor para la ventana
        
        // La expresión regular con tu regla específica para los números
        String regex = "([A-Za-z]\\w*)|(0|[1-9][0-9]*)|(==|!=|<=|>=|<|>|=)|(\\+|-|\\*|/)|(\\.|,|;|\\(|\\))";
        List<String> listaLexemas = new ArrayList<>();
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);
            
            while (matcher.find()) {
                String lexemaEncontrado = matcher.group();
                listaLexemas.add(lexemaEncontrado);

                if (matcher.group(1) != null) { // Es un Identificador -> SE VA A LA VENTANA
                    contador++;
                    sbResultados.append("ID: ").append(matcher.group(1)).append("\n");
                } else if (matcher.group(2) != null) { // Es un Número -> SE VA A LA VENTANA
                    contadorNumeros++;
                    sbResultados.append("NUM: ").append(matcher.group(2)).append("\n");
                } else if (matcher.group(3) != null) { // Op. Relacionales o Asignación -> TERMINAL
                    System.out.println("OP_REL/ASIG: " + matcher.group(3));
                } else if (matcher.group(4) != null) { // Op. Aritméticos -> TERMINAL
                    System.out.println("OP_ARIT: " + matcher.group(4));
                } else if (matcher.group(5) != null) { // Puntuación -> TERMINAL
                    System.out.println("PUNTUACION: " + matcher.group(5));
                }

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
}
