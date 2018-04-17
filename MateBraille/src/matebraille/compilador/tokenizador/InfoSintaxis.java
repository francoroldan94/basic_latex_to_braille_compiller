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
public class InfoSintaxis {

    private int posicion = -1, fila = 0, columna = 0;
    private Descriptor descriptor;

    /**
     *
     * @param contenido
     * @param información
     * @param posicion
     * @param fila
     * @param columna
     */
    public InfoSintaxis(Descriptor descriptor,int posicion, int fila, int columna){
        this.descriptor = descriptor;
        this.posicion = posicion;
        this.fila = fila;
        this.columna = columna; 
    }

    public int getPosicion() {
        return posicion;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }
    


}
