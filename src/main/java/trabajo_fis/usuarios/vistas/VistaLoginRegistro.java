package trabajo_fis.usuarios.vistas;

import java.util.Scanner;

import trabajo_fis.usuarios.logica.ControladorUsuario;

public class VistaLoginRegistro implements IVistaLoginRegistro {
   private IAutenticable autenticable = new ControladorUsuario();

   @Override
   public void iniciarSesion() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("Introduce tu correo electrónico:");
      String email = scanner.nextLine();
      
      System.out.println("Introduce tu contraseña:");
      String contrasena = scanner.nextLine();

      boolean exitoso = autenticable.iniciarSesion(email, contrasena);

      if (exitoso) {
         System.out.println("Ya has iniciado sesión.");
      } else {
         System.out.println("Correo electrónico o contraseña incorrectos.");
      }
   }

   @Override
   public void registrarse() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("Introduce tu nick:");
      String nick = scanner.nextLine();
      
      System.out.println("Introduce tu nombre completo:");
      String nombreCompleto = scanner.nextLine();
      
      System.out.println("Introduce tu correo electrónico:");
      String correoElectronico = scanner.nextLine();
      
      System.out.println("Introduce tu contraseña:");
      String contrasena = scanner.nextLine();
      
      System.out.println("Introduce tu DNI:");
      String DNI = scanner.nextLine();
      
      System.out.println("Introduce tu tarjeta bancaria:");
      String tarjetaBancaria = scanner.nextLine();

      autenticable.registrarse(nick, nombreCompleto, correoElectronico, contrasena, DNI, tarjetaBancaria);
   }
}
