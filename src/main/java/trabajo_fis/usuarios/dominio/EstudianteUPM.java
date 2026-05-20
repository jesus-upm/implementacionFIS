package trabajo_fis.usuarios.dominio;

public class EstudianteUPM extends ParticipanteUPM {
   private String numMatricula;
   
   public EstudianteUPM(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI, String tarjetaBancaria, String numMatricula) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI, tarjetaBancaria);
      this.numMatricula = numMatricula;
   }

   public void setNumMatricula(String numMatricula) {this.numMatricula = numMatricula; }
   public String getNumMatricula() {
      return numMatricula;
   }

   @Override
   public String toString() {
      return super.toString()+";numMatricula:"+getNumMatricula();
   }
}
