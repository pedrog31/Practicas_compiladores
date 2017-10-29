/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1compiladores.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import practica1compiladores.AF;
import practica1compiladores.Gramatica;
import practica1compiladores.Produccion;

/**
 *
 * @author pedro
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    DefaultListModel modelo;
    Gramatica gramatica;
    Produccion produccionActual;
    Boolean editing = false;
    AF af;

    public MainJFrame() {
        initComponents();

        jConvertirAF.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jPanelCreacionGramatica = new javax.swing.JPanel();
        jButtonNuevaGramatica = new javax.swing.JButton();
        jScrollPaneGramatica = new javax.swing.JScrollPane();
        jListGramatica = new javax.swing.JList<>();
        jButtonNuevaProduccion = new javax.swing.JButton();
        jLabelNumeroProduccion = new javax.swing.JLabel();
        jButtonNuevoTerminal = new javax.swing.JButton();
        jButtonNuevoNoTerminal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneProduccionSeleccionada = new javax.swing.JTextPane();
        jButtonBorrarUltimo = new javax.swing.JButton();
        jButtonSecuenciaNula = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jPanelSimplificacionGramatica = new javax.swing.JPanel();
        simplificar = new javax.swing.JButton();
        noterminalesVivos = new javax.swing.JLabel();
        noTerminalesMuertos = new javax.swing.JLabel();
        noTerminalesAlcanzables = new javax.swing.JLabel();
        noTerminalesInalcanzables = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gramaticaSimplificada = new javax.swing.JTextArea();
        jPanelAutomataFinito = new javax.swing.JPanel();
        generarAF = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        tipoAf = new javax.swing.JLabel();
        labelTipo = new javax.swing.JToggleButton();
        guardarAf = new javax.swing.JToggleButton();
        cargarAF = new javax.swing.JToggleButton();
        jConvertirAF = new javax.swing.JToggleButton();
        jPanelReconocimientoHilera = new javax.swing.JPanel();

        jToggleButton1.setText("jToggleButton1");

        jToggleButton2.setText("jToggleButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonNuevaGramatica.setText("Nueva gramatica");
        jButtonNuevaGramatica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaGramaticaActionPerformed(evt);
            }
        });

        jListGramatica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListGramaticaMouseClicked(evt);
            }
        });
        jScrollPaneGramatica.setViewportView(jListGramatica);

        jButtonNuevaProduccion.setText("Agregar produccion");
        jButtonNuevaProduccion.setActionCommand("");
        jButtonNuevaProduccion.setEnabled(false);
        jButtonNuevaProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaProduccionActionPerformed(evt);
            }
        });

        jLabelNumeroProduccion.setText("Produccion: ");

        jButtonNuevoTerminal.setText("Nuevo terminal");
        jButtonNuevoTerminal.setEnabled(false);
        jButtonNuevoTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoTerminalActionPerformed(evt);
            }
        });

        jButtonNuevoNoTerminal.setText("Nuevo no terminal");
        jButtonNuevoNoTerminal.setEnabled(false);
        jButtonNuevoNoTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoNoTerminalActionPerformed(evt);
            }
        });

        jTextPaneProduccionSeleccionada.setEditable(false);
        jScrollPane1.setViewportView(jTextPaneProduccionSeleccionada);

        jButtonBorrarUltimo.setText("Borrar ultimo");
        jButtonBorrarUltimo.setEnabled(false);
        jButtonBorrarUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarUltimoActionPerformed(evt);
            }
        });

        jButtonSecuenciaNula.setText("Secuencia nula");
        jButtonSecuenciaNula.setEnabled(false);
        jButtonSecuenciaNula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSecuenciaNulaActionPerformed(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setEnabled(false);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCreacionGramaticaLayout = new javax.swing.GroupLayout(jPanelCreacionGramatica);
        jPanelCreacionGramatica.setLayout(jPanelCreacionGramaticaLayout);
        jPanelCreacionGramaticaLayout.setHorizontalGroup(
            jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonNuevaGramatica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneGramatica)
                    .addComponent(jButtonNuevaProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBorrarUltimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                        .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNumeroProduccion)
                            .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                                .addComponent(jButtonNuevoTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonNuevoNoTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(jButtonGuardar))
                            .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSecuenciaNula, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanelCreacionGramaticaLayout.setVerticalGroup(
            jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCreacionGramaticaLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabelNumeroProduccion))
                    .addComponent(jButtonGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jButtonBorrarUltimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCreacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevoTerminal)
                    .addComponent(jButtonNuevoNoTerminal)
                    .addComponent(jButtonSecuenciaNula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNuevaProduccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneGramatica, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNuevaGramatica))
        );

        jTabbedPaneMain.addTab("Creacion gramatica", jPanelCreacionGramatica);

        simplificar.setText("Simplificar gramatica");
        simplificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simplificarActionPerformed(evt);
            }
        });

        noterminalesVivos.setText("No terminales vivos");

        noTerminalesMuertos.setText("No terminales muertos");

        noTerminalesAlcanzables.setText("No terminales alcanzables");

        noTerminalesInalcanzables.setText("No terminales inalcanzables");

        jLabel6.setText("Gramatica");

        gramaticaSimplificada.setEditable(false);
        gramaticaSimplificada.setColumns(20);
        gramaticaSimplificada.setRows(5);
        jScrollPane2.setViewportView(gramaticaSimplificada);

        javax.swing.GroupLayout jPanelSimplificacionGramaticaLayout = new javax.swing.GroupLayout(jPanelSimplificacionGramatica);
        jPanelSimplificacionGramatica.setLayout(jPanelSimplificacionGramaticaLayout);
        jPanelSimplificacionGramaticaLayout.setHorizontalGroup(
            jPanelSimplificacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimplificacionGramaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSimplificacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simplificar, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(jPanelSimplificacionGramaticaLayout.createSequentialGroup()
                        .addComponent(noTerminalesMuertos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noTerminalesInalcanzables))
                    .addGroup(jPanelSimplificacionGramaticaLayout.createSequentialGroup()
                        .addComponent(noterminalesVivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noTerminalesAlcanzables))
                    .addGroup(jPanelSimplificacionGramaticaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanelSimplificacionGramaticaLayout.setVerticalGroup(
            jPanelSimplificacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSimplificacionGramaticaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanelSimplificacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noterminalesVivos)
                    .addComponent(noTerminalesAlcanzables))
                .addGap(18, 18, 18)
                .addGroup(jPanelSimplificacionGramaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noTerminalesMuertos)
                    .addComponent(noTerminalesInalcanzables))
                .addGap(65, 65, 65)
                .addComponent(simplificar)
                .addContainerGap())
        );

        jTabbedPaneMain.addTab("Simplificacion gramatica", jPanelSimplificacionGramatica);

        generarAF.setText("Cargar Grámatica");
        generarAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarAFActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setGridColor(new java.awt.Color(204, 204, 204));
        jTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTable);

        tipoAf.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        tipoAf.setText("Tipo:");

        labelTipo.setSelected(true);
        labelTipo.setContentAreaFilled(false);
        labelTipo.setEnabled(false);
        labelTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTipoActionPerformed(evt);
            }
        });

        guardarAf.setText("Guardar");
        guardarAf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAfActionPerformed(evt);
            }
        });

        cargarAF.setText("Cargar AF");
        cargarAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarAFActionPerformed(evt);
            }
        });

        jConvertirAF.setText("Convertir");

        javax.swing.GroupLayout jPanelAutomataFinitoLayout = new javax.swing.GroupLayout(jPanelAutomataFinito);
        jPanelAutomataFinito.setLayout(jPanelAutomataFinitoLayout);
        jPanelAutomataFinitoLayout.setHorizontalGroup(
            jPanelAutomataFinitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAutomataFinitoLayout.createSequentialGroup()
                .addGroup(jPanelAutomataFinitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAutomataFinitoLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanelAutomataFinitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoAf, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelAutomataFinitoLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanelAutomataFinitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cargarAF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(guardarAf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(generarAF, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                            .addComponent(labelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelAutomataFinitoLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jConvertirAF)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanelAutomataFinitoLayout.setVerticalGroup(
            jPanelAutomataFinitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAutomataFinitoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(generarAF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cargarAF)
                .addGap(18, 18, 18)
                .addComponent(guardarAf)
                .addGap(14, 14, 14)
                .addComponent(tipoAf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTipo)
                .addGap(18, 18, 18)
                .addComponent(jConvertirAF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAutomataFinitoLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jTabbedPaneMain.addTab("Automata finito", jPanelAutomataFinito);

        javax.swing.GroupLayout jPanelReconocimientoHileraLayout = new javax.swing.GroupLayout(jPanelReconocimientoHilera);
        jPanelReconocimientoHilera.setLayout(jPanelReconocimientoHileraLayout);
        jPanelReconocimientoHileraLayout.setHorizontalGroup(
            jPanelReconocimientoHileraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 756, Short.MAX_VALUE)
        );
        jPanelReconocimientoHileraLayout.setVerticalGroup(
            jPanelReconocimientoHileraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jTabbedPaneMain.addTab("Reconocimiento hilera", jPanelReconocimientoHilera);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneMain)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevaGramaticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaGramaticaActionPerformed
        String[] options = {"Nueva gramatica", "Cargar desde Archivo de texto"};
        int seleccion = JOptionPane.showOptionDialog(rootPane,
                "Desea crear una nueva gramatica o cargarla desde un archivo de texto?",
                "Cargar gramatica", WIDTH, HEIGHT, null, options, NORMAL);
        gramatica = new Gramatica();
        modelo = new DefaultListModel();
        if (seleccion == 1) {
            JFileChooser mJFileChooser = new JFileChooser();
            if (mJFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    gramatica.leerGramatica(mJFileChooser.getSelectedFile().getPath());
                    jButtonNuevoNoTerminal.setEnabled(true);
                    gramatica.getProducciones().forEach((produccion) -> {
                        modelo.addElement(produccion.toString());
                    });
                    jListGramatica.setModel(modelo);
                } catch (FileNotFoundException ex) {
                }
            }
        } else {
            produccionActual = new Produccion(1);
            jListGramatica.setModel(modelo);
            jButtonNuevoNoTerminal.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonNuevaGramaticaActionPerformed

    private void jButtonNuevaProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaProduccionActionPerformed
        if (editing) {
            jTextPaneProduccionSeleccionada.setText("");
            editing = false;
        } else {
            gramatica.getProducciones().add(produccionActual);
            modelo.addElement(produccionActual.toString());
            jListGramatica.setModel(modelo);
        }
        jButtonBorrarUltimo.setEnabled(true);
        jButtonNuevoNoTerminal.setEnabled(true);
        produccionActual = new Produccion(gramatica.getProducciones().size() + 1);
        jButtonNuevaProduccion.setEnabled(produccionActual.isCorrect());
    }//GEN-LAST:event_jButtonNuevaProduccionActionPerformed

    private void jButtonNuevoTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoTerminalActionPerformed
        String terminal = JOptionPane.showInputDialog(rootPane, "Inserte el terminal: \n-Solo un caracter\n-letras minusculas o numeros", "Nuevo terminal", JOptionPane.OK_OPTION).substring(0, 1).toLowerCase();
        Pattern pat = Pattern.compile("\\w");
        Matcher mat = pat.matcher(terminal);
        if (mat.matches()) {
            jButtonSecuenciaNula.setEnabled(false);
            produccionActual.addPart(terminal);
            jTextPaneProduccionSeleccionada.setText(produccionActual.toString().substring(produccionActual.toString().indexOf(" ")));
            jButtonNuevaProduccion.setEnabled(produccionActual.isCorrect());
        } else {
            JOptionPane.showMessageDialog(rootPane, "\n-Solo un caracter\n-letras minusculas o numeros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonNuevoTerminalActionPerformed

    private void jButtonNuevoNoTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoNoTerminalActionPerformed
        jButtonSecuenciaNula.setEnabled(false);
        String noTerminal = "<" + JOptionPane.showInputDialog(rootPane, "Inserte el no terminal", "Nuevo no terminal", JOptionPane.OK_OPTION) + ">";
        produccionActual.addPart(noTerminal);
        if (produccionActual.isOnRight()) {
            jButtonNuevaProduccion.setEnabled(produccionActual.isCorrect());
        } else {
            produccionActual.addPart(" -> ");
            jButtonNuevoTerminal.setEnabled(true);
        }
        jTextPaneProduccionSeleccionada.setText(produccionActual.toString().substring(produccionActual.toString().indexOf(" ")));

    }//GEN-LAST:event_jButtonNuevoNoTerminalActionPerformed

    private void jListGramaticaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListGramaticaMouseClicked
        String produccion = jListGramatica.getSelectedValue();
        jTextPaneProduccionSeleccionada.setText(produccion.substring(produccion.indexOf(" ")));
        jButtonBorrarUltimo.setEnabled(true);
        jLabelNumeroProduccion.setText("Produccion: " + (jListGramatica.getSelectedIndex() + 1));
        produccionActual = gramatica.getProducciones().get(jListGramatica.getSelectedIndex());
    }//GEN-LAST:event_jListGramaticaMouseClicked

    private void jButtonBorrarUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarUltimoActionPerformed
        editing = true;
        String part = produccionActual.deleteLastPart();
        if (part != null) {
            try {
                modelo.set(produccionActual.getId() - 1, produccionActual.toString());
                jListGramatica.setModel(modelo);

            } catch (Exception e) {
            }
            jTextPaneProduccionSeleccionada.setText(produccionActual.toString().substring(produccionActual.toString().indexOf(" ")));
            if (produccionActual.isSeparator(produccionActual.getPartes().get(produccionActual.getPartes().size() - 1))) {
                jButtonSecuenciaNula.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "La produccion esta vacia", "Error", JOptionPane.ERROR_MESSAGE);
        }
        jButtonNuevaProduccion.setEnabled(produccionActual.isCorrect());
    }//GEN-LAST:event_jButtonBorrarUltimoActionPerformed

    private void jButtonSecuenciaNulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSecuenciaNulaActionPerformed
        produccionActual.addPart("λ");
        jButtonNuevoNoTerminal.setEnabled(false);
        jButtonNuevoTerminal.setEnabled(false);
        jButtonSecuenciaNula.setEnabled(false);
        jButtonNuevaProduccion.setEnabled(produccionActual.isCorrect());
        jButtonBorrarUltimo.setEnabled(true);
        jTextPaneProduccionSeleccionada.setText(produccionActual.toString().substring(produccionActual.toString().indexOf(" ")));
    }//GEN-LAST:event_jButtonSecuenciaNulaActionPerformed

    private void simplificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simplificarActionPerformed
        Set<String> vivos, muertos, alcanzables, inalcanzables;
        vivos = gramatica.getNoTerminalesVivos();
        muertos = gramatica.getNoTerminalesMuertos(vivos);
        alcanzables = gramatica.getNoTerminalesAlcanzables();
        inalcanzables = gramatica.getNoTerminalesInalcanzables(alcanzables);
        if (muertos.isEmpty() && inalcanzables.isEmpty()) {
            gramaticaSimplificada.setText("La gramatica no puede ser simplificada");
        } else {
            Gramatica gSimplificada = gramatica.simplificarGramatica(muertos, inalcanzables);
            gramaticaSimplificada.setText(gSimplificada.toString());
        }
        noTerminalesMuertos.setText(noTerminalesMuertos.getText() + ":  " + muertos.toString());
        noterminalesVivos.setText(noterminalesVivos.getText() + ":  " + vivos.toString());
        noTerminalesAlcanzables.setText(noTerminalesAlcanzables.getText() + ":  " + alcanzables.toString());
        noTerminalesInalcanzables.setText(noTerminalesInalcanzables.getText() + ":  " + inalcanzables.toString());
    }//GEN-LAST:event_simplificarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        JFileChooser fc = new JFileChooser();
        
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void imprimirAF(AF af) {

// Crea modelo de la tabla
        DefaultTableModel modeloAF = new DefaultTableModel();

        List<String> estados = af.getEstados();
        List<String> simbolos = af.getSimbolos();
        List<String> salidas = af.getSalidas();
        String[][] af1 = af.getAf();

        modeloAF.addColumn("  ");
        for (String x : simbolos) {
            modeloAF.addColumn(x);
        }
        modeloAF.addColumn("Salida");

        for (int i = 0; i <= estados.size() - 1; i++) {
            //System.out.print(estados.get(i) + " ");
            Vector fila = new Vector();
            fila.add(estados.get(i));
            for (int j = 0; j <= simbolos.size() - 1; j++) {
                fila.add(af1[i][j]);
            }
            fila.add(salidas.get(i));
            modeloAF.addRow(fila);
        }

        jTable.setModel(modeloAF);

    }

    private void generarAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarAFActionPerformed

        // TODO add your handling code here:
        //Lee gramatica 
        Gramatica gra = new Gramatica();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        try {
            gra.leerGramatica(fc.getSelectedFile().getPath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Crea AF si es regular
        AF af = new AF(gra);
        imprimirAF(af);
        if (af.isDeterministico()) {
            tipoAf.setText("Tipo: Deterministico");
        } else {
            labelTipo.setText("Tipo: No Deterministico");
            jConvertirAF.setVisible(true);
        }

//        if(gra.getIsRegular()){
//        }else{
//        JOptionPane.showMessageDialog(rootPane,"La grámatica ingresada no es regular. \n No puede generarse AF.");
//        }

    }//GEN-LAST:event_generarAFActionPerformed

    private void labelTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTipoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_labelTipoActionPerformed

    private void guardarAfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAfActionPerformed
        // TODO add your handling code here:
        af.guardarAF();
     

    }//GEN-LAST:event_guardarAfActionPerformed

    private void cargarAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarAFActionPerformed
        // TODO add your handling code here:
        //Lee gramatica 
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("af");
        JFileChooser fc = new JFileChooser();
//        fc.setFileFilter(filter);
        fc.showOpenDialog(fc);
        AF af = new AF(fc.getSelectedFile().getPath());
        imprimirAF(af);
        if (af.isDeterministico()) {
            tipoAf.setText("Tipo: Deterministico");
        } else {
            labelTipo.setText("Tipo: No Deterministico");
            jConvertirAF.setVisible(true);
        }


    }//GEN-LAST:event_cargarAFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                



}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        



} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea gramaticaSimplificada;
    private javax.swing.JToggleButton cargarAF;
    private javax.swing.JToggleButton generarAF;
    private javax.swing.JToggleButton guardarAf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBorrarUltimo;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevaGramatica;
    private javax.swing.JButton jButtonNuevaProduccion;
    private javax.swing.JButton jButtonNuevoNoTerminal;
    private javax.swing.JButton jButtonNuevoTerminal;
    private javax.swing.JButton jButtonSecuenciaNula;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JToggleButton jConvertirAF;
    private javax.swing.JLabel jLabelNumeroProduccion;
    private javax.swing.JList<String> jListGramatica;
    private javax.swing.JPanel jPanelAutomataFinito;
    private javax.swing.JPanel jPanelCreacionGramatica;
    private javax.swing.JPanel jPanelReconocimientoHilera;
    private javax.swing.JPanel jPanelSimplificacionGramatica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneGramatica;
    private javax.swing.JTabbedPane jTabbedPaneMain;
    private javax.swing.JTable jTable;
    private javax.swing.JTextPane jTextPaneProduccionSeleccionada;
    private javax.swing.JLabel noTerminalesAlcanzables;
    private javax.swing.JLabel noTerminalesInalcanzables;
    private javax.swing.JLabel noTerminalesMuertos;
    private javax.swing.JLabel noterminalesVivos;
    private javax.swing.JButton simplificar;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton labelTipo;
    private javax.swing.JLabel tipoAf;
    // End of variables declaration//GEN-END:variables
}
