package vistas;

import controladores.ControladorExpediente;
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


    public PestanaExpediente() {
        JLabel lblNumero, lblPrograma, lblFotos, lblCV, lblNegativo, lblNotas, lblPartida, lblCedula;
        JPanel pnlUno, pnlTres;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar, icoSolvencia;
        ControladorExpediente controlador;
        controlador = new ControladorExpediente(this);
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
                btnEditar.addActionListener(controlador);
                btnBorrar = new JButton("Borrar", icoBorrar);
                btnBorrar.addActionListener(controlador);
                btnSolvencia = new JButton("Solvencia", icoSolvencia);
                btnSolvencia.addActionListener(controlador);
                pnlTres.add(btnEditar);
                pnlTres.add(btnBorrar);
                pnlTres.add(btnSolvencia);
                setEditable();
                break;
            case CREATE:
                pnlTres.setLayout(new GridLayout(2,1));
                btnCrear = new JButton("Crear", icoCrear);
                btnCrear.addActionListener(controlador);
                btnBorrar = new JButton("Cancelar", icoSalir);
                pnlTres.add(btnCrear);
                break;
        }

        this.setLayout(new BorderLayout());
        this.add(pnlUno, BorderLayout.CENTER);
        this.add(pnlTres, BorderLayout.EAST);

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
}
