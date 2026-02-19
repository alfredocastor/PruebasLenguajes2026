package pruebacom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {

    private int contador;
    private String textoProcesado;

    public void analizarCodigo(String codigoOriginal) {
        // Reiniciamos valores
        contador = 0;
        StringBuilder sb = new StringBuilder();

        // Regex: Empieza con letra, sigue con letras o números
        String regex = "[A-Za-z]\\w*"; 
        Pattern patron = Pattern.compile(regex);

        // Separamos por líneas para no perder el formato
        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);
            
            // 1. Contamos cuántos hay en esta línea
            while (matcher.find()) {
                contador++;
            }
            
            // 2. Reemplazamos lo encontrado por [texto]
            // $0 representa la palabra que encontró el regex
            String lineaModificada = linea.replaceAll(regex, "[$0]");
            sb.append(lineaModificada).append("\n");
        }

        this.textoProcesado = sb.toString();
    }

    public int getContador() {
        return contador;
    }

    public String getTextoProcesado() {
        return textoProcesado;
    }
}