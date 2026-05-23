package trabajo_fis.usuarios.vistas;

import java.util.HashMap;
import java.util.Scanner;

import servidor.IUPMUserData;
import trabajo_fis.usuarios.logica.IAutenticable;
import trabajo_fis.usuarios.factory.ICreadorUsuario;
import servidor.ExternalLDAP;

public class VistaLoginRegistro implements IVistaLoginRegistro {
   private IAutenticable autenticable;

   public VistaLoginRegistro(IAutenticable autenticable) {
      this.autenticable = autenticable;
   }

   @Override
   public void iniciarSesion() {
      IUPMUserData userData = ExternalLDAP.LoginLDAP();
      String email = "";
      if (userData != null) email = userData.getEmail();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Introduce tu contraseña:");
      String contrasena = scanner.nextLine();
      if ( autenticable.iniciarSesion(email, contrasena)) {
         System.out.println("Ya has iniciado sesión.");
      }
   }

   @Override
   public void registrarse(ICreadorUsuario factoria) {
      HashMap<String, String> datos = new HashMap<>();
      System.out.println("=== INICIO DE REGISTRO DE USUARIO ===");
      IUPMUserData userData = ExternalLDAP.LoginLDAP();
      if (userData != null) datos.put("correoElectronico", userData.getEmail());
      Scanner sc = new Scanner(System.in);
      do {
         System.out.println("Seleccione tipo de usuario:");
         System.out.println("1. Participante Externo");
         System.out.println("2. Estudiante UPM");
         System.out.println("3. Personal UPM");
         String opcion = "";

         while (opcion=="") {
            System.out.print("Introduce el número de la opción que quieras: ");
            opcion = sc.next();
         }


         switch (opcion) {
            case "1":
               datos.put("tipoUsuario", "ParticipanteExterno");
               break;
            case "2":
               datos.put("tipoUsuario", "EstudianteUPM");
               break;
            case "3":
               datos.put("tipoUsuario", "PersonalUPM");
               break;
            default:
               System.out.println("Opción inválida");
         }
      } while (datos.isEmpty());

      // Datos comunes
      boolean condicionDeFin = false;
      String nickUser = null, mensajeNick = "Nick usuario (entre 4 y 12 caracteres): ";
      sc.nextLine(); // Limpieza de buffer
      while (!condicionDeFin) {
         System.out.print(mensajeNick);
         nickUser = sc.nextLine();
         if (nickUser.length() >= 4 && nickUser.length() <= 12) {
            datos.put("nickUsuario", nickUser);
            condicionDeFin = true;
         } else {
            mensajeNick = "Introduce un nick de usuario válido (entre 4 y 12 caracteres): ";
         }
      }

      System.out.print("Nombre completo: ");
      datos.put("nombreCompleto", sc.nextLine());

      Scanner debugScanner = new Scanner(System.in);
      boolean contraValida = false;
      String psswd = null, mensajePass = "Contraseña(Debe tener 12 carácteres min, entre ellas una minúscula, una mayúscula y un número): ";
      while (!contraValida) {
         System.out.print(mensajePass);
         psswd = debugScanner.nextLine();
         if (psswd.length() >= 12 && psswd.chars().anyMatch(Character::isLowerCase) && psswd.chars().anyMatch(Character::isUpperCase)
            && psswd.chars().anyMatch(Character::isDigit)
         ) {
            datos.put("contrasena", psswd);
            contraValida = true;
         } else {
            mensajePass = "Prueba a introducir una contraseña valida (Debe tener 12 carácteres min, entre ellas una minúscula, una mayúscula y un número): ";
         }
      }

      System.out.print("DNI: ");
      datos.put("DNI", debugScanner.nextLine());

      // Datos específicos
      String tipo = datos.get("tipoUsuario");

      if (tipo.equals("ParticipanteExterno")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", debugScanner.nextLine());

      } else if (tipo.equals("EstudianteUPM")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", debugScanner.nextLine());

         System.out.print("Número de matrícula: ");
         datos.put("numMatricula", debugScanner.nextLine());

      } else if (tipo.equals("PersonalUPM")) {

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", debugScanner.nextLine());

         System.out.print("Fecha de antigüedad (yyyy-mm-dd): ");
         datos.put("fechaAntiguedad", debugScanner.nextLine());

         System.out.print("¿Es PDI? (true/false): ");
         datos.put("esPDI", debugScanner.nextLine());
      }

      autenticable.registrarse(datos);
   }
}