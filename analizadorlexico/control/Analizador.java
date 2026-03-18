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
        //StringBuilder sbIds = new StringBuilder();
        StringBuilder sbResultados = new StringBuilder();
        String regex = "([A-Za-z]\\w*)|(0|[1-9][0-9]*)|(==|!=|<=|>=|<|>|=)|(\\+|-|\\*|/)|(\\.|,|;|\\(|\\))";
        List<String> listaLexemas = new ArrayList<>();
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            // Buscador de identificadores
            Matcher matcher = patron.matcher(linea);
            while (matcher.find()) {
                String lexemaEncontrado = matcher.group();
                listaLexemas.add(lexemaEncontrado);
                if (matcher.group(1) != null) { // Es un Identificador
                    contador++;
                    sbResultados.append("ID: ").append(matcher.group(1)).append("\n");

                } else if (matcher.group(2) != null) { // Es un Número
                    contadorNumeros++;
                    sbResultados.append("NUM: ").append(matcher.group(2)).append("\n");

                }else if (matcher.group(3) != null) { // Op. Relacionales o Asignación
                    sbResultados.append("OP_REL/ASIG: ").append(matcher.group(3)).append("\n");
                } else if (matcher.group(4) != null) { // Op. Aritméticos
                    sbResultados.append("OP_ARIT: ").append(matcher.group(4)).append("\n");
                } else if (matcher.group(5) != null) { // Puntuación
                    sbResultados.append("PUNTUACION: ").append(matcher.group(5)).append("\n");
                }

                sbTexto.append(linea).append("\n");
            }

            textoProcesado = sbTexto.toString();
            identificadores = sbResultados.toString();
        }
        // Imprimir todos los lexemas separados y guardados en el ArrayList
        System.out.println("--- Lexemas guardados en el ArrayList ---");
        for (String lex : listaLexemas) {
            System.out.println(lex);
        }
    }

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
