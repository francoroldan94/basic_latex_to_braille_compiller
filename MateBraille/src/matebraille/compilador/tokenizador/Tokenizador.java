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
    private ResultadoTokenizador res;
    /**
     *Constuctor básico, inicializa.
     */
    public Tokenizador(ListaComandos listaCmd,String codigo){
        this.listaCmd = listaCmd;
        this.codigo = codigo;
    }
    
    public ResultadoTokenizador iniciarProceso(){
        res = new ResultadoTokenizador();
        //Sucesión de procedimientos del tokenizador
        esVacio();
        return res;
    }
    
    private boolean esVacio(){ 
        if(codigo.equals("")){
            InfoSintaxis info = new InfoSintaxis();
            info.setInformación("Código vacío.");
            info.setColumna(0);
            info.setFila(0);
            res.getErrores().add(info);
            return false;
        } 
        return true;
    }
    
}
