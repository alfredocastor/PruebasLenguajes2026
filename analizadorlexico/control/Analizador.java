package analizadorlexico.control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String regex = "([A-Za-z]\\w*)|(0|[1-9][0-9]*)";
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            // Buscador de identificadores
            Matcher matcher = patron.matcher(linea);
            while (matcher.find()) {
                if (matcher.group(1) != null) { // Es un Identificador
                    contador++;
                    sbResultados.append("ID: ").append(matcher.group(1)).append("\n");

                } else if (matcher.group(2) != null) { // Es un Número
                    contadorNumeros++;
                    sbResultados.append("NUM: ").append(matcher.group(2)).append("\n");

                }

                sbTexto.append(linea).append("\n");
            }

            textoProcesado = sbTexto.toString();
            identificadores = sbResultados.toString();
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
