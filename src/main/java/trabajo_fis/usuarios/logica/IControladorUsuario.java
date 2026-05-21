package trabajo_fis.usuarios.logica;

import trabajo_fis.usuarios.dominio.PreferenciaArtistica;

import java.util.HashMap;
import java.util.List;

public interface IControladorUsuario {
   String getPreferenciaArtistica();
   void addPreferenciaArtistica(String preferenciaArtistica, int nivel);
   void removePreferenciaArtistica(String preferenciaArtistica);
   void darseDeBaja();
   boolean bajaInstructor(String email);
   void altaInstructor(HashMap<String, String> datos);
   String getInstructor(String email);
   String getParticipante(String email);

   List<PreferenciaArtistica> getPreferenciaArtistica();
   String getTipoUsuario();
   void altaInstructor(HashMap<String, String> datos);
   boolean bajaInstructor(String correo);
   void addPreferenciaArtistica(PreferenciaArtistica preferencia);
   void eliminarPreferenciaArtistica(int numPreferencia);
}
