package trabajo_fis.usuarios.logica;

import trabajo_fis.usuarios.factory.ICreadorUsuarios;

import java.util.HashMap;

public interface IAutenticable {
   public boolean iniciarSesion(String email, String contrasena);
   public void registrarse(ICreadorUsuarios factoria,  HashMap<String, String> datos);
}
