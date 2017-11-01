/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * Representa a un automata finito ya sea deterministico o no deterministico
 * @author Melissa
 */
public class AF {
    
    private List<String> estados;
    private List<String> simbolos;
    private String[][] af;
    private List<String> salidas;
    private List<String> estadosNuevos;
    private boolean deterministico;
    int nEstados;
    int nSimbolos;

    /**
     * Instancia un nuevo automata finito vacio, inicializa todos sus componentes de igual forma
     * @author Melissa
     */
    public AF() {
        af = new String[22][22];
        estados = new ArrayList();
        simbolos = new ArrayList();
        salidas = new ArrayList();
        estadosNuevos = new ArrayList();
        deterministico = true;

    }

    /**
     * Instancia un nuevo Automata finito con base en la gramatica enviada como parametro, esta debe de ser regular
     * @param g Gramatica que sera llevada al automata finito.
     */
    public AF(Gramatica g) {

        //no funcionan 
        nEstados = g.getnNoTerminales();
        nSimbolos = g.getnTerminales();
        System.out.println("Estados" + nEstados);
        af = new String[22][22];
        estados = new ArrayList();
        simbolos = new ArrayList();
        salidas = new ArrayList();
        estadosNuevos = new ArrayList();
        deterministico = true;
        String aux;

        //Recorrer gramatica y llenar matriz
        int pEstado, pEstado2, pSimbolo;
        String nTerminal, nTerminal2, simbolo;
        List<Produccion> producciones = g.getProducciones();

        for (Produccion i : producciones) {
            System.out.println(i);

            if (i.isOfAcceptance()) {
                // Si es de aceptación, 
                nTerminal = i.getPartes(0);
                pEstado = estados.indexOf(nTerminal);
                System.out.println(pEstado);

                salidas.set(pEstado, "1");

                if (pEstado == -1) {
                    System.out.println("No puede haber estado de aceptación sin ninguna transición");
                }

            } else {
                salidas.add("0");
                nTerminal = i.getPartes(0);
                simbolo = i.getPartes(2);
                nTerminal2 = i.getPartes(3);

                pEstado = estados.indexOf(nTerminal);
                pEstado2 = estados.indexOf(nTerminal2);
                pSimbolo = simbolos.indexOf(simbolo);

                if (pSimbolo == -1) {
                    simbolos.add(simbolo);
                    pSimbolo = simbolos.indexOf(simbolo);
                }
                if (pEstado == -1) {
                    estados.add(nTerminal);
                    pEstado = estados.indexOf(nTerminal);
                }

                if (pEstado2 == -1) {
                    estados.add(nTerminal2);
                }

                System.out.println(af[pEstado][pSimbolo]);
                //Si hay mas de una transición entonces es Nodeterministico 
                //Se añade a la posición de la matriz correspondiente, sin borrar anteriores
                //Se añade en el ArrayList de estados nuevos, para facilidad al convertir a deterministico
                if (af[pEstado][pSimbolo] == null) {
                    af[pEstado][pSimbolo] = nTerminal2;

                } else {

                    deterministico = false;

                    aux = (af[pEstado][pSimbolo] + ("-") + nTerminal2);
                    af[pEstado][pSimbolo] = aux;
                    //estadosNuevos.set(pEstado,aux);
                    System.out.println(aux);
                }
            }

        }

    }

