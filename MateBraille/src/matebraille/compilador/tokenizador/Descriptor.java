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
public class Descriptor {
    private String contenido,descripcion,id;

    public Descriptor(String id, String contenido, String descripcion) {
        this.contenido = contenido;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }


    public String getDescripcion() {
        return descripcion;
    }    
}
