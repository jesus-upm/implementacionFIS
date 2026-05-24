package trabajo_fis.usuarios.dominio;
public class Administrador extends Usuario {
   private String numeroTelefono;

   public Administrador(String nick, String nombreCompleto, String email, String contrasena, String numeroTelefono) {
      super(nick, nombreCompleto, email, contrasena);
      this.numeroTelefono = numeroTelefono;
   }

   public String getNumeroTelefono() {
      return numeroTelefono;
   }
   public void setNumeroTelefono(String numeroTelefono) {
      this.numeroTelefono = numeroTelefono;
   }

   @Override
   public String toString() {
      return super.toString()+";nTelefono;"+ getNumeroTelefono();
   }
}
