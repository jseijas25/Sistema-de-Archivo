package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelos.Usuario;
import reportes.ReportePdfUsuarios;
import vistas.VistaUsuarios;
import dataBase.UsuarioDAO;
import funciones.Libreria;

public class ControladorUsuarios implements ActionListener,KeyListener {
	private VistaUsuarios vista;
	private Usuario xusuario;
	public ControladorUsuarios(VistaUsuarios vistaUsuarios) {
		this.vista=vistaUsuarios;
		this.xusuario= new Usuario();
	}

	@Override

	public void actionPerformed(ActionEvent accion) {
		/*
	
//------------------------------------Retornar------------------------------------------------
		
		if(accion.getSource().equals(vista.getBtnRetornar()))
			vista.dispose();
//--------------------Imprimir-----------------------------------------------------------------
		
		if(accion.getSource().equals(vista.getBtnImprimir())){
			vista.dispose();
			new ReportePdfUsuarios();}		
//-----------------------------------Limpiar--------------------------------------------------		
	
		else if(accion.getSource().equals(vista.getBtnLimpiar())){
			vaciar();
			}
	
//------------------------------------Buscar--------------------------------------------------
		else if(accion.getSource().equals(vista.getBtnBuscar())){
			if(VerificarCedula((vista.getTxtCed().getText()))){
				JOptionPane.showMessageDialog(vista, "Cedula No Registrada", "Error", 0);
				vista.getTxtCed().requestFocus();
			}
			else{
				vista.setTxtCed(xusuario.getCedula());
				vista.setTxtApellido(xusuario.getApellido());
				vista.setTxtNombre(xusuario.getNombre());
				vista.setComboGenero(xusuario.getGenero()-1);
				Libreria funcion=new Libreria();
				vista.setTxtNombreUsuario(xusuario.getNombreUsuario());
				vista.setComboTipo(xusuario.getNivel()-1);
				vista.setTxtContrasena(xusuario.getClave());
				vista.setTxtEstado(xusuario.getStatus());
				
				
			}
		}
//------------------------------------------Buscar por Nombre-------------------
		
		else if(accion.getSource().equals(vista.getBtnBuscar2())){
			if(VerificarUsuarioNombre((vista.getTxtNombreUsuario().getText()))){
				JOptionPane.showMessageDialog(vista, "Nombre de usuario no Registrado", "Error", 0);
				vista.getTxtNombreUsuario().requestFocus();
			}
			else{
				JOptionPane.showMessageDialog(vista, "El nombre de usuario ya existe","Error",0);
				vista.setTxtCed(xusuario.getCedula());
				vista.setTxtApellido(xusuario.getApellido());
				vista.setTxtNombre(xusuario.getNombre());
				vista.setComboGenero(xusuario.getGenero()-1);
				vista.setTxtNombreUsuario(xusuario.getNombreUsuario());
				vista.setComboTipo(xusuario.getNivel()-1);
				vista.setTxtContrasena(xusuario.getClave());
				vista.setTxtEstado(xusuario.getStatus());
				
				
			}
		}
		
//------------------------------------Agregar-------------------------------------------------
				
		else if(accion.getSource().equals(vista.getBtnGuardar())){

				if(VerificarCedula(vista.getTxtCed().getText())&&
						VerificarUsuarioNombre(vista.getTxtNombreUsuario().getText()))	{
					UsuarioDAO baseDatos=new UsuarioDAO("archivopost");
					
					
					xusuario.setCedula(vista.getTxtCed().getText());
					xusuario.setNombre(vista.getTxtNombre().getText().toUpperCase());
					xusuario.setApellido(vista.getTxtApellido().getText().toUpperCase());
					xusuario.setGenero(vista.getComboGenero().getSelectedIndex()+1);
					xusuario.setNombreUsuario(vista.getTxtNombreUsuario().getText());
					xusuario.setClave(vista.getTxtContrasena().getText());
					xusuario.setNivel(vista.getComboTipo().getSelectedIndex()+1);
					xusuario.setStatus("A");
					
					
						if((xusuario.getCedula().trim().length()>0)&&
							(xusuario.getNombre().trim().length()>0)&&
							(xusuario.getApellido().trim().length()>0)&&
							(xusuario.getGenero()!=0)&&
							(xusuario.getNombreUsuario().trim().length()>0)&&
							(xusuario.getNivel()!=0)&&
							(xusuario.getClave().trim().length()>0))
							{

							baseDatos.Conectar();
							try{
								if (baseDatos.AgregarUsuario(xusuario)){
									JOptionPane.showMessageDialog(vista, "Usuario Registrado Exitosamente");
									vaciar();
							
								}
						
							} catch (SQLException e) {
								
							}
						}
					else{
						JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
						vista.getTxtCed().requestFocus();
					}
						
				}
				else{
					JOptionPane.showMessageDialog(vista, "La cedula o nombre de usuario ya existe","Error",0);
					vista.getTxtCed().requestFocus();
				}
			}//fin de agregar

		//------------------------------------Modificar----------------------------------------------
		
else if(accion.getSource().equals(vista.getBtnModificar())){
		if(!VerificarCedula(vista.getTxtCed().getText())&&
				!VerificarUsuarioNombre(vista.getTxtNombreUsuario().getText()))	{
						
						
						UsuarioDAO baseDatos=new UsuarioDAO("archivopost");
						
					
						xusuario.setCedula(vista.getTxtCed().getText());
						xusuario.setNombre(vista.getTxtNombre().getText().toUpperCase());
						xusuario.setApellido(vista.getTxtApellido().getText().toUpperCase());
						xusuario.setGenero(vista.getComboGenero().getSelectedIndex()+1);
						xusuario.setNombreUsuario(vista.getTxtNombreUsuario().getText());
						xusuario.setNivel(vista.getComboTipo().getSelectedIndex()+1);
						xusuario.setClave(vista.getTxtContrasena().getText());
						xusuario.setStatus("A");
						
						
							if((xusuario.getCedula().trim().length()>0)&&
								(xusuario.getNombre().trim().length()>0)&&
								(xusuario.getApellido().trim().length()>0)&&
								(xusuario.getGenero()!=0)&&
								(xusuario.getNombreUsuario().trim().length()>0)&&
								(xusuario.getNivel()!=0)&&
								(xusuario.getStatus().trim().length()>0))
						{


								baseDatos.Conectar();
							
								if (baseDatos.ModificarUsuario(xusuario)){
									JOptionPane.showMessageDialog(vista, "Usuario Modificado Exitosamente");
									
								
								}
							
							}
						else{
							JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
							vista.getTxtCed().requestFocus();
						}
							
					}
					else{
						JOptionPane.showMessageDialog(vista, "La cedula o nombre de usuario No existe","Error",0);
						vista.getTxtCed().requestFocus();
					}
					
					
			}//--------------fin de modificar---------------

//------------------------------------eliminar-----------------------------------------
		
else if(accion.getSource().equals(vista.getBtnEliminar())){
	if(!VerificarCedula(vista.getTxtCed().getText())&&
			!VerificarUsuarioNombre(vista.getTxtNombreUsuario().getText()))	{
		UsuarioDAO baseDatos =new UsuarioDAO("archivopost");
		xusuario.setStatus("D");
		
		
		
		if((xusuario.getCedula().trim().length()>0)&&
				(xusuario.getApellido().trim().length()>0)
				&&(xusuario.getNombre().trim().length()>0)
				&&(xusuario.getNombreUsuario().trim().length()>0)
				&&(xusuario.getClave().trim().length()>0)&&
				(xusuario.getNivel()!=0))	{

				baseDatos.Conectar();
			
				if (baseDatos.ModificarUsuario(xusuario)){
					JOptionPane.showMessageDialog(vista, "Usuario Desincorporado Exitosamente");
					this.vaciar();
					
				
				}
			
			}
		else{
			JOptionPane.showMessageDialog(vista, "Debe llenar todos los campos","Error",0);
			vista.getTxtCed().requestFocus();
		}
			
	}
	else{
		JOptionPane.showMessageDialog(vista, "La cedula o login No existe","Error",0);
		vista.getTxtCed().requestFocus();
	}
	
	
}

*/
				
}

