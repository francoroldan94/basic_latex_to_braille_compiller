/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

/**
 * @author franco
 * Tipos de lexemas
 */
public enum Tipo {
    /**
     *Comando compuesto. Por ejemplo "\frac".
     */
    CMD,

    /**
     * Caracter.
     */
    CHR,

    /**
     *Llave apertura.
     */
    B_INI,

    /**
     * Llave cierre.
     */
    B_FIN,

    /**
     * Simbolos como por ejemplo "\pi"
     */
    SIMB
}
