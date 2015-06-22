package vistas;

import controladores.BaseControlador;
import dataBase.ProgramaDAO;
import funciones._Con;
import modelos.Expediente;
import modelos.Programa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PestanaExpediente extends JPanel {
    private JTextField txtNumero;
    private JComboBox<String> boxPrograma;
    private JCheckBox chkFotos, chkCV, chkNegativo, chkNotas, chkPartida, chkCedula;
    private boolean editable;
    private JButton btnCrear, btnEditar, btnBorrar, btnSolvencia;

    public boolean getData(Expediente expediente, BaseVista vista) {
        if (txtNumero.getText().isEmpty()) {
            vista.setError("El campo # no puede estar vacio");
            return false;
        }
        try {
            expediente.setNumber(Integer.parseInt(txtNumero.getText()));
        } catch (NumberFormatException e) {
            vista.setError("El campo # debe ser numerico");
            return false;
        }
        if (expediente.getNumber() <= 0) {
            vista.setError("El campo # debe ser un número mayor que 0");
            return false;
        }
        Programa programa = new Programa();
        programa.setName(String.valueOf(boxPrograma.getSelectedItem()));
        ProgramaDAO.read(programa);
        expediente.setId_Programa(programa.getId());
        expediente.setPicturesChecked(chkFotos.isSelected());
        expediente.setCvChecked(chkCV.isSelected());
        expediente.setNegativePhotocopyChecked(chkNegativo.isSelected());
        expediente.setGradesChecked(chkNotas.isSelected());
        expediente.setBirthCertificateChecked(chkPartida.isSelected());
        expediente.setIdPhotocopyChecked(chkCedula.isSelected());
        return true;
    }

    public PestanaExpediente() {
        JLabel lblNumero, lblPrograma, lblFotos, lblCV, lblNegativo, lblNotas, lblPartida, lblCedula;
        JPanel pnlUno, pnlTres;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar, icoSolvencia;
        editable = true;

        icoCrear         = new ImageIcon(_Con.RUTA + "32x32/crear.png");
        icoBorrar=new ImageIcon(_Con.RUTA + "32x32/borrar.png");
        icoSalir=new ImageIcon(_Con.RUTA + "32x32/salir.png");
        icoEditar=new ImageIcon(_Con.RUTA + "32x32/editar.png");
        icoSolvencia = new ImageIcon(_Con.RUTA + "32x32/solvencia.png");

        lblNumero = new JLabel("#");
        txtNumero = new JTextField();

        ArrayList<Programa> programas = new ArrayList<>();
        ProgramaDAO.findAll(programas);
        String [] nombres_programas = new String[programas.size()];

        for (int i = 0; i < programas.size(); i++) {
            nombres_programas[i] = programas.get(i).getName();
        }

        lblPrograma = new JLabel("Programa: ");
        boxPrograma = new JComboBox<>(nombres_programas);

        lblFotos = new JLabel("Fotos: ");
        chkFotos = new JCheckBox();

        lblNotas = new JLabel("Notas: ");
        chkNotas = new JCheckBox();

        lblCV = new JLabel("CV: ");
        chkCV = new JCheckBox();

        lblNegativo = new JLabel("Negativo: ");
        chkNegativo = new JCheckBox();

        lblPartida = new JLabel("Partida");
        chkPartida = new JCheckBox();

        lblCedula = new JLabel("Copia de cedula: ");
        chkCedula = new JCheckBox();

        pnlUno = new JPanel(new GridLayout(4,4,2,2));
        pnlUno.add(lblNumero);
        pnlUno.add(txtNumero);
        pnlUno.add(lblPrograma);
        pnlUno.add(boxPrograma);
        pnlUno.add(lblFotos);
        pnlUno.add(chkFotos);
        pnlUno.add(lblNotas);
        pnlUno.add(chkNotas);
        pnlUno.add(lblCV);
        pnlUno.add(chkCV);
        pnlUno.add(lblNegativo);
        pnlUno.add(chkNegativo);
        pnlUno.add(lblPartida);
        pnlUno.add(chkPartida);
        pnlUno.add(lblCedula);
        pnlUno.add(chkCedula);

        btnCrear = new JButton("Crear");
        btnBorrar = new JButton("Borrar");
        btnEditar = new JButton("Editar");
        pnlTres = new JPanel();
        switch (_Con.getInstance().getOperation()){
            case READ:
                pnlTres.setLayout(new GridLayout(3,1));
                btnEditar = new JButton("Editar", icoEditar);
                btnBorrar = new JButton("Borrar", icoBorrar);
                btnSolvencia = new JButton("Solvencia", icoSolvencia);
                pnlTres.add(btnEditar);
                pnlTres.add(btnBorrar);
                pnlTres.add(btnSolvencia);
                setEditable();
                break;
            case CREATE:
                pnlTres.setLayout(new GridLayout(2, 1));
                btnCrear = new JButton("Crear", icoCrear);
                btnBorrar = new JButton("Cancelar", icoSalir);
                pnlTres.add(btnCrear);
                pnlTres.add(btnBorrar);
                break;
        }

        this.setLayout(new BorderLayout());
        this.add(pnlUno, BorderLayout.CENTER);
        this.add(pnlTres, BorderLayout.EAST);

    }

    public void setData(Expediente expediente){
        txtNumero.setText(String.valueOf(expediente.getNumber()));
        Programa programa = new Programa();
        programa.setId(expediente.getId_Programa());
        ProgramaDAO.readByID(programa);
        boxPrograma.setSelectedItem(programa.getName());
        chkFotos.setSelected(expediente.isPicturesChecked());
        chkCV.setSelected(expediente.isCvChecked());
        chkNegativo.setSelected(expediente.isNegativePhotocopyChecked());
        chkNotas.setSelected(expediente.isGradesChecked());
        chkPartida.setSelected(expediente.isBirthCertificateChecked());
        chkCedula.setSelected(expediente.isIdPhotocopyChecked());
    }

    public boolean isEditable() {
        return editable;
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
        txtNumero.setEnabled(editable);
        boxPrograma.setEnabled(editable);
        btnBorrar.setEnabled(!editable);
        btnSolvencia.setEnabled(!editable);
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

    public JButton getBtnSolvencia() {
        return btnSolvencia;
    }

    public void setControlador(BaseControlador controlador){
        if(btnEditar!=null)     btnEditar.addActionListener(controlador);
        if(btnBorrar!=null)     btnBorrar.addActionListener(controlador);
        if(btnSolvencia!=null)  btnSolvencia.addActionListener(controlador);
        if(btnCrear!=null)      btnCrear.addActionListener(controlador);
    }

    public void reload(){

    }
}
