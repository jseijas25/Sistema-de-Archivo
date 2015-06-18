package controladores;

import dataBase.EstudianteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Estudiante;
import vistas.VistaBuscarEstudiante;
import vistas.VistaEstudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControladorBuscarEstudiante extends BaseControlador {
    private Estudiante estudiante;
    private VistaBuscarEstudiante vista;

    public ControladorBuscarEstudiante(VistaBuscarEstudiante vista) {
        this.vista = vista;
        this.estudiante = new Estudiante();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnBuscar())){
            if(vista.getData(estudiante)) {
                if (EstudianteDAO.read(estudiante)) {
                    _Con.getInstance().setEstudiante(estudiante);
                    _Con.getInstance().setOperation(OperationType.READ);
                    new VistaEstudiante();
                } else {
                    int response = JOptionPane.showConfirmDialog(null, "Estudiante no encontrado\n¿Desea crearlo?", "No encontrado",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    switch (response){
                        case JOptionPane.YES_OPTION:
                            _Con.getInstance().setOperation(OperationType.CREATE);
                            new VistaEstudiante();
                            break;
                        case JOptionPane.NO_OPTION:
                        case JOptionPane.CLOSED_OPTION:
                        default:
                            break;
                    }
                    vista.dispose();
                }
            } else {
                vista.showError();
            }
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            vista.dispose();
        }
    }
}
