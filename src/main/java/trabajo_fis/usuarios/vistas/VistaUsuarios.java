package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.logica.IControladorUsuario;

import java.util.HashMap;
import java.util.Scanner;

public class VistaUsuarios implements IVistaUsuarios {
   private IControladorUsuario controladorUsuario;

   public VistaUsuarios(IControladorUsuario controladorUsuario) {
      this.controladorUsuario = controladorUsuario;
   }

   @Override
   public void mostrarPreferenciaArtistica() {
      String preferencia = controladorUsuario.getPreferenciaArtistica();
      System.out.println(preferencia);
   }

   @Override
   public void cambiarPreferenciaArtistica() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void darseDeBaja() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void bajaInstructor() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Correo de instructor a dar de baja: ");
      String correo = sc.nextLine();
      if(controladorUsuario.bajaInstructor(correo)){
         System.out.println("Instructor borrado con exito");
      }
      else {
         System.out.println("Error en la eliminacion del instructor");
      }
   }

   @Override
   public void altaInstructor() {

      Scanner sc = new Scanner(System.in);
      HashMap<String,String> datos = new HashMap<>();

      datos.put("tipoUsuario", "instructor");

      System.out.print("Nick usuario: ");
      datos.put("nickUsuario", sc.nextLine());

      System.out.print("Nombre completo: ");
      datos.put("nombreCompleto", sc.nextLine());

      System.out.print("Correo electrónico: ");
      datos.put("correoElectronico", sc.nextLine());

      System.out.print("Contraseña: ");
      datos.put("contrasena", sc.nextLine());

      System.out.print("DNI: ");
      datos.put("DNI", sc.nextLine());

      System.out.print("IBAN: ");
      datos.put("IBAN", sc.nextLine());

      controladorUsuario.altaInstructor(datos);
   }

   @Override
   public void mostrarInstructor() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mostrarParticipante() {
      // TODO Auto-generated method stub
      
   }
   
}