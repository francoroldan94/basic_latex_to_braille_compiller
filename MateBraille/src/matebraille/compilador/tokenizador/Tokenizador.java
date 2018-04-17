/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import matebraille.archivos.XMLComandos;
import java.util.ArrayList;
import matebraille.archivos.XMLDescriptores;

/**
 *
 * @author franco Esta Clase se encarga de analizar los lexemas del codigo latex
 * simplificado.
 */
public final class Tokenizador {

    private static XMLComandos listaCmd;
    private static XMLDescriptores listaDesc;
    private  String codigo = "";
    private ResultadoTokenizador res;

    /**
     * Constuctor básico, inicializa.
     *
     * @param listaCmd Lista de comandos especiales "\nombre"
     * @param codigo Código a procesar.
     */
    public Tokenizador(XMLComandos listaCmd,XMLDescriptores listaDesc, String codigo) {
        this.listaCmd = listaCmd;
        this.listaDesc = listaDesc;
        //Se le añaden dos espacios para que funcione el algoritmo de deteccion
        //De modos matemáticos correctos.
        this.codigo = codigo + "  ";
    }

//-----------------Validaciones previas a la tokenizacion-----------------------
    private boolean validacionPrevia() {
        res = new ResultadoTokenizador();
        //Sucesión de procedimientos del tokenizador
        if (!NoVacio()) {
            return false;
        }
        //Desde el punto de vista lexico.

        if (!modoMatematicoCorrecto()) {
            return false;
        }

        return true;
    }
    /**
     * Indica si el codigo es vacio
     *
     * @return
     */
    private boolean NoVacio() {
        if (codigo.equals("  ")) {
            InfoSintaxis info = crearInfoSintaxis("codigo vacio",0);
            res.nuevoError(info);
            return false;
        }

        return true;
    }
    /**
     * Detecta si el modo matemático se presenta en una secuencia correcta, es
     * decir la no existencia de "$$$". Lo cual implica que las apariciones son
     * Solamente de la forma: $ o $$. Se detecta la cohesion de bloques.
     *
     * @return
     */
    private boolean modoMatematicoCorrecto() {
        ///TODO: Verificar todos los $$$, no solo el primero.        
        int pos;
        pos = codigo.indexOf("$$$", 0);
        if (codigo.indexOf("$$$", 0) != -1) {
            InfoSintaxis info = crearInfoSintaxis("triada de $",pos);
            //Informe del error. Esto debe ser de rutina en todo error.
            res.nuevoError(info);
            return false;
        }
     
        int estado = 0;
        for (int i = 0; i < codigo.length() - 1; i++) {
            switch (estado) {
                case 0: //Inicial
                    if (i != codigo.length() - 2) {
                        if (esPesoAt(i)) {
                            estado = 1;
                        } else if (esPPesoAt(i)) {
                            estado = 2;
                            i++; //Esto es para esquivar el segundo peso
                        }
                    }              
                    break;
                case 1: //$
                    //Error de paridad
                    if (i == codigo.length() - 2) {
                        InfoSintaxis info = crearInfoSintaxis("$ extra", i-1);
                        res.nuevoError(info);
                        return false;
                    }
                    //Error $...$$
                    if (esPPesoAt(i)) {
                        InfoSintaxis info = crearInfoSintaxis("$...$$", i);
                        res.nuevoError(info);
                        return false;
                    } else if (esPesoAt(i)) {
                        estado = 0;
                    }
                    break;
                case 2://$$
                    //Error de paridad
                    if (i == codigo.length() - 2) {
                        InfoSintaxis info = crearInfoSintaxis("$$ extra",i-2);
                        res.nuevoError(info);
                        return false;
                    }

                    if (esPesoAt(i)) {
                        InfoSintaxis info = crearInfoSintaxis("$$...$",i);
                        res.nuevoError(info);
                        return false;

                    } else if (esPPesoAt(i)) {
                        estado = 0;
                        i++;
                    }

                    break;
            }
        }
        return true;
    }
//-----------------------Utilidades---------------------------------------------
    private InfoSintaxis crearInfoSintaxis(String id,int pos){
        return new InfoSintaxis(listaDesc.hallarPorId("triada de $"),pos,filasEnPos(pos), columnasEnPos(pos));
    }
    private int filasEnPos(int i) {
        return contarColumnas(codigo.substring(0, i));
    }

    private int columnasEnPos(int i) {
        return contarFilas(codigo.substring(0, i));
    }

    private int contarColumnas(String texto) {

        int indice = 0, viejo_indice = 0;

        while (indice != -1) {
            indice = texto.indexOf("\n", indice);
            if (indice != -1) {
                viejo_indice = indice;
                indice = indice + 1;
            }
        }

        return Integer.max((texto.length() - viejo_indice), 1);
    }

    private int contarFilas(String texto) {
        int lineas = 1, indice = 0;
        while (indice != -1) {
            indice = texto.indexOf("\n", indice);
            if (indice != -1) {
                lineas++;
                indice = indice + 1;
            }
        }
        return lineas;
    }

    private boolean esPesoAt(int i) {
        return codigo.charAt(i) == '$' && codigo.charAt(i + 1) != '$';
    }

    private boolean esPPesoAt(int i) {
        return codigo.charAt(i) == '$' && codigo.charAt(i + 1) == '$';
    }
//---------------------Analisis lexico------------------------------------------

    public boolean analizarCodigo() {

        if(!validacionPrevia()) return false;
        
        
        return true;
    }

    public ResultadoTokenizador getRes() {
        return res;
    }

}
