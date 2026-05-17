package trabajo_fis.usuarios.dominio;

import java.time.LocalDate;

public class PersonalUPM extends ParticipanteUPM {
   private LocalDate fechaAntiguedad;
   private boolean esPDI;

   public PersonalUPM(String nickUsuario, String nombreCompleto, String email, String contraseña, String DNI, String tarjetaBancaria,String rolUPM, LocalDate fechaAntiguedad, boolean esPDI) {
      super(nickUsuario, nombreCompleto, email, contraseña, DNI, tarjetaBancaria,rolUPM);
      this.fechaAntiguedad = fechaAntiguedad;
      this.esPDI = esPDI;
   }

   public LocalDate getFechaAntiguedad() {
      return fechaAntiguedad;
   }
   public void setFechaAntiguedad(LocalDate fechaAntiguedad) {
      this.fechaAntiguedad = fechaAntiguedad;
   }

   public boolean isEsPDI() {
      return esPDI;
   }
   public void setEsPDI(boolean esPDI) {
      this.esPDI = esPDI;
   }
}
