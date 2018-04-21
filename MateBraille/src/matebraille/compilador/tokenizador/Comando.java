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
    private String literal = "";
    private int aridad = 0;
    private TipoCmd tipo = TipoCmd.SIMB;
    private categoriaCmd cate = categoriaCmd.SIMB_ESPECIAL;
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
     * @param tipo TipoCmd de comando. 
     * @param cate Categoria semántica
     */
     public Comando(String literal,int aridad, TipoCmd tipo, categoriaCmd cate) {
        this.literal = literal;
        this.aridad = aridad;
        this.tipo = tipo;
        this.cate = cate;
        
    }
     public Comando(String literal,int aridad, TipoCmd tipo) {
        this.literal = literal;
        this.aridad = aridad;
        this.tipo = tipo;
        
    }

    public Comando() {
        
    }
    /**
     *
     * @return
     */
    public String getLiteral() {
        return this.literal;
    }
    
    public categoriaCmd getCategoria(){
        return this.cate;
    }

    /**
     *
     * @return
     */
    public int getAridad() {
        return aridad;
    }

    /**
     *
     * @return
     */
    public TipoCmd getTipo() {
        return tipo;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public void setAridad(int aridad) {
        this.aridad = aridad;
    }

    public void setTipo(TipoCmd tipo) {
        this.tipo = tipo;
    }

    public void setCate(categoriaCmd cate) {
        this.cate = cate;
    }
}
