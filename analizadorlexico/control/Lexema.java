package analizadorlexico.control;


public class Lexema {
  private String dato;  
  private String tipo;
  private int token; 
  //constructor
    public Lexema(String dato, String tipo) {
        this.dato = dato;
        this.tipo = tipo;
        
        //cambios aun no aplicados al principal
        switch (tipo) {
        case "ID":
            this.token = ALexico.esReservada(dato);
            this.tipo = (this.token == ALexico.IDENTIFICADOR) ? "ID" : "PR";
            break;
            
        case "NUM":
            this.token = ALexico.NUMERO;
            break;
            
        case "ERROR":
            this.token = 0;
            break;
            
        default:
            // El caso 'default' actúa como tu último 'else'
            this.tipo = "SIGNO";
            this.token = ALexico.obtenerTokenOp(dato);
            break;
    }
    }
   
    //getters y setters
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
    
    //to string
    
    @Override
    public String toString() {
        return "[" + dato + "\t" + tipo + "\t" + token + "]";
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
   

}
