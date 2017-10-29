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
    
    public AF(){    
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
            // System.out.println(i);

            if (i.isOfAcceptance()) {
                // Si es de aceptación, 
                nTerminal = i.getPartes(2);
                pEstado = estados.indexOf(nTerminal);
                salidas.add("1");

                if (pEstado == -1) {
                    System.out.println("No puede haber estado de aceptación sin ninguna transición");
                }

            } else {
                salidas.add("0");
                nTerminal = i.getPartes(2);
                simbolo = i.getPartes(5);
                nTerminal2 = i.getPartes(6);

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

    public AF convertirAf(){
       AF af2 = new AF();
       
       
    


    
    return af2;
}
    
    public void unir(AF af, String transicion){
        System.out.println("--UNIÓN--");
        String partes[] = transicion.split(",");
        
       for(int i=0; i<=this.simbolos.size()-1;i++){
        
//           for (int j=0 ; j<=partes.length-1; j++ ){
//               
//               int x = this.estados.indexOf(partes[i]);            
//               System.out.println("Union [x][i] "+this.af[x][i]);
//                 System.out.println("Union [i][x] "+this.af[i][x]);
//           }
       
       }
        
    }

    public String comprobarUnion(String a, String b){
        String newEstado="";

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
        String fileExt= ".af";

        String ruta = fc.getSelectedFile().getPath()+fileExt;
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

}
