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
 * @author pedro
 */
public class Produccion {
    private int id;
    private List<String> partes;

    public Produccion(int id) {
        this.id = id;
        partes = new ArrayList();
    }
    
    public Produccion (String textParts) {
        id = Integer.parseInt(textParts.substring(0,textParts.indexOf(".")));
        reconocerProduccion(textParts);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getPartes() {
        return partes;
    }

    public void setPartes(List<String> partes) {
        this.partes = partes;
    }
    
    public void addPart (String part) {
        partes.add(part);
    }
    
    public void deleteLastPart () {
        partes.remove(partes.size()-1);
    }

    @Override
    public String toString() {
        StringBuilder textParts = new StringBuilder(id+". ");
        partes.forEach((part) -> {
            textParts.append(part);
        });
        return textParts.toString();
    }

    private void reconocerProduccion(String textParts) {
        partes = new ArrayList();
        for (int i = textParts.indexOf(" ")+1; i < textParts.length(); i++) {
            char point = textParts.charAt(i);
            String part;
            switch (point) {
                case '<': 
                    int j = textParts.indexOf('>',i);
                    part = textParts.substring(i,j+1);
                    i = j;
                    break;
                    
                case ' ':
                    part = " -> ";
                    i = i + 3;
                    break;
                
                default:
                    part = Character.toString(point);
                    break;
            }
            partes.add(part);
        }
    }
    
    public boolean isTerminal (String part) {
        return !part.contains(">");
    }
}
