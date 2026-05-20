package trabajo_fis.usuarios.logica;

import trabajo_fis.usuarios.factory.ICreadorUsuario;

import java.util.HashMap;

public interface IAutenticable {
   boolean iniciarSesion(String email, String contrasena);
   void registrarse(ICreadorUsuario factoria, HashMap<String, String> datos);
}
