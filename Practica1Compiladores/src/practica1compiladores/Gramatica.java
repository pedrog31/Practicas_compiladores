/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

/**
 * Representa una gramatica 
 * @author pedro
 */
public class Gramatica {

    /**
     * Representa cada una de las producciones que representan la gramatica
     */
    private List<Produccion> producciones;
    
    /**
     * Instancia una nueva gramatica vacia inicializando la lista de producciones
     */
    public Gramatica() {
        producciones = new ArrayList();
    }

    /**
     * Cuenta en la lista el numero de no terminales que se encuentran en la gramatica
     * @return numero de no terminales presentes en la gramatica
     */
    public int getnNoTerminales() {
        return getAllNoTerminales().size();
    }
    
    /**
     * Cuenta en la lista el numero de terminales que se encuentran en la gramatica
     * @return numero de terminales presentes en la gramatica
     */
    public int getnTerminales() {
        HashSet<String> terminales = new HashSet();
        for (Produccion produccion: producciones) {
            for (String parte: produccion.getPartes()) {
                if (produccion.isNonTerminal(parte))
                    terminales.add(parte);
            }
        }
        return terminales.size();
    }

    /**
     * Dice si la gramatica es regular o no 
     * @return verdadero si todas las producciones de la gramatica son de la forma especial
     */
    public Boolean getIsRegular() {
       return producciones.stream().noneMatch((produccion) -> (!produccion.isSpecial()));
    }
    
    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    /**
     * Lee y mapea de un archivo con formato .gra la gramatica guardada
     * @param ruta Ruta al archivo en donde se encuentra la gramatica
     * @throws FileNotFoundException 
     */
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
    
    /**
     * Obtiene los no terminales alcanzables despues del recorrido, se usa para simplificar la gramatica
     * @return 
     */
    public Set<String> getNoTerminalesAlcanzables () {
        Set<String> alcanzables = new HashSet();
        alcanzables.add(producciones.get(0).getPartes(0));
        alcanzables = addAlcanzables(alcanzables);
        return alcanzables;
    }
    
    /**
     * Obtiene los no terminales muertos eliminando del total de no terminales los muertos, se usa para simplificar la gramatica
     * @param vivos
     * @return 
     */
    public Set<String> getNoTerminalesMuertos (Set<String> vivos){
        Set<String> muertos;
        muertos = getAllNoTerminales();
        muertos.removeAll(vivos);
        return muertos;
    }
    
    /**
     * Obtiene los terminales vivos llamando al metodo recursivo add vivos
     * @return no terminales vivos
     */
    public Set<String> getNoTerminalesVivos (){
        Set<String> vivos = new HashSet();
        vivos = addVivos(vivos);
        return vivos;
    }
    
    /**
     * Obtiene todos los no terminales de la gramatica
     * @return 
     */
    public Set<String> getAllNoTerminales () {
        Set<String> noTerminales = new HashSet();
        producciones.forEach((produccion) -> {
            noTerminales.add(produccion.getPartes(0));
        });
        return noTerminales;
    }

    /**
     * Metodo recursivo para a;adir los nuevos no terminales vivos de la gramatica
     * @param vivos lista de no terminales vivos, se agrega uno por llamada al metodo
     * @return No terminales vivos
     */
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

    /**
     * Metodo recursivo para a;adir los nuevos no terminales alcanzables de la gramatica
     * @param vivos lista de no terminales alcanzables, se agrega uno por llamada al metodo
     * @return No terminales alcanzables
     */
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
    
    /**
     * tomando los no terminales alcanzables saca todos los no terminales y removiendolos alcanzables quedando los que no lo son
     * @param alcanzables
     * @return 
     */
    public Set<String> getNoTerminalesInalcanzables (Set<String> alcanzables){
        Set<String> inalcanzables;
        inalcanzables = getAllNoTerminales();
        inalcanzables.removeAll(alcanzables);
        return inalcanzables;
    }
    
    /**
     * Llama a los demas metodos de la clase que ayudan a simplificar la gramatica
     * @param muertos
     * @param inalcanzables
     * @return 
     */
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

    /**
     * guarda en un archivo de texto la gramatica
     */
    public void guardar() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        BufferedWriter bw = null;
        try {
            String fileExt= ".gra";
            String ruta = fc.getSelectedFile().getPath()+fileExt;
            File archivo = new File(ruta);
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(toString());
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
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

    
}
