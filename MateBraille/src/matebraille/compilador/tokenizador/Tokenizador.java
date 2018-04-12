/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import java.util.ArrayList;

/**
 *
 * @author franco
 * Esta Clase se encarga de analizar los lexemas del codigo latex simplificado.
 */
public final class Tokenizador {

    private final ListaComandos listaCmd;
    private final String codigo;
    private ArrayList<ResultadoTokenizador> resultado;
    /**
     *Constuctor básico, inicializa.
     */
    public Tokenizador(ListaComandos listaCmd,String codigo){
        this.listaCmd = listaCmd;
        this.codigo = codigo;
    }
    
    public ArrayList<ResultadoTokenizador> iniciarProceso(){
        
        return resultado;
    }
    
    private ResultadoTokenizador esVacio(){ 
        if(codigo.equals("")){
            
        } 
    }
    
}
