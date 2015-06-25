package vistas;

import controladores.BaseControlador;
import dataBase.ExpedienteDAO;
import dataBase.ProgramaDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Expediente;
import modelos.Programa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PestanaExpediente extends JPanel {
    private JTextField txtNumero;
    private int id_expediente;
    private JComboBox<String> boxPrograma;
    private JCheckBox chkFotos, chkCV, chkNegativo, chkNotas, chkPartida, chkCedula, chkAuto;
    private boolean editable;
    private JButton btnCrear, btnEditar, btnBorrar, btnSolvencia;

    public PestanaExpediente() {
        JLabel lblNumero, lblPrograma, lblFotos, lblCV, lblNegativo, lblNotas, lblPartida, lblCedula,lblAuto;
        JPanel pnlUno, pnlTres;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar, icoSolvencia;
        editable = true;

        id_expediente = -10000;

        icoCrear         = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/crear.png");
        icoBorrar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/borrar.png");
        icoSalir=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/salir.png");
        icoEditar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/editar.png");
        icoSolvencia = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/solvencia.png");

        lblAuto = new JLabel("Auto");
        chkAuto = new JCheckBox();

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

        lblFotos = new JLabel("2 Fotos ");
        chkFotos = new JCheckBox();

        lblNotas = new JLabel("Fotocopia de la Notas Cert. ");
        chkNotas = new JCheckBox();

        lblCV = new JLabel("Sintesis Curricular: ");
        chkCV = new JCheckBox();

        lblNegativo = new JLabel("Fondo Negro Autenticado: ");
        chkNegativo = new JCheckBox();

        lblPartida = new JLabel("Fotocopia de la P.N");
        chkPartida = new JCheckBox();

        lblCedula = new JLabel("Fotocopia C.I: ");
        chkCedula = new JCheckBox();


        pnlUno = new JPanel(new GridLayout(8,2));
        pnlTres = new JPanel();
        switch (_Con.getInstance().getOperation()){
            case READ:
                pnlUno.add(lblNumero);
                pnlUno.add(txtNumero);
                pnlTres.setLayout(new GridLayout(3,1));
                btnEditar = new JButton("Editar", icoEditar);
                btnBorrar = new JButton("Borrar", icoBorrar);
                btnSolvencia = new JButton("Solvencia", icoSolvencia);
                pnlTres.add(btnEditar);
                pnlTres.add(btnBorrar);
                pnlTres.add(btnSolvencia);
                boxPrograma.setEnabled(false);
                setEditable();
                break;
            case CREATE:
                JPanel panel;
                panel = new JPanel(new GridLayout(1,2));
                panel.add(lblAuto);
                panel.add(chkAuto);
                pnlUno.add(panel);
                panel = new JPanel(new GridLayout(1,2));
                panel.add(lblNumero);
                panel.add(txtNumero);
                pnlUno.add(panel);
                chkAuto.setSelected(true);
                setAuto();
                pnlTres.setLayout(new GridLayout(2, 1));
                btnCrear = new JButton("Crear", icoCrear);
                btnBorrar = new JButton("Cancelar", icoSalir);
                pnlTres.add(btnCrear);
                pnlTres.add(btnBorrar);
                break;
        }
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

        this.setLayout(new BorderLayout());
        this.add(pnlUno, BorderLayout.CENTER);
        this.add(pnlTres, BorderLayout.EAST);

    }

    public boolean getData(Expediente expediente, BaseVista vista) {
        // Si el ID es mayor que 0, previamente se hizo un setData()
        if(id_expediente >=0){
            expediente.setId(id_expediente);
        }
        Programa programa = new Programa();
        programa.setName(String.valueOf(boxPrograma.getSelectedItem()));
        ProgramaDAO.read(programa);
        expediente.setId_Programa(programa.getId());
        if(_Con.getInstance().getOperation() == OperationType.CREATE){
            ArrayList<Expediente> expedientes = new ArrayList<>();
            ExpedienteDAO.findAllByProgram(expedientes, programa.getName());
            if(chkAuto.isSelected()){
                int number = 1;
                for(Expediente e : expedientes){
                    if (e.getNumber() != number) {
                        break;
                    } else {
                        number++;
                    }
                }
                expediente.setNumber(number);
            }else{
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
                    vista.setError("El campo # debe  ser un número mayor que 0");
                    return false;
                }
                for(Expediente e : expedientes){
                    if (e.getNumber() == expediente.getNumber()) {
                        vista.setError("El número ingresado ya se encuentra en uso\npara un expediente de ese programa");
                        return false;
                    }
                }
            }
        }else{
            try {
                expediente.setNumber(Integer.parseInt(txtNumero.getText()));
            } catch (NumberFormatException e) {
                vista.setError("El campo # debe ser numerico");
                return false;
            }
        }
        expediente.setPicturesChecked(chkFotos.isSelected());
        expediente.setCvChecked(chkCV.isSelected());
        expediente.setNegativePhotocopyChecked(chkNegativo.isSelected());
        expediente.setGradesChecked(chkNotas.isSelected());
        expediente.setBirthCertificateChecked(chkPartida.isSelected());
        expediente.setIdPhotocopyChecked(chkCedula.isSelected());
        return true;
    }

    public void setData(Expediente expediente){
        id_expediente = expediente.getId();
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
        chkCedula.setEnabled(editable);
        chkNotas.setEnabled(editable);
        chkPartida.setEnabled(editable);
        chkNegativo.setEnabled(editable);
        chkCV.setEnabled(editable);
        chkFotos.setEnabled(editable);
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

    public JCheckBox getChkAuto() {
        return chkAuto;
    }

    public void setControlador(BaseControlador controlador){
        if(btnEditar!=null){
            btnEditar.removeActionListener(controlador);
            btnEditar.addActionListener(controlador);
        }
        if(btnBorrar!=null){
            btnBorrar.removeActionListener(controlador);
            btnBorrar.addActionListener(controlador);
        }
        if(btnSolvencia!=null){
            btnSolvencia.removeActionListener(controlador);
            btnSolvencia.addActionListener(controlador);
        }
        if(btnCrear!=null){
            btnCrear.removeActionListener(controlador);
            btnCrear.addActionListener(controlador);
        }
        if(chkAuto!=null){
            chkAuto.removeActionListener(controlador);
            chkAuto.addActionListener(controlador);
        }
    }

    public void setAuto(){
        txtNumero.setEnabled(!chkAuto.isSelected());
    }
}
