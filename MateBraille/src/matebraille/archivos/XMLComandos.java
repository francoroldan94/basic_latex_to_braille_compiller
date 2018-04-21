/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import matebraille.compilador.tokenizador.Comando;
import matebraille.compilador.tokenizador.TipoCmd;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import matebraille.compilador.tokenizador.categoriaCmd;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SI EL CONTENIDO ES VARIABLE DEJAR EL CONTENIDO COMO ETIQUETA VACIA
 * <CONTENIDO></CONTENIDO>
 *
 * @author franco
 */
public final class XMLComandos extends DefaultHandler {

    private ArrayList<Comando> listaCmd = new ArrayList<>();
    Comando comando;
    private StringBuilder buffer = new StringBuilder();
    private File file;
    private SAXParser saxParser;

    /**
     * Inicializa la lista de comandos, necesario, puesto que después serán
     * obtenidos desde archivo.
     */
    public XMLComandos() {
        //Cargo los comandos básicos.
        //Aqui se debe cargar el XML llamando al parser

        /////COMANDOS NATIVOS (SIMBOLOS ESPECIALES)/////////////////
        nuevoCmd(new Comando("$", 0, TipoCmd.MODO_MATE));
        nuevoCmd(new Comando("$$", 0, TipoCmd.MODO_MATE_2));
        nuevoCmd(new Comando("{", 0, TipoCmd.L_INI));
        nuevoCmd(new Comando("}", 0, TipoCmd.L_FIN));
        nuevoCmd(new Comando(")", 0, TipoCmd.SIMB));
        nuevoCmd(new Comando("(", 0, TipoCmd.SIMB));
        nuevoCmd(new Comando("[", 0, TipoCmd.CORCH_INI));
        nuevoCmd(new Comando("[", 0, TipoCmd.CORCH_FIN));
        nuevoCmd(new Comando("_", 0, TipoCmd.SUB));
        nuevoCmd(new Comando("^", 0, TipoCmd.SUPRA));
        nuevoCmd(new Comando(" ", 0, TipoCmd.ESPACIO));
        nuevoCmd(new Comando("\n",0,TipoCmd.ENTER));
        //////////////////////PRUEBA////////////////////
//        nuevoCmd(new Comando("\\alpha", 0, TipoCmd.SIMB));
//        nuevoCmd(new Comando("\\sum", 0, TipoCmd.SIMB));
//        nuevoCmd(new Comando("\\alpha", 0, TipoCmd.SIMB));
//        nuevoCmd(new Comando("\\infty", 0, TipoCmd.SIMB));
//        nuevoCmd(new Comando("\\left", 0, TipoCmd.SIMB));
//        nuevoCmd(new Comando("\\left(", 0, TipoCmd.SIMB));
        ////////////CARGAR CMD'S//////////////////////////
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLComandos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLComandos.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File("comandos.xml");        
    }

    public void iniciarLectura() {
     
        try {
            saxParser.parse(file, this);
        } catch (SAXException ex) {
            Logger.getLogger(XMLComandos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLComandos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public void characters(char[] chars, int inicio, int longitud) throws SAXException {
        buffer.append(chars, inicio, longitud);
    }

    @Override
    public void endElement(String string, String string1, String string2) throws SAXException {
        switch (string2) {
            case "cmd":
                listaCmd.add(comando);
                break;
            case "literal":
                comando.setLiteral(buffer.toString());
                break;
            case "aridad":
                comando.setAridad(Integer.parseInt(buffer.toString()));
                break;
            case "tipo":
                comando.setTipo(TipoCmd.valueOf(buffer.toString()));
                break;
            case "categoria":
                comando.setCate(categoriaCmd.valueOf(buffer.toString()));
                break;
        }
    }

    @Override
    public void startElement(String string, String string1, String string2, Attributes atrbts) throws SAXException {
        switch (string2) {
            case "cmd":
                comando = new Comando();
                break;
            case "literal":
            case "aridad":
            case "tipo":
            case "categoria":
                buffer.setLength(0);
                break;
        }

    }

    /////////UTILIDADES DE LA CLASE////////////////////////////
    /**
     *
     * @param cmd
     */
    public void nuevoCmd(Comando cmd) {
        this.listaCmd.add(cmd);
    }

    /**
     *
     * @param valor
     * @return
     */
    public Comando getCmd(String valor) {
        return this.getCmdPorIndice(this.buscarCmd(valor));
    }

    private int buscarCmd(String cmd) {
        for (int i = 0; i < listaCmd.size(); i++) {
            if (listaCmd.get(i).getLiteral().equals(cmd)) {
                return i;
            }
        }
        return -1;
    }

    private Comando getCmdPorIndice(int indice) {
        if (!(0 <= indice && indice < this.listaCmd.size())) {
            return null;
        }
        return listaCmd.get(indice);
    }

}
