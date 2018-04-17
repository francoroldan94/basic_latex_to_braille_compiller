/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.archivos;

import java.util.ArrayList;
import matebraille.compilador.tokenizador.Descriptor;

/**
 *
 * @author franco
 */
public class XMLDescriptores {

    private ArrayList<Descriptor> descriptores;

    public Descriptor hallarPorId(String id) {
        for (int i = 0; i < descriptores.size(); i++) {
            if (descriptores.get(i).getId().equals(id)) {
                return descriptores.get(i);
            }
        }
        return null;
    }
}
