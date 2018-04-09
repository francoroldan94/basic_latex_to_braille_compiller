/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import java.util.ArrayList;

/**
 *Informa el resultado de la compilación
 * @author franco
 */
public class ResultadoTokenizador {
    private final ArrayList<Lexema> resultado = null;
    private final ArrayList<InfoSintaxis> errores = null;
    private final ArrayList<InfoSintaxis> advertencias = null;
    public ResultadoTokenizador(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Lexema> getLexemas() {
        return resultado;
    }
}
