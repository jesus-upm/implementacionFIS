package trabajo_fis.usuarios.logica;

import java.util.HashMap;

public interface IControladorUsuario {
   String getPreferenciaArtistica();
   void addPreferenciaArtistica(String preferenciaArtistica, int nivel);
   void removePreferenciaArtistica(String preferenciaArtistica);
   void darseDeBaja();
   boolean bajaInstructor(String email);
   void altaInstructor(HashMap<String, String> datos);
   String getInstructor(String email);
   String getParticipante(String email);
}
