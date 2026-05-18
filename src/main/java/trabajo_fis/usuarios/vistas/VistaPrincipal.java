package trabajo_fis.usuarios.vistas;

import java.util.Scanner;

import trabajo_fis.usuarios.dominio.TipoUsuario;
import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.*;
import trabajo_fis.usuarios.logica.IObtenerSesion;

public class VistaPrincipal {
	
	private IObtenerSesion sesionActual;
	
	public void mostrarVistaLoginRegistro() {
		
		IVistaLoginRegistro vlr= new VistaLoginRegistro();
		String inputUsuario="";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("¿Quieres iniciar sesión o registrarte? Introduce el número de la opción que desees:\n"
				+ "1. Iniciar sesión\n"
				+ "2. Registrarse\n");
		
		inputUsuario=sc.nextLine();
		

		if(inputUsuario.equals("1")) {
	        vlr.iniciarSesion();
			
		}else if (inputUsuario.equals("2")) {
			
			ICreadorUsuarios icu= new CreadorUsuario();
			
			vlr.registrarse(icu);   
			
		} else {
		    System.out.println("Opción no válida");
		}
		
	}
	
	public void mostrarVistaUsuarios() {
		
		IVistaUsuarios iv= new VistaUsuarios();
		Usuario u= sesionActual.getSesionActual();
		TipoUsuario tu=u.getTipoUsuario();
		if(tu.equals(TipoUsuario.administrador)) {
			// TODO Caso en el cual es admin el que inicia sesion
		}
		
		
	}
	public void cerrarSesion() {
		sesionActual=null;
		System.out.println("Has cerrado sesion correctamente");
		mostrarVistaLoginRegistro();
		
	}



}
