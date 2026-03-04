package compilador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {
    private int contador;
    private String textoProcesado;
    public void analizarCodigo(String codigoOriginal) {
        contador=0;
        StringBuilder sb=new StringBuilder();
        String regex="[A-Za-z]\\w*";
        Pattern patron=Pattern.compile(regex);

        String[] lineas=codigoOriginal.split("\n");

        for (String linea:lineas) {
            Matcher matcher=patron.matcher(linea);
            while (matcher.find()){
                contador++;
            }
            String lineaModificada=linea.replaceAll(regex, "[$0]");
            sb.append(lineaModificada).append("\n");
        }
        textoProcesado=sb.toString();
    }
    public int getContador() {
        return contador;
    }
    public String getTextoProcesado() {
        return textoProcesado;
    }
}