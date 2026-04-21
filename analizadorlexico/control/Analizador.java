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

    public void analizarCodigo(String codigoOriginal) {
        contador = 0;
        contadorNumeros = 0;
        StringBuilder sbTexto = new StringBuilder();
        StringBuilder sbResultados = new StringBuilder();
        hayErrores = false;
        boolean ignorarErrores = false;

        String regex = "([A-Za-z]\\w*)|" +//
                "([1-9]\\d*|0)|" +//
                "(==|!=|<=|>=|<|>|=|\\+|-|\\*|/)|" +//
                "(\\.|,|;|\\(|\\)|:)|"+//
                "(\\s)|" +//
                "(.)";
        List<Lexema> listaLexemas = new ArrayList<>();
        Pattern patron = Pattern.compile(regex);

        String[] lineas = codigoOriginal.split("\n");

        for (String linea : lineas) {
            Matcher matcher = patron.matcher(linea);

            while (matcher.find()) {
                String lexemaEncontrado = matcher.group();

                if (matcher.group(1) != null) { // Es un Identificador o Palabra Reservada
                    contador++;
                   listaLexemas.add(new Lexema(lexemaEncontrado,"ID"));
                    continue; 
                } 
                
                if (matcher.group(2) != null) { // Es un Número
                    contadorNumeros++;
                   listaLexemas.add(new Lexema(lexemaEncontrado,"NUM"));
                    continue; 
                }
                
                if (matcher.group(3) != null) { // Op. Relacionales o Asignación
                   listaLexemas.add(new Lexema(lexemaEncontrado,"SIGNO"));
                    continue; 
                } 
                
                if (matcher.group(4) != null) { // Op. Aritméticos
                   listaLexemas.add(new Lexema(lexemaEncontrado,"SIGNO"));
                    continue; 
                } 
                
                if (matcher.group(5) != null) { // espacios en blanco 
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
                    listaLexemas.add(new Lexema(lexemaEncontrado,"ERROR"));
                }
            }//fin while
            sbTexto.append(linea).append("\n");
        }
         for (Lexema e : listaLexemas) {
            System.out.println(e);
            sbResultados.append(e.toString()).append("\n");
         }
          textoProcesado = sbTexto.toString();
          identificadores = sbResultados.toString();
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

    public boolean tieneErrores() {
        return hayErrores; 
    }
}
