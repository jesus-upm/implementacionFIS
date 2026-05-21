package trabajo_fis.usuarios.dominio;

public abstract class ParticipanteUPM extends ParticipanteExterno {
   public ParticipanteUPM(String nickUsuario, String nombreCompleto, String email, String contrasena, String DNI, String tarjetaBancaria) {
      super(nickUsuario, nombreCompleto, email, contrasena, DNI, tarjetaBancaria);
   }
}