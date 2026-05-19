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
	        mostrarVistaUsuarios();
			
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
		
		String inputUsuario="";
		Scanner sc = new Scanner(System.in);
		
		
		if(tu.equals(TipoUsuario.administrador)) {
		
			
			do {			
				System.out.println("¿Que deseas realizar? Introduce el número de la opción que desees:\n"
						+ "1. Alta instructor\n"
						+ "2. Baja instructor\n"
						+ "3. Mostrar instructor\n"
						+ "4. Mostrar participante\n"
						+ "5. Cerrar sesion\n");
				inputUsuario=sc.nextLine();
				
					switch(inputUsuario) {
						
						case "1":iv.altaInstructor();
							break;
							
						case "2":iv.bajaInstructor();
						break;
						
						case "3":iv.mostrarInstructor();
						break;
						
						case "4":iv.mostrarParticipante();
						break;
						
						case "5":cerrarSesion();
						break;
						
						default: System.out.println("Opción no válida introducida");
						break;
						
					}

				}while(!inputUsuario.equals("3"));
			
		}else if(tu.equals(TipoUsuario.estudianteUPM)|| tu.equals(TipoUsuario.personalUPM) || tu.equals(TipoUsuario.participanteExterno)) {
			
			do {
				iv.mostrarPreferenciaArtistica();
				
				System.out.println("¿Que deseas realizar? Introduce el número de la opción que desees:\n"
						+ "1. Modificar preferencias artisticas\n"
						+ "2. Darme de baja\n"
						+ "3. Cerrar sesion\n");
				
				inputUsuario=sc.nextLine();
				
				switch(inputUsuario) {
					
					case "1":iv.cambiarPreferenciaArtistica();
						break;
						
					case "2":iv.darseDeBaja();
					break;
					
					case "3":cerrarSesion();
					break;
					
					default: System.out.println("Opción no válida introducida");
					break;
						
				}
				
			}while(!inputUsuario.equals("3"));
		}
		
		
	}
	public void cerrarSesion() {
		sesionActual=null;
		System.out.println("Has cerrado sesion correctamente");
		mostrarVistaLoginRegistro();
		
	}



}
