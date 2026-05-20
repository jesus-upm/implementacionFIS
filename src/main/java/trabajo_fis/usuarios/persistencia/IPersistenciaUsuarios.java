package trabajo_fis.usuarios.persistencia;
import java.util.List;

import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuario;

public interface IPersistenciaUsuarios {
   List<Usuario> cargarTodos();
   void insertar(Usuario usuario);
   void borrar(String email);
   Usuario seleccionar(String email);
   void actualizar(Usuario usuario);
}