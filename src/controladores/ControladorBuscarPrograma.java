package controladores;

import dataBase.ProgramaDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Programa;
import vistas.VistaBuscarPrograma;
import vistas.VistaPrograma;
import vistas.VistaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControladorBuscarPrograma extends BaseControlador {
    private Programa programa;
    private VistaBuscarPrograma vista;

    public ControladorBuscarPrograma(VistaBuscarPrograma vista) {
        this.vista = vista;
        this.programa = new Programa();
    }

    private void buscarPrograma() {
        if(vista.getData(programa)) {
            _Con.getInstance().setPrograma(programa);
            if (ProgramaDAO.read(programa)) {
                _Con.getInstance().setOperation(OperationType.READ);
                new VistaPrograma();
                vista.dispose();
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Programa no encontrado\n¿Desea crearlo?", "No encontrado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (response){
                    case JOptionPane.YES_OPTION:
                        _Con.getInstance().setOperation(OperationType.CREATE);
                        new VistaPrograma();
                        vista.dispose();
                        break;
                    case JOptionPane.NO_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                    default:
                        break;
                }
            }
        } else {
            vista.showError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnBuscar())){
            buscarPrograma();
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            vista.dispose();
        }
    }
}
