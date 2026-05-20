package trabajo_fis.usuarios.dominio;

public class Instructor extends UsuarioDNI {
   private String IBAN;
   public Instructor(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI, String IBAN) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI);
      this.IBAN = IBAN;
   }

   public String getIBAN() {
      return IBAN;
   }
   public void setIBAN(String IBAN) {
      this.IBAN = IBAN;
   }

   @Override
   public String toString() {
      return super.toString()+";DNI:"+getDNI()+";IBAN:"+getIBAN();
   }
}
