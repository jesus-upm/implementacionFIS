package trabajo_fis.usuarios.dominio;

import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

public abstract class Usuario {
   private String nickUsuario;
   private String nombreCompleto;
   private String email;
   private String contrasena;

   public Usuario(String nickUsuario, String nombreCompleto, String email, String contrasena) {
      this.nickUsuario = nickUsuario;
      this.nombreCompleto = nombreCompleto;
      this.email = email;
      this.contrasena = contrasena;
   }

   public boolean comprobarUsuario(String email, String contrasena) {
      return this.email.equals(email) && BCrypt.checkpw(contrasena, this.contrasena);
   }


   public String getNickUsuario() {
      return nickUsuario;
   }
   public String getNombreCompleto() {
      return nombreCompleto;
   }
   public String getEmail() {
      return email;
   }
   public String getContrasena() {
      return contrasena;
   }
   public void setNickUsuario(String nickUsuario) {
      this.nickUsuario = nickUsuario;
   }
   public void setNombreCompleto(String nombreCompleto) {
      this.nombreCompleto = nombreCompleto;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public void setContrasena(String contrasena) {
      this.contrasena = contrasena;
   }

   @Override
   public String toString() {return "tipoUsuario;"+this.getClass().getSimpleName()+";nickUsuario;"+nickUsuario + ";nombreCompleto;"+ nombreCompleto + ";correoElectronico;"+ email + ";contrasena;"+contrasena;}
}