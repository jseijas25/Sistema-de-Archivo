package vistas;

import controladores.ControladorBuscarEstudiante;
import modelos.Estudiante;

import javax.swing.*;
import java.awt.*;

public class VistaBuscarEstudiante extends BaseVista {
    private JButton btnBuscar, btnSalir;
    private JTextField txtCedula;

    public VistaBuscarEstudiante() {
        super();
        JLabel lblCedula;
        JPanel pnlPrincipal;
        ControladorBuscarEstudiante controlador;
        controlador = new ControladorBuscarEstudiante(this);

        pnlPrincipal = new JPanel(new GridLayout(2,2));

        txtCedula = new JTextField();
        lblCedula = new JLabel("CI:");

        btnBuscar = new JButton("Buscar");
        btnSalir = new JButton("Salir");

        lblCedula.setHorizontalAlignment(SwingConstants.CENTER);

        pnlPrincipal.add(lblCedula);
        pnlPrincipal.add(txtCedula);
        pnlPrincipal.add(btnBuscar);
        pnlPrincipal.add(btnSalir);

        btnSalir.addActionListener(controlador);
        btnBuscar.addActionListener(controlador);

        this.add(pnlPrincipal);
        this.setSize(200,85);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Buscar estudiante");
        this.setVisible(true);
    }

    public boolean getData(Estudiante estudiante){
        if (txtCedula.getText().isEmpty()){
            setError("El campo cedula no puede estar vacio");
        }else {
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
