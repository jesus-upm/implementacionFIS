package trabajo_fis.usuarios.dominio;

public class Usuario {
   private String nickUsuario;
   private String nombreCompleto;
   private String email;
   private String contraseña;

   public Usuario(String nickUsuario, String nombreCompleto, String email, String contraseña) {
      this.nickUsuario = nickUsuario;
      this.nombreCompleto = nombreCompleto;
      this.email = email;
      this.contraseña = contraseña;
   }

   public boolean comprobarUsuario(String nick, String contraseña) {
      return this.nickUsuario.equals(nick) && this.contraseña.equals(contraseña);
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
   
   public TipoUsuario getTipoUsuario() {
	   
	   return null;
   };
   
}