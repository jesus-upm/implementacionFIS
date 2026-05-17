package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.factory.ICreadorUsuarios;

public interface IVistaLoginRegistro {
   public void iniciarSesion();
   public void registrarse(ICreadorUsuarios factoria);
}
