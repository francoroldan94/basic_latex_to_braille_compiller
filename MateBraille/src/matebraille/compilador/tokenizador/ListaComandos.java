/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import java.util.ArrayList;

/**
 *
 * @author franco
 */
public class ListaComandos {
     private ArrayList<Comando> listaCmd;
     /**
      * Inicializa la lista de comandos, necesario, puesto que después
      * serán obtenidos desde archivo en versiones futuras.
      */
     public ListaComandos(){
         listaCmd = new ArrayList<Comando>();
         listaCmd.add(new Comando("\\phi"));
         listaCmd.add(new Comando("\\alpha"));
     }
     
     public int existeCmd(String cmd){
        for(int i = 0; i < listaCmd.size();i++){
            if(listaCmd.get(i).getLiteral().equals(cmd)){
                return i;
            }
        }
        return -1;
     }
}
