package vistas;

import controladores.ControladorPrograma;
import funciones._Con;
import modelos.Programa;

import javax.swing.*;
import java.awt.*;

public class VistaPrograma extends BaseVista {
    private JButton btnCrear, btnEditar, btnBorrar, btnSalir;
    private JTextField txtNombreProg, txtRecordCount;
    private JCheckBox chkActive;
    private boolean editable;

    public VistaPrograma(){
        JLabel lblNombreProg, lblRecordCount;
        JPanel pnlDatosProg, pnlLateral, pnlPrincipal;
        Icon icoCrear,icoBorrar,icoSalir,icoEditar;

        icoCrear         = new ImageIcon(_Con.RUTA_IMAGENES + "32x32/crear.png");
        icoBorrar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/borrar.png");
        icoSalir=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/salir.png");
        icoEditar=new ImageIcon(_Con.RUTA_IMAGENES + "32x32/editar.png");

        ControladorPrograma controlador;
        controlador = new ControladorPrograma(this);
        editable = true;

        lblNombreProg = new JLabel("Nombre de Programa: ");
        txtNombreProg = new JTextField(25);
        txtNombreProg.setEnabled(false);

        lblRecordCount = new JLabel("Nº de Expedientes: ");
        txtRecordCount = new JTextField();
        txtRecordCount.setEnabled(false);
        txtRecordCount.addActionListener(controlador);


        chkActive =new JCheckBox("Programa Activo");

        pnlDatosProg = new JPanel(new GridLayout(3,1));
        pnlDatosProg.add(lblNombreProg);
        pnlDatosProg.add(txtNombreProg);
        //pnlDatosProg.add(lblRecordCount);
       // pnlDatosProg.add(txtRecordCount);
        pnlDatosProg.add(chkActive);

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
        pnlPrincipal.add(pnlDatosProg, BorderLayout.CENTER);
        pnlPrincipal.add(pnlLateral, BorderLayout.EAST);

        this.addWindowListener(controlador);
        this.add(pnlPrincipal);
        this.setVisible(true);
        this.pack();
        this.setTitle("Programas");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public boolean getData(Programa programa){
        programa.setActive(chkActive.isSelected());
        return true;
    }

    public void setData(Programa programa){
        txtNombreProg.setText(String.valueOf(programa.getName().toUpperCase()));
      //  txtRecordCount.setText(String.valueOf(programa.getRecordCount()));
        chkActive.setSelected(programa.isActive());

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
        chkActive.setEnabled(editable);
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

    public boolean isEditable() {return editable;}
}
