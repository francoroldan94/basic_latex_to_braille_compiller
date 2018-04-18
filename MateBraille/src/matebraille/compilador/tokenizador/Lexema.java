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
public class Lexema {

    private Comando cmd = null;
    private TipoLexema tipo = TipoLexema.ALFABETICO;
    private String valor = null;
    private int posicion;
////////////////////CONSTRUCTOR DE LEXEMAS PRIVADO (PARA FABRICAS)/////////////
    private Lexema(Comando cmd, TipoLexema tipo, String valor, int posicion) {
        this.cmd = cmd;
        this.tipo = tipo;
        this.valor = valor;
        this.posicion = posicion;
    }
    
///////////////////METODOS FÁBRICA DE LEXEMAS//////////////////////////////////
    public static Lexema nuevoLexemaAlfabetico(String valor, int posicion) {
        Lexema lexema = new Lexema(null,TipoLexema.ALFABETICO,valor,posicion);
        return lexema;
    }
    public static Lexema nuevoLexemaNumerico(String valor, int posicion) {
        Lexema lexema = new Lexema(null,TipoLexema.NUMERICO,valor,posicion);
        return lexema;
    }
    public static Lexema nuevoLexemaCmd(Comando cmd, int posicion) {
        Lexema lexema = new Lexema(cmd,TipoLexema.NUMERICO,null,posicion);
        return lexema;
    }
    public static Lexema nuevoLexemaSigno(String valor, int posicion) {
        Lexema lexema = new Lexema(null,TipoLexema.SIGNO,valor,posicion);
        return lexema;
    }
///////////////////////GETTERS//////////////////////////////////////////////////
    public Comando getCmd() {
        return cmd; 
    }

    public TipoLexema getTipo() {
        return tipo;
    }

    public String getValor() {
        if(this.tipo == TipoLexema.LATEX)
            return cmd.getLiteral();
        return this.valor;
    }

    public int getPosicion() {
        return posicion;
    }
    
}
