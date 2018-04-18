/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille;

import matebraille.archivos.XMLComandos;
import matebraille.archivos.XMLDescriptores;
import matebraille.compilador.tokenizador.Lexema;
import matebraille.compilador.tokenizador.Tokenizador;

/**
 *
 * @author franco
 */
public class MateBraille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tokenizador toke = new Tokenizador(new XMLComandos(),new XMLDescriptores(),"Tomando $\\alpha>0$ se tiene que:");
        if(toke.analizarCodigo()){
            for(int i = 0; i < toke.getRes().getLexemas().size();i++){
                Lexema lex = toke.getRes().getLexemas().get(i);
                if(lex.getCmd() == null)
                    System.out.println("<"+lex.getValor()+">" + "____" + lex.getTipo().toString() + "___" + lex.getPosicion());
                else
                    System.out.println("<"+lex.getValor()+">" + "____" + lex.getTipo().toString()+
                            ", "+lex.getCmd().getTipo()+ "___" + lex.getPosicion());
            }
        }
    }
}
