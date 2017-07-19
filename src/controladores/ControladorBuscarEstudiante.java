package controladores;

import dataBase.EstudianteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Estudiante;
import vistas.VistaBuscarEstudiante;
import vistas.VistaEstudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ControladorBuscarEstudiante extends BaseControlador {
    private Estudiante estudiante;
    private VistaBuscarEstudiante vista;

    public ControladorBuscarEstudiante(VistaBuscarEstudiante vista) {
        this.vista = vista;
        this.estudiante = new Estudiante();
    }

    private void buscarEstudiante() {
        if(vista.getData(estudiante)) {
            _Con.getInstance().setEstudiante(estudiante);
            if (EstudianteDAO.read(estudiante)) {
                _Con.getInstance().setOperation(OperationType.READ);
                new VistaEstudiante();
                vista.dispose();
            } else {
                int response = JOptionPane.showConfirmDialog(null, "Estudiante no encontrado\n¿Desea crearlo?", "No encontrado",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (response){
                    case JOptionPane.YES_OPTION:
                        _Con.getInstance().setOperation(OperationType.CREATE);
                        new VistaEstudiante();
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
            buscarEstudiante();
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            vista.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (event.getSource().equals(vista.getTxtCedula())) {
                buscarEstudiante();
            }
        }
    }
}
