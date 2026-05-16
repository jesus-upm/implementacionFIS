package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.logica.IControladorUsuario;

public class VistaUsuarios implements IVistaUsuarios {
   private IControladorUsuario iControladorUsuario;

   @Override
   public void mostrarPreferenciaArtistica() {
      String preferencia = iControladorUsuario.getPreferenciaArtistica();
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
      // TODO Auto-generated method stub
      
   }

   @Override
   public void altaInstructor() {
      // TODO Auto-generated method stub
      
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