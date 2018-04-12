/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille;

import matebraille.compilador.tokenizador.ListaComandos;
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
        // TODO code application logic here
       ListaComandos lcmd = new ListaComandos();
       //  System.out.print(lcmd.getCmd("\\alpha").getLiteral());
       /*Tokenizador toke = new  Tokenizador(lcmd,"$$$");
       System.out.print(toke.validacionPrevia()+"\n");
       toke.analizarCodigo();
       System.out.print(toke.getRes().getErrores().get(0).getInformación()+"\n"
                        +"Columna: " + toke.getRes().getErrores().get(0).getColumna()
                        +"\nFila: " + toke.getRes().getErrores().get(0).getFila()+"\n");
        */
    }
    
    
}
