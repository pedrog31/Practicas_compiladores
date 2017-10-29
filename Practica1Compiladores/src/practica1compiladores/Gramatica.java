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
    
    public Gramatica() {
        producciones = new ArrayList();
    }

    public int getnNoTerminales() {
        for (Produccion produccion: producciones) {
            
        }
        return 0;
    }
    public int getnTerminales() {
        return 0;
    }

    public Boolean getIsRegular() {
       return producciones.stream().noneMatch((produccion) -> (!produccion.isSpecial()));
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
    
    public Set<String> getNoTerminalesAlcanzables () {
        Set<String> alcanzables = new HashSet();
        alcanzables.add(producciones.get(0).getPartes(0));
        alcanzables = addAlcanzables(alcanzables);
        return alcanzables;
    }
    
    public Set<String> getNoTerminalesMuertos (Set<String> vivos){
        Set<String> muertos;
        muertos = getAllNoTerminales();
        muertos.removeAll(vivos);
        return muertos;
    }
    
    public Set<String> getNoTerminalesVivos (){
        Set<String> vivos = new HashSet();
        vivos = addVivos(vivos);
        return vivos;
    }
    
    public Set<String> getAllNoTerminales () {
        Set<String> noTerminales = new HashSet();
        producciones.forEach((produccion) -> {
            noTerminales.add(produccion.getPartes(0));
        });
        return noTerminales;
    }

    private Set<String> addVivos(Set<String> vivos) {
        Boolean added;
        for (Produccion produccion: producciones) {
            added = true;
            for (String part: produccion.getRightParts()) {
                if (!(vivos.contains(part) || produccion.isTerminal(part))){
                    added = false;
                    break;
                }
            }
            if (added && vivos.add(produccion.getPartes(0))){
             vivos = addVivos(vivos);
             break;
            }
        }
        return vivos;
    }

    private Set<String> addAlcanzables(Set<String> alcanzables) {
        int size1 = alcanzables.size();
        for (Produccion produccion: producciones) {
            if (alcanzables.contains(produccion.getPartes(0))) {
                for (String part: produccion.getRightParts()){
                    if(produccion.isNonTerminal(part)){
                        alcanzables.add(part);
                    }
                }
            }else{
                if (alcanzables.size() > size1)
                    alcanzables = addAlcanzables(alcanzables);
                break;
            }
        }
        return alcanzables;
    }
    
    public Set<String> getNoTerminalesInalcanzables (Set<String> alcanzables){
        Set<String> inalcanzables;
        inalcanzables = getAllNoTerminales();
        inalcanzables.removeAll(alcanzables);
        return inalcanzables;
    }
    
    public Gramatica simplificarGramatica (Set<String> muertos, Set<String> inalcanzables) {
        Gramatica gramatica = new Gramatica();
        Boolean eliminable;
        for (Produccion produccion: producciones) {
            eliminable = false;
            List<String> partes = produccion.getPartes();
            for (String muerto: muertos) {
                if (partes.contains(muerto)){
                    eliminable = true;
                    break;
                }
            }
            for (String inalcanzable: inalcanzables) {
                if (partes.contains(inalcanzable)){
                    eliminable = true;
                    break;
                }
            }
            if (!eliminable) {
                Produccion produccionNueva = new Produccion(gramatica.getProducciones().size()+1);
                produccionNueva.setPartes(produccion.getPartes());
                gramatica.getProducciones().add(produccionNueva);
            }
                
        }
        return gramatica;
    }

    @Override
    public String toString() {
        StringBuilder gra = new StringBuilder();
        producciones.forEach((produccion) -> {
            gra.append(produccion.toString()).append("\n");
        });
        return gra.toString();
    }

    
}
