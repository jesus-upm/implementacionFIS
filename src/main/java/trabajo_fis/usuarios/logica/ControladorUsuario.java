package trabajo_fis.usuarios.logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import trabajo_fis.usuarios.dominio.ParticipanteExterno;
import trabajo_fis.usuarios.dominio.Usuario;
import trabajo_fis.usuarios.factory.ICreadorUsuarios;
import trabajo_fis.usuarios.persistencia.IPersistenciaUsuarios;
import trabajo_fis.usuarios.persistencia.PersistenciaUsuarios;

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
   public void registrarse(ICreadorUsuarios factoria, HashMap<String, String> datos) {
      if (!validarNick(datos.get("nickUsuario"))) {
         System.out.println("Error: nick inválido");
         return;
      }

      if (!validarContraseña(datos.get("contraseña"))) {
         System.out.println("Error: contraseña inválida");
         return;
      }

      if (comprobarCorreo(datos.get("correoElectronico"))) {
         System.out.println("Error: correo inválido");
         return;
      }

      if (datos.get("DNI") == null || datos.get("DNI").isEmpty()) {
         System.out.println("Error: DNI inválido");
         return;
      }

      // 2. VALIDACIÓN POR TIPO
      String tipo = datos.get("tipoUsuario");

      if (!validarCamposPorTipo(tipo, datos)) {
         System.out.println("Error: campos específicos inválidos");
         return;
      }


      Usuario usuarioRegistrado = factoria.crearUsuario(datos);

      if (usuarioRegistrado == null) {
         System.out.println("Error: no se pudo crear el usuario");
         return;
      }

      usuarioLogueado=usuarioRegistrado;
      persistenciaUsuarios.insertar(usuarioRegistrado);
      usuarios.add(usuarioRegistrado);

      System.out.println("Usuario registrado correctamente: " + tipo);

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


   private boolean validarCamposPorTipo(String tipo,
                                        HashMap<String, String> datos) {

      switch (tipo) {

         case "instructor":
            return datos.get("IBAN") != null;

         case "participanteExterno":
            return datos.get("tarjetaBancaria") != null;

         case "estudianteUPM":
            return datos.get("tarjetaBancaria") != null &&
                    datos.get("rolUPM") != null &&
                    datos.get("numMatricula") != null;

         case "personalUPM":
            return datos.get("tarjetaBancaria") != null &&
                    datos.get("rolUPM") != null &&
                    datos.get("fechaAntiguedad") != null &&
                    datos.get("esPDI") != null;

         default:
            return false;
      }
   }



   private boolean validarContraseña(String password) {

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
              new FileReader("src/main/java/trabajo_fis/blacklist.txt"))) {

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
}
