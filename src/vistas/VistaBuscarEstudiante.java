package vistas;

import controladores.ControladorBuscarEstudiante;
import funciones._Con;
import modelos.Estudiante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaBuscarEstudiante extends BaseVista {
    private JButton btnBuscar, btnSalir;
    private JTextField txtCedula;

    public VistaBuscarEstudiante() {
        super();
        JLabel lblCedula;
        JPanel pnlPrincipal, pnlcedula, pnlbotones;
        ControladorBuscarEstudiante controlador;
        controlador = new ControladorBuscarEstudiante(this);
        Icon icobuscar, icosalir;
        icobuscar        = new ImageIcon(_Con.RUTA + "32x32/buscar.png");
        icosalir         = new ImageIcon(_Con.RUTA + "32x32/salir.png");

        txtCedula = new JTextField(10);
        lblCedula = new JLabel("CI:");

        btnBuscar = new JButton("Buscar", icobuscar);
        btnSalir = new JButton("Salir", icosalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlcedula =new JPanel(new FlowLayout());
        pnlcedula.setBorder(new EmptyBorder(10,10,10,10));
        pnlcedula.add(lblCedula);
        pnlcedula.add(txtCedula);

        pnlbotones =new JPanel(new FlowLayout());
        pnlbotones.setBorder(new EmptyBorder(10,10,10,10));
        pnlbotones.add(btnBuscar);
        pnlbotones.add(btnSalir);

        pnlPrincipal.add(pnlcedula, BorderLayout.CENTER);
        pnlPrincipal.add(pnlbotones, BorderLayout.SOUTH);

        btnSalir.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);

        this.add(pnlPrincipal);
        this.setResizable(false);
        this.setTitle("Buscar estudiante");
        this.setVisible(true);
        this.setSize(280,150);
        this.setLocationRelativeTo(null);
    }

    public boolean getData(Estudiante estudiante) {
        if (txtCedula.getText().isEmpty()) {
            setError("El campo cedula no puede estar vacio");
            return false;
        }
        try {
            estudiante.setCedula(Integer.parseInt(txtCedula.getText()));
        } catch (NumberFormatException e) {
            setError("La cedula debe ser numerica");
            return false;
        }
        if (estudiante.getCedula() <= 0) {
            setError("La cedula debe ser un número mayor que 0");
            return false;
        }
        return true;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
