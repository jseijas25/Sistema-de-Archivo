package controladores;

import dataBase.EstudianteDAO;
import dataBase.ExpedienteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Estudiante;
import modelos.Expediente;
import reportes.ReportePdfSolvencia;
import vistas.PestanaExpediente;
import vistas.VistaBuscarEstudiante;
import vistas.VistaEstudiante;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControladorEstudiante extends BaseControlador implements ChangeListener {

    private Estudiante estudiante;
    private Expediente expediente;
    private VistaEstudiante vista;

    public ControladorEstudiante(VistaEstudiante vista) {
        this.vista = vista;
        estudiante = new Estudiante();
        expediente = new Expediente();
    }

    public void crearPestanaExpedienteNueva(){
        _Con.getInstance().setOperation(OperationType.CREATE);
        vista.getPestanaExpedientes().add(new PestanaExpediente());
        vista.recargarExpedientes();
    }

    private void salir() {
        if(vista.isEditable()){
            if(_Con.getInstance().getOperation()== OperationType.READ){
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

    private void eliminarEstudiante() {
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

    private void editarEstudiante() {
        if(vista.isEditable()){
            if(vista.getData(estudiante)){
                if(EstudianteDAO.update(estudiante)){
                    vista.setMessage("Exito al actualizar al estudiante");
                }else{
                    vista.setError("Estudiante no actualizado");
                }
            }
        }
        vista.setEditable();
    }

    private void crearEstudiante() {
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

    private void crearExpediente(PestanaExpediente pestanaExpediente){
        if(pestanaExpediente.getData(expediente, vista)){
            expediente.setId_Estudiante(estudiante.getCedula());
            if(ExpedienteDAO.create(expediente)){
                vista.setMessage("Expediente creado con exito!");
                _Con.getInstance().setOperation(OperationType.READ);
                vista.dispose();
                new VistaEstudiante();
            } else {
                vista.setError("Expediente no creado");
            }
        }
    }

    private void editarExpediente(PestanaExpediente pestanaExpediente) {
        if(pestanaExpediente.isEditable()){
            if(pestanaExpediente.getData(expediente, vista)){
                if(ExpedienteDAO.update(expediente)){
                    vista.setMessage("Exito al actualizar el expediente");
                }else{
                    vista.setError("Expediente no actualizado");
                }
            }
        }
        pestanaExpediente.setEditable();
    }

    private void borrarExpediente(PestanaExpediente pestanaExpediente) {
        int op = JOptionPane.showConfirmDialog(vista, "¿Está seguro que desea eliminar el expediente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if(op == JOptionPane.YES_OPTION){
            pestanaExpediente.getData(expediente, vista);
            if(ExpedienteDAO.delete(expediente.getId())){
                vista.setMessage("Expediente eliminado con exito");
                _Con.getInstance().setOperation(OperationType.READ);
                vista.dispose();
                new VistaEstudiante();
            }else{
                vista.setError("Expediente no eliminado");
            }
        }
    }

    private void solvencia(PestanaExpediente pestanaExpediente) {
        vista.getData(estudiante);
        pestanaExpediente.getData(expediente, vista);
        if(expediente.isSolvente()){
            vista.dispose();
            _Con.getInstance().setExpediente(expediente);
            _Con.getInstance().setEstudiante(estudiante);
            new ReportePdfSolvencia();
        }else{
            vista.setError("No está solvente");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnCrear())){
            crearEstudiante();
        }
        if(e.getSource().equals(vista.getBtnEditar())){
            editarEstudiante();
        }
        if(e.getSource().equals(vista.getBtnBorrar())){
            eliminarEstudiante();
        }
        if(e.getSource().equals(vista.getBtnSalir())){
            salir();
        }
        if(e.getSource().equals(vista.getBtnCrearExpediente())){
            crearPestanaExpedienteNueva();
        }

        for(PestanaExpediente pestanaExpediente : vista.getPestanaExpedientes()){
            if(e.getSource().equals(pestanaExpediente.getBtnCrear())){
                crearExpediente(pestanaExpediente);
            }
            if(e.getSource().equals(pestanaExpediente.getBtnEditar())){
                editarExpediente(pestanaExpediente);
            }
            if(e.getSource().equals(pestanaExpediente.getBtnBorrar())){
                borrarExpediente(pestanaExpediente);
            }
            if(e.getSource().equals(pestanaExpediente.getBtnSolvencia())){
                solvencia(pestanaExpediente);
            }
            if(e.getSource().equals(pestanaExpediente.getChkAuto())){
                pestanaExpediente.setAuto();
            }
        }
        vista.showError();
        vista.showMessage();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
        estudiante = _Con.getInstance().getEstudiante();
        _Con.getInstance().setExpedientes(new ArrayList<Expediente>());
        ExpedienteDAO.findAllByID(_Con.getInstance().getExpedientes(), estudiante.getCedula());
        for(Expediente expediente : _Con.getInstance().getExpedientes()){
            PestanaExpediente pe = new PestanaExpediente();
            pe.setData(expediente);
            pe.setControlador(this);
            vista.getPestanaExpedientes().add(pe);
        }
        vista.recargarExpedientes();
        vista.setData(estudiante);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
