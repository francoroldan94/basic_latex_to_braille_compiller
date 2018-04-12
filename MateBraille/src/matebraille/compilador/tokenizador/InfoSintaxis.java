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
    private String contenido;
    private String información;
        
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }



    public int getPosicion() {
        return posicion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getInformación() {
        return información;
    }

    public void setInformación(String información) {
        this.información = información;
    }


}
