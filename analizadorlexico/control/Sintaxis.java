/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorlexico.control;

import java.util.ArrayList;

/**
 *
 * @author Alfredo
 */
public class Sintaxis {
    ArrayList<Lexema> lexemas;
    
    public Sintaxis(ArrayList<Lexema> lexemas) {
        this.lexemas = lexemas;
    }
    public void programa(){
        System.out.println("Ya estamos en sintaxis");
        recorreLex();
        
    }
    private void recorreLex(){
        for(Lexema l: lexemas){
            System.out.println(l);
        }
    }
    
            //Todos los metodos inican con letra miniscula
}
