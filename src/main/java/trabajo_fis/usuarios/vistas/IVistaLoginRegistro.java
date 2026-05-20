package trabajo_fis.usuarios.vistas;

import trabajo_fis.usuarios.factory.ICreadorUsuario;

public interface IVistaLoginRegistro {
   public void iniciarSesion();
   public void registrarse(ICreadorUsuario factoria);
}
