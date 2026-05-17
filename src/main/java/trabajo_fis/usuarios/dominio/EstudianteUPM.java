package trabajo_fis.usuarios.dominio;

public class EstudianteUPM extends ParticipanteUPM {
   private String numMatricula;
   
   public EstudianteUPM(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI,String rolUpm, String tarjetaBancaria, String numMatricula) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI, tarjetaBancaria,rolUpm);
      this.numMatricula = numMatricula;
   }   
}
