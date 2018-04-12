/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matebraille.compilador.tokenizador;

import java.util.ArrayList;

/**
 *
 * @author franco Esta Clase se encarga de analizar los lexemas del codigo latex
 * simplificado.
 */
public final class Tokenizador {

    private final ListaComandos listaCmd;
    private final String codigo;
    private ResultadoTokenizador res;

    /**
     * Constuctor básico, inicializa.
     * @param listaCmd 
     * Lista de comandos especiales "\nombre"
     * @param codigo
     * Código a procesar.
     */
    public Tokenizador(ListaComandos listaCmd, String codigo) {
        this.listaCmd = listaCmd;
        this.codigo = codigo;
    }
    
//-----------------Validaciones previas a la tokenizacion-----------------------
    public boolean validacionPrevia() {
        res = new ResultadoTokenizador();
    //Sucesión de procedimientos del tokenizador
        if (!NoVacio()) return false;
        //Desde el punto de vista lexico.
        
       if (!modoMatematicoCorrecto()) return false;
        
       return true;
    }
    /**
     * Indica si el codigo es vacio
     *
     * @return
     */
    private boolean NoVacio() {
        if (codigo.equals("")) {
            InfoSintaxis info = new InfoSintaxis();
            info.setInformación("Código vacío.");
            info.setColumna(0);
            info.setFila(0);
            info.setContenido("");
            res.getErrores().add(info);
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
        
        int doble = 0, simple = 0, pos;
        boolean doble_inicio = true, simple_inicio = true;
        pos = codigo.indexOf("$$$", 0);
        if (codigo.indexOf("$$$", 0) != -1) {
            InfoSintaxis info = new InfoSintaxis();
            //Informe del error. Esto debe ser de rutina en todo error.
            info.setFila(contarFilas(codigo.substring(0, pos)));
            info.setColumna(contarColumnas(codigo.substring(0, pos)));
            info.setContenido("$$$");
            info.setInformación("Error: Se ha introducido \"$$$\", lo cual es incogruente. \nSe debe separar la tríada.");
            info.setPosicion(pos);
            res.getErrores().add(info);
            return false;
            
        }
        if(codigo.length() == 1) 
            if(codigo.charAt(0) == '$'){
            InfoSintaxis info =
                    new InfoSintaxis("$","Error: Se ha introducido \"$\","
                            + " lo cual es incogruente. "
                            + "\nSe debe cerrar el modo matematico.",pos,1,1);
            res.getErrores().add(info);
            return false;
            }
        /**
        * La idea es que siempre que se abra se sume uno y cuando se cierre se reste uno
        * Si hay bloques del tipo contrario abuertos, se lanza una exepción.
        */
        for(int i = 0; i<codigo.length()-1;i++){
            if(codigo.charAt(i) == '$' && codigo.charAt(i+1) != '$'){
                if(simple_inicio){
                    simple++;
                }else{
                    simple--;
                    if(doble != 0){
                        
                    }
                }
                simple_inicio = !simple_inicio;
            }else if(codigo.substring(i, i+1) == "$$"){
                if(doble_inicio){
                    doble++;
                }else{
                    doble--;
                    if(simple != 0){
                        //Error
                    }
                }
                doble_inicio = !doble_inicio;
            }
        }
        return true;
    }

//-----------------------Utilidades---------------------------------------------
    private int contarColumnas(String texto){       
        
        int indice = 0,viejo_indice = 0;
        
        while (indice != -1) {
            indice = texto.indexOf("\n", indice);
            if(indice != -1)
            {
                viejo_indice = indice;
                indice = indice + 1;
            }
        }
        
        return Integer.max((texto.length() - viejo_indice ),1);
    }
    private int contarFilas(String texto) {
        int lineas = 1, indice = 0;
        while (indice != -1) {
            indice = texto.indexOf("\n", indice);
            if(indice != -1)
            {
                lineas++;
                indice = indice + 1;
            }
        }
        return lineas;
    }

//---------------------Analisis lexico------------------------------------------
    public boolean analizarCodigo(){
        
        return true;
    }

    public ResultadoTokenizador getRes() {
        return res;
    }
    
}

