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
      Scanner scanner = new Scanner(System.in);
      System.out.print("Correo electrónico: ");
      String email = scanner.nextLine();
      System.out.print("Contraseña: ");
      String contrasena = scanner.nextLine();
      if(autenticable.iniciarSesion(email,contrasena)){
         System.out.println("Se inico sesion correctamente");
      }
      else {
         System.out.println("Error al iniciar sesion contraseña/correro invalido");
      }
   }

   @Override
   public void registrarse(ICreadorUsuario factoria) {
      Scanner sc = new Scanner(System.in);

      HashMap<String, String> datos = new HashMap<>();

// === SELECCIÓN DE MÉTODO DE REGISTRO ===
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
         System.out.println("Introduce tu nick: ");
         String nickUsuario = sc.nextLine();
         datos.put("nickUsuario", nickUsuario);
         // getId() es el nick
         System.out.println("Introduce tu nombreCompleto: ");
         String nombreCompleto = sc.nextLine();
         datos.put("nombreCompleto", nombreCompleto);

         datos.put("correoElectronico", userData.getEmail());

         System.out.println("Introduce la contrasena (min 12 caracteres, 1 mayuscula ,1 minuscula y 1 numero)");
         String contraseña = sc.nextLine();
         datos.put("contrasena", contraseña);

         System.out.println("Introduce tu DNI: ");
         String dni = sc.nextLine();
         datos.put("DNI", dni);

         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", sc.nextLine());




         // Datos específicos según el rol que devuelve el LDAP
         String tipo = userData.getRol().toString();
         if (tipo.equals("ALUMNO")) {
            datos.put("tipoUsuario","EstudianteUPM");

            System.out.print("Número de matrícula: ");
            datos.put("numMatricula", sc.nextLine());





         } else {
            datos.put("tipoUsuario","PersonalUPM");


            System.out.print("Fecha de antigüedad (yyyy-MM-dd): ");
            String fechaStr = sc.nextLine().trim();
            datos.put("fechaAntiguedad", fechaStr);

            if(tipo.equals("PDI")){
               datos.put("esPDI","true");
            }
            else {
               datos.put("esPDI","false");
            }

         }

      } else {
         datos.put("tipoUsuario","ParticipanteExterno");

         System.out.print("Nick usuario: ");
         datos.put("nickUsuario", sc.nextLine());

         System.out.print("Nombre completo: ");
         datos.put("nombreCompleto", sc.nextLine());

         System.out.print("Correo electrónico: ");
         datos.put("correoElectronico", sc.nextLine());

         System.out.print("Contrasena (min 12 caracteres, 1 mayuscula ,1 minuscula y 1 numero): ");
         datos.put("contrasena", sc.nextLine());

         System.out.print("DNI: ");
         datos.put("DNI", sc.nextLine());


         System.out.print("Tarjeta bancaria: ");
         datos.put("tarjetaBancaria", sc.nextLine());

      }

      autenticable.registrarse(datos);
   }
}