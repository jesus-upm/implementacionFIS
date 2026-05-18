package trabajo_fis.usuarios.dominio;

public class ParticipanteUPM extends ParticipanteExterno {
   private String rolUPM;
   public ParticipanteUPM(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI, String tarjetaBancaria,String rolUPM) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI, tarjetaBancaria);
      this.rolUPM=rolUPM;
   }

   public String getRolUPM() {
      return rolUPM;
   }

   public void setRolUPM(String rolUPM) {
      this.rolUPM = rolUPM;
   }
   
}
