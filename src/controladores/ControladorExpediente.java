package controladores;

import java.awt.event.ActionEvent;

import dataBase.ExpedienteDAO;
import funciones.OperationType;
import funciones._Con;
import modelos.Expediente;
import vistas.PestanaExpediente;

public class ControladorExpediente extends BaseControlador {

    private Expediente expediente;
    private PestanaExpediente vista;

    public ControladorExpediente(PestanaExpediente vista) {
        this.vista = vista;
        this.expediente = new Expediente();
    }

    private void borrar() {

    }

    private void solvencia() {

    }

    private void crear() {

    }

    private void editar() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(vista.getBtnBorrar())){
            borrar();
        }
        if(e.getSource().equals(vista.getBtnSolvencia())){
            solvencia();
        }
        if(e.getSource().equals(vista.getBtnCrear())){
            crear();
        }
        if(e.getSource().equals(vista.getBtnEditar())){
            editar();
        }
    }
}