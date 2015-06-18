package vistas;

import javax.swing.*;

/**
 * Created by Michael on 16/06/2015.
 */
public class VistaPrimera extends BaseVista {
    public VistaPrimera() {
        super();
        JLabel lblComprobando;
        lblComprobando = new JLabel("Comprobando Base de Datos");
        lblComprobando.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblComprobando);
        this.setVisible(true);
        this.setSize(200,100);
        this.setLocationRelativeTo(null);
        this.setTitle("Espere");
    }
}
