package trabajo_fis.usuarios.vistas;

import java.time.LocalDate;
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
      Scanner scanner = new Scanner(System.in);
      System.out.print("Correo electrónico: ");
      String email = scanner.nextLine();
      System.out.print("Contraseña: ");
      String contrasena = scanner.nextLine();
      if(autenticable.iniciarSesion(email,contrasena)){
         System.out.println("Se inició sesión correctamente");
      }
      else {
         System.out.println("Error al iniciar sesion contraseña/correro invalido");
      }
   }

   @Override
   public void registrarse() {
      Scanner sc = new Scanner(System.in);

      HashMap<String, String> datos = new HashMap<>();

//  SELECCIÓN DE METODO DE REGISTRO
      System.out.println("=== REGISTRO DE USUARIO ===");
      System.out.println("¿Cómo quieres registrarte?");
      System.out.println("1. Con cuenta IUPM");
      System.out.println("2. Sin cuenta IUPM");
      System.out.print("Elige una opción (1/2): ");
      String metodo = sc.nextLine().trim();

      if (metodo.equals("1")) {

         IUPMUserData userData = ExternalLDAP.LoginLDAP();

         if (userData == null) {
            System.out.println("Error: credenciales IUPM incorrectas.");
            return;
         }

         // Datos del LDAP metidos igual que si los hubiera escrito el usuario
         boolean nickCorrecto = false;
         String nickMensaje = "Introduce tu nick (entre 4 y 12 caracteres): ";
         while (!nickCorrecto) {
            System.out.print(nickMensaje);
            String nickUsuario = sc.nextLine();
            if (nickUsuario.length() >= 4 && nickUsuario.length() <= 12) {
               datos.put("nickUsuario", nickUsuario);
               nickCorrecto = true;
            } else {
               nickMensaje = "Introduce un nick correcto (entre 4 y 12 caracteres): ";
            }
         }
         // getId() es el nick
         System.out.print("Introduce tu nombre completo: ");
         String nombreCompleto = sc.nextLine();
         datos.put("nombreCompleto", nombreCompleto);
         datos.put("correoElectronico", userData.getEmail());

         String mensajeContrasena = "Introduce la contraseña (min. 12 caracteres, 1 mayúscula ,1 minúscula y 1 número): ";
         boolean contrasenaCorrecta = false;
         while (!contrasenaCorrecta) {
            System.out.print(mensajeContrasena);
            String contrasena = sc.nextLine();
            if (contrasena.length() >= 12 && contrasena.chars().anyMatch(Character::isDigit) && contrasena.chars().anyMatch(Character::isUpperCase) && contrasena.chars().anyMatch(Character::isLowerCase)) {
               datos.put("contrasena", contrasena);
               contrasenaCorrecta = true;
            } else {
               mensajeContrasena = "Introduce de nuevo una contraseña que cumpla los requisitos indicados (min. 12 caracteres, 1 mayúscula ,1 minúscula y 1 número): ";
            }
         }

         String mensajeDNI = "Introduce tu DNI: ";
         boolean dniCorrecto = false;
         while (!dniCorrecto) {
            System.out.print(mensajeDNI);
            String dni = sc.nextLine();
            if (dni.length() == 9 && dni.chars().limit(8).allMatch(Character::isDigit) && Character.isLetter(dni.charAt(8))) {
               datos.put("DNI", dni);
               dniCorrecto = true;
            } else {
               mensajeDNI = "Introduce un DNI en formato correcto: ";
            }
         }

         String tarjetaMensaje = "Tarjeta bancaria de 16 dígitos: ";
         boolean tarjetaCorrecta = false;
         while (!tarjetaCorrecta) {
            System.out.print(tarjetaMensaje);
            String tarjeta = sc.nextLine();
            if (tarjeta.length() == 16 && tarjeta.chars().allMatch(Character::isDigit)) {
               datos.put("tarjetaBancaria", tarjeta);
               tarjetaCorrecta = true;
            } else {
               tarjetaMensaje = "Formato de tarjeta inválido. Prueba de nuevo: ";
            }
         }

         String correoIntroducido = userData.getEmail();
         if (correoIntroducido.endsWith("@alumnos.upm.es")) {
            datos.put("tipoUsuario","EstudianteUPM");
            datos.put("esPDI","false");
            System.out.print("Número de matrícula: ");
            datos.put("numMatricula", sc.nextLine());

         } else if (correoIntroducido.endsWith("@upm.es")){
            datos.put("tipoUsuario","PersonalUPM");
            datos.put("esPDI","true");

            String mensajeFecha = "Fecha de antigüedad (yyyy-MM-dd): ";
            boolean fechaCorrecta = false;
            while (!fechaCorrecta) {
               System.out.print(mensajeFecha);
               String fechaStr = sc.nextLine().trim();
               try {
                  LocalDate.parse(fechaStr);
                  fechaCorrecta = true;
                  datos.put("fechaAntiguedad", fechaStr);
               } catch (Exception e ){
                  mensajeFecha = "Introduce un formato de fecha correcto: ";
               }
            }
         }

      } else {
         datos.put("tipoUsuario","ParticipanteExterno");
         String nickExternoMensaje = "Nick usuario (entre 4 y 12 caracteres): ";
         boolean externoNickCorrecto = false;
         while (!externoNickCorrecto) {
            System.out.print(nickExternoMensaje);
            String externoNick = sc.nextLine();
            if (externoNick.length() >= 4 && externoNick.length() <= 12) {
               datos.put("nickUsuario", externoNick);
               externoNickCorrecto = true;
            } else {
               nickExternoMensaje = "Introduce un nick que cumpla los requisitos (entre 4 y 12 caracteres): ";
            }
         }

         System.out.print("Nombre completo: ");
         datos.put("nombreCompleto", sc.nextLine());

         String mensajeExternoCorreo = "Correo electrónico: ";
         boolean correoExternoCorrecto = false;
         while (!correoExternoCorrecto) {
            System.out.print(mensajeExternoCorreo);
            String correoExterno = sc.nextLine();
            if (correoExterno.matches("[^@]+@[^@]+\\.(com|es)") && !correoExterno.endsWith("@upm.es")) {
               datos.put("correoElectronico", correoExterno);
               correoExternoCorrecto = true;
            } else {
               if (correoExterno.endsWith("@upm.es")) {
                  mensajeExternoCorreo = "No puedes registrarte con un correo de la UPM como usuario externo. Inténtalño de nuevo: ";
               } else {
                  mensajeExternoCorreo = "Introduce un formato de correo electrónico correcto: ";
               }
            }
         }

         String contrasenaExternoMensaje = "Contrasena (min 12 caracteres, 1 mayuscula ,1 minuscula y 1 numero): ";
         boolean contrasenaExternoCorrecto = false;
         while (!contrasenaExternoCorrecto) {
            System.out.print(contrasenaExternoMensaje);
            String contraExterno = sc.nextLine();
            if (contraExterno.length() >= 12 && contraExterno.chars().anyMatch(Character::isDigit) && contraExterno.chars().anyMatch(Character::isUpperCase) && contraExterno.chars().anyMatch(Character::isLowerCase)) {
               datos.put("contrasena", contraExterno);
               contrasenaExternoCorrecto = true;
            } else {
               contrasenaExternoMensaje = "Introduce una contraseña válida (min 12 caracteres, 1 mayuscula ,1 minuscula y 1 numero): ";
            }
         }

         String dniExternoMensaje = "DNI: ";
         boolean dniExternoCorrecto = false;
         while (!dniExternoCorrecto) {
            System.out.print(dniExternoMensaje);
            String dniExterno = sc.nextLine();
            if (dniExterno.length() == 9 && dniExterno.chars().limit(8).allMatch(Character::isDigit) && Character.isLetter(dniExterno.charAt(8))) {
               datos.put("DNI", dniExterno);
               dniExternoCorrecto = true;
            } else {
               dniExternoMensaje = "Introduce un DNI válido";
            }
         }

         String tarjetaExternoMensaje = "Tarjeta bancaria de 16 dígitos: ";
         boolean tarjetaExternoCorrecto = false;
         while (!tarjetaExternoCorrecto) {
            System.out.print(tarjetaExternoMensaje);
            String tarjetaExterno = sc.nextLine();
            if (tarjetaExterno.length() == 16 && tarjetaExterno.chars().allMatch(Character::isDigit)) {
               datos.put("tarjetaBancaria", tarjetaExterno);
               tarjetaExternoCorrecto = true;
            } else {
               tarjetaExternoMensaje = "Introduce una tarjeta bancaria correcta: ";
            }
         }

      }
      autenticable.registrarse(datos);
   }
}