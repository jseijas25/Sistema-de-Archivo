package vistas;

import funciones.OperationType;
import funciones._Con;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaEstudiante extends BaseVista {
    private JButton btnCrear, btnEditar, btnBorrar, btnSolvencia, btnSalir;

    private JTextField txtCedula, txtNombre, txtApellido;
    private JRadioButton rdbHombre, rdbMujer;
    private JCheckBox chkSolvente;
    private JTabbedPane tbbExpediente;
    private ArrayList<JPanel> panelsExpedientes;

    public VistaEstudiante(){
        ButtonGroup grpGenero;
        JLabel lblCedula, lblNombre, lblApellido, lblGenero, lblSolvente;
        JPanel pnlDatosEstudiante, pnlLateral, pnlPrincipal, pnlAux;

        lblCedula = new JLabel("Cedula: ");
        txtCedula = new JTextField();

        lblNombre = new JLabel("Nombre: ");
        txtNombre = new JTextField();

        lblApellido = new JLabel("Apellido: ");
        txtApellido = new JTextField();

        lblGenero = new JLabel("Genero: ");
        rdbHombre = new JRadioButton("Hombre");
        rdbMujer = new JRadioButton("Mujer");
        grpGenero = new ButtonGroup();
        grpGenero.add(rdbHombre);
        grpGenero.add(rdbMujer);

        lblSolvente = new JLabel("Solvente?");
        chkSolvente = new JCheckBox();

        tbbExpediente = new JTabbedPane();
        tbbExpediente.setSize(200,150);
        panelsExpedientes = new ArrayList<>();

        pnlDatosEstudiante = new JPanel(new GridLayout(5,2));
        pnlDatosEstudiante.add(lblCedula);      pnlDatosEstudiante.add(txtCedula);
        pnlDatosEstudiante.add(lblNombre);      pnlDatosEstudiante.add(txtNombre);
        pnlDatosEstudiante.add(lblApellido);    pnlDatosEstudiante.add(txtApellido);
        pnlAux = new JPanel(new GridLayout(1,2,2,2));
            pnlAux.add(rdbHombre);      pnlAux.add(rdbMujer);
        pnlDatosEstudiante.add(lblGenero);      pnlDatosEstudiante.add(pnlAux);
        pnlDatosEstudiante.add(lblSolvente);    pnlDatosEstudiante.add(chkSolvente);

        pnlLateral = new JPanel();
        switch (_Con.getInstance().getOperation()){
            case READ:
                pnlLateral.setLayout(new GridLayout(1,4,2,2));
                btnEditar = new JButton("Editar");
                btnBorrar = new JButton("Borrar");
                btnSolvencia = new JButton("Solvencia");
                pnlLateral.add(btnEditar);
                pnlLateral.add(btnBorrar);
                pnlLateral.add(btnSolvencia);
                break;
            case CREATE:
                pnlLateral.setLayout(new GridLayout(1,2,2,2));
                btnCrear = new JButton("Crear");
                pnlLateral.add(btnCrear);
                break;
        }

        btnSalir = new JButton("Salir");
        pnlLateral.add(btnSalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.add(pnlDatosEstudiante, BorderLayout.NORTH);
        pnlPrincipal.add(pnlLateral, BorderLayout.SOUTH);
        pnlPrincipal.add(tbbExpediente, BorderLayout.CENTER);

        this.add(pnlPrincipal);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public void setBtnBorrar(JButton btnBorrar) {
        this.btnBorrar = btnBorrar;
    }

    public JButton getBtnSolvencia() {
        return btnSolvencia;
    }

    public void setBtnSolvencia(JButton btnSolvencia) {
        this.btnSolvencia = btnSolvencia;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JRadioButton getRdbHombre() {
        return rdbHombre;
    }

    public void setRdbHombre(JRadioButton rdbHombre) {
        this.rdbHombre = rdbHombre;
    }

    public JRadioButton getRdbMujer() {
        return rdbMujer;
    }

    public void setRdbMujer(JRadioButton rdbMujer) {
        this.rdbMujer = rdbMujer;
    }

    public JCheckBox getChkSolvente() {
        return chkSolvente;
    }

    public void setChkSolvente(JCheckBox chkSolvente) {
        this.chkSolvente = chkSolvente;
    }

    public JTabbedPane getTbbExpediente() {
        return tbbExpediente;
    }

    public void setTbbExpediente(JTabbedPane tbbExpediente) {
        this.tbbExpediente = tbbExpediente;
    }

    public ArrayList<JPanel> getPanelsExpedientes() {
        return panelsExpedientes;
    }

    public void setPanelsExpedientes(ArrayList<JPanel> panelsExpedientes) {
        this.panelsExpedientes = panelsExpedientes;
    }
}
