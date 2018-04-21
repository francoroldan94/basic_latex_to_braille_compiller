/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

/**
 * @author franco Tipos de lexemas
 */
public enum TipoCmd {
    /**
     * Comando prefijo. Por ejemplo "\frac".
     */
    PREFIJO,
    /**
     * Comando infijo. Por ejemplo "\choose".
     */
    INFIJO,
    /**
     * Llave apertura.
     */
    L_INI,
    /**
     * Llave cierre.
     */
    L_FIN,
    /**
     * Simbolos como por ejemplo "\pi"
     */
    SIMB,
    /**
     * Signo "$"
     */
    MODO_MATE,
    /**
     * Signo "$$"
     */
    MODO_MATE_2,
    /**
     * Corchete [
     */
    CORCH_INI,
    /**
     * Corchete ]
     */
    CORCH_FIN,
    
    SUB,
    
    SUPRA, 
    
    ESPACIO,
    
    ENTER
}
