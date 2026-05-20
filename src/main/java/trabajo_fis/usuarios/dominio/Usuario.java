package trabajo_fis.usuarios.dominio;

import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

public class Usuario {
   private String nickUsuario;
   private String nombreCompleto;
   private String email;
   private String contraseña;
   private List<PreferenciasArtisticas> preferencias;

   public Usuario(String nickUsuario, String nombreCompleto, String email, String contraseña) {
      this.nickUsuario = nickUsuario;
      this.nombreCompleto = nombreCompleto;
      this.email = email;
      this.contraseña = contraseña;
   }
   
   public boolean añadirPreferencia(PreferenciasArtisticas p) {
       if (preferencias.size() >= 3) {
           return false; // No se puede añadir más
       }
       preferencias.add(p);
       return true;
   }

   public boolean eliminarPreferencia(PreferenciasArtisticas p) {
       return preferencias.remove(p);
   }
   
   public boolean modificarPreferencia(int indice, int nuevoNExp) {
	    if (indice < 0 || indice >= preferencias.size()) {
	        return false; // índice inválido
	    }

	    preferencias.get(indice).cambiarPreferencia(nuevoNExp);
	    return true;
	}

   public boolean comprobarUsuario(String nick, String contraseña) {
      return this.nickUsuario.equals(nick) && BCrypt.checkpw(contraseña, this.contraseña);
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
      return contraseña;
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

   public void setContrasena(String contraseña) {
      this.contraseña = contraseña;
   }

   public String toString() {
      return nickUsuario + "," + nombreCompleto + "," + email + "," + contraseña;
   }
   
}