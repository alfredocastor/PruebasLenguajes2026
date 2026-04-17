package analizadorlexico.control;


public class Lexema {
  private String dato;  
  private String tipo;
  private int token; 
  //constructor
    public Lexema(String dato, String tipo) {
        this.dato = dato;
        this.tipo = tipo;
        if (tipo.equals("ID")) {
           this.token = Estaticos.esReservada(dato);
            this.tipo =(token==100)? tipo:"PR";
            
        }else{
            this.token=0;
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
