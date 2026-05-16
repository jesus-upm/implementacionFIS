package trabajo_fis.usuarios.logica;

import java.util.List;

import trabajo_fis.usuarios.dominio.ParticipanteExterno;
import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;
import trabajo_fis.usuarios.persistencia.PersistenciaUsuarios;
import trabajo_fis.usuarios.vistas.IAutenticable;

public class ControladorUsuario implements IControladorUsuario, IAutenticable {
   private List<Usuario> usuarios;
   private Usuario usuarioLogueado;

   private IPersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();

   public ControladorUsuario() {
      usuarios = persistenciaUsuarios.cargarTodos();
   }

   @Override
   public String getPreferenciaArtistica() {
      return "cositas";//return (ParticipanteExterno) usuarioLogueado.getPreferenciaArtistica();
   }

   @Override
   public String getTipoUsuario() {
      return (usuarioLogueado != null) ? usuarioLogueado.getClass().getSimpleName() : "null";
   }

   @Override
   public boolean iniciarSesion(String email, String contrasena) {
      for (Usuario usuario : usuarios) {
         if (usuario.comprobarUsuario(email, contrasena)) {
            usuarioLogueado = usuario;
            return true;
         }  
      }
      return false;
   }

   @Override
   public void registrarse(String nick, String nombreCompleto, String correoElectronico, String contrasena, String DNI, String tarjetaBancaria) {
      Usuario nuevoUsuario = new ParticipanteExterno(nick, nombreCompleto, correoElectronico, contrasena, DNI, tarjetaBancaria);
      usuarios.add(nuevoUsuario);
      persistenciaUsuarios.insertar(nuevoUsuario);
   }
}
