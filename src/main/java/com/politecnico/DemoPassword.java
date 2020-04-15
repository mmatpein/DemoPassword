package com.politecnico;

import com.politecnico.control.ControlCredenciales;
import com.politecnico.ui.UIPrincipal;
import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;

public class DemoPassword
{
    public static void main( String[] args ) throws NoSuchAlgorithmException {
        System.out.println( "Hello World!" );
        System.out.println("GETRESOURCE:" + DemoPassword.class.getResource("/ok.png").getPath());
        ControlCredenciales control = new ControlCredenciales();
        JFrame frame = new JFrame("Alturas");
        frame.setContentPane(new UIPrincipal(control).getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height - 200;
        int width = toolkit.getScreenSize().width - 400;
        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
