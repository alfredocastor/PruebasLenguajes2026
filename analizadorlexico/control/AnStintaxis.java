package analizadorlexico.control;

import java.util.ArrayList;

public class AnStintaxis {
    private ArrayList<String> lexemas; //en String deberia salir "lexema
    private int tok;
    public AnStintaxis(ArrayList<String> lexemas) {
        this.lexemas = lexemas;
    }
    public static void getLexemasDeFuera(ArrayList<String> lexemas) {
        //this.lexemas = lexemas;
    }
    private int getNextToken(){
//aqui va el codigo para obtener el token el elemento 
//actual del ArrayList lexemas
return 0;
    }
    public void programa(){
        tok=getNextToken(); 
        bloque();
        if (tok!=32){ //32 deberia ser la constante .  if (tok != Lex.PUNTO){
            System.out.println("Error");
        }else {
            System.out.println("compilacion exitosa");
        }
    }
    public void bloque(){
        //aqui va el codigo que corresponde a bloque
    }
    //GLL
    /*  
    <Termino_Resto -> * <Factor><Termino_Resto>>
    <Termino_Resto -> / <Factor><Termino_Resto>>
    <Termino_Resto -> NULL
    
    First(Termino_Resto) = {*, /, NULL}
    */
   
   public void terminoResto(){
    //if (firstTerminoResto.countains(tok)
    if (!(tok == 5 || tok == 6)) {
        return; // NULL
    }
    tok = getNextToken();
    factor();
    terminoResto();
    
   }
   public void expresion(){
   // aqui va el codigo que corresponde a expresion
   }
   /*
   <Factor -> id   
   <Factor -> num
   <Factor -> ( <Expresion> )   
    */
   public void factor(){
    switch (tok) {
        case 100: //id 
        case 200: //num   
        tok=getNextToken();
        break;
        case 69:
            tok=getNextToken();
            expresion();  
            if (tok != 70) {
                //Errores.showError(12, tok, lexemas.get(idx), linea); buen manejo de errores
                System.out.println("Error: Se esperaba )");
                return;
            }  
            tok=getNextToken();
            default:    
            System.out.println("Error: Se esperaba First(Factor)");    
    }
   }
}
