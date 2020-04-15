package com.politecnico.ui;

import com.politecnico.algoritmos.Algoritmos;
import com.politecnico.control.ControlCredenciales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class UIPrincipal {
    private JList lstValidaciones;
    private JButton bntRegistrar;
    private JButton btnComprobar;
    private JPanel pnlMain;
    private JList lstNombres;
    ControlCredenciales control;
    DefaultListModel<DataTransfer> lstValidacionesModel;
    DefaultListModel<String> lstNombresModel;


    public UIPrincipal(ControlCredenciales control){
        this.control = control;
        try {
            control.cargarCredencialesDesdeBD();
            lstNombresModel.addAll(control.getNombresUsuario());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(pnlMain,"No se pudo conectar la base de datos","Error",JOptionPane.ERROR_MESSAGE);
        }

        bntRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DataTransfer datos = new DataTransfer();
                DialogoRegistrar dialogo = new DialogoRegistrar(datos);
                dialogo.setLocationRelativeTo(SwingUtilities.getRoot((Component) actionEvent.getSource()));
                dialogo.pack();
                dialogo.setVisible(true);
                try {
                    control.addCredencial(datos.nombre,datos.password, Algoritmos.getAlgoritmoByNombre(datos.algoritmo));
                    JOptionPane.showMessageDialog(SwingUtilities.getRoot((Component) actionEvent.getSource()),"La inserción se hizo correctamente","Inserción",JOptionPane.INFORMATION_MESSAGE);
                    lstNombresModel.addElement(datos.nombre);
                } catch (NoSuchAlgorithmException e) {
                    JOptionPane.showMessageDialog(SwingUtilities.getRoot((Component) actionEvent.getSource()),"No se pudo insertar el usuario","Problema al insertar", JOptionPane.ERROR);
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DataTransfer datos = new DataTransfer();
                DialogoComprobar dialogo = new DialogoComprobar(datos);
                dialogo.setLocationRelativeTo(SwingUtilities.getRoot((Component) actionEvent.getSource()));
                dialogo.pack();
                dialogo.setVisible(true);
                datos.hash = control.getHashByNombre(datos.nombre);
                try {
                    datos.validado = control.comprobarCredencial(datos.nombre,datos.password);
                    lstValidacionesModel.addElement(datos);
                } catch (NoSuchElementException e){
                    JOptionPane.showMessageDialog(SwingUtilities.getRoot((Component) actionEvent.getSource()),"No existe el usuario "+ datos.nombre,"Usuario inexistente",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Container getPanel() {
        return pnlMain;
    }

    private void createUIComponents() {
        lstValidaciones = new JList();
        lstValidacionesModel = new DefaultListModel<>();
        lstValidaciones.setModel(lstValidacionesModel);
        lstValidaciones.setCellRenderer(new CredencialCustomRenderer());

        System.out.println("Creando lstNombresModel");
        lstNombres = new JList();
        lstNombresModel = new DefaultListModel<String>();
        lstNombres.setModel(lstNombresModel);
    }
}
