/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

/**
 *
 * @author franco
 */
public final class CargadorDeListaDeComandos {
    private ListaComandos lCmd = null;

    /**
     *
     * @param lCmd
     */
    public CargadorDeListaDeComandos(ListaComandos lCmd){
        this.lCmd = lCmd;        
    }
    
    /**
     *
     * @param lista
     * @return
     */
    public boolean procesar(String lista){
        //TODO: Procesar el archivo de listas
        return false;
    }   
}
          

