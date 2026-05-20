package trabajo_fis.usuarios.factory;
import java.util.HashMap;

import trabajo_fis.usuarios.dominio.*;

public interface ICreadorUsuario {
	Usuario crearUsuario(HashMap<String, String> datos);
}
