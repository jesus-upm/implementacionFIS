package trabajo_fis;

import trabajo_fis.usuarios.vistas.IVistaLoginRegistro;
import trabajo_fis.usuarios.vistas.IVistaUsuarios;
import trabajo_fis.usuarios.vistas.VistaLoginRegistro;
import trabajo_fis.usuarios.vistas.VistaUsuarios;

import java.util.Scanner;

import trabajo_fis.usuarios.logica.ControladorUsuario;
import trabajo_fis.usuarios.logica.IControladorUsuario;
import trabajo_fis.usuarios.factory.*;

public class VistaPrincipal {
	private IVistaUsuarios vistaUsuarios = new VistaUsuarios();
	private IVistaLoginRegistro vistaLoginRegistro = new VistaLoginRegistro();
	private IControladorUsuario interfazControladorUsuario = new ControladorUsuario();
	private ICreadorUsuarios factoria = new CreadorUsuario();


	public static void main(String[] args) {
		VistaPrincipal vistaPrincipal = new VistaPrincipal();

		while (true) {
			if (vistaPrincipal.interfazControladorUsuario.getTipoUsuario()!="null") {
				vistaPrincipal.mostrarVistaUsuarios();
			} else {
				vistaPrincipal.mostrarVistaLoginRegistro();
			}
		}
	}
	
	public void mostrarVistaLoginRegistro() {
		Scanner scanner = new Scanner(System.in);
		// pon un texto que pregunte si quieren iniciar sesión o registrarse y luego muestra la vista de login o registro dependiendo de la respuesta del usuario
		System.out.println("¿Desea iniciar sesión o registrarse? (iniciar/registrarse)");
		String respuesta = scanner.nextLine();

		if (respuesta.equals("iniciar")) {
			vistaLoginRegistro.iniciarSesion();
		} else if (respuesta.equals("registrarse")) {
			vistaLoginRegistro.registrarse(factoria);
		}
	}
	
	public void mostrarVistaUsuarios() {
		// que elija que hacer con numeros:
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("¿Qué desea hacer? (1: Mostrar preferencias artísticas, 2: Cambiar preferencias artísticas, 3: Darse de baja, 4: Alta instructor, 5: Baja instructor, 6: Mostrar instructor, 7: Mostrar participante, 8: Cerrar sesión)");
		int respuesta = scanner.nextInt();
		
		switch (respuesta) {
			case 1 -> vistaUsuarios.mostrarPreferenciaArtistica();
			case 2 -> vistaUsuarios.cambiarPreferenciaArtistica();
			case 3 -> vistaUsuarios.darseDeBaja();
			case 4 -> vistaUsuarios.altaInstructor();
			case 5 -> vistaUsuarios.bajaInstructor();
			case 6 -> vistaUsuarios.mostrarInstructor();
			case 7 -> vistaUsuarios.mostrarParticipante();
			case 8 -> cerrarSesion();
		}
	}
	
	public void mostrarVistaCursos() {
		
	}
	
	public void mostrarVistaAsociaciones() {
		
	}
	
	public void cerrarSesion() {
		
	}
}
