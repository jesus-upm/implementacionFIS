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


	public VistaPrincipal(IControladorUsuario controladorUsuario, IAutenticable autenticable, IObtenerSesion obtenerSesion, ICreadorUsuario creadorUsuario) {
			this.iVistaLR = new VistaLoginRegistro(autenticable);
			this.iCreadorU=creadorUsuario;
			this.sesionActual = obtenerSesion;
			this.iVistaU = new VistaUsuarios(controladorUsuario);
		}

		public void iniciar(){
			while (true){
				if (sesionActual.getTipoUsuario()==null) {
					mostrarVistaLoginRegistro();
				}else {
					mostrarVistaUsuarios();
				}
			}
		}


		public void mostrarVistaLoginRegistro() {

			String inputUsuario="";

			Scanner sc = new Scanner(System.in);

			System.out.print("¿Quieres iniciar sesión o registrarte? Introduce el número de la opción que desees:\n"
					+ "1. Iniciar sesión\n"
					+ "2. Registrarse\n"
					+ "Escoge tu opción: ");

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

			if(sesionActual.getTipoUsuario().equals("Administrador")) {
				do {
					System.out.print("¿Que deseas realizar? Introduce el número de la opción que desees:\n"
							+ "1. Alta instructor\n"
							+ "2. Baja instructor\n"
							+ "3. Mostrar instructor\n"
							+ "4. Mostrar participante\n"
							+ "5. Cerrar sesion\n"
							+ "Elige tu opción: ");
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

				}while(!inputUsuario.equals("5"));

			}else if(sesionActual.getTipoUsuario().equals("EstudianteUPM")
					|| sesionActual.getTipoUsuario().equals("Instructor")
					|| sesionActual.getTipoUsuario().equals("PersonalUPM")
					|| sesionActual.getTipoUsuario().equals("ParticipanteExterno")) {

					System.out.print("¿Que deseas realizar?\n"
							+ "1. Darme de baja\n"
							+ "2. Cerrar sesion\n"
							+ "Introduce tu opción(introduce el número de la opción que quieras): ");

					inputUsuario=sc.nextLine();

					switch(inputUsuario) {
						case "1":iVistaU.darseDeBaja();
								 cerrarSesion();
							break;

						case "2":cerrarSesion();
							break;

						default: System.out.println("Opción no válida introducida");
							break;

					}
			}


		}
		public void cerrarSesion() {
			sesionActual=null;
			System.out.println("Has cerrado sesion correctamente");
			mostrarVistaLoginRegistro();

		}



	}