	@Override
	public void keyPressed(KeyEvent accion) {
		/*
		if(accion.getSource().equals(vista.getTxtCed())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			
			if(VerificarCedula(vista.getTxtCed().getText()))
				vista.getTxtNombre().requestFocus();
				else{
						JOptionPane.showMessageDialog(vista, "Cedula ya Registrada", "Error", 0);
						vista.setTxtCed(xusuario.getCedula());
						vista.setTxtApellido(xusuario.getApellido());
						vista.setTxtNombre(xusuario.getNombre());
						vista.setComboGenero(xusuario.getGenero()-1);
						vista.setTxtNombreUsuario(xusuario.getNombreUsuario());
						vista.setComboTipo(xusuario.getNivel()-1);
						vista.setTxtContrasena(xusuario.getClave());
						vista.setTxtEstado(xusuario.getStatus());
				}

		else if(accion.getSource().equals(vista.getTxtNombre())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			vista.getTxtApellido().requestFocus();
		else if(accion.getSource().equals(vista.getTxtApellido())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			vista.getComboGenero().requestFocus();
		else if(accion.getSource().equals(vista.getComboGenero())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			vista.getTxtNombreUsuario().requestFocus();
		else if(accion.getSource().equals(vista.getTxtNombreUsuario())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			
			if(VerificarUsuarioNombre((vista.getTxtNombreUsuario().getText()))){
				vista.getComboTipo().requestFocus();
			}
			else{
				JOptionPane.showMessageDialog(vista, "El nombre de usuario ya existe","Error",0);
				vista.setTxtCed(xusuario.getCedula());
				vista.setTxtApellido(xusuario.getApellido());
				vista.setTxtNombre(xusuario.getNombre());
				vista.setComboGenero(xusuario.getGenero()-1);
				vista.setTxtNombreUsuario(xusuario.getNombreUsuario());
				vista.setComboTipo(xusuario.getNivel()-1);
				vista.setTxtContrasena(xusuario.getClave());
				
				vista.setTxtEstado(xusuario.getStatus());
				
				
			}
			
			
			
		else if(accion.getSource().equals(vista.getComboTipo())
				&&(accion.getKeyCode()== KeyEvent.VK_ENTER))
			vista.getTxtContrasena().requestFocus();
			*/
		}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//-------------> vaciar campos <-------------
	public void vaciar(){
	}
	
	
}