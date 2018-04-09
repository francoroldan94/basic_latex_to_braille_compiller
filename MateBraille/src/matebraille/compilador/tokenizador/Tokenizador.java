/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

/**
 *
 * @author franco
 * Esta Clase se encarga de analizar los lexemas del codigo latex simplificado.
 */
public final class Tokenizador {

    private final ListaComandos listaCmd;
    private final String codigo;

    /**
     *Constuctor básico, inicializa.
     */
    public Tokenizador(ListaComandos listaCmd,String codigo){
        this.listaCmd = listaCmd;
        this.codigo = codigo;
    }
    
    public ResultadoTokenizador iniciarProceso(){
        ///TODO: Proceso de compilación.
        throw new UnsupportedOperationException("Not supported yet.");        
    }
    
}
