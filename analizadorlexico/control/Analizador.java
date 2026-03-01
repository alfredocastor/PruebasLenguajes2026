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
        StringBuilder sbIds = new StringBuilder();
        StringBuilder sbNumeros = new StringBuilder(); // Para guardar los números

        String regexId = "[A-Za-z]\\w*";
        Pattern patronId = Pattern.compile(regexId);
        String regexNum = "(0|[1-9][0-9]*)";
        Pattern patronNum = Pattern.compile(regexNum);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            // Buscador de identificadores
            Matcher matcherId = patronId.matcher(linea);
            while (matcherId.find()) {
                contador++;
                sbIds.append(matcherId.group()).append("\n");
            }
            
            // Buscador de números
            Matcher matcherNum = patronNum.matcher(linea);
            while (matcherNum.find()) {
                contadorNumeros++;
                sbNumeros.append(matcherNum.group()).append("\n");
            }

            sbTexto.append(linea).append("\n");
        }

        textoProcesado = sbTexto.toString();
        identificadores = sbIds.toString();
        numeros = sbNumeros.toString(); // Guardamos los números encontrados
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