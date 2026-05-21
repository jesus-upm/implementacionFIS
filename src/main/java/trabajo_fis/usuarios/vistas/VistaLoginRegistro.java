package trabajo_fis.usuarios.vistas;

import java.util.HashMap;
import java.util.Scanner;

import trabajo_fis.usuarios.logica.IAutenticable;
import trabajo_fis.usuarios.factory.ICreadorUsuario;

import static java.lang.Integer.parseInt;

public class VistaLoginRegistro implements IVistaLoginRegistro {
   private IAutenticable autenticable;


   public VistaLoginRegistro(IAutenticable autenticable) {
      this.autenticable = autenticable;
   }

   @Override
   public void iniciarSesion() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("Introduce tu correo electrónico:");
      String email = scanner.nextLine();
      
      System.out.println("Introduce tu contraseña:");
      String contrasena = scanner.nextLine();


      if ( autenticable.iniciarSesion(email, contrasena)) {
         System.out.println("Ya has iniciado sesión.");
         
         
      } else {
         System.out.println("Correo electrónico o contraseña incorrectos.");
      }
   }

   @Override
   public void registrarse(ICreadorUsuario factoria) {
      Scanner sc = new Scanner(System.in);

      HashMap<String, String> datos = new HashMap<>();
      System.out.println("=== REGISTRO DE USUARIO ===");
      System.out.println("Seleccione tipo de usuario:");
      System.out.println("1. Participante Externo");
      System.out.println("2. Estudiante UPM");
      System.out.println("3. Personal UPM");

      do {

         int opcion = -1;

         while (opcion==-1) {
            System.out.print("Introduce el número de la opción que quieras: ");

            try {
               opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
               System.out.println("Debes introducir un número válido");
            }
         }


         switch (opcion) {
            case 1:
               datos.put("tipoUsuario", "participanteExterno");
               break;
            case 2:
               datos.put("tipoUsuario", "estudianteUPM");
               break;
            case 3:
               datos.put("tipoUsuario", "personalUPM");
               break;
            default:
               System.out.println("Opción inválida");
         }
      }while (datos.isEmpty());

      // Datos comunes
      System.out.print("Nick usuario: ");
      datos.put("nickUsuario", sc.nextLine());

      System.out.print("Nombre completo: ");
      datos.put("nombreCompleto", sc.nextLine());

      System.out.print("Correo electrónico: ");
      datos.put("correoElectronico", sc.nextLine());

      System.out.print("Contraseña(Debe tener 12 carácteres min, entre ellas una mayúscula y un número): ");
      datos.put("contrasena", sc.nextLine());

      System.out.print("DNI: ");
      datos.put("DNI", sc.nextLine());

      // Datos específicos
      String tipo = datos.get("tipoUsuario");

      if (tipo.equals("participanteExterno")) {

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

         System.out.print("Fecha de antigüedad (yyyy-mm-dd): ");
         datos.put("fechaAntiguedad", sc.nextLine());

         System.out.print("¿Es PDI? (true/false): ");
         datos.put("esPDI", sc.nextLine());
      }

      autenticable.registrarse(datos);
   }
}