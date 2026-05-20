package trabajo_fis.usuarios.persistencia;
import java.util.List;

import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuarios;

public interface IPersistenciaUsuarios {
   public List<Usuario> cargarTodos(ICreadorUsuarios factoria);
   public void insertar(Usuario usuario);
   public void borrar(String email);
   public Usuario seleccionar(String email);
   public void actualizar(Usuario usuario);
}