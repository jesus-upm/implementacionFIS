package trabajo_fis.usuarios.dominio;

import java.time.LocalDate;

public class PersonalUPM extends ParticipanteUPM {
   private LocalDate fechaAntiguedad;
   private boolean esPDI;

   public PersonalUPM(String nickUsuario, String nombreCompleto, String email, String contrasena, String DNI, String tarjetaBancaria, LocalDate fechaAntiguedad, boolean esPDI) {
      super(nickUsuario, nombreCompleto, email, contrasena, DNI, tarjetaBancaria);
      this.fechaAntiguedad = fechaAntiguedad;
      this.esPDI = esPDI;
   }

   public LocalDate getFechaAntiguedad() {
      return fechaAntiguedad;
   }
   public void setFechaAntiguedad(LocalDate fechaAntiguedad) {
      this.fechaAntiguedad = fechaAntiguedad;
   }

   public boolean getEsPDI() {
      return esPDI;
   }
   public void setEsPDI(boolean esPDI) {
      this.esPDI = esPDI;
   }

   @Override
   public String toString() {
      return super.toString()+";fechaAntiguedad;"+getFechaAntiguedad()+";esPDI;"+ getEsPDI();
   }
}