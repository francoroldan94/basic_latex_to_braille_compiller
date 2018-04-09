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
    private boolean esCmd = false;
    private String valor = null;
    private int posicion;
    /**
     * Declaración de lexema como una cadena de caracteres.
     * @param contenido Valor literal del lexema.
     * @param posicion Posición en el archivo a compilar.
     */
    public Lexema(String contenido, int posicion){
        this.valor = contenido;
        this.posicion = posicion;
    }
    /**
     * Declaración del lexema como un comando predefinido en la lista de comandos.
     * @param cmd Tipo de comando.
     * @param posicion Posición en el archivo a compilar.
     */
    public Lexema(Comando cmd, int posicion){
        this.posicion = posicion;
        esCmd = true;
        this.cmd = cmd;
    }

    /**
     *
     * @return
     */
    public Comando getCmd() {
        return cmd;
    }

    /**
     *
     * @return
     */
    public boolean isEsCmd() {
        return esCmd;
    }

    /**
     *
     * @return
     */
    public String getValor() {
        if(this.esCmd){
            return cmd.getLiteral();
        }
        else{
            return valor;
        }
    }

    /**
     *
     * @return
     */
    public int getPosicion() {
        return posicion;
    }
    
}
