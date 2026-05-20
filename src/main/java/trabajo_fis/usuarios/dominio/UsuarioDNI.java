package trabajo_fis.usuarios.dominio;

public abstract class UsuarioDNI extends Usuario {
   private String DNI;

   public UsuarioDNI(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI) {
      super(nickUsuario, nombreCompleto, email, contraseña);
      this.DNI = DNI;
   }

   public String getDNI() {
      return DNI;
   }

   public void setDNI(String DNI) {
      this.DNI = DNI;
   }

   public String toString() {

      return super.toString()+";DNI:"+getDNI();
   }


}
