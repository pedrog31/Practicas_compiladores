/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

/**
 *
 * @author pedro
 */
public class maine {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Produccion produccion = new Produccion ("55. <s> -> b<nulo>");
        produccion.getPartes().forEach((s) -> {
            System.out.println(s);
        });
        System.out.println(produccion.toString());
    }
}
