package com.politecnico.ui;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.*;

public class DialogoRegistrar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNombre;
    private JTextField txtPassword;
    private JComboBox cmbAlgoritmo;
    private DataTransfer datos;

    public DialogoRegistrar(DataTransfer datos) {
        this.datos = datos;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        datos.nombre = txtNombre.getText();
        datos.password = txtPassword.getText();
        datos.algoritmo = cmbAlgoritmo.getSelectedItem().toString();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        cmbAlgoritmo = new JComboBox();
        cmbAlgoritmo.addItem("SHA3-512");
        cmbAlgoritmo.addItem("Bcrypt");
    }
}
