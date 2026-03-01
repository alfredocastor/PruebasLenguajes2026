package analizadorlexico.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GestorArchivos {
    public String leerArchivo(File archivo)throws IOException {
        StringBuilder contenido=new StringBuilder();
        try (BufferedReader br=new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea=br.readLine())!=null) {
                contenido.append(linea).append("\n");
            }
        }
        return contenido.toString();
    }
}