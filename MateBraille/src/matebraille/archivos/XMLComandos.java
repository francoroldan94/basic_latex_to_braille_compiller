/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.archivos;

import java.util.ArrayList;
import matebraille.compilador.tokenizador.Comando;
import matebraille.compilador.tokenizador.Tipo;

/**
 *
 * @author franco
 */
public final class XMLComandos {
     private final ArrayList<Comando> listaCmd = new ArrayList<>();
     /**
      * Inicializa la lista de comandos, necesario, puesto que después
      * serán obtenidos desde archivo.
      */
     
     public XMLComandos(){
         //Cargo los comandos básicos.
         //Aqui se debe cargar el XML llamando al parser
         nuevoCmd(new Comando("$", 0,Tipo.MODO_MATE));
         nuevoCmd(new Comando("$$", 0,Tipo.MODO_MATE_2));
         nuevoCmd(new Comando("{",0,Tipo.L_INI));
         nuevoCmd(new Comando("}",0,Tipo.L_FIN));
     }
     
    /**
     *
     * @param cmd
     */
    public void nuevoCmd(Comando cmd){
         this.listaCmd.add(cmd);
     }

    /**
     *
     * @param valor
     * @return
     */
    public Comando getCmd(String valor){
             return this.getCmdPorIndice(this.buscarCmd(valor));
     }
     
     private int buscarCmd(String cmd){
        for(int i = 0; i < listaCmd.size();i++){
            if(listaCmd.get(i).getLiteral().equals(cmd)){
                return i;
            }
        }
        return -1;
     }
     private Comando getCmdPorIndice(int indice){
         if(!(0<=indice && indice<this.listaCmd.size()))
             return null;
         return listaCmd.get(indice);
     }
     
}
