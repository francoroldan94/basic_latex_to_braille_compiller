/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import java.util.ArrayList;

/**
 * Informa el resultado de la compilación
 *
 * @author franco
 */
public class ResultadoTokenizador {

    /**
     * Lexemas de salida
     */
    private ArrayList<Lexema> lexemas = null;
    /**
     * Lista de errores
     */
    private ArrayList<InfoSintaxis> errores = null;
    /**
     * Lista de Advertencias
     */
    private ArrayList<InfoSintaxis> advertencias = null;

    public ResultadoTokenizador() {
        lexemas = new ArrayList<>();
        errores = new ArrayList<>();
        advertencias = new ArrayList<>();

    }

    public ArrayList<Lexema> getLexemas() {
        return lexemas;
    }


    public ArrayList<InfoSintaxis> getErrores() {           
        return errores;
    }

    public ArrayList<InfoSintaxis> getAdvertencias() {
        return advertencias;
    }
    public void nuevoError(InfoSintaxis info){
        errores.add(info);
    }
    public void nuevaAdvertencia(InfoSintaxis info){
        advertencias.add(info);
    }
    public void nuevoLexema(Lexema lexema){
        lexemas.add(lexema);
    }
}
