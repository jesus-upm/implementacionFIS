package trabajo_fis.usuarios.dominio;

public class ParticipanteUPM extends ParticipanteExterno {
   public ParticipanteUPM(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI, String tarjetaBancaria) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI, tarjetaBancaria);

   }

   @Override
   public String toString() {
      return super.toString();
   }
}
