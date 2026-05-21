package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.dominio.PreferenciaArtistica;
import trabajo_fis.usuarios.dominio.TipoDisciplina;
import trabajo_fis.usuarios.logica.IControladorUsuario;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class VistaUsuarios implements IVistaUsuarios {
   private IControladorUsuario iControladorUsuario;


   public VistaUsuarios(IControladorUsuario iControladorUsuario) {
      this.iControladorUsuario = iControladorUsuario;
   }

   @Override
   public void mostrarPreferenciaArtistica() {
      String prefArtist = iControladorUsuario.getPreferenciaArtistica();
      System.out.println(prefArtist);
      //for(PreferenciaArtistica pref : prefArtist){
      //   System.out.println("Disciplina: " + pref.getTipo() + ", Nivel de experiencia: " + pref.getNivelExperiencia());
      //}
   }

   @Override
   public void modificarPreferenciaArtistica() {

      Scanner sc = new Scanner(System.in);

      mostrarPreferenciaArtistica();
      System.out.println("¿Que accion quieres realizar(introduce el número de la opciçon que prefieras?\n" +
              "1. Añadir nueva preferencia artística\n" +
              "2. Eliminar preferencia artística\n" +
              "3. Modificar nivel de experiencia de una preferencia artística");
      String eleccionModificar = sc.nextLine();
      switch (eleccionModificar){
            case "1":
                System.out.println("Que disciplina artística quieres añadir? (Introduce el número de la opción que prefieras)\n" +
                        "1. Música\n" +
                        "2. Pintura\n" +
                        "3. Teatro\n");
                String tipo = sc.nextLine();
                TipoDisciplina tDisciplina= null;
                switch (tipo){
                    case "1":
                       tDisciplina = TipoDisciplina.MUSICA;
                        break;
                    case "2":
                       tDisciplina = TipoDisciplina.PINTURA;
                        break;
                    case "3":
                       tDisciplina = TipoDisciplina.TEATRO;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        return;
                }

                System.out.println("Introduce el nivel de experiencia: ");
                String nivel = sc.nextLine();
                PreferenciaArtistica nuevaPreferencia = new PreferenciaArtistica(tDisciplina, Integer.parseInt(nivel));
                iControladorUsuario.addPreferenciaArtistica(nuevaPreferencia.toString(), Integer.parseInt(nivel));
                break;
            case "2":
                System.out.println("Introduce el tipo de disciplina artística a eliminar: ");
                String tipoEliminar = sc.nextLine();
                // Aquí deberías llamar a un método del controlador para eliminar la preferencia artística
                // iControladorUsuario.eliminarPreferenciaArtistica(tipoEliminar);
                break;
            case "3":
                System.out.println("Introduce el tipo de disciplina artística a modificar: ");
                String tipoModificar = sc.nextLine();
                System.out.println("Introduce el nuevo nivel de experiencia: ");
                String nuevoNivel = sc.nextLine();

                //TODO: FROMSTRING
                //PreferenciaArtistica preferenciaModificada = new PreferenciaArtistica(tipoModificar.toString(), Integer.parseInt(nuevoNivel));
      }

         // Aquí deberías llamar a un método del controlador para actualizar la preferencia artística
         // iControladorUsuario.cambiarPreferenciaArtistica(nuevaPreferencia);
   }

   @Override
   public void darseDeBaja() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void bajaInstructor() {
      // TODO Auto-generated method stub
      Scanner sc = new Scanner(System.in);
      System.out.println("Correo de instructor a dar de baja: ");
      String correo = sc.nextLine();
      if(iControladorUsuario.bajaInstructor(correo)){
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

      datos.put("tipoUsuario", "Instructor");

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

      iControladorUsuario.altaInstructor(datos);
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