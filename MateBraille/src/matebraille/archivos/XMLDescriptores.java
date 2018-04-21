/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.archivos;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import matebraille.compilador.tokenizador.Comando;
import matebraille.compilador.tokenizador.TipoCmd;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import matebraille.compilador.tokenizador.Descriptor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author franco
 */
public class XMLDescriptores extends DefaultHandler {

    private ArrayList<Descriptor> descrip_errores= new ArrayList<>();;
    private ArrayList<Descriptor> descrip_advertencias= new ArrayList<>();;
    private Descriptor descriptor;
    private StringBuilder buffer = new StringBuilder();
    private File file;
    private SAXParser saxParser;

    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        buffer.append(chars, i, i1);
    }

    @Override
    public void endElement(String string, String string1, String string2) throws SAXException {
        switch (string2) {
            case "error":
                descrip_errores.add(descriptor);
                break;
            case "adv":
                descrip_advertencias.add(descriptor);
                break;
            case "id":
                descriptor.setId(buffer.toString());
                break;
            case "contenido":
                descriptor.setContenido(buffer.toString());
                break;
            case "descripcion":
                descriptor.setDescripcion(buffer.toString());
                break;
        }
    }

    @Override
    public void startElement(String string, String string1, String string2, Attributes atrbts) throws SAXException {
        switch (string2) {
            case "error":
            case "adv":
                descriptor = new Descriptor();
                break;
            case "id":
            case "contenido":
            case "descripcion":
                buffer.setLength(0);
                break;
        }
    }

    public XMLDescriptores() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLDescriptores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLDescriptores.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File("descriptores.xml");
    }

    public void iniciarLectura(){
        try {
            saxParser.parse(file, this);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XMLDescriptores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Descriptor hallarPorId(String id) {
        for (int i = 0; i < descrip_errores.size(); i++) {
            if (descrip_errores.get(i).getId().equals(id)) {
                return descrip_errores.get(i);
            }
        }
        for (int i = 0; i < descrip_advertencias.size(); i++) {
            if (descrip_advertencias.get(i).getId().equals(id)) {
                return descrip_advertencias.get(i);
            }
        }
        return null;
    }
}
