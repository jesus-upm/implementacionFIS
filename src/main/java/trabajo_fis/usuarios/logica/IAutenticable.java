package trabajo_fis.usuarios.logica;

import java.util.HashMap;

public interface IAutenticable {
   boolean iniciarSesion(String email, String contrasena);
   void registrarse(HashMap<String, String> datos);
}
