// create administrador class that extends Usuario
package trabajo_fis.usuarios.dominio;
public class Administrador extends Usuario {
   private String nTelefono;

   public Administrador(String nick, String nombreCompleto, String email, String contraseña, String nTelefono) {
      super(nick, nombreCompleto, email, contraseña);
      this.nTelefono = nTelefono;
   }

   public String getnTelefono() {
      return nTelefono;
   }
   public void setnTelefono(String nTelefono) {
      this.nTelefono = nTelefono;
   }

   @Override
   public String toString() {
      return super.toString()+";nTelefono;"+getnTelefono();
   }
}