    /**
     * Instancia un nuevo Automata finito con base en un archivo de texto enviado con su ruta como parametro, esta debe de ser regular
     * @param ruta Ruta en el equipo al automata guardado como archivo de texto
     */
    public AF(String ruta) {

        //carga af desde un archivo
        Boolean creado = false;
        estados = new ArrayList();
        simbolos = new ArrayList();
        salidas = new ArrayList();
        Scanner archivo;
        try {
            archivo = new Scanner(new File(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe en la misma carpeta del ejecutable." + ex);
            return;
        }

        if (archivo.hasNextLine()) {

            deterministico = true;

            String linea;

            linea = archivo.nextLine();

            llenarAF(1, linea);

            linea = archivo.nextLine();

            llenarAF(2, linea);

            linea = archivo.nextLine();

            llenarAF(3, linea);

            nEstados = estados.size();
            nSimbolos = simbolos.size();
            System.out.println(nEstados);
            System.out.println(nSimbolos);
            System.out.println(salidas.size());
            af = new String[22][22];

            int i = 0;
            while (archivo.hasNextLine()) {
                linea = archivo.nextLine();
                String[] partes = linea.split(" ");
                for (int j = 0; j <= partes.length - 1; j++) {
                    af[i][j] = partes[j];
                }
                i++;
            }

        }

    }

    /**
     * llena AF dependiendo si es o no estado, transicion o salida 
     * @param where Tipo de entidad.
     * @param linea Hilera que contiene la parte del automata
     */
    public void llenarAF(int where, String linea) {

        //llena af dependiendo si es o no estado, transicion o salida
        String[] partes = linea.split(" ");

        switch (where) {
            case 1:
                for (int i = 0; i <= partes.length - 1; i++) {
                    simbolos.add(partes[i]);
                }
                break;
            case 2:
                for (int i = 0; i <= partes.length - 1; i++) {
                    estados.add(partes[i]);
                }
                break;
            case 3:
                for (int i = 0; i <= partes.length - 1; i++) {
                    salidas.add(partes[i]);
                }
                break;

        }

    }

    /**
     * Convierte el automata y devuelve un nuevo automata que reconoce las mismas hileras pero es deterministico
     * @return Automata finito deterministico
     */
    public AF convertirADeterministico() {
        AF af2 = new AF();
        af2.simbolos = this.simbolos;
        System.out.println("CONVERSIÓN A DETER---------------------------");

        // recorrer estado inicial e ir preguntando 
        this.duplicar(af2, this.estados.get(0));
        af2.deterministico = true;
        return af2;
    }

    /**
     * Duplica las transiciones del string que hay en el parametro en el af 2
     * @param af2
     * @param transicion 
     */
    public void duplicar(AF af2, String transicion) {
        String transicionNueva;

        System.out.println("metodo duplicar " + transicion);

        if (af2.estados.contains(transicion) == false) {
            //si ya esta en af2 no hacer nada 
            // DE lo contrario  
            int i = this.estados.indexOf(transicion);
            System.out.println(i);

            af2.salidas.add(this.salidas.get(i));
            af2.estados.add(transicion);
            int i2 = af2.estados.size() - 1;

            for (int j = 0; j <= this.simbolos.size() - 1; j++) {

                transicionNueva = this.af[i][j];

                if (transicionNueva != null) {
                    transicionNueva = this.organizarEstado(transicionNueva);
                    af2.af[i2][j] = transicionNueva;
                    System.out.println("se guardó en af2 " + transicionNueva);

                    //pregunta si ya existe no hace nada porque debe estar 
                    // si no existe en af2 entonces debe agregarla con este mismo metodo
                    //preguntando que tipo de transicion es 
                    if (af2.estados.contains(transicionNueva) == false) {

                        if (this.isTransicionNoDeterministica(transicionNueva)) {
                            af2.estados.add(transicionNueva);
                            System.out.println("La transicion " + transicionNueva + " es no deterministica");
                            this.unir(af2, transicionNueva);

                        } else {

                            //Duplicar la misma transición en af2 porque transicion = esget(i)
                            this.duplicar(af2, transicionNueva);

                        }
                    }
                }
            }

        }

    }

    /**
     * Une los estados que se encuentran en la transicion de tipo no deterministico
     * @param af2
     * @param transicion 
     */
    public void unir(AF af2, String transicion) {
        int x, p;
        String aux;
        String sal = "0";

        List<String> partes = this.organizarEstado(transicion.split("-"));

        System.out.println("--UNIÓN--");

        //Como antes de llamar esta funcion se agrego la transicion entonces p será la posicion en af2 
        p = af2.estados.size() - 1;

        //Para hacer la union de las transiciones se usa un  
        //ciclo que recorre por columnas (simbolos)
        for (int i = 0; i <= this.simbolos.size() - 1; i++) {
            System.out.println("       SIMBOLO " + this.simbolos.get(i));

            // Se reinicia la variable para guardar transiciones de nuevo simbolo
            aux = "";

            // ciclo que recorre los estados involucrados en la union
            // en este ciclo interno se va haciendo la union dentro del simbolo i
            for (int j = 0; j <= partes.size() - 1; j++) {

                System.out.println("ESTADO " + partes.get(j));
                // x es la posicion del estado de la transicion
                x = this.estados.indexOf(partes.get(j));
                //Simplificar y comprobar que no sean <a>-<a> 
                //Menos costoso
                //AUX es la union de dichos estados

                aux = this.comprobarUnion(aux, this.af[x][i]);
                //Acá se debe hacer 
                System.out.println("Union [x][i] " + this.af[x][i]);

                // calcular la union de las salidas
                if (salidas.get(x).equals("1")) {
                    sal = "1";
                }
            }

            // guardar la union de las salidas
            af2.salidas.add(p, sal);
            //guarda cada nueva (o no) transicion en el nuevo af
            //SI ES NUEVA TOCA LLENARLA USANDO ESTE METODO
            aux = this.organizarEstado(aux);
            af2.af[p][i] = aux;

            if (af2.estados.contains(aux) == false) {

                if (this.isTransicionNoDeterministica(aux)) {
                    af2.estados.add(aux);
                    this.unir(af2, aux);

                } else {

//               Duplicar la misma transición en af2 porque transicion = esget(i)
                    this.duplicar(af2, aux);

                }

            }

        }

    }

    /**
     * CREA EL NUEVO ESTADO A PARTIR  DE LAS TRANSICIONES STRINGS SIN REPETIR
     * @param a
     * @param b
     * @return 
     */
    public String comprobarUnion(String a, String b) {
        if (a.equals(b) || b == null) {
            return a;
        }

        String[] pB = b.split("-");
        List<String> partesB = new ArrayList<String>(Arrays.asList(pB));

        for (String x : partesB) {

            if (a.indexOf(x) == -1) {

                if (a.length() != 0) {
                    a = a + "-" + x;
                } else {
                    a = x;
                }

            }
        }
        System.out.println(a);
        return a;
    }

    /**
     * Comprueba si esta el mismo contenido pero desorganizado
     * @param a
     * @return 
     */
    public List<String> organizarEstado(String[] a) {
        List<String> listaA = Arrays.asList(a);
        Collections.sort(listaA);
        return listaA;
    }

    /**
     * comprueba si esta el mismo contenido pero desorganizado
     * @param a
     * @return 
     */
    public String organizarEstado(String a) {
        String b = "";
        List<String> listaA = this.organizarEstado(a.split("-"));
        Collections.sort(listaA);

        for (String x : listaA) {

            if (b.length() != 0) {
                b = b + "-" + x;
            } else {
                b = x;
            }
        }

        return b;
    }

    /**
     * Con base en el automata instanciado guarda este en un archivo de texto formato .af
     */
    public void guardarAF() {

        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        String fileExt = ".af";

        String ruta = fc.getSelectedFile().getPath() + fileExt;
        File archivo = new File(ruta);

        BufferedWriter bw = null;
        try {
            //Construct the BufferedWriter object
            bw = new BufferedWriter(new FileWriter(archivo));
            //Start writing to the output stream

            for (String x : simbolos) {
                bw.write(x + " ");
            }
            bw.newLine();
            for (String x : estados) {
                bw.write(x + " ");
            }
            bw.newLine();
            for (String x : salidas) {
                bw.write(x + " ");
            }
            bw.newLine();
            for (int i = 0; i <= estados.size() - 1; i++) {
                for (int j = 0; j <= simbolos.size() - 1; j++) {
                    bw.write(af[i][j] + " ");
                }
                bw.newLine();
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
//Close the BufferedWriter
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Dice si una transaccion es o no deterministica, para esto cuenta la cantidad de transacciones que tiene esta
     * @param transicion 
     * @return 
     */
    public Boolean isTransicionNoDeterministica(String transicion) {
        // VER SI UNA TRANSICION EN LA MATRIZ AF TIENE "-" OSEA QUE ES NO DETERMINISTICA
        //HACER OBVIAMENTE 
        if (transicion.length() > 3) {
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene la lista de salidas del automata
     * @return 
     */
    public List<String> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<String> salidas) {
        this.salidas = salidas;
    }

    /**
     * Obtiene el tipo de automata
     * @return true si es deterministico, false de lo contrario.
     */
    public boolean isDeterministico() {
        return deterministico;
    }

    public void setDeterministico(boolean deterministico) {
        this.deterministico = deterministico;
    }

    /**
     * Obtiene la lista de estados del automata
     * @return 
     */
    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    /**
     * Obtiene la lista de simbolos del automata
     * @return 
     */
    public List<String> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<String> simbolos) {
        this.simbolos = simbolos;
    }

    /**
     * Obtiene la matriz de transiciones
     * @return 
     */
    public String[][] getAf() {
        return af;
    }

    public void setAf(String[][] af) {
        this.af = af;
    }

    /**
     * Elimina los estados extra;os del automata haciendo el recorrido sobre este
     * @return Nuevo automata sin estados extra;os
     */
    public AF quitarExtraños() {

        System.out.println("QUITAR ESTADOS EXTRAÑOS");
        // Debe ser deterministica para que no salgan errores
        // recorrer estado inicial e ir preguntando 
        AF af2 = new AF();
        af2.simbolos = this.simbolos;
        this.extraños(af2, this.estados.get(0));
        return af2;
    }

    /**
     * Duplica la transicion y a;ade el estado en el af2
     * @param af2
     * @param transicion 
     */
    public void extraños(AF af2, String transicion) {
        String transicion2;
        System.out.println("metodo EXTRAÑOS " + transicion);
        //Si ya esta en af2 no hacer nada 
        // de lo contrario  
        if (af2.estados.contains(transicion) == false) {
            //posicion en af de transicion
            int i = this.estados.indexOf(transicion);
            //Se agrega a af2
            af2.salidas.add(this.salidas.get(i));
            af2.estados.add(transicion);
            //i2= posicion de donde fue agregado la transicion
            int i2 = af2.estados.size() - 1;

            for (int j = 0; j <= this.simbolos.size() - 1; j++) {

                transicion2 = this.af[i][j];

                if (transicion2 != null) {
                    // se guarda en af2 la nueva transicion
                    af2.af[i2][j] = transicion2;
                    //pregunta si ya existe no hace nada porque debe estar 
                    // si no existe en af2 entonces debe agregarla con este mismo metodo
                    //preguntando que tipo de transicion es 
                    if (af2.estados.contains(transicion2) == false) {
//                        //Duplicar la misma transición en af2 porque transicion = esget(i)
                        
                        this.extraños(af2, transicion2);

                    }
                }
            }

        }
    }

    /**
     * Con base en una hilera enviada como parametro dice si es valida o no en el contexto del automata
     * @param hilera que sera evaluada
     * @return true si es correcta, false de lo contrario
     */
    public boolean comprobarHilera(String hilera) {
        String estado = estados.get(0);
        int posicionEstado = -1;
        for (int i = 0; hilera.length() > i; i++) {
            posicionEstado = estados.indexOf(estado);
            int posicionSimbolo = simbolos.indexOf("" + hilera.charAt(i));
            estado = af[posicionEstado][posicionSimbolo];
            if (estado.equals("null")) {
                return false;
            }
        }
        return posicionEstado != -1 && salidas.get(posicionEstado).equals("1");
    }

    /**
     * Obtiene una lista con el conjunto de estados que son equivalentes
     * @return Lista con estados que son equivalentes
     */
    public HashSet<HashSet<String>> getEstadosEquivalentes() {
        HashSet<HashSet<String>> particiones = primeraParticion();
        particiones = partir(particiones);
        return particiones;
    }

    /**
     * Despues de realizar la primera particion (que se hace evaluando los que tengan las mismas salidas) se parte nuevamente hasta que se haga una pasada completa
     * @param particiones lista con los conjuntos de estados con salidas 0 y 1
     * @return Lista con estados que son equivalentes
     */
    private HashSet<HashSet<String>> partir(HashSet<HashSet<String>> particiones) {
        for (int i = 0; i < particiones.size(); i++) {
            HashSet<String> particion = (HashSet) getElementForPosition(particiones, i);
            int j = 0;
            for (String simbolo : simbolos) {
                ArrayList<String> transiciones = getTransiciones(particion, j);
                ArrayList<String> numerosTransiciones = getNumerosTransicion(transiciones, particiones);
                HashSet<HashSet<String>> newParticiones = getNewParticiones(numerosTransiciones, particion);
                if (newParticiones.size() > 1) {
                    particiones.remove(particion);
                    particiones.addAll(newParticiones);
                    i = -1;
                    break;
                }
                j++;
            }
        }
        return particiones;
    }

    /**
     *Realiza la primera particion de los estados, un conjunto de aceptacion y otro de rechazo
     * @return dos conjuntos de estados
     */
    private HashSet<HashSet<String>> primeraParticion() {
        HashSet<HashSet<String>> particiones = new HashSet();
        HashSet<String> particion0 = new HashSet();
        HashSet<String> particion1 = new HashSet();
        int i = 0;
        for (String salida : salidas) {
            if (salida.equals("0")) {
                particion0.add(estados.get(i));
            } else {
                if (salida.equals("1")) {
                    particion1.add(estados.get(i));
                }
            }
            i++;
        }
        if (!particion0.isEmpty()) {
            particiones.add(particion0);
        }
        if (!particion1.isEmpty()) {
            particiones.add(particion1);
        }
        return particiones;
    }

    /**
     * Obtiene el elemento que se encuentra en la posicion de la lista enviada como parametro
     * @param set Lista de elementos de los cuales se debe sacar el elemento objeto de la posicion
     * @param elementPosition
     * @return
     */
    public Object getElementForPosition(HashSet set, int elementPosition) {
        int i = 0;
        for (Iterator<Object> it = set.iterator(); it.hasNext();) {
            Object item = it.next();
            if (elementPosition == i) {
                return item;
            }
            i++;
        }
        return null;
    }

    /**
     * Busca el elemento enviado como parametro en la lista tambien enviada como parametro
     * @param set
     * @param element
     * @return la posicion del elemento encontrado, -1 si no encuentra ninguno
     */
    public int indexOf(HashSet set, Object element) {
        int i = 0;
        for (Iterator<Object> it = set.iterator(); it.hasNext();) {
            Object item = it.next();
            if (item.equals(element)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Obtiene las transiciones hacia las cuales debe irse una particion
     * @param particion 
     * @param j
     * @return
     */
    private ArrayList<String> getTransiciones(HashSet<String> particion, int j) {
        ArrayList<String> transiciones = new ArrayList();
        String transicion;
        for (String estado : particion) {
            int posicionEstado = getPosicionEstado(estado);
            transiciones.add(af[posicionEstado][j]);
        }
        return transiciones;
    }

    /**
     * Busca en los estados del automata el estado enviado como parametro y devuelve la ubicacion de este
     * @param estado
     * @return
     */
    private int getPosicionEstado(String estado) {
        for (int i = 0; estados.size() > i; i++) {
            if (estados.get(i).equals(estado)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Obtiene el numero de la particion en la cual se encuentra la transicion enviada como parametro
     * @param transicion
     * @param particiones
     * @return 
     */
    private String getNumeroTransicion(String transicion, HashSet<HashSet<String>> particiones) {
        int i = 0;
        for (HashSet<String> particion : particiones) {
            if (particion.contains(transicion)) {
                return i + "";
            }
            i++;
        }
        return -1 + "";
    }

    /**
     * Obtiene la lista de todas las numeros de la particion en las cuales se encuentran las transiciones enviadas como parametro
     * @param transiciones
     * @param particiones
     * @return 
     */
    private ArrayList<String> getNumerosTransicion(ArrayList<String> transiciones, HashSet<HashSet<String>> particiones) {
        ArrayList<String> numerosTransiciones = new ArrayList();
        for (String transicion : transiciones) {
            numerosTransiciones.add(getNumeroTransicion(transicion, particiones));
        }
        return numerosTransiciones;
    }

    /**
     * Obtiene las transiciones que deben 
     * @param numerosTransiciones
     * @param particion
     * @return 
     */
    private HashSet<HashSet<String>> getNewParticiones(ArrayList<String> numerosTransiciones, HashSet<String> particion) {
        HashSet<String> copiaParticion = new HashSet();
        copiaParticion.addAll(particion);
        HashSet<HashSet<String>> nuevasParticiones = new HashSet();
        HashSet<String> nuevaParticion;
        while (!numerosTransiciones.isEmpty()) {
            String transicionActual = numerosTransiciones.get(0);
            nuevaParticion = new HashSet();
            while (numerosTransiciones.indexOf(transicionActual) != -1) {
                String estado = (String) getElementForPosition(copiaParticion, numerosTransiciones.indexOf(transicionActual));
                copiaParticion.remove(estado);
                numerosTransiciones.remove(transicionActual);
                nuevaParticion.add(estado);
            }
            nuevasParticiones.add(nuevaParticion);
        }

        return nuevasParticiones;
    }

    /**
     * Realiza la simplificacion del automata por estados equivalentes, primero
     * se deben de sacar estados extranos
     *
     * @return Automata finito simplificado
     * @since 1.0
     */
    public AF simplificarPorEstadosEquivalentes() {
        AF mAFSimplificado = new AF();
        mAFSimplificado.deterministico = this.deterministico;
        mAFSimplificado.simbolos.addAll(this.simbolos);
        HashSet<HashSet<String>> estadosEquivalentes = this.getEstadosEquivalentes();
        StringBuilder nuevoEstado;
        for (HashSet<String> estadoEquivalente : estadosEquivalentes) {
            nuevoEstado = new StringBuilder("<");
            int posicionEstado = getPosicionPrimerEstado(estadoEquivalente);
            mAFSimplificado.salidas.add(this.salidas.get(posicionEstado));
            for (String estado : estadoEquivalente) {
                estado = estado.replace("<", "").replace(">", "");
                nuevoEstado.append(estado);
            }
            nuevoEstado.append(">");
            mAFSimplificado.estados.add(nuevoEstado.toString());
        }
        int i = 0, j = 0;
        for (String estado : mAFSimplificado.estados) {
            j = 0;
            for (String simbolo : mAFSimplificado.simbolos) {
                mAFSimplificado.af[i][j] = getTransicion(estado, simbolo, mAFSimplificado.estados);
                j++;
            }
            i++;
        }
        return mAFSimplificado;
    }

    /**
     * Obtiene la posicion del primer estadoEstado equivalente en la lista de
     * estados sin simplificar para obtener su
     *
     * @param estadoEquivalente lista con los estados
     * @return
     */
    private int getPosicionPrimerEstado(HashSet<String> estadoEquivalente) {
        for (String estado : estadoEquivalente) {
            return getPosicionEstado(estado);
        }
        return -1;
    }

    /**
     * Obtiene la transicion tal cual debe quedar con los estados equivalentes
     * @param estado
     * @param simbolo
     * @param estados1
     * @return 
     */
    private String getTransicion(String estado, String simbolo, List<String> estados1) {
        int i = estados.indexOf("<" + estado.charAt(1) + ">");
        int j = simbolos.indexOf(simbolo);
        String estadoViejo = af[i][j];
        for (String estadoNuevo : estados1) {
            if (estadoNuevo.contains(estadoViejo.replace("<", "").replace(">", ""))) {
                return estadoNuevo;
            }
        }
        return null;
    }
}
