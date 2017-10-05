/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author pedro
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
//        Produccion produccion = new Produccion ("55. <s> -> b<nulo>");
//        produccion.getPartes().forEach((s) -> {
//            System.out.println(s);
//        });
//        System.out.println(produccion.toString());
        
        Gramatica gra = new Gramatica();
         JFileChooser fc= new JFileChooser();
         fc.showOpenDialog(fc);
         gra.leerGramatica(fc.getSelectedFile().getPath());
         
         
   
         AF af = new AF(gra);
         af.imprimirAF();

    }
  
}
