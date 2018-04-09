/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.analisisDeSintaxis;

import java.util.ArrayList;
import matebraille.compilador.tokenizador.Lexema;
import matebraille.compilador.tokenizador.ResultadoTokenizador;
/**
 *
 * @author franco
 */
public class AnalizadorDeSintaxis {
    private ArrayList<Lexema> codigo = null;
    public AnalizadorDeSintaxis(ResultadoTokenizador resultado) {
        codigo = resultado.getLexemas();
    }
    
}
