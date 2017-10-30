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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Meli
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

    public AF() {
        af = new String[22][22];
        estados = new ArrayList();
        simbolos = new ArrayList();
        salidas = new ArrayList();
        estadosNuevos = new ArrayList();
        deterministico = true;

    }

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
                    aux = (af[pEstado][pSimbolo] + (",") + nTerminal2);
                    af[pEstado][pSimbolo] = aux;
                    estadosNuevos.add(aux);
                    System.out.println(aux);
                }
            }

        }

    }

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

    public void llenarAF(int where, String linea) {

        //llena af dependiendo si es o no estado, transicion o salida
        System.out.println("LINEA " + linea);

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

    public AF convertirAf() {
        AF af2 = new AF();

        return af2;
    }

    public void unir(AF afNuevo, String transicion) {

        // esto creo que junta muchos estados de una transicion no deterministica y los vuelve uno agregandolos al nuevo af
        System.out.println("--UNIÓN--");
        String partes[] = transicion.split(",");

        for (int i = 0; i <= this.simbolos.size() - 1; i++) {

//           for (int j=0 ; j<=partes.length-1; j++ ){
//               
//               int x = this.estados.indexOf(partes[i]);            
//               System.out.println("Union [x][i] "+this.af[x][i]);
//                 System.out.println("Union [i][x] "+this.af[i][x]);
//           }
        }

    }

    public String comprobarUnion(String a, String b) {
        String newEstado = "";

        String[] pA = a.split(",");
        List<String> partesA = new ArrayList<String>(Arrays.asList(pA));
        System.out.println(partesA);
        String[] pB = b.split(",");
        List<String> partesB = new ArrayList<String>(Arrays.asList(pB));
        System.out.println(partesB);

        return newEstado;
    }

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

    public void imprimirAF() {

        System.out.println("");
        for (String x : simbolos) {
            System.out.print("     " + x);
        }
        System.out.println("");
        for (int i = 0; i <= estados.size() - 1; i++) {
            System.out.print(estados.get(i) + " ");
            for (int j = 0; j <= simbolos.size() - 1; j++) {
                System.out.print(af[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("___________________________");
        System.out.println("simbolos " + simbolos);
        System.out.println("estados " + estados);
        System.out.println("simbolos nuevos " + estadosNuevos);

    }

    public List<String> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<String> salidas) {
        this.salidas = salidas;
    }

    public boolean isDeterministico() {
        return deterministico;
    }

    public void setDeterministico(boolean deterministico) {
        this.deterministico = deterministico;
    }

    public List<String> getEstados() {
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    public List<String> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<String> simbolos) {
        this.simbolos = simbolos;
    }

    public String[][] getAf() {
        return af;
    }

    public void setAf(String[][] af) {
        this.af = af;
    }

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

    public HashSet<HashSet<String>> getEstadosEquivalentes() {
        HashSet<HashSet<String>> particiones = primeraParticion();
        particiones = partir(particiones);
        return particiones;
    }

    private HashSet<HashSet<String>> partir(HashSet<HashSet<String>> particiones) {
        for (int i = 0; i < particiones.size(); i++) {
            HashSet<String> particion = (HashSet) getElementForPosition(particiones, i);
            int j = 0;
            for (String simbolo : simbolos) {
                ArrayList<String> transiciones = getTransiciones(particion, j);
                ArrayList<String> numerosTransiciones = getNumerosTransicion(transiciones, particiones);
                HashSet<HashSet<String>> newParticiones = getNewParticiones(numerosTransiciones, particion);
                if (newParticiones.size() > 1){
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
     * funciona melo
     *
     * @return
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
     * funciona melo
     *
     * @param set
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
     * funciona melo
     *
     * @param set
     * @param element
     * @return
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
     * Funciona melo
     *
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
     * funciona melo
     *
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

    private String getNumeroTransicion(String transicion, HashSet<HashSet<String>> particiones) {
        int i = 0;
        for (HashSet<String> particion : particiones) {
            if (particion.contains(transicion)) {
                return i+"";
            }
            i++;
        }
        return -1+"";
    }

    private ArrayList<String> getNumerosTransicion(ArrayList<String> transiciones, HashSet<HashSet<String>> particiones) {
        ArrayList<String> numerosTransiciones = new ArrayList();
        for (String transicion : transiciones) {
            numerosTransiciones.add(getNumeroTransicion(transicion, particiones));
        }
        return numerosTransiciones;
    }

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

    private boolean validar(HashSet<String> nuevaParticion, HashSet<HashSet<String>> nuevasParticiones) {
        for (String estadoParticion: nuevaParticion) {
            for (HashSet<String> particion: nuevasParticiones) {
                if (particion.contains(estadoParticion))
                    return false;
            }
        }
        return true;
    }

}
