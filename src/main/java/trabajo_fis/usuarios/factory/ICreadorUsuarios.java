package trabajo_fis.usuarios.factory;
import java.util.HashMap;

import trabajo_fis.usuarios.dominio.*;

public interface ICreadorUsuarios {

	public Usuario crearUsuario(HashMap<String, String> datos);

}
