/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import matebraille.archivos.XMLComandos;
import matebraille.archivos.XMLDescriptores;
import matebraille.compilador.tokenizador.InfoSintaxis;
import matebraille.compilador.tokenizador.Lexema;
import matebraille.compilador.tokenizador.Tokenizador;
import org.xml.sax.SAXException;

/**
 *
 * @author franco
 */
public class MateBraille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XMLComandos xmlCmd;
        xmlCmd = new XMLComandos();
        XMLDescriptores xmlDesc = new XMLDescriptores();
        xmlCmd.iniciarLectura();
        xmlDesc.iniciarLectura();
        Tokenizador toke = new Tokenizador(xmlCmd, xmlDesc, "$ \\alpha = a_{n+1}\n$");
        if (toke.analizarCodigo()) {
            for (int i = 0; i < toke.getRes().getLexemas().size(); i++) {
                Lexema lex = toke.getRes().getLexemas().get(i);
                if (lex.getCmd() == null) {
                    System.out.println("<" + lex.getValor() + ">" + " Tipo: " + lex.getTipo().toString() + " Posicion: " + lex.getPosicion());
                } else {
                    System.out.println("<" + lex.getValor() + ">" + " Tipo: " + lex.getTipo().toString() 
                            + ", " + lex.getCmd().getTipo()  + " Cate: " + lex.getCmd().getCategoria().toString() + ", Posicion: " + lex.getPosicion());
                    System.out.println("-----------------------------------------");
                }
            }
        }
        else{
            for(int i = 0; i < toke.getRes().getErrores().size();i++){
                InfoSintaxis info = toke.getRes().getErrores().get(i);
                System.out.println("Error "+(i+1)+"\n->"+info.getDescriptor().getDescripcion()+
                                   "\nfila: "+ info.getFila() + " col: "+info.getColumna()+"\n" );
                
            }
        }
    }
}
