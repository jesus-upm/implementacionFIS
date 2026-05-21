package trabajo_fis.usuarios.logica;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import trabajo_fis.usuarios.dominio.*;
import trabajo_fis.usuarios.factory.ICreadorUsuario;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;

public class ControladorUsuario implements IControladorUsuario, IAutenticable, IObtenerSesion {
   private List<Usuario> usuarios;
   private Usuario usuarioLogueado;
   private ICreadorUsuario creadorUsuarios;

   private IPersistenciaUsuarios persistenciaUsuarios;

   public ControladorUsuario(ICreadorUsuario factoria, IPersistenciaUsuarios persistencia) {
      creadorUsuarios = factoria;
      persistenciaUsuarios = persistencia;
      usuarios = persistenciaUsuarios.cargarTodos();
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
   public void cerrarSesion() {
      usuarioLogueado = null;
   }

   @Override
   public void registrarse(HashMap<String, String> datos) {

      Usuario usuarioRegistrado = comprobarYCrearUsuario(datos);

      if (usuarioRegistrado == null) {
         System.out.println("Error: no se pudo crear el usuario");
         return;
      }

      usuarioLogueado=usuarioRegistrado;
      persistenciaUsuarios.insertar(usuarioRegistrado);
      usuarios.add(usuarioRegistrado);

      System.out.println("Usuario registrado correctamente: " + datos.get("tipoUsuario"));

   }

   @Override
   public String getPreferenciaArtistica() {
      return "cositas";//return (ParticipanteExterno) usuarioLogueado.getPreferenciaArtistica();
   }

   public void addPreferenciaArtistica(String preferenciaArtistica, int nivel) {

   }
   public void removePreferenciaArtistica(String preferenciaArtistica) {

   }

   @Override
   public void darseDeBaja(){

   }

   public boolean bajaInstructor(String email){
      Iterator<Usuario> iterator = usuarios.iterator();

      while (iterator.hasNext()) {
         Usuario usuario = iterator.next();

         if (usuario.getEmail().equalsIgnoreCase(email)
                 && usuario.getClass().getSimpleName().equals("Instructor")) {
            usuarios.remove(usuario);
            persistenciaUsuarios.borrar(email);
            return true;
         }
      }

      return false;
   }

   public void altaInstructor(HashMap<String, String> datos){
      Usuario usuarioRegistrado = comprobarYCrearUsuario(datos);
      if (usuarioRegistrado == null) {
         System.out.println("Error: no se pudo crear el instructor");
         return;
      }
      persistenciaUsuarios.insertar(usuarioRegistrado);
      usuarios.add(usuarioRegistrado);
      System.out.println("Instructor creado correctamente ");
   }

   public String getInstructor(String email) {
      Iterator<Usuario> iterator = usuarios.iterator();

      while (iterator.hasNext()) {
         Usuario usuario = iterator.next();

         if (usuario.getEmail().equalsIgnoreCase(email)
                 && usuario instanceof Instructor) {

            return usuario.toString();
         }
      }
      return "Instructor no encontrado";
   }
   public String getParticipante(String email) {
      Iterator<Usuario> iterator = usuarios.iterator();

      while (iterator.hasNext()) {
         Usuario usuario = iterator.next();

         if (usuario.getEmail().equalsIgnoreCase(email) && usuario instanceof ParticipanteExterno) {

            return usuario.toString();
         }
      }
      return "Participante no encontrado";
   }
   
   @Override
   public String getTipoUsuario() {
      return (usuarioLogueado != null) ? usuarioLogueado.getClass().getSimpleName() : null;
   }

   private boolean validarContrasena(String password) {

      if (password == null) return false;

      if (password.length() < 12) return false;

      return password != null &&
              password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{12,}$");
   }

   private boolean validarNick(String nick) {

      if (nick == null) return false;

      List<String> blacklist=cargarPalabras();

      nick = nick.toLowerCase().trim();

      // reglas básicas
      if (nick.length() < 4 || nick.length() > 12) return false;

      if (!nick.matches("^[a-zA-Z0-9]+$")) return false;

      // blacklist (tu archivo)
      if (blacklist.contains(nick)) return false;

      return true;
   }

   private List<String> cargarPalabras() {

      List<String> palabras = new ArrayList<>();

      try (BufferedReader br = new BufferedReader(
              new FileReader("src/Main/java/trabajo_fis/blacklist.txt"))) {

         String linea;

         while ((linea = br.readLine()) != null) {

            linea = linea.trim().toLowerCase();

            if (!linea.isEmpty() && !linea.startsWith("#")) {
               palabras.add(linea);
            }
         }

      } catch (IOException e) {
         System.out.println("Error cargando blacklist: " + e.getMessage());
      }

      return palabras;
   }

   private Usuario comprobarYCrearUsuario(HashMap<String, String> datos){
      if (!validarNick(datos.get("nickUsuario"))) {
         System.out.println("Error: nick inválido");
         return null;
      }

      if (!validarContrasena(datos.get("contrasena"))) {
         System.out.println("Error: contraseña inválida");
         return null;
      }

      if (comprobarCorreo(datos.get("correoElectronico"))) {
         System.out.println("Error: correo inválido");
         return null;
      }

      if (datos.get("DNI") == null || datos.get("DNI").isEmpty()) {
         System.out.println("Error: DNI inválido");
         return null;
      }

      String contrasenaHash = BCrypt.hashpw(datos.get("contrasena"), BCrypt.gensalt());
      datos.put("contrasena",contrasenaHash);


      Usuario usuarioRegistrado = creadorUsuarios.crearUsuario(datos);
      return usuarioRegistrado;
   }

   public boolean comprobarCorreo(String correo) {
      if(correo == null ||
              correo.isEmpty()){
         return true;
      }
      for (Usuario usuario : usuarios) {
         if (usuario.getEmail().equalsIgnoreCase(correo)) {
            return true;
         }
      }

      return false;
   }


}
