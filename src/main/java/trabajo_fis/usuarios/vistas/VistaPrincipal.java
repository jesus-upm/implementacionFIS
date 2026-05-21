package trabajo_fis.usuarios.vistas;

import java.util.Scanner;

import trabajo_fis.usuarios.factory.*;
import trabajo_fis.usuarios.logica.IControladorUsuario;

import trabajo_fis.usuarios.logica.IAutenticable;
import trabajo_fis.usuarios.logica.IObtenerSesion;

public class VistaPrincipal {
	private IObtenerSesion sesionActual;
	private IVistaLoginRegistro iVistaLR;
	private IVistaUsuarios iVistaU;
	private ICreadorUsuario iCreadorU;

	
	public VistaPrincipal(IControladorUsuario controladorUsuario, IAutenticable autenticable, IObtenerSesion obtenerSesion,ICreadorUsuario creadorUsuario) {
		this.iVistaLR = new VistaLoginRegistro(autenticable);
		this.iCreadorU=creadorUsuario;
		this.sesionActual = obtenerSesion;
		this.iVistaU = new VistaUsuarios(controladorUsuario);
	}
	public void iniciar(){
		while (true){
			if (sesionActual.getSesionActual()==null) {
				mostrarVistaLoginRegistro();
			}else {
				mostrarVistaUsuarios();
			}
		}
	}
	
	
	public void mostrarVistaLoginRegistro() {
		
		String inputUsuario="";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("¿Quieres iniciar sesión o registrarte? Introduce el número de la opción que desees:\n"
				+ "1. Iniciar sesión\n"
				+ "2. Registrarse\n");
		
		inputUsuario=sc.nextLine();
		

		if(inputUsuario.equals("1")) {
	        iVistaLR.iniciarSesion();
			
		}else if (inputUsuario.equals("2")) {
						
			iVistaLR.registrarse(iCreadorU);
			return;
			
		} else {
		    System.out.println("Opción no válida");
		}
		
	}
	
	public void mostrarVistaUsuarios() {
		String inputUsuario="";
		Scanner sc = new Scanner(System.in);

		if(sesionActual.getSesionActual().equals("Administrador")) {
			do {			
				System.out.println("¿Que deseas realizar? Introduce el número de la opción que desees:\n"
						+ "1. Alta instructor\n"
						+ "2. Baja instructor\n"
						+ "3. Mostrar instructor\n"
						+ "4. Mostrar participante\n"
						+ "5. Cerrar sesion\n");
				inputUsuario=sc.nextLine();
				
					switch(inputUsuario) {
						
						case "1":iVistaU.altaInstructor();
							break;
							
						case "2":iVistaU.bajaInstructor();
						break;
						
						case "3":iVistaU.mostrarInstructor();
						break;
						
						case "4":iVistaU.mostrarParticipante();
						break;
						
						case "5":cerrarSesion();
						break;
						
						default: System.out.println("Opción no válida introducida");
						break;
						
					}

				}while(!inputUsuario.equals("3"));
			
		}else if(sesionActual.getSesionActual().equals("EstudianteUPM") || sesionActual.getSesionActual().equals("Instructor") || sesionActual.getSesionActual().equals("ParticipanteExterno")) {
			
			do {
				iVistaU.mostrarPreferenciaArtistica();
				
				System.out.println("¿Que deseas realizar? Introduce el número de la opción que desees:\n"
						+ "1. Modificar preferencias artisticas\n"
						+ "2. Darme de baja\n"
						+ "3. Cerrar sesion\n");
				
				inputUsuario=sc.nextLine();
				
				switch(inputUsuario) {
					
					case "1":iVistaU.cambiarPreferenciaArtistica();
						break;
						
					case "2":iVistaU.darseDeBaja();
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
