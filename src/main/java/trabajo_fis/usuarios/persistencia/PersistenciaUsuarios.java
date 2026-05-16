package trabajo_fis.usuarios.persistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trabajo_fis.usuarios.dominio.Usuario;

public class PersistenciaUsuarios implements IPersistenciaUsuarios {
   private static final String ARCHIVO = System.getProperty("user.dir") + File.separator + "usuarios.txt";
   
   @Override
   public List<Usuario> cargarTodos() {   
      File file = new File(ARCHIVO);
      if (!file.exists()) return new ArrayList<>();

      List<Usuario> usuarios = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
         String linea;
         while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            Class<?> clase = Class.forName(partes[0]);
            Constructor<?> constructor = clase.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            String[] params = Arrays.copyOfRange(partes, 1, partes.length);
            usuarios.add((Usuario) constructor.newInstance((Object[]) params));
         }
      } catch (Exception e) {
         System.out.println(e);
      }
      return usuarios;
   }

   @Override
   public void insertar(Usuario usuario) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
         bw.newLine();
         bw.write(usuario.getClass().getName());
         bw.write(",");
         bw.write(usuario.toString());
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   @Override
   public void borrar(String email) {
      System.out.println("Usuario borrado con email: " + email);
   }

   @Override
   public Usuario seleccionar(String email) {
      System.out.println("Usuario seleccionado con email: " + email);
      return new Usuario("nick", "Nombre Completo", email, "contraseña");
   }

   @Override
   public void actualizar(Usuario usuario) {
      System.out.println("Usuario actualizado: " + usuario.getNickUsuario());
   }
   
}
