package controladores;

import dataBase.EstudianteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Estudiante;
import vistas.VistaBuscarEstudiante;
import vistas.VistaEstudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class ControladorEstudiante extends BaseControlador {

    private Estudiante estudiante;
    private VistaEstudiante vista;

    public ControladorEstudiante(VistaEstudiante vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnCrear())){
            if(vista.getData(estudiante)){
                if(EstudianteDAO.create(estudiante)){
                    vista.setMessage("Estudiante creado con exito");
                    vista.dispose();
                    _Con.getInstance().setOperation(OperationType.READ);
                    new VistaEstudiante();
                } else {
                    vista.setError("Estudiante no creado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnEditar())){
            if(vista.isEditable()){
                if(vista.getData(estudiante)){
                    if(EstudianteDAO.update(estudiante)){
                        vista.setMessage("Exito al actualizar al estudiante");
                    }else{
                        vista.setError("Estutiante no actualizado");
                    }
                }
            }
            vista.setEditable();
        }
        if(e.getSource().equals(vista.getBtnBorrar())){
            int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea eliminar el estudiante?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == JOptionPane.YES_OPTION){
                if(EstudianteDAO.delete(estudiante.getCedula())){
                    vista.setMessage("Estudiante eliminado con exito");
                    vista.dispose();
                    new VistaBuscarEstudiante();
                }else{
                    vista.setError("Estudiante no eliminado");
                }
            }
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            if(vista.isEditable()){
                if(_Con.getInstance().getOperation()==OperationType.READ){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la edición?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaEstudiante();
                    }
                }else if(_Con.getInstance().getOperation() == OperationType.CREATE){
                    int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea cancelar la creación\ndel nuevo usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if(op == JOptionPane.YES_OPTION){
                        new VistaBuscarEstudiante();
                    }
                }
            }
            vista.dispose();
        }
        vista.showError();
        vista.showMessage();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
        estudiante = _Con.getInstance().getEstudiante();
        vista.setData(estudiante);
    }
}
