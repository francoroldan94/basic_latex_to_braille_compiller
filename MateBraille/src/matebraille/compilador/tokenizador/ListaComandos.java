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
public final class ListaComandos {
     private final ArrayList<Comando> listaCmd = new ArrayList<>();
     /**
      * Inicializa la lista de comandos, necesario, puesto que después
      * serán obtenidos desde archivo.
      */
     
     public ListaComandos(){
         //Cargo los comandos básicos.
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