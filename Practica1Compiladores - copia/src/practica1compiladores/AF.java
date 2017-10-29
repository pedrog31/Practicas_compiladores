/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meli
 */
public class AF {

    private List<String> estados;
    private List<String> simbolos;
    private String[][] af;

    public AF(Gramatica g) {
        af = new String[10][10];
        estados = new ArrayList();
        simbolos = new ArrayList();

        //Recorrer gramatica y llenar matriz
        int pEstado, pEstado2, pSimbolo;
        String nTerminal, nTerminal2, simbolo;
        List<Produccion> producciones = g.getProducciones();

        for (Produccion i : producciones) {
            // System.out.println(i);

            if (i.isOfAcceptance()) {
                // Si es de aceptación, 
                nTerminal = i.getPartes(1);
                pEstado = estados.indexOf(nTerminal);

                if (pEstado == -1) {
                    estados.add(nTerminal);
                    pEstado = estados.size();
                }

                af[9][pEstado] = "1";

            } else {
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

                af[pEstado][pSimbolo] = nTerminal2;

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
