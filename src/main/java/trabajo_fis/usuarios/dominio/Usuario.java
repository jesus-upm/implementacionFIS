package trabajo_fis.usuarios.dominio;

import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

public class Usuario {
   private String nickUsuario;
   private String nombreCompleto;
   private String email;
   private String contrasena;
   private List<PreferenciasArtisticas> preferencias;

   public Usuario(String nickUsuario, String nombreCompleto, String email, String contrasena) {
      this.nickUsuario = nickUsuario;
      this.nombreCompleto = nombreCompleto;
      this.email = email;
      this.contrasena = contrasena;
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
      return this.nickUsuario.equals(nick) && BCrypt.checkpw(contraseña, this.contrasena);
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
   public String toString() {
      return "nickUsuario:"+getNickUsuario()+";nombreCompleto:"+getNombreCompleto()+";correoElectronico:"+getEmail()+";contraseña:"+getContrasena();
   }
}