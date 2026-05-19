package trabajo_fis.usuarios.logica;

import trabajo_fis.usuarios.factory.ICreadorUsuarios;

import java.util.HashMap;

public interface IControladorUsuario {
   public String getPreferenciaArtistica();
   public String getTipoUsuario();
   public void altaInstructor(ICreadorUsuarios factoria, HashMap<String, String> datos);
   public boolean bajaInstructor(String correo);
}
