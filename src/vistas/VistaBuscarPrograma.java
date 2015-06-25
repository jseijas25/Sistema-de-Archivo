package vistas;

import controladores.ControladorBuscarPrograma;
import funciones._Con;
import modelos.Programa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaBuscarPrograma extends BaseVista {
    private JButton btnBuscar, btnSalir;
    private JTextField txtNombreProg;

    public VistaBuscarPrograma() {
        super();
        JLabel lblNombreProg;
        JPanel pnlPrincipal, pnlNombreProg, pnlbotones;
        ControladorBuscarPrograma controlador;
        controlador = new ControladorBuscarPrograma(this);
        Icon icobuscar, icosalir;
        icobuscar         = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/buscar.png");
        icosalir         = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/salir.png");

        txtNombreProg = new JTextField(20);
        lblNombreProg = new JLabel("Programa:");

        btnBuscar = new JButton("Buscar", icobuscar);
        btnSalir = new JButton("Salir", icosalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlNombreProg =new JPanel(new FlowLayout());
        pnlNombreProg.setBorder(new EmptyBorder(10,10,10,10));
        pnlNombreProg.add(lblNombreProg);
        pnlNombreProg.add(txtNombreProg);

        pnlbotones =new JPanel(new FlowLayout());
        pnlbotones.setBorder(new EmptyBorder(10,10,10,10));
        pnlbotones.add(btnBuscar);
        pnlbotones.add(btnSalir);

        pnlPrincipal.add(pnlNombreProg, BorderLayout.CENTER);
        pnlPrincipal.add(pnlbotones, BorderLayout.SOUTH);

        btnSalir.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);

        this.add(pnlPrincipal);
        this.setResizable(false);
        this.setTitle("Buscar Programa");
        this.setVisible(true);
        this.setSize(280,200);
        this.setLocationRelativeTo(null);
    }

    public boolean getData(Programa programa){
        System.out.println(txtNombreProg.getText().length());
        if (txtNombreProg.getText().isEmpty()){
            setError("El campo Programa no puede estar vacio");
            return false;
        }
        programa.setName(txtNombreProg.getText());

        return true;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
