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
public class Comando {
    private String literal;
    private int aridad = 0;
    private Tipo tipo = Tipo.SIMB;
    /**
     * Creacion de comando simbólico. (aridad 0)
     * @param literal Comando literal.
     */
    public Comando(String literal) {
        this.literal = literal;
    }
    
    /**
     * Creación de comando compuesto o caracteres especiales. 
     * @param literal Valor literal del comando.
     * @param aridad Cantidad de subloques. En el caso de las "{,}" o  "$" es nula.
     * @param tipo Tipo de comando. 
     */
     public Comando(String literal,int aridad, Tipo tipo) {
        this.literal = literal;
        this.aridad = aridad;
        this.tipo = tipo;
    }
    
    public String getLiteral() {
        return literal;
    }
    public int getAridad() {
        return aridad;
    }
    public Tipo getTipo() {
        return tipo;
    }
}
