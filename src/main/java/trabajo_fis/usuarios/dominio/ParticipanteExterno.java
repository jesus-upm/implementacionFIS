package trabajo_fis.usuarios.dominio;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteExterno extends UsuarioDNI {
   private String tarjetaBancaria;
   private int descuento = 200; //TODO: valor de descuento a definir

   private List<PreferenciaArtistica> preferenciasArtisticas = new ArrayList<>();

   public ParticipanteExterno(String nickUsuario, String nombreCompleto, String email, String contrasena, String DNI, String tarjetaBancaria) {
      super(nickUsuario, nombreCompleto, email, contrasena, DNI);
      this.tarjetaBancaria = tarjetaBancaria;
   }

   public String getTarjetaBancaria() {
      return tarjetaBancaria;
   }
   public void setTarjetaBancaria(String tarjetaBancaria) {
      this.tarjetaBancaria = tarjetaBancaria;
   }
   public int getDescuento() {
      return descuento;
   }
   public void setDescuento(int descuento) {
      this.descuento = descuento;
   }

   public String toString() {
      return super.toString()+";tarjetaBancaria;"+getTarjetaBancaria();
   }
}
