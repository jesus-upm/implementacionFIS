package trabajo_fis.usuarios.vistas;

import java.util.HashMap;
import java.util.Scanner;

import trabajo_fis.usuarios.logica.IAutenticable;
import trabajo_fis.usuarios.factory.ICreadorUsuarios;
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
   public void registrarse(ICreadorUsuarios factoria) {
      Scanner sc = new Scanner(System.in);

      HashMap<String, String> datos = new HashMap<>();
      System.out.println("=== REGISTRO DE USUARIO ===");
      System.out.println("Seleccione tipo de usuario:");
      System.out.println("1. Instructor");
      System.out.println("2. Participante Externo");
      System.out.println("3. Estudiante UPM");
      System.out.println("4. Personal UPM");

      do {

         int opcion = Integer.parseInt(sc.nextLine());


         switch (opcion) {
            case 1:
               datos.put("tipoUsuario", "instructor");
               break;
            case 2:
               datos.put("tipoUsuario", "participanteExterno");
               break;
            case 3:
               datos.put("tipoUsuario", "estudianteUPM");
               break;
            case 4:
               datos.put("tipoUsuario", "personalUPM");
               break;
            default:
               System.out.println("Opción inválida");
         }
      }while (datos.containsKey("tipoUsuario"));

      // Datos comunes
      System.out.print("Nick usuario: ");
      datos.put("nickUsuario", sc.nextLine());

      System.out.print("Nombre completo: ");
      datos.put("nombreCompleto", sc.nextLine());

      System.out.print("Correo electrónico: ");
      datos.put("correoElectronico", sc.nextLine());

      System.out.print("Contraseña: ");
      datos.put("contraseña", sc.nextLine());

      System.out.print("DNI: ");
      datos.put("DNI", sc.nextLine());

      // Datos específicos
      String tipo = datos.get("tipoUsuario");

      if (tipo.equals("instructor")) {

         System.out.print("IBAN: ");
         datos.put("IBAN", sc.nextLine());

      } else if (tipo.equals("participanteExterno")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", sc.nextLine());

      } else if (tipo.equals("estudianteUPM")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", sc.nextLine());

         System.out.print("Rol UPM: ");
         datos.put("rolUPM", sc.nextLine());

         System.out.print("Número de matrícula: ");
         datos.put("numMatricula", sc.nextLine());

      } else if (tipo.equals("personalUPM")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", sc.nextLine());

         System.out.print("Rol UPM: ");
         datos.put("rolUPM", sc.nextLine());

         System.out.print("Fecha de antigüedad (yyyy-mm-dd): ");
         datos.put("fechaAntiguedad", sc.nextLine());

         System.out.print("¿Es PDI? (true/false): ");
         datos.put("esPDI", sc.nextLine());
      }

      autenticable.registrarse(factoria,datos);


   }
      }
