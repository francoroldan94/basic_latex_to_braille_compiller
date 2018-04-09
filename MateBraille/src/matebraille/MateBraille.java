/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille;

import matebraille.compilador.tokenizador.ListaComandos;

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
        System.out.print(lcmd.getCmd("\\alpha").getLiteral());
    }
    
}
