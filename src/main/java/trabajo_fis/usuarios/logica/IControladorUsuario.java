package trabajo_fis.usuarios.logica;

import trabajo_fis.usuarios.dominio.PreferenciaArtistica;

import java.util.HashMap;
import java.util.List;

public interface IControladorUsuario {
   void darseDeBaja();
   boolean bajaInstructor(String email);
   void altaInstructor(HashMap<String, String> datos);
   String getInstructor(String email);
   String getParticipante(String email);
}
