package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.factory.ICreadorUsuarios;

public interface IVistaUsuarios {
   public void mostrarPreferenciaArtistica();
   public void cambiarPreferenciaArtistica();
   public void darseDeBaja();
   public void bajaInstructor();
   public void altaInstructor(ICreadorUsuarios factoria);
   public void mostrarInstructor();
   public void mostrarParticipante();
}
