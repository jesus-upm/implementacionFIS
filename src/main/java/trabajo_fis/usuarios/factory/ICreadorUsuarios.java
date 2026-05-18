package trabajo_fis.usuarios.factory;

import trabajo_fis.usuarios.dominio.Usuario;

import java.util.HashMap;

public interface ICreadorUsuarios {
     Usuario crearUsuario(HashMap<String,String> datos);
}
