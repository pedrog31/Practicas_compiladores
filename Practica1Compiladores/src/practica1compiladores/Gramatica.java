/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.io.*;
import java.util.*;

/**
 *
 * @author pedro
 */
public class Gramatica {

    private List<Produccion> producciones;
    private int nNoTerminales;
    private int nTerminales;
    private Boolean isRegular;

    public int getnNoTerminales() {
        return nNoTerminales;
    }

    public void setnNoTerminales(int nNoTerminales) {
        this.nNoTerminales = nNoTerminales;
    }

    public int getnTerminales() {
        return nTerminales;
    }

    public void setnTerminales(int nTerminales) {
        this.nTerminales = nTerminales;
    }

    public Boolean getIsRegular() {
        return producciones.stream().noneMatch((produccion) -> (!produccion.isSpecial()));
    }

    public void setIsRegular(Boolean isRegular) {
        this.isRegular = isRegular;
    }

    public Gramatica() {
        producciones = new ArrayList();
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    public void leerGramatica(String ruta) throws FileNotFoundException {
        Scanner archivo;
        try {
            archivo = new Scanner(new File(ruta));
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no existe en la misma carpeta del ejecutable."+ex);
            return;
        }
        if (archivo.hasNextLine()) {
            String linea;
            while (archivo.hasNextLine()) {
                linea = archivo.nextLine();
                producciones.add(new Produccion (linea));
            }
        } else producciones = null;
    }

}
