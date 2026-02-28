package pruebacom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {
    private int contador;
    private String textoProcesado;
    private String identificadores;

    public void analizarCodigo(String codigoOriginal) {
        contador = 0;
        StringBuilder sbTexto = new StringBuilder();
        StringBuilder sbIds = new StringBuilder();

        String regex = "[A-Za-z]\\w*";
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);
            while (matcher.find()) {
                contador++;
                sbIds.append(matcher.group()).append("\n"); // uno por línea
            }
            sbTexto.append(linea).append("\n");
        }

        textoProcesado = sbTexto.toString();
        identificadores = sbIds.toString();
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
}