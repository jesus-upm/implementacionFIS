package trabajo_fis.usuarios.vistas;

public interface IAutenticable {
   public boolean iniciarSesion(String email, String contrasena);
   public void registrarse(String nick, String nombreCompleto, String correoElectronico, String contrasena, String DNI, String tarjetaBancaria);
}
