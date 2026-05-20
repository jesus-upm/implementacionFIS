package trabajo_fis.usuarios.logica;

import java.util.HashMap;

public interface IControladorUsuario {
   String getPreferenciaArtistica();
   String getTipoUsuario();
   void altaInstructor(HashMap<String, String> datos);
   boolean bajaInstructor(String correo);
}
