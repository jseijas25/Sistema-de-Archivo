package vistas;

import controladores.ControladorEstudiante;
import dataBase.ExpedienteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaEstudiante extends BaseVista {
    private JButton btnCrear, btnEditar, btnBorrar, btnSalir, btnCrearExpediente;

    private JTextField txtCedula, txtNombre, txtApellido;
    private JRadioButton rdbHombre, rdbMujer;
    private JTabbedPane tbbExpediente;
    private JPanel pnlNuevoExpediente;
    private ArrayList<PestanaExpediente> pestanaExpedientes;
    private boolean editable;

    public VistaEstudiante(){
        ButtonGroup grpGenero;
        JLabel lblCedula, lblNombre, lblApellido, lblGenero;
        JPanel pnlDatosEstudiante, pnlLateral, pnlPrincipal, pnlAux;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar;

        icoCrear         = new ImageIcon(_Con.RUTA + "32x32/crear.png");
        icoBorrar=new ImageIcon(_Con.RUTA + "32x32/borrar.png");
        icoSalir=new ImageIcon(_Con.RUTA + "32x32/salir.png");
        icoEditar=new ImageIcon(_Con.RUTA + "32x32/editar.png");

        ControladorEstudiante controlador;
        controlador = new ControladorEstudiante(this);
        editable = true;

        lblCedula = new JLabel("Cedula: ");
        txtCedula = new JTextField();
        txtCedula.setEnabled(false);

        lblNombre = new JLabel("Nombre: ");
        txtNombre = new JTextField();
        txtNombre.addActionListener(controlador);

        lblApellido = new JLabel("Apellido: ");
        txtApellido = new JTextField();
        txtApellido.addActionListener(controlador);

        lblGenero = new JLabel("Genero: ");
        rdbHombre = new JRadioButton("Hombre");
        rdbMujer = new JRadioButton("Mujer");
        grpGenero = new ButtonGroup();
        grpGenero.add(rdbHombre);
        grpGenero.add(rdbMujer);

        tbbExpediente = new JTabbedPane();
        pestanaExpedientes = new ArrayList<>();
        pnlNuevoExpediente = new JPanel();
        btnCrearExpediente = new JButton("Crear Expediente");
        btnCrearExpediente.addActionListener(controlador);
        pnlNuevoExpediente.add(btnCrearExpediente);

        pnlAux = new JPanel();
        pnlDatosEstudiante = new JPanel(new GridLayout(4,2));
        pnlDatosEstudiante.add(lblCedula);      pnlDatosEstudiante.add(txtCedula);
        pnlDatosEstudiante.add(lblNombre);      pnlDatosEstudiante.add(txtNombre);
        pnlDatosEstudiante.add(lblApellido);    pnlDatosEstudiante.add(txtApellido);
        pnlAux.setLayout(new GridLayout(1,2,2,2));
        pnlAux.add(rdbHombre);      pnlAux.add(rdbMujer);
        pnlDatosEstudiante.add(lblGenero);      pnlDatosEstudiante.add(pnlAux);

        pnlLateral = new JPanel();
        switch (_Con.getInstance().getOperation()){
            case READ:
                pnlLateral.setLayout(new GridLayout(3,1));
                btnEditar = new JButton("Editar", icoEditar);
                btnEditar.addActionListener(controlador);
                btnBorrar = new JButton("Borrar", icoBorrar);
                btnBorrar.addActionListener(controlador);
                pnlLateral.add(btnEditar);
                pnlLateral.add(btnBorrar);
                setEditable();
                break;
            case CREATE:
                pnlLateral.setLayout(new GridLayout(2,1));
                btnCrear = new JButton("Crear", icoCrear);
                btnCrear.addActionListener(controlador);
                pnlLateral.add(btnCrear);
                break;
        }

        btnSalir = new JButton("Salir", icoSalir);
        btnSalir.addActionListener(controlador);
        pnlLateral.add(btnSalir);

        pnlPrincipal = new JPanel(new BorderLayout());
        pnlPrincipal.add(pnlDatosEstudiante, BorderLayout.CENTER);
        pnlPrincipal.add(pnlLateral, BorderLayout.EAST);
        pnlPrincipal.add(tbbExpediente, BorderLayout.SOUTH);

        this.addWindowListener(controlador);
        this.add(pnlPrincipal);
        this.setVisible(true);
        this.pack();
        this.setTitle("Estudiante");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public boolean getData(Estudiante estudiante){
        if(txtNombre.getText().isEmpty()){
            setError("El campo \"Nombre\" no puede estar vacio");
            return false;
        }
        estudiante.setNombre(txtNombre.getText());
        if(txtApellido.getText().isEmpty()){
            setError("El campo \"Apellido\" no puede estar vacio");
            return false;
        }
        estudiante.setApellido(txtApellido.getText());
        if(rdbHombre.isSelected()){
            estudiante.setGenero(0);
        }else if(rdbMujer.isSelected()){
            estudiante.setGenero(1);
        }else{
            setError("Debe seleccionar el genero");
            return false;
        }
        return true;
    }

    public void setData(Estudiante estudiante){
        txtCedula.setText(String.valueOf(estudiante.getCedula()));
        txtNombre.setText(estudiante.getNombre());
        txtApellido.setText(estudiante.getApellido());
        if(estudiante.getGenero() == 0){
            rdbHombre.setSelected(true);
        }else {
            rdbMujer.setSelected(true);
        }
        recargarExpedientes();
    }

    public void recargarExpedientes() {
        tbbExpediente.removeAll();
        for (int i = 0; i < pestanaExpedientes.size(); i++) {
            tbbExpediente.addTab("Expediente: "+i, pestanaExpedientes.get(i));
        }
        tbbExpediente.addTab("+", pnlNuevoExpediente);
        this.pack();
    }

    public void setEditable() {
        if(editable){
            editable = false;
            btnEditar.setText("Editar");
        }else{
            int op = JOptionPane.showConfirmDialog(this, "¿Está seguro de habilitar edición?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                editable = true;
                btnEditar.setText("Guardar");
            }
        }
        txtNombre.setEnabled(editable);
        txtApellido.setEnabled(editable);
        rdbHombre.setEnabled(editable);
        rdbMujer.setEnabled(editable);
        btnBorrar.setEnabled(!editable);
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnCrearExpediente() {
        return btnCrearExpediente;
    }

    public JTabbedPane getTbbExpediente() {
        return tbbExpediente;
    }

    public JPanel getPnlNuevoExpediente() {
        return pnlNuevoExpediente;
    }

    public ArrayList<PestanaExpediente> getPestanaExpedientes() {
        return pestanaExpedientes;
    }

    public boolean isEditable() {
        return editable;
    }
}
