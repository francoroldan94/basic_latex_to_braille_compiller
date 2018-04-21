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
    private String codigo = "";
    private ResultadoTokenizador res;

    /**
     * Constuctor básico, inicializa.
     *
     * @param listaCmd Lista de comandos especiales "\nombre"
     * @param codigo Código a procesar.
     */
    public Tokenizador(XMLComandos listaCmd, XMLDescriptores listaDesc, String codigo) {
        this.listaCmd = listaCmd;
        this.listaDesc = listaDesc;
        //Se le añaden dos espacios para que funcione el algoritmo de deteccion
        //De modos matemáticos correctos.
        this.codigo = codigo + "  ";
    }

//-----------------Validaciones previas a la tokenizacion-----------------------
    private boolean validacionPrevia() {
        res = new ResultadoTokenizador();
        //Sucesión de procedimientos 
        return NoVacio() && modoMatematicoCorrecto();
    }

    /**
     * Indica si el codigo es vacio
     *
     * @return
     */
    private boolean NoVacio() {
        if (codigo.equals("  ")) {
            InfoSintaxis info = crearInfoSintaxis("codigo vacio", 0);
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
    public enum estMMat {
        NORMAL, SIMPLE, DOBLE
    };

    @SuppressWarnings("empty-statement")
    private boolean modoMatematicoCorrecto() {
        ///TODO: Verificar todos los $$$, no solo el primero.        
        int pos;
        pos = codigo.indexOf("$$$", 0);
        if (codigo.indexOf("$$$", 0) != -1) {
            InfoSintaxis info = crearInfoSintaxis("triada de $", pos);
            //Informe del error. Esto debe ser de rutina en todo error.
            res.nuevoError(info);
            return false;
        }

        estMMat estadoMMat;
        estadoMMat = estMMat.NORMAL;
        for (int i = 0; i < codigo.length() - 1; i++) {
            switch (estadoMMat) {
                case NORMAL: //Inicial
                    if (i != codigo.length() - 2) {
                        if (esPesoAt(i)) {
                            estadoMMat = estMMat.SIMPLE;
                        } else if (esPPesoAt(i)) {
                            estadoMMat = estMMat.DOBLE;
                            i++; //Esto es para esquivar el segundo peso
                        }
                    }
                    break;
                case SIMPLE: //$
                    //Error de paridad
                    if (i == codigo.length() - 2) {
                        InfoSintaxis info = crearInfoSintaxis("$ extra", i - 1);
                        res.nuevoError(info);
                        return false;
                    }
                    //Error $...$$
                    if (esPPesoAt(i)) {
                        InfoSintaxis info = crearInfoSintaxis("$...$$", i);
                        res.nuevoError(info);
                        return false;
                    } else if (esPesoAt(i)) {
                        estadoMMat = estMMat.NORMAL;
                    }
                    break;
                case DOBLE://$$
                    //Error de paridad
                    if (i == codigo.length() - 2) {
                        InfoSintaxis info = crearInfoSintaxis("$$ extra", i - 2);
                        res.nuevoError(info);
                        return false;
                    }

                    if (esPesoAt(i)) {
                        InfoSintaxis info = crearInfoSintaxis("$$...$", i);
                        res.nuevoError(info);
                        return false;

                    } else if (esPPesoAt(i)) {
                        estadoMMat = estMMat.NORMAL;
                        i++;
                    }

                    break;
            }
        }
        return true;
    }
//-----------------------Utilidades---------------------------------------------

    private InfoSintaxis crearInfoSintaxis(String id, int pos) {
        return new InfoSintaxis(listaDesc.hallarPorId(id), pos, filasEnPos(pos), columnasEnPos(pos));
    }

    private InfoSintaxis crearInfoSintaxis(String id, String contVariable, int pos) {
        return new InfoSintaxis(listaDesc.hallarPorId(id), contVariable, pos, filasEnPos(pos), columnasEnPos(pos));
    }

    private int filasEnPos(int i) {
        return contarFilas(codigo.substring(0, i));
    }

    private int columnasEnPos(int i) {
        return contarColumnas(codigo.substring(0, i));
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
        //Se le suma 1 porque el \ no entra en el texto
        if(viejo_indice == 0) return texto.length()+1; 
        //Aquí tampoco se incluye, pero el \n lo compensa
        return Integer.max((texto.length() - viejo_indice) , 1);
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

    private enum estLex {
        NORMAL, INICIO, CMD, NUM, CARACT
    };

    private class Retorno {

        public estLex estado;
        public int i;
    };

    public boolean analizarCodigo() {
        if (!validacionPrevia()) {
            return false;
        } else {
            Retorno ret = new Retorno();
            ret.estado = estLex.NORMAL;
            StringBuilder contenido = new StringBuilder();

            for (ret.i = 0; ret.i < codigo.length(); ret.i++) {
                switch (ret.estado) {
                    case NORMAL:
                        ret = lexNormal(ret.i, contenido);
                        break;
                    case INICIO:
                        ret = lexInicio(ret.i, contenido);
                        break;
                    case CMD:
                        ret = lexCmd(ret.i, contenido);
                        break;
                    case NUM:
                        ret = lexNum(ret.i, contenido);
                        break;
                    case CARACT:
                        ret = lexCaract(ret.i, contenido);
                        break;
                }
                //Esto porque no se programó correción de errores, ni modo ṕanico, ni análisis global.
                if (ret == null) {
                    return false;
                }
            }
        }

        return true;
    }
///////////////ESTADOS DE PROCESAMIENTO/////////////////////////////////////////

    private Retorno lexNormal(int i, StringBuilder contenido) {
        char caracter = codigo.charAt(i);
        Retorno ret = new Retorno();
        ret.i = i;
        if (esPPesoAt(i)) {
            if (contenido.length() != 0) {
                Lexema lex = Lexema.nuevoLexemaNoMatematico(contenido.toString(), i - contenido.length());
                res.nuevoLexema(lex); //Carga el lexema a la base de datos
                contenido.setLength(0); //Vacía el contenido acumulado.
            }
            res.nuevoLexema(Lexema.nuevoLexemaCmd(listaCmd.getCmd("$$"), i - contenido.length()));
            ret.i++; //Salta un signo $
            ret.estado = estLex.INICIO;
            return ret;
        }

        if (esPesoAt(i)) {
            if (contenido.length() != 0) {
                Lexema lex = Lexema.nuevoLexemaNoMatematico(contenido.toString(), i - contenido.length());
                res.nuevoLexema(lex); //Carga el lexema a la base de datos
                contenido.setLength(0); //Vacía el contenido acumulado.
            }
            res.nuevoLexema(Lexema.nuevoLexemaCmd(listaCmd.getCmd("$"), i));
            ret.estado = estLex.INICIO;
            return ret;
        }

        //Fin del archivo
        if (i == codigo.length() - 2) {
            if (contenido.length() != 0) {
                res.nuevoLexema(Lexema.nuevoLexemaNoMatematico(contenido.toString(), i - contenido.length())); //Carga el lexema a la base de datos
                contenido.setLength(0); //Vacía el contenido acumulado.
            }
            ret.i = codigo.length(); //Esto hace que termine el análisis.
            ret.estado = estLex.NORMAL;
            return ret;
        }
        //Sin criterio de parada simplemente recolecta otro caracter, puesto que está fuera del modo matemático.
        contenido.append(caracter);
        ret.estado = estLex.NORMAL;
        return ret;
    }

    private Retorno lexInicio(int i, StringBuilder contenido) {
        char caracter = codigo.charAt(i);
        Retorno ret = new Retorno();
        ret.i = i;
        if (esPPesoAt(i)) {
            res.nuevoLexema(Lexema.nuevoLexemaCmd(listaCmd.getCmd("$$"), i));
            ret.i++; //Salta un signo $

            ret.estado = estLex.NORMAL;
            return ret;
        }

        if (esPesoAt(i)) {
            res.nuevoLexema(Lexema.nuevoLexemaCmd(listaCmd.getCmd("$"), i));
            ret.estado = estLex.NORMAL;
            return ret;
        }
        if (esCaracterEspecial(i)) {
            if (codigo.charAt(i) == '\\') {
                contenido.setLength(0);  //Vaciamos el contenido 
                contenido.append('\\'); //Se añade slash
                ret.estado = estLex.CMD; //Se inicia el modo comando
                return ret;

            } else if (listaCmd.getCmd(Character.toString(codigo.charAt(i))) != null) {
                res.nuevoLexema(Lexema.nuevoLexemaCmd(listaCmd.getCmd(Character.toString(codigo.charAt(i))), i));
            } else {
                res.nuevoLexema(Lexema.nuevoLexemaSigno(Character.toString(caracter), i));
            }
        }

        if (Character.isDigit(caracter)) {
            contenido.setLength(0);
            contenido.append(caracter);
            ret.estado = estLex.NUM;
            return ret;
        }
        if (Character.isAlphabetic(caracter)) {
            contenido.setLength(0);
            contenido.append(caracter);
            ret.estado = estLex.CARACT;
            return ret;
        }

        ret.estado = estLex.INICIO;
        return ret;
    }

    private Retorno lexCmd(int i, StringBuilder contenido) {
        char caracter = codigo.charAt(i);
        Retorno ret = new Retorno();
        ret.i = i;

        // ¿SE ADMITEN NÚMEROS EN LOS IDENTIFICADORES?
        if (Character.isAlphabetic(caracter)) {
            contenido.append(caracter);
            ret.estado = estLex.CMD;
            return ret;
        } else {
            /**
             * Se detecta si el comando \contenido# con # el último caracter no alfabético
             * Es un comando (cmd_ext), en caso de no serlo se verifica si \contenido es un 
             * Comando (cmd), se adiere la regla de que los comandos no pueden poseer caracteres
             * Numéricos y se evita el espacio como parte de un cmd. Se absorbe el último caracter
             * Para que no sea procesado por el inicio.
             */
            Comando cmd_ext = listaCmd.getCmd(contenido.toString() + caracter);
            Comando cmd = listaCmd.getCmd(contenido.toString());

            if (caracter != ' ' && !Character.isDigit(caracter) && cmd_ext != null) {
                res.nuevoLexema(Lexema.nuevoLexemaCmd(cmd_ext, i - contenido.length()));
                ret.estado = estLex.INICIO;               
                contenido.setLength(0); //Siempre vaciar el acumulador.
                return ret;
            }else if (cmd != null) {
                res.nuevoLexema(Lexema.nuevoLexemaCmd(cmd, i - contenido.length()));
                ret.estado = estLex.INICIO;
                contenido.setLength(0); //Siempre vaciar el acumulador.
                ret.i--; //Habilita al modo inicio a procesar el último caracter.
                return ret;
            } else {
                res.nuevoError(crearInfoSintaxis("cmd desconocido",contenido.toString(),i-contenido.length()));
                return null;
            }
        }
    }

    private Retorno lexNum(int i, StringBuilder contenido) {
        char caracter = codigo.charAt(i);
        Retorno ret = new Retorno();
        ret.i = i;
        if (Character.isDigit(caracter)) {
            contenido.append(caracter);
            ret.estado = estLex.NUM;
            return ret;
        } else {
            ret.i--;
            res.nuevoLexema(Lexema.nuevoLexemaNumerico(contenido.toString(), i - contenido.length()));
            contenido.setLength(0);
            ret.estado = estLex.INICIO;
            return ret;
        }

    }

    private Retorno lexCaract(int i, StringBuilder contenido) {
        char caracter = codigo.charAt(i);
        Retorno ret = new Retorno();
        ret.i = i;
        if (Character.isAlphabetic(caracter)) {
            contenido.append(caracter);
            ret.estado = estLex.CARACT;
            return ret;

        } else {
            ret.i--;
            res.nuevoLexema(Lexema.nuevoLexemaAlfabetico(contenido.toString(), i - contenido.length()));
            contenido.setLength(0);
            ret.estado = estLex.INICIO;
            return ret;
        }
    }
////////////////////////HERRAMIENTAS DE PROCESAMIENTO///////////////////////////

    private boolean esCaracterEspecial(int i) {
        char c = codigo.charAt(i);
        return !Character.isAlphabetic(c) && !Character.isDigit(c);
    }
/////////////////RESULTADO//////////////////////////////////////////////////////

    public ResultadoTokenizador getRes() {
        return res;
    }
}
