package com.politecnico.ui;

import com.politecnico.modelo.Credencial;
import com.politecnico.modelo.CredencialExtendida;

import javax.swing.*;
import java.awt.*;

public class CredencialCustomRenderer implements ListCellRenderer<DataTransfer>{
    private JPanel pnlMain;
    private JLabel lblNombre;
    private JLabel lblPassword;
    private JLabel lblIconoValidacion;
    private JLabel lblHash;


    @Override
        public Component getListCellRendererComponent(JList<? extends DataTransfer> jList, DataTransfer dataTransfer, int i, boolean b, boolean b1) {
        lblNombre.setText(dataTransfer.nombre);
        lblPassword.setText(dataTransfer.password);
        lblHash.setText(dataTransfer.hash);
        ImageIcon iconoValidacion = new ImageIcon(getClass().getResource("/ok.png"));
        if (dataTransfer.validado)
            lblIconoValidacion.setIcon(new ImageIcon(getClass().getResource("/ok.png")));
        else
            lblIconoValidacion.setIcon(new ImageIcon(getClass().getResource("/error.png")));
        return pnlMain;
    }
}
